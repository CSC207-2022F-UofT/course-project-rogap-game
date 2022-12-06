package Use_Cases;

import java.awt.*;
import java.util.ArrayList;

public abstract class Movement {

    public abstract int newXLocation();
    public abstract int newYLocation();
    public abstract void setVelX(int x);
    public abstract void setVelY(int y);
    public abstract void setMoving ();

}
