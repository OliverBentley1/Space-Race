import java.util.Random;
import java.awt.Rectangle;

public class Dot {
    public int y = 0;
    public int x = 0;
    public boolean right;
    public Dot() {
        Random r = new Random();
        
        y = r.nextInt(400);
        if (r.nextInt(2) == 1) {
            right = false;
            x = r.nextInt(500) + 593;
        } else {
            right = true;
            x = 0 - (r.nextInt(500) + 107);
        }
    }
    
    public boolean touchRocket(double x, double y) {
        Rectangle r = new Rectangle((int) x, (int) y, Panel.SHIPWIDTH, Panel.SHIPHEIGHT);
        if (r.contains(this.x, this.y)) {
            return true;
        } else {
            return false;
        }
    }
}
