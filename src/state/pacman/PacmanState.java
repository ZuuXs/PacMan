package state.pacman;
import java.awt.Graphics;

import component.Pacman;

public interface PacmanState {
    void update(Pacman pacman);
    void render(Pacman pacman, Graphics g, int startAngle, int arcAngle);
}
