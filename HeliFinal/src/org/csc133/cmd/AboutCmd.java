package org.csc133.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a4.GameWorld;

public class AboutCmd extends Command 
{
	/**
	 * Creates a command for side menu for info on program.
	 */
	org.csc133.a4.Game g;
	public AboutCmd(org.csc133.a4.Game g)
	{
		super("About");
		this.g = g;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		g.pauseGame();
		String info = "Jagroop Singh\nSkyMail3000 Assignment\nCSC " +
				"133\nVersion a3";
		Dialog.show("About", info, "Ok", null);
	}
}
