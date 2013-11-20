package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.player.LeaderBoard;
import model.player.Player;

/**classe affichant la fenetre de classement des joueurs en fonction du cash et du green*/
public class LeaderBoardView extends JFrame
{
    //declaration des attributs
    private JPanel jps;
    private JLabel label;
    
    /**consructeur*/
    public LeaderBoardView()
    {
        //on appel le constructeur mere et on initialise le titre de la fenetre
        super("Best Player");
        //on cree la fenetre avec ses composants
        build();
    }
    
    //methode initialisant la fentre de classement
    private void build()
    {
        //On donne une taille à notre fenêtre
        this.setSize(300,90);
        //On centre la fenêtre sur l'écran
        this.setLocationRelativeTo(null);
        //On interdit la redimensionnement de la fenêtre
        this.setResizable(true);
        this.setVisible(true);
        
        //creation d'un objet JPanel
        JPanel panel = new JPanel();
        //on utilise un gridLayout
        panel.setLayout(new GridLayout(1,0));
        //on rend le fond blanc
        panel.setBackground(Color.WHITE);
        
        //on declare et on cree un nouveau panel
        JPanel boxPanel1 = new JPanel();
        //on utilise un BoxLayout sur l'axe des abscisses
        boxPanel1.setLayout(new BoxLayout(boxPanel1, BoxLayout.X_AXIS));
        //on cree du vide tout autours du panel
        boxPanel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boxPanel1.setBackground(Color.WHITE);

        try {
            FileInputStream fichier = new FileInputStream("leaderboard.ser");
            ObjectInputStream ois = new ObjectInputStream(fichier);
            LeaderBoard leaderBoard = (LeaderBoard)ois.readObject();
            label = new JLabel("<html>Name : " + leaderBoard.getName() + "<br>Score : " + leaderBoard.getScore() + "</html>");
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        //on ajoute les differents elements
        boxPanel1.add(label);
        panel.add(boxPanel1);
        //on ajoute le panel principal à la JFrame
        this.add(panel);  
    }
}
    
    
    
