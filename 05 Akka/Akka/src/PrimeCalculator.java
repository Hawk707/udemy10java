import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by HAWK-VAIO on 2/11/2017.
 */
public class PrimeCalculator {
    public void calculate(int start, int end) {
        int[] range = {start, end};
        ActorSystem actorSystem = ActorSystem.create("primeCalculator");

        ActorRef primeMaster = actorSystem.actorOf(Props.create(PrimeMaster.class, 20));

        primeMaster.tell(range, primeMaster);
    }

    public static void main(String[] args){
        PrimeCalculator primeCalculator = new PrimeCalculator();
        primeCalculator.calculate(1, 10000000);
    }
}
