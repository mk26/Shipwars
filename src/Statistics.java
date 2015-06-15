import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.prefs.Preferences;


public class Statistics extends JFrame {

    private JPanel contentPane;
    static Preferences prefs = Preferences.userNodeForPackage(ShipWarsUtility.class);


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Statistics frame = new Statistics();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Statistics() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 349, 422);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblPlayer1 = new JLabel("Player 1");
        lblPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlayer1.setForeground(Color.WHITE);
        lblPlayer1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblPlayer1.setBounds(54, 55, 238, 32);
        contentPane.add(lblPlayer1);

        JLabel lblPlayed1 = new JLabel("10");
        lblPlayed1.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlayed1.setForeground(Color.WHITE);
        lblPlayed1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblPlayed1.setBounds(74, 138, 38, 32);
        contentPane.add(lblPlayed1);

        JLabel lblWon1 = new JLabel("10");
        lblWon1.setHorizontalAlignment(SwingConstants.CENTER);
        lblWon1.setForeground(Color.WHITE);
        lblWon1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblWon1.setBounds(156, 138, 38, 32);
        contentPane.add(lblWon1);

        JLabel lblLost1 = new JLabel("10");
        lblLost1.setHorizontalAlignment(SwingConstants.CENTER);
        lblLost1.setForeground(Color.WHITE);
        lblLost1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblLost1.setBounds(237, 138, 38, 32);
        contentPane.add(lblLost1);


        JLabel lblPlayer2 = new JLabel("Player 2");
        lblPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlayer2.setForeground(Color.WHITE);
        lblPlayer2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblPlayer2.setBounds(54, 218, 238, 32);
        contentPane.add(lblPlayer2);

        JLabel lblLost2 = new JLabel("10");
        lblLost2.setHorizontalAlignment(SwingConstants.CENTER);
        lblLost2.setForeground(Color.WHITE);
        lblLost2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblLost2.setBounds(237, 308, 38, 32);
        contentPane.add(lblLost2);

        JLabel lblWon2 = new JLabel("10");
        lblWon2.setHorizontalAlignment(SwingConstants.CENTER);
        lblWon2.setForeground(Color.WHITE);
        lblWon2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblWon2.setBounds(156, 308, 38, 32);
        contentPane.add(lblWon2);

        JLabel lblPlayed2 = new JLabel("10");
        lblPlayed2.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlayed2.setForeground(Color.WHITE);
        lblPlayed2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        lblPlayed2.setBounds(74, 308, 38, 32);
        contentPane.add(lblPlayed2);

        JLabel lblBackGround = new JLabel("New label");
        lblBackGround.setIcon(new ImageIcon(ShipWars.class.getResource("/images/sw_stats.png")));
        lblBackGround.setBounds(0, 0, 350, 400);
        contentPane.add(lblBackGround);

        lblPlayer1.setText(prefs.get("PLAYER_NAME1", "Guest"));
        lblPlayer2.setText(prefs.get("PLAYER_NAME2", "Robot"));
        UserStatistics userStatistics = UserStatistics.getInstance();
        int p1plays = userStatistics.getStatsCount(prefs.get("USER_NAME1", ""), "plays");
        int p1Wins = userStatistics.getStatsCount(prefs.get("USER_NAME1", ""), "won");
        int p1Loss = userStatistics.getStatsCount(prefs.get("USER_NAME1", ""), "lost");
        int p2plays = userStatistics.getStatsCount(prefs.get("USER_NAME2", ""), "plays");
        int p2Wins = userStatistics.getStatsCount(prefs.get("USER_NAME2", ""), "won");
        int p2Loss = userStatistics.getStatsCount(prefs.get("USER_NAME2", ""), "lost");
        lblPlayed1.setText(String.valueOf(p1plays));
        lblWon1.setText(String.valueOf(p1Wins));
        lblLost1.setText(String.valueOf(p1Loss));
        lblPlayed2.setText(String.valueOf(p2plays));
        lblWon2.setText(String.valueOf(p2Wins));
        lblLost2.setText(String.valueOf(p2Loss));
    }
}
