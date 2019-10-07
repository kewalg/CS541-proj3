package com.mygdx.mygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.Random;

import javax.xml.bind.Marshaller;

import sun.rmi.runtime.Log;

import static com.badlogic.gdx.math.MathUtils.random;

public class MyGame extends ApplicationAdapter {
    private Stage stage, stage1;
    private ImageButton button, button1;
    private Texture myTexture, myTexture1;
    private TextureRegion myTextureRegion, myTextureRegion1;
    private TextureRegionDrawable myTexRegionDrawable, myTexRegionDrawable1;
    SpriteBatch batch;
    Texture bg;
    Texture hero[];
    int heroPos = 0;
    int hero_AnimateSlow = 0;
    float gravity = 0.5f;
    float velocity = 0;
    float hero_YState;
    float hero_XState;
    ArrayList<Integer> monX = new ArrayList<Integer>();
    ArrayList<Integer> monY = new ArrayList<Integer>();
    ArrayList<Rectangle> monRectangle = new ArrayList<Rectangle>();
    Texture monster;
    int monCount = 0;
    Rectangle heroRectangle;
    int score = 0;
    BitmapFont font;
    int gameState = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        bg = new Texture("bg.png");
        hero = new Texture[10];
        hero_YState = Gdx.graphics.getHeight() / 2;
        hero_XState = 0;
        Random random;
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(10);


        //heroRectangle = new Rectangle();
        monster = new Texture("monster.png");
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

        //button for attacking
        myTexture = new Texture(Gdx.files.internal("attack.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        button = new ImageButton(myTexRegionDrawable);
        button.setPosition(1600, 100);

        //button for jumping
        myTexture1 = new Texture(Gdx.files.internal("jump.png"));
        myTextureRegion1 = new TextureRegion(myTexture1);
        myTexRegionDrawable1 = new TextureRegionDrawable(myTextureRegion1);
        button1 = new ImageButton(myTexRegionDrawable1);
        button1.setPosition(1750, 100);

        //multiple buttons connection
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        stage = new Stage(new ScreenViewport());
        stage1 = new Stage(new ScreenViewport());
        stage.addActor(button);
        stage1.addActor(button1);
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(stage1);
        Gdx.input.setInputProcessor(inputMultiplexer);

        random = new Random();
    }


    //class for generating monsters at random positions
    public void genMonsters() {
        float height = random.nextFloat() * Gdx.graphics.getHeight();
        monY.add((int) height);
        monX.add(Gdx.graphics.getWidth());
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        if (gameState == 1) {
            //game is live
            //game live
            //after every 100 count, generate one monster
            if (monCount < 100) {
                monCount++;
            } else {
                monCount = 0;
                genMonsters();
            }

            //drawing monsters on screen
            monRectangle.clear();
            for (int i = 0; i < monY.size(); i++) {
                batch.draw(monster, monX.get(i), monY.get(i));
                monX.set(i, monX.get(i) - 4);
                //adding rectangle border to monster
                monRectangle.add(new Rectangle(monX.get(i), monY.get(i), monster.getWidth(), monster.getHeight()));
            }

            velocity = velocity + gravity;
            hero_YState = hero_YState - velocity;
            if (hero_YState <= 0) {
                hero_YState = 0;
            }


        } else if (gameState == 0) {
            if (Gdx.input.isTouched()) {
                gameState = 1;
            }
            //waiting to start
        } else if (gameState == 2) {

            if (Gdx.input.isTouched()) {
                gameState = 1;
                hero_YState = Gdx.graphics.getHeight() / 2;
                score = 0;
                velocity = 0;
                monX.clear();
                monY.clear();
                monRectangle.clear();
                monCount = 0;
            }
        }


        //game live
        //after every 100 count, generate one monster
       /* if (monCount < 100) {
            monCount++;
        } else {
            monCount = 0;
            genMonsters();
        }

        //drawing monsters on screen
        monRectangle.clear();
        for (int i = 0; i < monY.size(); i++) {
            batch.draw(monster, monX.get(i), monY.get(i));
            monX.set(i, monX.get(i) - 4);
            //adding rectangle border to monster
            monRectangle.add(new Rectangle(monX.get(i), monY.get(i), monster.getWidth(), monster.getHeight()));
        }

        velocity = velocity + gravity;
        hero_YState = hero_YState - velocity;
        if (hero_YState <= 0) {
            hero_YState = 0;
        }
*/
        batch.draw(hero[heroPos], hero_XState, hero_YState);
        heroRectangle = new Rectangle(hero_XState, hero_YState, hero[heroPos].getWidth(), hero[heroPos].getHeight());

        // hero logic for deleting monster by default.
        for (int i = 0; i < monRectangle.size(); i++) {
            if (Intersector.overlaps(heroRectangle, monRectangle.get(i))) {
                score++;
                monRectangle.remove(i);
                monX.remove(i);
                monY.remove(i);
                if (button.isPressed()) {
                    gameState = 1;
                } else {
                    gameState = 2;
                }

                //gameState = 2;                  // check this logic
                break;
            }
        }
        font.draw(batch, String.valueOf(score), 100, 1000);
        batch.end();


        //button for attack
        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw();
        button.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                //animation
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
                for (int i = 0; i < monRectangle.size(); i++) {
                    if (Intersector.overlaps(heroRectangle, monRectangle.get(i))) {
                        monRectangle.remove(i);
                        monX.remove(i);
                        monY.remove(i);
                        score++;
                        break;
                    }
                }
                return false;
            }
        });

        //button for jumping
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
