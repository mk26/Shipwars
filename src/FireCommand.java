public class FireCommand implements Command {

    @Override
    public void execute() {
        ShipWars.gameContext.getGameState().fire(ShipWars.gameContext);
    }
}
