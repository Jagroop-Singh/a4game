package org.csc133.a4;

public interface ICollider {

    public boolean collidesWith(ICollider other);

    public void handleCollision(ICollider other);

}


