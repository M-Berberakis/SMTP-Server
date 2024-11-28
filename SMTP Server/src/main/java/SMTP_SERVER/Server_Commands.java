package SMTP_SERVER;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_Commands {
    public DataOutputStream out_Stream;
    public DataInputStream in_Stream;
    private String encrypted_Message = "";

    public Server_Commands(MailData data, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        this.in_Stream = inputStream;
        this.out_Stream = outputStream;
        check_Message(data);
    }      
    private void check_Message(MailData Mydata) throws IOException {
        String user_INPUT = in_Stream.readUTF();
        while(true){
            if (user_INPUT.toUpperCase().startsWith("MAIL FROM:")){
                out_Stream.writeUTF("STATUS: 250 SUCCESS\n Please use the following format for your email, youremail@gmail.com'");
                String MailSender = in_Stream.readUTF();
                Mydata.addSender(MailSender);
                System.out.println("MAIL FROM: " + MailSender);
                out_Stream.writeUTF("STATUS: 250 SUCCESS\n Type 'RCPT TO:' to enter the receipent's email");
                Recipient(Mydata);
                break;
            }
            else{
                out_Stream.writeUTF("STATUS: 501 ERROR, try again: ");
                user_INPUT = in_Stream.readUTF();
            }
        }
    }   
    private void Recipient(MailData myData) throws IOException {
        String recipient_INFO = in_Stream.readUTF();
        while(true){
            if (recipient_INFO.toUpperCase().equals("RCPT TO:")){
                out_Stream.writeUTF("STATUS: 250 SUCCESS\n Please use the following format for your email, youremail@gmail.com'");
                String rcpt = in_Stream.readUTF();
                myData.addRecipient(rcpt);
                System.out.print("RCPT MAIL: " + rcpt);
                out_Stream.writeUTF("STATUS: 250 SUCCESS\n Type 'DATA' to proceed");
                mail_DATA(myData);
                break;
            }
            else{
                out_Stream.writeUTF("STATUS: 501 ERROR, try again: ");
                recipient_INFO = in_Stream.readUTF();
            }
        }
    }   
    private void mail_DATA(MailData data) throws IOException {
        String dataInput = in_Stream.readUTF();
        while(true){
            if(dataInput.toUpperCase().equals("DATA")) {
                out_Stream.writeUTF("STATUS: 354 SUCCESS\n copy and paste at the end of your message---> <CR><LF>.<CR><LF>");
                break;
            }
            else{
                out_Stream.writeUTF("STATUS: 501 ERROR, try again: ");
                dataInput = in_Stream.readUTF();
            }
        }
        while(true){
            String messageInput = in_Stream.readUTF();
            if(messageInput.endsWith("<CR><LF>.<CR><LF>")){
                String new_INPUT = messageInput.substring(0, messageInput.length());
                System.out.print("\nUSER MESSAGE: " + new_INPUT);
                
                Cesarean_Encryption C_Encrypt = new Cesarean_Encryption(new_INPUT);
                encrypted_Message = C_Encrypt.getEncryption();
                data.addMessage(encrypted_Message);
                double Message_ID = Math.random();
                System.out.println("\nMESSAGE ID: " + Message_ID);
                out_Stream.writeUTF("STATUS: 250 SUCCESS\n" + "Message ID: " + Message_ID + "\nType 'QUIT' to quit the server");
                break;
            }
            else{
                out_Stream.writeUTF("Server: 501 ERROR, try again");
                messageInput = in_Stream.readUTF();
            }
        }
        while(true){
            String QUIT_command = in_Stream.readUTF();
            if(QUIT_command.toUpperCase().equals("QUIT")) {
                out_Stream.writeUTF("STATUS: 221 SUCCESS\n Server shutting down");
                break;
            }
            else{
                out_Stream.writeUTF("STATUS: 501 ERROR, try again");
                QUIT_command = in_Stream.readUTF();
            }
        }   
    }
    public String getEncryption()
        {
            return encrypted_Message;
        }
}

