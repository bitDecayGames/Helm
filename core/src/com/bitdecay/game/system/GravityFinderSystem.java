package com.bitdecay.game.system;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.bitdecay.game.GameEntity;
import com.bitdecay.game.GamePilot;
import com.bitdecay.game.component.GravityAffectedComponent;
import com.bitdecay.game.component.GravityProducerComponent;
import com.bitdecay.game.component.TransformComponent;
import com.bitdecay.game.component.VelocityComponent;

/**
 * Created by Monday on 12/12/2016.
 */
public class GravityFinderSystem extends AbstractIteratingGameSystem {

    public static final float WORLD_GRAVITY_STRENGTH = -10;
    public static Vector2 universalGravity = new Vector2(0, WORLD_GRAVITY_STRENGTH);

    public static Array<GameEntity> foundGravitySources = new Array<>(10);

    public GravityFinderSystem(GamePilot pilot) {
        super(pilot);
    }

    @Override
    public void before() {
        foundGravitySources.clear();
    }

    @Override
    public void actOnSingle(GameEntity entity, float delta) {
        foundGravitySources.add(entity);
    }

    @Override
    public boolean canActOn(GameEntity entity) {
        return entity.hasComponents(
                GravityProducerComponent.class,
                TransformComponent.class
        );
    }
}