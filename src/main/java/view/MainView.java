package view;

import controler.AbstractControler;
import java.awt.BorderLayout;
import java.awt.Color;
import model.board.Board;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.dice.Die;
import model.dice.DieTypes;
import model.player.Player;
import observer.Observer;

/**classe qui met en place la page d'acceuil*/
public class MainView extends JFrame implements Observer, ActionListener
{  
    private Board board;
    private Desktop desktop = null;
    private java.net.URI url;
    private JMenuItem newGameMenuItem;
    private JMenuItem leaderBoardMenuItem;
    private JMenuItem quitMenuItem;
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
    private JButton[] jButtonBombJoker;
    private JButton[] jButtonRollJoker;
    private JButton rollButton;
    private JLabel imageMainView;
    private Die selectedRolledDie;
    private Die selectedBoardDie;
    private Die selectedBombJoker;
    private boolean leaderboard;
    private int selectedPosX;
    private int selectedPosY;
    private JProgressBar progressBarScore;
    private JLabel scoreLabel;
    private boolean cleanRollJokerActivated;
    private LeaderBoardView leaderBoardView;
    
    public MainView(AbstractControler controler) {
        super("Lock N Roll");
        this.controler = controler;
        selectedPosX = 4;
        selectedPosY = 4;
        selectedRolledDie = null;
        selectedBoardDie = null;
        selectedBombJoker = null;
        leaderboard = false;
        cleanRollJokerActivated = false;
        this.buildFrame();
        this.buildMenuBar();
        
        this.setVisible(true);
    }
    
    private void buildFrame() {
        //On donne une taille � notre fen�tre
        this.setSize(340,400);
        //On centre la fen�tre sur l'�cran
        this.setLocationRelativeTo(null);
        //On interdit la redimensionnement de la fen�tre
        this.setResizable(false); 
        //On dit � l'application de se fermer lors du clic sur la croix
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        startPagePanel = new JPanel(new BorderLayout());
        imageMainView = new JLabel(new ImageIcon(getClass().getResource("/images/LockNRoll.jpg")));
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
        JPanel boardFlowPanel = new JPanel(new FlowLayout());
        boardFlowPanel.setBackground(Color.WHITE); 
        boardPanel = new JPanel();
        boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
        boardPanel.setBackground(Color.WHITE); 
        
        jButtonDiceBoard = new JButton[4][4];
        jButtonRolledDice = new JButton[4];
        jButtonBombJoker = new JButton[2];
        jButtonRollJoker = new JButton[2];
        
        JPanel dicePanel = new JPanel();
        dicePanel.setLayout(new BoxLayout(dicePanel, BoxLayout.X_AXIS));
        dicePanel.setBackground(Color.WHITE); 
        
        //creation d'un objet JPanel
        diceBoardPanel = new JPanel();
        //layout par defaut
        diceBoardPanel.setLayout(new GridLayout(4, 4, 5, 5));
        diceBoardPanel.setBackground(Color.WHITE); 
                        
        //creation des JButtons installer sur le panel en les rendant evenementiel
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                jButtonDiceBoard[i][j] = new JButton("");
                jButtonDiceBoard[i][j].setActionCommand("button" + i + j);
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
        rolledDicePanel.setBackground(Color.WHITE); 
        
        for(int i = 0; i < 4; i++) {
            jButtonRolledDice[i] = new JButton("");
            jButtonRolledDice[i].setActionCommand("button" + i);
            jButtonRolledDice[i].addActionListener(this);
            jButtonRolledDice[i].setMaximumSize(new Dimension(50,50));
            jButtonRolledDice[i].setMinimumSize(new Dimension(50,50));
            jButtonRolledDice[i].setPreferredSize(new Dimension(50,50));
            
            rolledDicePanel.add(jButtonRolledDice[i]);
        }
        
        dicePanel.add(rolledDicePanel);
        dicePanel.add(Box.createHorizontalStrut(15));
        dicePanel.add(diceBoardPanel);
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setBackground(Color.WHITE); 
        
        rollButton = new JButton("Roll");
        rollButton.addActionListener(this);
        rollButton.setMaximumSize(new Dimension(50,50));
        rollButton.setMinimumSize(new Dimension(50,50));
        rollButton.setPreferredSize(new Dimension(50,50));
        
        topPanel.add(rollButton);
        topPanel.add(Box.createHorizontalStrut(15));
        
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setBackground(Color.WHITE); 
        
        scoreLabel = new JLabel("Score : " + 0);
        scorePanel.add(Box.createHorizontalStrut(60));
        scorePanel.add(scoreLabel);
        scorePanel.add(Box.createVerticalStrut(5));
        
        progressBarScore = new JProgressBar(0, 100);
        progressBarScore.setValue(0);
        progressBarScore.setStringPainted(true);
        scorePanel.add(progressBarScore);
        
        topPanel.add(scorePanel);
        
        JPanel jokerPanel = new JPanel();
        jokerPanel.setLayout(new BoxLayout(jokerPanel, BoxLayout.X_AXIS));
        //topPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        jokerPanel.setBackground(Color.WHITE); 
        
        for(int i = 0; i < 2; i++) {
            jButtonBombJoker[i] = new JButton("");
            jButtonBombJoker[i].setActionCommand("bombJokerButton" + i);
            jButtonBombJoker[i].addActionListener(this);
            jButtonBombJoker[i].setMaximumSize(new Dimension(50,50));
            jButtonBombJoker[i].setMinimumSize(new Dimension(50,50));
            jButtonBombJoker[i].setPreferredSize(new Dimension(50,50));

            jokerPanel.add(jButtonBombJoker[i]);
            jokerPanel.add(Box.createHorizontalStrut(5));
        }
        
        for(int i = 0; i < 2; i++) {
            jButtonRollJoker[i] = new JButton("");
            jButtonRollJoker[i].setActionCommand("rollJokerButton" + i);
            jButtonRollJoker[i].addActionListener(this);
            jButtonRollJoker[i].setMaximumSize(new Dimension(50,50));
            jButtonRollJoker[i].setMinimumSize(new Dimension(50,50));
            jButtonRollJoker[i].setPreferredSize(new Dimension(50,50));

            jokerPanel.add(jButtonRollJoker[i]);
            jokerPanel.add(Box.createHorizontalStrut(5));
        }
        
        boardPanel.add(Box.createVerticalStrut(10));
        boardPanel.add(topPanel);
        boardPanel.add(Box.createVerticalStrut(10));
        boardPanel.add(dicePanel);
        boardPanel.add(Box.createVerticalStrut(10));
        boardPanel.add(jokerPanel);
        boardFlowPanel.add(boardPanel);
        
        return boardFlowPanel;
    }
    
