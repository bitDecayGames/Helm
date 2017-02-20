package com.bitdecay.game.desktop.editor.mode;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.desktop.editor.LevelBuilder;

/**
 * Created by Monday on 2/18/2017.
 */

public class GravityWellMouseMode extends AbstractDrawCircleMouseMode {
    public GravityWellMouseMode(LevelBuilder builder) {
        super(builder);
    }

    @Override
    protected void objectDrawn(Vector2 startPoint, float radius) {
        builder.addGravityWell(startPoint, radius);
    }
}