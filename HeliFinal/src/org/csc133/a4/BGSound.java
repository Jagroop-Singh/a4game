package org.csc133.a4;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

class Sound {
    private Media m;

    public Sound(String fileName) {
        try {
            m = MediaManager.createMedia(Display.getInstance().getResourceAsStream(getClass(),
                    "/" + fileName), "audio/wav");

        } catch (Exception e) {
        }
    }

    public void play() {
        m.setVolume(50);
        m.setTime(0);
        m.play();
    }
}