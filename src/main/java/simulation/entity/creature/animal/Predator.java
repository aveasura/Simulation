package simulation.entity.creature.animal;

import simulation.controller.MovementController;
import simulation.entity.creature.Creature;
import simulation.map.GameMap;
import simulation.map.Position;
import simulation.map.pathfinding.BFS;

import java.util.List;

public class Predator extends Creature {
    private final int attackPower;

    public Predator(int hp, int speed, int attackPower, Position position) {
        super(hp, speed, position);
        this.attackPower = attackPower;
    }

    public List<Position> searchTarget(MovementController movementController) {
        Position currentPosition = this.getPosition();
        GameMap map = movementController.getGameMap();
        BFS bfs = new BFS(map);
        List<Position> path = bfs.bfs(currentPosition, Herbivore.class);

        if (path != null) {
            System.out.println("Predator: Цель \"Herbivore\" найдена. Маршрут: " + path);
            return path;

        } else {
            System.out.println("Цель не найдена!");
            return null;
        }
    }

    public int attack(Herbivore herbivore) {
        herbivore.setHp(herbivore.getHp() - this.attackPower);
        System.out.println("HP" + herbivore.getHp());
        return herbivore.getHp();
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
