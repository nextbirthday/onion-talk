package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import lombok.Getter;
import lombok.Setter;
import model.MainFriendLogic;
import util.dto.Account;
import util.dto.Friend;

@Getter
@Setter
// 채팅 목록
@SuppressWarnings("serial")
public class MainView extends JFrame implements ActionListener, KeyListener, MouseListener, ListSelectionListener {
    // Account DTO를 받아오기 위한 전역변수 선언
    Account account;
    Friend friend;

    // 선언부
    String imgPath = ".\\src\\images\\";
    ImageIcon imageIcon = new ImageIcon(imgPath + "main.png");
    JFrame jf = new JFrame();

    JPanel jp_north = new JPanel(); // 위쪽 도화지
    JPanel jp_center = new JPanel();// 가운데 도화지
    JPanel jp_south = new JPanel(); // 아래 버튼 도화지

    JoinView jv = null;

    JLabel jlb_cht = new JLabel();
    // JLabel jlb_nick = new JLabel(); // 닉네임 레이블//확인필요
    JTextField inputField = new JTextField(10); // 테스트 입력 Field
    JLabel jlb_nick = new JLabel(inputField.getText()); // 닉네임 레이블//확인필요
    JLabel jlb_list = new JLabel("친구추가");
    JLabel jlb_msg = new JLabel("NULL"); // 상태메시지 레이블
    EtchedBorder eborder = new EtchedBorder(EtchedBorder.RAISED); // 라벨 테두리

    JButton jbtn_change_msg = new JButton("상태메시지 수정"); // 상태메세지 수정 버튼
    JButton jbtn_add_friend = new JButton("추가"); // 친구추가 버튼
    JButton jbtn_delete_friend = new JButton("x"); // 친구삭제 버튼

    // 아래 버튼
    JButton jbtn_home = new JButton("친구목록"); // 친구목록 버튼
    JButton jbtn_chatroom = new JButton("채팅목록");// 채팅방 버튼
    JButton jbtn_setting = new JButton("설정");// 설정 버튼
    JButton jbtn_logout = new JButton("로그아웃");// 로그아웃 버튼

    // generic type 명시할 것
    DefaultListModel<String> model = new DefaultListModel<>(); // JList에 보이는 실제 데이터
    private JScrollPane scrolled;
    JList<String> list = new JList<>(model);; // 리스트
    
    JButton addBtn = new JButton("검색");; // 검색 버튼
    JButton delBtn = new JButton("삭제"); // 삭제 버튼

    public MainView() {
    }

    // 생성자
    public MainView(Account account) {
        this.account = account;
        
    }

    // 내부클래스로 배경 이미지 처리
    // class MyPanel extends JPanel {
    // public void paintComponent( Graphics g ) {
    // g.drawImage( imageIcon.getImage(), 0, 0, null );
    // setOpaque( false );
    // super.paintComponent( g );
    // }
    // }// end of MyPanel - 사용자 패널정의 - LoginForm$1.class, LoginForm$MyPanel.class
    // 화면그리기

    public void initDisplay() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setContentPane( new MyPanel() );
        this.setLayout(new BorderLayout(50, 20));// 배치

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 하나만 선택 될 수 있도록

        // ==========이벤트 속성 추가=================
        jbtn_change_msg.addActionListener(this); // 엔터 처리
        inputField.addKeyListener(this); // 엔터 처리
        addBtn.addMouseListener(this); // 아이템 추가
        delBtn.addMouseListener(this); // 아이템 삭제
        list.addListSelectionListener(this); // 항목 선택시
        list.addMouseListener(this); //리스트 클릭시 이벤트

        // 상단 - 닉네임 - 상태메세지
        jp_north.add(jlb_nick);// 닉네임
        // jlb_nick.setText( account.getUser_nick() );
        jp_north.add(jlb_msg); // 상태메세지
        // jlb_msg.setText( account.getUser_msg() );
        jp_north.add(jbtn_change_msg);
        jlb_nick.setBounds(40, 20, 100, 35);
        jlb_msg.setBounds(100, 20, 200, 35);
        jlb_msg.setToolTipText("수정버튼을 누르면 메세지를 변경하실 수 있습니다");
        jbtn_change_msg.setBounds(280, 20, 60, 35);
        jlb_msg.setBorder(eborder); // 메세지 테두리 설정

        // 하단 -탭
        jp_south.add(jbtn_home);
        jp_south.add(jbtn_chatroom);
        jp_south.add(jbtn_setting);
        jp_south.add(jbtn_logout);
        jp_south.setBackground(new Color(146, 208, 80));

        // 가운데 - 친구목록

        list.setSize(400, 600);
        jp_center.add(jlb_list);
        this.add("Center", jp_center);
        this.setLayout(new BorderLayout());

