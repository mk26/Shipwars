import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Block extends JButton implements BlockInterface {

    ImageIcon blockIcon;
    String defaultBlockIcon;
    boolean isRevealed=false;
    ArrayList<String> blockAttributes=new ArrayList<String>();

    @Override
    public void setImage(String imageLocation) {
        setBorder(BorderFactory.createEmptyBorder());
        blockIcon = ShipWarsUtility.scaledImage(imageLocation);
        setIcon(blockIcon);
    }

    @Override
    public String getImage() {
        return defaultBlockIcon;
    }

    @Override
    public void setDimensions() {
        setSize(new Dimension(50, 50));
    }

    @Override
    public ArrayList<String> getAttributes() {
        return blockAttributes;
    }
}
