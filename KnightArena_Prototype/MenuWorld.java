import greenfoot.*;

/** Main menu for Knight Arena. */
public class MenuWorld extends World {
    public MenuWorld() {
        super(960, 600, 1);
        
        setBackground("menu_bg.png");
        
        addObject(new MenuButton("play"), 480, 360);
        addObject(new MenuButton("how"), 480, 436);
    }
}
