import java.util.TimerTask;
import java.util.Timer;

public class Line {
    public static int y = 0;
    public Line () {
        Frame.stop = false;
        for (int i = 0; i < Panel.NUMDOTS; i++) {
            new Dot(i);
        }
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
    	    @Override
    	    public void run() {
                y++;
                if (y >= 500) {
                    Frame.stop = true;
                    if (Frame.p1Score > Frame.p2Score) {
                        Panel.mainLabel.setText("Left Ship Wins");
                    } else if (Frame.p2Score > Frame.p1Score) {
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
