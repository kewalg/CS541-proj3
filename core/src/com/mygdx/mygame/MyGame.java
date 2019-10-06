package com.mygdx.mygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import javax.xml.bind.Marshaller;

import sun.rmi.runtime.Log;

public class MyGame extends ApplicationAdapter {
    private Stage stage,stage1;
    private ImageButton button,button1;
    private Texture myTexture, myTexture1;
    private TextureRegion myTextureRegion,myTextureRegion1;
    private TextureRegionDrawable myTexRegionDrawable,myTexRegionDrawable1;
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

        //button for jumping
        myTexture = new Texture(Gdx.files.internal("monster.png"));
        myTexture1 = new Texture(Gdx.files.internal("monster.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTextureRegion1 = new TextureRegion(myTexture1);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
        button = new ImageButton(myTexRegionDrawable);
        button1 = new ImageButton(myTexRegionDrawable1);
        button.setPosition(1500, 0);
        button1.setPosition(500, 0);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        stage = new Stage(new ScreenViewport());
        stage1 = new Stage(new ScreenViewport());
        stage.addActor(button);
        stage1.addActor(button1);
        //Gdx.input.setInputProcessor(stage);
        //Gdx.input.setInputProcessor(stage1);
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(stage1);
        Gdx.input.setInputProcessor(inputMultiplexer);

    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        /*if (Gdx.input.isTouched()) {
            velocity = -10;
        }*/
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

        batch.draw(hero[heroPos], hero_XState, hero_YState);
        batch.end();

        //button for jumping
        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw();
        button.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if ((Gdx.input.isTouched())){
                    velocity = -10;
                }
                return false;
            }
        });
        stage1.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage1.draw(); //Draw the ui
        button1.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if (Gdx.input.isTouched()) {
                    velocity = -10;
                }
                return false;
            }
        });
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
