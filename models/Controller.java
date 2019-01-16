package models;

import models.beings.*;
import models.products.*;

import java.util.Date;
import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.text.SimpleDateFormat;

public class Controller
{

  private OompaLoompaSong song;
  private HashMap<Long, Kid> kidsByCode;
  private ArrayList<GoldenTicket> tickets;
  private HashMap<Long, Integer> numProduct;
  private static Scanner sc = new Scanner(System.in);
  private static SimpleDateFormat my_format = new SimpleDateFormat("dd-MM-yy");
  private static Random rand = new Random();
  private ArrayList<Product> productsEmpty;
  private ArrayList<OompaLoompa> oompas;
  private ArrayList<Product> products;
  private ArrayList<Kid> kids;

  public Controller()
  {
    kids = new ArrayList<>();
    oompas = new ArrayList<>();
    tickets = new ArrayList<>();
    products = new ArrayList<>();
    kidsByCode = new HashMap<>();
    numProduct = new HashMap<>();
    productsEmpty = new ArrayList<>();
  }

  public void ListTicket()
  {
    for(GoldenTicket t: tickets)
      System.out.println(t);
  }

  public void ListRaffledTicket()
  {
    for(GoldenTicket t: tickets)
      if (t.isRaffled())
        System.out.println(t);
  }

  public void ListWinner()
  {
    for(Kid k: kids)
    {
      if (k.hasPrize())
      {
        System.out.println(k.getName());
      }
    }
  }

  public void WriteToFile()
  {
    try
    {
      int cnt = 0;
      for(Kid k: kids)
      {
        cnt++;
        k.writeToFile("Users" + cnt);
      }
      cnt = 0;
      for(Product p: products)
      {
        cnt++;
        p.writeToFile("Products" + cnt);
      }
    }
    catch (Exception e)
    {
      System.out.println("Error!" + e.toString());
    }
  }

  public void NewSong()
  {
    song = new OompaLoompaSong(rand.nextInt(24) + 1);
    System.out.println("Sing a random line:" + song.sing());
    // System.out.println(song);
  }

  public void RegisterSale()
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
      // System.out.println("Removed!");
    }
    System.out.println("Kid with id " + kid.getCode() + " just bought " + p);
    kid.addProduct(p);
    System.out.println(p + " is added to kid's purchased list");
    products.remove(0);
    System.out.println(p + " is removed from product list");
  }

  public void RuffleTicket()
  {
    System.out.println("Number of generating tickets:");
    int n = sc.nextInt();
    Collections.shuffle(productsEmpty);
    // After shuffling, take the n-th first element and assign the ticket to it.
    System.out.println("Distributing tickets:");
    while (n > 0 && productsEmpty.size() > 0)
    {
      GoldenTicket t = new GoldenTicket();
      t.setCode(Long.toString(rand.nextLong()));
      t.setDate(new Date());
      tickets.add(t);
      Product p = productsEmpty.get(0);
      p.setPrize(t);
      productsEmpty.remove(0);
      n--;
      System.out.println(p + " now have a golden ticket");
    }
    while (n > 0)
    {
      GoldenTicket t = new GoldenTicket();
      t.setCode(Long.toString(rand.nextLong()));
      t.setDate(new Date());
      tickets.add(t);
      n--;
    }
  }

  public void ReadProduct()
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

  public void ReadMortal() throws Exception
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

  public void ReadTicket()
  {
    GoldenTicket ticket = new GoldenTicket();
    System.out.println("Code:");
    String code = sc.next();
    ticket.setCode(code);
    ticket.setDate(new Date());
    tickets.add(ticket);
  }

  // Prevent empty string.
  public String ReadLine()
  {
    String s = sc.nextLine();
    while (s.isEmpty())
      s = sc.nextLine();
    return s;
  }
}