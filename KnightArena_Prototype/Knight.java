import greenfoot.*;

/** Player movement, facing, attacks, health and temporary invulnerability. */
public class Knight extends Actor {
    private double exactX;
    private double exactY;
    private double faceX = 0;
    private double faceY = -1;
    private int health = 5;
    private int attackCooldown = 0;
    private int invulnerable = 0;
    private final GreenfootImage normalImage;

    public Knight() {
        normalImage = new GreenfootImage("knight.png");
        setImage(normalImage);
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
        
        if (attackCooldown > 0) {
            attackCooldown--;    
        }
        if (invulnerable > 0) {
            invulnerable--;    
        }
        
        moveKnight(arena);
        if (Greenfoot.isKeyDown("space") && attackCooldown == 0) {
            swordAttack(arena);
        }
        
        // Flickering
        getImage().setTransparency(invulnerable > 0 && invulnerable % 8 < 4 ? 130 : 255);
    }

    private void moveKnight(ArenaWorld arena) {
        int dx = 0;
        int dy = 0;
        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) dx--;
        if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) dx++;
        if (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up")) dy--;
        if (Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down")) dy++;
        if (dx == 0 && dy == 0) return;

        double length = Math.sqrt(dx * dx + dy * dy);
        double ux = dx / length;
        double uy = dy / length;
        faceX = ux;
        faceY = uy;
        setRotation((int) Math.round(Math.toDegrees(Math.atan2(faceY, faceX))) + 90);

        double step = 3.2;
        if (arena.canOccupy(exactX + ux * step, exactY, 18)) exactX += ux * step;
        if (arena.canOccupy(exactX, exactY + uy * step, 18)) exactY += uy * step;
        setLocation((int) Math.round(exactX), (int) Math.round(exactY));
    }

    private void swordAttack(ArenaWorld arena) {
        attackCooldown = 28; // ~half a second
        int angle = (int) Math.round(Math.toDegrees(Math.atan2(faceY, faceX)));
        arena.addObject(new Slash(angle), getX(), getY());
        Greenfoot.playSound("swing.wav");
        arena.hitEnemies(getX(), getY(), faceX, faceY);
    }

    /** Returns false while invulnerable so touch cannot remove health instantly. */
    public boolean hurt() {
        if (invulnerable > 0 || health <= 0) return false;
        health--;
        invulnerable = 60;
        Greenfoot.playSound("hurt.wav");
        if (health <= 0 && getWorld() instanceof ArenaWorld) {
            ((ArenaWorld) getWorld()).finish(false);
        }
        return true;
    }

    public int getHealth() { return health; }
}