     /**methode realisant les actions lorsqu'il y a un evenement*/
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object source;
        
        //on recupere la source du clic
        if(e.getSource() instanceof JMenuItem) {
            source = (JMenuItem)(e.getSource());
            
            if(source == newGameMenuItem) {
                this.setNewContentPane(this.buildBoardPanel());
                controler.startNewGame();
            }
            //si l'utilisateur clic sur "rules"
            else if(source == websiteMenuItem) {
                //tant qu'il n'y a pas d'exception
                try {
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
                InputStream pdf = getClass().getClassLoader().getResourceAsStream("pdf/LockNRoll.pdf");
     
                try {
                    File pdfCree = new File("LockNRoll rules.pdf");
                    // Extraction du PDF qui se situe dans l'archive
                    FileOutputStream fos = new FileOutputStream(pdfCree);
                    while(pdf.available() > 0) {
                          fos.write(pdf.read());
                    }
                    fos.close();
                    // Ouverture du PDF
                    Desktop.getDesktop().open(pdfCree);
                }

                catch (IOException err) {
                    System.out.println("erreur : " + err);
                }
            }
            else if(source == leaderBoardMenuItem)
            {
                leaderBoardView = new LeaderBoardView();
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
        else if(e.getSource() instanceof JButton) {
            if(e.getActionCommand().equals("button0")) {
                this.selectOrMoveRolledDie(0);
            }
            else if(e.getActionCommand().equals("button1")) {
                this.selectOrMoveRolledDie(1);
            }
            else if(e.getActionCommand().equals("button2")) {
                this.selectOrMoveRolledDie(2);
            }
            else if(e.getActionCommand().equals("button3")) {
                this.selectOrMoveRolledDie(3);
            } 
            else if(e.getActionCommand().equals("button00")) {
                this.selectOrMoveDie(0, 0);
            }  
            else if(e.getActionCommand().equals("button01")) {
                this.selectOrMoveDie(0, 1);
            }  
            else if(e.getActionCommand().equals("button02")) {
                this.selectOrMoveDie(0, 2);
            }  
            else if(e.getActionCommand().equals("button03")) {
                this.selectOrMoveDie(0, 3);
            }  
            else if(e.getActionCommand().equals("button10")) {
                this.selectOrMoveDie(1, 0);
            }  
            else if(e.getActionCommand().equals("button11")) {
                this.selectOrMoveDie(1, 1);
            }  
            else if(e.getActionCommand().equals("button12")) {
                this.selectOrMoveDie(1, 2);
            }  
            else if(e.getActionCommand().equals("button13")) {
                this.selectOrMoveDie(1, 3);
            }  
            else if(e.getActionCommand().equals("button20")) {
                this.selectOrMoveDie(2, 0);
            }  
            else if(e.getActionCommand().equals("button21")) {
                this.selectOrMoveDie(2, 1);
            }  
            else if(e.getActionCommand().equals("button22")) {
                this.selectOrMoveDie(2, 2);
            }  
            else if(e.getActionCommand().equals("button23")) {
                this.selectOrMoveDie(2, 3);
            }  
            else if(e.getActionCommand().equals("button30")) {
                this.selectOrMoveDie(3, 0);
            }  
            else if(e.getActionCommand().equals("button31")) {
                this.selectOrMoveDie(3, 1);
            }  
            else if(e.getActionCommand().equals("button32")) {
                this.selectOrMoveDie(3, 2);
            }  
            else if(e.getActionCommand().equals("button33")) {
                this.selectOrMoveDie(3, 3);
            }
            else if(e.getActionCommand().equals("bombJokerButton0")) {
                this.selectOrMoveBombJoker(0);
            }
            else if(e.getActionCommand().equals("bombJokerButton1")) {
                this.selectOrMoveBombJoker(1);
            }
            else if(e.getActionCommand().equals("rollJokerButton0")) {
                cleanRollJokerActivated = this.activateCleanRollJoker(0);
            }
            else if(e.getActionCommand().equals("rollJokerButton1")) {
                cleanRollJokerActivated = this.activateCleanRollJoker(1);
            }
            else if((JButton)e.getSource() == rollButton) {
                if(!cleanRollJokerActivated) {
                    controler.makeTurn();
                    cleanRollJokerActivated = false;
                }
                else {
                    controler.makeTurn();
                    controler.rollDice();
                    cleanRollJokerActivated = false;
                }
            }
        }
    }
    
    private boolean activateCleanRollJoker(int posX) {
        return controler.activateCleanRollJoker(posX);
    }
    
    private void selectOrMoveBombJoker(int posX) {
        if(!(controler.selectBombJoker(posX).getValue() == 0) && !(controler.selectBombJoker(posX).getColor() == 0)) {
            this.selectBombJoker(posX);
        }
    }
   
    private void selectOrMoveDie(int posX, int posY) {
        
        if(!(controler.selectBoardDie(posX, posY).getValue() == 0) && !(controler.selectBoardDie(posX, posY).getColor() == 0) && !controler.selectBoardDie(posX, posY).getLocked()) {   
            this.selectBoardDie(posX, posY);
        }
        else if(selectedBombJoker != null)
            this.moveBombJoker(posX, posY, selectedPosX);
        else if(selectedPosY == 4 && selectedRolledDie != null) {
            this.moveRolledDie(selectedRolledDie, posX, posY, selectedPosX);
        }
        else if(selectedBoardDie != null) {
            this.moveBoardDie(selectedBoardDie, posX, posY, selectedPosX, selectedPosY);
        }
    }
    
    private void selectOrMoveRolledDie(int posX) {
        if(!(controler.selectRolledDie(posX).getValue() == 0) && !(controler.selectRolledDie(posX).getColor() == 0)) {
            this.selectRolledDie(posX);
        }
        else if(selectedBoardDie != null)
            this.moveBoardDie(selectedBoardDie, posX, selectedPosX, selectedPosY);
    }
    
    private void selectRolledDie(int posX) {
        if(!(controler.selectRolledDie(posX).getValue() == 0) && !(controler.selectRolledDie(posX).getColor() == 0)) {
            selectedRolledDie = controler.selectRolledDie(posX);
            selectedBoardDie = null;
            selectedBombJoker = null;
            selectedPosX = posX;
            selectedPosY = 4;
        }
    }
    
    private void selectBoardDie(int posX, int posY) {
            selectedBoardDie = controler.selectBoardDie(posX, posY);
            selectedRolledDie = null;
            selectedBombJoker = null;
            selectedPosX = posX;
            selectedPosY = posY;
    }
    
    private void selectBombJoker(int posX) {
            selectedBoardDie = null;
            selectedRolledDie = null;
            selectedBombJoker = controler.selectBombJoker(posX);
            selectedPosX = posX;
            selectedPosY = 4;
    }
    
    private void moveRolledDie(Die selectedDie, int posX, int posY, int selectedPosX) {
        if(controler.selectBoardDie(posX, posY).getValue() == 0 && controler.selectBoardDie(posX, posY).getColor() == 0) {
            controler.moveRolledDie(selectedDie, posX, posY, selectedPosX);
            selectedBoardDie = null;
            selectedRolledDie = null;
            selectedBombJoker = null;
            
            selectedPosX = 4;
            selectedPosY = 4;
        }
    }
    
    private void moveBombJoker(int posX, int posY, int selectedPosX) {
        if(controler.selectBoardDie(posX, posY).getLocked() == true) {
            controler.moveBombJoker(new Die(0, 0, false), posX, posY, selectedPosX);
            selectedBoardDie = null;
            selectedRolledDie = null;
            selectedBombJoker = null;
            
            selectedPosX = 4;
            selectedPosY = 4;
        }
    }
    
    private void moveBoardDie(Die selectedDie, int posX, int posY, int selectedPosX, int selectedPosY) {
        if(controler.selectBoardDie(posX, posY).getValue() == 0 && controler.selectBoardDie(posX, posY).getColor() == 0) {
            controler.moveBoardDie(selectedDie, posX, posY, selectedPosX, selectedPosY);
            selectedBoardDie = null;
            selectedRolledDie = null;
            selectedBombJoker = null;
            
            selectedPosX = 4;
            selectedPosY = 4;
        }
    }
    
    private void moveBoardDie(Die selectedDie, int posX, int selectedPosX, int selectedPosY) {
        if(controler.selectRolledDie(posX).getValue() == 0 && controler.selectRolledDie(posX).getColor() == 0) {
            controler.moveBoardDie(selectedDie, posX, selectedPosX, selectedPosY);
            selectedBoardDie = null;
            selectedRolledDie = null;
            selectedBombJoker = null;
            
            selectedPosX = 4;
            selectedPosY = 4;
        }
    }

    @Override
    public void updateBoardDice(Board board) {
        Die[][] diceBoard = board.getDiceBoard();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                try {
                    jButtonDiceBoard[i][j].setIcon(new ImageIcon(this.getDieImage(diceBoard[i][j])));
                }
                catch (IOException ex) {
                    Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @Override
    public void updateRolledDice(Board board) {
        Die[] rolledDice = board.getRolledDice();
        
        for(int i = 0; i < 4; i++) {
            try {
                jButtonRolledDice[i].setIcon(new ImageIcon(this.getDieImage(rolledDice[i])));
            } 
            catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void updateCleanRollJoker(Player player) {
        try {
            if(player.getCleanRollJokerActivated(0))
                jButtonRollJoker[0].setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("images/dice/cleanRollActivated.jpg"))));
            else if(player.getCleanRollJoker(0))
                jButtonRollJoker[0].setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("images/dice/cleanRollJoker.jpg"))));
            else
                jButtonRollJoker[0].setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("images/dice/joker.jpg"))));
            
            if(player.getCleanRollJokerActivated(1)) 
                jButtonRollJoker[1].setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("images/dice/cleanRollActivated.jpg"))));
            else if(player.getCleanRollJoker(1))
                jButtonRollJoker[1].setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("images/dice/cleanRollJoker.jpg"))));
            else
                jButtonRollJoker[1].setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("images/dice/joker.jpg"))));
        }
        catch (IOException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateBombJoker(Player player) {
        Die[] bombJoker = player.getBombJoker();
        
        for(int i = 0; i < 2; i++) {
            try {
                if(bombJoker[i].getColor() == 5)
                    jButtonBombJoker[i].setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("images/dice/bombDie.jpg"))));
                else
                    jButtonBombJoker[i].setIcon(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("images/dice/joker.jpg"))));
            } 
            catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void updateScore(Player player, Board board) {
        if(player.getScore() >= player.getJokerScore(player.getActualJokerScore()) 
        && player.getJokerScore(player.getActualJokerScore()) < player.getJokerScore(3)) {
            player.setActualJokerScore(player.getActualJokerScore() + 1);
            if(player.getNbCleanRollJoker() < 2)
                player.setNbCleanRollJoker(player.getNbCleanRollJoker() + 1);
            if(board.getUnlockedPlaces() != 0) {
                if(!player.getCleanRollJoker(0)) 
                    player.setCleanRollJoker(true, 0);
                else 
                    player.setCleanRollJoker(true, 1);
            }
        }
        scoreLabel.setText("Score : " + player.getScore());
        
        if(player.getScore() > player.getJokerScore(player.getActualJokerScore()) && player.getScore() > 2500) {
            player.setTwoLastJokerScore(1000);
            if(player.getNbCleanRollJoker() < 2)
                player.setNbCleanRollJoker(player.getNbCleanRollJoker() + 1);
            if(board.getUnlockedPlaces() != 0) {
                if(!player.getCleanRollJoker(0)) 
                    player.setCleanRollJoker(true, 0);
                else 
                    player.setCleanRollJoker(true, 1);
            }
        }
        if(player.getJokerScore(player.getActualJokerScore()) > 250) {
            progressBarScore.setValue((int)((player.getScore() - player.getJokerScore(player.getActualJokerScore() - 1)))*100/(
            player.getJokerScore(player.getActualJokerScore()) - player.getJokerScore(player.getActualJokerScore() - 1)));
        }
        else
            progressBarScore.setValue((int)(player.getScore()*100/player.getJokerScore(player.getActualJokerScore())));
    }
    
    private Image getDieImage(Die die) throws IOException {
        Image img = null;
        
        if(!die.getLocked()) {
            if(die.getColor() == DieTypes.Color.BLUE.getInt()) {
                if(die.getValue() == 1)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/blue/Blue Die 1.jpg"));
                else if(die.getValue() == 2)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/blue/Blue Die 2.jpg"));
                else if(die.getValue() == 3)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/blue/Blue Die 3.jpg"));
                else if(die.getValue() == 4)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/blue/Blue Die 4.jpg"));
            }
            else if(die.getColor() == DieTypes.Color.GREEN.getInt()) {
                if(die.getValue() == 1)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/green/Green Die 1.jpg"));
                else if(die.getValue() == 2)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/green/Green Die 2.jpg"));
                else if(die.getValue() == 3)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/green/Green Die 3.jpg"));
                else if(die.getValue() == 4)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/green/Green Die 4.jpg"));
            }
            else if(die.getColor() == DieTypes.Color.RED.getInt()) {
                if(die.getValue() == 1)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/red/Red Die 1.jpg"));
                else if(die.getValue() == 2)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/red/Red Die 2.jpg"));
                else if(die.getValue() == 3)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/red/Red Die 3.jpg"));
                else if(die.getValue() == 4)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/red/Red Die 4.jpg"));
            }
            else if(die.getColor() == DieTypes.Color.YELLOW.getInt()) {
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
        }
        else {
            if(die.getColor() == DieTypes.Color.BLUE.getInt()) {
                if(die.getValue() == 1)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/blue/Blue Locked Die 1.jpg"));
                else if(die.getValue() == 2)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/blue/Blue Locked Die 2.jpg"));
                else if(die.getValue() == 3)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/blue/Blue Locked Die 3.jpg"));
                else if(die.getValue() == 4)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/blue/Blue Locked Die 4.jpg"));
            }
            else if(die.getColor() == DieTypes.Color.GREEN.getInt()) {
                if(die.getValue() == 1)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/green/Green Locked Die 1.jpg"));
                else if(die.getValue() == 2)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/green/Green Locked Die 2.jpg"));
                else if(die.getValue() == 3)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/green/Green Locked Die 3.jpg"));
                else if(die.getValue() == 4)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/green/Green Locked Die 4.jpg"));
            }
            else if(die.getColor() == DieTypes.Color.RED.getInt()) {
                if(die.getValue() == 1)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/red/Red Locked Die 1.jpg"));
                else if(die.getValue() == 2)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/red/Red Locked Die 2.jpg"));
                else if(die.getValue() == 3)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/red/Red Locked Die 3.jpg"));
                else if(die.getValue() == 4)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/red/Red Locked Die 4.jpg"));
            }
            else if(die.getColor() == DieTypes.Color.YELLOW.getInt()) {
                if(die.getValue() == 1)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/yellow/Yellow Locked Die 1.jpg"));
                else if(die.getValue() == 2)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/yellow/Yellow Locked Die 2.jpg"));
                else if(die.getValue() == 3)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/yellow/Yellow Locked Die 3.jpg"));
                else if(die.getValue() == 4)
                    img = ImageIO.read(getClass().getClassLoader().getResource("images/dice/yellow/Yellow Locked Die 4.jpg"));
            }
        }
        
        return img;
    }
}
