import java.util.ArrayList;
import java.util.Random;;

/*Едва небо просветлело на востоке, он без лишних проволочек разбудил Семь с половиной. 
Но ему еще пришлось подождать, пока Семь с половиной приведет себя в порядок. 
Старый бездельник аккуратно почистил все свои семь с половиной ног
и только после этого заявил, что готов двинуться дальше. */

public class Main {
    public static void main(String[] args) {

        Spider sevenAndAHalf = new Spider(
            "Семь с половиной", 
            7.5f
        );
        ArrayList<Provolochka> himProvolochki = new ArrayList<Provolochka>();

        himProvolochki.add(new Provolochka(1, "ждать пока паук сам проснется"));
        himProvolochki.add(new Provolochka(10, "идти одному, без паука"));
        He he = new He(
            "Он", 
            2,
            himProvolochki
        );

        Random rand = new Random();
        
        House house;
        if (rand.nextBoolean()) {
            house = new House(he);
        } else {
            house = new House(sevenAndAHalf);
        }

        // случайное время суток
        DayTime daytime = DayTime.values()[Math.abs(rand.nextInt()) % DayTime.values().length];

        run_story(he, sevenAndAHalf, daytime, house);
    }

    static void run_story(He he, Spider spider, DayTime daytime, House place) {
        daytime.introduce();
        //  Он приходит к Пауку чтобы разбудить его и повести с собой
        switch (daytime) {
            case DAY: // днем паук спит, а Он понимает и уходит
                try {
                    spider.fallAsleep();
                }
                catch (SleepSequenceError e) {
                    System.out.println(e.getMessage());
                }
                place.enter(he);

                if (place.getOwner() == he) {
                    try { 
                        he.wakeSomebodyUp(spider); 
                        he.say("Иди у себя спи");
                        place.leave(spider);
                    } catch (CantBeAwakenError e) {  
                        he.say("вот это наглость!");
                    }
                } else {
                    he.say("*шепетом* ой, спит паук");
                    place.leave(he);
                }
                break;
            case NIGHT: // Он спит, а паук гуляет, а потом ложится спать
                try {
                    he.fallAsleep();
                }
                catch (SleepSequenceError e) {
                    System.out.println(e.getMessage());
                }

                try {
                    spider.go_walk();
                    spider.fallAsleep();
                } catch (SleepSequenceError e) {
                    System.out.println(e.getMessage());
                }
                break;
            case SUNSET: // паук просыпается
                try { 
                    spider.wakeUp(); 
                } catch (SleepSequenceError e) { 
                    Narrate.describe_env("Обычно в это время пауки просыпаются, но в этот раз все было иначе"); 
                }
                break;
            case SUNRISING:
                place.enter(he);
                he.clear_provolochki();
                try {
                    he.wakeSomebodyUp(spider);
                    do {
                        he.wait_minutes(1);
                    } while (spider.cleanLeg());
                    spider.say("Можем отправляться");
                    he.say("ура");
                }
                catch (CantBeAwakenError e) {
                    he.addProvolochka(100, "обижаться на " + spider.getName());
                    place.leave(he);
                }
                
                return;
        }
    }
}

