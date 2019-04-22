package com.mygdx.game9876.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game9876.MyGdxGame876;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1400;
		config.height = 1000;
		new LwjglApplication(new MyGdxGame876(), config);
	}
}
