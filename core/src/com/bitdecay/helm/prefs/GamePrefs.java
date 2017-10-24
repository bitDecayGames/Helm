package com.bitdecay.helm.prefs;

/**
 * Created by Monday on 1/4/2017.
 */
public class GamePrefs {
    public static final String CHOSEN_PALETTE = "chosenPalette";

    public static final String HIGH_SCORE = "highScore";
    public static final String BEST_TIME = "bestTime";

    public static final int SCORE_NOT_SET = Integer.MIN_VALUE;
    public static final float TIME_NOT_SET = Float.POSITIVE_INFINITY;

    public static final String USE_LEFT_HANDED_CONTROLS = "leftHandedControls";
    public static final boolean USE_LEFT_HANDED_CONTROLS_DEFAULT = false;

    // See usages for understanding this number. It's used in multiple ways
    public static final String SENSITIVITY = "steeringSensitivity";
    public static final int SENSITIVITY_MIN = -100;
    public static final int SENSITIVITY_MAX = 200;
    public static final int SENSITIVITY_DEFAULT = 0;

    public static final String MUTE_MUSIC = "musicMusic";
    public static final boolean MUTE_MUSIC_DEFAULT = false;

    public static final String DISABLE_BACK_BUTTON = "disableBackButton";
    public static final boolean DISABLE_BACK_BUTTON_DEFAULT = false;
}
