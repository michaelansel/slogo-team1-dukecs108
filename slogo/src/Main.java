import slogo.model.parser.SlogoParser;
import util.parser.ParserException;
import util.parser.ParserResult;


public class Main
{

    /**
     * @param args
     */
    /*public static void main (String[] args)
    {
        ParserResult result = null;
        try
        {
            result = SlogoParser.parse("fd 50");
        }
        catch (ParserException e)
        {
            e.printStackTrace();
        }
        System.out.println(result);
        return;
    }*/
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Frame();
            }
        });
	}

}
