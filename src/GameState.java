public interface GameState {
    //void transition(GameContext context);
    //void getCurrentPlayer();
    void clear();
    void deploy(GameContext context);
    void fire(GameContext context);
}
