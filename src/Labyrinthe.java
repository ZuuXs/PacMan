import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Labyrinthe extends JComponent {
    private int[][] board;

    public Labyrinthe() {
        //Constructeur du Labyrinthe

        //0=vide, 1=mur, 2=pacpoint, 3=pacpoint vert, 4=pacpoint violet, 5=pacpoint orange
        board = new int[][]{
                /*01*/{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                /*02*/{1, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 5, 1},
                /*03*/{1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1},
                /*04*/{1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1},
                /*05*/{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
                /*06*/{1, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2, 1},
                /*07*/{1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 1, 1, 1, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1},
                /*08*/{1, 2, 1, 1, 1, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 1, 1, 1, 2, 1},
                /*09*/{1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2, 2, 1, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2, 4, 1},
                /*10*/{1, 1, 1, 1, 1, 1, 2, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 2, 1, 1, 1, 1, 1, 1},
                /*11*/{1, 1, 1, 1, 1, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 1, 1, 1, 1, 1},
                /*12*/{0, 0, 0, 0, 0, 0, 2, 2, 0, 1, 1, 0, 0, 0, 1, 1, 0, 2, 2, 0, 0, 0, 0, 0, 0},
                /*13*/{0, 0, 0, 0, 0, 0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 1, 0, 2, 2, 0, 0, 0, 0, 0, 0},
                /*14*/{1, 1, 1, 1, 1, 1, 2, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 2, 1, 1, 1, 1, 1, 1},
                /*15*/{1, 1, 1, 1, 1, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 1, 1, 1, 1, 1},
                /*16*/{1, 4, 2, 2, 2, 2, 2, 1, 2, 2, 1, 1, 1, 1, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1},
                /*17*/{1, 2, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 1},
                /*18*/{1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1},
                /*19*/{1, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2, 1},
                /*20*/{1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1},
                /*21*/{1, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 1, 1, 1, 1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 1},
                /*22*/{1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1},
                /*23*/{1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1},
                /*24*/{1, 5, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 1},
                /*25*/{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
    }


    //Recuperation tu tableau
    public int[][] getBoard() {return board;}
    //Recuperation de la longueur du tableau
    public int getLength(){
        return board.length;
    }
    //Changement d'une case du tableau
    public void setCase(int x, int y, int newValue){
        board[x][y]=newValue;
    }

    //Effet du pacpoint vert
    public void greenEffect(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                //Parcours de tout les murs du jeu
                if(board[i][j] == 1){
                    //Choisir aléatoirement de laisser ou enlever le mur
                    Random random = new Random();
                    if(random.nextBoolean()){
                        board[i][j] = 0;
                    }
                }
            }
        }
        repaint();
    }

    //Dessin du Composant
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                //Dessin des murs (20x20 bleu)
                if (board[i][j] == 1) {
                    g.setColor(Color.BLUE);
                    g.fillRect(j*20,i*20,20,20);
                }
                //Dessin des pacpoints (5x5 cercle jaune)
                else if(board[i][j]==2){
                    g.setColor(Color.YELLOW);
                    g.fillOval(j*20+9,i*20+9,5,5);
                }
                //Dessin des pacpoints verts (15x15 cercle vert)
                else if(board[i][j]==3){
                    g.setColor(Color.GREEN);
                    g.fillOval(j*20+3,i*20+3,15,15);
                }
                //Dessin des pacpoints violets (15x15 cercle violet)
                else if(board[i][j] == 4) {
                    g.setColor(Color.MAGENTA);
                    g.fillOval(j * 20 + 3, i * 20 + 3, 15, 15);
                }
                //Dessin des pacpoints oranges (15x15 cercle orange)
                else if(board[i][j] == 5) {
                    g.setColor(Color.ORANGE);
                    g.fillOval(j * 20 + 3, i * 20 + 3, 15, 15);
                }
            }
        }
    }
}
