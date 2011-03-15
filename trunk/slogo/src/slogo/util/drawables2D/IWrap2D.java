package slogo.util.drawables2D;

import java.awt.Dimension;
import java.util.ArrayList;


public interface IWrap2D extends IWrap
{

    public abstract ArrayList<IWrap2D> wrapRight (Dimension limits);


    public abstract ArrayList<IWrap2D> wrapLeft (Dimension limits);


    public abstract ArrayList<IWrap2D> wrapBottom (Dimension limits);


    public abstract ArrayList<IWrap2D> wrapTop (Dimension limits);

}
