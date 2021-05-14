package org.csc133.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a4.GameWorld;

public class LeftTurnCommand extends Command {
    private GameWorld gw;

    /**
     * Creates a button command to turn the player to the left.
     *
     * @param gw - Reference to game world to invoke appropriate method
     */
    public LeftTurnCommand(GameWorld gw) {
        super("Turn left");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gw.changeDirection('l');
        System.out.println("Turn left");
    }
}
