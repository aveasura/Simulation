
public abstract class Entity {
    protected boolean isExist;
    protected Position position;

    public Entity(Position position){
        this.isExist = true;
        this.position = position;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }
}