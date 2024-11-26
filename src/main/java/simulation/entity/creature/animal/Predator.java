package simulation.entity.creature.animal;

import simulation.entity.Entity;
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

    public void searchTarget(GameMap gameMap) {
        Position currentPosition = this.getPosition();
        BFS bfs = new BFS(gameMap);
        List<Position> target = bfs.bfs(currentPosition, Herbivore.class);

        if (target != null) {
            System.out.println("Цель \"Herbivore\" найдена: " + target);
        } else System.out.println("Цель не найдена!");
    }

    @Override
    protected void makeMove() {
        // todo логика хода
        System.out.println("predator move");
    }

    public void attack() {
        // todo логика атаки
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
