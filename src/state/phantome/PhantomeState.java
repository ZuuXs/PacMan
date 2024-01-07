package state.phantome;

import java.awt.Graphics;

import component.Phantome;

public interface PhantomeState {
    void update(Phantome phantome);
    void render(Phantome phantome, Graphics g);
}


