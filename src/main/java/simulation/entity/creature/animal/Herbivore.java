package simulation.entity.creature.animal;

import simulation.entity.Entity;
import simulation.entity.creature.Creature;
import simulation.entity.resource.Grass;
import simulation.map.GameMap;
import simulation.map.Position;
import simulation.map.pathfinding.BFS;

// Стремятся найти ресурс (траву), может потратить свой ход на движение в сторону травы, либо на её поглощение
public class Herbivore extends Creature {

    public Herbivore(int hp, int speed, Position position) {
        super(hp, speed, position);
    }

    @Override
    public void makeMove() {
        // todo логика хода
        System.out.println("herbivore move");
    }

    @Override
    public char getSymbol() {
        return 'H';
    }

    public void searchTarget(GameMap gameMap) {
        Position currentPosition = this.getPosition();
        BFS bfs = new BFS(gameMap);

        Entity target = bfs.bfs(currentPosition, Grass.class);
        if (target != null) System.out.println("Цель найдена: " + target.getPosition());
        else System.out.println("Цель не найдена!");
    }
}
