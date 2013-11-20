package model;

import model.board.Board;
import model.dice.Die;
import model.player.Player;
import view.IdView;

public class Game extends AbstractModel{
    private Board board;
    private Player player;
    private CaseTest caseTest;
    private IdView idView;

    public Game() {
        player = new Player();
        board = new Board();
        idView = null;
        caseTest = new CaseTest(board, player);
        board.rollDice();
    }
    
    public Game(Board board) {
        this.board = board;
        //board.rollDices();
    }
    
    @Override
    public void startNewGame() {
        player = new Player();
        board = new Board();
        board.rollDice();
        
        notifyBoardObserver(board);
        notifyJokerObserver(player);
    }
    
    @Override
    public void makeTurn() {
        System.out.println("roll");
        int cpt = 0;
        int i = -1;
        
        caseTest.findCombinations(board, player);
        System.out.println();
        board.lockDice();
        
        //victory test
        System.out.println("places : " + board.getUnlockedPlaces() + "bomb : " + player.getNbBombJoker() + "rolljok : " + player.getNbCleanRollJoker());
        if(board.getUnlockedPlaces() == 0 && player.getNbBombJoker() == 0 && player.getNbCleanRollJoker() == 0) {
            idView = new IdView(player);
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
        
        notifyScoreObserver(player);
        notifyBoardObserver(board);
        notifyJokerObserver(player);
    }
    
    @Override
    public void rollDice() {
        board.rollDice();
        
        player.setNbCleanRollJoker(player.getNbCleanRollJoker() - 1);
        
        if(player.getCleanRollJokerActivated(0)) {
            player.setCleanRollJokerActivated(false, 0);
            player.setCleanRollJoker(false, 0);
        }
        else {
            player.setCleanRollJokerActivated(false, 1);
            player.setCleanRollJoker(false, 1);
        }
        
        notifyBoardObserver(board);
        notifyJokerObserver(player);
    }
    
    @Override
    public boolean activateCleanRollJoker(int posX) {
        player.setCleanRollJokerActivated(!player.getCleanRollJokerActivated(posX), posX);
        
        if(posX == 0 && player.getCleanRollJokerActivated(0) && player.getCleanRollJokerActivated(1))
            player.setCleanRollJokerActivated(false, 1);
        else if(posX == 1 && player.getCleanRollJokerActivated(0) && player.getCleanRollJokerActivated(1))
            player.setCleanRollJokerActivated(false, 0);
        
        notifyJokerObserver(player);
        
        return player.getCleanRollJokerActivated(0) || player.getCleanRollJokerActivated(1);
    }

    @Override
    public Die selectBoardDie(int posX, int posY) {
        return new Die(board.getBoardDie(posX, posY).getValue(), board.getBoardDie(posX, posY).getColor(), board.getBoardDie(posX, posY).getLocked());
    }
    
    @Override
    public Die selectRolledDie(int posX) {
        return new Die(board.getRolledDie(posX).getValue(), board.getRolledDie(posX).getColor(), board.getRolledDie(posX).getLocked());
    }
    
    @Override
    public Die selectBombJoker(int posX) {
        return new Die(player.getBombJokerDie(posX).getValue(), player.getBombJokerDie(posX).getColor(), player.getBombJokerDie(posX).getLocked());
    }
    
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
    
    @Override
    public void moveBoardDie(Die die, int posX, int posY, int selectedPosX, int selectedPosY) {
        board.setBoardDie(die, posX, posY);
        board.setBoardDie(new Die(0, 0, false), selectedPosX, selectedPosY);
        
        //On met à jour 
        notifyBoardObserver(board);
    }
    
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
