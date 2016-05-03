package us.fihgu.toolbox.http;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.CoderResult;

import us.fihgu.toolbox.web.SelectionHandler;

public class HTTPReadHandler implements SelectionHandler
{

	private HTTPServer server;

	public HTTPReadHandler(HTTPServer server)
	{
		this.server = server;
	}

	@Override
	public void handleSelection(SelectionKey selectionKey)
	{
		if (selectionKey.isReadable())
		{
			HTTPRequest request = (HTTPRequest) selectionKey.attachment();
			SocketChannel channel = (SocketChannel) selectionKey.channel();
			try
			{
				if (request.body != null)
				{
					this.readBody(selectionKey, channel, request);
				}
				else
				{
					this.readHeader(selectionKey, channel, request);
				}
			}
			catch (IOException e)
			{
				if (server.info)
				{
					System.out.println("disconnect: " + channel.socket().getRemoteSocketAddress());
				}
				this.closeChannel(selectionKey);
			}
			catch (BadRequestException e)
			{
				this.sendBadRequest(selectionKey);
			}
		}
	}

	/**
	 * @param selectionKey
	 * @throws IOException
	 */
	protected void readBody(SelectionKey selectionKey, SocketChannel channel, HTTPRequest request) throws IOException
	{
		int byteRead = channel.read(request.body);
		if (byteRead == -1)
		{
			throw new IOException("End of Stream");
		}

		if (!request.body.hasRemaining())
		{
			//body ByteBuffer is filled.
			this.onComplete(request, selectionKey);
		}
	}
	
	protected void readHeader(SelectionKey selectionKey, SocketChannel channel, HTTPRequest request) throws BadRequestException, IOException
	{
		//read to readBuffer
		int byteRead = channel.read(request.readBuffer);
		
		if (byteRead == -1)
		{
			throw new IOException("End of Stream");
		}
		else if(byteRead == 0)
		{
			//read nothing
			return;
		}
		else
		{
			//decode the read bytes.
			this.decode(request);
			
			//check if a full line is read.
			int index = -1;
			while((index = request.lineBuilder.indexOf("\r\n")) >= 0)
			{
				String line = request.lineBuilder.substring(0, index);
				this.processLine(selectionKey, request, line);
				request.lineBuilder.delete(0, index + 2);
			}
		}		
	}
	
	private void processLine(SelectionKey selectionKey, HTTPRequest request, String line) throws BadRequestException
	{
		if(request.method == null)
		{			
			//method is not read yet, try read method, path, version.
			String[] part = line.split(" ");
			
			if(part.length != 3)
			{
				//malformed request
				throw new BadRequestException();
			}
			
			try
			{
				//try read the method
				request.method = HTTPRequestMethod.valueOf(part[0].toUpperCase());
			}
			catch(IllegalArgumentException e)
			{
				//unknown request type
				throw new BadRequestException();
			}
			
			request.path = part[1];
			request.version = part[2].toUpperCase();
			
			if(!request.version.equals("HTTP/1.1") && !request.version.equals("HTTP/1.0"))
			{
				//unknown version
				throw new BadRequestException();
			}
		}
		else if(line.equals(""))
		{
			//end of head
			
			//initialize body buffer if there is a Content-Length header.
			String temp = request.headers.get("content-length");
			if(temp != null)
			{
				int length = -1;
				try
				{
					length = Integer.parseInt(temp);
				}
				catch(NumberFormatException e)
				{
					//Content Length is not a number
					throw new BadRequestException();
				}
				request.body = ByteBuffer.allocate(length);
			}
			else
			{
				//no Content-Length, treat as complete.
				this.onComplete(request, selectionKey);
			}
		}
		else
		{
			//register header
			int index = line.indexOf(':');
			if(index == -1)
			{
				//not a header
				throw new BadRequestException();
			}
			String key = line.substring(0, index).trim().toLowerCase();
			String value = line.substring(key.length() + 1).trim().replace("\n", "");
			request.headers.put(key, value);
		}
	}
	
	/**
	 * decode content in request's readBuffer, then put it into it's lineBuilder
	 */
	private void decode(HTTPRequest request)
	{
		//read something
		request.readBuffer.flip();
		
		//decode the bytes.
		CharBuffer charBuffer = CharBuffer.allocate(request.readBuffer.remaining());
		server.decoder.reset();
		CoderResult result = server.decoder.decode(request.readBuffer, charBuffer, true);
		server.decoder.flush(charBuffer);

		//replace unmapped characters
		if (result.isUnmappable())
		{
			charBuffer.append('?');
			request.readBuffer.position(request.readBuffer.position() + result.length());
		}
		
		//prepare the readBuffer for next read
		request.readBuffer.compact();
		
		//append decoded string to lineBuilder
		charBuffer.flip();
		request.lineBuilder.append(charBuffer.toString());
	}
	
	protected void onComplete(HTTPRequest request, SelectionKey selectionKey)
	{
		selectionKey.cancel();
		if(server.debug)
		{
			System.out.println(request);
		}
		
	}

	/**
	 * unregister the SelectionKey and close the channel.
	 */
	protected void closeChannel(SelectionKey selectionKey)
	{
		selectionKey.cancel();
		try
		{
			selectionKey.channel().close();
		}
		catch (IOException e1)
		{
			// game over can't even close a channel now.
			e1.printStackTrace();
		}
	}
	
	public void sendBadRequest(SelectionKey selectionKey)
	{
		selectionKey.cancel();
		System.out.println("BadRequest");
		//TODO: send to write Handler
	}

	@Override
	public int getDefaultInterestSet()
	{
		return SelectionKey.OP_READ;
	}
}
