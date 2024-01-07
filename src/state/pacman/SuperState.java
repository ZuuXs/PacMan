package state.pacman;
import java.awt.Color;
import java.awt.Graphics;

import component.Pacman;

public class SuperState implements PacmanState{

    @Override
    public void update(Pacman pacman) {
        //Activation du Super Pacman
        pacman.setSuperPac(true);
        pacman.setPower("                                                   ~~SUPER PACMAN~~");
        System.out.println("SUPERDUPER!!!!");
        //Mettre la partie en etat superPac (L'inversement des roles)
        pacman.repaint();
    }

    @Override
    public void render(Pacman pacman, Graphics g, int startAngle, int arcAngle) {
        // Draw a glow effect
        g.setColor(new Color(255, 165, 0, 128)); // Semi-transparent orange for glow
        g.fillArc(pacman.getCoordinates().getX() - 5, pacman.getCoordinates().getY() - 5, pacman.getPacmanSize() + 10, pacman.getPacmanSize() + 10, startAngle, arcAngle);

        g.setColor(Color.ORANGE); // Super state color
        g.fillArc(pacman.getCoordinates().getX(), pacman.getCoordinates().getY(), pacman.getPacmanSize(), pacman.getPacmanSize(), startAngle, arcAngle);
    }
}
