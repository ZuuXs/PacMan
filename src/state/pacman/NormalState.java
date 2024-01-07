package state.pacman;
import java.awt.Graphics;


import component.Pacman;

public class NormalState implements PacmanState {
    

    @Override
    public void update(Pacman pacman) {
        pacman.setVisible(true);
        pacman.setSuperPac(false);
        pacman.setPower("");
    }

    @Override
    public void render(Pacman pacman, Graphics g, int startAngle, int arcAngle){
        g.setColor(pacman.getPacmanColor()); // Use PACMAN_COLOR from Pacman class
        g.fillArc(pacman.getCoordinates().getX(), pacman.getCoordinates().getY(), pacman.getPacmanSize(), pacman.getPacmanSize(), startAngle, arcAngle);
    }
    
}
