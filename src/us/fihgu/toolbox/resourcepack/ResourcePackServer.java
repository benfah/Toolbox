package us.fihgu.toolbox.resourcepack;
import java.net.InetSocketAddress;
import us.fihgu.toolbox.http.FileContext;
import us.fihgu.toolbox.http.HTTPServer;
import us.fihgu.toolbox.http.StaticContextGenerator;
import us.fihgu.toolbox.network.NetworkUtils;
import us.fihgu.toolbox.Loader;

public class ResourcePackServer
{
	private static HTTPServer server;
	public static void startServer()
	{
		String localhost = Loader.instance.getConfig().getString("http.localhost");
		String host = Loader.instance.getConfig().getString("http.host");
		int port = Loader.instance.getConfig().getInt("http.port");
		int numReadThread = Loader.instance.getConfig().getInt("http.numReadThread");
		int numWriteThread = Loader.instance.getConfig().getInt("http.numWriteThread");
		
		if(localhost == null)
		{
			localhost = NetworkUtils.getLocalIP().getHostAddress();
		}
		
		if(host == null)
		{
			host = NetworkUtils.getExternalIP();
		}
		
		
		
		InetSocketAddress address = new InetSocketAddress(host , port);
		System.out.println(address);
		server = new HTTPServer(address);
		server.numReadThread = numReadThread;
		server.numWriteThread = numWriteThread;
		server.putContextGenerator("/resourcepack.zip", new StaticContextGenerator(new FileContext(Paths.get("./resourcepacks/resourcepack.zip"))));
		server.startServer();
	}
	
	public static void stopServer()
	{
		server.stopServer();
	}
}
