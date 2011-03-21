package slogo.model.artists.qualities.defaults;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import slogo.model.ModelException;
import slogo.model.artists.qualities.*;
import slogo.model.artists.qualities.behavior.*;


public class DefaultQualityLibrary
{

    static List<ArtistQuality> defaultStates;
    public static final IBehavior DEFAULT_BEHAVIOR = new DefaultBehavior();
    public static final double DEFAULT_HEADING = 90.0;
    public static final File DEFAULT_IMAGE = new File("src/images/directedTurtle.png");
    public static final String DEFAULT_NAME = "unnamed";
    public static final Color DEFAULT_COLOR = Color.BLACK;
    public static final BasicStroke DEFAULT_STROKE = new BasicStroke(1,
                                                                     BasicStroke.CAP_BUTT,
                                                                     BasicStroke.JOIN_MITER);
    public static final Point2D DEFAULT_HOME = new Point2D.Double(0.0,0.0);
    public static final Boolean DEFAULT_VISIBILITY = Visibility.VISIBLE;
    public static final Dimension DEFAULT_ICON_DIM = new Dimension(30,30);
    
    
    
    public DefaultQualityLibrary(){
        defaultStates = new ArrayList<ArtistQuality>();
        try
        {
            defaultStates.addAll(Arrays.asList(new ArtistQuality[]{
                    new Behavior(DEFAULT_BEHAVIOR),
                    new Heading(DEFAULT_HEADING),
                    new Icon(DEFAULT_IMAGE, DEFAULT_ICON_DIM),
                    new Name(DEFAULT_NAME),
                    new Pen(DEFAULT_STROKE, DEFAULT_COLOR),
                    new Coordinates(DEFAULT_HOME),
                    new Visibility(DEFAULT_VISIBILITY)
                    }));
        }
        catch (Exception e)
        {
            throw new ModelException("Default Turtle Icon path does not exist");
        }
    
    }
    

    public static ArtistQuality getDefaultbyClass (Class<? extends ArtistQuality> c)
    {
        for(ArtistQuality mq: defaultStates){
            if (mq.getClass().equals(c))
                return mq;
        }
        
        throw new ModelException(ModelException.NO_DEFAULT_LOADED);
        
    }

}
