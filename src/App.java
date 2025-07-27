import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
       // System.out.println("Hello, World!");
       int boardWidth=750;
       int boardHeight=250;
       JFrame frame =new JFrame("Chrome Dinosaur");
      // frame.setVisible(true);
      frame.setSize (boardWidth,boardHeight);
      frame.setLocationRelativeTo(null);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      ChromeDinosaur chromedinosaur = new ChromeDinosaur();
      
      frame.add(chromedinosaur);
      frame.pack();

         frame.setVisible(true);
    }
}
