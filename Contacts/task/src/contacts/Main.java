package contacts;


import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static List<ContactAbstract> lista = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    private static final String filepath="phonebook.db";

    public static void main(String[] args) {

       // loadDatabase();
        System.out.println("open phonebook.db");

        String line ="";
        while (!line.equals("exit")){
            System.out.println();
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            line = scanner.nextLine();
            if(!line.equals("exit")) menuFirst(line);
        }

    }
    //*************************************************************************
    public static void menuFirst(String line) {
        switch (line){
            case "add":
                addMenu();
                break;
            case "remove":
                if(lista.size()==0){
                    System.out.println("No records to remove!");

                }else{
                  //  removeMenu();

                }
                break;
            case "search":
                    if(lista.size()==0){
                        System.out.println("No records to search!");

                    }else{

                        searchMenu();



                    }
                break;

            case "count":
                System.out.println("The Phone Book has "+lista.size()+" records.");
                break;


            case "list":
                if(lista.size()==0){
                    System.out.println("No records to list!");

                }else{
                    for ( int i = 0; i < lista.size(); i++){
                        System.out.println(i+1 + " " + lista.get(i).getBasicInformations());
                    }
                    System.out.println();
                    System.out.print("[list] Enter action ([number], back): ");
                    String wybor = scanner.nextLine();
                    if(!wybor.equals("back")){

                        int i = Integer.parseInt(wybor);
                        i--;
                        menuRecord(String.valueOf(i));
                    }

                }
                break;

        }
    }
    //*************************************************************************
    private static void searchMenu() {

        boolean powtorz = true;

        while (powtorz) {
            System.out.print("Enter search query: ");
            String wybor = scanner.nextLine();
            Map<Integer, Integer> mapa = new HashMap<>();
            int couter = 0;
            for (int i = 0; i < lista.size(); i++) {

                String name = lista.get(i).getBasicInformations().toLowerCase(Locale.ROOT) +lista.get(i).getNumber();
                String s = wybor.toLowerCase(Locale.ROOT);
                if (name.contains(s)) {
                    couter++;
                    mapa.put(couter, i);
                }


                /*
                String s = wybor.toLowerCase(Locale.ROOT);
                String name = lista.get(i).getBasicInformations().toLowerCase(Locale.ROOT) +lista.get(i).getNumber();
                String szukanie = "(?i).*"+s + ".*";
                Pattern pattern = Pattern.compile(szukanie);
                if(pattern.matcher(name).matches()){
                    couter++;
                    mapa.put(couter, i);
                }
                */




            }
            System.out.println("Found " + mapa.size() + " results:");
            for (Map.Entry<Integer, Integer> entry : mapa.entrySet()) {
                System.out.println(entry.getKey() + ". " + lista.get(entry.getValue()).getBasicInformations());
            }
            System.out.println();
            System.out.print("[search] Enter action ([number], back, again): ");
            String wybor2 = scanner.nextLine();
            if (wybor2.equals("back"))
                        powtorz=false;
            else if(wybor2 == "again")
                            powtorz = true;
            else {

                try{
                    int i = Integer.parseInt(wybor2);
                  //  int orDefault = mapa.getOrDefault(i,0);
                    if(!mapa.isEmpty()){
                        int val = mapa.get(i);
                        menuRecord(String.valueOf(val));
                        powtorz=false;

                    }else {
                        menuRecord(String.valueOf(i));
                        powtorz=false;
                    }


                }catch (Exception e){

                }



            }






        }
    }

    //*************************************************************************
  private static void menuRecord(String choice){
      Integer i = Integer.valueOf(choice);
      lista.get(i).printFullInformations();
      System.out.println();
      System.out.print("[record] Enter action (edit, delete, menu): ");
      String wybor = scanner.nextLine();
      switch (wybor){
          case "edit":

              List<String> strings = lista.get(i).returnListOfFieldsStrings();
              String tekst="";
              for (String s: strings){
                  tekst=tekst+", "+s;
              }
              tekst = tekst.substring(2);
              System.out.println("Select a field (" + tekst + "):");
              String wybor2 = scanner.nextLine();
              editMenu(wybor2,i);




              break;
          case "delete":

              lista.remove(i);
              System.out.println("The record removed!");

              break;

      }
      //  saveDatabase();


  }
    //*************************************************************************
    private static void addMenu() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("[add] Enter the type (person, organization):");
        String type = scanner.nextLine();

        switch (type){
            case "person":

                Person2 person2 = new Person2();
                person2.setFullInformations();
                Collections.reverse(lista);
                lista.add(person2);
                Collections.reverse(lista);
               // lista.add(person2);
                System.out.println("The record added.");
                break;

            case "organization":

                Organization2 organization2 = new Organization2();
                organization2.setFullInformations();
                Collections.reverse(lista);
                lista.add(organization2);
                Collections.reverse(lista);

               // lista.add(organization);
                System.out.println("The record added.");

                break;


        }
        saveDatabase();

    }
    //*************************************************************************
    private static void editMenu(String pole, int i ) {
        String tekst = lista.get(i).getTekst(pole);
        System.out.print(tekst);
        String wartosc = scanner.nextLine();
        String s = lista.get(i).changeFieldValue(pole, wartosc);
        if(s.length() !=0)
            System.out.println(s);


    }



    //*************************************************************************
    static void saveDatabase() {
        try {

            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lista);
            oos.close();
            //boolean databaseIsSaved = true;

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    } // End of saveDatabase
    //*************************************************************************
    static void loadDatabase() {

        try {
            FileInputStream fis = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ArrayList<ContactAbstract>)ois.readObject();
          // Collections.reverse(lista);
            ois.close();
        }
        catch (IOException e) {
           // System.out.println("***catch ERROR***");
           // e.printStackTrace();

        }
        catch (ClassNotFoundException e) {
            System.out.println("***catch ERROR***");
          //  e.printStackTrace();
        }
    } // End of loadDatabase

    //*************************************************************************


}
