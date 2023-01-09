package server.thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import server.TalkServer;

public class TalkServerThread extends Thread {
    
    List<TalkServerThread> clientList = new Vector<>();
    
    TalkServer talkServer;
    
    // 서버로부터 클라이언트 연결정보를 받아오기 위한 전역변수 추가
    Socket             socket;
    ObjectOutputStream oos;
    ObjectInputStream  ois;
    
    public TalkServerThread( TalkServer talkServer ) {
        this.talkServer = talkServer;
    }
    
    @Override
    public void run() {}
    
}
