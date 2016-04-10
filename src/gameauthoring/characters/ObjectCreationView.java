package gameauthoring.characters;

import java.util.List;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


/**
 * Object that holds and displays both view items that make up object creation interface
 * 
 * @author JoeLilien, Jeremy Schreck
 *
 */
public class ObjectCreationView<E> implements IObjectCreationView<E> {

    private GridPane myCreationPane = new GridPane();
    private IObjectListView<E> myObjectListView;
    private IFormView myFormView;
    private List<ISubFormView> mySubFormViews;
    private String myTitle;
    private Button myNewButton = new Button("New");


    /**
     * Constructor
     * 
     * @param objectListView The IObjectListView that displays the list of created objects
     * @param formView The IFormView that displays the form to create a new object
     */
    public ObjectCreationView (IObjectListView<E> objectListView, IFormView formView) {
        setObjectListView(objectListView);
        setFormView(formView);
        init();
    }
    
    public ObjectCreationView(List<ISubFormView> subFormViews) {
        setObjectListView(createListView());
        setFormView(createFormView(subFormViews));
        init();
    }
    
    private IObjectListView<E> createListView () {
        ObservableList<E> objectList = FXCollections.observableArrayList();
        IObjectListView<E> objectListView = new ObjectListView<E>(objectList);
        return objectListView;
    }
    
    private IFormView createFormView (List<ISubFormView> subFormViews) {
        IFormView formView = new FormView(subFormViews);
        return formView;
    }

    /**
     * Initialize view
     */
    private void init () {
        myCreationPane.add(myObjectListView.draw(), 0, 0);
        myCreationPane.add(myFormView.draw(), 1, 0);
    }

    @Override
    public Node draw () {
        return myCreationPane;
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }

    // Getters and Setters

    @Override
    public void setObjectListView (IObjectListView objectListView) {
        this.myObjectListView = objectListView;
    }

    @Override
    public void setFormView (IFormView formView) {
        this.myFormView = formView;
    }

    @Override
    public IObjectListView getObjectListView () {
        // TODO Auto-generated method stub
        return myObjectListView;
    }

    @Override
    public IFormView getFormView () {
        // TODO Auto-generated method stub
        return myFormView;
    }

    @Override
    public void setSubFormViews (List<ISubFormView> subFormViews) {
        this.mySubFormViews = subFormViews;
        
    }

    @Override
    public void setTitle (String name) {
        myTitle = name;
    }

    @Override
    public void setNewAction (Consumer<?> action) {
        myNewButton.setOnAction(e -> action.accept(null));
    }

    @Override
    public void setEditAction (Consumer<E> action) {
        // TODO Auto-generated method stub
        //set listcell's edit button's setOnAction to call action
        //how to retrieve which item is associated with that list cell?
        getObjectListView().setEditAction(action);
        
    }

}
