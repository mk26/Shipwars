import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.sql.SQLException;


public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField txtFldP1user;
    private JTextField txtFldP2user;
    private JLabel lblPlayer2;
    private JPasswordField txtFldP1pass;
    private JPasswordField txtFldP2pass;
    private JButton btnRegister;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JButton btnResetPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Login() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        contentPane = new JPanel();
        setContentPane(contentPane);
        setTitle("ShipWars - Login");
        setBounds(100, 100, 640, 416);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        txtFldP1user = new JTextField();
        txtFldP1user.setToolTipText("Username");
        txtFldP1user.setBounds(32, 162, 200, 28);
        getContentPane().add(txtFldP1user);
        txtFldP1user.setColumns(10);

        txtFldP2user = new JTextField();
        txtFldP2user.setToolTipText("Username");
        txtFldP2user.setColumns(10);
        txtFldP2user.setBounds(406, 162, 200, 28);
        getContentPane().add(txtFldP2user);

        JLabel lblPlayer1 = new JLabel("Player 1");
        lblPlayer1.setForeground(Color.WHITE);
        lblPlayer1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblPlayer1.setBounds(36, 122, 81, 28);
        getContentPane().add(lblPlayer1);

        lblPlayer2 = new JLabel("Player 2");
        lblPlayer2.setForeground(Color.WHITE);
        lblPlayer2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblPlayer2.setBounds(522, 122, 81, 28);
        getContentPane().add(lblPlayer2);

        txtFldP1pass = new JPasswordField();
        txtFldP1pass.setToolTipText("Password");
        txtFldP1pass.setBounds(32, 202, 200, 28);
        getContentPane().add(txtFldP1pass);

        txtFldP2pass = new JPasswordField();
        txtFldP2pass.setToolTipText("Password");
        txtFldP2pass.setBounds(406, 202, 200, 28);
        getContentPane().add(txtFldP2pass);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (txtFldP1user.getText().equals("") || txtFldP2user.getText().equals("")) {
                    JOptionPane.showMessageDialog(getContentPane(), "Please enter username");
                    txtFldP1user.requestFocus();
                    return;
                }

                if (String.valueOf(txtFldP1pass.getPassword()).equals("") || String.valueOf(txtFldP2pass.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(getContentPane(), "Password can't be empty, Please enter your Password");
                    txtFldP1pass.requestFocus();
                    return;
                }

                String user1 = txtFldP1user.getText();
                String password1 = String.valueOf(txtFldP1pass.getPassword());
                String player1 = null;
                try {
                    player1 = DatabaseUtility.getAuthentication(user1, password1);
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                String user2 = txtFldP2user.getText();
                String password2 = String.valueOf(txtFldP2pass.getPassword());
                String player2 = null;
                try {
                    player2 = DatabaseUtility.getAuthentication(user2, password2);
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                if(player1 == null) {
                    JOptionPane.showMessageDialog(getContentPane(), "Invalid Player 1 Credentials");
                } else if(player2 == null) {
                    JOptionPane.showMessageDialog(getContentPane(), "Invalid Player 2 Credentials");
                } else {
                    ShipWarsUtility.setPreferences("PLAYER_NAME1", player1);
                    ShipWarsUtility.setPreferences("USER_NAME1", user1);
                    ShipWarsUtility.setPreferences("PLAYER_NAME2", player2);
                    ShipWarsUtility.setPreferences("USER_NAME2", user2);
                    ShipWars window = new ShipWars();
                    window.frmShipwars.setVisible(true);
                    dispose();

        }}});

        btnLogin.setBounds(259, 339, 117, 29);
        getContentPane().add(btnLogin);

        btnRegister = new JButton("Register");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Register().setVisible(true);

            }
        });
        btnRegister.setBounds(539, 6, 95, 28);
        getContentPane().add(btnRegister);

        btnResetPassword = new JButton("Reset password");
        btnResetPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ForgotPassword().setVisible(true);
            }
        });
        btnResetPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        btnResetPassword.setBounds(250, 228, 126, 28);
        getContentPane().add(btnResetPassword);

        lblUsername = new JLabel("Username");
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblUsername.setBounds(281, 161, 81, 28);
        getContentPane().add(lblUsername);

        lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblPassword.setBounds(281, 201, 73, 28);
        getContentPane().add(lblPassword);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(ShipWars.class.getResource("/images/shipwars_login.png")));
        lblNewLabel.setBounds(0, 0, 640, 394);
        getContentPane().add(lblNewLabel);
    }
}
