import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	Socket socket;

	public Client(String host, int port, int time) throws UnknownHostException, IOException {
		socket = new Socket(host, port); // "127.0.0.1", 10000

		sendData(time);
	}

	private void sendData(int timeSeconds) throws IOException {
		DataOutputStream os = new DataOutputStream(socket.getOutputStream());
		long timeMS = (long) timeSeconds * 1000;

		int totalChunks = 0;
		boolean ifTimeOut = false;
		long startTime = System.currentTimeMillis();

		byte[] oneChunk = new byte[1000];
		while (!ifTimeOut) {
			os.write(oneChunk);
			++totalChunks;
			ifTimeOut = System.currentTimeMillis() - startTime >= (long) timeMS;
		}
		
		long endTime = System.currentTimeMillis();
		long durationMS = endTime - startTime;

		os.close();
		socket.close();
		
		int kbSent = totalChunks;
		double sentRate = kbSent * 8 / durationMS;
		System.out.println("sent=" + kbSent + " KB " + "rate=" + sentRate + " Mbps");
	}

}
