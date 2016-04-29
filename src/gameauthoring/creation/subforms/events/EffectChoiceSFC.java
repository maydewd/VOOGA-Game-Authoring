package gameauthoring.creation.subforms.events;

import java.util.List;
import engine.IGame;
import engine.definitions.concrete.EventPackageDefinition;
import engine.effects.IEffect;
import gameauthoring.creation.subforms.dynamic.MultiOptionSFC;
import util.BundleOperations;

public class EffectChoiceSFC extends MultiOptionSFC<EventPackageDefinition>{
    
    private String effectKey = "EFFECTS";

    public EffectChoiceSFC (IGame game) {
        super(game);
        setMyOptions(BundleOperations.getPropertyValueAsList(effectKey, getMyOptionsFile()));
        setMyView(new EffectChoiceSFV(getMyOptions()));
        setActions();       
    }

    @Override
    protected List<IEffect> getList(EventPackageDefinition item){
        return item.getMyEffectsList();
    }

    @Override
    protected void resetContents (EventPackageDefinition item) {
        item.getMyEffectsList().clear();
    }

     

}