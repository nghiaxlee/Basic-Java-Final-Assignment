import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;

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

  // Util implements.
  public boolean hasPrize() { return prizeTicket != null; }
  public void writeToFile(String fname) throws Exception
  {
    PrintWriter pw = new PrintWriter(new FileOutputStream(new File(fname), true));
    pw.append(toString());
    pw.append('\n');
    pw.close();
  }

  @Override
  public String toString()
  {
    String s = "Description: " + description;
    s += "\nBarcode: " + barcode;
    s += "\nSerial number: " + serialNumber;
    return s;
  }
}
