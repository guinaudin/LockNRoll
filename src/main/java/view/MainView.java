package view;

import controller.AbstractControler;
import java.awt.Color;
import model.board.Board;
import model.Game;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.Constant;
import observer.Observer;

/**classe qui met en place la page d'acceuil*/
public class MainView extends JFrame implements Observer, ActionListener
{  
    //declaration d'un objet Desktop et d'un url
    private Game boardModel;
    private Board board;
    private Desktop desktop = null;
    private java.net.URI url;
    private JMenuItem newGameMenuItem;
    private JMenuItem leaderBoardMenuItem;
    private JMenuItem quitMenuItem;
    private JMenuItem onMenuItem;
    private JMenuItem offMenuItem;
    private JMenuItem chooseSoundMenuItem;
    private JMenu miscellaneousMenu;
    private JMenuItem websiteMenuItem;
    private JMenuItem rulesMenuItem;
    private AbstractControler controler;
    private JPanel mainPanel;
    private JPanel globalPanel;
    private JButton[][] jButtonDiceBoard;
    private JButton[] jButtonRolledDice;
    private JLabel imageMainView;
  
    /**constructeur*/
    public MainView(AbstractControler controler) 
    {
        //on appel le constructeur mere et on set le titre de la fenetre
        super("Lock N Roll");
        this.controler = controler;
        //On initialise notre fen�tre
        build();
        this.setVisible(true);
    }
    
    /**methode initialisant la fenetre*/
    private void build()
    {
        //On donne une taille � notre fen�tre
        setSize(600,450);
        //On centre la fen�tre sur l'�cran
        setLocationRelativeTo(null);
        //On interdit la redimensionnement de la fen�tre
        setResizable(true); 
        //On dit � l'application de se fermer lors du clic sur la croix
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jButtonDiceBoard = new JButton[Constant.SIZE][Constant.SIZE];
        jButtonRolledDice = new JButton[Constant.SIZE];
        
        //creation de la menuBar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        //creation de la premiere icone de la menuBar
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        
        //creation du premier element, un sous-menu
        newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.addActionListener(this);
        gameMenu.add(newGameMenuItem);
        
        //creation de la premiere icone de la menuBar
        leaderBoardMenuItem = new JMenuItem("Leaderboard");
        leaderBoardMenuItem.addActionListener(this);
        gameMenu.add(leaderBoardMenuItem);
        
        //creation de la premiere icone de la menuBar
        quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(this);
        gameMenu.add(quitMenuItem);
        
        //creation de la seconde icone de la menuBar
        JMenu optionMenu = new JMenu("Options");
        menuBar.add(optionMenu);
        
        //creation du premier element, un sous-menu
        JMenu soundSubMenu = new JMenu("Sounds");
        onMenuItem = new JMenuItem("On");
        onMenuItem.addActionListener(this);
        soundSubMenu.add(onMenuItem);
        offMenuItem = new JMenuItem("Off");
        offMenuItem.addActionListener(this);
        soundSubMenu.add(offMenuItem);       
        optionMenu.add(soundSubMenu);
        
        //creation du second element
        chooseSoundMenuItem = new JMenuItem("Choose Sound");
        chooseSoundMenuItem.addActionListener(this);
        optionMenu.add(chooseSoundMenuItem);
        
        //creation de la troisieme icone de la menuBar
        miscellaneousMenu = new JMenu("Miscellaneous");
        menuBar.add(miscellaneousMenu);
        
        //creation du 1er element
        websiteMenuItem = new JMenuItem("Website");
        websiteMenuItem.addActionListener(this);
        miscellaneousMenu.add(websiteMenuItem);
        
        //creation du 2nd element
        rulesMenuItem = new JMenuItem("Rules");
        rulesMenuItem.addActionListener(this);
        miscellaneousMenu.add(rulesMenuItem);
        
        //on ajoute une image de fond dans la JFrame
        
        //JLabel imageMainView = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("ressources/images/LockNRoll.jpg"))));
        //System.out.println("path = " + getClass().getClassLoader().getResource(""));
        imageMainView = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/LockNRoll.jpg")));
        globalPanel = new JPanel();
        globalPanel.add(imageMainView);
        globalPanel.add(this.buildPanel());
        this.setContentPane(globalPanel);
    }
    
    /**methode privée ajoutant les boutants gerant des evenements*/
    private JPanel buildPanel()
    {
        //creation d'un objet JPanel
        mainPanel = new JPanel();
        //layout par defaut
        mainPanel.setLayout(new GridLayout(5, 4, 5, 5));
        //on rend le fond blanc
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setVisible(true);
                        
        //creation des JButtons installer sur le panel en les rendant evenementiel
        for(int i = 0; i < Constant.SIZE; i++) {
            for(int j = 0; j < Constant.SIZE; j++) {
                jButtonDiceBoard[i][j] = new JButton("");
                jButtonDiceBoard[i][j].addActionListener(this);
                jButtonDiceBoard[i][j].setMaximumSize(new Dimension(50,50));
                jButtonDiceBoard[i][j].setMinimumSize(new Dimension(50,50));
                jButtonDiceBoard[i][j].setPreferredSize(new Dimension(50,50));
                try {
                  Image img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/Empty Die.jpg"));
                  jButtonDiceBoard[i][j].setIcon(new ImageIcon(img));
                } 
                catch (IOException ex) {
                }
                mainPanel.add(jButtonDiceBoard[i][j]);
            }
        }
        return mainPanel;
    }
    
     /**methode realisant les actions lorsqu'il y a un evenement*/
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //on recupere la source du clic
        Object source = (JMenuItem)(e.getSource());
        
        //si l'utilisateur clic sur "rules"
        if(source == newGameMenuItem)
        {
            getContentPane().removeAll();
            getContentPane().add(this.buildPanel());//Adding to content pane, not to Framev
            validate();
            repaint();
            //Mise en place de l'ui avec le plateau de jeu
            //controler.startNewGame();
        }
        //si l'utilisateur clic sur "Website"
        else if(source == websiteMenuItem)
        {
            //tant qu'il n'y a pas d'exception
            try 
            {
                //creation de l'url � partir du lien internet
                url = new java.net.URI("http://jayisgames.com/games/lock-n-roll/");
                
                //si le bureau permet le chargement du lien
                if(Desktop.isDesktopSupported())
                {
                    //on recupere le desktop
                    desktop = Desktop.getDesktop();
                    //puis on charge le lien
                    desktop.browse(url);
                }
            }
            catch (Exception ex) 
            {
                System.out.println(ex.getMessage());
            }
        }
        //si l'utilisateur clic sur "rules"
        else if(source == rulesMenuItem)
        {
            try 
            {
                if (Desktop.isDesktopSupported())     
                {
                    //on ouvre le pdf sur le bureau
                    Desktop.getDesktop().open(new File(getClass().getClassLoader().getResource("LockNRoll.pdf").getPath()));
                }
            } 
            catch (IOException ex) 
            {
                System.out.println(ex.getMessage());
            }
        }
        //si l'utilisateur clic sur "quitter"
        else if(source == quitMenuItem)
        {
            //on ferme la fenetre
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            //on quitte le programme
            System.exit(0);
        }
    }

    public void update(Board board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
