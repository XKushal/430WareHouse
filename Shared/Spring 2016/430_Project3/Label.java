
import java.awt.*;

/*
 * @Modified: Subodh Bhattarai
 */

public class Label extends Item {

    private Point startingPoint;
    private String text = "";

    public Label(Point point) {
        startingPoint = point;
    }

    public void addCharacter(char character) {
        text += character;
    }

    public void removeCharacter() {
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
    }

    @Override
    public boolean includes(Point point) {
        return distance(point, startingPoint) < 10.0;
    }

    @Override
    public void render() {
        uiContext.draw(this);
    }

    public String getText() {
        return text;
    }

    public Point getStartingPoint() {
        return startingPoint;
    }

    @Override
    public void move(int x, int y) {
        startingPoint.x += x;
        startingPoint.y += y;
    }
}
