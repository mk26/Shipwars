import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;


public class ShipWars implements Observer {

    JFrame frmShipwars;

    public static JPanel panel;
    public static JComboBox cmboxShipType;
    public static JRadioButton rdbtnH;
    public static JRadioButton rdbtnV;

    JPanel panelDeploy;
    JButton btnDeploy;
    JButton btnClear;
    ButtonGroup bgShipDirection;
    JButton btnFire;
    JPanel panelDetails;
    JLabel lblPlayer1;
    JLabel lblShips1;
    JLabel lblPowerPads1;
    JLabel lblPlayer2;
    JLabel lblShips2;
    JLabel lblPowerPads2;
    JTextArea infoText;
    JButton btnStats;

    private GameControl gameControl;
    public static GameContext gameContext;

    static Preferences prefs = Preferences.userNodeForPackage(ShipWarsUtility.class);

    public static Player playerOne = new Player(prefs.get("PLAYER_NAME1", ""));
    public static Player playerTwo = new Player(prefs.get("PLAYER_NAME2", ""));

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    ShipWars window = new ShipWars();
                    window.frmShipwars.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public ShipWars() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmShipwars = new JFrame();
        frmShipwars.setTitle("ShipWars");
        frmShipwars.setBounds(100, 100, 600, 500);
        frmShipwars.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmShipwars.getContentPane().setLayout(null);

        //GameBoard

        panel = new JPanel();
        panel.setBounds(10, 10, 400, 400);
        frmShipwars.getContentPane().add(panel);
        panel.setLayout(new GridLayout(8,8));

        playerOne.board.addToPanel(panel);

        panelDeploy = new JPanel();
        panelDeploy.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panelDeploy.setBounds(10, 422, 584, 50);
        frmShipwars.getContentPane().add(panelDeploy);
        panelDeploy.setLayout(null);

        JLabel lblShip = new JLabel("Ship Type");
        lblShip.setBounds(12, 16, 61, 16);
        panelDeploy.add(lblShip);

        cmboxShipType = new JComboBox();
        cmboxShipType.setBounds(85, 12, 100, 27);
        cmboxShipType.addItem(ShipWarsUtility.scaledImage("/images/ship-1b.png"));
        //cmboxShipType.addItem("1 Block Ship");
        cmboxShipType.addItem(ShipWarsUtility.scaledImage("/images/ship-2b.png"));
        //cmboxShipType.addItem("2 Block Ship");
        cmboxShipType.addItem(ShipWarsUtility.scaledImage("/images/ship-3b.png"));
        //cmboxShipType.addItem("3 Block Ship");
        panelDeploy.add(cmboxShipType);

        rdbtnH = new JRadioButton("Horizontal");
        rdbtnH.setBounds(200, 0, 103, 50);
        panelDeploy.add(rdbtnH);

        rdbtnV = new JRadioButton("Vertical");
        rdbtnV.setBounds(309, 0, 95, 50);
        panelDeploy.add(rdbtnV);

        bgShipDirection = new ButtonGroup();
        bgShipDirection.add(rdbtnH);
        bgShipDirection.add(rdbtnV);
        rdbtnH.setSelected(true);

