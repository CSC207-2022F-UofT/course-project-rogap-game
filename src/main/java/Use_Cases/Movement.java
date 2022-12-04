package Use_Cases;

import java.awt.*;
import java.util.ArrayList;

public abstract class Movement {

    public abstract Rectangle getHitBox();
    public abstract int newXLocation();
    public abstract int newYLocation();
    public abstract void setVelX();
    public abstract void setVelY();
    public abstract void setMoving ();

}
