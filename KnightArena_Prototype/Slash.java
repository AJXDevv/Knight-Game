import greenfoot.*;

/** Brief sword arc in front of the knight. */
public class Slash extends Actor {
    private int age = 0;

    public Slash(int angle) {
        setImage("slash.png");
        setRotation(angle);
    }

    public void act() {
        age++;
        getImage().setTransparency(Math.max(0, 240 - age * 29));
        if (age >= 8 && getWorld() != null) getWorld().removeObject(this);
    }
}
