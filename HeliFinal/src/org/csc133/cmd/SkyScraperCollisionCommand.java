package org.csc133.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a4.GameWorld;

public class SkyScraperCollisionCommand extends Command {
    GameWorld gw;
    public SkyScraperCollisionCommand(GameWorld gw){
        super("SkyScraper Collision");
        this.gw = gw;
    }
    @Override
    public void actionPerformed(ActionEvent evt){
        int skyScraperNumber = -1;
        final TextField skyScraperTextField = new TextField("","",4,
                TextArea.NUMERIC);
        Dialog.show("Enter SkyScraper Number", skyScraperTextField,
                new Command("Enter"));
        boolean validateNumber = true;
        String input = skyScraperTextField.getText();
        try{
            skyScraperNumber = Integer.parseInt(input);
        }catch (NumberFormatException e){
            validateNumber = false;
        }
        if(validateNumber)
            gw.collideHeli((char) skyScraperNumber);
    }
}
