package gameauthoring.creation.subforms.fire;

import engine.profile.IProfilable;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.dynamic.MultiOptionSFC;

/**
 * 
 * 
 * @author Joe Lilien
 *
 */
public abstract class RemovableSFC<T extends IProfilable> implements ISubFormController<T> {

    private T myDefinition;
    private RemoveOption myView;

    public RemovableSFC (MultiOptionSFC<T> sfc) {
        myView = new RemoveOption(e->sfc.removeSFC(this));
    }


    public abstract void removeModule (Object myMod);

    public abstract Object getModuleDefinition ();

    protected T getMyDefinition () {
        return myDefinition;
    }


    protected RemoveOption getRemoveMenu(){
        return myView;
    }
    
    protected void setMySpriteDefinition (T mySpriteDefinition) {
        this.myDefinition = mySpriteDefinition;
    }

}