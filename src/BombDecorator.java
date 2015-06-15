import java.util.ArrayList;

public class BombDecorator extends BlockDecorator {

    BombDecorator(Block enhancedBlock)
    {
        super(enhancedBlock);
        this.addBomb();
    }

    private void addBomb()
    {
        this.setImage("/images/bomb.png");
        this.defaultBlockIcon="/images/bomb.png";
    }

    @Override
    public ArrayList<String> getAttributes() {
        if (!block.getAttributes().contains("Bomb")) block.getAttributes().add("Bomb");
        return block.getAttributes();
    }
}
