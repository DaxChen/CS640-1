import java.net.UnknownHostException;

public class Iperfer {

	public static void main(String[] args) {
		if (args[0].equals("-s") && args[1].equals("-p") && args.length == 3) {
			int port = 10000;
			// parse port number
			try {
				port = Integer.parseInt(args[2]);
			} catch(Exception e) {
				System.out.println("Caught exception when parsing port number: " + args[2]);
				System.exit(1);
			}

			// check port
			if (port < 1024 || port > 65535) {
				System.err.println("Error: port number must be in the range 1024 to 65535");
				System.exit(1);
			}

			System.out.println("Using server mode...");
			try {
				new Server(port);
			} catch(Exception e) {
				System.out.println("Caught exception when initializing Server: " + e.getClass().getName());
			}
		}
		else if (args[0].equals("-c") && args[1].equals("-h") && args[3].equals("-p") && args[5].equals("-t") && args.length == 7) {
			String host = args[2];
			int port = 10000;
			int time = 10;

			try {
				port = Integer.parseInt(args[4]);
			} catch(Exception e) {
				System.out.println("Caught exception when parsing port number: " + args[2]);
				System.exit(1);
			}

			try {
				port = Integer.parseInt(args[6]);
			} catch(Exception e) {
				System.out.println("Caught exception when parsing time: " + args[6]);
				System.exit(1);
			}

			if (port < 1024 || port > 65535) {
				System.err.println("Error: port number must be in the range 1024 to 65535");
				System.exit(1);
			}

			System.out.println("Using client mode...");

			try {
				new Client(host, port, time);
			} catch(UnknownHostException e) {
				System.out.println("Cannot initialize Clinet, host not valid: " + host);
			} catch(Exception e){
				System.out.println("Caught exception when initializing Client:" + e.getClass().getName());
			}
		}
		else {
			System.out.println("Error: missing or additional arguments");
			System.exit(1);
		}
	}
}

