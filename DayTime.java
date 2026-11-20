public enum DayTime {
    DAY, SUNSET, NIGHT, SUNRISING;
    public DayTime getNext() {
        DayTime[] dayTimes = DayTime.values();
        return dayTimes[(this.ordinal() + 1) % dayTimes.length];
    }

    public void introduce() {
        switch (this) { 
            case DAY: 
                Narrate.describe_env("Был солнечный день.");
                break;
            case NIGHT:
                Narrate.describe_env("Темная ночь окутала долину.");
                break;
            case SUNRISING:
                Narrate.describe_env("Небо едва посветлело на востоке.");
                break;
            case SUNSET:
                Narrate.describe_env("Солнце уже садилось.");
        }
    }
}
