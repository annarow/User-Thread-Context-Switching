import java.io.*;
import java.net.*;
import java.lang.Thread;

/*
430 Program 2 Part 2 Multi-Threaded Server
2/4/2022
By: Anna Rowena Waldron
Code inspired by Professor code from slides.
 */

/*
This class creates 10 threads and assigns each thread a worker object and then runs the server that will provide
date and time when requested.
 */
public class DateServerMT{
    private final static int NUM_WORKERS = 10; //amount of threads

    /*
    Main method that takes in a port number, if none is provided uses default port number. Creates a DateServerMT object
    which is a server and runs it.
     */
    public static void main(String args[]){
        if(args.length == 0){
            DateServerMT theServer = new DateServerMT(NUM_WORKERS, 13287);
            makeServer(13287);
        }else {
            DateServerMT theServer = new DateServerMT(NUM_WORKERS, Integer.parseInt(args[0]));
            makeServer(Integer.parseInt(args[0]));
        }
    }

    /*
    Constructor that generates the threads and worker objects as told by num_workers. Also runs the threads.
     */
    DateServerMT(int num_workers, int port){
        Runnable[] workers = new Worker[num_workers];
        Thread myThreads[] = new Thread[num_workers];

        for (int i = 0; i < num_workers; i++){
            workers[i] = new Worker(i, port);
            myThreads[i] = new Thread(workers[i]);
            myThreads[i].start();
        }
    }

    /*
    Makes a server that listens to the provided port parameter.
     */
    public static void makeServer(int port){
        try {
            ServerSocket sock = new ServerSocket(port);
            while (true) {
                Socket client = sock.accept();
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                pout.println(new java.util.Date().toString());
                client.close();
            }
        } catch (IOException ie) {
        }
    }
}
