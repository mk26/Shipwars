public class PowerDestroyStrategy implements BlockDestroyStrategy{

    @Override
    public Block destroyBlock(Block block) {
        if (block.getAttributes().contains("Ship")) {
            block = new DestroyedDecorator(block);
            ShipWarsUtility.playSound("/sounds/bomb.wav");
        }
        else
        {
            block = new MissedDecorator(block);
            ShipWarsUtility.playSound("/sounds/missed.wav");
        }
        return block;
    }
}
