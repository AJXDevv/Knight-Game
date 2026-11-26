import greenfoot.*;

/**
 * Base class for every enemy. It handles everything shared:
 * flashing, stun, contact damage, chasing the knight and taking hits.
 * Subclasses only choose their image and stats.
 */
public abstract class Enemy extends Actor {
    private static final int CONTACT_RANGE = 31;
    private static final int COLLISION_RADIUS = 16;

    // Stats (set once by the subclass through the constructor)
    protected final double speed;
    protected final int points;
    protected int hitPoints;

    // Exact position, because Greenfoot positions are whole numbers
    protected double exactX;
    protected double exactY;

    private int stun = 0;
    private int flash = 0;

    public Enemy(String imageName, int hitPoints, double speed, int points) {
        this.hitPoints = hitPoints;
        this.speed = speed;
        this.points = points;
        setImage(new GreenfootImage(imageName));
    }

    protected void addedToWorld(World world) {
        exactX = getX();
        exactY = getY();
    }

    public void act() {
        if (!(getWorld() instanceof ArenaWorld)) {
            return;
        }
        ArenaWorld arena = (ArenaWorld) getWorld();
        Knight knight = arena.getKnight();
        if (knight == null || knight.getWorld() == null) {
            return;
        }

        updateFlash();

        if (stun > 0) {
            stun--;
            return;
        }

        if (distanceTo(knight) < CONTACT_RANGE) {
            knight.hurt();
        }
        move(arena, knight);
    }

    /** Default behavior: walk straight at the knight and slide around obstacles. */
    protected void move(ArenaWorld arena, Knight knight) {
        double dx = knight.getX() - exactX;
        double dy = knight.getY() - exactY;
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance < 1) {
            return;
        }

        double sx = dx / distance * speed;
        double sy = dy / distance * speed;
        boolean movedX = false;
        boolean movedY = false;

        if (arena.canOccupy(exactX + sx, exactY, COLLISION_RADIUS)) {
            exactX += sx;
            movedX = true;
        }
        if (arena.canOccupy(exactX, exactY + sy, COLLISION_RADIUS)) {
            exactY += sy;
            movedY = true;
        }

        // If an obstacle blocks the direct route, follow its side.
        if (!movedX && !movedY) {
            double side = ((getX() + getY()) % 2 == 0) ? 1 : -1;
            if (arena.canOccupy(exactX - sy * side, exactY + sx * side, COLLISION_RADIUS)) {
                exactX -= sy * side;
                exactY += sx * side;
            } else if (arena.canOccupy(exactX + sy * side, exactY - sx * side, COLLISION_RADIUS)) {
                exactX += sy * side;
                exactY -= sx * side;
            }
        }
        setLocation((int) Math.round(exactX), (int) Math.round(exactY));
    }

    public void takeHit(int damage) {
        if (!(getWorld() instanceof ArenaWorld)) {
            return;
        }
        hitPoints -= damage;
        stun = 9;
        flash = 12;
        Greenfoot.playSound("hit.wav");
        if (hitPoints <= 0) {
            ((ArenaWorld) getWorld()).enemyDefeated(this);
        }
    }

    public int getPoints() {
        return points;
    }

    private double distanceTo(Knight knight) {
        double dx = knight.getX() - exactX;
        double dy = knight.getY() - exactY;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private void updateFlash() {
        if (flash > 0) {
            flash--;
            getImage().setTransparency(flash % 4 < 2 ? 110 : 255);
        } else {
            getImage().setTransparency(255);
        }
    }
}