import javax.swing.*;

public class App {
    public static void main(String[] args) {
        int boardWidth = 1000;
        int boardHeight = 350;

        JFrame frame = new JFrame("Chrome Dinosaur");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HomePage homePage = new HomePage(frame);
        frame.add(homePage);
        frame.setVisible(true);
    }
}
