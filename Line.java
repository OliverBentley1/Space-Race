import java.util.TimerTask;
import java.util.Timer;

public class Line {
    public static int y = 0;
    public Line () {
        Frame.stop = false;
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
    	    @Override
    	    public void run() {
                y++;
                if (y >= 500) {
                    Frame.stop = true;
                    if (Main.p1Score > Main.p2Score) {
                        Panel.mainLabel.setText("Left Ship Wins");
                    } else if (Main.p2Score > Main.p1Score) {
                        Panel.mainLabel.setText("Right Ship Wins");
                    } else {
                        Panel.mainLabel.setText("Tie Game");
                    }
                    timer.cancel();
                }
            }
        };
        timer.schedule(tt, 2, 100);
    }
}
