
interface CanSleep {
    public void fallAsleep() throws SleepSequenceError;
    public void wakeUp() throws SleepSequenceError;
    public boolean isSleeping();
}
