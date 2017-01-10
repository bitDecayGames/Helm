package com.bitdecay.game.system;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.GameEntity;
import com.bitdecay.game.GamePilot;
import com.bitdecay.game.prefs.GamePrefs;
import com.bitdecay.game.Helm;
import com.bitdecay.game.component.SteeringControlComponent;
import com.bitdecay.game.input.ActiveTouch;
import com.bitdecay.game.input.TouchTracker;

/**
 * Created by Monday on 12/16/2016.
 */
public class SteeringInputSystem extends AbstractIteratingGameSystem implements InputProcessor {

    public static int BASE_JOYSTICK_SENSITIVITY = 75;

    public static int BASE_LINEARITY = 300;
    private static float BASE_INTERSECTION = .5f;

    TouchTracker tracker = new TouchTracker(5);

    private Vector2 simpleSteeringStartVector = new Vector2();

    public SteeringInputSystem(GamePilot pilot) {
        super(pilot);
    }

    @Override
    public void actOnSingle(GameEntity entity, float delta) {
        SteeringControlComponent control = entity.getComponent(SteeringControlComponent.class);

        boolean joystickSteering = Helm.prefs.getBoolean(GamePrefs.USE_JOYSTICK_STEERING, GamePrefs.USE_JOYSTICK_STEERING_DEFAULT);
        int prefSensitivity = Helm.prefs.getInteger(GamePrefs.SENSITIVITY, GamePrefs.SENSITIVITY_DEFAULT);

        if (joystickSteering) {
            setSimpleSteeringStartPoint(control);
        }
        control.endPoint = null;

        for (ActiveTouch touch : tracker.activeTouches) {
            if (control.activeArea.contains(touch.startingLocation)) {

                if (joystickSteering) {
                    int joystickSensitivity = BASE_JOYSTICK_SENSITIVITY - prefSensitivity;
                    control.sensitivity = joystickSensitivity;
                    updateSimpleControls(control, touch);
                    float deltaX = control.endPoint.x - control.startPoint.x;
                    float deltaY = control.endPoint.y - control.startPoint.y;
                    Vector2 touchVector = new Vector2(deltaX, deltaY);
                    if (touchVector.len() > joystickSensitivity) {
                        float angle = (float) Math.atan2(touchVector.y, touchVector.x);
                        control.angle = angle;
                    }
                } else {
                    // swipe steering
                    int sensitivity = BASE_LINEARITY - prefSensitivity;
                    Vector2 deltaVector = touch.currentLocation.cpy().sub(touch.lastLocation);
                    accelerate(deltaVector, sensitivity, BASE_INTERSECTION);
                    control.angle -= deltaVector.x / sensitivity;
                }
            }
        }
    }

    private void accelerate(Vector2 deltaVector, int linearity, float intersection) {
        //function is: 1/(k+j) * x * (|x|+j)
        // k is the intersection
        // j is the linearity
        System.out.println("THIS1 linearity: " + linearity + "      intersection " + intersection);
        System.out.println("THIS1 VECTOR2 IN: " + deltaVector);
        deltaVector.x = (1f / (intersection + linearity)) * deltaVector.x * (Math.abs(deltaVector.x) + linearity);
        deltaVector.y = (1f / (intersection + linearity)) * deltaVector.y * (Math.abs(deltaVector.y) + linearity);
        System.out.println("THIS1 VECTOR2 OUT: " + deltaVector);

    }

    private void setSimpleSteeringStartPoint(SteeringControlComponent control) {
        float height_ratio = Helm.prefs.getFloat(GamePrefs.SIMPLE_STEERING_HEIGHT, .3f);
        float width_ratio = Helm.prefs.getFloat(GamePrefs.SIMPLE_STEERING_WIDTH, .3f);
        control.startPoint = control.activeArea.getSize(simpleSteeringStartVector).scl(width_ratio, height_ratio);
    }

    private void updateSimpleControls(SteeringControlComponent control, ActiveTouch touch) {
        control.endPoint = touch.currentLocation;
    }

    @Override
    public boolean canActOn(GameEntity entity) {
        return entity.hasComponent(SteeringControlComponent.class);
    }

    @Override
    public void reset() {
        // Don't want to reset the steering system?
        tracker = new TouchTracker(5);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        tracker.touchDown(screenX, screenY, pointer, button);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        tracker.touchDragged(screenX, screenY, pointer);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        tracker.touchUp(screenX, screenY, pointer, button);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
