package client.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.JButton;
import javax.swing.JFrame;

import lombok.extern.log4j.Log4j2;

@Log4j2( topic = "logger" )
public class LithHarbor implements LineListener, ActionListener {
    
    private boolean      isPlaybackCompleted = true;
    private JFrame       frame;
    private JButton      playButton, stopButton;
    private Clip         audioClip;
    private final String audioFilePath;
    
    public LithHarbor( String audioFilePath ) {
        this.audioFilePath = audioFilePath;
        frame = new JFrame( "리스항구" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible( true );
        
        playButton = new JButton( "재생" );
        playButton.setFont( new Font( "맑은 고딕", Font.BOLD, 50 ) );
        playButton.addActionListener( this );
        playButton.setActionCommand( "PLAY" );
        
        stopButton = new JButton( "정지" );
        stopButton.setFont( new Font( "맑은 고딕", Font.BOLD, 30 ) );
        stopButton.addActionListener( this );
        stopButton.setActionCommand( "STOP" );
        
        frame.add( BorderLayout.CENTER, playButton );
        frame.add( BorderLayout.SOUTH, stopButton );
        frame.setSize( 400, 300 );
    }
    
    private void play() {
        // 소스 경로에 음원 파일을 위치시키고, main 실행 시 args에 경로를 넣어준다.
        // 파일명에 띄어쓰기가 들어갔을 경우 ""으로 감싸준다. 경로를 읽지 못할 경우 null
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream( audioFilePath );
        
        if ( inputStream == null ) {
            log.error( "inputStream is {}", inputStream );
            return;
        }
        
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream( inputStream );
            AudioFormat      audioFormat = audioStream.getFormat();
            DataLine.Info    info        = new DataLine.Info( Clip.class, audioFormat );
            audioClip = ( Clip ) AudioSystem.getLine( info );
            audioClip.addLineListener( this );
            audioClip.open( audioStream );
            audioClip.start();
            
            // audioClip.close();
            // audioStream.close();
        }
        catch ( Exception e ) {
            log.error( "Exception : ", e );
        }
    }
    
    @Override
    public void update( LineEvent event ) {
        log.info( event );
        
        if ( LineEvent.Type.START == event.getType() ) {
            isPlaybackCompleted = false;
            log.info( "Playback started." );
        }
        else if ( LineEvent.Type.STOP == event.getType() ) {
            isPlaybackCompleted = true;
            log.info( "Playback completed." );
        }
        
    }
    
    @Override
    public void actionPerformed( ActionEvent e ) {
        log.info( "ActionCommand : {}, isPlaybackCompleted : {}", e.getActionCommand(), isPlaybackCompleted );
        
        String cmd = e.getActionCommand();
        
        if ( cmd.equals( "PLAY" ) ) {
            
            if ( isPlaybackCompleted ) {
                play();
            }
            else
                return;
        }
        else if ( cmd.equals( "STOP" ) && !isPlaybackCompleted ) {
            audioClip.stop();
        }
        else
            return;
    }
    
    public static void main( String[] args ) {
        
        if ( args.length == 1 ) {
            log.debug( args[0] );
            new LithHarbor( args[0] );
        }
        else
            System.exit( 1 );
    }
}
