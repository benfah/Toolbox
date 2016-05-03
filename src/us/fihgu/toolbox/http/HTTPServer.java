package us.fihgu.toolbox.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import us.fihgu.toolbox.web.ReadSelectorThreadPool;
import us.fihgu.toolbox.web.SelectionHandler;
import us.fihgu.toolbox.web.WebServer;

/**
 * This HTTP Server runs in its own thread.<br>
 * It uses non-block nio channels.<br>
 * @author fihgu
 *
 */
public class HTTPServer extends WebServer
{
	protected ReadSelectorThreadPool readPool;
	protected HTTPAcceptHandler acceptHandler = new HTTPAcceptHandler(this);
	protected CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
	
	public boolean debug = true;
	public boolean info = true;

	public HTTPServer(InetSocketAddress address)
	{
		super(address);
	}
	
	@Override
	protected void init() throws IOException
	{
		readPool = new ReadSelectorThreadPool(new HTTPReadHandler(this), 1);
		readPool.startAll();
		super.init();
	}
	
	

	@Override
	public void stopServer()
	{
		super.stopServer();
		readPool.closeAll();
	}

	@Override
	public SelectionHandler getAcceptHandler()
	{
		return acceptHandler;
	}
}
