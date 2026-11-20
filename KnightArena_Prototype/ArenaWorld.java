import greenfoot.*;
import java.util.List;

/** Playable arena prototype with five short waves. */
public class ArenaWorld extends World {
    private final Knight knight;
    private final Hud hud;
    private int wave = 0;
    private int score = 0;
    private int enemiesRemainingToSpawn = 0;
    private int spawnTimer = 0;
    private int betweenWaves = 65;
    private int messageTimer = 0;
    private TextLabel waveLabel;
    private boolean finished = false;

    public ArenaWorld() {
        super(960, 600, 1);
        setBackground("arena_bg.png");
        setPaintOrder(Hud.class, TextLabel.class, Slash.class,
                      Knight.class, Enemy.class, Obstacle.class);
        // These are the same three obstacle types in the design proposal.
        addObject(new Obstacle("pillar.png", 23, 23), 250, 200);
        addObject(new Obstacle("pillar.png", 23, 23), 710, 402);
        addObject(new Obstacle("crate.png", 23, 20), 738, 216);
        addObject(new Obstacle("crate.png", 23, 20), 228, 427);
        addObject(new Obstacle("barrel.png", 19, 18), 461, 177);
        addObject(new Obstacle("barrel.png", 19, 18), 577, 464);

        knight = new Knight();
        addObject(knight, 480, 319);
        hud = new Hud();
        addObject(hud, 480, 35);
        startNextWave();
    }

    public void act() {
        if (finished) return;
        if (messageTimer > 0) {
            messageTimer--;
            if (messageTimer == 0 && waveLabel != null) {
                removeObject(waveLabel);
                waveLabel = null;
            }
        }
        if (enemiesRemainingToSpawn > 0) {
            if (--spawnTimer <= 0) {
                spawnEnemy();
                enemiesRemainingToSpawn--;
                spawnTimer = 25;
            }
            return;
        }
        // Once all enemies in the current wave are gone, start a short countdown.
        List<Enemy> enemies = getObjects(Enemy.class);
        if (!enemies.isEmpty()) {
            betweenWaves = 120;
        } else if (--betweenWaves <= 0) {
            if (wave >= 5) {
                finish(true);
            } else {
                startNextWave();
            }
        }
    }

    private void startNextWave() {
        wave++;
        enemiesRemainingToSpawn = 2 + wave * 2; // 4 / 6 / 8 / 10 / 12
        spawnTimer = 65;
        betweenWaves = 120;
        if (waveLabel != null) removeObject(waveLabel);
        waveLabel = new TextLabel("WAVE " + wave, 38,
                 new Color(250, 223, 164));
        addObject(waveLabel, 480, 121);
        messageTimer = 92;
    }

    private void spawnEnemy() {
        int x = 100;
        int y = 110;
        for (int tries = 0; tries < 25; tries++) {
            int side = Greenfoot.getRandomNumber(4);
            if (side == 0) { x = 105 + Greenfoot.getRandomNumber(750); y = 118; }
            if (side == 1) { x = 105 + Greenfoot.getRandomNumber(750); y = 524; }
            if (side == 2) { x = 100; y = 122 + Greenfoot.getRandomNumber(391); }
            if (side == 3) { x = 858; y = 122 + Greenfoot.getRandomNumber(391); }
            int dx = x - knight.getX();
            int dy = y - knight.getY();
            if (dx * dx + dy * dy > 175 * 175 && canOccupy(x, y, 18)) break;
        }
        boolean fast = wave >= 3 && Greenfoot.getRandomNumber(100) < 25 + 5 * (wave - 3);
        addObject(new Enemy(fast), x, y);
    }

    /** Shared collision rule for both the knight and its enemies. */
    public boolean canOccupy(double x, double y, int radius) {
        if (x < 75 + radius || x > 885 - radius
                || y < 104 + radius || y > 530 - radius) return false;
        for (Obstacle obstacle : getObjects(Obstacle.class)) {
            if (Math.abs(x - obstacle.getX()) < obstacle.getHalfWidth() + radius
                && Math.abs(y - obstacle.getY()) < obstacle.getHalfHeight() + radius) {
                return false;
            }
        }
        return true;
    }

    /** Short sword reach with a forward-facing hit area. */
    public void hitEnemies(int x, int y, double faceX, double faceY) {
        for (Enemy enemy : getObjects(Enemy.class)) {
            double dx = enemy.getX() - x;
            double dy = enemy.getY() - y;
            double dist = Math.sqrt(dx * dx + dy * dy);
            if (dist <= 83 && dist > 1 && (dx * faceX + dy * faceY) / dist >= 0.15) {
                enemy.takeHit();
            }
        }
    }

    public void enemyDefeated(Enemy enemy) {
        score += 100;
        removeObject(enemy);
    }

    public void finish(boolean victory) {
        if (finished) return;
        finished = true;
        if (victory) Greenfoot.playSound("win.wav");
        int cleared = victory ? 5 : wave - 1;
        Greenfoot.setWorld(new EndWorld(victory, score, Math.max(0, cleared)));
    }

    public Knight getKnight() { return knight; }
    public int getScore() { return score; }
    public int getWave() { return wave; }
}
