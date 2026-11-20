class SleepSequenceError extends Exception {
    public SleepSequenceError(Entity o) {
        super(compileMessage(o));
    }
    static private String compileMessage(Entity o) {
        if (o.isSleeping()) {
            return o.getName() + " уже спит. ";
        } else {
            return o.getName() + " уже бодрствует. ";
        }
    }
}
