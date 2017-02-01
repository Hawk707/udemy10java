import org.omg.CORBA.portable.UnknownException;

import javax.swing.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by HAWK-VAIO on 1/31/2017.
 */
public class ChatClient extends JFrame implements Runnable {
    Socket socket;
    JTextArea ta;
    JButton send, logout;
    JTextField tf;

    Thread thread;

    DataInputStream din;
    DataOutputStream dout;

    String LoginName;

    ChatClient(String login) throws UnknownException, IOException{
        super(login);
        LoginName = login;

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                try {
                    dout.writeUTF(LoginName + " " + "LOGOUT");
                    System.exit(1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        ta = new JTextArea(18, 50);
        tf = new JTextField(50);

        tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        if(tf.getText().length() > 0) {
                            dout.writeUTF(LoginName + " " + "DATA " + tf.getText().toString());
                        }
                        //clear text field:
                        tf.setText("");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        send = new JButton("Send");
        logout = new JButton("Logout");

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(tf.getText().length() > 0) {
                        dout.writeUTF(LoginName + " " + "DATA " + tf.getText().toString());
                    }
                    //clear text field:
                    tf.setText("");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dout.writeUTF(LoginName + " " + "LOGOUT");
                    System.exit(1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        socket = new Socket("localhost", 5217);

        din = new DataInputStream(socket.getInputStream());
        dout = new DataOutputStream(socket.getOutputStream());

        dout.writeUTF(LoginName);
        dout.writeUTF(LoginName + " " + "LOGIN");

        thread = new Thread(this);
        thread.start();
        setup();
    }

    private void setup() {
        setSize(600, 400);

        JPanel panel = new JPanel();

        panel.add(new JScrollPane(ta));
        panel.add(tf);
        panel.add(send);
        panel.add(logout);

        add(panel);

        setVisible(true);
    }

    @Override
    public void run() {
        while(true){
            try {
                ta.append("\n" + din.readUTF());
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
