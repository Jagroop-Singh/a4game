package org.csc133.a4;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;

import java.io.IOException;

public class CockpitDisplay extends Component {
    Image[] digits = new Image[10];
    Image colonImage;
    private int ledColor;
    private final int numDigitsShowing;
    Image[] clockDigits;

    public CockpitDisplay(int numDigitsShowing) {
        this.numDigitsShowing = numDigitsShowing;
        clockDigits = new Image[numDigitsShowing];
        try {
            //replaced list of declarations with a loop
            for (int i = 0; i <= 9; i++)
                digits[i] = Image.createImage("/LED_digit_" + i + ".png");

            colonImage = Image.createImage("/LED_colon.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDigits(0);
    }

    public void setDigits(int number) {
        if (number == 0) {
            for (int digitIndex = 0; digitIndex < numDigitsShowing; digitIndex++)
                clockDigits[digitIndex] = digits[0];
            this.repaint();
        } else {
            int i = numDigitsShowing - 1;
            while (i >= 0) {
                try {
                    clockDigits[i] = digits[number % 10];
                    number = number / 10;
                }catch (ArrayIndexOutOfBoundsException e){
                    clockDigits[i] = digits[0];
                    number  /= 10;
                }
                i--;
            }
        }
        this.repaint();
    }

    public void setLedColor(int ledColor) {
        this.ledColor = ledColor;
    }

    public void start() {
        getComponentForm().registerAnimated(this);
    }

    public void stop() {
        getComponentForm().deregisterAnimated(this);
    }

    public boolean animate() {
        this.repaint();
        return true;
    }

    public void laidOut() {
        this.start();
    }

    protected Dimension calcPreferredSize() {
        return new Dimension(colonImage.getWidth() * numDigitsShowing,
                colonImage.getHeight());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        final int COLOR_PAD = 1;

        int digitWidth = clockDigits[0].getWidth();
        int digitHeight = clockDigits[0].getHeight();
        int clockWidth = numDigitsShowing * digitWidth;

        float scaleFactor = Math.min(
                getInnerHeight() / (float) digitHeight,
                getInnerWidth() / (float) clockWidth
        );

        int displayDigitWidth = (int) (scaleFactor * digitWidth);
        int displayDigitHeight = (int) (scaleFactor * digitHeight);
        int displayClockWidth = displayDigitWidth * numDigitsShowing;

        int displayX = getX() + (getWidth() - displayClockWidth) / 2;
        int displayY = getY() + (getHeight() - displayDigitHeight) / 2;

        g.setColor(ColorUtil.BLACK);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(ledColor);
        g.fillRect(displayX + COLOR_PAD,
                displayY + COLOR_PAD,
                displayClockWidth - COLOR_PAD * 2,
                displayDigitHeight - COLOR_PAD * 2);

        for (int digitIndex = 0; digitIndex < numDigitsShowing; digitIndex++)
            g.drawImage(clockDigits[digitIndex],
                    displayX + digitIndex * displayDigitWidth,
                    displayY,
                    displayDigitWidth,
                    displayDigitHeight);
    }
}
