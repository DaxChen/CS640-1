import java.io.IOException;

public class Iperfer {

	public static void main(String[] args) throws IOException {
		// TODO: On the time and hostname input values, provide input validations
		// that you think are reasonable.
		// As always, state your assumptions through comments in code.
		if (args[0].equals("-s") && args[1].equals("-p") && args.length == 3) {
			int port = Integer.parseInt(args[2]);
			if (port < 1024 || port > 65535) {
				System.err.println("Error: port number must be in the range 1024 to 65535");
				System.exit(1);
			}
			System.out.println("Using server mode...");
			new Server(port);
		}
		else if (args[0].equals("-c") && args[1].equals("-h") && args[3].equals("-p") && args[5].equals("-t") && args.length == 7) {
			String host = args[2];
			int port = Integer.parseInt(args[4]);
			int time = Integer.parseInt(args[6]);
			if (port < 1024 || port > 65535) {
				System.err.println("Error: port number must be in the range 1024 to 65535");
				System.exit(1);
			}
			System.out.println("Using client mode...");
			new Client(host, port, time);
		}
		else {
			System.out.println("Error: missing or additional arguments");
			System.exit(1);
		}
	}

}
