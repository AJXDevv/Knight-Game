import greenfoot.*;

/** Short instructions for the prototype. */
public class HelpWorld extends World {
    public HelpWorld() {
        super(960, 600, 1);
        setBackground("help_bg.png");
        addObject(new MenuButton("back"), 480, 541);
    }
}
