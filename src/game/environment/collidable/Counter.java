package game.environment.collidable;

/**
 * This class counting the blocks on the screen.
 */
public class Counter {
    private int count;

    /**
     * Creates new counter.
     */
    public Counter() {
        this.count = 0;
    }
    /**
     * add number to current count.
     *
     * @param number that adds to the num of blocks
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * subtract number to current count.
     *
     * @param number that subtracts to the num of blocks
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * get current count.
     *
     * @return current count
     */
    public int getValue() {
        return this.count;
    }
}
