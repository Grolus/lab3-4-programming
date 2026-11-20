public class Spider extends Entity {
    private float dirtyLegAmount;
    public boolean cleanLeg() {
        if (dirtyLegAmount >= 1) {
            cleanOneLeg();
            return true;
        } else if (dirtyLegAmount > 0) {
            cleanAllLegs();
            return false;
        } else { 
            return false; 
        }
    }
    public void cleanOneLeg() {
        dirtyLegAmount -= 1;
        Narrate.act("Уже " + (legsAmount - dirtyLegAmount) + " из ног паука " + getName() + " очищены! ");
    }
    public void cleanAllLegs() {
        Narrate.act("Последние " + dirtyLegAmount + " ноги паука " + getName() + " очищены! ");
        dirtyLegAmount = 0;
    }
    public Spider(String name, float legsAmount, float dirtyLegAmount) {
        super(name, legsAmount);
        this.dirtyLegAmount = dirtyLegAmount;
    }
    public Spider(String name, float legsAmount) {
        this(name, legsAmount, legsAmount); // legs are dirty by default
    }
    public void say(String phrase) {
        Narrate.act("Паук " + getName() + " прохрипел: \"" + phrase + "\". ");
    }
    public void go_walk() throws SleepSequenceError {
        if (isSleeping) { throw new SleepSequenceError(this); }
        this.dirtyLegAmount = this.legsAmount;
        Narrate.act("Паук " + getName() + " отправился на прогулку и испачкал все свои ноги");
    }
}
