import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pacman extends JComponent implements KeyListener {
    final private ImageIcon PACMAN_UP = new ImageIcon("img/pacman/U1.png");
    final private ImageIcon PACMAN_UP2 = new ImageIcon("img/pacman/U2.png");
    final private ImageIcon PACMAN_DOWN = new ImageIcon("img/pacman/D1.png");
    final private ImageIcon PACMAN_DOWN2 = new ImageIcon("img/pacman/D2.png");
    final private ImageIcon PACMAN_LEFT = new ImageIcon("img/pacman/L1.png");
    final private ImageIcon PACMAN_LEFT2 = new ImageIcon("img/pacman/L2.png");
    final private ImageIcon PACMAN_RIGHT = new ImageIcon("img/pacman/R1.png");
    final private ImageIcon PACMAN_RIGHT2 = new ImageIcon("img/pacman/R2.png");
    final private ImageIcon PACMAN_IDLE = new ImageIcon("img/pacman/Idle.png");
    final private int DEFAULT_X =12;
    final private int DEFAULT_Y =19;
    final private int MAX_SCORE=30300;
    private int animationHelper=0;
    private ImageIcon currentImage ;
    private Direction currentDirection;
    private boolean isVisible,superPac;
    private int posX,posY,boardPosX,boardPosY,lives,score,addLife;
    private Timer animationTimer,moveTimer,stateTimer;
    private Labyrinthe labyrinthe;
    private Game game;
    private enum Direction {
        UP, DOWN, LEFT, RIGHT, IDLE
    }

    public Pacman(Labyrinthe labyrinthe,Game game) {
        //Constructeur de pacman

        this.labyrinthe=labyrinthe;
        this.game = game;
        isVisible=true;
        superPac=false;
        boardPosX = DEFAULT_X;
        boardPosY = DEFAULT_Y;
        score=0;
        lives =3;
        currentDirection = Direction.IDLE;
        currentImage = PACMAN_IDLE;


        //Ecoute des touches
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        //Animation du pacman
        animationTimer = new Timer(100, e -> {
            if (animationHelper > 0 && animationHelper < 4) {
                switch (currentDirection) {
                    case UP:
                        this.upAnim();
                        break;
                    case DOWN:
                        this.downAnim();
                        break;
                    case LEFT:
                        this.leftAnim();
                        break;
                    case RIGHT:
                        this.rightAnim();
                        break;
                    case IDLE:
                        currentImage = PACMAN_IDLE;
                        break;
                }
            } else {
                currentImage = PACMAN_IDLE;
                if(animationHelper==4){
                    System.out.println(animationHelper);
                    animationHelper=1;
                }else {
                    System.out.println(animationHelper);
                    animationHelper++;
                }
            }
            repaint();
        });
        animationTimer.start();

        //Lancer le pacman
        moveTimer = new Timer(250, e -> movePacman());
        moveTimer.start();

        //Verification de l'etat chaque 10ms
        stateTimer=new Timer(20,e ->{
            if (this.isVisible) {
                Phantome[] tabPhantoms = game.getPhantoms();
                for (int j = 0; j < tabPhantoms.length; j++) {
                    //Si le pacman se trouve a la meme case du phantome
                    if (boardPosX == tabPhantoms[j].getBoardPosX() && boardPosY == tabPhantoms[j].getBoardPosY()) {
                        //Si le fantome est en etat vulnerable il se fait manger donc reinitialisation
                        if (tabPhantoms[j].getVulnerable()) {
                            tabPhantoms[j].reset();
                        //Sinon c'est le fantome qui mange le pacman
                        } else {
                            game.reset();
                            lives--;
                            //Mise a jour des vies sur l'ecran
                            game.updateLives();

                            //Verifications des vies restantes
                            if (lives == 0) {
                                JOptionPane.showMessageDialog(null, "     Score: " + score + "\n     You lose!");
                                System.exit(0);
                            }

                            break;
                        }
                    }
                }
            }
            //Verification des points collecté
            if(score == MAX_SCORE) {
                // You win
                JOptionPane.showMessageDialog(null, "You win!");
                System.exit(0);
            }
        });
        stateTimer.start();
    }

    public int getScore() {
        return score;
    }
    public int getLives() {
        return lives;
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            if(!(currentDirection==Direction.LEFT)){
                currentDirection = Direction.LEFT;
                animationHelper = 0;
                System.out.println("left");
            }
        }else if (keyCode == KeyEvent.VK_RIGHT) {
            if(!(currentDirection==Direction.RIGHT)) {
                currentDirection = Direction.RIGHT;
                animationHelper = 0;
                System.out.println("right");
            }
        }else if (keyCode == KeyEvent.VK_UP) {
            if(!(currentDirection==Direction.UP)) {
                currentDirection = Direction.UP;
                animationHelper = 0;
                System.out.println("Up");
            }
        }else if (keyCode == KeyEvent.VK_DOWN) {
            if(!(currentDirection==Direction.DOWN)) {
                currentDirection = Direction.DOWN;
                animationHelper = 0;
                System.out.println("Down");
            }
        }
        repaint();
    }





    private void movePacman() {
        int nextPosX=boardPosX;
        int nextPosY=boardPosY;

        //Direction du pacman et calcul de la fonction du wraparound
        switch (currentDirection) {
            case UP:
                nextPosY = (nextPosY == 0) ? 24 : (nextPosY - 1);
                break;
            case DOWN:
                nextPosY = (nextPosY+1)%labyrinthe.getLength();
                break;
            case LEFT:
                nextPosX = (nextPosX == 0) ? 24 : (nextPosX - 1);
                break;
            case RIGHT:
                nextPosX = (nextPosX+1)%labyrinthe.getLength();
                break;
        }

        //Si on ne detecte pas de mur a la prochaine case le pacman avance normalement
        if (labyrinthe.getBoard()[nextPosY][nextPosX] != 1) {
            boardPosX = nextPosX;
            boardPosY = nextPosY;

            //A la detection d'une pacpoint on rajoute 100 au score
            if(labyrinthe.getBoard()[nextPosY][nextPosX]==2){
                score+=100;
                addLife+=100;
            //A la detection d'un pacpoint vert on rajoute 1000 au score et on active l'effet du pacpoint
            }else if(labyrinthe.getBoard()[nextPosY][nextPosX]==3){
                score+=1000;
                addLife+=1000;
                labyrinthe.greenEffect();
            //A la detection d'un pacpoint Violet on rajoute 300 au score et on active l'effet du pacpoint
            }else if(labyrinthe.getBoard()[nextPosY][nextPosX]==4){
                score+=300;
                addLife+=300;
                this.purpleEffect();
            //A la detection d'un pacpoint Orange on rajoute 500 au score et on active l'effet du pacpoint
            }else if(labyrinthe.getBoard()[nextPosY][nextPosX]==5){
                score+=500;
                addLife+=500;
                this.orangeEffect();
            }

            //Vider la case visité
            labyrinthe.setCase(nextPosY,nextPosX,0);

            //Mise a jour du score a l'ecran
            game.updateScore();

            //Calcul des vies suplémentaires
            if(addLife>=5000){
                addLife-=5000;
                lives++;
                game.updateLives();
            }
        }
        repaint();
    }

    //Effet du pacpoint violet
    public void purpleEffect(){
        //Invisibilité
        isVisible=false;
        game.updateState();
        System.out.println("BOOOOOO");
        repaint();
        //Timer du pouvoir (10secondes)
        new Timer(10000, e -> {
            isVisible = true;
            game.updateState();
            repaint();
        }).start();
        deactivateOrangeEffect();
    }

    //Effet du pacpoint Orange
    public void orangeEffect(){
        //Activation du Super Pacman
        superPac=true;
        game.updateState();
        System.out.println("SUPERDUPER!!!!");
        //Mettre la partie en etat superPac (L'inversement des roles)
        game.superPac();
        repaint();
        isVisible=true;
    }

    //Desactivation du Super Pacman
    public void deactivateOrangeEffect(){
        superPac=false;
        repaint();

    }

    //Reinitialisation du Pacman
    public void reset(){
        superPac=false;
        isVisible=true;
        game.updateState();
        currentDirection = Direction.IDLE;
        boardPosX = DEFAULT_X;
        boardPosY = DEFAULT_Y;
        repaint();
    }

    public void rightAnim(){
        if (animationHelper == 1 || animationHelper == 3) {
            currentImage = PACMAN_RIGHT;
            animationHelper++;
        }else if(animationHelper==2){
            currentImage=PACMAN_RIGHT2;
            animationHelper++;
        }
        repaint();
    }

    public void leftAnim(){
         if (animationHelper == 1 || animationHelper == 3) {
             currentImage = PACMAN_LEFT;
             animationHelper++;
         }else if(animationHelper==2){
             currentImage=PACMAN_LEFT2;
             animationHelper++;
         }
         repaint();
    }
    public void upAnim(){
        if (animationHelper == 1 || animationHelper == 3) {
            currentImage = PACMAN_UP;
            animationHelper++;
        }else if(animationHelper==2){
            currentImage=PACMAN_UP2;
            animationHelper++;
        }
        repaint();
    }
    public void downAnim(){
        if (animationHelper == 1 || animationHelper == 3) {
            currentImage = PACMAN_DOWN;
            animationHelper++;
        }else if(animationHelper==2){
            currentImage=PACMAN_DOWN2;
            animationHelper++;
        }
        repaint();
    }

    public void deadAnim(){

    }

    public String getPower(){
        if(!this.isVisible){
            return "                                                   ~~InvisibilitY~~";
        }else if(this.superPac) {
            return "                                                   ~~SUPER PACMAN~~";
        }
        return"";
    }

    //Dessin du composant
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //Transformation de la position xy sur le tableau en position xy sur l'interface graphique
        //Multiplication par 20 car chaque case du tableau est representé en 20pixel
        posX = boardPosX * 20;
        posY = boardPosY * 20;

        //Dessin du Pacman Invisible
        if(!this.isVisible) {
            g.setColor(Color.YELLOW);
            g.drawOval(posX, posY, 18, 18);
        //Dessin du Super Pacman
        }else if(this.superPac){
            g.setColor(Color.ORANGE);
            g.fillOval(posX, posY, 18, 18);
        //Dessin du Pacman
        }else{
            Image ScaledImage = currentImage.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
            ImageIcon scaledImageIcon = new ImageIcon(ScaledImage);
            g.drawImage(scaledImageIcon.getImage(), posX, posY, this);
        }
    }
}
