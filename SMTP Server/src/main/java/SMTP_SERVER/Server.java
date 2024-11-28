package SMTP_SERVER;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    
                public static void main(String[] args) throws IOException{
                int serverPort = 5000;
                MailData mailData = new MailData();
                System.out.println("SMTP server is operational: " + serverPort);
		try {
                    ServerSocket server_Socket = new ServerSocket(serverPort);
                    server_Socket.setReuseAddress(true);
			while (true)
                        {
				Socket client_Socket = server_Socket.accept();
                                System.out.println("Connection with the client has been established ");
                                System.out.println("Connected client's information: "  + client_Socket);
                                NewConnection newConnection = new NewConnection(mailData, client_Socket);
                                Thread threadConnection = new Thread(newConnection);
                                threadConnection.start();
                                }
                    }catch (IOException e){e.printStackTrace();}
                }
	
	private static class NewConnection implements Runnable
        {
                private final Socket clientSocket;
		DataOutputStream out_Stream;
		DataInputStream in_Stream;
                MailData mailData;
                
                public NewConnection(MailData mailData, Socket socket)
                {
                    this.clientSocket = socket;
                    this.mailData = mailData;
                }
                public void run()
                {
		try 
                {
                        System.out.println("Server ready for client: " + clientSocket);
			out_Stream = new DataOutputStream(clientSocket.getOutputStream());
			in_Stream  = new DataInputStream(clientSocket.getInputStream());
                        
			out_Stream.writeUTF("STATUS: 220, Socket is open\n\n");

			handleEHLO(in_Stream, out_Stream);
                        
                        Server_Commands INPUT = new Server_Commands(mailData, in_Stream, out_Stream);
                        
                        System.out.println("Encrypted message: " + INPUT.getEncryption());
			
                    } catch(IOException e){}
                
                System.out.println("Sender: " + mailData.getSenders());
                System.out.println("Recipient: " + mailData.getRecipients());
                
                socket_Close(clientSocket);
            }
	}
	
	private static void handleEHLO(DataInputStream in_Stream, DataOutputStream out_Stream) throws IOException 
        {
		String EHLO = in_Stream.readUTF();
                EHLO = EHLO.toLowerCase();
                
                while(true)
                {
                if(EHLO.startsWith("ehlo") || EHLO.startsWith("helo"))
                {
                    out_Stream.writeUTF("STATUS: 250 SUCCESS\n Type 'MAIL FROM:' to proceed");
                    break;
                }
                else 
                {
                    out_Stream.writeUTF("STATUS: ERROR 501 try again:");
                    EHLO = in_Stream.readUTF();
                    }
                }
	}
		
 	private static void socket_Close(Socket socket) 
        {
		try 
                {
			socket.close();
		} catch (Exception ex) 
                {
			ex.printStackTrace();
                }
        }
}
		
