package SMTP_CLIENT;

public class Cesarean_Inc_Decryption 
{

    private String encrypted_Message = "";
    private String decrypted_Message = "";
    private int KEY;
    
    public Cesarean_Inc_Decryption (String message){
        encrypted_Message = message;
        KEY = 3;
        char holder;
        
        for(int i = 0; i < encrypted_Message.length(); ++i){
            holder = encrypted_Message.charAt(i);
            if(holder >= 'a' && holder <= 'z'){
                holder = (char)(holder - KEY);
                if(holder < 'a'){
	            holder = (char)(holder + 'z' - 'a' + 1);
                }
	        decrypted_Message += holder;
	    } else if(holder >= 'A' && holder <= 'Z'){
                holder = (char)(holder - KEY);
	        if(holder < 'A'){
	            holder = (char)(holder + 'Z' - 'A' + 1);
	        }
	        decrypted_Message += holder;
	    } else {
                decrypted_Message += holder;
	    }
	}
    }
    public String get_decrypted(){
        return decrypted_Message;
    }  
   
}

