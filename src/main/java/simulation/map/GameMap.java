package simulation.map;

import simulation.entity.Entity;
import simulation.entity.creature.Creature;
import simulation.entity.creature.animal.Herbivore;
import simulation.entity.creature.animal.Predator;

import java.util.HashMap;
import java.util.Map;

public class GameMap {
    private Map<Position, Entity> map = new HashMap<>();
    private int width;
    private int height;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void spawn(Position position, Entity entity) {
        if (isOutOfBound(position)) throw new IllegalArgumentException("Invalid position: " + position);
        if (map.containsKey(position)) throw new IllegalStateException("Position already occupied: " + position);

        map.put(position, entity);
        entity.setPosition(position);
    }

    public void showMap() {
        for (Map.Entry<Position, Entity> map : map.entrySet()) {
            System.out.println(map);
        }
    }

    public void displayMap() {

        for (int y = 0; y < width ; y++) {
            for (int x = 0; x < height; x++) {
                Position position = new Position(x, y);
                if (map.containsKey(position)) {
                    Entity entity = map.get(position);
                    System.out.print(entity.getSymbol() + "  "); // символ сущности
                } else {
                    System.out.print("•  "); // Пустая клетка
                }
            }
            System.out.println();
        }
    }

    public Entity getEntityAt(Position position) {
        return map.get(position);
    }

    public boolean isOutOfBound(Position position) {
        return position.getX() < 0 || position.getX() >= width || position.getY() < 0 || position.getY() >= height;
    }

    public boolean isOccupied(Position position) {
        return map.containsKey(position);
    }

    public void move(Class<? extends Creature> creature, Position from, Position to) {
        if (isOutOfBound(to)) throw new IllegalArgumentException("Invalid position: " + to);
//        if (isOccupied(to)) throw new IllegalArgumentException("Position already occupied: " + to);
        if (!map.containsKey(from)) throw new IllegalArgumentException("No entity at position: " + from);

        Entity entity = map.get(from);
        if (isOccupied(to)) {
            if (entity instanceof Predator predator) {
                Entity target = map.get(to);
                if (target != null && target instanceof Herbivore) {
                    predator.attack((Herbivore) target);
                    if (((Herbivore) target).getHp() == 0) {
                        System.out.println("Predator killing Herbivore!!");
                        target.setExist(false);
                        map.remove(to);
                    } else {
                        System.out.println("Цель пока жива");
                    }
                }
            }

        } else {

            entity = map.remove(from); // Убираем сущность с текущей позиции
            map.put(to, entity);    // Перемещаем сущность на новую позицию
            entity.setPosition(to); // Обновляем позицию у сущности
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
