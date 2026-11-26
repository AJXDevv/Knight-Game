import greenfoot.*;

/** Short instructions for the prototype. */
public class HelpWorld extends World {
    public HelpWorld() {
        super(960, 600, 1);

        // Arena picture with a dark layer on top so the text is easy to read
        GreenfootImage bg = new GreenfootImage("arena_bg.png");
        bg.setColor(new Color(0, 0, 0, 170));
        bg.fillRect(0, 0, 960, 600);
        setBackground(bg);

        addObject(new TextLabel("HOW TO PLAY", 60, new Color(247, 230, 195)), 480, 150);

        // One line of text per row. Add or remove lines here.
        String[] lines = {
            "MOVE:  WASD or arrow keys to move.",
            "ATTACK:  Press SPACE to swing your sword.",
            "SURVIVE:  Defeat every enemy to reach the next wave.",
            "SCORE:  Each enemy is worth 100 points. Survive all " + WaveManager.TOTAL_WAVES + " waves!"
        };
        for (int i = 0; i < lines.length; i++) {
            addObject(new TextLabel(lines[i], 24, Color.WHITE), 480, 250 + i * 60);
        }

        addObject(new MenuButton("back"), 480, 500);
    }
}