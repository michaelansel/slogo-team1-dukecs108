import slogo.model.parser.SlogoParser;
import util.parser.ParserException;
import util.parser.ParserResult;


public class Main
{

    /**
     * @param args
     */
    public static void main (String[] args)
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
    }

}
