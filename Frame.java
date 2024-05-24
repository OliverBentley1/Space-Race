import javax.swing.*;
import java.awt.event.*;

public class Frame extends JFrame implements KeyListener {
    public static boolean stop = true;
    public static Panel panel;
    final public static int FRAMESIZE = 500;
    public static int p1Velocity = 0;
    public static int p2Velocity = 0;
    final public static int startP1X = 125;
    final public static int startY = 450;
    final public static int startP2X = 375; 
    public static int p1X = startP1X;
    public static int p1Y = startY;
    public static int p2X = startP2X;
    public static int p2Y = startY;
    static boolean up2 = false;
    static boolean keyDown2 = false;
    static boolean up1 = false;
    static boolean keyDown1 = false;
    public static int p1Score = 0;
    public static int p2Score = 0;
    
    Frame() {
        panel = new Panel();
        
        this.setTitle("Space Race");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAMESIZE, FRAMESIZE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(new ImageIcon("spacerace.jpg").getImage());
        this.addKeyListener(this);
        this.add(panel);
        this.pack();
        this.setVisible(true);
        
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (!stop) {
                    try {
    	               Thread.sleep(100);
    	           } catch (Exception e) {}
                    if (keyDown1) {
                        if (up1 && p1Velocity > -20) {
                            Panel.rocketImage1 = new ImageIcon("RocketWithFire.png").getImage();
                            p1Velocity--;
                        } else if (p1Y < startY && p1Velocity < 20){
                            p1Velocity++;
                        }
                    } 
                    if (p1Velocity > 0 && p1Y > startY) {
                        p1Velocity = 0;
                    } else {
                        p1Y += p1Velocity;
                    }
                    
                    if (p1Y + 50 <= 0) {
                        p1Y = startY;
                        p1Velocity = 0;
                        p1Score++;
                        Panel.label1.setText(""+p1Score);
                    }
                }
            }
        };
        Thread thread2 = new Thread () {
            @Override
            public void run() {
                thread1.start();
                while (!stop) {
                    try {
    	               Thread.sleep(100);
    	           } catch (Exception e) {}
    	           if (keyDown2) {
                        if (up2 && p2Velocity > -20) {
                            Panel.rocketImage2 = new ImageIcon("RocketWithFire.png").getImage();
                            p2Velocity--;
                        } else if (p2Y < startY && p2Velocity < 20){
                            p2Velocity++;
                        }
                    }
                    if (p2Velocity > 0 && p2Y > startY) {
                        p2Velocity = 0;
                    } else {
                        p2Y += p2Velocity;
                    }
                    if (p2Y + 50 <= 0) {
                        p2Y = startY;
                        p2Velocity = 0;
                        p2Score++;
                        Panel.label2.setText(""+p2Score);
                    }
                }
            }
        };
        thread2.start();
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (!stop && (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP)) {
            keyDown2 = false;
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                Panel.rocketImage2 = new ImageIcon("rocket.png").getImage();
            }
        }
        if (!stop && (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S)) {
            keyDown1 = false;
            if (e.getKeyCode() == KeyEvent.VK_W) {
                Panel.rocketImage1 = new ImageIcon("rocket.png").getImage();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!stop) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    up2 = false;
                    keyDown2 = true;
                    break;
                case KeyEvent.VK_UP:
                    up2 = true;
                    keyDown2 = true;
                    break;
                case KeyEvent.VK_W:
                    up1 = true;
                    keyDown1 = true;
                    break;
                case KeyEvent.VK_S:
                    up1 = false;
                    keyDown1 = true;
                    break;  
            }
        } 
    }
}