        btnDeploy = new JButton("Deploy");
        btnDeploy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameControl=new GameControl(new DeployCommand());
                gameControl.execute();
                //gameContext.setGameState(new DeployState(playerTwo));
                //gameContext.transition();
                //ShipWarsUtility.refreshBoardInPanel(board, panel);
            }
        });

        btnDeploy.setBounds(475, 12, 103, 29);
        panelDeploy.add(btnDeploy);

        btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //COMMAND
                gameControl=new GameControl(new ClearCommand());
                gameControl.execute();
            }
        });

        btnClear.setBounds(407, 12, 76, 29);
        panelDeploy.add(btnClear);

        panelDetails = new JPanel();
        panelDetails.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panelDetails.setBounds(422, 10, 172, 400);
        frmShipwars.getContentPane().add(panelDetails);
        panelDetails.setLayout(null);

        lblPlayer1 = new JLabel(playerOne.getPlayerName());
        lblPlayer1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlayer1.setBounds(6, 6, 160, 50);
        panelDetails.add(lblPlayer1);

        lblShips1 = new JLabel(String.valueOf(playerOne.getShipsLeft()));
        lblShips1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        lblShips1.setBounds(6, 68, 75, 27);
        panelDetails.add(lblShips1);

        lblPowerPads1 = new JLabel(String.valueOf(playerOne.getPowerPadsLeft()));
        lblPowerPads1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        lblPowerPads1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPowerPads1.setBounds(69, 68, 97, 27);
        panelDetails.add(lblPowerPads1);

        lblPlayer2 = new JLabel(playerTwo.getPlayerName());
        lblPlayer2.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        lblPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlayer2.setBounds(6, 101, 160, 50);
        panelDetails.add(lblPlayer2);

        lblShips2 = new JLabel(String.valueOf(playerTwo.getShipsLeft()));
        lblShips2.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        lblShips2.setBounds(6, 163, 75, 27);
        panelDetails.add(lblShips2);

        lblPowerPads2 = new JLabel(String.valueOf(playerTwo.getPowerPadsLeft()));
        lblPowerPads2.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        lblPowerPads2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPowerPads2.setBounds(69, 163, 97, 27);
        panelDetails.add(lblPowerPads2);

        infoText = new JTextArea();
        infoText.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        infoText.setText("Click on blocks to deploy ships. Maximum allowed is one of each type of ship.");
        infoText.setEditable(false);
        infoText.setWrapStyleWord(true);
        infoText.setAutoscrolls(true);
        infoText.setLineWrap(true);
        infoText.setBounds(6, 215, 160, 75);
        panelDetails.add(infoText);

        btnFire = new JButton("Fire");
        btnFire.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //COMMAND
                gameControl=new GameControl(new FireCommand());
                gameControl.execute();
            }
        });
        btnFire.setBounds(6, 300, 160, 94);
        panelDetails.add(btnFire);
        btnFire.setVisible(false);

        btnStats = new JButton("Stats");
        btnStats.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Statistics().setVisible(true);
            }
        });
        btnStats.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        btnStats.setBounds(0, 185, 170, 29);
        panelDetails.add(btnStats);


        /* TEST - DECORATOR
        playerOne.board.blocks[1][2]=new BombDecorator(new PowerPad());
        System.out.print(playerOne.board.blocks[1][2].getClass());
        System.out.print(playerOne.board.blocks[1][2].getAttributes());
        System.out.print(playerOne.board.blocks[1][2].getIcon());*/

        //STATE
        gameContext=new GameContext(new DeployState(playerOne, playerTwo));
        gameContext.register(this);


        //OBSERVER
        playerOne.register(this);
        playerTwo.register(this);

        //TEST - STRATEGY
        //ShipWarsUtility.playSound("/sounds/missed.wav");

    }

    @Override
    public void update(Subject subject,int shipsLeft, int powerPadsLeft) {

        if (subject==playerOne)
        {
            lblShips1.setText("ShipBlocks : "+String.valueOf(shipsLeft));
            lblPowerPads1.setText("PowerPads : "+String.valueOf(powerPadsLeft));
        }
        else if (subject==playerTwo)
        {
            lblShips2.setText("ShipBlocks : "+String.valueOf(shipsLeft));
            lblPowerPads2.setText("PowerPads : "+String.valueOf(powerPadsLeft));
        }
    }

    @Override
    public void updateGUI() {
        playerOne.board.hideBlocks();
        playerTwo.board.hideBlocks();
        panelDeploy.setVisible(false);
        btnFire.setVisible(true);
        infoText.setText("Click on a block to plant bombs. Click Fire when ready. Shots per turn depend ships left. Powerpads destroy blocks around them also");

        if(gameContext.getCurrentPlayer()==playerOne)
        {
            lblPlayer1.setForeground(Color.RED);
            lblPlayer2.setForeground(Color.black);
            playerTwo.board.shotsLeft=playerOne.board.getShotsLeft();
            playerTwo.board.shotsPlacedInTurn=0;
            ShipWarsUtility.refreshBoard(playerTwo.board);
            if(playerOne.getShipsLeft()==0) {

                DatabaseUtility.updateScore(prefs.get("USER_NAME2", ""), "won");
                DatabaseUtility.updateScore(prefs.get("USER_NAME2", ""), "plays");
                DatabaseUtility.updateScore(prefs.get("USER_NAME1", ""), "lost");
                DatabaseUtility.updateScore(prefs.get("USER_NAME1", ""), "plays");

                ShipWarsUtility.playSound("/sounds/win.wav");
                int option=JOptionPane.showConfirmDialog(ShipWars.panel, "GAME OVER! "+playerTwo.getPlayerName()+" Wins!","Game Over",JOptionPane.DEFAULT_OPTION);
                if(option==0)
                {
                    frmShipwars.dispose();
                    System.exit(0);
                }
                /*playerOne.board.restoreAllBlocks();
                playerTwo.board.restoreAllBlocks();
                ShipWarsUtility.refreshBoard(playerOne.board);
                ShipWarsUtility.refreshBoard(playerTwo.board);*/
            }
        }
        else {
            lblPlayer1.setForeground(Color.black);
            lblPlayer2.setForeground(Color.RED);
            playerOne.board.shotsLeft=playerTwo.board.getShotsLeft();
            playerOne.board.shotsPlacedInTurn=0;
            ShipWarsUtility.refreshBoard(playerOne.board);
            if(playerTwo.getShipsLeft()==0) {

                DatabaseUtility.updateScore(prefs.get("USER_NAME1", ""), "won");
                DatabaseUtility.updateScore(prefs.get("USER_NAME1", ""), "plays");
                DatabaseUtility.updateScore(prefs.get("USER_NAME2", ""), "lost");
                DatabaseUtility.updateScore(prefs.get("USER_NAME2", ""), "plays");

                ShipWarsUtility.playSound("/sounds/win.wav");
                int option=JOptionPane.showConfirmDialog(ShipWars.panel, "GAME OVER! "+playerOne.getPlayerName()+" Wins!","Game Over",JOptionPane.DEFAULT_OPTION);
                if(option==0)
                {
                    frmShipwars.dispose();
                    System.exit(0);
                }
                /*playerOne.board.restoreAllBlocks();
                playerTwo.board.restoreAllBlocks();
                ShipWarsUtility.refreshBoard(playerOne.board);
                ShipWarsUtility.refreshBoard(playerTwo.board);*/
            }
        }
    }
}