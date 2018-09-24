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
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		int length = is.read(readBuff,0,readBuff.length);
		// int kbReceive = length / 1000;
		int kbReceive = 0;
		while (length != -1) {
			length = is.read(readBuff,0,readBuff.length);
			// kbReceive += length / 1000;
			kbReceive++;
			//System.out.println(kbReceive);
		}
		long timeS = (System.nanoTime() - startTime) / 1000000000;
		is.close();
		client.close();
		server.close();

		double rate = kbReceive / timeS * 8 / 1000;

		System.out.println("received = " + kbReceive + " KB" + "; rate = " + rate + " Mbps");
	}

}
