import java.util.*;

public class MyMenu
{
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

    Random rand = new Random();
    ArrayList<Kid> kids = new ArrayList<>();
    ArrayList<OompaLoompa> oompas = new ArrayList<>();
    ArrayList<GoldenTicket> tickets = new ArrayList<>();


    Scanner sc = new Scanner(System.in);
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
    do
    {
      try
      {
        System.out.print("Select item: ");
        option = sc.nextInt();
      }
      catch (Exception e)
      {
        System.out.println("Error!");
      }
      switch (option)
      {
        case 1: //    1 - Register Prize tickets
          System.out.println("Registering Prize ticket:");
          GoldenTicket ticket = new GoldenTicket();
          System.out.println("Code:");
          String code = sc.next();
          ticket.setCode(code);
          ticket.setDate(new Date());
          tickets.add(ticket);
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
          break;
        case 6: //    6 - Register Products
          System.out.println("Registering product:");
          break;
        case 7: //    7 - Ruffle tickets
          System.out.println("Ruffling tickets:");
          break;
        case 8: //    8 - Register sale
          System.out.println("Registering sale:");
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
}
