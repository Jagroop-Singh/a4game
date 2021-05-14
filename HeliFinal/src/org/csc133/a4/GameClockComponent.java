package org.csc133.a4;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;

import java.awt.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.stream.IntStream;

public class GameClockComponent extends Component{
    //renamed digitalImages to digits, as digitalImages can represent any
    //picture, while digits specifies what the variable holds
    Image[] digits = new Image[10];
    Image colonImage;

    private Calendar elapsedTime;
    private Calendar startTime;
    private int ledColor;
    //converted HM_COLON_IDX and MS_COLON_IDX to field variables
    private final int numDigitsShowing = 7;
    Image[] clockDigits = new Image[numDigitsShowing];

    public GameClockComponent() {
        startTime = Calendar.getInstance();
        try {
            //replaced list of declarations with a loop
            for (int i = 0; i <= 9; i++)
                digits[i] = Image.createImage("/LED_digit_" + i + ".png");

            colonImage = Image.createImage("/LED_colon.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //replaced for loop with a for each ordered loop
        IntStream.range(0, numDigitsShowing).forEachOrdered(i ->
                clockDigits[i] = digits[0]);
        int HM_COLON_IDX = 2;
        clockDigits[HM_COLON_IDX] = colonImage;
        int MS_COLON_IDX = 5;
        clockDigits[MS_COLON_IDX] = colonImage;

        ledColor = ColorUtil.CYAN;
    }

    private void setTime(int m, int s, int ts) {
        clockDigits[0] = digits[m / 10];
        clockDigits[1] = digits[m % 10];
        clockDigits[3] = digits[s / 10];
        clockDigits[4] = digits[s % 10];
        clockDigits[6] = digits[ts];
    }

    private void setCurrentTime() {
        //renamed rightNow to currentTime as currentTime is a better
        //variable description
        Calendar currentTime = Calendar.getInstance();
        int currentTenthOfASecond = currentTime.get(Calendar.MINUTE)*60*1000 +
                currentTime.get(Calendar.SECOND)*1000+
                currentTime.get(Calendar.MILLISECOND);
        int startTenthOfASecond = startTime.get(Calendar.MINUTE)*60*1000 +
                startTime.get(Calendar.SECOND)*1000+
                startTime.get(Calendar.MILLISECOND);
        int difference = currentTenthOfASecond - startTenthOfASecond;
        int minute = 0;
        int second = 0;
        int tenthOfASecond = 0;
        while (difference > 0){
            if (difference >= 60000) {
                minute++;
                difference -= 60000;
            }else if(difference >= 1000){
                second ++;
                difference -= 1000;
            }else if(difference >=100){
                tenthOfASecond ++;
                difference -= 100;
            }else{
                break;
            }
        }
        setTime(minute, second, tenthOfASecond);
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

    public void laidOut() {
        this.start();
    }

    @Override
    public boolean animate() {
        setCurrentTime();
        return true;
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
        g.setColor(ColorUtil.BLUE);
        g.fillRect(displayX + COLOR_PAD,
                displayY + COLOR_PAD,
                displayClockWidth - COLOR_PAD * 2,
                displayDigitHeight - COLOR_PAD * 2);
        g.setColor(ColorUtil.GREEN);
        g.fillRect(displayClockWidth / numDigitsShowing + COLOR_PAD,
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
    public void resetElapsedTime(){
        this.startTime = Calendar.getInstance();
        elapsedTime.set(Calendar.MINUTE, 0);
        elapsedTime.set(Calendar.SECOND, 0);
        elapsedTime.set(Calendar.MILLISECOND, 0);

    }
    public void startElapsedTime(){

    }
    public void stopElapsedTime(){

    }
    public Calendar getElapsedTime(){
        return this.elapsedTime;
    }
}
