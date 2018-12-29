import models.beings.*;
import models.products.*;

import java.util.Date;
import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.text.SimpleDateFormat;

public class MyMenu
{

    static OompaLoompaSong song;
    static Random rand = new Random();
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Kid> kids = new ArrayList<>();
    static ArrayList<Product> products = new ArrayList<>();
    static HashMap<Long, Kid> kidsByCode = new HashMap<>();
    static ArrayList<OompaLoompa> oompas = new ArrayList<>();
    static ArrayList<GoldenTicket> tickets = new ArrayList<>();
    static HashMap<Long, Integer> numProduct = new HashMap<>();
    static ArrayList<Product> productsEmpty = new ArrayList<>();
    static SimpleDateFormat my_format = new SimpleDateFormat("dd-MM-yy");

  public static void main(String args[])
  {
    int option = 0;
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
            NewSong();
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
            RuffleTicket();
            break;
          case 8: //    8 - Register sale
            System.out.println("Registering sale:");
            RegisterSale();
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
      WriteToFile();
    }
    catch (Exception e)
    {
      System.out.println("Error! " + e.toString());
    }
    // TODO: Catch specific one, i.e InputMismatchException, ParseException
  }

  public static void WriteToFile() throws Exception
  {
    for(Kid k: kids)
    {
      k.writeToFile("Users.txt");
    }
    for(Product p: products)
    {
      p.writeToFile("Users.txt");
    }
  }

  public static void NewSong()
  {
    song = new OompaLoompaSong(rand.nextInt(24) + 1);
    // System.out.println(song.sing());
    // System.out.println(song);
  }

  public static void RegisterSale()
  {
    System.out.println("User code:");
    long kid_code = sc.nextLong();
    Kid kid = kidsByCode.get(kid_code);
    if (kid == null)
    {
      System.out.println("There is no kid with the given code");
      return;
    }
    System.out.println("Product code:");
    long prod_code = sc.nextLong();
    int n = numProduct.getOrDefault(prod_code, 0);
    if (n == 0)
    {
      System.out.println("No more item with barcode " + prod_code + " to sell");
      return;
    }
    int pos = rand.nextInt(n) + 1; // will sell the pos-th product.
    for(int i = 0, cnt = 0; i < products.size(); ++i)
    {
      Product p = products.get(i);
      if (p.getBarcode() == prod_code)
        cnt++;
      if (cnt == pos)
      {
        Collections.swap(products, i, 0);
        break;
      }
    }
    Product p = products.get(0);
    if (!p.hasPrize())
    {
      productsEmpty.remove(p);
      System.out.println("Removed!");
    }
    kid.addProduct(products.get(0));
    products.remove(0);
  }

  public static void RuffleTicket()
  {
    System.out.println("Number of generating tickets:");
    int n = sc.nextInt();
    Collections.shuffle(productsEmpty);
    // After shuffling, take the n-th first element and assign the ticket to it.
    while (n > 0 && productsEmpty.size() > 0)
    {
      GoldenTicket t = new GoldenTicket();
      Product p = productsEmpty.get(0);
      t.setCode(Long.toString(rand.nextLong()));
      t.setDate(new Date());
      tickets.add(t);
      p.setPrize(t);
      productsEmpty.remove(0);
      n--;
      System.out.println(p + " Now have a golden ticket");
    }
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
    products.add(p);
    productsEmpty.add(p);
    numProduct.put(bar, numProduct.getOrDefault(bar, 0) + 1);
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
      throw new java.util.InputMismatchException();
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
