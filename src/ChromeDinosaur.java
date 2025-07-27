import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


// public class ChromeDinosaur {
  
// }



public class ChromeDinosaur extends JPanel {
       int boardWidth=750;
       int boardHeight=250;
       Image dinosaurImg;
       Image dinosaurDeadImg;
       Image dinosaurJumpImg;
       Image cactus1Img;
       Image cactus2Img;
       Image cactus3Img;
        
         class Block{
            int x;
            int y;
            int width;
            int height;
            Image img;
              Block(int x,int y,int width, int height,Image img){
                this.x=x;
                this.y=y;
                this.width=width;
                this.height=height;
                this.img=img;

              }
         }
       //dinosaur
       int dinosaurWidth =88;
       int dinosaurHeight = 94;
       int dinosaurX =50;
       int dinosaurY = boardHeight-dinosaurHeight;

       Block dinosaur;

       public ChromeDinosaur(){
        setPreferredSize(new Dimension (boardWidth,boardHeight));
        setBackground(Color.pink);
   
         // dinosaurImg =new ImageIcon(getClass().getResource("./img/dino-run.gif")).getImage();
         dinosaurImg = new ImageIcon(getClass().getResource("./img/dino-run.gif")).getImage();
        dinosaurDeadImg = new ImageIcon(getClass().getResource("./img/dino-dead.png")).getImage();
        dinosaurJumpImg = new ImageIcon(getClass().getResource("./img/dino-jump.png")).getImage();
         cactus1Img = new ImageIcon(getClass().getResource("./img/cactus1.png")).getImage();
       cactus2Img =  new ImageIcon(getClass().getResource("./img/cactus2.png")).getImage();
       cactus3Img = new ImageIcon(getClass().getResource("./img/cactus3.png")).getImage();
         
       //dinosaur
       dinosaur = new Block(dinosaurX, dinosaurY,dinosaurWidth,dinosaurHeight,dinosaurImg);

       }
   public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
   }
  public void draw(Graphics g){
    g.drawImage(dinosaur.img, dinosaur.x, dinosaur.y, dinosaur.width, dinosaur.height, this);
}

}