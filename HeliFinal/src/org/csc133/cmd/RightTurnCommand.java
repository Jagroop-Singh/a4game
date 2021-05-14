package org.csc133.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a4.GameWorld;

public class RightTurnCommand extends Command {
    private GameWorld gw;

    /**
     * Creates a button command to turn the player to the right.
     *
     * @param gw - Reference to game world to invoke appropriate method
     */
    public RightTurnCommand(GameWorld gw) {
        super("Turn right");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gw.changeDirection('r');
        System.out.println("Turn right");
    }
}
