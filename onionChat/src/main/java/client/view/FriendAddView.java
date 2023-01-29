package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lombok.extern.log4j.Log4j2;
import model.FriendAddLogic;
import util.dto.Account;

@Log4j2
@SuppressWarnings( "serial" )
public class FriendAddView extends JDialog implements ActionListener {
    
    MainView mainview;
    Account  myAccount;
    Account  friendAccount;
    
    JLabel friend = new JLabel( "친구이름(아이디)" );
    
    JPanel centerPanel = new JPanel();
    JPanel southPanel  = new JPanel();
    
    JLabel friendLabel;
    
    JButton addButton  = new JButton( "추가" );
    JButton exitButton = new JButton( "닫기" );
    
    public FriendAddView() {}
    
    public FriendAddView( MainView mainview, Account myAccount, Account friendAccount ) {
        this.mainview = mainview;
        this.myAccount = myAccount;
        this.friendAccount = friendAccount;
        init();
        log.info( myAccount + ", " + friendAccount );
    }
    
    public void init() {
        friendLabel = new JLabel( friendAccount.getUser_name() + "(" + friendAccount.getUser_id() + ")님" );
        
        centerPanel.add( friendLabel );
        southPanel.add( addButton );
        southPanel.add( exitButton );
        
        friendLabel.setLocation( 100, 100 );
        addButton.addActionListener( this );
        exitButton.addActionListener( this );
        
        this.add( "Center", centerPanel );
        this.add( "South", southPanel );
        this.setVisible( true );
        this.setSize( 400, 300 );
        this.setTitle( "친구추가" );
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
    }
    
    @Override
    public void actionPerformed( ActionEvent e ) {
        Object object = e.getSource();
        
        if ( object == addButton ) {
            // String friendID = mainview.inputField.getText();
            String friendID2 = null;
            friendID2 = friendAccount.getUser_nick() + "(" + friendAccount.getUser_id() + ")";
            
            // FriendAddLogic friendAddLogic = new FriendAddLogic();
            // friendAddLogic.friendAdd( myAccount, friendAccount.getUser_id() );
            
            new FriendAddLogic().friendAdd( myAccount, friendAccount.getUser_id() );
            mainview.model.addElement( friendID2 );
            mainview.inputField.setText( "" );
            mainview.inputField.requestFocus();
            this.dispose();
        }
        else if ( object == exitButton ) {
            this.dispose();
        }
    }
}
