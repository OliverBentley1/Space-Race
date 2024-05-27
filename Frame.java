import javax.swing.*;
import java.awt.event.*;

public class Frame extends JFrame implements KeyListener {
    public static boolean stop = true;
    public static Panel panel;
    final public static int FRAMESIZE = 500;
    static boolean up2 = false;
    static boolean keyDown2 = false;
    static boolean up1 = false;
    static boolean keyDown1 = false;
    
    Frame() {
        panel = new Panel();
        
        this.setTitle("Space Race");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAMESIZE, FRAMESIZE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(new ImageIcon("spacerace.png").getImage());
        this.addKeyListener(this);
        this.add(panel);
        this.pack();
        this.setVisible(true);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (!stop && (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP)) {
            keyDown2 = false;
            if (e.getKeyCode() == KeyEvent.VK_UP && Main.waitP2 == 0) {
                Panel.rocketImage2 = new ImageIcon("rocket.png").getImage();
            }
        }
        if (!stop && (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S)) {
            keyDown1 = false;
            if (e.getKeyCode() == KeyEvent.VK_W && Main.waitP1 == 0) {
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
