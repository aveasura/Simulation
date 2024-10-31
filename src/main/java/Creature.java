
abstract public class Creature extends Entity {
    protected int speed;
    protected int hp;

    public Creature(int speed, int hp, Position position) {
        super(position);
        this.speed = speed;
        this.hp = hp;
    }

    abstract void makeMove();
}
