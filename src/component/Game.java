package component;
import javax.swing.*;

import coordinate.Coordinate;
import coordinate.Direction;

import java.awt.*;
import java.util.Random;

public class Game extends JPanel {
    final private int MAX_SCORE = 30300;

    private Pacman pacman;
    private Labyrinthe labyrinthe;
    private Phantome cyanPhantom,orangePhantom,redPhantom,pinkPhantom;
    private JLabel scoreLabel, livesLabel, stateLabel;
    private Timer movePacmanTimer,movePhantomeTimer,stateTimer;

    public Game() {
        //Constructeur Game

        //Instance des objets du jeu
        labyrinthe = new Labyrinthe();
        pacman = new Pacman();
        cyanPhantom = new Phantome(Color.CYAN, 11, 12);
        orangePhantom = new Phantome(Color.ORANGE, 12, 12);
        redPhantom = new Phantome(Color.RED, 13, 12);
        pinkPhantom = new Phantome(Color.PINK, 14, 12);

        pacman.registerObserver(cyanPhantom);
        pacman.registerObserver(orangePhantom);
        pacman.registerObserver(redPhantom);
        pacman.registerObserver(pinkPhantom);

        //Creation du paneau
        setLayout(new BorderLayout());
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 500));

        //Definition des limites
        labyrinthe.setBounds(0, 0, 500, 500);
        pacman.setBounds(0, 0, 500, 500);
        cyanPhantom.setBounds(0, 0, 500, 500);
        orangePhantom.setBounds(0, 0, 500, 500);
        redPhantom.setBounds(0, 0, 500, 500);
        pinkPhantom.setBounds(0, 0, 500, 500);

        //Ajout des objet au paneau
        layeredPane.add(labyrinthe, Integer.valueOf(0));
        layeredPane.add(pacman, Integer.valueOf(1));
        layeredPane.add(cyanPhantom, Integer.valueOf(2));
        layeredPane.add(orangePhantom, Integer.valueOf(3));
        layeredPane.add(redPhantom, Integer.valueOf(4));
        layeredPane.add(pinkPhantom, Integer.valueOf(5));

        add(layeredPane, BorderLayout.CENTER);

        //Creation d'un footer
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.BLACK);

        //Positionement du score et des vies
        scoreLabel = new JLabel("0");
        scoreLabel.setForeground(Color.YELLOW);
        bottomPanel.add(scoreLabel, BorderLayout.EAST);

        livesLabel = new JLabel("Lives: 3");
        livesLabel.setForeground(Color.YELLOW);
        bottomPanel.add(livesLabel, BorderLayout.WEST);

        stateLabel = new JLabel("");
        stateLabel.setForeground(Color.YELLOW);
        bottomPanel.add(stateLabel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        //Lancement du Pacman
        movePacmanTimer = new Timer(250, e -> movePacman());
        movePacmanTimer.start();

        //Lancement des phantomes
        movePhantomeTimer = new Timer(getPhantoms()[0].getDelay(), e -> {
            for (Phantome ghost : getPhantoms()) {
                movePhantom(ghost);
            }
        });
        
        Timer waiting= new Timer(2000,e -> movePhantomeTimer.start());
        waiting.start();
    


        //L'etat du jeu
        stateTimer = new Timer(20, e -> check());
        stateTimer.start();
    }

    public Pacman getPacman() {
        return this.pacman;
    }


    //Recuperation des phantomes
    public Phantome[] getPhantoms(){
        return new Phantome[]{cyanPhantom, orangePhantom, redPhantom, pinkPhantom};
    }

    //Mise a jour du score et des vies
    public void updateScore() {scoreLabel.setText(String.valueOf(pacman.getScore()));}
    public void updateLives() {livesLabel.setText("Lives: "+String.valueOf(pacman.getLives()));}
    public void updateState() {stateLabel.setText(pacman.getPower()); }

    public void update() {
        updateScore();
        updateLives();
        updateState();
    }


    public int[] moveEntity(int nextPosX, int nextPosY, Direction direction) {
        //Direction et calcul de la fonction du wraparound
        switch (direction) {
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
            default:
                break;
        }

        return (new int[] { nextPosX, nextPosY });
    }

    //Fonction du mouvement du pacman
    public void movePacman() {
        int[] nextCase = moveEntity(pacman.getCoordinates().posXYToBoard().getX(), pacman.getCoordinates().posXYToBoard().getY(), pacman.getCurrentDirection());
        //Recuperation de la position du pacman sur le tableau
        int nextPosX = nextCase[0];
        int nextPosY = nextCase[1];
        
        if (labyrinthe.getBoard()[nextPosY][nextPosX] != 1) {
            pacman.setCoordinates(new Coordinate(nextPosX, nextPosY).boardToPosXY());

            //A la detection d'une pacpoint on rajoute 100 au score
            if (labyrinthe.getBoard()[nextPosY][nextPosX] == 2) {
                pacman.addScore(100);
                //A la detection d'un pacpoint vert on rajoute 1000 au score et on active l'effet du pacpoint
            } else if (labyrinthe.getBoard()[nextPosY][nextPosX] == 3) {
                pacman.addScore(1000);
                labyrinthe.greenEffect();
                //A la detection d'un pacpoint Violet on rajoute 300 au score et on active l'effet du pacpoint
            } else if (labyrinthe.getBoard()[nextPosY][nextPosX] == 4) {
                pacman.addScore(300);
                pacman.setState(new state.pacman.NormalState());
                pacman.setState(new state.pacman.InvisibleState());
                //A la detection d'un pacpoint Orange on rajoute 500 au score et on active l'effet du pacpoint
            } else if (labyrinthe.getBoard()[nextPosY][nextPosX] == 5) {
                pacman.addScore(500);
                pacman.setState(new state.pacman.NormalState());
                pacman.setState(new state.pacman.SuperState());
                superPac();
            }

            pacman.update();

            //Vider la case visité
            labyrinthe.setCase(nextPosY, nextPosX, 0);

            //Mise a jour du pannel
            update();

            pacman.setCoordinates(new Coordinate(nextPosX,nextPosY).boardToPosXY());
            pacman.repaint();
        }
    }

    //Fonction du mouvement des phantomes
    private void movePhantom(Phantome phantome) {
        
        int[] nextCase = moveEntity(phantome.getCoordinates().posXYToBoard().getX(), phantome.getCoordinates().posXYToBoard().getY(), phantome.getCurrentDirection());
        int nextPosX = nextCase[0];
        int nextPosY = nextCase[1];

        if (labyrinthe.getBoard()[nextPosY][nextPosX] != 1) {
            phantome.setCoordinates(new Coordinate(nextPosX, nextPosY).boardToPosXY());
        } else {
            Random random = new Random();
            phantome.setcurrentDirection(Direction.values()[random.nextInt(4)]);
        }

        phantome.update();

        repaint();
    }


    //Fonction pour la verification de l'etat du jeu (Collisions/Fin de partie)
    public void check() {
        if (!(pacman.getState() instanceof state.pacman.InvisibleState)) {
            Phantome[] tabPhantoms = getPhantoms();
            for (int j = 0; j < tabPhantoms.length; j++) {
                //Si le pacman se trouve a la meme position du phantome
                if (pacman.getCoordinates().getX() == tabPhantoms[j].getCoordinates().getX() && pacman.getCoordinates().getY() == tabPhantoms[j].getCoordinates().getY()) {
                    //Si le fantome est en etat vulnerable il se fait manger donc reinitialisation
                    if (tabPhantoms[j].getVulnerable()) {
                        tabPhantoms[j].reset();
                    //Sinon c'est le fantome qui mange le pacman
                    } else {
                        reset();
                        pacman.decrementLives();

                        //Verifications des vies restantes
                        if (pacman.getLives() == 0) {
                            JOptionPane.showMessageDialog(null, "     Score: " + pacman.getScore() + "\n     You lose!");
                            System.exit(0);
                        }
                        break;
                    }
                }
            }
        }
        //Verification des points collecté
        if (pacman.getScore() == MAX_SCORE) {
            // You win
            JOptionPane.showMessageDialog(null, "You win!");
            System.exit(0);
        }
    }


    //Activation du super pacman
    public void superPac() {
        //Slow Fantomes
        movePhantomeTimer.setDelay(500);

        //Timer du pouvoir (7 secondes)
        new Timer(7000, e -> {
            movePhantomeTimer.setDelay(250);
            pacman.setState(new state.pacman.NormalState());
        }).start();
    }

    

    //Reinitialisation des objets
    public void reset() {
        pacman.reset();
        for (Phantome ghost : getPhantoms()) {
            ghost.reset();
        }
        movePhantomeTimer.stop();
        Timer waiting= new Timer(2000,e -> movePhantomeTimer.start());
        waiting.start();
    }

    //Dessin du Composant sur le JFrame
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //Changement de l'Arriere plan
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }



}
