package com.bitdecay.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.bitdecay.game.Helm;
import com.bitdecay.game.Version;

/**
 * Created by Monday on 12/21/2016.
 */
public class TitleScreen implements Screen {

    SpriteBatch batch;
    Texture backgroundImage;

    Stage stage;
    Skin skin;

    Helm game;

    public TitleScreen(Helm game) {
        this.game = game;
        batch = new SpriteBatch();
        backgroundImage = new Texture(Gdx.files.internal("img/TitleScreen.png"));

        stage = new Stage();
        skin = new Skin(Gdx.files.internal("skin/skin.json"));

        buildMainMenu();

        buildVersionTag();
    }

    private void buildMainMenu() {
        Table mainMenu = new Table();
        mainMenu.setFillParent(true);
        mainMenu.align(Align.center);
        mainMenu.setOrigin(Align.center);

        Label startLabel = new Label("Start", skin);
        startLabel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });
        startLabel.setFontScale(10);

        mainMenu.add(startLabel);

        stage.addActor(mainMenu);
    }

    private void buildVersionTag() {
        Table versionTable = new Table();
        versionTable.align(Align.bottomLeft);
        versionTable.setOrigin(Align.bottomLeft);

        Label versionLabel = new Label("Version " + Version.CURRENT_VERSION, skin);
        versionLabel.setFontScale(3);

        versionTable.add(versionLabel);

        stage.addActor(versionTable);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        backgroundImage.dispose();
        batch.dispose();
        stage.dispose();
    }
}
