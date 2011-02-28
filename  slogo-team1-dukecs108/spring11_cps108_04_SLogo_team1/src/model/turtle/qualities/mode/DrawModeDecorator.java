package model.turtle.qualities.mode;


public abstract class DrawModeDecorator implements IMode
{
    public IMode myDecoratedBehavior;

    public DrawModeDecorator()
    {
        myDecoratedBehavior = new DefaultDrawMode();
    }


    public DrawModeDecorator(IMode mode)
    {
        myDecoratedBehavior = mode;
    }



   public void setSubMode (IMode mode)
   {
       myDecoratedBehavior = mode;
       
   }
}
