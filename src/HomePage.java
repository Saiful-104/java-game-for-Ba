import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JPanel {
    private JFrame frame;

    public HomePage(JFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());
        setBackground(new Color(224, 255, 255));

        setPreferredSize(new Dimension(1000, 350));

        JLabel welcomeLabel = new JLabel("Welcome to Chrome Dinosaur Game", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        welcomeLabel.setForeground(new Color(0, 102, 102));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(40, 10, 40, 10));
        add(welcomeLabel, BorderLayout.NORTH);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        startButton.setFocusPainted(false);
        startButton.setBackground(new Color(0, 153, 153));
        startButton.setForeground(Color.WHITE);
        startButton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(224, 255, 255));
        centerPanel.add(startButton);
        add(centerPanel, BorderLayout.CENTER);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();

                ChromeDinosaur chromeDinosaur = new ChromeDinosaur();
                chromeDinosaur.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));

                frame.add(chromeDinosaur);
                frame.pack();
                chromeDinosaur.requestFocusInWindow();
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}
