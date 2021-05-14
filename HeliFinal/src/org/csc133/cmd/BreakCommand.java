package org.csc133.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a4.GameWorld;

public class BreakCommand extends Command {
    private GameWorld gw;

    /**
     * Creates a button command to decelerate the player
     *
     * @param gw - Reference to game world to invoke appropriate method
     */
    public BreakCommand(GameWorld gw) {
        super("Decelerate");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gw.changeSpeed('b');
        System.out.println("Decelerate");
    }
}
