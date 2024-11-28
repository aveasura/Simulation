package simulation.entity.creature.animal;

import simulation.controller.MovementController;
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
    private List<Position> path;

    public Predator(int hp, int speed, int attackPower, Position position) {
        super(hp, speed, position);
        this.attackPower = attackPower;
    }

    public void searchTarget(MovementController movementController) {
        Position currentPosition = this.getPosition();
        GameMap map = movementController.getGameMap();

        BFS bfs = new BFS(map);
        path = bfs.bfs(currentPosition, Herbivore.class);

        if (path != null) {
            System.out.println("Predator: Цель \"Herbivore\" найдена. Маршрут: " + path);

            this.makeMove(movementController);

        } else {
            System.out.println("Цель не найдена!");
        }
    }

    @Override
    protected void makeMove(MovementController movementController) {

        if (path == null || path.isEmpty()) {
            System.out.println("Нет пути для перемещения.");
            return;
        }

        int predatorSpeed = this.getSpeed();
        int nextPositionIndex = Math.min(predatorSpeed, path.size() - 1);
        Position nextPosition = path.get(nextPositionIndex);

        System.out.println("Predator перемещается на: " + nextPosition);

        movementController.moveEntity(this, nextPosition);
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
