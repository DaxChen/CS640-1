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
	    long startTime = System.nanoTime();
	    byte[] readBuff= new byte[1000];
		int length = is.read(readBuff,0,readBuff.length);
		long bytes = length;
	    while (length != -1) {
	    	length = is.read(readBuff,0,readBuff.length);
			bytes += length;
	    }
	    long timeS = (System.nanoTime() - startTime) / 1000000000;
	    is.close();
	    client.close();
	    server.close();
	    
	    int kbReceive = (int) (bytes / 1000);
	    
	    double rate = kbReceive / timeS * 8 / 1000;
	    
	    System.out.println("received = " + kbReceive + " KB" + "; rate = " + rate + " Mbps");
	}

}
