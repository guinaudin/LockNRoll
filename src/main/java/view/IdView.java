package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import model.player.LeaderBoard;
import model.player.Player;

/**classe qui demande à l'utilisateur les elements nescessaire pour jouer online ou offline*/
public class IdView extends JFrame implements KeyListener
{
    //declaration des attributs Swing
    private JTextField jtf1;
    
    private String playerName;
    private Player player;
    private LeaderBoard tempLeaderBoard;
    
    /**constructeur*/
    public IdView(Player player)
    {
        //on appel le constructeur mere et on set le titre de la fenetre
        super("You Lost");
        //initialisation des attributs
        this.player = player;
        playerName = "";
        //appel de la methode iitialisant la fenetre
        build();
    }
    
    /**methode initialisant la fenetre*/
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

        //on declare et on cree un JLabel
        JLabel labelName = new JLabel("Player Name : ");
        //on instancie un JTextField
        jtf1 = new JTextField();
        //on le rend evenementiel
        jtf1.addKeyListener(this);
        //on lui donne une dimension
        jtf1.setPreferredSize(new Dimension(150,30));
        //on peut rentrer jusqu'a 15 caracteres
        jtf1.setColumns(15);
        //on set une police
        Font police = new Font("Arial", Font.BOLD, 14);
        jtf1.setFont(police);
        //on ajoute les differents elements
        boxPanel1.add(labelName);
        boxPanel1.add(Box.createHorizontalStrut(10));
        boxPanel1.add(jtf1);
        panel.add(boxPanel1);
        //on ajoute le panel principal à la JFrame
        this.add(panel);
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
    }

     /**methode realisant les actions lorsqu'il y a un evenement clavier*/
    @Override
    public void keyPressed(KeyEvent e) 
    {	
        //si l'utilisateur appuie sur ENTER
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            playerName = ("".equals(playerName)) ? (jtf1.getText()) : "Unnamed Player";
            //on recupere le texte dans le JTextField pour le nom du joueur
            player.setName(playerName);
            
            LeaderBoard leaderBoard = new LeaderBoard(player.getName(), player.getScore());
            
            try {
                FileInputStream fichierOis = new FileInputStream("leaderboard.ser");
                ObjectInputStream ois = new ObjectInputStream(fichierOis);
                tempLeaderBoard = (LeaderBoard)ois.readObject();
                ois.close();
            }
            catch (ClassNotFoundException ex) {
                Logger.getLogger(IdView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(IdView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(IdView.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
            
            try {
                
                if(tempLeaderBoard.getScore() <= player.getScore()) {
                    FileOutputStream fichierOos = new FileOutputStream("leaderboard.ser");
                    ObjectOutputStream oos = new ObjectOutputStream(fichierOos);
                    oos.writeObject(leaderBoard);
                    oos.flush();
                    oos.close();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(IdView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(IdView.class.getName()).log(Level.SEVERE, null, ex);
            }

            //on ferme la fenetre automatiquement
            this.dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
    }
}
