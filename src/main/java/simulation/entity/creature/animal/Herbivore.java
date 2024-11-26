package simulation.entity.creature.animal;

import simulation.entity.creature.Creature;
import simulation.entity.resource.Grass;
import simulation.map.GameMap;
import simulation.map.Position;
import simulation.map.pathfinding.BFS;

import java.util.List;

// Стремятся найти ресурс (траву), может потратить свой ход на движение в сторону травы, либо на её поглощение
public class Herbivore extends Creature {

    public Herbivore(int hp, int speed, Position position) {
        super(hp, speed, position);
    }

    public void searchTarget(GameMap gameMap) {

        if (!this.isExist()) {
            System.out.println("Травоядное мертво: " + getPosition());
            return;
        }

        Position currentPosition = this.getPosition();
        BFS bfs = new BFS(gameMap);

        List<Position> target = bfs.bfs(currentPosition, Grass.class);
        if (target != null) System.out.println("Herbivore: Цель \"Grass\" найдена. Маршрут: " + target);
        else System.out.println("Цель не найдена!");
    }

    @Override
    protected void makeMove(Position currentPosition, GameMap gameMap) {
        System.out.println("herbivore move");
    }

    @Override
    public char getSymbol() {
        return 'H';
    }
}
