package org.csc133.a4;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;


public class GlassCockpit extends Container {

    private GameClockComponent elapsedTime;
    private CockpitDisplay FuelLevel;
    private CockpitDisplay Damage;
    private CockpitDisplay LivesLeft;
    private CockpitDisplay LastSkyScraperReached;
    private CockpitDisplay Heading;

    public GlassCockpit() {
        this.setLayout(new GridLayout(2, 6));
        this.add(new Label("Game Time")).add(new Label(" Fuel"))
                .add(new Label("Damage")).add(new Label("Lives"))
                .add(new Label("Last")).add(new Label("Heading"));

        setupGameTime();
        setupFuelLevel();
        setupDamage();
        setupLives();
        setupLastSkyScraper();
        setupHeading();
    }

    private void setupLastSkyScraper() {
        Container SkyScraperNumber = new Container();
        SkyScraperNumber.getAllStyles().setBgTransparency(255);
        SkyScraperNumber.getAllStyles().setBgColor(ColorUtil.WHITE);
        SkyScraperNumber.setLayout(new FlowLayout());
        LastSkyScraperReached = new CockpitDisplay(1);
        LastSkyScraperReached.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        LastSkyScraperReached.getAllStyles().setPadding(25, 25, 25, 25);
        LastSkyScraperReached.getAllStyles().setMargin(25, 25, 25, 25);
        LastSkyScraperReached.setLedColor(ColorUtil.BLUE);
        SkyScraperNumber.add(LastSkyScraperReached);
        this.add(SkyScraperNumber);
    }

    private void setupHeading() {
        Container HeadingNumber = new Container();
        HeadingNumber.getAllStyles().setBgTransparency(255);
        HeadingNumber.getAllStyles().setBgColor(ColorUtil.WHITE);
        HeadingNumber.setLayout(new FlowLayout());
        Heading = new CockpitDisplay(3);
        Heading.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        Heading.getAllStyles().setPadding(25, 25, 25, 25);
        Heading.getAllStyles().setMargin(25, 25, 25, 25);
        Heading.setLedColor(ColorUtil.BLUE);
        HeadingNumber.add(Heading);
        this.add(HeadingNumber);
    }

    public void setupGameTime() {
        Container time = new Container();
        time.getAllStyles().setBgTransparency(255);
        time.getAllStyles().setBgColor(ColorUtil.WHITE);
        time.setLayout(new FlowLayout());
        elapsedTime = newDefaultClock(25, 25, ColorUtil.GREEN);
        time.add(elapsedTime);
        this.add(time);
    }

    public void setupFuelLevel() {
        Container Fuel = new Container();
        Fuel.getAllStyles().setBgTransparency(255);
        Fuel.getAllStyles().setBgColor(ColorUtil.WHITE);
        Fuel.setLayout(new FlowLayout());
        FuelLevel = new CockpitDisplay(4);
        FuelLevel.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        FuelLevel.getAllStyles().setPadding(25, 25, 25, 25);
        FuelLevel.getAllStyles().setMargin(25, 25, 25, 25);
        FuelLevel.setLedColor(ColorUtil.BLUE);
        Fuel.add(FuelLevel);
        this.add(Fuel);
    }

    public void setupDamage() {
        Container DamageLevel = new Container();
        DamageLevel.getAllStyles().setBgTransparency(255);
        DamageLevel.getAllStyles().setBgColor(ColorUtil.WHITE);
        DamageLevel.setLayout(new FlowLayout());
        Damage = new CockpitDisplay(2);
        Damage.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        Damage.getAllStyles().setPadding(25, 25, 25, 25);
        Damage.getAllStyles().setMargin(25, 25, 25, 25);
        Damage.setLedColor(ColorUtil.BLUE);
        DamageLevel.add(Damage);
        this.add(DamageLevel);
    }

    public void setupLives() {
        Container Lives = new Container();
        Lives.getAllStyles().setBgTransparency(255);
        Lives.getAllStyles().setBgColor(ColorUtil.WHITE);
        Lives.setLayout(new FlowLayout());
        LivesLeft = new CockpitDisplay(1);
        LivesLeft.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        LivesLeft.getAllStyles().setPadding(25, 25, 25, 25);
        LivesLeft.getAllStyles().setMargin(25, 25, 25, 25);
        LivesLeft.setLedColor(ColorUtil.BLUE);
        Lives.add(LivesLeft);
        this.add(Lives);
    }

    //encapsulated method for security
    private GameClockComponent newDefaultClock(int m, int p, int ledColor) {
        GameClockComponent aClock = new GameClockComponent();
        //setPaddingUnit, specifies the PaddingUnit we're using to make
        //the code work, default code example does not work correctly without
        //it
        aClock.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        aClock.getAllStyles().setPadding(p, p, p, p);
        aClock.getAllStyles().setMargin(m, m, m, m);
        aClock.setLedColor(ledColor);
        return aClock;
    }

    public void update(Object arg) {
        IGameWorld gameProxy = (IGameWorld) arg;
        this.FuelLevel.setDigits(gameProxy.getFuelLevel());
        this.Damage.setDigits(gameProxy.getDamage()/100);
        this.LivesLeft.setDigits(gameProxy.getLives());
        this.LastSkyScraperReached.setDigits(gameProxy.getLastSkyScraperReached());
        this.Heading.setDigits(gameProxy.getHeading()>360?
                gameProxy.getHeading()-360:gameProxy.getHeading());
        this.repaint();
    }

}
