

import Controller.BoardController;
import Model.BoardModel;
import javax.swing.SwingUtilities;

public class LockNRoll {
    public static void main(String[] args) {  
        //creation d'une tache(Runnable) contenant le code d�di� � la cr�ation de la fen�tre et on l'ajoute dans le thread de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {   
                BoardModel model = new BoardModel();
                BoardController controller = new BoardController(model);
                controller.displayViews();
            }
        });
    }
}
