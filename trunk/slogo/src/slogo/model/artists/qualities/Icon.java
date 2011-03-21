package slogo.model.artists.qualities;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import slogo.model.ModelException;
import slogo.model.artists.qualities.defaults.DefaultQualityLibrary;


public class Icon extends ArtistQuality
{

    Image myIcon;
    Dimension myDim;
    File myFilePath; 
    
    public Icon(){
        super();
    }
    
    public Icon(File file)  {
            this(file, DefaultQualityLibrary.DEFAULT_ICON_DIM );
    }


    public Icon (File file, Dimension dim) 
    {
        super(Icon.class);
        myFilePath = file;
        try
        {
            myIcon = ImageIO.read(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        myDim =dim;
        System.out.println(myFilePath);
     }

    @Override
    boolean equals (ArtistQuality aq) throws ModelException
    {
        if (myIcon.equals(((Icon) aq).getIcon()))
            return myDim.equals(((Icon) aq).getDimension());
        return false;
    }

    private Dimension getDimension ()
    {
        return myDim;
    }

    public Image getIcon ()
    {
        return myIcon;
    }
    
    

    @Override
    public Object setTo (ArtistQuality aq) throws ModelException
    {
        myIcon = ((Icon) aq).getIcon();
        myDim = ((Icon) aq).getDimension();
        return null;
    }

//    @Override
//    public int compareTo (ArtistQuality aq)
//    {
//        if (Double.compare(myDim.getWidth(),((Icon) aq).getDimension().getWidth())==0)
//            return Double.compare(myDim.getHeight(),((Icon) aq).getDimension().getHeight());
//        return Double.compare(myDim.getWidth(),((Icon) aq).getDimension().getWidth());
//    }

    public File getFilePath ()
    {
        return myFilePath;
    }

    public Image getImage(){
        return myIcon;
    }
    
}
