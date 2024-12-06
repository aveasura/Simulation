package simulation.entity.creature.animal;

import simulation.entity.Entity;
import simulation.entity.creature.Creature;
import simulation.entity.resource.Grass;

public class Herbivore extends Creature implements Attackable {
    private static final char HERBIVORE_SYMBOL = 'H';

    public Herbivore(int hp, int speed, int x, int y) {
        super(hp, speed, x, y);
    }

    @Override
    protected Class<? extends Entity> getTargetClass() {
        return Grass.class;
    }

    @Override
    public char getEntitySymbol() {
        return HERBIVORE_SYMBOL;
    }

    @Override
    public void takeDamage(int damage) {
        hp = hp - damage;
        if (hp <= 0) {
            setExist(false);
        }
    }
}