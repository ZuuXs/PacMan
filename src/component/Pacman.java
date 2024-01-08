package component;
import javax.swing.*;

import coordinate.*;
import observer.*;
import state.pacman.PacmanState;
import state.pacman.NormalState;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;

public class Pacman extends JComponent implements Observable{

    final private Color PACMAN_COLOR = Color.YELLOW;
    final private int PACMAN_SIZE = 20;
    final private int DEFAULT_X =12;
    final private int DEFAULT_Y = 19;
    
    private List<Observer> observers = new ArrayList<>();
    private PacmanState currentState;
    private int animationHelper=0;
    private Direction currentDirection;
    private boolean isVisible,superPac;
    private int lives, score, addLife, startAngle, arcAngle;
    private Coordinate coordinates;
    private Timer animationTimer;
    private String power;

    public Pacman() {
        //Constructeur de pacman
        coordinates = new Coordinate(DEFAULT_X, DEFAULT_Y).boardToPosXY();
        score = 0;
        lives = 3;
        currentDirection = Direction.IDLE;
        currentState = new NormalState();
 

        //Animation du pacman
        animationTimer = new Timer(100, e -> {
            animationHelper = (animationHelper + 1) % 4;
            repaint();
        });
        animationTimer.start();
    }

    


    //Getters
    public int getScore() {
        return score;
    }


    public int getLives() {
        return lives;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
    
    public Coordinate getCoordinates() {
        return coordinates;
    }


    public int getPacmanSize() {
        return PACMAN_SIZE;
    }

    public Color getPacmanColor() {
        return PACMAN_COLOR;
    }

    public PacmanState getState() {
        return currentState;
    }

    public String getPower() {
        return power;
    }

    public Boolean getSuperPac() {
        return superPac;
    }
    
    public Boolean getVisible(){
        return isVisible;
    }


    //Setters
    public void setState(PacmanState state) {
        this.currentState = state;
        notifyObservers();
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }


    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public void setSuperPac(boolean superPac) {
        this.superPac = superPac;
    }


    public void setDirection(Direction direction) {
        this.currentDirection = direction;
    }

    public void resetAnimationHelper() {
        this.animationHelper = 0;
    }

    public void update() {
        currentState.update(this);
    }

    public void addScore(int score) {
        this.score += score;
        addLife += score;

        //Calcul des vies suplémentaires
        if (addLife >= 5000) {
            addLife -= 5000;
            lives++;
        }
    }
    
    public void decrementLives() {
        lives--;
    }

    //Reinitialisation du Pacman
    public void reset() {
        currentState = new NormalState();
        currentDirection = Direction.IDLE;
        coordinates = new Coordinate(DEFAULT_X, DEFAULT_Y).boardToPosXY();
        repaint();
    }

    // Observable interface methods
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.updateObserver(this);
        }
    }
    
    private void calculateRenderParameters() {

        // Calculate startAngle and arcAngle based on direction and animation helper
        startAngle = 0;
        arcAngle = 360;

        if (currentDirection != Direction.IDLE && animationHelper > 1) {
            switch (currentDirection) {
                case UP:
                    startAngle = 135;
                    arcAngle = 270;
                    break;
                case DOWN:
                    startAngle = 315;
                    arcAngle = 270;
                    break;
                case LEFT:
                    startAngle = 225;
                    arcAngle = 270;
                    break;
                case RIGHT:
                    startAngle = 45;
                    arcAngle = 270;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        calculateRenderParameters(); // Calculate the rendering parameters
        currentState.render(this, g,startAngle,arcAngle);
    }


}
