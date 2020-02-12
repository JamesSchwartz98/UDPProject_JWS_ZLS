import java.net.*;
import java.io.*;

public class Server{
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    
    public Server(int port){
        try{
            server = new ServerSocket(port);
            System.out.println("Server Established");
            
            System.out.println("Waiting for client connection");
            
            socket = server.accept();
            System.out.println("Connection Established");
            
            //accepting input
            in = new DataInputStream( new BufferedInputStream(socket.getInputStream()));
            
            String line = "";
            
            while(!line.equals("End")){
                try{
                    line = in.readUTF();
                    System.out.println(line);
                }
                catch(IOException i){
                    System.out.println(i);
                }
            }//end input acception loop
            System.out.println("Client Closed");
            
            socket.close();
            in.close();
        }
        catch(IOException i){
            System.out.println(i);   
        }
    }//end Server method
    
    public static void main(String args[]){
        Server server = new Server(2025);
    }//end main method
}//end Server class