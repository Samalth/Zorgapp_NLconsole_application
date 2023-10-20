import java.util.*;
class ZorgApp {

    public static void main(String[] args) {
        String RESET = "\u001B[0m";
        String RED_TEXT = "\u001B[31m";
        String GREEN_TEXT = "\u001B[32m";
        String YELLOW_TEXT = "\u001B[33m";
        String BLUE_TEXT = "\u001B[34m";
        String PURPLE_TEXT = "\u001B[35m";

        System.out.println(" ");
        System.out.format("%s\n", "=".repeat(60));
        User user = new User(7, "Dokter-Chirurg","Melvin Bos");
        Administration administration = new Administration(user);

        administration.addUser(new User(1,"Fysiotherapeut", "William Riker"));
        administration.addUser(new User(2,"Huisarts", "Bob Metselaar"));
        administration.addUser(new User(3,"Tandarts","Jabba de Hut"));
        administration.addUser(new User(5,"Apotheker","Dirk Kat"));
        administration.addUser(new User(7,"Dokter-Chirurg","Melvin van de Tak"));

        administration.listUsers();

        boolean userSwitched = false;
        while (!userSwitched) {
            System.out.print("Kies een gebruiker om naar te wisselen (gebruikers-ID): ");
            Scanner scanner = new Scanner(System.in);
            try {
                int switchUserId = scanner.nextInt();

                if (administration.switchUser(switchUserId)) {
                    userSwitched = true;
                    System.out.println("Gewisseld naar gebruiker: " + administration.currentUser.getUserName());
                    System.out.println(" ");
                } else {
                    System.out.println("Ongeldige gebruikers-ID. Probeer opnieuw.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ongeldige invoer. Voer een geldig geheel getal in voor de gebruikers-ID.");
                scanner.next();
            }
        }

        administration.menu();

        System.out.println(" ");
        System.out.println(YELLOW_TEXT + "Applicatie afsluiten..." + RESET);
        System.out.println(" ");
        System.out.println(GREEN_TEXT + "Applicatie afgesloten" + RESET);
    }
}
