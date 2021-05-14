package org.csc133.a4;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

import java.io.IOException;

public class PlayerHelicopter extends Helicopter {
    private static PlayerHelicopter singleton_instance = null;
    private boolean phase = true;
    private PlayerHelicopter(Point2D location) {
        super(location);
        helicopterImages = new Image[2];
        try {
            helicopterImages[0] = Image.createImage("/heliPhaseOne.png");
            helicopterImages[1] = Image.createImage("/heliPhaseTwo.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image[] helicopterImages;

    public static PlayerHelicopter getInstance() {
        if (singleton_instance == null) {
            singleton_instance = new PlayerHelicopter(new Point2D(2, 2));
        }
        return singleton_instance;
    }

    @Override
    public void draw(Graphics g, Point pCmpRelPrnt) {
        // TODO Auto-generated method stub
        int i = phase?0:1;
        g.setColor(this.getColor());
        g.setColor(ColorUtil.GREEN);
        int xLoc = (int) this.getLocation().getX() + pCmpRelPrnt.getX();
        int yLoc = (int) this.getLocation().getY() + pCmpRelPrnt.getY();

        g.drawImage(
                helicopterImages[i].rotate(180).rotate(this.getHeading()),
                xLoc, yLoc, 100, 100
        );
    }
}
