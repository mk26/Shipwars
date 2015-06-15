import java.util.ArrayList;

public class Player implements Subject, Observer {

    private ArrayList<Observer> observerList;
    private String playerName;
    Board board;
    private int shipsLeft=0;
    private int powerPadsLeft=0;

    Player(String playerName)
    {
        this.observerList=new ArrayList<Observer>();
        this.playerName=playerName;
        this.board=new Board();
        board.register(this);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getShipsLeft() {
        return shipsLeft;
    }

    public void setShipsLeft(int shipsLeft) {
        this.shipsLeft = shipsLeft;
        notifyObservers();
    }

    public int getPowerPadsLeft() {
        return powerPadsLeft;
    }

    public void setPowerPadsLeft(int powerPadsLeft) {
        this.powerPadsLeft = powerPadsLeft;
        notifyObservers();
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
        for (Observer scoreObserver : observerList) {
            scoreObserver.update(this, shipsLeft, powerPadsLeft);
        }
    }

    @Override
    public void update(Subject subject, int shipsLeft, int powerpadsLeft) {
        setShipsLeft(shipsLeft);
        setPowerPadsLeft(powerpadsLeft);
    }

    @Override
    public void updateGUI() {

    }
}
