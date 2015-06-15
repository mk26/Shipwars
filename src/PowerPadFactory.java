public class PowerPadFactory implements BlockFactory {

    @Override
    public Block createBlock() {
        return new PowerPad();
    }
}
