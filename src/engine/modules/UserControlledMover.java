package engine.modules;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.ControlKeys;
import util.Key;
import util.TimeDuration;
import java.util.HashMap;
import java.util.Map;
import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import engine.IPositionable;
import engine.effects.IEffect;
import engine.interactionevents.InputType;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import engine.sprite.ISprite;
import util.Direction;


public class UserControlledMover extends Mover {

    private ObjectProperty<IAttribute> mySpeed;

    private Map<Direction, Key> myKeys;
    private Map<Direction, Boolean> myTraveling;

    public UserControlledMover (double speed,
                                ControlKeys controls, IPositionable sprite) {
        super(sprite);
        mySpeed = new SimpleObjectProperty<>(new Attribute(speed, AttributeType.SPEED));
        makeKeyMap(controls);
        makeTravelingMap();

    }

    private void makeTravelingMap () {
        myTraveling = new HashMap<>();
        myTraveling.put(Direction.UP, false);
        myTraveling.put(Direction.LEFT, false);
        myTraveling.put(Direction.RIGHT, false);
        myTraveling.put(Direction.DOWN, false);
    }

    private void makeKeyMap (ControlKeys controls) {
        myKeys = new HashMap<>();
        myKeys.put(Direction.UP, controls.getUpKey());
        myKeys.put(Direction.LEFT, controls.getLeftKey());
        myKeys.put(Direction.RIGHT, controls.getRightKey());
        myKeys.put(Direction.DOWN, controls.getDownKey());
    }

    @Override
    public void update (TimeDuration duration) {
        move(duration);

    }

    @Override
    public void applyEffect (IEffect effect) {
        getXVel().get().applyEffect(effect);
        getYVel().get().applyEffect(effect);
    }

    @Override
    public void registerKeyEvent (KeyIOEvent event) {
        if (event.getType() == InputType.KEY_PRESSED) {
            registerKeyPress(event.getKey());
        }
        else if (event.getType() == InputType.KEY_RELEASED) {
            registerKeyRelease(event.getKey());
        }
    }

    @Override
    public void registerMouseEvent (MouseIOEvent event) {
        // do nothing
    }

    private void registerKeyPress (Key key) {

        
        if (key.isEqual(myKeys.get(Direction.RIGHT))) {
            goRight();
        }
        else if (key.isEqual(myKeys.get(Direction.LEFT))) {
            goLeft();
        }
        else if (key.isEqual(myKeys.get(Direction.UP))) {
            goUp();
        }
        else if (key.isEqual(myKeys.get(Direction.DOWN))) {
            goDown();
        }
    }

    private void registerKeyRelease (Key key) {
        if (key.isEqual(myKeys.get(Direction.RIGHT))) {
            if (myTraveling.get(Direction.LEFT)) {
                goLeft();
            }
            else {
                stopHorizontal();
            }
            myTraveling.put(Direction.RIGHT, false);
        }
        else if (key.isEqual(myKeys.get(Direction.LEFT))) {
            if (myTraveling.get(Direction.RIGHT)) {
                goRight();
            }
            else {
                stopHorizontal();
            }
            myTraveling.put(Direction.LEFT, false);
        }
        else if (key.isEqual(myKeys.get(Direction.UP))) {
            if (myTraveling.get(Direction.DOWN)) {
                goDown();
            }
            else {
                stopVertical();
            }
            myTraveling.put(Direction.UP, false);
        }
        else if (key.isEqual(myKeys.get(Direction.DOWN))) {
            if (myTraveling.get(Direction.UP)) {
                goUp();
            }
            else {
                stopVertical();
            }
            myTraveling.put(Direction.DOWN, false);
        }
    }

    private void stopVertical () {
        getYVel().get().setValue(NO_MOTION);
        myTraveling.put(Direction.DOWN, false);
        myTraveling.put(Direction.UP, false);
    }

    private void goRight () {
        getXVel().get().setValue(mySpeed.get().getValueProperty().get());
        myTraveling.put(Direction.RIGHT, true);
    }

    private void goLeft () {
        getXVel().get().setValue(-mySpeed.get().getValueProperty().get());
        myTraveling.put(Direction.LEFT, true);
    }

    private void goUp () {
        getYVel().get().setValue(-mySpeed.get().getValueProperty().get());
        myTraveling.put(Direction.UP, true);
    }

    private void goDown () {
        getYVel().get().setValue(mySpeed.get().getValueProperty().get());
        myTraveling.put(Direction.DOWN, true);
    }

    private void stopHorizontal () {
        getXVel().get().setValue(NO_MOTION);
        myTraveling.put(Direction.RIGHT, false);
        myTraveling.put(Direction.LEFT, false);
    }

    @Override
    public ObservableList<ObjectProperty<IAttribute>> getSpecificAttributes () {
        ObservableList<ObjectProperty<IAttribute>> attributeList =
                FXCollections.observableArrayList();
        attributeList.add(mySpeed);
        return attributeList;
    }

}
