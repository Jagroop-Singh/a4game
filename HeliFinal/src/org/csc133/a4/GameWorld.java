package org.csc133.a4;

import com.codename1.ui.geom.Point2D;

import java.util.Random;

public class GameWorld implements IGameWorld {

    private double GWWidth;
    private double GWHeight;
    private boolean gameReset = false;
    private static int highestSkyScraperReached;
    private int score;
    private int lastSkyScraper;
    private int lives = 3;
    private int elapsedTime;
    private Random rand;
    private GlassCockpit gc;
    private MapView mv;
    //    private Vector<GameObject> GameObjectCollection = new Vector<GameObject>();
    private GameCollection GameObjectCollection;

    public GameWorld() {

    }

    public void init() {

        //code here to create the
        // initial game objects/setup
        this.score = 1;
        this.elapsedTime = 0;
        rand = new Random();
        Bird BirdA = new Bird();
        Bird BirdB = new Bird();
        GameObjectCollection = new GameCollection();
        skyScraperSetup();
        RefuelingBlimp BlimpOne = new RefuelingBlimp();
        RefuelingBlimp BlimpTwo = new RefuelingBlimp();
        SkyScraper one = findSkyScraper(1);
        PlayerHelicopter myHelicopter = PlayerHelicopter.getInstance();
        myHelicopter.setLocation(one.getLocation());
        int randNum = rand.nextInt(20);
        NPH enemyHelicopterOne =
                new NPH(new Point2D(one.getLocationX() + randNum,
                        one.getLocationY()));
        randNum = rand.nextInt(20);
        NPH enemyHelicopterTwo = new NPH(new Point2D(one.getLocationX() + randNum,
                one.getLocationY()));
        randNum = rand.nextInt(20);
        NPH enemyHelicopterThree = new NPH(new Point2D(one.getLocationX() + randNum,
                one.getLocationY()));

        this.lastSkyScraper = 9;
        GameObjectCollection.add(myHelicopter);
        GameObjectCollection.add(BirdA);
        GameObjectCollection.add(BirdB);
        GameObjectCollection.add(BlimpOne);
        GameObjectCollection.add(BlimpTwo);
        GameObjectCollection.add(enemyHelicopterOne);
        GameObjectCollection.add(enemyHelicopterTwo);
        GameObjectCollection.add(enemyHelicopterThree);
        if (this.highestSkyScraperReached < score) {
            highestSkyScraperReached = score;
        }
//        mv.update(this);
    }

    private void skyScraperSetup() {

        int result1 =
                rand.nextInt((int) (GWHeight - GWHeight / 10) +
                        (int) GWHeight / 10);
        int result2 =
                rand.nextInt((int) (GWWidth - GWWidth / 10)) +
                        (int) GWWidth / 10;
        GameObjectCollection.add(new SkyScraper(1,
                new Point2D(result1,
                        result2)));
        int i = 1;
        SkyScraper skyScraper = null;
        SkyScraper newSkyScraper = null;
        while (i < 9) {
            result1 =
                    rand.nextInt((int) (GWHeight - GWHeight / 10)) +
                            (int) GWHeight / 10;
            result2 =
                    rand.nextInt((int) (GWWidth - GWWidth / 10)) +
                            (int) GWWidth / 10;
            skyScraper = findSkyScraper(i);
            if (skyScraper == null) {
                break;
            }
            newSkyScraper = new SkyScraper(i + 1,
                    new Point2D(result1,
                            result2));
            if (skyScraper.collidesWith(newSkyScraper)) {
            } else {
                GameObjectCollection.add(newSkyScraper);
                i++;
            }
        }
    }

    @Override
    public int getLastSkyScraperReached() {
        return findHeli().getLastSkyScraperReached();
    }

    // additional methods here to
    // manipulate world objects and
    // related game state data
    public int getLives() {
        return lives;
    }

    @Override
    public int getFuelLevel() {
        return findHeli().getFuelLevel();
    }

    @Override
    public int getHeading() {
        return findHeli().getHeading();
    }

    @Override
    public int getDamage() {
        return findHeli().getDamageLevel();
    }

    @Override
    public GameCollection getCollection() {
        return this.GameObjectCollection;
    }

    public RefuelingBlimp BlimpCollision() {
        return new RefuelingBlimp();

    }

    //accelerate or decelerate heli speed
    public void changeSpeed(char c) {
        PlayerHelicopter myHeli = findHeli();

        switch (c) {
            case 'a':
                myHeli.changeSpeed('a');
                break;
            case 'b':
                myHeli.changeSpeed('b');
                break;
        }
    }

    //change heli heading
    public void changeDirection(char c) {
        PlayerHelicopter myHeli = findHeli();

        switch (c) {
            case 'r':
                myHeli.turn('r');
                break;
            case 'l':
                myHeli.turn('l');
                break;
        }
    }

    //map heli collision with interactions
    public void collideHeli(char c) {
        PlayerHelicopter myHeli = findHeli();
        SkyScraper nextSkyScraper = null;

        switch (c) {
            case 'c':
                myHeli.takeDamage('c');
                break;
            case 'g':
                myHeli.takeDamage('g');
                break;
            case 'e':
                RefuelingBlimp myBlimp = findBlimp();
                myHeli.collidesWith(myBlimp);
                GameObjectCollection.add(new RefuelingBlimp());
                //cases 1-9 are for skyscrapers 1-9
            case '1':
                nextSkyScraper = this.findSkyScraper(1);

                break;
            case '2':
                nextSkyScraper = this.findSkyScraper(2);
                break;
            case '3':
                nextSkyScraper = this.findSkyScraper(3);
                break;
            case '4':
                nextSkyScraper = this.findSkyScraper(4);
                break;
            case '5':
                nextSkyScraper = this.findSkyScraper(5);
                break;
            case '6':
                nextSkyScraper = this.findSkyScraper(6);
                break;
            case '7':
                nextSkyScraper = this.findSkyScraper(7);
                break;
            case '8':
                nextSkyScraper = this.findSkyScraper(8);
                break;
            case '9':
                nextSkyScraper = this.findSkyScraper(9);
                break;
        }
        myHeli.collidesWith(nextSkyScraper);
        //if Heli reached last skyscraper, win game
        if (myHeli.getLastSkyScraperReached() == this.lastSkyScraper) {
            winningMessage();
        }

    }

