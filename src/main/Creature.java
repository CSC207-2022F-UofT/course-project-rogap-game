public  abstract class Creature {
    public abstract void Creature();

    public abstract void setMovableBehavior();

    public abstract void setHealth(); // Set the Initial Creature Health

    public abstract void getHealth(); // Get the creature's current Health

    public abstract void reduceHealth();

    public abstract void die();

    abstract UserRegisterResponseModel create();
}
