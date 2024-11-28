package SMTP_SERVER;

public class Cesarean_Encryption {   

    private String unencrypted_Message = "";
    private String encrypted_Message = "";
    private int KEY = 3;
    
    public Cesarean_Encryption(String Message){
        unencrypted_Message = Message;
        char ch;
        
        for(int i = 0; i < unencrypted_Message.length(); ++i){
            ch = unencrypted_Message.charAt(i);
            if(ch >= 'a' && ch <= 'z'){
                ch = (char)(ch + KEY);
                if(ch > 'z'){
                    ch = (char)(ch - 'z' + 'a' -1);
                }
	        encrypted_Message += ch;
	    } else if(ch >= 'A' && ch <= 'Z'){
                ch = (char)(ch + KEY);
	        if(ch > 'Z'){
	            ch = (char)(ch - 'Z' + 'A' -1);
	        }
	        encrypted_Message += ch;
	    } else {
                encrypted_Message += ch;
	    }
	}
    }
    public String getEncryption(){
        return encrypted_Message;
    }
}
