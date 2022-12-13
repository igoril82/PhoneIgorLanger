package PhoneIgorLanger;

import IgorPhone.People;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook {


    ;


    Scanner sc = new Scanner(System.in);

    ArrayList<People> contacts = new ArrayList<>();
    ArrayList<String> list = new ArrayList<String>();

    /**
     * His is a function that adds a new number.
     * At this stage, we check how many characters are in the name
     * In addition, a typed phone number is also checked
     */
    public void addNewContact() throws IOException {
        People p = new People();

        int counterOfName = 0;
        int counterOfNumber = 0;

        while (true) {
            System.out.println("Please enter the correct contact name. Name must be in range 4 - 30 symbols.");
            p.setName(sc.next());
            if (p.getName().matches("^.{4,30}$")) {
                break;
            } else {
                counterOfName++;
                if (counterOfName > 2) {
                    System.out.println("You have tried to enter an incorrect name 3 times. Phone Book is terminated now");
                    System.exit(0);

                }

            }

        }
        p.setName(p.getName());

        while (true) {
            System.out.println("Please enter the correct contact number. Number may contain only '+', spaces and digits. Min length 8, max length 15.");
            p.setPhoneNumber(sc.next());
            if (p.getPhoneNumber().matches("^\\+?[0-9 ]{8,15}$")) {
                break;
            } else {
                counterOfNumber++;
                if (counterOfNumber > 2) {
                    System.out.println("You have tried to enter an incorrect number 3 times. Phone Book is terminated now");
                    System.exit(0);

                }
            }

        }
        contacts.add(p);

        System.out.println("Successfully added contact " + p.getName() + " to Phone Book");

    }

    /**
     * This is a function that returns a list of all entries in the book.
     */
    public void readContacts() {
        System.out.println("Names      ||  Numbers");
        for (People p1 : contacts) {
            System.out.println("Name :" + p1.getName() + " || " + " Number :" + p1.getPhoneNumber());

        }

    }

    /**
     * This is a function that deletes a number from the phone book.
     * There is a validation check to see if the name exists in the phone book.
     */
    public void deleteContact() {
        System.out.println("Please enter name for delete");
        Scanner stringScanner = new Scanner(System.in);
        String nameForDelete = stringScanner.nextLine();
        for (People people : contacts)
            if (people.getName().equals(nameForDelete)) {
                contacts.remove(people);
                System.out.println(nameForDelete + " was deleted from the Phone Book");
                break;
            } else {
                System.out.println("The contact " + nameForDelete + " is not appeared in Phone Book");

            }

    }

    /**
     * This is a function where you can search a name by user number.
     */
    public void searchContactByName() {
        System.out.println("Please enter name for search");
        Scanner stringScanner = new Scanner(System.in);
        String nameForSearch = stringScanner.nextLine();
        for (People p3 : contacts) {
            if (p3.getName().equals(nameForSearch)) {
                System.out.println("The following results were found: ");
                System.out.println(p3.getName() + " " + p3.getPhoneNumber());
                break;
            } else {
                System.out.println("The contact " + nameForSearch + " is not appeared in Phone Book");
            }
        }
    }

    /**
     * This is a function that sorted all contacts by name from small to large.
     */
    public void sortingByNameFromSmallToLarge() {
        System.out.println("Contacts sorted by name from small to large :");
        for (People p4 : contacts) {
            list.addAll(Collections.singleton(p4.getName() + " " + p4.getPhoneNumber()));
            list.sort(Comparator.comparingInt(String::length));
        }
        System.out.println(list);
        list.clear();
    }

    /**
     * This is a function that sorted all contacts by number from small to large.
     */

    public void sortingByNumberFromSmallToLarge() {
        for (People p5 : contacts) {
            list.addAll(Collections.singleton(p5.getPhoneNumber() + " " + p5.getName()));
            list.sort(Comparator.comparingInt(String::length));
        }
        System.out.println("Contacts sorted by number from small to large");
        System.out.println(list);
        list.clear();

    }

    /**
     * This is a function that sorted all contacts in reverse order.
     */
    public void sortingInReverseOrder() throws IOException {
        for (People p6 : contacts) {
            list.addAll(Collections.singleton(p6.getName() + " " + p6.getPhoneNumber()));
            Collections.sort(list, Collections.reverseOrder());
        }
        System.out.println("This is list of contacts in reverse order ");
        System.out.println(list);
        list.clear();
    }

    /**
     * This is a function that deletes duplicates values with same and number.
     */
    public void deleteDuplicateValues() {
        Set<String> set = new HashSet<String>();
        for (People p7 : contacts) {
            boolean flag = set.add(p7.getName() + p7.getPhoneNumber());
            if (!flag) {
                System.out.println(p7.getName() + " " + p7.getPhoneNumber());
                contacts.remove(p7);
                System.out.println("The duplicates values of name " + p7.getName() + " and number " + p7.getPhoneNumber() + " were deleted ");
            } else {
                System.out.println("No more duplicates contacts in Phone book");

            }

        }

    }


    /**
     * TThis is a function that export all data to external file.
     */
    public void exportToFile() throws IOException {
        try {

            File f = new File("C:/Jira/contacts1.csv");
            FileWriter fw = new FileWriter(f, true);

            if (f.exists()) {

                System.err.println("file exists");
            } else {

                System.err.println("Create new file");
                f.createNewFile();
            }
            for (People people : contacts) {

                System.out.println("name " + people.getName() + " and number " + people.getPhoneNumber() + " saved to file " + f);
                fw.write(String.format("\"%s\" ", people.getName()));
                fw.write(", ");
                fw.write(String.format("\"%s\" ", people.getPhoneNumber()));
                fw.write("\n");
            }
            fw.close();
        } catch (Exception e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * TThis is a function that import all data from external file.
     */
    public void importFromFile() {
        System.out.println("Please enter the patch of DataFile");
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();
        File f = new File(fileName);
        try {
            System.out.println("Below is a list of all contacts in the Phone book");
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            sc.useDelimiter("\\Z");
            System.out.println("Name   ||  Number");

            BufferedReader bf = new BufferedReader(
                    new FileReader(f));

            String line = bf.readLine();

            while (line != null) {
                list.add(line);
                line = bf.readLine();
            }
            bf.close();
            String[] array
                    = contacts.toArray(new String[0]);
            for (String str : array) {
                System.out.println(str);
            }
            System.out.println(sc.next());
        } catch (Exception e) {
            System.err.println("You Phone book is empty or patch to file is incorrect.");
            e.printStackTrace();

        }

    }

    /**
     * TThis is a function that calling to Main menu.
     */
    public void userMenu() throws IOException {

        System.out.println("Hello , please choose from the following options: \n 1 - add new contact , \n 2 - delete exists contact , \n 3 - list of all contacts in Phone book , \n 4 - search specific contact by name, \n 5 - sorting all contacts  by name  from small to large , \n 6 - sorting all contacts by number in reverse order, \n 7 - show values in reverse order,  \n 8 - delete duplicates values, \n 9 - export data to file, \n 10 - import data from file" + ",\n 11 - exit. ");

        Scanner input = new Scanner(System.in);
        String line = input.nextLine().trim();


        while (!line.equals("exit")) {


            switch (line) {

                case "1":
                    System.out.println("your choose - 1 add new contact");
                    addNewContact();
                    break;

                case "2":
                    System.out.println("your choose - 2 delete exists contact");
                    deleteContact();
                    break;


                case "3":
                    System.out.println("your choose 3 - list all contact");
                    readContacts();
                    break;


                case "4":
                    System.out.println("your choose 4 - search contact by name");
                    searchContactByName();
                    break;

                case "5":
                    System.out.println("your choose - 5 sorting all contacts  by name from small to large");
                    sortingByNameFromSmallToLarge();
                    break;


                case "6":
                    System.out.println("your choose - 6 sorting all contacts by number from small to large");
                    sortingByNumberFromSmallToLarge();
                    break;

                case "7":
                    System.out.println("your choose - 7 sorting all contacts in reverse order");
                    sortingInReverseOrder();
                    break;

                case "8":
                    System.out.println("your choose - 8 delete duplicates contacts");
                    deleteDuplicateValues();
                    break;

                case "9":
                    System.out.println("your choose - 9  export contacts to file ");
                    exportToFile();
                    break;

                case "10":
                    System.out.println("your choose - 10 import contacts from file ");
                    importFromFile();
                    break;
                case "11":
                    System.out.println("you choose - 11 Good bay, Phone Book is terminated now.");
                    System.exit(0);

                default:
                    System.err.println("Invalid command please try again");
                    break;

            }
            System.out.println();
            userMenu();
            System.out.print("\n> ");
            line = input.nextLine().trim();


        }
        System.out.println("Good bay, Phone Book is terminated now.");
        System.exit(0);
        input.close();
    }
}


