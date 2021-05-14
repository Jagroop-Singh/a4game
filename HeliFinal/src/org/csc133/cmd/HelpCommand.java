package org.csc133.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a4.GameWorld;

public class HelpCommand extends Command {
    /**
     * Creates a command for side menu for info on program.
     */
    org.csc133.a4.Game g;
    public HelpCommand(org.csc133.a4.Game g) {
        super("Help");
        this.g = g;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        g.pauseGame();
        String info = "a -> ";
        Dialog.show("Help", info, "Ok", null);
    }
}
