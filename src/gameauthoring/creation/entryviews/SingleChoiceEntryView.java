package gameauthoring.creation.entryviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.definitions.AttributeDefinition;
import engine.profile.IProfilable;
import gameauthoring.ProfileCellView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


/**
 * Allows for Single choice from list of given choices associated with Label given as constructor
 * Arguement
 * 
 * 
 * @author JoeLilien
 *
 */
public class SingleChoiceEntryView<E extends IProfilable> extends EntryView {
    private String myLabel;
    private GridPane myContainer;
    private ComboBox<E> myChoices;

    public SingleChoiceEntryView (String label, ObservableList<E> observableList, double spacing) {
        super(label);
        this.myContainer = new GridPane();
        this.myChoices = new ComboBox<E>(observableList);
        myChoices.setCellFactory(c -> new NameCellView<E>());
        
        myContainer.add(new Label(myLabel), 0, 0);
        myContainer.add(myChoices, 0, 1);
    }

    public void setSelected (E item) {
        myChoices.getSelectionModel().select(item);
    }

    public E getSelected () {
        return myChoices.getSelectionModel().getSelectedItem();
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

    @Override
    public Node draw () {
        return myContainer;
    }

}
