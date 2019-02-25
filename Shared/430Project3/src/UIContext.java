import java.awt.*;

public interface UIContext {

    //  public abstract void drawCircle(Circle circle);
    public abstract void draw(Line line);

    public abstract void draw(Ellipse ellipse);

    public abstract void draw(Polygon polygon);

    public abstract void draw(Label label);

    public abstract void draw(Item item);
    
    public abstract void draw(Point point);
}
