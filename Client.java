import.java.net.*
import java.io.*

public class Client{
    private Socket socket = null;
    private DataInput input = null;
    private DataOutput output = null;
    
    public Client(String address, int port){
        try{
            socket = new Socket(addres, port);
            Systemin.out.println("Connection Established");
        
            input = new DataInput(System.in);
            output = new DataOutput(socket.getOutput());
        } // end terminal request
        
        catch(UnknownHostException u){
            System.out.println(u); //come back to assess
        }
        catch(IOExcpetion i){
            System.out.println(i);
        }
    
        String line = "";
        
        while(!line.equals("End"||"end")){
            try{
                line = input.readLine();
                out.writeUTF(line):
            }
            catch(IOException i){
                Systemin.out.println(i);
            }
        
        }//end the exit terminal loop
    }//end Client method

} // end Client class