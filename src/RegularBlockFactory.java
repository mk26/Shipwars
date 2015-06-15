public class RegularBlockFactory implements BlockFactory{

    @Override
    public Block createBlock() {
        return new RegularBlock();
    }
}
