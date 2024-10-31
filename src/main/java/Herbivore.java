
public class Herbivore extends Creature {

    public Herbivore(int speed, int hp, Position position) {
        super(speed, hp, position);
    }

    @Override
    void makeMove() {
        Grass target = findGrass();

        // todo может потратить свой ход на движение в сторону травы, либо на её поглощение
        if (target != null) {
            moveToGrass(target);
            eatGrass(target);
        }

    }

    private Grass findGrass() {
        // todo Стремятся найти ресурс (траву) [алгоритм поиска ближайшего растения]
        return null;
    }

    private void moveToGrass(Grass grass) {
        // todo перемещение к растению
    }

    private void eatGrass(Grass grass) {
        // todo уничтожение растения
    }
}
