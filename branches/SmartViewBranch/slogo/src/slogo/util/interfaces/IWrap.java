package slogo.util.interfaces;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;

public interface IWrap
{

    Collection<IWrap> wrap (Dimension limits);
    
}
