public class House extends Place {
    private Entity owner;
    public House(Entity owner) {
        this.owner = owner;
        //enter(owner);
    }
    public Entity getOwner() { return owner; }
    public String getName() { return "дом, принадлежащий " + owner.getName(); }
}