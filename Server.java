import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Server{
    private DatagramSocket socket;
    private List<String> listQuotes = new ArrayList<String>();
    private Random random;
    
    public Server(int port) throws SocketException{
        socket = new DatagramSocket(port);
        random = new Random();
    }//end Server method
    
    public static void main(String[] args){
        String quoteFile = "quote.csv";
        int port = 2025;
        
        try{
            DateFormat dateForm = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            Server server = new Server(port);
            System.out.println("Server started "+dateForm.format(date));
            server.loadQuote(quoteFile);
            server.quoteRequestLoop();
        }
        catch(SocketException ex){
            System.out.println("Socket error: "+ex.getMessage());
        } // end Socket Exception
        catch(IOException ex){
            System.out.println("I/O error: "+ex.getMessage());
        } // end IOException
    } // end main method
    
    private void quoteRequestLoop() throws IOException{
        while(true){
            DatagramPacket request = new DatagramPacket(new byte[1], 1);
            socket.receive(request);
            
            String quote = getQuote();
            byte[] buffer = quote.getBytes();
            
            InetAddress clientAddress = request.getAddress();
            int clientPort = request.getPort();
            
            DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
            socket.send(response);
            
            DateFormat dateForm = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            String requestString = "Request received from "+ clientAddress+": "+clientPort+" "+dateForm.format(date);
            System.out.println(requestString);
            System.out.println();
        }
    }//end void quoteRequests
    
    private void loadQuote(String quoteFile) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(quoteFile));
        String quote;
        
        while((quote = reader.readLine()) != null){
            listQuotes.add(quote);
        }
        reader.close();
    }// end loadQuote
    
    private String getQuote(){
        int randomIndex = random.nextInt(listQuotes.size());
        String randomQuote = listQuotes.get(randomIndex);
        return randomQuote;
    }//end getQuote method
}//end Server class
















