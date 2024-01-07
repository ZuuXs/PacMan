package state.phantome;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import component.Phantome;

public class VulnerableState implements PhantomeState{

    @Override
    public void update(Phantome phantome) {
        phantome.setVulnerable(true);
        phantome.setDelay(500);
    }

    @Override
    public void render(Phantome phantome, Graphics g) {
        //body color
        g.setColor(new Color(100, 100, 255));
        //body
        phantome.ghostModel(g);
        //pupils
        g.setColor(Color.BLACK);
        Random random = new Random();
        int leftPupilX = phantome.getCoordinates().getX() + 5 + random.nextInt(3);
        int leftPupilY = phantome.getCoordinates().getY() + 8 + random.nextInt(3);
        int rightPupilX = phantome.getCoordinates().getX() + 10 + random.nextInt(3);
        int rightPupilY = phantome.getCoordinates().getY() + 8 + random.nextInt(3);
        g.fillOval(leftPupilX, leftPupilY, 2, 2);
        g.fillOval(rightPupilX, rightPupilY, 2, 2);
    }
    
}
