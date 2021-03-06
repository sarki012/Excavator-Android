package com.esark.excavator;

import android.content.Context;

import com.esark.framework.Game;
import com.esark.framework.Graphics;
import com.esark.framework.Screen;
import com.esark.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    public GameScreen gameScreen = new GameScreen(game);
    public void update(float deltaTime, Context context) {
        Graphics g = game.getGraphics();
        Assets.excavatorLandscapeBackground = g.newPixmap("excavatorLandscapeBackground.png", PixmapFormat.ARGB4444);
        Assets.excavatorPortraitBackground = g.newPixmap("excavatorPortraitBackground.png", PixmapFormat.ARGB4444);
        game.setScreen(gameScreen);
    }
    public void present(float deltaTime) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}