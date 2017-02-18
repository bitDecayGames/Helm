package com.bitdecay.game.collision;

import com.bitdecay.game.component.collide.CollisionGeometryComponent;

/**
 * Created by Monday on 2/18/2017.
 */

public class NoOpCollider extends Collider {
    public NoOpCollider(CollisionGeometryComponent geom1, CollisionGeometryComponent geom2) {
        super(geom1, geom2);
    }

    @Override
    public boolean collisionFound() {
        return false;
    }
}
