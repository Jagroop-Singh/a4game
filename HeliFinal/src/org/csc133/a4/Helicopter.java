//This class represents the helicopter the user controls
package org.csc133.a4;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

import java.io.IOException;

public class Helicopter extends Movable implements ISteerable, ICollider, IDrawable {

    private int stickAngle, fuelLevel, damageLevel, lastSkyScraperReached,
            maximumSpeed;
    private final int fuelConsumptionRate, maximumDamageLevel;

    private Image[] helicopterImages;

    public Helicopter(Point2D location) {
        setSize(40);
        setColor(255, 0, 0);
        this.maximumSpeed = 100;
        setFuelLevel(1000);
        this.fuelConsumptionRate = 1;
        this.maximumDamageLevel = 100;
        setDamageLevel(0);
        setLastSkyScraperReached(0);
        setHeading(0);
        setStickAngle(0);
        setSpeed(10);
        this.setLocation(location);
        helicopterImages = new Image[1];
        try {
            helicopterImages[0] = Image.createImage("/heli2.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This moves Heli to a skyscraper given by input
    public void moveToSkyScraper(SkyScraper skyScraper) {
        System.out.println("Reached SkyScraper Sequence Number " + lastSkyScraperReached);
        if (skyScraper.getSequenceNumber() == 1 +
                this.getLastSkyScraperReached()) {
            lastSkyScraperReached = skyScraper.getSequenceNumber();
            System.out.println("Score +1");
        } else {
            this.setLocation(skyScraper.getLocation());
        }
    }

    //This details whether or not to decrease or increase speed based off of
    //input
    public void changeSpeed(char c) {
        switch (c) {
            case 'a':
                this.setSpeed(Math.min(this.getSpeed() + 5,
                        this.getMaximumSpeed()));
                break;
            case 'b':
                this.setSpeed(Math.max(this.getSpeed() - 5, 0));
                break;
            default:
                // do nothing with wrong input
        }
    }

    //This details how much to turn and where to turn based on direction
    public void turn(char c) {
        switch (c) {
            case 'l':
                //turn left
                if (this.getHeading() > 20) {
                    this.setHeading(this.getHeading() - 20);
                } else {
                    this.setHeading(359);
                }
                break;
            case 'r':
                //turn right
                if (this.getHeading() < 339) {
                    this.setHeading(this.getHeading() + 20);
                } else {
                    this.setHeading(0);
                }
                break;
        }

    }

    //  Max damage level is 100, this details damage from a game object
    //  based off the game object type
    public void takeDamage(char c) {

        setColor(this.getColor());
        switch (c) {
            case 'c':
                this.setDamageLevel(this.getDamageLevel() + 10);
                this.setMaximumSpeed(this.getMaximumSpeed() - 10);
                if (this.getSpeed() > this.getMaximumSpeed()) {
                    this.setSpeed(this.getMaximumSpeed());
                }
                break;
            case 'g':
                setColor(this.getColor());
                this.setDamageLevel(this.getDamageLevel() + 20);
                this.setMaximumSpeed(this.getMaximumSpeed() - 20);
                if (this.getSpeed() > this.getMaximumSpeed()) {
                    this.setSpeed(this.getMaximumSpeed());
                }
                break;
        }

    }

    //refuel the Heli after meeting blimp
    public void refuel(RefuelingBlimp blimp) {
        this.setFuelLevel(this.getFuelLevel() + blimp.getCapacity());
        blimp.setCapacity(0);
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public int getDamageLevel() {
        return damageLevel;
    }

    public void setDamageLevel(int damageLevel) {
        this.damageLevel = damageLevel;
    }

    public int getLastSkyScraperReached() {
        return lastSkyScraperReached;
    }

    public void setLastSkyScraperReached(int lastSkyScraperReached) {
        this.lastSkyScraperReached = lastSkyScraperReached;
    }

    public int getStickAngle() {
        return stickAngle;
    }

    public void setStickAngle(int stickAngle) {
        this.stickAngle = stickAngle;
    }

    public int getMaximumSpeed() {
        return maximumSpeed;
    }

    public void setMaximumSpeed(int maximumSpeed) {
        this.maximumSpeed = maximumSpeed;
    }

    public int getFuelConsumptionRate() {
        return fuelConsumptionRate;
    }

    @Override
    public String toString() {
        return "Helicopter{" +
                super.toString() +
                "stickAngle=" + stickAngle +
                ", fuelLevel=" + fuelLevel +
                ", damageLevel=" + damageLevel +
                ", lastSkyScraperReached=" + lastSkyScraperReached +
                ", maximumSpeed=" + maximumSpeed +
                ", fuelConsumptionRate=" + fuelConsumptionRate +
                '}';
    }

    public int getMaximumDamageLevel() {
        return maximumDamageLevel;
    }

    @Override
    public void turn() {

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
        if ((other instanceof Bird)) {
            this.takeDamage('c');
        }
//        else if ((other instanceof NPH)) {
//            this.takeDamage('g');
//        }
    }
}


