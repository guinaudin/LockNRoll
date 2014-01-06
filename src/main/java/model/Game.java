package model;

import model.board.Board;
import model.dice.Die;
import model.player.Player;
import view.IdView;

/**Classe définissant l'ensemble des méthodes du modèle abstrait*/
public class Game extends AbstractModel{
    private Board board;
    private Player player;
    private CaseTest caseTest;
    private IdView idView;
    private Boolean victory;

    public Game() {
        player = new Player();
        board = new Board();
        idView = null;
        caseTest = new CaseTest(board, player);
        victory = false;
        board.rollDice();
    }
    
    public Game(Board board) {
        this.board = board;
    }
    
    /**Démarrage d'un nouveau jeu*/
    @Override
    public void startNewGame() {
        player = new Player();
        board = new Board();
        victory = false;
        board.rollDice();
        
        notifyBoardObserver(board);
        notifyJokerObserver(player);
    }
    
    /**Méthode appeler après clic sur le bouton Roll*/
    @Override
    public void makeTurn() {
        int cpt = 0;
        int i = -1;
        
        if(!victory) {
            caseTest.findCombinations(board, player);
            board.lockDice();

            //victory test
            if(board.getUnlockedPlaces() == 0 && player.getNbBombJoker() == 0) {
                idView = new IdView(player);
                victory = true;
            }

            //roll dice or not
            do {
                i++;
                if(board.getRolledDie(i).getColor() == 0)
                   cpt++;   
            }while(cpt<4 && board.getRolledDie(i).getColor() == 0);

            if(cpt == 4) {
                board.rollDice();
            }

            notifyScoreObserver(player, board);
            notifyBoardObserver(board);
            notifyJokerObserver(player);
        }
    }
    
    /**Lancement aléatoire des dés dans la colonne de tirage*/
    @Override
    public void rollDice() {
        board.rollDice();
        
        if(player.getCleanRollJokerActivated(0)) {
            player.setNbCleanRollJoker(player.getNbCleanRollJoker() - 1);
            player.setCleanRollJokerActivated(false, 0);
            player.setCleanRollJoker(false, 0);
        }
        else {
            player.setNbCleanRollJoker(player.getNbCleanRollJoker() - 1);
            player.setCleanRollJokerActivated(false, 1);
            player.setCleanRollJoker(false, 1);
        }
        
        notifyBoardObserver(board);
        notifyJokerObserver(player);
    }
    
    /**Activation du joker pour faire un nouveau tirage de dé aléatoire*/
    @Override
    public boolean activateCleanRollJoker(int posX) {
        if(player.getCleanRollJoker(posX)) {
            player.setCleanRollJokerActivated(!player.getCleanRollJokerActivated(posX), posX);

            if(posX == 0 && player.getCleanRollJokerActivated(0) && player.getCleanRollJokerActivated(1))
                player.setCleanRollJokerActivated(false, 1);
            else if(posX == 1 && player.getCleanRollJokerActivated(0) && player.getCleanRollJokerActivated(1))
                player.setCleanRollJokerActivated(false, 0);

            notifyJokerObserver(player);

            return player.getCleanRollJokerActivated(0) || player.getCleanRollJokerActivated(1);
        }
        else 
            return false;
    }
    
    /**sélectionner un dé sur le plateau*/
    @Override
    public Die selectBoardDie(int posX, int posY) {
        return new Die(board.getBoardDie(posX, posY).getValue(), board.getBoardDie(posX, posY).getColor(), board.getBoardDie(posX, posY).getLocked());
    }
    
    /**sélectionner un dé sur la colonne de tirage*/
    @Override
    public Die selectRolledDie(int posX) {
        return new Die(board.getRolledDie(posX).getValue(), board.getRolledDie(posX).getColor(), board.getRolledDie(posX).getLocked());
    }
    /**sélectionner le joker bomb*/
    @Override
    public Die selectBombJoker(int posX) {
        return new Die(player.getBombJokerDie(posX).getValue(), player.getBombJokerDie(posX).getColor(), player.getBombJokerDie(posX).getLocked());
    }
    
    /**Déplacement d'un dé provenant de la colonne de tirage sur le lateau*/
    @Override
    public void moveRolledDie(Die die, int posX, int posY, int selectedPosX) {
        board.getBoardDie(posX, posY).setColor(die.getColor());
        board.getBoardDie(posX, posY).setValue(die.getValue());
        board.setRolledDie(new Die(0, 0, false), selectedPosX);
        board.setUnlockedPlaces(board.getUnlockedPlaces() - 1);
        
        //On met à jour 
        notifyBoardObserver(board);
        notifyJokerObserver(player);
    }
    
    /**Déplacement du joker bomb sur le plateau*/
    @Override
    public void moveBombJoker(Die die, int posX, int posY, int selectedPosX) {
        board.setBoardDie(die, posX, posY);
        player.setBombJokerDie(new Die(0, 0, false), selectedPosX);
        board.setUnlockedPlaces(board.getUnlockedPlaces() + 1);
        player.setNbBombJoker(player.getNbBombJoker() - 1);
        
        //On met à jour 
        notifyBoardObserver(board);
        notifyJokerObserver(player);
    }
    
    /**Déplacement d'un dé provenant du plateau sur le plateau*/
    @Override
    public void moveBoardDie(Die die, int posX, int posY, int selectedPosX, int selectedPosY) {
        board.setBoardDie(die, posX, posY);
        board.setBoardDie(new Die(0, 0, false), selectedPosX, selectedPosY);
        
        //On met à jour 
        notifyBoardObserver(board);
    }
    
    /**Déplacement d'un dé provenant du plateau sur la colonne de tirage*/
    @Override
    public void moveBoardDie(Die die, int posX, int selectedPosX, int selectedPosY) {
        board.setRolledDie(die, posX);
        board.setBoardDie(new Die(0, 0, false), selectedPosX, selectedPosY);
        board.setUnlockedPlaces(board.getUnlockedPlaces() + 1);
        
        //On met à jour 
        notifyBoardObserver(board);
    }

    public Board getBoard() {
        return board;
    }
    
    public void setBoard(Board board) {
        this.board = board;
    }
}
