import greenfoot.*;

/** Always-visible health, wave and score. */
public class Hud extends Actor {
    private int oldHealth = -1;
    private int oldWave = -1;
    private int oldScore = -1;

    public Hud() {
        setImage(new GreenfootImage(960, 70));
    }

    public void act() {
        if (!(getWorld() instanceof ArenaWorld)) {
            return;    
        }
        ArenaWorld world = (ArenaWorld) getWorld();
        
        int health = world.getKnight().getHealth();
        int wave = world.getWave();
        int score = world.getScore();
        if (oldHealth == health && oldWave == wave && oldScore == score) {
            return;    
        }
        oldHealth = health;
        oldWave = wave;
        oldScore = score;

        GreenfootImage image = new GreenfootImage(960, 70);
        Color ivory = new Color(247, 230, 195);
        Color crimson = new Color(245, 153, 146);
        
        image.drawImage(new GreenfootImage("HEALTH", 18, ivory, new Color(0, 0, 0, 0)), 29, 25);
        String hearts = "";
        for (int i = 0; i < 5; i++) {
            hearts += i < health ? "\u2665 " : "\u2661 ";    
        }
        image.drawImage(new GreenfootImage(hearts, 23, crimson, new Color(0, 0, 0, 0)), 124, 21);
        
        image.drawImage(new GreenfootImage("WAVE  " + wave + " / 5", 23, ivory, new Color(0, 0, 0, 0)), 394, 18);
        image.drawImage(new GreenfootImage("SCORE  " + score, 22, ivory, new Color(0, 0, 0, 0)), 724, 20);
        
        setImage(image);
    }
}
