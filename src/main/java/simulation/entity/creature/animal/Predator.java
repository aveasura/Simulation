package simulation.entity.creature.animal;

import simulation.entity.Entity;
import simulation.entity.creature.Creature;
import simulation.map.GameMap;
import simulation.map.Position;
import simulation.map.pathfinding.BFS;

public class Predator extends Creature {
    private final int attackPower;

    public Predator(int hp, int speed, int attackPower, Position position) {
        super(hp, speed, position);
        this.attackPower = attackPower;
    }

    @Override
    protected void makeMove() {
        System.out.println("predator move");
    }

    public void searchTarget(GameMap gameMap) {
        Position currentPosition = this.getPosition();
        BFS bfs = new BFS(gameMap);
        Entity target = bfs.bfs(currentPosition, Herbivore.class);

        if (target != null) {
            System.out.println("Цель найдена: " + target.getPosition());
        } else System.out.println("Цель не найдена!");

    }

    public void attack() {
        System.out.println("predator attack");
    }

    @Override
    public String toString() {
        return "Predator{" +
                "attackPower=" + attackPower +
                '}';
    }

    @Override
    public char getSymbol() {
        return 'P';
    }
}
