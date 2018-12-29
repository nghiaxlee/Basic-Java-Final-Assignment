import java.util.Random;
import java.util.ArrayList;

public class OompaLoompaSong
{
  private static Random rand = new Random();
  private ArrayList<String> song;
  private int lines;

  public OompaLoompaSong(int lines)
  {
    this.lines = lines;
    song = new ArrayList<>();
    for(int i = 0; i < lines; ++i)
    {
      String line = GenLine();
      song.add(line);
    }
  }

  private String GenLine()
  {
    int len = rand.nextInt(42) + 1;
    String s = "";
    for(int i = 0; i < len; ++i)
      s += (char)(rand.nextInt(95) + 32);
    return s;
  }

  // Return a String with the song
  public String sing()
  {
    return song.get(rand.nextInt(lines));
  }

  // toString (wonder if it different from sing()??)
  public String toString()
  {
    String ret = String.join("\n", song);
    return ret;
  }

  // equals
}
