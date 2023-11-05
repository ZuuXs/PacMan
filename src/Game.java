import javax.swing.*;
import java.awt.*;
public class Game extends JPanel {
    private Pacman pacman;
    private Labyrinthe labyrinthe;
    private Phantome cyanPhantom,orangePhantom,redPhantom,pinkPhantom;
    private JLabel scoreLabel,livesLabel,stateLabel;
    public Game() {
        //Constructeur Game

        //Instance des objets du jeu
        labyrinthe = new Labyrinthe();
        pacman = new Pacman(labyrinthe, this);
        cyanPhantom = new Phantome(Color.CYAN,11,12,labyrinthe);
        orangePhantom = new Phantome(Color.ORANGE,12,12,labyrinthe);
        redPhantom = new Phantome(Color.RED,13,12,labyrinthe);
        pinkPhantom = new Phantome(Color.PINK,14,12,labyrinthe);

        //Creation du paneau
        setLayout(new BorderLayout());
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 500));

        //Definition des limites
        labyrinthe.setBounds(0, 0, 500, 500);
        pacman.setBounds(0, 0, 500, 500);
        cyanPhantom.setBounds(0,0,500,500);
        orangePhantom.setBounds(0,0,500,500);
        redPhantom.setBounds(0,0,500,500);
        pinkPhantom.setBounds(0,0,500,500);

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
    }

    //Recuperation des phantomes
    public Phantome[] getPhantoms(){
        Phantome[] tab=new Phantome[]{cyanPhantom, orangePhantom, redPhantom, pinkPhantom};
        return tab;
    }

    //Mise a jour du score et des vies
    public void updateScore() {
        scoreLabel.setText(String.valueOf(pacman.getScore()));
    }
    public void updateLives() {
        livesLabel.setText("Lives: "+String.valueOf(pacman.getLives()));
    }
    public void updateState() { stateLabel.setText(pacman.getPower()); }

    //Reinitialisation des objets
    public void reset(){
        pacman.reset();
        cyanPhantom.reset();
        orangePhantom.reset();
        redPhantom.reset();
        pinkPhantom.reset();
    }

    //Activation du super pacman
    public void superPac(){
        //Fantomes vulnerables
        cyanPhantom.setVulnerable();
        orangePhantom.setVulnerable();
        redPhantom.setVulnerable();
        pinkPhantom.setVulnerable();

        //Timer du pouvoir (7 secondes)
        new Timer(7000, e -> {
            cyanPhantom.setUnvulnerable();
            orangePhantom.setUnvulnerable();
            redPhantom.setUnvulnerable();
            pinkPhantom.setUnvulnerable();
            pacman.deactivateOrangeEffect();
            this.updateState();
            repaint();
        }).start();
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
