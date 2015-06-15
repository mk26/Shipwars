import java.util.ArrayList;

public class ShipDecorator extends BlockDecorator {

    String imagePath;

    ShipDecorator(Block enhancedBlock,int shipSize,int pos)
    {
        super(enhancedBlock);
        this.isRevealed=false;
        imagePath = "/images/" + "ship".concat("-").concat(Integer.toString(shipSize)).concat("-").concat(Integer.toString(pos)).concat(".png");
        this.addShip();
    }

    private void addShip()
    {
        this.setImage(imagePath);
        this.defaultBlockIcon=imagePath;
    }


    @Override
    public ArrayList<String> getAttributes() {
        if (!block.getAttributes().contains("Ship")) block.getAttributes().add("Ship");
        return block.getAttributes();
    }

}
