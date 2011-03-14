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
import slogo.model.arena.Arena;
import slogo.model.arena.TurtleException;
import slogo.model.arena.turtle.Turtle;
import slogo.util.drawables2D.IDraw2D;
import slogo.util.drawables2D.Line;


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
        for (Turtle turtle: myArena.getTurtleMap().values()){
            drawLines(turtle.getLinesToDraw(0)); //for now draws all lines each time
            try
            {
                turtle.draw((Graphics2D) myImage.getGraphics());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
              
        ((Graphics2D)g).drawImage(myImage, null, 0, 0);
        
   }



    /**
     * @param lines
     */
    private void drawLines (List<IDraw2D> lines)
    {
        for (IDraw2D line: lines){
            line.draw(myImage.createGraphics(), this.getPreferredSize());
        }
    }
    
    
    
    
}
