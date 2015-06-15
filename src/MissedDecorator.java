import java.util.ArrayList;

public class MissedDecorator extends BlockDecorator {

    MissedDecorator(Block enhancedBlock)
    {
        super(enhancedBlock);
        this.addMissed();
    }

    private void addMissed()
    {
        this.setImage("/images/missed.png");
        this.defaultBlockIcon="/images/missed.png";
    }

    @Override
    public ArrayList<String> getAttributes() {
        if (!block.getAttributes().contains("Missed")) block.getAttributes().add("Missed");
        return block.getAttributes();
    }

}