    //return Heli
    public PlayerHelicopter findHeli() {
        for (int i = 0; i < GameObjectCollection.getSize(); i++) {
            if (GameObjectCollection.getObjAt(i) instanceof PlayerHelicopter) {
                return (PlayerHelicopter) GameObjectCollection.getObjAt(i);
            }
        }
        System.out.println("No Helicopter found!");
        return null;
    }

    //return Blimp with capacity
    public RefuelingBlimp findBlimp() {
        for (int i = 0; i < GameObjectCollection.getSize(); i++) {
            if (GameObjectCollection.getObjAt(i) instanceof RefuelingBlimp) {
                return (RefuelingBlimp) GameObjectCollection.getObjAt(i);
            }
        }
        RefuelingBlimp myBlimp = new RefuelingBlimp();
        GameObjectCollection.add(myBlimp);
        return myBlimp;
    }

    //return skyscraper with sequence number
    public SkyScraper findSkyScraper(int snum) {
        for (int i = 0; i < GameObjectCollection.getSize(); i++) {
            if (GameObjectCollection.getObjAt(i) instanceof SkyScraper) {
                SkyScraper s =
                        (SkyScraper) GameObjectCollection.getObjAt(i);
                if (s.getSequenceNumber() == snum)
                    return s;

            }
        }
        System.out.println("No SkyScraper with sequenceNumber " + snum);
        return null;
    }

    //game clock +1, with interactions that occur with passage of time
    public void tick() {
        /*
         * Things to do when clock ticks-
         * - move objects
         */
        IIterator iterator = GameObjectCollection.getIterator();
        while (iterator.hasNext()) {
            GameObject obj = iterator.getNext();
            if (obj instanceof IMoveable) {
                IMoveable moveObj = (IMoveable) obj;
                moveObj.Move(GWWidth, GWHeight, elapsedTime); //finding each moveable object and moving it
                if (moveObj instanceof Bird) {
                    Bird birdObj = (Bird) moveObj;
                    birdObj.changeHeading();
                } else if (moveObj instanceof PlayerHelicopter) {
                    PlayerHelicopter heliObj = (PlayerHelicopter) moveObj;
                    if (heliObj.getFuelLevel()
                            < heliObj.getFuelConsumptionRate()
                            || heliObj.getDamageLevel() >=
                            heliObj.getMaximumDamageLevel()) {
                        if (this.getLives() <= 1) {
                            endGame();
                        } else {
                            this.lives--;
                            this.gameReset = true;
                        }
                    }
                    heliObj.setFuelLevel(
                            heliObj.getFuelLevel() -
                                    heliObj.
                                            getFuelConsumptionRate());
                }

            }


        }
        CheckCollisions();
    }

    private void CheckCollisions() {
        IIterator iter = GameObjectCollection.getIterator();
        while (iter.hasNext()) {
            GameObject thisObj = iter.getNext();
            if (thisObj instanceof ICollider) {
                ICollider thisColliderObj = (ICollider) thisObj;
                IIterator otherIter = GameObjectCollection.getIterator();
                while (otherIter.hasNext()) {
                    GameObject otherObj = otherIter.getNext();
                    if (otherObj instanceof ICollider && !(thisObj.equals(otherObj))) {
                        ICollider otherColliderObj = (ICollider) otherObj;

                        if (thisColliderObj.collidesWith(otherColliderObj)) {
                            thisColliderObj.handleCollision(otherColliderObj);

                        }
                    }
                }
            }
        }
    }

    //lose game message and exit
    public void endGame() {

        System.out.println("Game over, better luck next time!");
//        System.exit(0);
    }

    //win game message and exit
    public void winningMessage() {
        System.out.println("Game over, you win! Total time: " + elapsedTime);
        System.exit(0);
    }


    //Print game stats for user
//    public void printGameState() {
//        if (score > highestSkyScraperReached)
//            highestSkyScraperReached = score;
//        Helicopter myHeli = findHeli();
//        System.out.println("Highest ScyScraper Reached: " + highestSkyScraperReached);
//        System.out.println("Score: " + score);
//        System.out.println("Game Time: " + elapsedTime);
//        System.out.println("Lives Left: " + lives);
//        System.out.println("Damage Level: " + myHeli.getDamageLevel());
//        System.out.println("Fuel Level: " + myHeli.getFuelLevel());
//        System.out.println("Speed: " + myHeli.getSpeed());
//    }

    public boolean isGameReset() {
        return gameReset;
    }

    public void setGWHeight(double mapHeight) {
        GWHeight = mapHeight;
    }

    public double getGWHeight() {
        // TODO Auto-generated method stub
        return GWHeight;
    }

    public void setGWWidth(double mapWidth) {
        // TODO Auto-generated method stub
        GWWidth = mapWidth;
    }

    public double getGWWidth() {
        // TODO Auto-generated method stub
        return this.GWWidth;
    }
}
