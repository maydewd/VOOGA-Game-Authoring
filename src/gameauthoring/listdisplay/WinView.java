package gameauthoring.listdisplay;

import engine.IGame;
import engine.ILevel;
import engine.events.EventType;

public class WinView extends EndView {

    public WinView (IGame game, ILevel level) {
        super(game, level);
    }

    @Override
    protected EventType getEventType () {
        return EventType.WIN;
    }
    
    

    
}
