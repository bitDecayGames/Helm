package com.bitdecay.game.scoring;

import com.badlogic.gdx.utils.Array;
import com.bitdecay.game.world.WorldInstance;
import com.bitdecay.game.world.WorldUtils;

/**
 * Created by Monday on 6/14/2017.
 */

public class ScoreUtils {
    public static int getTotalHighScore() {
        Array<WorldInstance> worlds = WorldUtils.getWorlds();
        int totalHighScore = 0;
        for (WorldInstance world : worlds) {
            totalHighScore += world.getHighScore();
        }
        return totalHighScore;
    }
}
