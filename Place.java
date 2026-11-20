import java.util.ArrayList;

public abstract class Place {
    protected ArrayList<Entity> entitiesIn;
    abstract public String getName();
    public void enter(Entity e) { 
        entitiesIn.add(e); 
        Narrate.act(e.getName() + " вошел в " + getName());
    }
    public void leave(Entity e) { 
        entitiesIn.remove(e); 
        Narrate.act(e.getName() + " покидает " + getName());
    }
    public Place() { 
        entitiesIn = new ArrayList<Entity>();
    }
}
