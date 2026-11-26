import greenfoot.*;

/** Victory or game over screen. */
public class EndWorld extends World {
    public EndWorld(boolean victory, int score, int wavesCleared) {
        super(960, 600, 1);
        setBackground("end_bg.png");
        
        String title = victory ? "VICTORY!" : "GAME OVER";
        Color titleColor = victory ? new Color(245, 218, 142) : new Color(237, 128, 125);
        addObject(new TextLabel(title, 57, titleColor), 480, 155);
        
        addObject(new TextLabel("FINAL SCORE   " + score, 30, new Color(240, 236, 215)), 480, 263);
        addObject(new TextLabel("WAVES CLEARED   " + wavesCleared + " / " + WaveManager.TOTAL_WAVES, 22,
            new Color(181, 208, 211)), 480, 315);
            
        addObject(new MenuButton("again"), 480, 375);
        addObject(new MenuButton("menu"), 480, 451);
    }
}
