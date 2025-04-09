import java.io.*;

public class reservation {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);

    int pno[] = new int[275];
    String name[] = new String[275];
    String phno[] = new String[275];
    int age[] = new int[275];
    int cl[] = new int[275];
    int pcount = 0;
    int pnum = 1;
    int max1 = 75, max2 = 125, max3 = 175;

    public void doMenu() throws Exception {
        int cho = 0;
        do {
            System.out.println("\f");
            doHeading();
            System.out.println("1. Book ticket");
            System.out.println("2. Cancel ticket");
            System.out.println("3. Search passenger");
            System.out.println("4. Reservation chart");
            System.out.println("5. Display unbooked tickets");
            System.out.println("6. Exit");
            System.out.print("Please enter your choice: ");
            cho = Integer.parseInt(br.readLine());

            switch (cho) {
                case 1 -> doBook();
                case 2 -> doCancel();
                case 3 -> doSearch();
                case 4 -> doDispList();
                case 5 -> doDispUnbooked();
                case 6 -> doExit();
                default -> System.out.println("Invalid choice");
            }

            System.out.println("\nPress Enter to continue...");
            br.readLine();
        } while (cho != 6);
    }

    private void doHeading() {
        System.out.println("#########################################################");
        System.out.println("********* Railway Reservation For Kabul Express *********");
        System.out.println("#########################################################");
    }

    private void doBook() throws Exception {
        System.out.println("Select class of ticket:");
        System.out.println("1. AC\t 2. First\t 3. Sleeper");
        int c = Integer.parseInt(br.readLine());

        System.out.print("Enter number of tickets: ");
        int t = Integer.parseInt(br.readLine());

        boolean ticketAvailable = switch (c) {
            case 1 -> max1 >= t;
            case 2 -> max2 >= t;
            case 3 -> max3 >= t;
            default -> false;
        };

        if (ticketAvailable) {
            for (int i = 0; i < t; i++) {
                pno[pcount] = pnum;
                System.out.print("Enter name: ");
                name[pcount] = br.readLine();
                System.out.print("Enter age: ");
                age[pcount] = Integer.parseInt(br.readLine());
                cl[pcount] = c;
                System.out.print("Enter phone number: ");
                phno[pcount] = br.readLine();
                pcount++;
                pnum++;
                System.out.println("Ticket successfully booked.\n");
            }

            int cost = switch (c) {
                case 1 -> 1500;
                case 2 -> 1200;
                case 3 -> 1000;
                default -> 0;
            };

            if (c == 1) max1 -= t;
            else if (c == 2) max2 -= t;
            else if (c == 3) max3 -= t;

            System.out.println("Please pay Rs. " + (t * cost));
        } else {
            System.out.println("Not enough seats available in selected class.");
        }
    }

    private void doCancel() throws Exception {
        System.out.print("Enter passenger number to cancel: ");
        int p = Integer.parseInt(br.readLine());

        int t_pno[] = new int[275];
        String t_name[] = new String[275];
        String t_phno[] = new String[275];
        int t_age[] = new int[275];
        int t_cl[] = new int[275];
        int t_pcount = 0;
        boolean passengerFound = false;

        for (int i = 0; i < pcount; i++) {
            if (pno[i] != p) {
                t_pno[t_pcount] = pno[i];
                t_name[t_pcount] = name[i];
                t_phno[t_pcount] = phno[i];
                t_age[t_pcount] = age[i];
                t_cl[t_pcount] = cl[i];
                t_pcount++;
            } else {
                passengerFound = true;
                int refund = switch (cl[i]) {
                    case 1 -> 1800;
                    case 2 -> 1500;
                    case 3 -> 1000;
                    default -> 0;
                };

                if (cl[i] == 1) max1++;
                else if (cl[i] == 2) max2++;
                else if (cl[i] == 3) max3++;

                System.out.println("Please collect refund of Rs. " + refund);
            }
        }

        if (passengerFound) {
            pno = t_pno;
            name = t_name;
            age = t_age;
            cl = t_cl;
            phno = t_phno;
            pcount = t_pcount;
            System.out.println("Ticket successfully cancelled.");
        } else {
            System.out.println("Passenger not found.");
        }
    }

    private void doDispList() {
        System.out.println("Passenger List:");
        displayByClass(1, "AC");
        displayByClass(2, "First");
        displayByClass(3, "Sleeper");
    }

    private void displayByClass(int classCode, String className) {
        System.out.println("\nClass: " + className);
        System.out.println("PNo\tName\t\tAge\tPhone");
        for (int i = 0; i < pcount; i++) {
            if (cl[i] == classCode) {
                System.out.println(pno[i] + "\t" + name[i] + "\t\t" + age[i] + "\t" + phno[i]);
            }
        }
    }

    private void doSearch() throws Exception {
        System.out.print("Enter passenger number to search: ");
        int p = Integer.parseInt(br.readLine());
        boolean found = false;

        for (int i = 0; i < pcount; i++) {
            if (pno[i] == p) {
                found = true;
                System.out.println("Passenger found:");
                System.out.println("PNo: " + pno[i]);
                System.out.println("Name: " + name[i]);
                System.out.println("Class: " + cl[i]);
                System.out.println("Phone: " + phno[i]);
                System.out.println("Age: " + age[i]);
                break;
            }
        }

        if (!found) {
            System.out.println("No such passenger found.");
        }
    }

    private void doDispUnbooked() {
        System.out.println("\nUnbooked Tickets:");
        System.out.println("AC Class: " + max1);
        System.out.println("First Class: " + max2);
        System.out.println("Sleeper Class: " + max3);
    }

    private void doExit() {
        System.out.println("=======================================");
        System.out.println("Project done by: Nikhil Falke");
        System.out.println("=======================================");
    }
    public static void main(String[] args) throws Exception {
        reservation res = new reservation();
        res.doMenu();
}
}