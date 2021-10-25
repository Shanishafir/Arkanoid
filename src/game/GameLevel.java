package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.environment.GameEnvironment;
import game.environment.ScoreTrackingListener;
import game.environment.collidable.Counter;
import game.environment.collidable.Block;
import game.environment.collidable.BlockRemover;
import game.environment.collidable.Paddle;
import game.environment.collidable.Collidable;
import game.environment.sprite.LevelName;
import game.environment.sprite.ScoreIndicator;
import game.environment.sprite.Sprite;
import game.environment.sprite.SpriteCollection;
import game.geometry.Ball;
import game.geometry.BallRemover;
import game.geometry.Point;
import game.geometry.Rectangle;

import java.awt.Color;

/**
 * Game class.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Counter remainNumOfBlocks = new Counter();
    private BlockRemover blockRemover = new BlockRemover(this, remainNumOfBlocks);
    private Counter remainNumOfBalls = new Counter();
    private BallRemover ballRemover = new BallRemover(this, remainNumOfBalls);
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation level;
    private KeyboardSensor ks;

    /**
     * Contractor.
     *
     * @param level The level we are playing now
     * @param runner the animation runner is running
     * @param ks the key board sensor
     * @param score the score in the game
     */
    public GameLevel(LevelInformation level, AnimationRunner runner, KeyboardSensor ks, Counter score) {

        this.level = level;
        this.runner = runner;
        this.ks = ks;
        this.score = score;
    }

    /**
     * adds collidable to game environment.
     *
     * @param c collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * adds sprite to sprite collection.
     *
     * @param s sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * returns the current game environment.
     *
     * @return GameEnvironment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * initializes a new game.
     */
    public void initialize() {
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(this.score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        LevelName name = new LevelName(this.level.levelName());
        sprites.addSprite(level.getBackground());
       this.createBoundaries();
        //Creates paddle
         this.createsPaddle();
        //creates blocks
        this.createBlocks(scoreTracking);
        name.addToGame(this);
        this.remainNumOfBalls.increase(level.numberOfBalls());

    }

    /**
     * Creates blocks.
     * @param scoreTracking the listener that responsible to score
     */
    public void createBlocks(ScoreTrackingListener scoreTracking) {
        for (Block b : this.level.blocks()) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTracking);
        }
        this.remainNumOfBlocks.increase(this.level.numberOfBlocksToRemove());
    }
    /**
     * Creates , draw and add boundaries to game.
     */
    public void createBoundaries() {
        Point upperLeft1 = new Point(0, 20);
        Point upperLeft2 = new Point(0, 40);
        Point upperLeft3 = new Point(20, 600);
        Point upperLeft4 = new Point(780, 20);
        Block block1 = new Block(upperLeft1, 800, 20, Color.GRAY);
        Block block2 = new Block(upperLeft2, 20, 580, Color.GRAY);
        Block block3 = new Block(upperLeft3, 760, 20, Color.GRAY);
        Block block4 = new Block(upperLeft4, 20, 580, Color.GRAY);
        block1.addToGame(this);
        block2.addToGame(this);
        block3.addToGame(this);
        block3.addHitListener(ballRemover);
        block4.addToGame(this);
    }

    /**
     * Creates the paddle.
     */
    public void createsPaddle() {
        Rectangle paddleShape = new Rectangle(new Point(100, 560), level.paddleWidth(), 20);
        Paddle paddle = new Paddle(paddleShape, runner.getKeyBoardSensor(), Color.ORANGE, level.paddleSpeed());
        paddle.addToGame(this);
    }
    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.createBallsOnTopOfPaddle();
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Creates the balls on top of the paddle.
     */
    public void createBallsOnTopOfPaddle() {
        for (int i = 0; i < level.numberOfBalls(); i++) {
            Ball ball = new Ball(105 + 3 * i, 560, 5, Color.WHITE);
            ball.setVelocity(level.initialBallVelocities().get(i));
            ball.addToGame(this);
        }

    }

    /**
     * This method removes c from collied able list.
     *
     * @param c is the collide object that removed
     */
    public void removeCollidable(Collidable c) {
        environment.deleteCollC(c);
    }

    /**
     * This method removes sprite s from spriteCollection.
     *
     * @param s the sprite object that removed
     */
    public void removeSpriteS(Sprite s) {
        sprites.deleteSpriteS(s);
    }

    /**
     * This method runs one frame.
     *
     * @param d the surface the method draws on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(0, 0, 100));
        d.fillRectangle(0, 0, 800, 600);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (remainNumOfBlocks.getValue() < 1) {
            score.increase(100);
            this.running = false;
        }
        if (remainNumOfBalls.getValue() < 1) {
            this.running = false;
        }
        if (this.runner.getKeyBoardSensor().isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.runner.getKeyBoardSensor(),
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
    }

    /**
     * This method indicate then stop running the game.
     *
     * @return boolean parameter that tells if stop running the game
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Returns the num of balls that are of the screen.
     * @return num of balls
     */
    public int getReamainNumBalls() {
        return this.remainNumOfBalls.getValue();
    }
}