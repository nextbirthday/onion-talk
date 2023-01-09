package client.thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;

@SuppressWarnings( "serial" )
public class TalkClient extends JFrame implements Runnable {
    
    Socket             socket;
    ObjectOutputStream oos;
    ObjectInputStream  ois;
    
    @Override
    public void run() {
        
    }
    
    public static void main( String[] args ) {
        
    }
}
