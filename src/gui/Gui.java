package gui;

import javax.swing.*;
import component.Game;
import component.Pacman;
import coordinate.Direction;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.*;

public class Gui {
    public Gui() {
        JFrame frame = new JFrame("PacMan");
        Game game= new Game();
        Pacman pacman= game.getPacman();
        frame.setContentPane(game);
        frame.setSize(516,549);
        frame.setBackground(Color.BLACK);
        frame.setLocation(700, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if(!(pacman.getCurrentDirection()==Direction.LEFT)){
                            pacman.setDirection(Direction.LEFT);
                            pacman.resetAnimationHelper();
                            System.out.println("left");
                            pacman.repaint();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(!(pacman.getCurrentDirection()==Direction.RIGHT)){
                            pacman.setDirection(Direction.RIGHT);
                            pacman.resetAnimationHelper();
                            System.out.println("right");
                            pacman.repaint();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if(!(pacman.getCurrentDirection()==Direction.UP)){
                            pacman.setDirection(Direction.UP);
                            pacman.resetAnimationHelper();
                            System.out.println("up");
                            pacman.repaint();
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(!(pacman.getCurrentDirection()==Direction.DOWN)){
                            pacman.setDirection(Direction.DOWN);
                            pacman.resetAnimationHelper();
                            System.out.println("down");
                            pacman.repaint();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
