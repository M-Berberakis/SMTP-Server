package SMTP_CLIENT;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Scanner;
import SMTP_SERVER.Cesarean_Encryption;

class Client{
    public static void main(String[] args){
        
        System.out.println("Client Operational\n");
        
        DataOutputStream out_DataStream;
	DataInputStream in_DataStream;
        
        
        //Creating a log in page before the email can be written
        String username = "Student99";
        String password = "Password99";
        String inputUSER =" ";
        String inputPASS =" ";
        
        Scanner log_INPUT = new Scanner(System.in);
        
        while (true)
        {
        
        System.out.println("Please enter your username: ");
        inputUSER = log_INPUT.nextLine();
        
        System.out.println("Please enter your password: ");
        inputPASS = log_INPUT.nextLine();
        
        if (username.equals(inputUSER) && password.equals(inputPASS))
        {
            System.out.println("Access granted, please proceed");
            break;
        }
        
        //If the username of password are incorrect
        //the user is asked to re-enter their cridentials
        else
        {
            System.out.println("Access Denied"); // if the username of password are incorrect
        }
        }
        
        //Process of SMTP begins

        try{
            Socket client_Socket = new Socket("localhost", 5000);
             
            out_DataStream = new DataOutputStream(client_Socket.getOutputStream());
            
	    in_DataStream  = new DataInputStream(client_Socket.getInputStream());

            System.out.println(in_DataStream.readUTF());
            
            Scanner user_INPUT = new Scanner(System.in);

            System.out.println("To begin the process, type in HELO: ");
            String USER_input = user_INPUT.nextLine();
  
            while (true){
                out_DataStream.writeUTF(USER_input);
                System.out.println(in_DataStream.readUTF());
                USER_input = user_INPUT.nextLine();
                if(USER_input.contains("quit")){
                    break;
                  }
                }
            user_INPUT.close();

        }catch (IOException e){e.printStackTrace();}
    }
}