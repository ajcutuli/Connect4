public class RandomAI implements CFPlayer {
    @Override
    public int nextMove(CFGame g) {
        return (int) (7*Math.random() + 1); // random int from 1 to 7
    }

    @Override
    public String getName() {
        return "Random Player";
    }
}
