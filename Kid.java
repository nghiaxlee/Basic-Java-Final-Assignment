public class Kid extends MortalBeing
{
  private Date birthday;
  private ArrayList<Product> purchaseed;
  private String birthplace;

  public Kid(String code, String name, Date birthday, ArrayList<Product> p, String place)
  {
    super(code, name);
    this.birthday = birthday;
    purchased = p;
    birthplace = place;
  }

  // Getters and Setters

}
