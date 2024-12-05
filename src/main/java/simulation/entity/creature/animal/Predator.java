package simulation.entity.creature.animal;

import simulation.entity.Entity;
import simulation.entity.creature.Creature;

public class Predator extends Creature {
    private static final char PREDATOR_SYMBOL = 'P';
    protected final int attackPower;

    public Predator(int hp, int speed, int attackPower, int x, int y) {
        super(hp, speed, x, y);
        this.attackPower = attackPower;
    }

    public void attack(Attackable target) {
        target.takeDamage(this.attackPower);
    }

    @Override
    protected Class<? extends Entity> getTargetClass() {
        return Herbivore.class;
    }

    @Override
    public String toString() {
        return "Predator{" +
                "attackPower=" + attackPower +
                '}';
    }

    @Override
    public char getEntitySymbol() {
        return PREDATOR_SYMBOL;
    }
}