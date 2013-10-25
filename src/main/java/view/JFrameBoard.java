package view;

import controller.BoardController;
import controller.BoardView;
import model.board.Board;
import model.board.BoardChangedEvent;
import model.BoardModel;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

/**classe qui met en place la page d'acceuil*/
public class JFrameBoard extends BoardView implements ActionListener
{  
    //declaration d'un objet Desktop et d'un url
    private BoardModel boardModel;
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
    private JFrame frame;
  
    /**constructeur*/
    public JFrameBoard(BoardController controller, Board board) 
    {
        //on appel le constructeur mere et on set le titre de la fenetre
        super(controller);
        this.board = board;
        //On initialise notre fen�tre
        build();
    }
    
    /**methode initialisant la fenetre*/
    private void build()
    {
        frame = new JFrame();
        //On donne une taille � notre fen�tre
        frame.setSize(515,560);
        //On centre la fen�tre sur l'�cran
        frame.setLocationRelativeTo(null);
        //On interdit la redimensionnement de la fen�tre
        frame.setResizable(false); 
        //On dit � l'application de se fermer lors du clic sur la croix
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //creation de la menuBar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
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
        JLabel imageMainView = new JLabel(new ImageIcon("C:/Users/Guillaume/Documents/NetBeansProjects/LockNRoll/src/main/ressources/images/LockNRoll.jpg"));
        frame.getContentPane().add(imageMainView);
    }
    
    @Override
    public void close() {
        frame.dispose();
    }

    @Override
    public void display() {
        frame.setVisible(true);
    }
    
    /*public void actionPerformed(ActionEvent arg0) {
            getController().notifyVolumeChanged(Integer.parseInt(field.getValue().toString()));
    }*/
    
     /**methode realisant les actions lorsqu'il y a un evenement*/
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //on recupere la source du clic
        Object source = (JMenuItem)(e.getSource());
        
        //si l'utilisateur clic sur "rules"
        if(source == newGameMenuItem)
        {
            //Mise en place de l'ui avec le plateau de jeu
            
            //creer le board
            boardModel = new BoardModel();
            
            //lancer les 4 dés aléatoirement
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
                    Desktop.getDesktop().open(new File(getClass().getClassLoader().getResource("Ressources/LockNRoll.pdf").getPath()));
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
            //on coupe l'extrait musical
            //SoP.getAudio().close();
            //on ferme la fenetre
            this.close();
            //on quitte le programme
            System.exit(0);
        }
        /*else if(source==menuItem6)
        {
            SoP.getAudio().play();
        }
        else if(source==menuItem7)
        {
            SoP.getAudio().close();
        }
        else if((source==menuItem8)&&(SoP.isVisible()==false))
        {
            SoP=new SoundPage(SoP.getAudio());
            SoP.setVisible(true);
        }
        else if((source==menuItem9)&&(IdP.getDemarrer()==1)&&(C.isVisible()==false))
        {
            C.setVisible(true);
        }
        else if((source==menuItem10)&&(IdP.getDemarrer()==1)&&(fenCourbe.isVisible()==false))
        {
            fenCourbe.setVisible(true);
        }   
        else if((source==menuItem11)&&(IdP.getDemarrer()==1)&&(fenSFOR.isVisible()==false))
        {
            fenSFOR.setVisible(true);
        }
        else if((source==menuItem12)&&(IdP.getDemarrer()==1)&&(fenCam.isVisible()==false))
        {
            fenCam.setVisible(true);
        }*/
    }

    @Override
    public void boardChanged(BoardChangedEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
