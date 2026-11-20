import java.util.ArrayList;
import java.util.Random;

class He extends Entity {
    private boolean isHurrying;
    private ArrayList<Provolochka> provolochki;
    public void wait_minutes(int minutes) {
        if (isHurrying) {
            Narrate.act(getName() + " раздраженно подождал " + minutes + " минут, потому что сильно спешил. ");
        } else {
            Narrate.act(getName() + " подождал " + minutes + " минут. ");
        }
        if (!isHurrying ) {
            java.util.Random rand = new java.util.Random();
            if (rand.nextInt() % 10 >= 7) {
                isHurrying = true;
            }
        }
    }
    public void hurry() {
        isHurrying = true;
    }
    public void addProvolochka(int w, String desc) {
        provolochki.add(new Provolochka(w, desc));
        Narrate.act("Теперь " + getName() + " решил " + desc);
    }
    public void clear_provolochki() {
        Narrate.act(getName() + " решил избавиться от проволочек. ");
        while (!this.provolochki.isEmpty()) this.clear_random_provolochka();
    }
    protected void clear_random_provolochka() {
        Random rand = new Random();
        int chosen_i = Math.abs(rand.nextInt()) % this.provolochki.size();
        Provolochka chosen = this.provolochki.remove(chosen_i);
        Narrate.act(this.getName() + " не будет " + chosen.desc() + ". ");
    }
    public He(String name, float legsAmount, ArrayList<Provolochka> provolochki) {
        super(name, legsAmount);
        this.provolochki = provolochki;
    }
    public void say(String phrase) {
        Narrate.act("\"" + phrase + "\" -- сказал " + getName() + ". ");
    }
}