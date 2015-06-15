public class RegularDestroyStrategy implements BlockDestroyStrategy {

    @Override
    public Block destroyBlock(Block block) {

        if (block.getAttributes().contains("Bomb") && block.getAttributes().contains("Ship"))
        {
            block = new DestroyedDecorator(block);
            ShipWarsUtility.playSound("/sounds/bomb.wav");
        }
        else if (block.getAttributes().contains("Bomb")) {
            block = new MissedDecorator(block);
            ShipWarsUtility.playSound("/sounds/missed.wav");
        }
        return block;
    }
}
