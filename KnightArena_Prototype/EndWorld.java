import greenfoot.*;

/** Victory or game over screen. */
public class EndWorld extends World {
    public EndWorld(boolean victory, int score, int wavesCleared) {
        super(960, 600, 1);

        // Arena picture with a dark layer on top so the text is easy to read
        GreenfootImage bg = new GreenfootImage("arena_bg.png");
        bg.setColor(new Color(0, 0, 0, 170));
        bg.fillRect(0, 0, 960, 600);
        setBackground(bg);

        String title = victory ? "VICTORY!" : "GAME OVER";
        Color titleColor = victory ? new Color(245, 218, 142) : new Color(237, 128, 125);
        addObject(new TextLabel(title, 57, titleColor), 480, 155);

        addObject(new TextLabel("FINAL SCORE   " + score, 30, new Color(240, 236, 215)), 480, 263);
        addObject(new TextLabel("WAVES CLEARED   " + wavesCleared + " / " + WaveManager.TOTAL_WAVES, 22,
            new Color(181, 208, 211)), 480, 315);

        addObject(new MenuButton("again"), 480, 390);
        addObject(new MenuButton("menu"), 480, 465);
    }
}