//This class reprensents the Refueling balloons that supply fuel to the player
package org.csc133.a4;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;

import java.io.IOException;

public class RefuelingBlimp extends Fixed implements IDrawable, ICollider {
    private int capacity;
    private Image blimpImage;

    public RefuelingBlimp() {
        this.setColor(0, 255, 0);

        this.capacity = this.getSize();
        try{
            blimpImage = Image.createImage("/Blimp.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    //empty capacity because of refuel, and change color
    public void refuel() {
        this.setCapacity(0);
        this.setColor(this.getColor() - 1);
    }

    @Override
    public String toString() {
        return "RefuelingBlimp{" +
                super.toString() +
                "capacity=" + capacity +
                '}';
    }

    @Override
    public void draw(Graphics g, Point pCmpRelPrnt) {
        // TODO Auto-generated method stub
        g.setColor(this.getColor());
        g.setColor(ColorUtil.BLACK);
        int xLoc =
                (int) this.getLocation().getX() + pCmpRelPrnt.getX() - (10 / 2);
        int yLoc =
                (int) this.getLocation().getY() + pCmpRelPrnt.getY() - (10 / 2);

        g.drawImage(blimpImage, xLoc,yLoc,this.getSize(),this.getSize());
        g.setColor(ColorUtil.BLACK);
        g.drawString(Integer.toString(capacity), xLoc, yLoc);
//        g.fillArc(xLoc, yLoc, 30, 30, 0, 360);

    }

    @Override
    public boolean collidesWith(ICollider other) {
        boolean result = false;
        double thisCenterX = this.getLocation().getX();
        double thisCenterY = this.getLocation().getY();

        double otherCenterX = ((GameObject) other).getLocation().getX();
        double otherCenterY = ((GameObject) other).getLocation().getY();

        double dx = thisCenterX - otherCenterX;
        double dy = thisCenterY - otherCenterY;

        double distBetweenCentersSqr = (dx * dx + dy * dy);

        int thisRadius = this.getSize() / 2;
        int otherRadius = ((GameObject) other).getSize() / 2;

        int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);

        if (distBetweenCentersSqr <= radiiSqr) {
            result = true;
        }

        return result;
    }

    @Override
    public void handleCollision(ICollider other) {
        if(other instanceof Helicopter){
            Helicopter heli = (Helicopter) other;
            heli.setFuelLevel(heli.getFuelLevel() + this.getCapacity());
            heli.refuel(this);
        }
    }
}
