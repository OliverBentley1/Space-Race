import java.util.TimerTask;
import java.util.Timer;
import java.util.Random;
import java.awt.Rectangle;

public class Dot {
    public int y = 0;
    public int x = -100;
    public int index;
    public Dot(int i) {
        Random r = new Random();
        index = i;
        Panel.dotList.add(this);
        Thread thread = new Thread() {
            @Override
            public void run() {
                while(!Frame.stop) {
                    y = r.nextInt(350);
                    try {
    	               Thread.sleep(r.nextInt(5000));
    	           } catch (Exception e) {}
                    if (r.nextInt(2) == 1) {
                        x = -100;
                       while(x < 550) {
    	                    x++;
    	                    if (touchRocket(Frame.p1X - 25, Frame.p1Y - 50)) {
    	                        Frame.p1Y = Frame.startY;
                                Frame.p1Velocity = 0;
    	                    }
    	                    if (touchRocket(Frame.p2X - 25, Frame.p2Y - 50)) {
    	                        Frame.p2Y = Frame.startY;
                                Frame.p2Velocity = 0;
    	                    }
    	                    try {
    	                        Thread.sleep(10);
    	                    } catch (Exception e) {}
                       }       
                    } else {
                        x = 550;
                        while(x > -100) {
    	                    x--;
    	                    if (touchRocket(Frame.p1X - 25, Frame.p1Y - 50)) {
    	                        Frame.p1Y = Frame.startY;
                                Frame.p1Velocity = 0;
    	                    }
    	                    if (touchRocket(Frame.p2X - 25, Frame.p2Y - 50)) {
    	                        Frame.p2Y = Frame.startY;
                                Frame.p2Velocity = 0;
    	                    }
    	                   try {
    	                        Thread.sleep(10);
    	                   } catch (Exception e) {}
                       }  
                    }
                }
            }
        };
        thread.start();
    }
    
    public boolean touchRocket(int x, int y) {
        Rectangle r = new Rectangle(x, y, Panel.SHIPWIDTH, Panel.SHIPHEIGHT);
        if (r.contains(this.x, this.y)) {
            return true;
        } else {
            return false;
        }
    }
}
