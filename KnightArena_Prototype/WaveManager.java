import greenfoot.*;

/**
 * Holds the rules for the waves: how many waves there are,
 * how many enemies each wave has, and which enemy to create.
 * It keeps no state. ArenaWorld still tracks the current wave number.
 */
public class WaveManager {
    public static final int TOTAL_WAVES = 5;

    /** How many enemies spawn in the given wave (4 / 6 / 8 / 10 / 12). */
    public int getEnemyCount(int wave) {
        return 2 + (wave * 2);
    }

    /** Creates the next enemy for the given wave. */
    public Enemy createEnemy(int wave) {
        if (Greenfoot.getRandomNumber(100) < getFastChance(wave)) {
            return new FastEnemy();
        }
        return new BasicEnemy();
    }

    /** Chance (in percent) that an enemy is fast: 0 / 0 / 25 / 30 / 35. */
    private int getFastChance(int wave) {
        if (wave < 3) {
            return 0;
        }
        return 25 + (5 * (wave - 3));
    }
}