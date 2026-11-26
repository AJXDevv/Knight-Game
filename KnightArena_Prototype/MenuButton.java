import greenfoot.*;

/** Simple clickable button. It draws itself, so no button images are needed. */
public class MenuButton extends Actor {
    private static final int WIDTH = 260;
    private static final int HEIGHT = 56;

    private final String action;
    private final String text;
    private boolean hovering = false;

    public MenuButton(String action) {
        this.action = action;
        this.text = getLabel(action);
        draw(false);
    }

    public void act() {
        // Highlight the button when the mouse is on it
        MouseInfo info = Greenfoot.getMouseInfo();
        if (info != null) {
            boolean over = Math.abs(info.getX() - getX()) < WIDTH / 2
                && Math.abs(info.getY() - getY()) < HEIGHT / 2;
            if (over != hovering) {
                hovering = over;
                draw(hovering);
            }
        }

        if (!Greenfoot.mouseClicked(this)) {
            return;
        }

        if (action.equals("play") || action.equals("again")) {
            Greenfoot.setWorld(new ArenaWorld());
        } else if (action.equals("how")) {
            Greenfoot.setWorld(new HelpWorld());
        } else if (action.equals("back") || action.equals("menu")) {
            Greenfoot.setWorld(new MenuWorld());
        }
    }

    private String getLabel(String action) {
        if (action.equals("play")) {
            return "PLAY";
        } else if (action.equals("again")) {
            return "PLAY AGAIN";
        } else if (action.equals("how")) {
            return "HOW TO PLAY";
        } else if (action.equals("back")) {
            return "BACK";
        }
        return "MAIN MENU";
    }

    private void draw(boolean highlighted) {
        GreenfootImage image = new GreenfootImage(WIDTH, HEIGHT);

        // Box and outline
        if (highlighted) {
            image.setColor(new Color(90, 110, 120));
        } else {
            image.setColor(new Color(55, 70, 80));
        }
        image.fillRect(0, 0, WIDTH, HEIGHT);
        image.setColor(new Color(240, 230, 200));
        image.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);

        // Text in the middle
        GreenfootImage label = new GreenfootImage(text, 26, Color.WHITE, new Color(0, 0, 0, 0));
        image.drawImage(label, (WIDTH - label.getWidth()) / 2, (HEIGHT - label.getHeight()) / 2);

        setImage(image);
    }
}