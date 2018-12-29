import java.util.Scanner;
import models.Controller;

public class MyMenu
{
  public static void main(String args[])
  {
    System.out.println("List items:"
                        + "\n 0 - Exit"
                        + "\n 1 - Register Prize tickets"
                        + "\n 2 - List all prize tickets"
                        + "\n 3 - List only raffled tickets"
                        + "\n 4 - Create a new Oompa Loompa song"
                        + "\n 5 - Register Beings"
                        + "\n 6 - Register Products"
                        + "\n 7 - Ruffle tickets"
                        + "\n 8 - Register sale"
                        + "\n 9 - List winners");
    try
    {
      int option = 0;
      Controller c = new Controller();
      Scanner sc = new Scanner(System.in);
      do
      {
        System.out.print("Select item: ");
        option = sc.nextInt();      
        switch (option)
        {
          case 0:
            System.out.println("Exit, writing data to file");
            break;
          case 1: //    1 - Register Prize tickets
            System.out.println("Registering Prize ticket:");
            c.ReadTicket();
            break;
          case 2: //    2 - List all prize tickets
            System.out.println("Printing all tickets:");
            c.ListTicket();
            break;
          case 3: //    3 - List only raffled tickets
            System.out.println("Printing raffled tickets:");
            c.ListRaffledTicket();
            break;
          case 4: //    4 - Create a new Oompa Loompa song
            System.out.println("Generating new song:");
            c.NewSong();
            break;
          case 5: //    5 - Register Beings
            System.out.println("Registering being:");
            c.ReadMortal();
            break;
          case 6: //    6 - Register Products
            System.out.println("Registering product:");
            c.ReadProduct();
            break;
          case 7: //    7 - Ruffle tickets
            System.out.println("Ruffling tickets:");
            c.RuffleTicket();
            break;
          case 8: //    8 - Register sale
            System.out.println("Registering sale:");
            c.RegisterSale();
            break;
          case 9: //    9 - List winners
            System.out.println("Winners:");
            c.ListWinner();
            break;
          default:
            System.out.println("Not an option!");
            break;
        }
      } while (option != 0);
      c.WriteToFile();
    }
    catch (Exception e)
    {
      System.out.println("Error! " + e.toString());
    }
    // TODO: Catch specific one, i.e InputMismatchException, ParseException
  }
}
