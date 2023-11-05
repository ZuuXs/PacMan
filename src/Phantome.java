import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Phantome extends JComponent {
    final private ImageIcon RED_PHANTOM_UP1 =new ImageIcon("img/ghost/red/U1.png");
    final private ImageIcon RED_PHANTOM_UP2 =new ImageIcon("img/ghost/red/U2.png");
    final private ImageIcon RED_PHANTOM_RIGHT1 =new ImageIcon("img/ghost/red/R1.png");
    final private ImageIcon RED_PHANTOM_RIGHT2 =new ImageIcon("img/ghost/red/R2.png");
    final private ImageIcon RED_PHANTOM_LEFT1 =new ImageIcon("img/ghost/red/L1.png");
    final private ImageIcon RED_PHANTOM_LEFT2 =new ImageIcon("img/ghost/red/L2.png");
    final private ImageIcon RED_PHANTOM_DOWN1 =new ImageIcon("img/ghost/red/D1.png");
    final private ImageIcon RED_PHANTOM_DOWN2 =new ImageIcon("img/ghost/red/D2.png");
    //////////////////////////////////////////////////////////////////////////////////////////
    final private ImageIcon CYAN_PHANTOM_UP1 =new ImageIcon("img/ghost/cyan/U1.png");
    final private ImageIcon CYAN_PHANTOM_UP2 =new ImageIcon("img/ghost/cyan/U2.png");
    final private ImageIcon CYAN_PHANTOM_RIGHT1 =new ImageIcon("img/ghost/cyan/R1.png");
    final private ImageIcon CYAN_PHANTOM_RIGHT2 =new ImageIcon("img/ghost/cyan/R2.png");
    final private ImageIcon CYAN_PHANTOM_LEFT1 =new ImageIcon("img/ghost/cyan/L1.png");
    final private ImageIcon CYAN_PHANTOM_LEFT2 =new ImageIcon("img/ghost/cyan/L2.png");
    final private ImageIcon CYAN_PHANTOM_DOWN1 =new ImageIcon("img/ghost/cyan/D1.png");
    final private ImageIcon CYAN_PHANTOM_DOWN2 =new ImageIcon("img/ghost/cyan/D2.png");

    ///////////////////////////////////////////////////////////////////////////////////////////
    final private ImageIcon PINK_PHANTOM_UP1 =new ImageIcon("img/ghost/pink/U1.png");
    final private ImageIcon PINK_PHANTOM_UP2 =new ImageIcon("img/ghost/pink/U2.png");
    final private ImageIcon PINK_PHANTOM_RIGHT1 =new ImageIcon("img/ghost/pink/R1.png");
    final private ImageIcon PINK_PHANTOM_RIGHT2 =new ImageIcon("img/ghost/pink/R2.png");
    final private ImageIcon PINK_PHANTOM_LEFT1 =new ImageIcon("img/ghost/pink/L1.png");
    final private ImageIcon PINK_PHANTOM_LEFT2 =new ImageIcon("img/ghost/pink/L2.png");
    final private ImageIcon PINK_PHANTOM_DOWN1 =new ImageIcon("img/ghost/pink/D1.png");
    final private ImageIcon PINK_PHANTOM_DOWN2 =new ImageIcon("img/ghost/pink/D2.png");

    ///////////////////////////////////////////////////////////////////////////////////////////
    final private ImageIcon ORANGE_PHANTOM_UP1 =new ImageIcon("img/ghost/orange/U1.png");
    final private ImageIcon ORANGE_PHANTOM_UP2 =new ImageIcon("img/ghost/orange/U2.png");
    final private ImageIcon ORANGE_PHANTOM_RIGHT1 =new ImageIcon("img/ghost/orange/R1.png");
    final private ImageIcon ORANGE_PHANTOM_RIGHT2 =new ImageIcon("img/ghost/orange/R2.png");
    final private ImageIcon ORANGE_PHANTOM_LEFT1 =new ImageIcon("img/ghost/orange/L1.png");
    final private ImageIcon ORANGE_PHANTOM_LEFT2 =new ImageIcon("img/ghost/orange/L2.png");
    final private ImageIcon ORANGE_PHANTOM_DOWN1 =new ImageIcon("img/ghost/orange/D1.png");
    final private ImageIcon ORANGE_PHANTOM_DOWN2 =new ImageIcon("img/ghost/orange/D2.png");
    ////////////////////////////////////////////////////////////////////////////////////////////////
    final private ImageIcon VULNERABLE1 =new ImageIcon("img/ghost/vulnerable/1.png");
    final private ImageIcon VULNERABLE2 =new ImageIcon("img/ghost/vulnerable/2.png");

    final private int DEFAULTX,DEFAULTY;
    private ImageIcon currentImage ;
    private Labyrinthe labyrinthe;
    private Direction currentDirection;
    private Boolean vulnerable;
    private int boardPosX,boardPosY,posX,posY,originalDelay;
    private Random random;
    private Timer moveTimer,animationTimer;
    private Color ghostColor;
    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Phantome(Color c,int defaultX, int defaultY, Labyrinthe labyrinthe){
        //Constructeur du Fantome

        this.ghostColor=c;
        if(ghostColor.equals(Color.RED)){
            currentImage=RED_PHANTOM_UP1;
        }else if(ghostColor.equals(Color.CYAN)){
            currentImage=CYAN_PHANTOM_UP1;
        }else if(ghostColor.equals(Color.ORANGE)){
            currentImage=ORANGE_PHANTOM_UP1;
        }else if(ghostColor.equals(Color.PINK)){
            currentImage=PINK_PHANTOM_UP1;
        }
        vulnerable=false;

        this.DEFAULTX=defaultX;
        this.DEFAULTY=defaultY;
        this.boardPosX=DEFAULTX;
        this.boardPosY=DEFAULTY;

        this.labyrinthe = labyrinthe;

        random = new Random();

        currentDirection = Direction.values()[random.nextInt(4)];


        animationTimer= new Timer(200,e -> {
            if(!this.vulnerable){
                switch (currentDirection) {
                    case UP:
                        if (ghostColor.equals(Color.RED)) {
                            if (currentImage == RED_PHANTOM_UP1) {
                                currentImage = RED_PHANTOM_UP2;
                            } else {
                                currentImage = RED_PHANTOM_UP1;
                            }
                        } else if (ghostColor.equals(Color.PINK)) {
                            if (currentImage == PINK_PHANTOM_UP1) {
                                currentImage = PINK_PHANTOM_UP2;
                            } else {
                                currentImage = PINK_PHANTOM_UP1;
                            }
                        } else if (ghostColor.equals(Color.CYAN)) {
                            if (currentImage == CYAN_PHANTOM_UP1) {
                                currentImage = CYAN_PHANTOM_UP2;
                            } else {
                                currentImage = CYAN_PHANTOM_UP1;
                            }
                        } else if (ghostColor.equals(Color.ORANGE)) {
                            if (currentImage == ORANGE_PHANTOM_UP1) {
                                currentImage = ORANGE_PHANTOM_UP2;
                            } else {
                                currentImage = ORANGE_PHANTOM_UP1;
                            }
                        }
                        break;
                    case DOWN:
                        if (ghostColor.equals(Color.RED)) {
                            if (currentImage == RED_PHANTOM_DOWN1) {
                                currentImage = RED_PHANTOM_DOWN2;
                            } else {
                                currentImage = RED_PHANTOM_DOWN1;
                            }
                        } else if (ghostColor.equals(Color.PINK)) {
                            if (currentImage == PINK_PHANTOM_DOWN1) {
                                currentImage = PINK_PHANTOM_DOWN2;
                            } else {
                                currentImage = PINK_PHANTOM_DOWN1;
                            }
                        } else if (ghostColor.equals(Color.CYAN)) {
                            if (currentImage == CYAN_PHANTOM_DOWN1) {
                                currentImage = CYAN_PHANTOM_DOWN2;
                            } else {
                                currentImage = CYAN_PHANTOM_DOWN1;
                            }
                        } else if (ghostColor.equals(Color.ORANGE)) {
                            if (currentImage == ORANGE_PHANTOM_DOWN1) {
                                currentImage = ORANGE_PHANTOM_DOWN2;
                            } else {
                                currentImage = ORANGE_PHANTOM_DOWN1;
                            }
                        }
                        break;
                    case LEFT:
                        if (ghostColor.equals(Color.RED)) {
                            if (currentImage == RED_PHANTOM_LEFT1) {
                                currentImage = RED_PHANTOM_LEFT2;
                            } else {
                                currentImage = RED_PHANTOM_LEFT1;
                            }
                        } else if (ghostColor.equals(Color.PINK)) {
                            if (currentImage == PINK_PHANTOM_LEFT1) {
                                currentImage = PINK_PHANTOM_LEFT2;
                            } else {
                                currentImage = PINK_PHANTOM_LEFT1;
                            }
                        } else if (ghostColor.equals(Color.CYAN)) {
                            if (currentImage == CYAN_PHANTOM_LEFT1) {
                                currentImage = CYAN_PHANTOM_LEFT2;
                            } else {
                                currentImage = CYAN_PHANTOM_LEFT1;
                            }
                        } else if (ghostColor.equals(Color.ORANGE)) {
                            if (currentImage == ORANGE_PHANTOM_LEFT1) {
                                currentImage = ORANGE_PHANTOM_LEFT2;
                            } else {
                                currentImage = ORANGE_PHANTOM_LEFT1;
                            }
                        }
                        break;
                    case RIGHT:
                        if (ghostColor.equals(Color.RED)) {
                            if (currentImage == RED_PHANTOM_RIGHT1) {
                                currentImage = RED_PHANTOM_RIGHT2;
                            } else {
                                currentImage = RED_PHANTOM_RIGHT1;
                            }
                        } else if (ghostColor.equals(Color.PINK)) {
                            if (currentImage == PINK_PHANTOM_RIGHT1) {
                                currentImage = PINK_PHANTOM_RIGHT2;
                            } else {
                                currentImage = PINK_PHANTOM_RIGHT1;
                            }
                        } else if (ghostColor.equals(Color.CYAN)) {
                            if (currentImage == CYAN_PHANTOM_RIGHT1) {
                                currentImage = CYAN_PHANTOM_RIGHT2;
                            } else {
                                currentImage = CYAN_PHANTOM_RIGHT1;
                            }
                        } else if (ghostColor.equals(Color.ORANGE)) {
                            if (currentImage == ORANGE_PHANTOM_RIGHT1) {
                                currentImage = ORANGE_PHANTOM_RIGHT2;
                            } else {
                                currentImage = ORANGE_PHANTOM_RIGHT1;
                            }
                        }
                        break;
                }
            }else{
                if(currentImage==VULNERABLE1){
                    currentImage=VULNERABLE2;
                }else{
                    currentImage=VULNERABLE1;
                }
            }
            repaint();
        });

        animationTimer.start();

        moveTimer = new Timer(250, e -> movePhantom());
        moveTimer.start();

        originalDelay = moveTimer.getDelay();
    }

    public int getBoardPosX() { return boardPosX; }
    public int getBoardPosY() { return boardPosY; }
    public Boolean getVulnerable() {
        return vulnerable;
    }

    public void setVulnerable(){
        vulnerable=true;
        moveTimer.setDelay(originalDelay * 2);
    }
    public void setUnvulnerable(){
        vulnerable=false;
        moveTimer.setDelay(originalDelay);
    }


    private void movePhantom() {
        int nextPosX = boardPosX;
        int nextPosY = boardPosY;

        switch (currentDirection) {
            case UP:
                nextPosY = (nextPosY == 0) ? 24 : (nextPosY - 1);
                break;
            case DOWN:
                nextPosY = (nextPosY + 1) % labyrinthe.getLength();
                break;
            case LEFT:
                nextPosX = (nextPosX == 0) ? 24 : (nextPosX - 1);
                break;
            case RIGHT:
                nextPosX = (nextPosX + 1) % labyrinthe.getLength();
                break;
        }

        if (labyrinthe.getBoard()[nextPosY][nextPosX] != 1) {
            boardPosX = nextPosX;
            boardPosY = nextPosY;
        } else {
            currentDirection = Direction.values()[random.nextInt(4)];
        }

        repaint();
    }

    public void reset(){
        this.setUnvulnerable();
        boardPosX=DEFAULTX;
        boardPosY=DEFAULTY;
        moveTimer.stop();
        Timer waiting= new Timer(1000,e -> moveTimer.start());
        repaint();
        waiting.start();
    }



    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        posX = (boardPosX) * 20;
        posY = (boardPosY) * 20;
        Image ScaledImage = currentImage.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon scaledImageIcon = new ImageIcon(ScaledImage);
        g.drawImage(scaledImageIcon.getImage(), posX, posY, this);

    }
}
