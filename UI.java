import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UI {

	public UI() {

        f = new JFrame("OTHELLO");
        l = new JButton[4][4];
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p1=new JPanel();
        f.setResizable(false);
        p1.setLayout(new GridLayout(4,4));
        f.setSize(400,400);
        f.setLocationRelativeTo(null);
        image0 = new ImageIcon("dark.png");
        image1 = new ImageIcon("light.png");


        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                l[i][j]= new JButton();
                l[i][j].setSize(50,50);
                l[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                l[i][j].setEnabled(true);
                l[i][j].setBackground(new Color(0, 128, 0));
                l[i][j].setOpaque(true);
                p1.add(l[i][j]);
            }
        }

        f.add(p1);
    }

    JFrame f;
    JButton l[][];
    static int p=0;
    Icon image0,image1;
    JPanel p1;

    public void updateGUI(Board board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(board.board[i][j]==1) {
                    l[i][j].setIcon(image0);
                    l[i][j].setEnabled(true);
                } else if(board.board[i][j]==-1) {
                    l[i][j].setIcon(image1);
                    l[i][j].setEnabled(true);
                } else {

                }
            }
        }
    }
}
