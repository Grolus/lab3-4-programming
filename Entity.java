
public abstract class Entity implements CanSleep{
    protected  String name;
    protected  float legsAmount;
    protected  boolean isSleeping;
    public abstract void say(String phrase);
    public String getName() { 
        return name; 
    }
    public void wakeUp() throws SleepSequenceError {
        if (!isSleeping) {
            throw new SleepSequenceError(this);
        }
        Narrate.act(getName() + " проснулся. ");
        this.isSleeping = false;
    }
    public void fallAsleep() throws SleepSequenceError {
        if (isSleeping) {
            throw new SleepSequenceError(this);
        }
        Narrate.act(getName() + " засыпает. ");
        this.isSleeping = true;
    }
    public void wakeSomebodyUp(Entity e) throws CantBeAwakenError {
        Narrate.act(getName() + " пытается разбудить " + e.getName() + ". ");
        if ((new java.util.Random()).nextBoolean()) { 
            try {
                e.wakeUp();
            } catch (SleepSequenceError error) { } // если уже не спит, но не проблема
        } else {
            Narrate.act("Безуспешно. " + e.getName() + " спит слишком крепко. ");
            throw new CantBeAwakenError();
        }
    }
    public Entity(String n, float l) {
        name = n;
        legsAmount = l;
        isSleeping = false;
    }
    public boolean isSleeping() { 
        return this.isSleeping; 
    }
}
