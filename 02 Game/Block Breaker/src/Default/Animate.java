package Default;

/**
 * Created by HAWK-VAIO on 1/24/2017.
 */
public class Animate implements Runnable {
    BlockBreakerPanel bp;

    Animate(BlockBreakerPanel b){
        bp = b;

    }
    public void run(){
        while(true){
            bp.update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
