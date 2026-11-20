import greenfoot.*;

/** A simple enemy that walks toward the knight and slides around obstacles. */
public class Enemy extends Actor {
    private final boolean fast;
    private double exactX;
    private double exactY;
    private int hitPoints;
    private int stun = 0;
    private int flash = 0;
    private final GreenfootImage sprite;

    public Enemy(boolean fast) {
        this.fast = fast;
        hitPoints = fast ? 1 : 2;
        sprite = new GreenfootImage(fast ? "enemy_fast.png" : "enemy.png");
        setImage(sprite);
    }

    protected void addedToWorld(World world) {
        exactX = getX();
        exactY = getY();
    }

    public void act() {
        if (!(getWorld() instanceof ArenaWorld)) return;
        ArenaWorld arena = (ArenaWorld) getWorld();
        Knight knight = arena.getKnight();
        if (knight == null || knight.getWorld() == null) return;
        if (flash > 0) {
            flash--;
            getImage().setTransparency(flash % 4 < 2 ? 110 : 255);
        } else {
            getImage().setTransparency(255);
        }
        if (stun > 0) { stun--; return; }

        double dx = knight.getX() - exactX;
        double dy = knight.getY() - exactY;
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance < 31) {
            knight.hurt();
        }
        if (distance < 1) return;
        double speed = fast ? 2.15 : 1.45;
        double sx = dx / distance * speed;
        double sy = dy / distance * speed;
        boolean movedX = false;
        boolean movedY = false;
        if (arena.canOccupy(exactX + sx, exactY, 16)) {
            exactX += sx;
            movedX = true;
        }
        if (arena.canOccupy(exactX, exactY + sy, 16)) {
            exactY += sy;
            movedY = true;
        }
        // If an obstacle blocks the direct route, follow its side.
        if (!movedX && !movedY) {
            double side = ((getX() + getY()) % 2 == 0) ? 1 : -1;
            if (arena.canOccupy(exactX - sy * side, exactY + sx * side, 16)) {
                exactX -= sy * side;
                exactY += sx * side;
            } else if (arena.canOccupy(exactX + sy * side, exactY - sx * side, 16)) {
                exactX += sy * side;
                exactY -= sx * side;
            }
        }
        setLocation((int) Math.round(exactX), (int) Math.round(exactY));
    }

    public void takeHit() {
        if (!(getWorld() instanceof ArenaWorld)) return;
        hitPoints--;
        stun = 9;
        flash = 12;
        Greenfoot.playSound("hit.wav");
        if (hitPoints <= 0) {
            ((ArenaWorld) getWorld()).enemyDefeated(this);
        }
    }
}
