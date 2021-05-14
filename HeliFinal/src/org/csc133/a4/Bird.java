//this class represents the bird in the game
package org.csc133.a4;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;


import java.io.IOException;
import java.util.Random;

public class Bird extends Movable implements IDrawable, ICollider {
    Image birdImage;

    public Bird() {
        Random rand = new Random();
        int red = rand.nextInt(255);
        int green = rand.nextInt(255);
        int blue = rand.nextInt(255);
        int randomSpeed = rand.nextInt(5) + 5;
        int randomHeading = rand.nextInt(360);
        super.setColor(red, green, blue);
        setSpeed(randomSpeed);
        setHeading(randomHeading);
        try {
            birdImage = Image.createImage("/Bird.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //change heading randomly to avoid flying in a straight line
    public void changeHeading() {
        Random rand = new Random();
        int change = rand.nextInt(2);
        switch (change) {
            case 0:
                if (this.getHeading() + 5 > 359) {
                    this.setHeading(this.getHeading() - 5);
                } else {
                    this.setHeading(this.getHeading() + 5);
                }
                break;
            case 1:
                if (this.getHeading() - 5 < 0) {
                    this.setHeading(this.getHeading() + 5);
                } else {
                    this.setHeading(this.getHeading() - 5);
                }
                break;
        }
    }

    @Override
    public String toString() {
        return "Bird{" +
                super.toString() +
                "}";
    }

    @Override
    public void draw(Graphics g, Point pCmpRelPrnt) {
        g.setColor(this.getColor());
        g.setColor(ColorUtil.GRAY);
        int xLoc = (int) this.getLocation().getX() + pCmpRelPrnt.getX() - (getSize() / 2);
        int yLoc = (int) this.getLocation().getY() + pCmpRelPrnt.getY() - (getSize() / 2);
        g.drawImage(birdImage.rotate(this.getHeading()), xLoc, yLoc,
                30,
                30);

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
        if(other instanceof NPH)
        {
            NPH enemy = (NPH) other;
            enemy.takeDamage('c');
        }else if(other instanceof Helicopter){
            Helicopter heli = (Helicopter) other;
            heli.takeDamage('c');
        }
    }
}
