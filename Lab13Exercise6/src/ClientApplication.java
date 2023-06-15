
import java.net.Socket;
import java.net.InetAddress;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ClientApplication {

	public static void main(String[] args) {
		
		ClientFrame clientFrame = new ClientFrame();
		String text = "Good Morning";
		String targetLanguage = "Bahasa Melayu";
		
		try {
			
		// Launch client-side frame
		
		clientFrame.setVisible(true);
			
		// Connect to the server @ localhost, port 1234
		Socket socket = new Socket(InetAddress.getLocalHost(), 1234);

		// Update the status of the connection
		clientFrame.updateConnectionStatus(socket.isConnected());
		System.out.println(socket.isConnected());

		// Write text for server
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
		
		// send text
		printWriter.println(text);
		printWriter.println(targetLanguage);
		System.out.println("text send to server");
		
		// Read from network
		BufferedReader bufferedReader = new BufferedReader
				(new InputStreamReader(socket.getInputStream()));

		// display translated words
		String translated = bufferedReader.readLine();
		clientFrame.translatedWord(translated);
	

		// Close everything
		bufferedReader.close();
		socket.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
