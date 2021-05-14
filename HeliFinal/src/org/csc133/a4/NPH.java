package org.csc133.a4;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

import java.io.IOException;

public class NPH extends Helicopter implements IStrategy {
    private IStrategy strategy;
    private Image[] helicopterImages;


    public NPH(Point2D location) {
        super(location);
        helicopterImages = new Image[1];
        try{
            helicopterImages[0] = Image.createImage("/EnemyHeli.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStrategy() {

    }

    public void invokeStrategy() {

    }

    @Override
    public void draw(Graphics g, Point pCmpRelPrnt) {
        // TODO Auto-generated method stub
        g.setColor(this.getColor());
        g.setColor(ColorUtil.GREEN);
        int xLoc = (int) this.getLocation().getX() + pCmpRelPrnt.getX();
        int yLoc = (int) this.getLocation().getY() + pCmpRelPrnt.getY();

        g.drawImage(
                helicopterImages[0].rotate(180).rotate(this.getHeading()),
                xLoc, yLoc, 100, 100
        );
    }

    @Override
    public String toString() {
        return "NPH{" +
                "strategy=" + strategy +
                '}';
    }
}
