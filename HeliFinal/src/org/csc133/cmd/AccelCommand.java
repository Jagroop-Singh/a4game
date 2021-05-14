package org.csc133.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a4.GameWorld;

public class AccelCommand extends Command {
    private GameWorld gw;

    /**
     * Creates a button command to accelerate the player
     *
     * @param gw - Reference to game world to invoke appropriate method
     */
    public AccelCommand(GameWorld gw) {
        super("Accelerate");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gw.changeSpeed('a');
        System.out.println("Accelerate" + " " + gw.findHeli().getSpeed());
    }
}
