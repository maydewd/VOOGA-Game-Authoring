package gameauthoring;

import java.util.List;

public interface SpriteTabController {
    /**
     * Has an observable list of sprites
     */
    public void showAndEdit();
    
    public void createBlankSprite(); // Empty sprite
    
    public void deleteSprite();
    
    void editSprite(List<SubFormView> subForms); 
}
