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

            //Laat de lijst van gebruikers zien voor laten zien van hoofdmenu
            administration.listUsers();


            // Gebruikerswissel
            boolean userSwitched = false;
            while (!userSwitched) {
                System.out.print("Kies een gebruiker om naar te wisselen (gebruikers-ID): ");
                Scanner scanner = new Scanner(System.in);
                int switchUserId = scanner.nextInt();

                if (administration.switchUser(switchUserId)) {
                    userSwitched = true;
                    System.out.println("Gewisseld naar gebruiker: " + administration.currentUser.getUserName());
                } else {
                    System.out.println("Ongeldige gebruikers-ID. Probeer opnieuw.");
                }
            }

            //Nu het hoofdmenu
            administration.menu();
        }
 }