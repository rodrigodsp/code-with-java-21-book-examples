package com.codewithjava21;

import java.util.Random;

public class Ball {

    private final int maxSpeed = 6;
    private final int oneThird = maxSpeed / 3;

    private boolean movingUp;
    private boolean movingLeft;
    private int ballSizeOffset;
    private int ballX;
    private int ballY;
    private int ballSize;
    private int hSpeed;
    private int vSpeed;

    public Ball(int size, int panelWidth, int brickHeightx8, int brickBuffer) {

        ballSize = size;
        ballSizeOffset = (size / 2) + 1;

        ballX = panelWidth / 2;
        ballY = brickHeightx8 + brickBuffer + 10;

        // starting speed and set angle (45 degrees)
        hSpeed = maxSpeed;
        vSpeed = maxSpeed;

        // starting direction
        Random leftRightDirection = new Random();
        movingLeft = leftRightDirection.nextBoolean();
        movingUp = false;
    }

    public void update() {

        if (movingLeft) {
            ballX -= hSpeed;
        } else {
            ballX += hSpeed;
        }

        if (movingUp) {
            ballY -= hSpeed;
        } else {
            ballY += hSpeed;
        }
    }

    public void increaseAngle() {

        if (vSpeed - oneThird > 1) {
            // don't want it to get too low (flat)
            hSpeed += oneThird;
            vSpeed -= oneThird;
        }
    }

    public void decreaseAngle() {

        if (hSpeed - oneThird > 1) {
            // don't want it to get too low (vertical)
            hSpeed -= oneThird;
            vSpeed += oneThird;
        }
    }

    public void flipVerticalDirection() {

        if (movingUp) {
            movingUp = false;
        } else {
            movingUp = true;
        }
    }


    public boolean isMovingUp() {
        return this.movingUp;
    }

    public boolean getMovingUp() {
        return this.movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public boolean isMovingLeft() {
        return this.movingLeft;
    }

    public boolean getMovingLeft() {
        return this.movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public int getBallX() {
        return this.ballX;
    }

    public void setBallX(int ballX) {
        this.ballX = ballX;
    }

    public int getBallY() {
        return this.ballY;
    }

    public void setBallY(int ballY) {
        this.ballY = ballY;
    }

    public int getBallSizeOffset() {
        return this.ballSizeOffset;
    }

    public int getBallSize() {
        return this.ballSize;
    }

    
    
}
