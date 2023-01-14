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
    
    // 소켓 관련 초기화
    public void init() {
        
        try {
            // 서버측의 ip주소 작성하기
            // socket = new Socket("192.168.0.244",3000);
            // new ServerSocket(3000)이 받아서 accept()통해서 client 소켓에 저장이된다.
            socket = new Socket( "localhost", 54300 );
            oos = new ObjectOutputStream( socket.getOutputStream() );
            ois = new ObjectInputStream( socket.getInputStream() );
            // initDisplay에서 닉네임이 결정된 후 init메소드가 호출되므로
            // 서버에게 내가 입장한 사실을 알린다.(말하기)
            // oos.writeObject( 100 + util.command.Protocol.TALK_IN + );
            // 서버에 말을 한 후 들을 준비를 한다.
            TalkClientThread tct = new TalkClientThread( this );
            tct.start();
        }
        catch ( Exception e ) {
            // 예외가 발생했을 때 직접적인 원인되는 클래스명 출력하기
            System.out.println( e.toString() );
        }
    }
    
    public static void main( String[] args ) {
        
    }
}
