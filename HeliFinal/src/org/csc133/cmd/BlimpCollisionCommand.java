package org.csc133.cmd;

import com.codename1.ui.Command;
import org.csc133.a4.GameWorld;

import java.awt.event.ActionEvent;

public class BlimpCollisionCommand extends Command {
    GameWorld gw;
    public BlimpCollisionCommand(GameWorld gw) {
        super("Collide With a Blimp");
        this.gw = gw;
    }

    public void actionPerformed(ActionEvent evt){
        gw.collideHeli('e');
    }
}
