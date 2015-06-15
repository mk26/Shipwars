public class ClearCommand implements Command {

    @Override
    public void execute() {
        ShipWars.gameContext.getGameState().clear();
    }
}
