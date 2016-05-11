package us.fihgu.toolbox.resourcepack;
import java.net.InetSocketAddress;
import java.nio.file.Paths;

import us.fihgu.toolbox.http.FileContext;
import us.fihgu.toolbox.http.HTTPServer;
import us.fihgu.toolbox.http.StaticContextGenerator;
import us.fihgu.toolbox.network.NetworkUtils;
import us.fihgu.toolbox.Loader;

public class ResourcePackServer
{
	public static String localhost;
	public static String host;
	public static int port;
	
	public static String path;
	
	private static HTTPServer server;
	public static void startServer()
	{
		localhost = Loader.instance.getConfig().getString("http.localhost", NetworkUtils.getLocalIP().getHostAddress());
		host = Loader.instance.getConfig().getString("http.host", NetworkUtils.getExternalIP());
		port = Loader.instance.getConfig().getInt("http.port");
		int numReadThread = Loader.instance.getConfig().getInt("http.numReadThread");
		int numWriteThread = Loader.instance.getConfig().getInt("http.numWriteThread");
		
		int build = Loader.instance.getConfig().getInt("resource.build", 1);
		path = "/resourcepack" + build + ".zip";
		build++;
		Loader.instance.getConfig().set("resource.build", build);
		
		InetSocketAddress address = new InetSocketAddress(localhost , port);
		server = new HTTPServer(address);
		server.numReadThread = numReadThread;
		server.numWriteThread = numWriteThread;
		server.putContextGenerator(path, new StaticContextGenerator(new FileContext(Paths.get("./fihgu/toolbox/resource/resource.zip"))));
		server.startServer();
	}
	
	public static void stopServer()
	{
		server.stopServer();
	}
}
