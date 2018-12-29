package models.beings;

public class OompaLoompa extends MortalBeing
{
  private int height; // not sure 'bout this.
  private String food;

  // public OompaLoompa()
  // {}

  public OompaLoompa(long code, String name, int height, String food)
  {
    super(code, name);
    this.height = height;
    this.food = food;
  }

  // Getters and Setters
  public void setHeight(int h) { height = h; }
  public void setFood(String f) { food = f; }

  public int getHeight() { return height; }
  public String getFood() { return food; }
}
