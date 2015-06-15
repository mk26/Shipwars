import java.util.ArrayList;

public class DestroyedDecorator extends BlockDecorator {

    DestroyedDecorator(Block enhancedBlock)
    {
        super(enhancedBlock);
        this.addDestroyed();
    }

    private void addDestroyed()
    {
        if(block.getAttributes().contains("Powerpad")) {
            this.setImage("images/powerpad_destroyed.png");
            this.defaultBlockIcon="images/powerpad_destroyed.png";
        }
            else {
            this.setImage("/images/destroyed.png");
            this.defaultBlockIcon="/images/destroyed.png";
        }
    }

    @Override
    public ArrayList<String> getAttributes() {
        if (!block.getAttributes().contains("Destroyed")) block.getAttributes().add("Destroyed");
        return block.getAttributes();
    }
}
