package simulation.entity.creature.animal;

import simulation.controller.MovementController;
import simulation.entity.creature.Creature;
import simulation.entity.resource.Grass;
import simulation.map.GameMap;
import simulation.map.Position;
import simulation.map.pathfinding.BFS;

import java.util.Collections;
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

        if (!this.isAlive() || path == null || path.isEmpty()) {
            return Collections.emptyList();
        }

        return path;
    }

    public void eatGrass(Grass grass) {
        grass.setExist(false);
        System.out.println(this.getSymbol() + ": eat grass");
    }

    @Override
    public char getSymbol() {
        return 'H';
    }
}
