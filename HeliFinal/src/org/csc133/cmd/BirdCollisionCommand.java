package org.csc133.cmd;

import com.codename1.ui.Command;
import org.csc133.a4.GameWorld;

import java.awt.event.ActionEvent;

public class BirdCollisionCommand extends Command {
    GameWorld gw;

    public BirdCollisionCommand(GameWorld gw) {
        super("Collide with Bird");
        this.gw = gw;

    }

    public void actionPerformed(ActionEvent evt) {
        gw.collideHeli('c');
    }
}
