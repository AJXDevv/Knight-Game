import greenfoot.*;

/** Main menu for Knight Arena. */
public class MenuWorld extends World {
    public MenuWorld() {
        super(960, 600, 1);

        // Arena picture with a dark layer on top so the text is easy to read
        GreenfootImage bg = new GreenfootImage("arena_bg.png");
        bg.setColor(new Color(0, 0, 0, 170));
        bg.fillRect(0, 0, 960, 600);
        setBackground(bg);

        addObject(new TextLabel("KNIGHT ARENA", 72, new Color(230, 100, 100)), 480, 175);
        addObject(new TextLabel("Survive the waves", 30, new Color(250, 223, 100)), 480, 235);

        addObject(new MenuButton("play"), 480, 340);
        addObject(new MenuButton("how"), 480, 415);

        addObject(new TextLabel("WASD or arrows to move     SPACE to attack", 25, new Color(255, 255, 255)), 480, 500);
    }
}