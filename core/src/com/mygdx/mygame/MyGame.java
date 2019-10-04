package com.mygdx.mygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture bg;
    Texture man[];
    int manPos = 0;
    int animate_slow = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        bg = new Texture("bg.png");
        man = new Texture[10];
        man[0] = new Texture("frame_1.png");
        man[1] = new Texture("frame_2.png");
        man[2] = new Texture("frame_3.png");
        man[3] = new Texture("frame_4.png");
        man[4] = new Texture("frame_5.png");
        man[5] = new Texture("frame_6.png");
        man[6] = new Texture("frame_7.png");
        man[7] = new Texture("frame_8.png");
        man[8] = new Texture("frame_9.png");
        man[9] = new Texture("frame_10.png");
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (animate_slow < 4) {
            animate_slow++;
        } else {
            animate_slow = 0;
            if (manPos < 9) {
                manPos++;
            } else {
                manPos = 0;
            }
        }

        batch.draw(man[manPos], Gdx.graphics.getWidth() / 2 - man[manPos].getWidth() / 2, Gdx.graphics.getHeight() / 2 - man[manPos].getHeight() / 2);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
