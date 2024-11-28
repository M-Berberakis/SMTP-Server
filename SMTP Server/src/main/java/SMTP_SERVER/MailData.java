package SMTP_SERVER;

import java.util.ArrayList;

public class MailData {
    
    public ArrayList<String> MailRecipients = new ArrayList<String>();
    public ArrayList<String> MailSenders = new ArrayList<String>();
    public ArrayList<String> MailMessages = new ArrayList<String>();
    
    //This list stores the encrypted messages that get 
    //sent between sender and recipient
    public ArrayList addMessage(String input){
        MailMessages.add(input);
        return MailMessages;
    }
    
    
    //This list stores the mail senders
    public ArrayList addSender(String input){
        MailSenders.add(input);
        return MailSenders;
    }
    
    //This list stores the mail recipients 
    public ArrayList addRecipient(String input){
        MailRecipients.add(input);
        return MailRecipients;
    }
    
    
    
    //The following methods are responsible for sending the information to the array lists
    
    public ArrayList getRecipients(){
        return MailRecipients;
    }
    
    public ArrayList getSenders(){
        return MailSenders;
    }
    
    public ArrayList getMessages(){
        return MailMessages;
    }
    
}
