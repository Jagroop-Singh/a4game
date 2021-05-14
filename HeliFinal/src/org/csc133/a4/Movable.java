package org.csc133.a4;

import com.codename1.ui.geom.Point2D;

import java.util.Random;

public class Movable extends GameObject implements IMoveable {
    private int speed;
    private int heading;
    Random rand;

    public Movable() {
        rand = new Random();
        speed = rand.nextInt(16);
        heading = rand.nextInt(360);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHeading() {
        return heading < 360? heading: heading-360;
    }

    public void setHeading(int heading) {

        this.heading = heading < 360? heading: heading-360;
    }

    public String toString() {
        String parentString = super.toString();
        String str = "Speed = " + speed + "Direction = " + heading;
        return parentString + str;
    }


    @Override
    public void Move(double mapWidth, double mapHeight, double time) {
        Point2D oldLocation = getLocation(); //get current location and store here

        Point2D newLocation = new Point2D(0, 0); // new location initialized

        int properAngle = 90 - heading; //angle because coordinate plane is
        // shifted
        double deltaX = 0;
        double deltaY = 0;



        /*
         * direction * speed results in units of distance
         */
        if (heading == 0 || heading == 180) //if only vertical movement
        {
            deltaY = Math.sin(Math.toRadians(properAngle)) * speed;
        } else if (heading == 90 || heading == 270) //only horizontal
            deltaX = Math.cos(Math.toRadians(properAngle)) * speed;
        else // if both vertical and horizontal movement required
        {
            deltaX = Math.cos(Math.toRadians(properAngle)) * speed;
            deltaY = Math.sin(Math.toRadians(properAngle)) * speed;
        }

        /*
         * Changing new location x and y by using oldLocation x and y
         * and adding delta X and delta Y
         */

        newLocation.setX(deltaX + oldLocation.getX());
        newLocation.setY(deltaY + oldLocation.getY());
//        System.out.println(newLocation.getX() + " " +  mapWidth);
        if (newLocation.getX() >= mapWidth - 30) {
            newLocation = oldLocation;
            setHeading(getHeading()+20);
        } else if (newLocation.getX() <= 0) {
            newLocation = oldLocation;
            setHeading(getHeading()+20);
        } else if (newLocation.getY() >= mapHeight - 30) {
            newLocation = oldLocation;
            setHeading(getHeading()+20);
        } else if (newLocation.getY() <= 0) {
            newLocation = oldLocation;
            setHeading(getHeading()+20);

        }
        newLocation.setX(newLocation.getX());
        newLocation.setY(newLocation.getY());


        setLocation(newLocation); //changing location to new location
    }
}
