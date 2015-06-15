public class GameControl {

    private Command command;

    GameControl(Command command)
    {
        this.command=command;
    }

    public void execute()
    {
        command.execute();
    }
}
