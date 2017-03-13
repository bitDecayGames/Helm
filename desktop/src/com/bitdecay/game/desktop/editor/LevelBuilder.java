package com.bitdecay.game.desktop.editor;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.bitdecay.game.world.LevelDefinition;
import com.bitdecay.game.world.LineSegment;

import java.util.ArrayList;

/**
 * Created by Monday on 1/2/2017.
 */
public class LevelBuilder {

    public ArrayList<LineSegment> lines = new ArrayList<>();
    public ArrayList<Circle> gravityWells = new ArrayList<>();
    public ArrayList<Circle> repulsionFields = new ArrayList<>();
    public ArrayList<Circle> focusPoints = new ArrayList<>();
    public Rectangle landingPlat;
    public float landingPlatRotation;
    public Vector2 startPoint;
    public int startingFuel = 300;
    public String name = "";

    public void setStartPoint(Vector2 point) {
        startPoint = new Vector2(point);
    }

    public void setLandingPlatform(Rectangle rectangle, float rotation) {
        landingPlat = new Rectangle(rectangle);
        landingPlatRotation = rotation;
        if (landingPlat.width < 0) {
            landingPlat.x += landingPlat.width;
            landingPlat.width *= -1;
        }

        if (landingPlat.height < 0) {
            landingPlat.y += landingPlat.height;
            landingPlat.height *= -1;
        }
    }

    public void addLineSegment(Vector2 start, Vector2 end) {
        for (LineSegment line : lines) {
            if (line.startPoint.equals(start) && line.endPoint.equals(end)) {
                // same line
                return;
            } else if (line.startPoint.equals(end) && line.endPoint.equals(start)) {
                // same line
                return;
            }
        }

        lines.add(new LineSegment(start, end));
    }

    public void removeLineSegment(LineSegment line) {
        lines.remove(line);
    }

    public void addGravityWell(Vector2 position, float size) {
        gravityWells.add(new Circle(position, size));
    }

    public void removeGravityWell(Circle well) {
        gravityWells.remove(well);
    }

    public void addRepulsionField(Vector2 position, float size) {
        repulsionFields.add(new Circle(position, size));
    }

    public void removeRepulsionField(Circle field) {
        repulsionFields.remove(field);
    }

    public void addFocusPoint(Vector2 startPoint, float radius) {
        focusPoints.add(new Circle(startPoint, radius));
    }

    public void removeFocusPoint(Circle focusPoint) {
        focusPoints.remove(focusPoint);
    }

    public void setLevel(LevelDefinition level) {
        lines.clear();
        for (LineSegment levelLine : level.levelLines) {
            lines.add(levelLine);
        }

        gravityWells.clear();
        for (Circle gravityWell : level.gravityWells) {
            gravityWells.add(gravityWell);
        }

        repulsionFields.clear();
        for (Circle repulsionField : level.repulsionFields) {
            repulsionFields.add(repulsionField);
        }

        focusPoints.clear();
        for (Circle circle : level.focusPoints) {
            focusPoints.add(circle);
        }

        startPoint = level.startPosition;

        startingFuel = level.startingFuel;

        landingPlat = level.finishPlatform;
        landingPlatRotation = level.finishPlatformRotation;

        name = level.name;
    }

    public boolean isLevelValid() {
        return landingPlat != null && startPoint != null && name != null && !name.equals("");
    }

    public LevelDefinition build() {
        LevelDefinition level = new LevelDefinition();
        level.name = name;
        level.levelLines = new Array<>(lines.size());
        for (LineSegment line : lines) {
            level.levelLines.add(line);
        }

        level.gravityWells = new Array<>(gravityWells.size());
        for (Circle gravityWell : gravityWells) {
            level.gravityWells.add(gravityWell);
        }

        level.repulsionFields = new Array<>(repulsionFields.size());
        for (Circle repulsionField : repulsionFields) {
            level.repulsionFields.add(repulsionField);
        }

        for (Circle focalPoint : focusPoints) {
            level.focusPoints.add(focalPoint);
        }

        level.startPosition = new Vector2(startPoint);
        level.finishPlatform = new Rectangle(landingPlat);
        level.finishPlatformRotation = landingPlatRotation;

        level.startingFuel = startingFuel;

        return level;
    }
}
