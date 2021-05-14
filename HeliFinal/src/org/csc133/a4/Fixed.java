//This class shows a list of functions and attributes all if its children
// will have
package org.csc133.a4;

import com.codename1.ui.geom.Point2D;

public class Fixed extends GameObject {
    private final Point2D location;
    public Fixed(){
        this.location = super.getLocation();
    }

}
