import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class ChromeDinosaur extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 1000;
    int boardHeight = 350;

    Image dinosaurImg;
    Image dinosaurDeadImg;
    Image dinosaurJumpImg;
    Image cactus1Img;
    Image cactus2Img;
    Image cactus3Img;
    Image birdImg;

    class Block {
        int x, y, width, height;
        Image img;
        String type;
   
        Block(int x, int y, int width, int height, Image img, String type) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.img = img;
            this.type = type;
        }
    }

    int dinosaurWidth = 88;
    int dinosaurHeight = 94;
    int dinosaurX = 50;
    int dinosaurY = boardHeight - dinosaurHeight;
    Block dinosaur;

    int cactus1Width = 34;
    int cactus2Width = 69;
    int cactus3Width = 102;
    int cactusHeight = 70;
    int cactusX = boardWidth - 50;
    int cactusY = boardHeight - cactusHeight;

    int birdWidth = 80;
    int birdHeight = 70;
    int birdY = boardHeight - dinosaurHeight - 150; // Bird আরও উপরে

    ArrayList<Block> obstacleArray;

    int velocityX = -4; // Easy mode শুরুতে কম speed
    int velocityY = 0;
    int gravity = 1;

    boolean gameOver = false;
    int score = 0;

    Timer gameLoop;
    Timer placeObstacleTimer;

    String lastObstacleType = "";

    public ChromeDinosaur() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.lightGray);
        setFocusable(true);
        addKeyListener(this);

        dinosaurImg = loadImage("/img/dino-run.gif");
        dinosaurDeadImg = loadImage("/img/dino-dead.png");
        dinosaurJumpImg = loadImage("/img/dino-jump.png");
        cactus1Img = loadImage("/img/cactus1.png");
        cactus2Img = loadImage("/img/cactus2.png");
        cactus3Img = loadImage("/img/cactus3.png");
        birdImg = loadImage("/img/bird.gif");

        dinosaur = new Block(dinosaurX, dinosaurY, dinosaurWidth, dinosaurHeight, dinosaurImg, "dino");
        obstacleArray = new ArrayList<>();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        placeObstacleTimer = new Timer(30, e -> placeObstacle());
        placeObstacleTimer.start();
    }

    private Image loadImage(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL == null) {
            System.err.println("Error: Could not find image file: " + path);
            return null;
        }
        return new ImageIcon(imgURL).getImage();
    }

    void placeObstacle() {
        if (gameOver) return;

        if (lastObstacleType.equals("cactus")) {
            obstacleArray.add(new Block(boardWidth, birdY, birdWidth, birdHeight, birdImg, "bird"));
            lastObstacleType = "bird";
        } else {
            double chance = Math.random();
            if (chance > 0.66) {
                obstacleArray.add(new Block(cactusX, cactusY, cactus3Width, cactusHeight, cactus3Img, "cactus"));
            } else if (chance > 0.33) {
                obstacleArray.add(new Block(cactusX, cactusY, cactus2Width, cactusHeight, cactus2Img, "cactus"));
            } else {
                obstacleArray.add(new Block(cactusX, cactusY, cactus1Width, cactusHeight, cactus1Img, "cactus"));
            }
            lastObstacleType = "cactus";
        }

        if (obstacleArray.size() > 10) {
            obstacleArray.remove(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(dinosaur.img, dinosaur.x, dinosaur.y, dinosaur.width, dinosaur.height, null);

        for (Block obs : obstacleArray) {
            g.drawImage(obs.img, obs.x, obs.y, obs.width, obs.height, null);
        }

        g.setColor(Color.black);
        g.setFont(new Font("Courier", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over: " + score, 10, 35);
        } else {
            g.drawString("Score: " + score, 10, 35);
            g.drawString("Level: " + getLevel(), 10, 70);
        }
    }

    public void move() {
        velocityY += gravity;
        dinosaur.y += velocityY;

        if (dinosaur.y > dinosaurY) {
            dinosaur.y = dinosaurY;
            velocityY = 0;
            dinosaur.img = dinosaurImg;
        }

        // Level speed change
        if (score <= 1000) {
            velocityX = -4; // Easy
        } else if (score <= 3000) {
            velocityX = -7; // Medium
        } else {
            velocityX = -11; // Hard
        }

        for (Block obs : obstacleArray) {
            obs.x += velocityX;

            if ((obs.type.equals("bird") || obs.type.equals("cactus")) && collision(dinosaur, obs)) {
                gameOver = true;
                dinosaur.img = dinosaurDeadImg;
            }
        }

        score++;
    }

    String getLevel() {
        if (score <= 1000) return "Easy";
        if (score <= 3000) return "Medium";
        return "Hard";
    }

    boolean collision(Block a, Block b) {
        int margin = 8;
        Rectangle rectA = new Rectangle(a.x + margin, a.y + margin, a.width - 2 * margin, a.height - 2 * margin);
        Rectangle rectB = new Rectangle(b.x + margin, b.y + margin, b.width - 2 * margin, b.height - 2 * margin);
        return rectA.intersects(rectB);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

        if (gameOver) {
            placeObstacleTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (dinosaur.y == dinosaurY) {
                velocityY = -17;
                dinosaur.img = dinosaurJumpImg;
            }

            if (gameOver) {
                dinosaur.y = dinosaurY;
                dinosaur.img = dinosaurImg;
                velocityY = 0;
                obstacleArray.clear();
                score = 0;
                gameOver = false;
                gameLoop.start();
                placeObstacleTimer.start();
            }
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    //final  code
}