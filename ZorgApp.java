import java.util.*;
class ZorgApp {

    public static void main(String[] args) {
        String RESET = "\u001B[0m";
        String GREEN_TEXT = "\u001B[32m";
        String YELLOW_TEXT = "\u001B[33m";
        String BLUE_TEXT = "\u001B[34m";
        String PURPLE_TEXT = "\u001B[35m";

        System.out.println(" ");
        System.out.format("%s\n", "=".repeat(60));
        System.out.println(" ");
        System.out.println(GREEN_TEXT + "Welkom" + RESET);
        System.out.println(" ");
        User user = new User(7, "Dokter-Chirurg","Melvin van de Tak");
        Administration administration = new Administration(user);

        administration.addUser(new User(1,"Fysiotherapeut", "Willem Rijker"));
        administration.addUser(new User(2,"Huisarts", "Bob Metselaar"));
        administration.addUser(new User(3,"Tandarts","Joop de Hut"));
        administration.addUser(new User(5,"Apotheker","Stan de Water"));
        administration.addUser(new User(7,"Dokter-Chirurg","Melvin van de Tak"));
        administration.addUser(new User(99,"SB","Systeem Beheer"));

        administration.listUsers();

        boolean userSwitched = false;
        while (!userSwitched) {
            System.out.print("Kies een gebruiker om naar te wisselen (gebruikers-ID): ");
            Scanner scanner = new Scanner(System.in);
            try {
                int switchUserId = scanner.nextInt();

                if (administration.switchUser(switchUserId)) {
                    userSwitched = true;
                    System.out.format("Gewisseld naar gebruiker: %s %s\n", administration.currentUser.getUserRole(), administration.currentUser.getUserName());
                    System.out.println(" ");
                } else {
                    System.out.println("Ongeldige gebruikers-ID. Probeer opnieuw.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ongeldige invoer. Voer een geldig gebruikers-ID in.");
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
