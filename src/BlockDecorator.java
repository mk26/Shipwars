import java.util.ArrayList;

public abstract class BlockDecorator extends Block implements BlockInterface {

    protected Block block;

    BlockDecorator(Block enhancedBlock)
    {
        this.block=enhancedBlock;
        this.isRevealed=true;
    }

    @Override
    public ArrayList<String> getAttributes() {
        return block.getAttributes();
    }
}
