package simulation.entity.creature.animal;

import simulation.controller.MovementController;
import simulation.entity.creature.Creature;
import simulation.entity.resource.Grass;
import simulation.map.GameMap;
import simulation.map.Position;
import simulation.map.pathfinding.BFS;

import java.util.List;

public class Herbivore extends Creature {
    public Herbivore(int hp, int speed, Position position) {
        super(hp, speed, position);
    }

    public List<Position> searchTarget(MovementController movementController) {
        Position currentPosition = this.getPosition();
        GameMap map = movementController.getGameMap();
        BFS bfs = new BFS(map);

        List<Position> path = bfs.bfs(currentPosition, Grass.class);

        if (!this.isAlive()) {
            System.out.println("Травоядное мертво: " + getPosition());
            return null;
        }

        if (path != null && !path.isEmpty()) {
            System.out.println("Herbivore: Цель \"Grass\" найдена. Маршрут: " + path);
            return path;

        } else {
            System.out.println("Цель не найдена!");
            return null;
        }
    }

    public boolean eatGrass(Grass grass) {
        grass.setExist(false);
        return grass.isExist();
    }

    public boolean isAlive() {
        return getHp() > 0;
    }

    @Override
    public char getSymbol() {
        return 'H';
    }
}
