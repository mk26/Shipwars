import java.util.ArrayList;

public class GameContext implements Subject{

    private ArrayList<Observer> observerList=new ArrayList<Observer>();
    private GameState gameState;
    private Player currentPlayer;

    GameContext(GameState gameState)
    {
        this.gameState=gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        notifyObservers();
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
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
        for (Observer stateObserver : observerList) {
            if(gameState instanceof PlayState)
            stateObserver.updateGUI();
        }
    }
}
