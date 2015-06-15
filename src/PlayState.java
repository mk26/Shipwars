public class PlayState implements GameState {

    Player currentPlayer;
    Player nextPlayer;

    PlayState(Player currentPlayer,Player nextPlayer,GameContext context)
    {
        this.currentPlayer=currentPlayer;
        this.nextPlayer=nextPlayer;
        context.setCurrentPlayer(currentPlayer);
        System.out.println("In play state of "+currentPlayer.getPlayerName());
    }

    @Override
    public void clear() {

    }

    @Override
    public void deploy(GameContext context) {

    }

    @Override
    public void fire(GameContext context) {
        nextPlayer.board.fire();
        context.setGameState(new PlayState(nextPlayer, currentPlayer,context));
    }
}
