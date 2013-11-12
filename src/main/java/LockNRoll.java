import controler.AbstractControler;
import controler.BoardControler;
import javax.swing.SwingUtilities;
import model.AbstractModel;
import model.Game;
import view.MainView;

public class LockNRoll {
    public static void main(String[] args) {  
        //creation d'une tache(Runnable) contenant le code d�di� � la cr�ation de la fen�tre et on l'ajoute dans le thread de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {   
                //Instanciation de notre modèle
                AbstractModel abstractModel = new Game();
                //Création du contrôleur
                AbstractControler controler = new BoardControler(abstractModel);
                //Création de notre fenêtre avec le contrôleur en paramètre
                MainView boardView = new MainView(controler);
                //Ajout de la fenêtre comme observer de notre modèle
                abstractModel.addObserver(boardView);
            }
        });
    }
}
