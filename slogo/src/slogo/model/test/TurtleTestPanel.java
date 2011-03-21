package slogo.model.test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import slogo.model.action.Action;
import slogo.model.arena.Arena;
import slogo.model.arena.TurtleException;
import slogo.model.arena.turtle.Turtle;
import slogo.util.drawables2D.Line;
import slogo.util.interfaces.ICartesian2D;
import slogo.util.interfaces.IDraw2D;
import slogo.view.SwingDraw;


public class TurtleTestPanel extends JPanel
{

    Arena myArena;
    BufferedImage myImage;
    
    
    public TurtleTestPanel (Arena arena)
    {
        super();
        myArena = arena;
        
    }
    
   
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        myImage = new BufferedImage(this.getPreferredSize().width, 
                                    this.getPreferredSize().width, BufferedImage.TYPE_INT_RGB);
        myImage.getGraphics().setColor(Color.white);
        myImage.getGraphics().fillRect(0, 0, (int)this.getPreferredSize().getWidth(), (int)this.getPreferredSize().getHeight());
        
        SwingDraw swingDraw = new SwingDraw(myImage);
        for(Action action : myArena.getActions())
            action.draw(swingDraw);
              
        ((Graphics2D)g).drawImage(myImage, null, 0, 0);
        
   }
}
