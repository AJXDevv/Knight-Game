import greenfoot.*;

/** Simple non-clickable text actor. */
public class TextLabel extends Actor {
    public TextLabel(String text, int size, Color color) {
        setImage(new GreenfootImage(text, size, color, new Color(0, 0, 0, 0)));
    }
}
