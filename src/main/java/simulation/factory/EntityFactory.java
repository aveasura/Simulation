package simulation.factory;

import simulation.entity.EntityType;
import simulation.entity.Entity;

public class EntityFactory {
    public static Entity createEntity(EntityType type, int x, int y) {

        return switch (type) {

            case PREDATOR -> PredatorFactory.createPredator(x, y);
            case HERBIVORE -> HerbivoreFactory.createHerbivore(x, y);
            case MOUNTAIN -> MountainFactory.createRock(x, y);
            case GRASS -> GrassFactory.createGrass(x, y);
            case TREE -> TreeFactory.createTree(x, y);

            default -> throw new IllegalArgumentException("Unknown entity type: " + type);
        };
    }
}