import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Window {
    public static void main(String[] args) {
        BouncingBalls mainClass = new BouncingBalls();
        mainClass.start();
    }
}

class BouncingBalls {
    public JFrame ballWindow;
    private DrawPanel drawPanel;
    private java.util.List<leBall> ballCollection;

    private int topWindowOffset = 30;

    void start() {

        ballCollection = new ArrayList<>();

        // Generates 42 balls with randomized characteristics and adds them to an ArrayList. 
        // #MeaningOfLife
        for (int i = 0; i < 42; i++) {
            leBall ball = new leBall(
                    // Random spawn positions; window size - 100 
                    Math.floor(Math.random() * 900),
                    Math.floor((Math.random() * 500)),
                    // Size randomization from 10 to 50
                    (int) Math.floor(Math.random() * 40) + 10,
                    // Random colors using RGB
                    new Color(
                        (int) Math.floor((Math.random() * 256)), (int) Math.floor((Math.random() * 256)), (int) Math.floor((Math.random() * 256))),
                    // Random velocities from -4 - 4 (X axis) and from -7.5 -7.5 (y axis)
                    Math.floor((Math.random() * 8) - 4),
                    Math.floor((Math.random() * 15) - 7.5),
                    (600 - topWindowOffset), //Provide 30 less in height to compensate for top window border, may vary from OS to OS
                    (1000) 
            );
            ballCollection.add(ball);
        }

        // Start JFrame and initialize basic variables and parameters
        ballWindow = new JFrame();
        drawPanel = new DrawPanel();
        ballWindow.getContentPane().add(drawPanel);
        ballWindow.setTitle("Buncha' balls");
        ballWindow.setSize(1000, 600);
        ballWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ballWindow.setVisible(true);


        //Updating ball position
        while (true) {
            for (leBall theBallsLol: ballCollection) {
                theBallsLol.update();
            }

            //Determines visual update speed
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ballWindow.repaint();
        }
    }

    //Introduces graphics and calls the draw method from leBall.java. Draws all ball in ArrayList
    class DrawPanel extends JPanel {    
        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            for (leBall theBallsLol: ballCollection) {
                theBallsLol.draw(graphics);
            }
        }
    }
}