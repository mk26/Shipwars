import sun.awt.image.ToolkitImage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class Board implements ActionListener, Subject {

    private static final int BSIZE = 8;
    private Block[][] blocks=new Block[8][8];

    private BlockFactory blockFactory;
    private ArrayList<Observer> observerList=new ArrayList<Observer>();
    private BlockDestroyStrategy destroyStrategy;

    //Block Configuration Lists
    private ArrayList<String> powerPadLocations = new ArrayList();
    private ArrayList<String> selectedBlocksList = new ArrayList();
    private ArrayList<String> oneBlockShipList = new ArrayList();
    private ArrayList<String> twoBlockShipList = new ArrayList();
    private ArrayList<String> threeBlockShipList = new ArrayList();
    private ArrayList<String> attackBlocksList = new ArrayList();

    int shotsLeft=3;
    public int shotsPlacedInTurn=0;


    Board()
    {
        this.blockFactory = new RegularBlockFactory();
        initBlocks();
    }

    void addToPanel(JPanel panel)
    {
        for (int i = 0; i < BSIZE; i++) {
            for (int j = 0; j < BSIZE; j++) {
                panel.add(blocks[i][j]);
            }
        }
    }

    void initBlocks() {
        for (int i = 0; i < BSIZE; i++) {
            for (int j = 0; j < BSIZE; j++) {
                blocks[i][j] = blockFactory.createBlock();
                blocks[i][j].addActionListener(this);
                blocks[i][j].setActionCommand("" + i + "" + j);
            }
        }
        deployPowerPads();
    }

    public void deployPowerPads() {
        BlockFactory powerPadFactory = new PowerPadFactory();
        for (int i = 0; i < 4; i++) {
            Random random = new Random();
            int x = random.nextInt(8);
            int y = random.nextInt(8);
            String xCoord = Integer.toString(x);
            String yCoord = Integer.toString(y);
            String blockLocation = xCoord.concat(yCoord);
            if (!powerPadLocations.contains(blockLocation)) {
                powerPadLocations.add(blockLocation);
                blocks[x][y] = powerPadFactory.createBlock();
                blocks[x][y].addActionListener(this);
                blocks[x][y].setActionCommand("" + x + "" + y);
            } else {
                i--;
            }
        }
    }

    public void hideBlocks() {
        for (int i = 0; i < BSIZE; i++) {
            for (int j = 0; j < BSIZE; j++) {
                if (!blocks[i][j].isRevealed)
                    blocks[i][j].setImage("/images/hidden.png");
                else restoreBlock(blocks[i][j]);
            }
        }
    }

    public void restoreBlock(Block block) {
        block.setImage(block.defaultBlockIcon);
    }

    public void clearDeployment()
    {
        for (int i = 0; i < BSIZE; i++) {
            for (int j = 0; j < BSIZE; j++) {
                if(!(blocks[i][j] instanceof PowerPad))
                {
                    blocks[i][j] = blockFactory.createBlock();
                    blocks[i][j].addActionListener(this);
                    blocks[i][j].setActionCommand("" + i + "" + j);
                }
            }
        }

        selectedBlocksList.clear();
        oneBlockShipList.clear();
        twoBlockShipList.clear();
        threeBlockShipList.clear();
    }

    public boolean deployShips() {
        if (oneBlockShipList.size() == 1 && twoBlockShipList.size() == 2 && threeBlockShipList.size() == 3) {
            JOptionPane.showMessageDialog(ShipWars.panel, "Deployment complete");
            notifyObservers();
            return true;
        }
        else
        {
            JOptionPane.showMessageDialog(ShipWars.panel, "Deployment failed");
            //notifyObservers();
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        char[] c = e.getActionCommand().toCharArray();

        if (ShipWars.gameContext.getGameState() instanceof DeployState) {
            ShipWarsUtility.playSound("/sounds/click.wav");
            placeShips(c);
        }
        else if (ShipWars.gameContext.getGameState() instanceof PlayState) {
            placeBombs(c);
        }

        ShipWarsUtility.refreshBoard(this);
    }

    public void fire() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (blocks[i][j].getAttributes().contains("Bomb") && blocks[i][j].getAttributes().contains("Powerpad")) {
                    this.setBehaviour(new PowerDestroyStrategy());
                    if ((i + 1) < 8) blocks[i + 1][j] = destroyBlock(blocks[i + 1][j]);
                    if ((j + 1) < 8) blocks[i][j + 1] = destroyBlock(blocks[i][j + 1]);
                    if ((i - 1) >= 0) blocks[i - 1][j] = destroyBlock(blocks[i - 1][j]);
                    if ((j - 1) >= 0) blocks[i][j - 1] = destroyBlock(blocks[i][j - 1]);
                    blocks[i][j] = new DestroyedDecorator(blocks[i][j]);
                }
                else {
                    this.setBehaviour(new RegularDestroyStrategy());
                    blocks[i][j] = destroyBlock(blocks[i][j]);
                }
            }
        }
        ShipWarsUtility.refreshBoard(this);
        notifyObservers();
    }

    private void placeBombs(char[] c) {
        System.out.println("Total Shots left"+ shotsLeft);
        System.out.println("Shots placed in turn" + shotsPlacedInTurn);
        if (shotsPlacedInTurn < shotsLeft) {
            attackBlocksList.add(c[0] + "" + c[1]);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (Integer.parseInt(String.valueOf(c[0])) == i && Integer.parseInt(String.valueOf(c[1])) == j) {
                        blocks[i][j] = new BombDecorator(blocks[i][j]);
                        blocks[i][j].addActionListener(this);
                        blocks[i][j].setActionCommand("" + i + "" + j);
                        shotsPlacedInTurn++;
                        ShipWarsUtility.playSound("/sounds/fuse.wav");
                    }
                }
            }
        }
        //shotsLeft=getShotsLeft();
    }


    private void placeShips(char[] c) {
        boolean iH = ShipWars.rdbtnH.isSelected();
        boolean iV = ShipWars.rdbtnV.isSelected();

        boolean oneShip = ShipWars.cmboxShipType.getSelectedIndex()==0;
        boolean twoShip = ShipWars.cmboxShipType.getSelectedIndex()==1;
        boolean threeShip = ShipWars.cmboxShipType.getSelectedIndex()==2;

        int iValue = Integer.parseInt(String.valueOf(c[0]));
        int jValue = Integer.parseInt(String.valueOf(c[1]));
        int shipSize = 0;

        if (oneShip) {
            shipSize = 1;
        } else if (twoShip) {
            shipSize = 2;
        } else if (threeShip) {
            shipSize = 3;
        }

        System.out.println("Placing a "+String.valueOf(shipSize)+"-block ship");

        boolean flag = true;
        if (iH) {
            if ((jValue + shipSize) > 8) {
                JOptionPane.showMessageDialog(ShipWars.panel, "Ship out of bounds");
                flag = false;
            }
        } else if (iV) {
            if ((iValue + shipSize) > 8) {
                JOptionPane.showMessageDialog(ShipWars.panel, "Ship out of bounds");
                flag = false;
            }
        }
        if (flag) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (Integer.parseInt(String.valueOf(c[0])) == i && Integer.parseInt(String.valueOf(c[1])) == j) {
                        for (int k = 0; k < shipSize; k++) {
                            if (iV) {
                                String iStr = Integer.toString(i + k);
                                String jStr = Integer.toString(j);
                                String comb = iStr.concat(jStr);
                                if (!selectedBlocksList.contains(comb) && !powerPadLocations.contains(comb)) {
                                    flag = true;
                                } else {
                                    flag = false;
                                    JOptionPane.showMessageDialog(ShipWars.panel, "Ship overlap");
                                    break;
                                }
                            }
                            if (iH) {
                                String iStr = Integer.toString(i);
                                String jStr = Integer.toString(j + k);
                                String comb = iStr.concat(jStr);
                                if (!selectedBlocksList.contains(comb) && !powerPadLocations.contains(comb)) {
                                    flag = true;
                                } else {
                                    flag = false;
                                    JOptionPane.showMessageDialog(ShipWars.panel, "Ship overlap");
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (flag) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (Integer.parseInt(String.valueOf(c[0])) == i && Integer.parseInt(String.valueOf(c[1])) == j) {
                        for (int k = 0; k < shipSize; k++) {
                            if (iV) {
                                String iStr = Integer.toString(i + k);
                                String jStr = Integer.toString(j);
                                selectedBlocksList.add(iStr.concat(jStr));
                                if (shipSize == 1) {
                                    oneBlockShipList.add(iStr.concat(jStr));
                                }
                                if (shipSize == 2) {
                                    twoBlockShipList.add(iStr.concat(jStr));

                                }
                                if (shipSize == 3) {
                                    threeBlockShipList.add(iStr.concat(jStr));
                                }

                                blocks[i + k][j]=new ShipDecorator(blocks[i + k][j],shipSize,k);
                                BufferedImage image = ((ToolkitImage) blocks[i + k][j].blockIcon.getImage()).getBufferedImage();
                                blocks[i + k][j].setIcon(new ImageIcon(ShipWarsUtility.rotateImage(image, 90)));
                                blocks[i + k][j].addActionListener(this);
                                blocks[i + k][j].setActionCommand("" + (i+k) + "" + j);
                            }
                            if (iH) {
                                String iStr = Integer.toString(i);
                                String jStr = Integer.toString(j + k);
                                selectedBlocksList.add(iStr.concat(jStr));
                                if (shipSize == 1) {
                                    oneBlockShipList.add(iStr.concat(jStr));
                                }
                                if (shipSize == 2) {
                                    twoBlockShipList.add(iStr.concat(jStr));
                                }
                                if (shipSize == 3) {
                                    threeBlockShipList.add(iStr.concat(jStr));
                                }
                                blocks[i][j + k]=new ShipDecorator(blocks[i][j + k],shipSize,k);
                                blocks[i][j + k].addActionListener(this);
                                blocks[i][j + k].setActionCommand("" + i + "" + (j+k));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void register(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        int shipsLeft = getShipsLeft();
        int powerPadsLeft = getPowerPadsLeft();
        for (Observer playerObserver : observerList) {
            playerObserver.update(this, shipsLeft, powerPadsLeft);
        }
    }


    public int getShotsLeft()
    {
        /*
        if (oneBlockShipList.size() == 0) {
            shotsLeft--;
        }
        if (twoBlockShipList.size() == 0) {
            shotsLeft--;
        }
        if (threeBlockShipList.size() == 0) {
            shotsLeft--;
        }
        return shotsLeft;*/
        return getShipsLeft();
    }

    public int getShipsLeft()
    {
        int shipsLeft=0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (blocks[i][j].getAttributes().contains("Ship") && !blocks[i][j].getAttributes().contains("Destroyed")) {
                    shipsLeft++;
                }
            }
        }
        return shipsLeft;
    }

    public int getPowerPadsLeft()
    {
        int powerPadsLeft=0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (blocks[i][j] instanceof PowerPad) {
                    powerPadsLeft++;
                }
            }
        }
        return powerPadsLeft;
    }

    public void setBehaviour(BlockDestroyStrategy destroyStrategy)
    {
        this.destroyStrategy=destroyStrategy;
    }

    public BlockDestroyStrategy getBehaviour()
    {
        return destroyStrategy;
    }

    Block destroyBlock(Block block)
    {
        return destroyStrategy.destroyBlock(block);
    }
}