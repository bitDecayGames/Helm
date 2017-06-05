package com.bitdecay.game.menu;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bitdecay.game.Helm;

/**
 * Created by Monday on 6/4/2017.
 */

public class IconUtils {
    private static TextureRegion perfectIcon;


    public static void init(Helm helm) {
        perfectIcon = helm.assets.get("img/icons.atlas", TextureAtlas.class).findRegion("perfect_icon");
    }

    public static Image getPerfectIcon() {
        return new Image(perfectIcon);
    }

    public static Image getEmptyIcon() {
        return new Image();
    }
}