 import java.util.*;

 class ZorgApp {
        public static void main(String[] args) {
            System.out.println(" ");
            User user = new User(4, "Dr. Hans Camora"); //Maak een gebruiker aan
            Administration administration = new Administration(user);

            //Voeg nieuwe gebruikers toe
            administration.addUser("Fysiotherapeut William Riker");
            administration.addUser("Huisarts Bob Metselaar");
            administration.addUser("Tandarts Jabba de Hut");
            administration.addUser("Apotheker Dirk Kat");
            administration.addUser("Dr Hans Camora");


            //Laat de lijst van gebruikers zien voor laten zien van hoofdmenu
            administration.listUsers();


            // Gebruikerswissel
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


            //Nu het hoofdmenu
            administration.menu();
        }
 }