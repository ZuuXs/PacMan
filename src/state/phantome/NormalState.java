package state.phantome;

import java.awt.Color;
import java.awt.Graphics;

import component.Phantome;

public class NormalState implements PhantomeState {

    @Override
    public void update(Phantome phantome) {
        phantome.setVulnerable(false);
        phantome.setDelay(250);
    }

    @Override
    public void render(Phantome phantome, Graphics g) {
        //body color
        g.setColor(phantome.getColor());
        //body
        phantome.ghostModel(g);
        //pupils
        g.setColor(Color.BLACK);
        g.fillOval(phantome.getCoordinates().getX() + 6, phantome.getCoordinates().getY() + 9, 2, 2);
        g.fillOval(phantome.getCoordinates().getX() + 11, phantome.getCoordinates().getY() + 9, 2, 2);
    }
    
}
