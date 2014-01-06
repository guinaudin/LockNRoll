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

/**classe qui demande les infos du joueur*/
public class IdView extends JFrame implements KeyListener
{
    //declaration des attributs Swing
    private JTextField nameField;
    
    private String playerName;
    private Player player;
    private LeaderBoard[] tempLeaderBoardArray;
    private Boolean moveLeaderBoard;
    
    public IdView(Player player)
    {
        //on appel le constructeur mere et on set le titre de la fenetre
        super("You Lost");
        //initialisation des attributs
        this.player = player;
        playerName = "";
        tempLeaderBoardArray = new LeaderBoard[10];
        moveLeaderBoard = false;
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
        JPanel boxPanel = new JPanel();
        //on utilise un BoxLayout sur l'axe des abscisses
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.X_AXIS));
        //on cree du vide tout autours du panel
        boxPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //on declare et on cree un JLabel
        JLabel labelName = new JLabel("Player Name : ");
        //on instancie un JTextField
        nameField = new JTextField();
        //on le rend evenementiel
        nameField.addKeyListener(this);
        //on lui donne une dimension
        nameField.setPreferredSize(new Dimension(150,30));
        //on peut rentrer jusqu'a 15 caracteres
        nameField.setColumns(15);
        //on set une police
        Font police = new Font("Arial", Font.BOLD, 14);
        nameField.setFont(police);
        //on ajoute les differents elements
        boxPanel.add(labelName);
        boxPanel.add(Box.createHorizontalStrut(10));
        boxPanel.add(nameField);
        panel.add(boxPanel);
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
            playerName = ("".equals(playerName))?(nameField.getText()) : "Unamed Player";
            //on recupere le texte dans le JTextField pour le nom du joueur
            player.setName(playerName);
            
            LeaderBoard leaderBoard = new LeaderBoard(player.getName(), player.getScore());
            
            try {
                FileInputStream fichierOis = new FileInputStream("leaderboard.ser");
                if(fichierOis.available() != 0) {
                    ObjectInputStream ois = new ObjectInputStream(fichierOis);
                    tempLeaderBoardArray = (LeaderBoard[])ois.readObject();
                    ois.close();
                }
                else {
                    for(int i = 0; i < tempLeaderBoardArray.length; i++)
                        tempLeaderBoardArray[i] = new LeaderBoard("Unamed Player", 0);
                }

                for(int i = tempLeaderBoardArray.length; i > 0 ; i--) {
                    if(leaderBoard.getScore() >= tempLeaderBoardArray[i-1].getScore() && !moveLeaderBoard) {
                        tempLeaderBoardArray[i-1] = leaderBoard;
                        moveLeaderBoard = true;
                    }
                    else if(leaderBoard.getScore() >= tempLeaderBoardArray[i-1].getScore() && moveLeaderBoard) {
                        if(i != 0) {
                            tempLeaderBoardArray[i] = tempLeaderBoardArray[i-1];
                            tempLeaderBoardArray[i-1] = leaderBoard;
                        }
                    }
                }
            }
            catch (ClassNotFoundException ex) {
                Logger.getLogger(IdView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(IdView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(IdView.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                if(moveLeaderBoard) {
                    FileOutputStream fichierOos = new FileOutputStream("leaderboard.ser");
                    ObjectOutputStream oos = new ObjectOutputStream(fichierOos);
                    oos.writeObject(tempLeaderBoardArray);
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
