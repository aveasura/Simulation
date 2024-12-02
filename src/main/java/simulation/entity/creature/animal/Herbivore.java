package simulation.entity.creature.animal;

import simulation.entity.Entity;
import simulation.entity.creature.Creature;
import simulation.entity.resource.Grass;

public class Herbivore extends Creature implements Attackable {

    public Herbivore(int hp, int speed, int x, int y) {
        super(hp, speed, x, y);
    }

    @Override
    protected Class<? extends Entity> getTargetClass() {
        return Grass.class;
    }

    @Override
    public char getSymbol() {
        return 'H';
    }

    @Override
    public void takeDamage(int damage) {
        setHp(getHp() - damage);
        if (getHp() <= 0) {
            setAlive(false);
        }
    }
}