        // JPanel topPanel=new JPanel(new FlowLayout(10,10,FlowLayout.LEFT));
        jp_center.add(inputField);
        jp_center.add(addBtn);
        jp_center.add(delBtn); // 위쪽 패널 [textfield] [add] [del]
        jp_center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 상, 좌, 하, 우 공백(Padding)
        jp_center.setBackground(new Color(146, 208, 80)); // 판넬색 변경
        scrolled = new JScrollPane(list);
        scrolled.setPreferredSize(new Dimension(300, 400)); // jlist 사이즈 변경
        scrolled.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));// jlist 상하좌우공백
        jp_center.add(scrolled);
        // this.add(jp_center,"North");

        this.add("North", jp_north);
        this.add("Center", jp_center); // 가운데 list
        this.add("South", jp_south);

        this.setTitle("친구목록");
        this.setLocation(500, 100);
        this.setSize(400, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // ====== 버튼 클릭 됐을 때 ===========
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == addBtn) { // 검색버튼 눌렀을 때 실행 
            addItem2();
        }

        if (e.getSource() == delBtn) {
            int selected = list.getSelectedIndex();
            removeItem(selected);
            System.out.println(selected);
        }

        if (e.getSource() == list){   //리스트 눌렀을 때 채팅방으로 이동
            int selected = list.getSelectedIndex();
            System.out.println(selected);
            if(selected >= 0){
                ChatView chatView = new ChatView();  //친구방에 들어가야됨
            }
        }

    }

    public void removeItem(int index) {

        if (index < 0) {
            if (model.size() == 0)
                return; // 아무것도 저장되어 있지 않으면 return
            index = 0; // 그 이상이면 가장 상위 list index
        }

        model.remove(index);
    }

    ///////////////////// 친구 추가///////////////////////////
    public void addItem() { // db연동 안되어있을 때 jlist에 친구 추가 하기 (사용안함)
        String inputText = inputField.getText();
        if (inputText == null || inputText.length() == 0)
            return;
        model.addElement(inputText);
        inputField.setText(""); // 내용 지우기
        inputField.requestFocus(); // 다음 입력을 편하게 받기 위해서 TextField에 포커스 요청
        // 가장 마지막으로 list 위치 이동
        scrolled.getVerticalScrollBar().setValue(scrolled.getVerticalScrollBar().getMaximum());
    }

    public void addItem2() { // db연동의 경우 jlist에 친구 추가 하기
        // 만약 디비에 있는 친구라면 [친구등록이 가능합니다] 창을 띄우고 추가시킨다.
        // 만약 디비에 없는 친구라면 [아이디를 확인해주세요] 창을 띄운다.
        String inputText = inputField.getText();// 사용자가 입력한 아이디
        MainFriendLogic mainFriendLogic = new MainFriendLogic();
        Account account = mainFriendLogic.friendIdFind(inputText);
        //Friend friend = mainFriendLogic.friendAdd(inputText);

        if ("없음".equals(account.getUser_id())) {
            JOptionPane.showMessageDialog(this, "아이디를 확인하세요");
            return;// addItem2탈출
        } else {
            int result = JOptionPane.showConfirmDialog(this,
                    account.getUser_id() + "(" + account.getUser_name() + ")" + "님을 친구로 등록하시겠습니까? ", "친구 등록 중",
                    JOptionPane.YES_NO_OPTION);

            // 아니오 눌렀을 때 창이 닫혀야됨
            if (result == JOptionPane.CLOSED_OPTION) {
                // 사용자가 예 아니오 선택 없이 다이얼로그 창을 닫은 경우

            } else if (result == JOptionPane.YES_OPTION) {
                // 사용자가 예를 선택한 경우 - mainFriendLogic의 friendAdd

                model.addElement( inputText ); //list에 값 추가하기
                inputField.requestFocus(); // 다음 입력을 편하게 받기 위해서 TextField에 포커스 요청
                // 가장 마지막으로 list 위치 이동
                scrolled.getVerticalScrollBar().setValue(scrolled.getVerticalScrollBar().getMaximum());

            } else {
                // 사용자가 아니오를 선택한 경우
                JOptionPane.getRootFrame().dispose();
            }
        }

        // if ( inputText == null || inputText.length() == 0 )
        // return;
        // }

        inputField.setText(""); // 내용 지우기

    }

    // MouseListener
    
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_ENTER) {
            addItem2();
        }
    }

    // ListSelectionListener
    @Override
    public void valueChanged(ListSelectionEvent e) {

        if (!e.getValueIsAdjusting()) { // 이거 없으면 mouse 눌릴때, 뗄때 각각 한번씩 호출되서 총 두번 호출
            System.out.println("selected :" + list.getSelectedValue());
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object obj = ae.getSource();

        if (obj == jbtn_change_msg) {
            String newMsg = JOptionPane.showInputDialog(jf, "변경할 상태메세지를 입력하세요", "", JOptionPane.INFORMATION_MESSAGE);
            // 공백 입력 허용불가하게 바꾸기
            if (newMsg.length() > 0)
                jlb_msg.setText(newMsg);
        }
    }

    public static void main(String[] args) {
        Account account = new Account();
        MainView mainView = new MainView(account);
        mainView.initDisplay();

    }
}