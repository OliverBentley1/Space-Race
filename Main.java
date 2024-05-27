import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Main {
    public static Frame frame;
    public static Dot[] dotList = new Dot[Panel.NUMDOTS];
    public static double p1Velocity = 0;
    public static double p2Velocity = 0;
    final public static int startP1X = 125;
    final public static int startY = 475;
    final public static int startP2X = 375; 
    public static double p1X = startP1X;
    public static double p1Y = startY;
    public static double p2X = startP2X;
    public static double p2Y = startY;
    public static int waitP1 = 0;
    public static int waitP2 = 0;
    static boolean stopP1 = false;
    static boolean stopP2 = false;
    public static int p1Score = 0;
    public static int p2Score = 0;
    final static int waitTime = 50;
    
    public static void main(String[] args) {
        Random r = new Random();
        //initialize the frame
        frame = new Frame();
        
        //initialize the dots
        for (int i = 0; i < Panel.NUMDOTS; i++) {
            dotList[i] = new Dot();
        }
        
        //this sets off the count down to let the dots have a 
        //headstart
        Panel.mainLabel.setText("3");
        for (int i = 0; i < 300; i++) {
            try {
    	        Thread.sleep(10);
    	    } catch (Exception e) {}
    	    for (int j = 0; j < dotList.length; j++) {
                if (dotList[j].right) {
                    dotList[j].x++;
                } else {
                    dotList[j].x--;
                }
    	    }
    	    nextFrame();
            if (i == 75) {
                Panel.mainLabel.setText("2");
            }
            if (i == 150) {
                Panel.mainLabel.setText("1");
            }
            if (i == 225) {
               Panel.mainLabel.setText("Go"); 
            }
        }
        Panel.mainLabel.setText("");
        
        //initializes line which starts the game
        Line line = new Line();
        while(!Frame.stop) {
            //moves the dots and checks if they either hit the wall
            //or hit any of the rockets
            for (int i = 0; i < dotList.length; i++) {
                Dot dot = dotList[i];
                //checks if it hit the wall and if so randomizes
                //where the dots will be placed on that side
                if (dot.right && dot.x >= 607) {
                    dot.y = r.nextInt(400);
                    dot.right = false;
                    dot.x = r.nextInt(500) + 593;
                } else if (!dot.right && dot.x <= -93) {
                    dot.y = r.nextInt(400);
                    dot.right = true;
                    dot.x = 0 - (r.nextInt(250) + 107);
                }
                
                //if the dot is going right that move it right
                //else moves left and checks if it is touching the rockets 
                if (dot.right) {
                    dot.x++;
    	            if (dot.touchRocket(p1X - 13.0, p1Y - 25.0) && !stopP1) {
    	                waitP1 = waitTime;
                        stopP1 = true;
                        Panel.rocketImage1 = new ImageIcon("explosion.png").getImage();
                        p1Velocity = 0;
    	            }
    	            if (dot.touchRocket(p2X - 13.0, p2Y - 25.0) && !stopP2) {
    	                waitP2 = waitTime;
                        stopP2 = true;
                        Panel.rocketImage2 = new ImageIcon("explosion.png").getImage();
                        p2Velocity = 0;
    	            }
                } else {
                    dot.x--;
    	            if (dot.touchRocket(p1X - 13.0, p1Y - 25.0) && !stopP1) {
    	                waitP1 = waitTime;
                        stopP1 = true;
                        Panel.rocketImage1 = new ImageIcon("explosion.png").getImage();
                        p1Velocity = 0;
    	            }
    	            if (dot.touchRocket(p2X - 13.0, p2Y - 25.0) && !stopP2) {
    	                waitP2 = waitTime;
                        stopP2 = true;
                        Panel.rocketImage2 = new ImageIcon("explosion.png").getImage();
                        p2Velocity = 0;
    	            }
                }
            }
            
            //if the rocket has been hit and if the timer is over
            if (waitP1 == 0 && stopP1) {
                stopP1 = false;
                p1Y = startY;
                Panel.rocketImage1 = new ImageIcon("rocket.png").getImage();
            }
            
            //checks if the playre is pressing a key down for the left rocket
            if (Frame.keyDown1 && waitP1 == 0) {
                
                //changes the velocity deponding on the current velocity
                //and which button is being pressed
                if (Frame.up1 && p1Velocity > -1.5) {
                    Panel.rocketImage1 = new ImageIcon("RocketWithFire.png").getImage();
                    p1Velocity-=.05;
                } else if (p1Y < startY && p1Velocity < 1){
                    p1Velocity+=.05;
                }
            }
            
            //checks if the rocket hits the bottom, if so then sets then
            //sets the velocity to 0
            if (p1Velocity > 0 && p1Y > startY) {
                p1Velocity = 0;
            } else {
                p1Y += p1Velocity;
            }
              
            //if the rocket got to the top it sets the score and resets the 
            //rocket
            if (p1Y <= 0) {
                p1Y = startY;
                p1Velocity = 0;
                p1Score++;
                Panel.label1.setText(""+p1Score);
            }
            
            //checks if rocket 2 is still waiting from being hit
            if (waitP2 == 0 && stopP2) {
                stopP2 = false;
                p2Y = startY;
                Panel.rocketImage2 = new ImageIcon("rocket.png").getImage();
            }
            
            //executes this code if the rocket was not hit and if any of the
            //keys are pressed
            if (Frame.keyDown2 && waitP2 == 0) {
                
                //checks if the velocity capicity is reached and if the 
                //the buttons were pressed
                if (Frame.up2 && p2Velocity > -1.5) {
                    Panel.rocketImage2 = new ImageIcon("RocketWithFire.png").getImage();
                    p2Velocity-=.05;
                } else if (p2Y < startY && p2Velocity < 1){
                    p2Velocity+=.05;
                }
            }
            
            //checks if it hit the bottom boarder, if not than makes the
            //rocket move forward
            if (p2Velocity > 0 && p2Y > startY) {
                p2Velocity = 0;
            } else {
                p2Y += p2Velocity;
            }
               
            //checks if the rocket touched the tio and updates the score        
            if (p2Y <= 0) {
                waitP2 = 5;
                stopP2 = true;
                p2Y = startY;
                p2Velocity = 0;
                p2Score++;
                Panel.label2.setText(""+p2Score);
            }
            
            //delays the loop
            try {
    	        Thread.sleep(10);
    	    } catch (Exception e) {}
    	    //counts down the timer when the either rockets get hit
    	    if (stopP1) {
    	        waitP1--;
    	    }
    	    if (stopP2) {
    	        waitP2--;
    	    }
    	    //paints a new frame              
            nextFrame();
        }
    }
    
    public static void nextFrame() {
        Frame.panel.repaint();
    }
}
