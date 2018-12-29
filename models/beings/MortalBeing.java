package models.beings;

public abstract class MortalBeing
{

  protected long code;
  protected String name;

  public MortalBeing()
  {}

  public MortalBeing(long code, String name)
  {
    this.code = code;
    this.name = name;
  }

  // Getters and Setters
  public void setCode(long code) { this.code = code; }
  public void setName(String name) { this.name = name; }

  public long getCode() { return code; }
  public String getName() { return name; }

}
