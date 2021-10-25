
import game.AnimationRunner;
import game.GameFlow;
import game.LevelInformation;
import game.levels.DirectHit;
import game.levels.FinalFour;
import game.levels.Green3;
import game.levels.WideEasy;

import java.util.ArrayList;
import java.util.List;

/**
 * Ass5Game class.
 */
public class Ass6Game {

    /**
     * main method.
     * @param args command line values
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        AnimationRunner ar = new AnimationRunner();
        List<LevelInformation> levels = new ArrayList<>();
        for (String s : args) {
            if (s.equals("1")) {
                levels.add(new DirectHit());
            } else if (s.equals("2")) {
                levels.add(new WideEasy());
            } else if (s.equals("3")) {
                levels.add(new Green3());
            } else if (s.equals("4")) {
                levels.add(new FinalFour());
            }
        }

        GameFlow newGame = new GameFlow(ar, ar.getKeyBoardSensor());
        newGame.runLevels(levels);

    }
}
