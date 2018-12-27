public class Product implements Util
{
  private String description;
  private long barcode;
  private String serialNumber;
  private GoldenTicket prizeTicket;

  public Product()
  {}

  public Product(String des, long code, String serial)
  {
    description = des;
    barcode = code;
    serialNumber = serial;
  }

  // Getters and Setters
  public void setDescription(String des) { description = des; }
  public void setBarcode(long code) { barcode = code; }
  public void setSerialNumber(String serial) { serialNumber = serial; }
  public void setPrize(GoldenTicket ticket) { prizeTicket = ticket; }

  public String getDescription() { return description; }
  public long getBarcode() { return barcode; }
  public String getSerialNumber() { return serialNumber; }
  public GoldenTicket getPrize() { return prizeTicket; }

  public boolean hasPrize() { return prizeTicket != null; }

  @Override
  public String toString()
  {
    String s = "Description: " + description;
    s += "\nBarcode: " + barcode;
    s += "\nSerial number: " + serialNumber;
    return s;
  }
}
