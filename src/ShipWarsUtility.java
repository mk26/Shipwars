import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.prefs.Preferences;

public class ShipWarsUtility {

    public static ImageIcon scaledImage(String imageLocation)
    {
        return new ImageIcon(new ImageIcon(ShipWars.class.getResource(imageLocation)).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
    }

    public static void refreshBoard(Board board)
    {
        ShipWars.panel.removeAll();
        board.addToPanel(ShipWars.panel);
        ShipWars.panel.repaint();
        ShipWars.panel.updateUI();
    }

    public static BufferedImage rotateImage(BufferedImage img, double angle) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage bufferedImage =new BufferedImage(w, h, img.getType());
        Graphics2D g = bufferedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.rotate(Math.toRadians(angle), w / 2, h / 2);
        g.drawImage(img, null, 0, 0);
        return bufferedImage;
    }

    public static void playSound(String soundLocation)
    {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    ShipWars.class.getResourceAsStream(soundLocation));
            clip.open(inputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void setPreferences (String name, String value) {
        Preferences prefs = Preferences.userNodeForPackage(ShipWarsUtility.class);
        prefs.put(name, value);
    }
}

