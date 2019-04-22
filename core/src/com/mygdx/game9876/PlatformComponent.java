package com.mygdx.game9876;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by CyberJoe on 8/9/2015.
 */
public class PlatformComponent implements Component {
    Vector2 originalPosition;
    float timePassed = 0;
}
