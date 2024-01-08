package component;
import javax.swing.*;

import coordinate.*;
import observer.*;
import state.pacman.PacmanState;
import state.pacman.SuperState;
import state.phantome.NormalState;
import state.phantome.PhantomeState;
import state.phantome.VulnerableState;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Phantome extends JComponent implements Observer {
    private final Coordinate DEFAULT_COORDINATE;

    private Direction currentDirection;
    private Boolean vulnerable;
    private int delay;
    private Random random;
    private Timer moveTimer;
    private Color ghostColor;
    private PhantomeState currentState;
    private Coordinate coordinates;


    public Phantome(Color c, int defaultX, int defaultY) {
        //Constructeur du Fantome

        this.ghostColor = c;
        vulnerable = false;
        currentState = new NormalState();

        DEFAULT_COORDINATE = new Coordinate(defaultX, defaultY).boardToPosXY();

        coordinates = DEFAULT_COORDINATE;

        random = new Random();
        currentDirection = Direction.values()[random.nextInt(4)];

        delay = 250;
    }

    //Getters
    public Coordinate getCoordinates() {
        return coordinates;
    }

    
    public Boolean getVulnerable() {
        return vulnerable;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public Color getColor() {
        return ghostColor;
    }

    public Timer getMoveTimer() {
        return moveTimer;
    }

    public int getDelay() {
        return delay;
    }

    //Setters
    public void setVulnerable(Boolean t) {
        vulnerable = t;
    }

    public void setState(PhantomeState state) {
        this.currentState = state;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public void setcurrentDirection(Direction direction) {
        currentDirection = direction;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void update() {
        currentState.update(this);
    }
    
    public void reset() {
        currentState = new NormalState();
        currentState.update(this);
        coordinates = DEFAULT_COORDINATE;
    }

    
    // Observer interface method
    @Override
    public void updateObserver(Observable o) {
        if (o instanceof Pacman){
            PacmanState pacmanState= ((Pacman)o).getState();
        
            if (pacmanState instanceof SuperState) {
                setState(new VulnerableState());
            } else {
                setState(new NormalState());
            }
        }
    }

    public void ghostModel(Graphics g) {
        // Draw the rounded top part
        g.fillArc(coordinates.getX(), coordinates.getY(), 20, 20, 0, 180);
        // Draw the wavy bottom part
        int[] xPoints = {coordinates.getX(), coordinates.getX() + 4, coordinates.getX() + 8, coordinates.getX() + 12, coordinates.getX() + 16, coordinates.getX() + 20, coordinates.getX() + 20, coordinates.getX()};
        int[] yPoints = {coordinates.getY() + 20, coordinates.getY() + 15, coordinates.getY() + 20, coordinates.getY() + 15, coordinates.getY() + 20, coordinates.getY() + 15, coordinates.getY() + 10, coordinates.getY() + 10};
        g.fillPolygon(xPoints,yPoints, xPoints.length);
        // Eyes
        g.setColor(Color.WHITE);
        g.fillOval(coordinates.getX() + 5, coordinates.getY() + 8, 4, 4);
        g.fillOval(coordinates.getX() + 10, coordinates.getY() + 8, 4, 4);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        currentState.render(this, g);
    }
}
