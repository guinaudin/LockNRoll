package view;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.player.LeaderBoard;

/**classe affichant la fenetre de classement*/
public class LeaderBoardView extends JFrame
{
    private JLabel[] leaderBoardLabelArray;
    private LeaderBoard[] leaderBoardArray;
    
    public LeaderBoardView() {
        super("Leaderboard");
        leaderBoardArray = new LeaderBoard[10];
        leaderBoardLabelArray = new JLabel[10];
        build();
    }
    
    //methode initialisant la fentre de classement
    private void build() {
        int j = 0;
        //On donne une taille à notre fenêtre
        this.setSize(250,260);
        //On centre la fenêtre sur l'écran
        this.setLocationRelativeTo(null);
        //On interdit la redimensionnement de la fenêtre
        this.setResizable(true);
        this.setVisible(true);
        
        //on declare et on cree un nouveau panel
        JPanel boxPanel = new JPanel();
        //on utilise un BoxLayout sur l'axe des abscisses
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        //on cree du vide tout autours du panel
        boxPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boxPanel.setBackground(Color.WHITE);
        
        try {
            FileInputStream fichier = new FileInputStream("leaderboard.ser");
            if(fichier.available() != 0) {
                ObjectInputStream ois = new ObjectInputStream(fichier);
                leaderBoardArray = (LeaderBoard[])ois.readObject();
                for(int i = 0; i < leaderBoardLabelArray.length; i++) {
                    j = i + 1;
                    leaderBoardLabelArray[i] = new JLabel("<html>" + j + " | " + leaderBoardArray[i].getName() + " : " + leaderBoardArray[i].getScore() + "</html>");
                    boxPanel.add(Box.createVerticalStrut(5));
                    boxPanel.add(leaderBoardLabelArray[i]);
                }
                ois.close();
                
                //on ajoute le panel principal à la JFrame
                this.add(boxPanel);  
            }
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
    
    
    
