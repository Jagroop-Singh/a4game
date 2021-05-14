//This class represents a SkyScraper
package org.csc133.a4;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

import java.io.IOException;

public class SkyScraper extends Fixed implements IDrawable, ICollider {
    private int size;
    private int sequenceNumber;
    private final int SkyScrWidth = 100;
    private final int SkyScrHEIGHT = 100;
    private int color = ColorUtil.BLACK;
    private boolean visited = false;
    Image helipad;


    public SkyScraper(int sequenceNumber, Point2D location) {
        this.setLocation(location);
        this.size = 10;
        this.sequenceNumber = sequenceNumber;
        try {
            helipad = Image.createImage("/heliLandRed.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "SkyScraper{" +
                super.toString() +
                "size=" + size +
                ", sequenceNumber=" + sequenceNumber +
                '}';
    }

    @Override
    public void draw(Graphics g, Point pCmpRelPrnt) {
        // TODO Auto-generated method stub
        g.setColor(this.color);
        int xLoc = (int) this.getLocation().getX() + pCmpRelPrnt.getX() - (SkyScrWidth / 2);
        int yLoc = (int) this.getLocation().getY() + pCmpRelPrnt.getY() - (SkyScrHEIGHT / 2);

//        g.drawArc(xLoc, yLoc, SkyScrWidth, SkyScrHEIGHT, 0, 360);
//        g.drawRect(xLoc,yLoc,SkyScrWidth,SkyScrHEIGHT);
        if (visited) {
            try {
                helipad = Image.createImage("/heliLandGreen.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        g.drawString(Integer.toString(this.getSequenceNumber()), xLoc, yLoc);
        g.drawImage(
                helipad,
                xLoc,
                yLoc,
                this.SkyScrWidth,
                this.SkyScrHEIGHT
        );
    }

    @Override
    public boolean collidesWith(ICollider other) {
        boolean retval = false;

        double thisCenterX = this.getLocation().getX();
        double thisCenterY = this.getLocation().getY();

        double otherCenterX = ((GameObject) other).getLocation().getX();
        double otherCenterY = ((GameObject) other).getLocation().getY();

        double dx = thisCenterX - otherCenterX;
        double dy = thisCenterY - otherCenterY;

        double distBetweenCentersSqr = (dx * dx + dy * dy);

        int thisRadius = this.SkyScrHEIGHT / 2;
        int otherRadius = ((GameObject) other).getSize() / 2;

        int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);

        if (distBetweenCentersSqr <= radiiSqr) {
            retval = true;
        }

        return retval;
    }

    @Override
    public void handleCollision(ICollider other) {
        if (other instanceof NPH) {
            NPH enemyHeli = (NPH) other;
            if (enemyHeli.getLastSkyScraperReached() == this.sequenceNumber - 1) {
                enemyHeli.setLastSkyScraperReached(this.sequenceNumber);
            }
        } else if (other instanceof Helicopter) {
            Helicopter heli = (Helicopter) other;
            if (heli.getLastSkyScraperReached() == this.sequenceNumber - 1) {
                heli.setLastSkyScraperReached(this.sequenceNumber);
                this.visited = true;
                this.color = ColorUtil.GREEN;
            }
        }

    }
}
