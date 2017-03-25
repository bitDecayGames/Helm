package com.bitdecay.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.bitdecay.game.Helm;
import com.bitdecay.game.input.InputReplay;
import com.bitdecay.game.persist.JsonUtils;
import com.bitdecay.game.persist.ReplayUtils;

import sun.font.TextLabel;

/**
 * Created by Monday on 2/26/2017.
 */
public class ReplaySelectScreen extends AbstractScrollingItemScreen {
    public ReplaySelectScreen(Helm game) {
        super(game);
        build();
    }

    @Override
    public String getTitle() {
        return "Replays";
    }

    @Override
    public void populateRows(Table table) {
        FileHandle replayDir = Gdx.files.local(ReplayUtils.REPLAY_DIR);
        for (final FileHandle replayFile : replayDir.list()) {
            final InputReplay replay = JsonUtils.unmarshal(InputReplay.class, replayFile);

            ClickListener watchListener = new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new GameScreen(game, replay));
                }
            };

            final ClickListener confirmListener = new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ReplayUtils.deleteReplay(replayFile);
                    game.setScreen(new ReplaySelectScreen(game));
                }
            };

            ClickListener firstDeleteListener = new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    event.getTarget().clearListeners();
                    ((Label)event.getTarget()).setText("Confirm");
                    event.getTarget().addListener(confirmListener);
                }
            };


            TextButton watchButton = new TextButton("Watch", skin);
            watchButton.getLabel().setFontScale(game.fontScale);
            watchButton.addListener(watchListener);

            Label nameLabel = new Label(replayFile.name(), skin);
            nameLabel.setAlignment(Align.center);
            nameLabel.setFontScale(game.fontScale);
            nameLabel.addListener(watchListener);

            TextButton deleteButton = new TextButton(" Delete ", skin);
            deleteButton.getLabel().setFontScale(game.fontScale);
            deleteButton.addListener(firstDeleteListener);

            table.add(watchButton).expand(false, false).padRight(game.fontScale * 10);
            table.add(nameLabel);
            table.add(deleteButton).expand(false, false).padLeft(game.fontScale * 10);
            table.row().padTop(game.fontScale * 10);
        }
    }

    @Override
    public Actor getReturnButton() {
        TextButton returnButton = new TextButton("Return to Title Screen", skin);
        returnButton.getLabel().setFontScale(game.fontScale);
        returnButton.align(Align.bottomRight);
        returnButton.setOrigin(Align.bottomRight);
        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new TitleScreen(game));
            }
        });
        return returnButton;
    }
}
