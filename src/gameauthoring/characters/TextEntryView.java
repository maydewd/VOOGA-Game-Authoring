package gameauthoring.characters;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;


/**
 * Allows for Text Data Entry Associated with Label given as constructor Arguement
 * 
 * @author JoeLilien
 *
 */
public class TextEntryView extends EntryView {
    private String myLabel;
    private VBox myContainer; // TODO Magic Number and Factory
    private TextArea myTextInput = new TextArea();
    

    public TextEntryView (String label, IFormDataManager data, double spacing, double width, double height) {
        super(label, data);
        this.myTextInput.setMinSize(width, height);
        this.myTextInput.setMaxSize(width, height);
        this.myTextInput.textProperty().bindBidirectional(getData().getValueProperty());
        this.myContainer = new VBox(spacing);
        myContainer.getChildren().add(new Label(myLabel));
        myContainer.getChildren().add(myTextInput);
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub

    }


    /**
     * Ensures that entry is a double value if it is required
     * 
     * @param key
     * @param value
     * @return
     */
//    private FormData getDataWithErrorCheck (String key, String value) {
//        try {
//            Double.parseDouble(value);
//            return new FormData(key, new ArrayList<String>(Arrays.asList(value)));
//        }
//        catch (IllegalArgumentException e) {
//            ErrorMessage err = new ErrorMessage(myLabel + " Value Must Be a Double!");
//            err.showError();
//            return null;
//        }
//    }

    @Override
    public Node draw () {
        return myContainer;
    }

}
