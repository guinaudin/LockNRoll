package view;

import controller.AbstractControler;
import java.awt.BorderLayout;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.Constant;
import model.dice.Dice;
import model.dice.DiceTypes;
import model.dice.GameDice;
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
    private JPanel boardPanel;
    private JPanel diceBoardPanel;
    private JPanel rolledDicePanel;
    private JPanel startPagePanel;
    private JButton[][] jButtonDiceBoard;
    private JButton[] jButtonRolledDice;
    private JLabel imageMainView;
  
    /**constructeur*/
    public MainView(AbstractControler controler) {
        super("Lock N Roll");
        this.controler = controler;
        this.buildFrame();
        this.buildMenuBar();
        
        this.setVisible(true);
    }
    
    private void buildFrame() {
        //On donne une taille � notre fen�tre
        this.setSize(600,450);
        //On centre la fen�tre sur l'�cran
        this.setLocationRelativeTo(null);
        //On interdit la redimensionnement de la fen�tre
        this.setResizable(true); 
        //On dit � l'application de se fermer lors du clic sur la croix
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        startPagePanel = new JPanel();
        imageMainView = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/LockNRoll.jpg")));
        startPagePanel.add(imageMainView);
        this.setContentPane(startPagePanel);
    }
    
    private void buildMenuBar() {
        //creation de la menuBar
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        
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
    }
    
    private void setNewContentPane(JPanel panel) {
        this.getContentPane().removeAll();
        this.getContentPane().add(panel);
        this.validate();
        this.repaint();
    }
    
    /**methode privée ajoutant les boutants gerant des evenements*/
    private JPanel buildBoardPanel() {
        boardPanel = new JPanel();
        jButtonDiceBoard = new JButton[Constant.SIZE][Constant.SIZE];
        jButtonRolledDice = new JButton[Constant.SIZE];
        //creation d'un objet JPanel
        diceBoardPanel = new JPanel();
        //layout par defaut
        diceBoardPanel.setLayout(new GridLayout(5, 4, 5, 5));
                        
        //creation des JButtons installer sur le panel en les rendant evenementiel
        for(int i = 0; i < Constant.SIZE; i++) {
            for(int j = 0; j < Constant.SIZE; j++) {
                jButtonDiceBoard[i][j] = new JButton("");
                jButtonDiceBoard[i][j].addActionListener(this);
                jButtonDiceBoard[i][j].setMaximumSize(new Dimension(50,50));
                jButtonDiceBoard[i][j].setMinimumSize(new Dimension(50,50));
                jButtonDiceBoard[i][j].setPreferredSize(new Dimension(50,50));
          
                diceBoardPanel.add(jButtonDiceBoard[i][j]);
            }
        }
        
        //creation d'un objet JPanel
        rolledDicePanel = new JPanel();
        //layout par defaut
        rolledDicePanel.setLayout(new GridLayout(4, 1, 5, 5));
        
        for(int i = 0; i < Constant.SIZE; i++) {
            jButtonRolledDice[i] = new JButton("");
            jButtonRolledDice[i].addActionListener(this);
            jButtonRolledDice[i].setMaximumSize(new Dimension(50,50));
            jButtonRolledDice[i].setMinimumSize(new Dimension(50,50));
            jButtonRolledDice[i].setPreferredSize(new Dimension(50,50));
            
            rolledDicePanel.add(jButtonRolledDice[i]);
        }
        
        
        boardPanel.add(rolledDicePanel,BorderLayout.WEST);
        boardPanel.add(diceBoardPanel,BorderLayout.CENTER);
        //this.add(scorePanel,BorderLayout.NORTH);
        
        return boardPanel;
    }
    
    public void rollDice() {
        
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
            this.setNewContentPane(this.buildBoardPanel());
            //Mise en place de l'ui avec le plateau de jeu
            controler.startNewGame();
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

    public void updateBoardDice(Board board) {
        Dice[][] diceBoard = board.getDiceBoard();
        
        for(int i = 0; i < Constant.SIZE; i++) {
            for(int j = 0; j < Constant.SIZE; j++) {
                try {
                    jButtonDiceBoard[i][j].setIcon(new ImageIcon(this.getDieImage(diceBoard[i][j])));
                }
                catch (IOException ex) {
                    Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void updateRolledDice(Board board) {
        GameDice[] rolledDice = board.getRolledDice();
        
        for(int i = 0; i < Constant.SIZE; i++) {
            try {
                jButtonRolledDice[i].setIcon(new ImageIcon(this.getDieImage(rolledDice[i])));
            } 
            catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private Image getDieImage(Dice die) throws IOException {
    Image img = null;
            
        if(die.getColor() == DiceTypes.Color.BLUE.getInt()) {
            if(die.getValue() == 1)
                img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/blue/Blue Die 1.jpg"));
            else if(die.getValue() == 2)
                img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/blue/Blue Die 2.jpg"));
            else if(die.getValue() == 3)
                img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/blue/Blue Die 3.jpg"));
            else if(die.getValue() == 4)
                img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/blue/Blue Die 4.jpg"));
        }
        else if(die.getColor() == DiceTypes.Color.GREEN.getInt()) {
            if(die.getValue() == 1)
                img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/green/Green Die 1.jpg"));
            else if(die.getValue() == 2)
                img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/green/Green Die 2.jpg"));
            else if(die.getValue() == 3)
                img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/green/Green Die 3.jpg"));
            else if(die.getValue() == 4)
                img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/green/Green Die 4.jpg"));
        }
        else if(die.getColor() == DiceTypes.Color.RED.getInt()) {
            if(die.getValue() == 1)
                img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/red/Red Die 1.jpg"));
            else if(die.getValue() == 2)
                img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/red/Red Die 2.jpg"));
            else if(die.getValue() == 3)
                img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/red/Red Die 3.jpg"));
            else if(die.getValue() == 4)
                img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/red/Red Die 4.jpg"));
        }
        else if(die.getColor() == DiceTypes.Color.YELLOW.getInt()) {
            if(die.getValue() == 1)
                img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/yellow/Yellow Die 1.jpg"));
            else if(die.getValue() == 2)
                img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/yellow/Yellow Die 2.jpg"));
            else if(die.getValue() == 3)
                img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/yellow/Yellow Die 3.jpg"));
            else if(die.getValue() == 4)
                img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/yellow/Yellow Die 4.jpg"));
        }
        else
            img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/Empty Die.jpg"));
        
        return img;
    }
}
