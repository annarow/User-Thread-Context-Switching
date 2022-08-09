import java.io.*;
import java.net.*;

/*
430 Program 2 Part 2 Multi-Threaded Server
2/4/2022
By: Anna Rowena Waldron
Code inspired by Professor code from slides.
 */


public class Worker implements  Runnable{
/*
This class implements Runnable which means it defines a public void function run. Whenever a new worker object is
created, it has an id assigned stored in workerID. The class sends requests to the server at the specified port
when run gets called.
 */
    private int workerID;
    private int searchingPort;  //port to send requests through

    //Constructor
    Worker(int id, int port) {
        this.workerID = id;
        this.searchingPort = port;
    }

    /*
    Run function creates a socket to communicate with the server. If successful, asks the server for current date
    and time, then prints it out along with workerID and then closes connection to the server. If it fails,
    an exception is thrown.
     */
    public void run(){
        try {
            Thread.sleep(5000); //allow time to start server before attempting connection to server
            Socket sock = new Socket("127.0.0.1", this.searchingPort);
            InputStream in = sock.getInputStream();
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = bin.readLine()) != null) {
                System.out.print("Worker #" + this.workerID + ":\t");
                System.out.println(line);
            }
            sock.close();
        } catch (IOException | InterruptedException ie) {
            System.out.println("Worker #" + this.workerID + " could not connect");
        }
    }

}
