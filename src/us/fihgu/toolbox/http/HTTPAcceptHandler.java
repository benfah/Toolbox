package us.fihgu.toolbox.http;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import us.fihgu.toolbox.web.SelectionHandler;

public class HTTPAcceptHandler implements SelectionHandler
{
	private HTTPServer server;
	
	public HTTPAcceptHandler(HTTPServer server)
	{
		this.server = server;
	}
	
	@Override
	public void handleSelection(SelectionKey selectionKey)
	{
		try
		{
			if(selectionKey.isAcceptable())
			{				
				SocketChannel client = ((ServerSocketChannel)selectionKey.channel()).accept();
				client.configureBlocking(false);
				
				HTTPRequest request = new HTTPRequest();
				server.readPool.getLightest().register(client, request);
				
				if(server.info)
				{
					System.out.println("connect: " + client.getRemoteAddress().toString());
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public int getDefaultInterestSet()
	{
		return SelectionKey.OP_ACCEPT;
	}
}
