import java.net.*;
import java.io.*;

public class Client{
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;
    
    public Client(String address, int port){
        try{
            socket = new Socket(address, port);
            System.out.println("Connection Established");
        
            input = new DataInputStream(System.in);
            output = new DataOutputStream(socket.getOutputStream());
        } // end terminal request
        
        catch(UnknownHostException u){
            System.out.println(u); //come back to assess
        }
        catch(IOException i){
            System.out.println(i);
        }
    
        String line = "";
        
        while(!line.equals("END")){
            try{
                line = input.readLine();
                output.writeUTF(line);
            }
            catch(IOException i){
                System.out.println(i);
            }
        }//end input termination
        
        //closing connection
        try{
            input.close();
            output.close();
            socket.close();
        }
        catch(IOException i){
            System.out.println(i);
        }
    }//end Client method

    public static void main(String args[]){
        Client client = new Client("127.0.0.1", 2025);
    }

} // end Client class