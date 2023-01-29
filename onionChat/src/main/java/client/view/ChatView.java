package client.view;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import model.ChatNoteLogic;

public class ChatView extends JDialog implements ActionListener {
    // 선언
    String imagePath = "/src/main/resources/images/";
    ImageIcon onionIcon = new ImageIcon(imagePath + "onionicon.png"); //
    ImageIcon onionbgIcon = new ImageIcon(imagePath + "onionbg.png"); //
    JLabel jlb_bgLabel = new JLabel(onionbgIcon); //
    // Container con = this.getContentPane();
    JPanel jp_centerPanel = new JPanel(); // 텍스트 메인 페널
    JPanel jp_sendPanel = new JPanel(); // 텍스트 전송 페널
    JButton jbtn_sendButton = new JButton("전송"); // 텍스트 전송 버튼
    JTextField jtf_chatTextField = new JTextField(24); // 텍스트 입력
    StyledDocument sd_display = new DefaultStyledDocument(new StyleContext());
    JTextPane jtp_chatDisplay = new JTextPane(sd_display);
    JViewport viewport = new JViewport() {
        public void paintComponent(Graphics g) {
            Image img = onionbgIcon.getImage(); //
            setOpaque(false);
            Graphics2D gd = (Graphics2D) g;
            gd.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    0.7f));
            g.drawImage(img, 0, 0, this);
            super.paintComponent(g);
        }
    };
    Font font = new Font("굴림", Font.PLAIN, 13);
    JScrollPane js_scrollPane = new JScrollPane(); //
    // 생성

    public ChatView() {
        initDisplay();
    }

    // 화면
    public void initDisplay() {
        js_scrollPane.setOpaque(true);
        js_scrollPane.setViewport(viewport);
        jtp_chatDisplay.setOpaque(false);
        js_scrollPane.setViewportView(jtp_chatDisplay);
        // 메인 페널
        jp_centerPanel.setLayout(new BorderLayout());
        jp_centerPanel.add("Center", js_scrollPane);
        jp_centerPanel.add("South", jp_sendPanel);
        // 전송 페널
        jp_sendPanel.setLayout(new BorderLayout());
        jp_sendPanel.add("Center", jtf_chatTextField);
        jp_sendPanel.add("East", jbtn_sendButton);
        // 버튼 크기
        jbtn_sendButton.setPreferredSize(new Dimension(65, 40));
        // 전송 화면
        // jta_chatDisplay.setLineWrap(true); // 자동 줄바꿈
        jtp_chatDisplay.setEditable(false); // 텍스트 필드 입력 불가
        // 폰트
        jtp_chatDisplay.setFont(font);
        jtf_chatTextField.setFont(font);
        // 액션
        jtf_chatTextField.addActionListener(this);
        jbtn_sendButton.addActionListener(this);

        setIconImage(onionIcon.getImage()); // 아이콘 선언
        setLayout(new GridLayout(1, 2));
        add(jp_centerPanel);
        setTitle("양파쿵야TALK");
        setVisible(true);
        setSize(400, 600);
        // Dialog는 Frame에 종속되기 때문에 EXIT이 아니라 DISPOSE이다.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // X 클릭 자동 종료
    }

    // 메인
    public static void main(String[] args) {
        new ChatView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();

        // 전송 ??
        if (jbtn_sendButton == object || jtf_chatTextField == object) {
            // 사용자가 입력한 메시지
            String message = jtf_chatTextField.getText(); // 전송 시 입력 메시지 받아옴
            // 사용자가 입력한 메시지를 받아서 소켓서버에 전송하기
            // ex) oos.writeObject(300|tomato|오늘뭐해?);
            // 서버소켓에 메시지를 전송하자마자 오라클 서버의 chatnode테이블에 insert문 처리하기
            ChatNoteLogic cn = new ChatNoteLogic();

            try {
                sd_display.insertString(sd_display.getLength(), message + "\n", null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            jtp_chatDisplay.setCaretPosition(sd_display.getLength());
            jtf_chatTextField.setText(""); // TextField 비움
        }
    }
}