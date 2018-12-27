public abstract class MortalBeing
{

  protected String code;
  protected String name;

  public MortalBeing()
  {}

  public MortalBeing(String code, String name)
  {
    this.code = code;
    this.name = name;
  }

  // Getters and Setters
  public void setCode(String code) { this.code = code; }
  public void setName(String name) { this.name = name; }

  public String getCode() { return code; }
  public String getName() { return name; }

}
