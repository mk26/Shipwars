public class DeployCommand implements Command {

    @Override
    public void execute() {
        ShipWars.gameContext.getGameState().deploy(ShipWars.gameContext);
    }
}
