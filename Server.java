import java.net.*;
import java.io.*;
import java.util.*;

public class Server{
    private DatagramSocket socket;
    private List<String> listQuotes = new ArrayList<String>();
    private Random random;
    
    public Server(int port) throws SocketException{
        socket = new DatagramSocket(port);
        random = new Random();
    }//end Server method
    
    public static void main(String[] args){
        if(args.length<2){
            System.out.println("Syntax Server <file> <port>");
            return;
        }
        
        String quoteFile = args[0];
        int port = Integer.parseInt(args[1]);
        
        try{
            Server server = new Server(port);
            server.loadQuote(quoteFile);
            server.service();
        }//end the try
        catch(SocketException ex){
            System.out.println("Socket error: "+ex.getMessage());
        } // end Socket Exception
        catch(IOException ex){
            System.out.println("I/O error: "+ex.getMessage());
        } // end IOException
    } // end main method
    
    private void service() throws IOException{
        while(true){
            DatagramPacket request = new DatagramPacket(new byte[1], 1);
            socket.receive(request);
            
            String quote = getRandomQuote();
            byte[] buffer = quote.getBytes();
            
            InetAddress clientAddress = request.getAddress();
            int clientPort = request.getPort();
            
            DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
            socket.send(response);
        }
    }//end void service
    
    private void loadQuote(String quoteFile) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(quoteFile));
        String quote;
        
        while((quote = reader.readLine()) != null){
            listQuotes.add(quote);
        }
        reader.close();
    }// end loadQuote
    
    private String getRandomQuote(){
        int randomIndex = random.nextInt(listQuotes.size());
        String randomQuote = listQuotes.get(randomIndex);
        return randomQuote;
    }
}//end Server class