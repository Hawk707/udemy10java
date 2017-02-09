import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

/**
 * Created by HAWK-VAIO on 2/7/2017.
 */
public class ConnectDialog extends JDialog implements ActionListener{

    boolean isCancelled = true;
    JLabel lhost = new JLabel("Host Name");
    JTextField host = new JTextField();
    JLabel lport = new JLabel("Port");
    JTextField port = new JTextField();
    JLabel ldatabase = new JLabel("Database");
    JTextField database = new JTextField();
    JLabel luser = new JLabel("User name");
    JTextField user = new JTextField();
    JLabel lpass = new JLabel("Password");
    JPasswordField pass = new JPasswordField();

    JButton ok = new JButton("OK");
    JButton cancel = new JButton("Cancel");

    Properties props;

    public ConnectDialog(JFrame owner, String title, Properties p){
        super(owner, title, true);
        setSize(300, 200);
        setLocation(250, 200);
        props = new Properties(p);
        ok.setPreferredSize(new Dimension(75, 25));
        ok.addActionListener(this);
        cancel.setPreferredSize(new Dimension(75, 25));
        cancel.addActionListener(this);

        JPanel cpanel1 = new JPanel();
        JPanel cpanel2 = new JPanel();


        cpanel1.setLayout(new GridLayout(5, 2));

        cpanel1.add(lhost);
        cpanel1.add(host);
        cpanel1.add(lport);
        cpanel1.add(port);
        cpanel1.add(ldatabase);
        cpanel1.add(database);
        cpanel1.add(luser);
        cpanel1.add(user);
        cpanel1.add(lpass);
        cpanel1.add(pass);

        cpanel2.add(ok);
        cpanel2.add(cancel);

        add(cpanel1, BorderLayout.NORTH);
        add(cpanel2, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
if(e.getSource() == ok){
    isCancelled = false;

    this.dispose();
}
    }

    public Properties getProps(){
        props.setProperty("Database", database.getText());
        props.setProperty("Host_Name", host.getText());
        props.setProperty("Port", port.getText());
        props.setProperty("User_Name", user.getText());

        return props;
    }
}
