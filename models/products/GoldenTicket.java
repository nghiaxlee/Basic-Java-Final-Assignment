package models.products;

import java.util.Date;
import java.text.SimpleDateFormat;

public class GoldenTicket
{

  private String code;
  private Date raffled;
  private static SimpleDateFormat my_format = new SimpleDateFormat("dd-MMM-yy");

  public GoldenTicket()
  {}

  public GoldenTicket(String code, Date raffled)
  {
    this.code = code;
    this.raffled = raffled;
  }

  // Getters and Setters
  public void setCode(String code) { this.code = code; }
  public void setDate(Date raffled) { this.raffled = raffled; }

  public String getCode() { return code; }
  public Date getDate() { return raffled; }

  // If the current ticket has been raffled already
  public boolean isRaffled()
  {
    return raffled != null;
  }

  @Override
  public String toString()
  {
    String s = "Ticket: " + code;
    if (isRaffled())
      s += "\nRaffled's date:" + my_format.format(raffled);
    else
      s += "\nNot yet raffled";
    return s;
  }
}
