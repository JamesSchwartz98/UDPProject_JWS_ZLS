import java.net.*;
import java.io.*;
import java.util.*;

public class Client{
    
    public static void main(String args[]){
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        
        try{
            InetAddress address = InetAddress.getByName(hostname);
            DatagramSocket socket = new DatagramSocket();
            String userReq;
            
            while(true){
                Scanner input = new Scanner(System.in);
                System.out.println("Enter a command: ");
                userReq = input.nextLine();

                if(userReq.equals("REQUESTQUOTE")||userReq.equals("REQUEST QUOTE")){
                    DatagramPacket request = new DatagramPacket(new byte[1], 1, address, port);
                    socket.send(request);

                    byte[] buffer = new byte[512];
                    DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                    socket.receive(response);
                    String quote = new String(buffer, 0, response.getLength());

                    System.out.println(quote);
                    System.out.println();
                }//end REQUESTQUOTE loop
                else if(userReq.equals("END")||userReq.equals("End")||userReq.equals("end")){
                    System.out.println("Client closed");
                    socket.close();
                    break;
                }//end END loop
                else{
                    System.out.println("Please enter a valid command.");
                    Systemin.out.println();
                }
            }//end while
        }//end try
 /**       catch(SocketTimeoutException ex){
            System.out.println("Timeout error: "+ex.getMessage());
            ex.printStackTrace();
        }*/
        catch(IOException ex){
            System.out.println("Client error: "+ex.getMessage());
            ex.printStackTrace();
        } // end IOException 
    }//end main string
} // end Client class