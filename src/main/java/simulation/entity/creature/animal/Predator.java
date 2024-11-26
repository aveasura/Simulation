package simulation.entity.creature.animal;

import simulation.entity.creature.Creature;
import simulation.map.GameMap;
import simulation.map.Position;
import simulation.map.pathfinding.BFS;

import java.util.List;

// На что может потратить ход хищник:
// Переместиться (чтобы приблизиться к жертве - травоядному)
// Атаковать травоядное. При этом количество HP травоядного уменьшается на силу атаки хищника.
//                       Если значение HP жертвы опускается до 0, травоядное исчезает

public class Predator extends Creature {
    private final int attackPower;

    public Predator(int hp, int speed, int attackPower, Position position) {
        super(hp, speed, position);
        this.attackPower = attackPower;
    }

    List<Position> path;

    public void searchTarget(GameMap gameMap) {
        Position currentPosition = this.getPosition();
        BFS bfs = new BFS(gameMap);
        path = bfs.bfs(currentPosition, Herbivore.class);

        if (path != null) {
            System.out.println("Predator: Цель \"Herbivore\" найдена. Маршрут: " + path);
            makeMove(currentPosition, gameMap);
        } else System.out.println("Цель не найдена!");
    }

    @Override
    protected void makeMove(Position currentPosition, GameMap gameMap) {
        int predatorSpeed = this.getSpeed();
        System.out.println("predator move: " + path.get(predatorSpeed));
        gameMap.move(Predator.class, currentPosition, path.get(predatorSpeed));
    }

    public void attack(Herbivore herbivore) {
        System.out.println("Predator attack!!! Hp herbivore decreased by: " + attackPower);
        herbivore.setHp(herbivore.getHp() - attackPower);
        System.out.println("Herbivore hp: " + herbivore.getHp());
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
