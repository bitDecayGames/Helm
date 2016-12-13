package com.bitdecay.game;

import com.bitdecay.game.component.GameComponent;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Monday on 12/8/2016.
 */
public class GameEntity {
    private Map<Class<? extends GameComponent>,GameComponent> components = new HashMap<>();

    public boolean hasComponent(Class<?> componentClazz) {
        return components.get(componentClazz) != null;
    }

    public void addComponent(GameComponent component) {
        components.put(component.getClass(), component);
    }

    public <T> T getComponent(Class<T> componentClazz) {
        return componentClazz.cast(components.get(componentClazz));
    }
}

