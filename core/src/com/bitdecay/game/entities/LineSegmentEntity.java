package com.bitdecay.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.GameEntity;
import com.bitdecay.game.collision.CollisionKind;
import com.bitdecay.game.component.CollisionGeometryComponent;
import com.bitdecay.game.component.CollisionKindComponent;
import com.bitdecay.game.component.RenderColorComponent;
import com.bitdecay.game.component.BodyDefComponent;
import com.bitdecay.game.component.TransformComponent;
import com.bitdecay.game.math.Geom;
import com.bitdecay.game.world.LineSegment;

/**
 * Created by Monday on 12/14/2016.
 */
public class LineSegmentEntity extends GameEntity {
    public LineSegmentEntity(LineSegment line) {
        float[] geomPoints = new float[]{line.startPoint.x, line.startPoint.y, line.endPoint.x, line.endPoint.y};
        addComponent(new BodyDefComponent(geomPoints));
        addComponent(new CollisionGeometryComponent(geomPoints, CollisionGeometryComponent.Direction.DELIVERS));
        addComponent(new CollisionKindComponent(CollisionKind.WALL));

        addComponent(new TransformComponent(Vector2.Zero, Geom.NO_ROTATION));
        addComponent(new RenderColorComponent(Color.RED));
    }
}
