package engine.definitions.concrete;

import java.util.List;
import engine.definitions.SpriteDefinition;
import engine.modules.PathMover;
import engine.sprite.ISprite;
import util.Coordinate;


public class EnemyDefinition extends SpriteDefinition {

    private List<Coordinate> myPath;
    private double mySpeed;

    public EnemyDefinition (double speed, List<Coordinate> path) {
        setSpeed(speed);
        setPath(path);
    }

    public void setPath (List<Coordinate> path) {
        myPath = path;

    }

    public void setSpeed (double speed) {
        mySpeed = speed;

    }

    @Override
    public ISprite create () {
        ISprite sprite = super.create();
        sprite.initialize(new PathMover(mySpeed, myPath, sprite), createGraphicModule(), createModules(),
                          createAttributes(),
                          createCoordinate());
        return sprite;

    }
}