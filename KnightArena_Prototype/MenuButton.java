import greenfoot.*;

/** Clickable image-based button used in menus. */
public class MenuButton extends Actor {
    private final String action;
    private boolean hovering = false;

    public MenuButton(String action) {
        this.action = action;
        setImage("button_" + action + ".png");
    }

    public void act() {
        MouseInfo info = Greenfoot.getMouseInfo();
        boolean over = info != null
            && Math.abs(info.getX() - getX()) < 147
            && Math.abs(info.getY() - getY()) < 28;
        if (over != hovering) {
            hovering = over;
            setImage("button_" + action + (hovering ? "_hover" : "") + ".png");
        }
        if (!Greenfoot.mouseClicked(this)) return;

        if (action.equals("play") || action.equals("again")) {
            Greenfoot.setWorld(new ArenaWorld());
        } else if (action.equals("how")) {
            Greenfoot.setWorld(new HelpWorld());
        } else if (action.equals("back") || action.equals("menu")) {
            Greenfoot.setWorld(new MenuWorld());
        }
    }
}
