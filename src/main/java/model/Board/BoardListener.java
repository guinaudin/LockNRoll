package Model.Board;

import java.util.EventListener;

public interface BoardListener extends EventListener{
    public void boardChanged(BoardChangedEvent event);
}
