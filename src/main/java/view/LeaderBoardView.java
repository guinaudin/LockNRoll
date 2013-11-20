package view;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
        super("Leaderboard");
        //on cree la fenetre avec ses composants
        build();
    }
    
    //methode initialisant la fentre de classement
    private void build()
    {
        //taille de la fenetre
        this.setSize(400,517);
        //position de la fenetre
        this.setLocation(5,5);
        //elle n'est pas agrandissable
        this.setResizable(false); 
        //elle se ferme si on appuie sur la croix
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //creation d'un JPanel
        jps=new JPanel();       
        
        try {
            FileInputStream fichier = new FileInputStream(getClass().getClassLoader().getResource("classement/leaderboard.ser").getPath());
            ObjectInputStream ois = new ObjectInputStream(fichier);
            Player player = (Player)ois.readObject();
            label = new JLabel("Name : " + player.getName());
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        jps.add(label);
        //on cree une JScrollPane
        JScrollPane scrollbar = new JScrollPane(jps);
        //ce JScrollPane fonctionne de maniere infiniment verticale
        scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //on met un fond blanc
        jps.setBackground(Color.white);
        //on met la scrollBar dans le JPanel
        this.getContentPane().add(scrollbar);
    }
}
    
    
    
