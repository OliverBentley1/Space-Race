import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel {
    final public static int PANELSIZE = 500;
    final public static int DOTSIZE = 15;
    final public static int SHIPWIDTH = 50;
    final public static int SHIPHEIGHT = SHIPWIDTH * 2;
    final public static int NUMDOTS = 20;
    
    Image backgroundImage;
    Image dotImage;
    public static Image rocketImage1;
    public static Image rocketImage2;
    Image line;
    public static JLabel label1;
    public static JLabel label2;
    public static JLabel mainLabel;
    
    public static ArrayList<Dot> dotList = new ArrayList<Dot>(); 
    
    Panel() {
        this.setPreferredSize(new Dimension(PANELSIZE, PANELSIZE));
        this.setLayout(null);
        
        backgroundImage = new ImageIcon("background.png").getImage();
        dotImage = new ImageIcon("dot.png").getImage();
        rocketImage1 = new ImageIcon("rocket.png").getImage();
        rocketImage2 = new ImageIcon("rocket.png").getImage();
        line = new ImageIcon("line.png").getImage();
        
        label1 = new JLabel("0");
	    label1.setFont(new Font("Comic Sans", Font.BOLD, 30));
	    label1.setBounds(10, 0, PANELSIZE, PANELSIZE);
	    label1.setVerticalAlignment(JLabel.TOP);
	    label1.setHorizontalAlignment(JLabel.LEFT);
	    label1.setForeground(Color.WHITE);
	    
	    label2 = new JLabel("0");
	    label2.setFont(new Font("Comic Sans", Font.BOLD, 30));
	    label2.setBounds(0, 0, PANELSIZE - 10, PANELSIZE);
	    label2.setVerticalAlignment(JLabel.TOP);
	    label2.setHorizontalAlignment(JLabel.RIGHT);
	    label2.setForeground(Color.WHITE);
	    
	    mainLabel = new JLabel();
	    mainLabel.setFont(new Font("Comic Sans", Font.BOLD, 50));
	    mainLabel.setBounds(0, 0, PANELSIZE, PANELSIZE);
	    mainLabel.setVerticalAlignment(JLabel.CENTER);
	    mainLabel.setHorizontalAlignment(JLabel.CENTER);
	    mainLabel.setForeground(Color.WHITE);
	    
	    this.add(label1);
	    this.add(label2);
	    this.add(mainLabel);
    }
    
    protected void paintComponent(Graphics g) {
        int dotFixedLoc = (int) (DOTSIZE / 2); // subract off of the x and y
        int shipFixedX = (int) (SHIPWIDTH / 2);
        int shipFixedY = (int) (SHIPHEIGHT / 2);
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(backgroundImage, 0, 0, PANELSIZE, PANELSIZE, null);
        for (int i = 0; i < dotList.size(); i++) {
            Dot dot = dotList.get(i);
            g2D.drawImage(dotImage, dot.x - dotFixedLoc, dot.y - dotFixedLoc, DOTSIZE, DOTSIZE, null);
        }
        g2D.drawImage(rocketImage1, Frame.p1X - shipFixedX, Frame.p1Y - shipFixedY, SHIPWIDTH, SHIPHEIGHT, null);
        g2D.drawImage(rocketImage2, Frame.p2X - shipFixedX, Frame.p2Y - shipFixedY, SHIPWIDTH, SHIPHEIGHT, null);
        g2D.drawImage(line, 248, Line.y, 5, 500, null);
        
        if (!Frame.stop) {
            repaint(); 
        }
    }
}
