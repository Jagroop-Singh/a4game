//This Class describes the methods and attributes that all GameObjects have
package org.csc133.a4;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;

import java.util.Random;

public class GameObject {
    private Point2D location;
    private int color;
    private int size;

    public GameObject() {
        location = new Point2D(300,200);
    }

    public double getLocationX() {
        return location.getX();
    }

    public double getLocationY() {
        return location.getY();
    }

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(double x, double y) {
        this.location = new Point2D(x, y);
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int R, int G, int B) {

    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String toString() {
        String parentDesc = super.toString();
        String myDesc =
                "Location = " + location.getX() + "," + location.getY()
                        + "color: "
                        + "[" + ColorUtil.red(color)
                        + "," + ColorUtil.green(color)
                        + "," + ColorUtil.blue(color) + "]";
        return parentDesc + myDesc;
    }

}
