package Use_Cases;

import java.awt.*;
import java.util.ArrayList;

public abstract class Movement {

    public abstract Rectangle getHitBox();
    public abstract int newXLocation(int currLocation);
    public abstract int newYLocation(int currLocation);
    public abstract void setVelX(int x);
    public abstract void setVelY(int y);
    public abstract void setMoving ();

}
