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
	Texture background;
	Texture snake;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.jpg");
		snake = new Texture("arrow1.png");
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background, 0, 0 , Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(snake, Gdx.graphics.getWidth()/2 - snake.getWidth()/2, Gdx.graphics.getHeight()/2 - snake.getHeight()/2);
		/*Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.rect(10,10,90, 90);
		shapeRenderer.end();
		*/
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		snake.dispose();
	}
}
