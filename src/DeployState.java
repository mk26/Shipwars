public class DeployState implements GameState {

    Player currentPlayer;
    Player nextPlayer;

    DeployState(Player currentPlayer, Player nextPlayer)
    {
        this.currentPlayer=currentPlayer;
        this.nextPlayer=nextPlayer;
        ShipWarsUtility.refreshBoard(currentPlayer.board);
        System.out.println("In deploy state of "+currentPlayer.getPlayerName());
    }

    @Override
    public void clear() {
        currentPlayer.board.clearDeployment();
        ShipWarsUtility.refreshBoard(currentPlayer.board);
    }

    @Override
    public void deploy(GameContext context) {

        if(currentPlayer==ShipWars.playerTwo)
        {
            if(currentPlayer.board.deployShips())
                context.setGameState(new PlayState(nextPlayer, currentPlayer,context));
        } else {
            if(currentPlayer.board.deployShips())
                context.setGameState(new DeployState(nextPlayer, currentPlayer));
        }
    }

    @Override
    public void fire(GameContext context) {

    }
}
