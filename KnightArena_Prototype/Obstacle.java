import greenfoot.*;

/** Stone pillars, crates and barrels. They block movement. */
public class Obstacle extends Actor {
    private final int halfWidth;
    private final int halfHeight;

    public Obstacle(String imageName, int halfWidth, int halfHeight) {
        this.halfWidth = halfWidth;
        this.halfHeight = halfHeight;
        setImage(imageName);
    }

    public int getHalfWidth() { return halfWidth; }
    public int getHalfHeight() { return halfHeight; }
}
