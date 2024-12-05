package simulation.manager;

import simulation.entity.Entity;
import simulation.entity.EntityType;
import simulation.entity.creature.Creature;
import simulation.factory.EntityFactory;
import simulation.map.GameMap;
import simulation.map.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityManager {

    public EntityCollections createAndPlaceEntities(GameMap gameMap, int numberOfEntities) {
        List<Creature> creatures = new ArrayList<>();
        List<Entity> staticEntities = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < numberOfEntities; i++) {
            Position position;

            // Генерация случайной позиции с проверкой на занятость клетки
            do {
                int randomX = random.nextInt(0, gameMap.getWidth());
                int randomY = random.nextInt(0, gameMap.getHeight());
                position = new Position(randomX, randomY);
            } while (gameMap.getEntityAt(position) != null);

            // Создание сущности
            EntityType randomType = EntityType.values()[random.nextInt(EntityType.values().length)];
            Entity entity = EntityFactory.createEntity(randomType, position.getX(), position.getY());

            // Размещение на карте и добавление в соответствующий список
            gameMap.placeEntity(position, entity);

            if (entity instanceof Creature creature) {
                creatures.add(creature);
            } else {
                staticEntities.add(entity);
            }
        }

        return new EntityCollections(creatures, staticEntities);
    }

    // Класс для хранения списков существ и статических объектов.
    public static class EntityCollections {
        private final List<Creature> creatures;
        private final List<Entity> staticEntities;

        public EntityCollections(List<Creature> creatures, List<Entity> staticEntities) {
            this.creatures = creatures;
            this.staticEntities = staticEntities;
        }

        public List<Creature> getCreatures() {
            return creatures;
        }

        public List<Entity> getStaticEntities() {
            return staticEntities;
        }
    }
}