import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket server;
	Socket client;
	DataInputStream is;
	int port;
	
	public Server(int port) throws IOException {
		this.port = port;
		server = new ServerSocket(port);
		
		init();
	}
	
	private void init() throws IOException {
		System.out.println("Waiting for client connection on " + port + "...");
		// Blocks until a client requests a connection
		client = server.accept();
		System.out.println("connected from IP: " + client.getRemoteSocketAddress());
		
		is = new DataInputStream(client.getInputStream());
		
		// track time
	    long startTime = System.currentTimeMillis();
	    byte[] readBuff= new byte[1000];
		int length = is.read(readBuff,0,readBuff.length);
		long bytes = length;
	    while (length != -1) {
	    	length = is.read(readBuff,0,readBuff.length);
			if (length > 0)
				bytes += length;
	    }
//	    long timeS = (System.nanoTime() - startTime) / 1000000000;
	    long timeMS = System.currentTimeMillis() - startTime;
	    is.close();
	    client.close();
	    server.close();
	    
	    long kbReceive = bytes / 1000;
	    
	    double rate = kbReceive * 8 / timeMS;
	    
	    System.out.println("received = " + kbReceive + " KB" + "; rate = " + rate + " Mbps");
	}

}
