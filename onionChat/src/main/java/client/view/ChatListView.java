package client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.ChatListLogic;

// 채팅 목록
@SuppressWarnings("serial")
public class ChatListView extends JFrame implements KeyListener, ActionListener, MouseListener {

    JPanel jp_main = new JPanel(); // 메인 도화지
    JPanel jp_south = new JPanel(); // 하반부 버튼 도화지
    JPanel jp_north = new JPanel(); // 삭제 버튼 도화지
    JLabel jlb_list = new JLabel("채팅 목록");
    JButton btn_upd = new JButton("방이름 변경");
    JButton btn_exit = new JButton("삭제");

    //// 하단부 버튼
    JButton btn_home = new JButton("홈");
    JButton btn_chat = new JButton("채팅방");
    JButton btn_set = new JButton("설정");
    JButton btn_logout = new JButton("로그아웃");

    //// JList
    DefaultListModel<String> model = new DefaultListModel<>(); // JList에 보이는 실제 데이터
    JList<String> list = new JList<>(model);; // 리스트
    JScrollPane scrolled = new JScrollPane(list);
    String roomName = null;

    // public final Map<String, ImageIcon> imageMap;

    // 생성자
    public ChatListView() {
        // String[] nameList = { "*" };
        // imageMap = createImageMap(nameList);
        btn_upd.addActionListener(this);
        btn_exit.addActionListener(this);
        initDisplay();
    }

    ///////////////////////////////// 화면 /////////////////////////////////////
    public void initDisplay() {
        // 리스트 외곽 경계선
        // list.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //// 채팅방 목록
        jp_main.add(jlb_list);
        jp_main.setBackground(new Color(146, 208, 80));
        scrolled = new JScrollPane(list);
        scrolled.setPreferredSize(new Dimension(350, 450)); // jlist 사이즈 변경
        scrolled.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20)); // jlist 상하좌우공백
        jp_main.add(scrolled);
        this.add("Center", jp_main);

        //// 채팅방 DB 연결
        List<String> roomList = null;
        ChatListLogic cLogic = new ChatListLogic();
        roomList = cLogic.chatList();
        for (int i = 0; i < roomList.size(); i++) {
            model.addElement(roomList.get(i));

        }

        //// 상단부 대화방버튼
        jp_north.add(btn_upd);
        jp_north.add(btn_exit);
        jp_north.setBackground(new Color(146, 208, 80));
        jp_north.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add("North", jp_north);

        //// 하단부 버튼
        jp_south.add(btn_home);
        jp_south.add(btn_chat);
        jp_south.add(btn_set);
        jp_south.add(btn_logout);
        jp_south.setBackground(new Color(146, 208, 80));
        this.add("South", jp_south);

        //// 타이틀, 화면 띄우기
        this.setTitle("양파쿵야 Talk");
        this.setSize(400, 600);
        this.setLocationRelativeTo(null); // 창 가운데
        this.setVisible(true);
    }

    ///////////////// 이미지 넣기 아직 안됨 //////////////////
    /*
     * public Map<String, ImageIcon> createImageMap(String[] list) {
     * Map<String, ImageIcon> map = new HashMap<>();
     * try {
     * map.put(roomName,
     * new ImageIcon(
     * "D:\\vscode_java\\dev_java\\app\\src\\main\\java\\dev_java\\images\\oniontalk\\p\\"
     * ));
     * } catch (Exception e) {
     * e.printStackTrace(); // 에러 확인
     * }
     * return map;
     * }
     */

    ///////////////////////////// 이벤트 ///////////////////////////////
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            System.out.println("더블클릭됨");
            ChatView cv = new ChatView();
            cv.initDisplay();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int sel = list.getSelectedIndex();
        roomName = list.getSelectedValue();
        System.out.println(roomName);

        //////////////////// 채팅방 이름 변경 해야함
        if (sel >= 0 && e.getSource() == btn_upd) {
            String newMsg = JOptionPane.showInputDialog(this, "변경할 채팅방 이름을 입력하세요", "", JOptionPane.INFORMATION_MESSAGE);
            if (newMsg.length() > 0) {
                // roomName.setText(newMsg);
            }
            // 방 선택 안하고 변경버튼 눌렀을 때
        } else if (e.getSource() == btn_upd) {
            if (roomName == null) {
                JOptionPane.showMessageDialog(this, "변경할 대화방을 선택하세요");
                System.out.println("선택안함");
                return;// actionPerform()탈출
            } else {
                System.out.println("선택한 방이름 : " + roomName);
            }
        }

        // 채팅방 삭제
        if (sel >= 0 && e.getSource() == btn_exit) {
            int answer = JOptionPane.showConfirmDialog(this, "삭제하시겠습니까?", "날 지울거야?", JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) { // 사용자가 yes를 눌렀을 경우
                model.remove(sel);
                System.out.println("삭제 성공");
            } else { // 사용자가 Yes 이외의 값을 눌렀을 경우
                System.out.println("삭제 취소");
            }
        }
        // 방 선택 안하고 삭제버튼 눌렀을 때
        else if (e.getSource() == btn_exit) {
            if (roomName == null) {
                JOptionPane.showMessageDialog(this, "삭제할 대화방을 선택하세요");
                System.out.println("선택안함");
                return;// actionPerform()탈출
            } else {
                System.out.println("선택한 방이름 : " + roomName);
            }
        }

    }// end of actionPerformed

    ////////////// 메인
    public static void main(String[] args) {
        new ChatListView();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}