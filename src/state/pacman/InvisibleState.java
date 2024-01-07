package state.pacman;
import java.awt.Color;
import java.awt.Graphics;

import component.Pacman;

public class InvisibleState implements PacmanState {

    private long invisibilityEndTime;

    public InvisibleState() {
        // Initialize invisibility end time
        invisibilityEndTime = System.currentTimeMillis() + 10000; // 10 seconds from now
    }

    @Override
    public void update(Pacman pacman) {
        pacman.setVisible(false);
        pacman.setPower("                                                   ~~InvisibilitY~~");
        System.out.println("BOOOOOO");
        pacman.repaint();

        if (System.currentTimeMillis() >= invisibilityEndTime) {
            pacman.setState(new NormalState());
        }
    }
    
    @Override
    public void render(Pacman pacman, Graphics g, int startAngle, int arcAngle) {
        g.setColor(new Color(255, 255, 0, 128)); // Semi-transparent yellow
        g.fillArc(pacman.getCoordinates().getX(), pacman.getCoordinates().getY(), pacman.getPacmanSize(), pacman.getPacmanSize(), startAngle, arcAngle);
    }
    
}
