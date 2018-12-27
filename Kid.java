import java.util.Date;
import java.util.ArrayList;

public class Kid extends MortalBeing implements Util
{
  private Date birthday;
  private ArrayList<Product> purchased;
  private String birthplace;

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

  // Getters and Setters
  public void setBirthPlace(String place) { birthplace = place; }
  public void setBirthDay(Date day) { birthday = day; }

  public String getBirthPlace() { return birthplace; }
  public Date getBirthDay() { return birthday; }
  public ArrayList<Product> getPurchased() { return purchased; }
}
