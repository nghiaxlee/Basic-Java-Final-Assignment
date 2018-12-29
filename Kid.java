import java.io.File;
import java.util.Date;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

public class Kid extends MortalBeing implements Util
{
  private Date birthday;
  private String birthplace;
  private ArrayList<Product> purchased;
  private static SimpleDateFormat my_format = new SimpleDateFormat("dd-MM-yy");

  // public Kid()
  // {}

  public Kid(long code, String name, Date birthday, String place)
  {
    super(code, name);
    this.birthday = birthday;
    this.birthplace = place;
    purchased = new ArrayList<>();
  }

  public boolean hasPrize()
  {
    for (Product p: purchased)
    {
      if (p.hasPrize())
        return true;
    }
    return false;
  }

  public void writeToFile(String fname) throws Exception
  {
    PrintWriter pw = new PrintWriter(new FileOutputStream(new File(fname), true));
    pw.append(toString());
    pw.append('\n');
    pw.close();
  }

  // Getters and Setters
  public void setBirthPlace(String place) { birthplace = place; }
  public void setBirthDay(Date day) { birthday = day; }
  public void addProduct(Product p) { purchased.add(p); }

  public String getBirthPlace() { return birthplace; }
  public Date getBirthDay() { return birthday; }
  public ArrayList<Product> getPurchased() { return purchased; }

  public String toString()
  {
    String s = "Name: " + name;
    s += "\nUser code: " + code;
    s += "\nBirthday: " + my_format.format(birthday);
    s += "\nPlace of Birth: " + birthplace;
    // TODO: Purchased items??
    return s;
  }
}
