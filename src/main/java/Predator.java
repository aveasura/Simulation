
public class Predator extends Creature {
    protected int attackPower;

    public Predator(int speed, int hp, int attackPower, Position position) {
        super(speed, hp, position);
        this.attackPower = attackPower;
    }

    @Override
    void makeMove() {
        Herbivore target = findPray();
        if (target != null) {
            moveToPray(target);
            attack(target);
        }
    }

    private Herbivore findPray() {
        // todo Логика поиска ближайшей жертвы
        return null;
    }

    private void moveToPray(Herbivore target) {
        // todo логика движения к жертве
    }

    private void attack(Herbivore target) {
        // todo логика расчета урона и уничтожения травоядного
        // количество HP травоядного уменьшается на силу атаки хищника
        // Если значение HP жертвы опускается до 0, травоядное исчезает

    }
}
