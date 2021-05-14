package org.csc133.a4;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.UITimer;
import org.csc133.cmd.*;

public class Game extends Form implements Runnable {
    private final double TIMETOTICK = 20;
    private int elapsedTime;
    private AccelCommand accelerateCommand;
    private BreakCommand breakCommand;
    private LeftTurnCommand leftCommand;
    private RightTurnCommand rightCommand;
    private SkyScraperCollisionCommand skyCollide;
    private BirdCollisionCommand BirdCollide;
    private BlimpCollisionCommand BlimpCollide;
    private NPHCollisionCommand nphCollide;
    private AboutCmd aboutCommand;
    private HelpCommand helpCommand;
    private QuitCommand quit;
    private buttonObj accelButton;
    private buttonObj brakeButton;
    private buttonObj rightButton;
    private buttonObj leftButton;

    private UITimer timer;
    private GameWorld gw;
    private MapView mv;
    private GlassCockpit gc;
    private boolean pauseState;

    public Game() {
        init();
    }

    public void init() {
        this.setLayout(new BorderLayout());
        this.setScrollable(false);

        gw = new GameWorld();
        mv = new MapView(gw);
        gw.setGWHeight(mv.getMapHeight());
        gw.setGWWidth(mv.getMapWidth());
        gc = new GlassCockpit();

        setup();

        this.addComponent(BorderLayout.NORTH, gc);
        this.addComponent(BorderLayout.CENTER, mv);
        this.show();


        gw.setGWHeight(mv.getHeight());
        gw.setGWWidth(mv.getWidth());
        gw.init();
        timer = new UITimer(this);
        timer.schedule((int) TIMETOTICK, true, this);
        new Sound("bg.wav").play();
    }

    private void setup() {
        setUPMenu();
        setUpCMD();

    }

    private void setUPMenu() {
        Toolbar menu = new Toolbar();
        this.setToolbar(menu);
        menu.setTitle("SkyMail3000");
        helpCommand = new HelpCommand(this);
        menu.addCommandToSideMenu(helpCommand);
        aboutCommand = new AboutCmd(this);
        menu.addCommandToSideMenu(aboutCommand);
        quit = new QuitCommand();
        addKeyListener('x', quit);
        addKeyListener(88, quit);
        menu.addCommandToSideMenu(quit);
    }

    private void setUpCMD() {
        Container mainContainer = new Container(new GridLayout(1, 4));

        accelerateCommand = new AccelCommand(gw);
        accelButton = new buttonObj(accelerateCommand);
        addKeyListener('a', accelerateCommand);
        addKeyListener(65, accelerateCommand);
        mainContainer.add(accelButton);

        breakCommand = new BreakCommand(gw);
        brakeButton = new buttonObj(breakCommand);
        addKeyListener('b', breakCommand);
        addKeyListener(66, breakCommand);
        mainContainer.add(brakeButton);

        leftCommand = new LeftTurnCommand(gw);
        leftButton = new buttonObj(leftCommand);
        addKeyListener('l', leftCommand);
        addKeyListener(76, leftCommand);
        mainContainer.add(leftButton);

        rightCommand = new RightTurnCommand(gw);
        rightButton = new buttonObj(rightCommand);
        addKeyListener('r', rightCommand);
        addKeyListener(82, rightCommand);
        mainContainer.add(rightButton);

        skyCollide = new SkyScraperCollisionCommand(gw);
        addKeyListener('s', skyCollide);
        addKeyListener(83, skyCollide);
        BirdCollide = new BirdCollisionCommand(gw);
        addKeyListener('g', BirdCollide);
        addKeyListener(71, BirdCollide);
        BlimpCollide = new BlimpCollisionCommand(gw);
        addKeyListener('e', BlimpCollide);
        addKeyListener(69, BlimpCollide);
        nphCollide = new NPHCollisionCommand(gw);
        addKeyListener('n', nphCollide);
        addKeyListener(78, nphCollide);


        mv.setWidth(this.getWidth() - mainContainer.getPreferredW() - mv.getX());

        this.addComponent(BorderLayout.SOUTH, mainContainer);

    }

    private void play() {
        // code here to accept and
        // execute user commands that
        // operate on the game world
        // (refer   to   “Appendix   -   CN1
        // Notes” for accepting
        // keyboard  commands  via  a  text
        // field located on the form)

        this.show();
    }

    @Override
    public void run() {
        gw.tick();
        mv.update(gw);
        gc.update(gw);
        elapsedTime += TIMETOTICK;


        if (gw.getLives() == 0) {
            timer.cancel();
            gw.endGame();
        }
    }

    public void pauseGame() {
        this.pauseState = !pauseState;
        accelButton.setEnabled(!accelButton.isEnabled());
        brakeButton.setEnabled(!brakeButton.isEnabled());
        leftButton.setEnabled(!leftButton.isEnabled());
        rightButton.setEnabled(!rightButton.isEnabled());
        if (pauseState) {
            timer.cancel();

            removeKeyListener('w', accelerateCommand);
            removeKeyListener(-91, accelerateCommand);


            removeKeyListener('s', breakCommand);
            removeKeyListener(-92, breakCommand);

            removeKeyListener('a', leftCommand);
            removeKeyListener(-93, leftCommand);

            removeKeyListener('d', rightCommand);
            removeKeyListener(-94, rightCommand);

        } else {
            timer.schedule((int) TIMETOTICK, true, this);
//            gc.
            addKeyListener('w', accelerateCommand);
            addKeyListener(-91, accelerateCommand);


            addKeyListener('s', breakCommand);
            addKeyListener(-92, breakCommand);

            addKeyListener('a', leftCommand);
            addKeyListener(-93, leftCommand);

            addKeyListener('d', rightCommand);
            addKeyListener(-94, rightCommand);


        }

    }
}
