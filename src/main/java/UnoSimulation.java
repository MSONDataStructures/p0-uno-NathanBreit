
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * <p>An entire terminal-based simulation of a multi-game Uno match.
 * Command-line switches can control certain aspects of the game. Output is
 * provided to the screen about game flow and final scores.</p>
 * @since 1.0
 */
public class UnoSimulation
{
    /** 
     * Controls how many messages fly by the screen while narrating an Uno
     * match in text.
     */
    public static boolean PRINT_VERBOSE = true;

    /**
     * <p>The name of a file (relative to working directory) containing
     * comma-separated lines, each of which contains a player name
     * (unrestricted text) and the <i>prefix</i> of the (package-less)
     * class name (implementer of UnoPlayer) that player will use as a
     * playing strategy.</p>
     *
     * For example, if the file contained these lines:
     * <pre>
     * Fred,fsmith
     * Jane,jdoe
     * Billy,bbob
     * Thelma,tlou
     * </pre>
     * then the code would pit Fred (whose classname was
     * "fsmith_UnoPlayer") against Jane (whose classname was
     * "jdoe_Unoplayer") against, Billy,... etc.
     */
    public static final String PLAYER_FILENAME = "lib/players.txt";

    /**
     * The names ("Joe") and classes ("jsmith_UnoPlayer") of competing
     * players.
     */
    private static ArrayList<String> playerNames = new ArrayList<String>();
    private static ArrayList<String> playerClasses = new ArrayList<String>();

    public static int GAMES = 20;
    
    /** 
     * Run an Uno simulation of some number of games pitting some set of
     * opponents against each other. The mandatory command-line argument
     * (numberOfGames) should contain an integer specifying how many games
     * to play in the match. The optional second command-line argument
     * should be either the word "verbose" or "quiet" and controls the
     * magnitude of output.
     */
    public static void main(String args[])
    {
        int numGames = GAMES;
        try
        {
            loadPlayerData();
            Scoreboard s = new Scoreboard(playerNames.toArray(new String[0]));
            for (int i = 0; i < numGames; i++)
            {
                Game g = new Game(s,playerClasses);
                g.play();
            }
            System.out.println(s);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void loadPlayerData() throws Exception
    {
        BufferedReader br = new BufferedReader(new FileReader(
            PLAYER_FILENAME));
        String playerLine = br.readLine();
        while (playerLine != null)
        {
            Scanner line = new Scanner(playerLine).useDelimiter(",");
            playerNames.add(line.next());
            playerClasses.add(line.next() + "_UnoPlayer");
            playerLine = br.readLine();
        }
    }
}
