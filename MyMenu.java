import java.util.*;
import java.text.SimpleDateFormat;

public class MyMenu
{

    static Random rand = new Random();
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Kid> kids = new ArrayList<>();
    static HashMap<Long, Kid> kidsByCode = new HashMap<>();
    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<OompaLoompa> oompas = new ArrayList<>();
    static ArrayList<GoldenTicket> tickets = new ArrayList<>();
    static SimpleDateFormat my_format = new SimpleDateFormat("dd-MM-yy");

  public static void main(String args[])
  {
    
//    The software shall implement the following items:
//
//    1 - Register Prize tickets
//    2 - List all prize tickets
//    3 - List only raffled tickets
//    4 - Create a new Oompa Loompa song
//    5 - Register Beings
//    => What does responsible for the typing mean?? Should I read from file, should I handle exception.
//    6 - Register Products
//    => Read from file.
//    7 - Ruffle tickets
//    => Ask teacher.
//    8 - Register sale 
//    => users should be storaged in HashMap/HashTable, products should be storaged in HashSet(if not exists duplicate) or HashMap(if has duplicate)
//    9 - List winners

//    Use Java.text.SimpleDateFormat for the dates formatting and parsing (if necessary)
//    Use some library for creating the random Oompa Loompa song => CLGT????????????

//    Recommendations:
//
//    if for instance the number of tickets is bigger than the number of products, it is okay to simply add the tickets to the products and inform the user, you don't need to ask the user to inform another number (unless you want);
//    You can create a class on a separated file to populate the Arrays (it will be less messy for your Main).

    // HashMap<Long, ArrayList<Product>> productsByCode = new HashMap<>();
    int option = 0;
    // Done: Print Menu
    System.out.println("List items:"
                        + "\n 1 - Register Prize tickets"
                        + "\n 2 - List all prize tickets"
                        + "\n 3 - List only raffled tickets"
                        + "\n 4 - Create a new Oompa Loompa song"
                        + "\n 5 - Register Beings"
                        + "\n 6 - Register Products"
                        + "\n 7 - Ruffle tickets"
                        + "\n 8 - Register sale"
                        + "\n 9 - List winners");
    // TODO: Put case into function. like case 6: p = ReadProduct();
    try
    {
      do
      {
        System.out.print("Select item: ");
        option = sc.nextInt();      
        switch (option)
        {
          case 1: //    1 - Register Prize tickets
            System.out.println("Registering Prize ticket:");
            ReadTicket();
            break;
          case 2: //    2 - List all prize tickets
            System.out.println("Printing all tickets:");
            for(GoldenTicket t: tickets)
              System.out.println(t);
            break;
          case 3: //    3 - List only raffled tickets
            System.out.println("Printing raffled tickets:");
            for(GoldenTicket t: tickets)
              if (t.isRaffled())
                System.out.println(t);
            break;
          case 4: //    4 - Create a new Oompa Loompa song
            System.out.println("Generating new song:");
            break;
          case 5: //    5 - Register Beings
            System.out.println("Registering being:");
            ReadMortal();
            break;
          case 6: //    6 - Register Products
            System.out.println("Registering product:");
            ReadProduct();
            break;
          case 7: //    7 - Ruffle tickets
            // TODO: I would like to store products by hashmap with barcode. but how can I distribute the tickets then?
            System.out.println("Ruffling tickets:");
            break;
          case 8: //    8 - Register sale
            System.out.println("Registering sale:");
            System.out.println("User code:");
            long kid_code = sc.nextLong();
            Kid kid = kidsByCode.get(kid_code);
            System.out.println("Product code:");
            long prod_code = sc.nextLong();
            // Remove from general lists (swap to the last element, remove the last).
            // Add to kid lists

            break;
          case 9: //    9 - List winners
            System.out.println("Winners:");
            for(Kid k: kids)
            {
              if (k.hasPrize())
              {
                System.out.println(k.getName());
              }
            }
            break;
          default:
            System.out.println("Not an option!");
            break;
        }
      } while (option != 0);
    }
    catch (Exception e)
    {
      System.out.println("Error! " + e.toString());
    }
    // TODO: Catch specific one, i.e InputMismatchException, ParseException
  }

  public static void ReadProduct()
  {
    Product p = new Product();
    System.out.println("Description:");
    String des = ReadLine();
    System.out.println("Barcode:");
    long bar = sc.nextLong();
    System.out.println("Serial number:");
    String serial = sc.next();
    p.setDescription(des);
    p.setBarcode(bar);
    p.setSerialNumber(serial);
    // System.out.println(p);
    products.add(p);
  }

  public static void ReadMortal() throws Exception
  {
    System.out.println("Name:");
    String name = ReadLine();
    System.out.println("Code:");
    long code = sc.nextLong();
    System.out.println("Type (Kid/Oompa):");
    String type = sc.next();
    type = type.toUpperCase();
    if (type.equals("KID"))
    {
      System.out.println("Birthday(dd-MM-yy):");
      String birthday = ReadLine();
      System.out.println("Place of Birth:");
      String birthplace = ReadLine();
      Kid k = new Kid(code, name, my_format.parse(birthday), birthplace);
      kids.add(k);
      kidsByCode.put(code, k);
    }
    else if (type.equals("OOMPA"))
    {
      System.out.println("Height:");
      int h = sc.nextInt();
      System.out.println("Favorite food:");
      String food = ReadLine();
      OompaLoompa o = new OompaLoompa(code, name, h, food);
      oompas.add(o);
    }
    else
    {
      System.out.println("Type can only be Kid or Oompa");
      throw new InputMismatchException();
    }
  }

  public static void ReadTicket()
  {
    GoldenTicket ticket = new GoldenTicket();
    System.out.println("Code:");
    String code = sc.next();
    ticket.setCode(code);
    ticket.setDate(new Date());
    tickets.add(ticket);
  }

  // Prevent empty string.
  public static String ReadLine()
  {
    String s = sc.nextLine();
    while (s.isEmpty())
      s = sc.nextLine();
    return s;
  }
}
