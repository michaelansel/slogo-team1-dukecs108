package slogo.model.turtle.qualities.mode;


public abstract class DrawModeDecorator implements IMode
{
    public IMode myDecoratedMode;

    public DrawModeDecorator()
    {
        myDecoratedMode = new DefaultDrawMode();
    }


    public DrawModeDecorator(IMode mode)
    {
        myDecoratedMode = mode;
    }



   public void setSubMode (IMode mode)
   {
       myDecoratedMode = mode;
       
   }
}
