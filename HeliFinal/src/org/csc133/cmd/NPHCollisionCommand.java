package org.csc133.cmd;

import com.codename1.ui.Command;
import org.csc133.a4.GameWorld;

import java.awt.event.ActionEvent;

public class NPHCollisionCommand extends Command {
    GameWorld gw;

    public NPHCollisionCommand(GameWorld gw) {
        super("Collide with NPH");
        this.gw = gw;

    }

    public void actionPerformed(ActionEvent evt) {
        gw.collideHeli('g');
    }
}
