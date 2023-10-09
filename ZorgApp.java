import java.util.*;

class ZorgApp {

    public static void main(String[] args) {
        String RESET = "\u001B[0m";
        String RED_TEXT = "\u001B[31m";
        String GREEN_TEXT = "\u001B[32m";
        String YELLOW_TEXT = "\u001B[33m";
        String BLUE_TEXT = "\u001B[34m";
        String PURPLE_TEXT = "\u001B[35m";
        String CYAN_TEXT = "\u001B[36m";



        System.out.println(" ");
        System.out.format("%s\n", "=".repeat(60));
        User user = new User(4, "Dr. Melvin Bos");
        Administration administration = new Administration(user);

        //Voeg nieuwe gebruikers toe
        administration.addUser("Fysiotherapeut William Riker");
        administration.addUser("Huisarts Bob Metselaar");
        administration.addUser("Tandarts Jabba de Hut");
        administration.addUser("Apotheker Dirk Kat");
        administration.addUser("Dr Melvin Bos");


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
                } else {
                    System.out.println("Ongeldige gebruikers-ID. Probeer opnieuw.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ongeldige invoer. Voer een geldig geheel getal in voor de gebruikers-ID.");
                scanner.next(); // Schoonmaak v scanner om foute invoer te wissen
            }
        }

        administration.menu();

        System.out.println(" ");
        System.out.println(YELLOW_TEXT + "Applicatie afsluiten..." + RESET);
        System.out.println(" ");
        System.out.println(GREEN_TEXT + "Applicatie afgesloten" + RESET);
    }
}