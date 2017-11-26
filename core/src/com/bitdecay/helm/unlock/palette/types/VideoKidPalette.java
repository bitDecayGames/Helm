package com.bitdecay.helm.unlock.palette.types;

import com.badlogic.gdx.graphics.Color;
import com.bitdecay.helm.system.render.GamePalette;
import com.bitdecay.helm.unlock.palette.GameColors;

/**
 * Created by Monday on 6/13/2017.
 */

public class VideoKidPalette extends GamePalette {
    private static final Color green1 =new Color(0x9bbc0fff);
    private static final Color green2 =new Color(0x8bac0fff);
    private static final Color green3 =new Color(0x306230ff);
    private static final Color green4 =new Color(0x0f380fff);


    public VideoKidPalette() {
        colors.put(GameColors.LEVEL_SEGMENT, green3);
        colors.put(GameColors.EXPLOSION, green1);
        colors.put(GameColors.SHIP_BODY, green3);
        colors.put(GameColors.LAUNCH_SMOKE, green1);
        colors.put(GameColors.FUEL_METER, green2);
        colors.put(GameColors.REPULSION_FIELD, green2);
        colors.put(GameColors.GRAVITY_WELL, green2);
        colors.put(GameColors.BOOST, green1);
        colors.put(GameColors.LANDING_PLATFORM, green1);
        colors.put(GameColors.BACKGROUND, green4);
    }
}
