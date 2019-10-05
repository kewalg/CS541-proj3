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
    Texture hero[];
    int heroPos = 0;
    int hero_AnimateSlow = 0;
    float gravity = 0.5f;
    float velocity = 0;
    float hero_YState;
    float hero_XState;


    @Override
    public void create() {
        batch = new SpriteBatch();
        bg = new Texture("bg.png");
        hero = new Texture[10];
        hero_YState = Gdx.graphics.getHeight() / 2;
        hero_XState = 0;
        hero[0] = new Texture("frame_1.png");
        hero[1] = new Texture("frame_2.png");
        hero[2] = new Texture("frame_3.png");
        hero[3] = new Texture("frame_4.png");
        hero[4] = new Texture("frame_5.png");
        hero[5] = new Texture("frame_6.png");
        hero[6] = new Texture("frame_7.png");
        hero[7] = new Texture("frame_8.png");
        hero[8] = new Texture("frame_9.png");
        hero[9] = new Texture("frame_10.png");
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

/*
        if (Gdx.input.isTouched()){

        }
*/

        if (hero_AnimateSlow < 4) {
            hero_AnimateSlow++;
        } else {
            hero_AnimateSlow = 0;
            if (heroPos < 9) {
                heroPos++;
            } else {
                heroPos = 0;
            }
        }


        velocity = velocity + gravity;
        hero_YState = hero_YState - velocity;
        if (hero_YState <= 0) {
            hero_YState = 0;
        }


        //batch.draw(hero[heroPos], Gdx.graphics.getWidth() / 2 - hero[heroPos].getWidth() / 2, Gdx.graphics.getHeight() / 2 - hero[heroPos].getHeight() / 2);
        //batch.draw(hero[heroPos], Gdx.graphics.getWidth() / (2) - hero[heroPos].getWidth() / 2, hero_YState);
        batch.draw(hero[heroPos], hero_XState ,hero_YState);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
