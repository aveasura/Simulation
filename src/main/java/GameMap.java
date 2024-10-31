import java.util.HashMap;
import java.util.Map;

// Карта, содержит в себе коллекцию для хранения существ и их расположения.
public class GameMap {
    Map<Creature, Position> map = new HashMap<>();

    // добавление на карту
    void spawn() {
        Position herbivorePosition = new Position(0, 0);
        Creature herbivore = new Herbivore(1, 1, herbivorePosition);

        Position predatorPosition = new Position(2, 2);
        Creature predator = new Predator(1, 1, 1, predatorPosition);

        map.put(herbivore, herbivorePosition);
        map.put(predator, predatorPosition);
    }
}
