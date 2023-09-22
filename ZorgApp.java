class ZorgApp {
    public static void main(String[] args) {
        User user = new User(1, "Dr. Hans Camora"); // Maak een gebruiker aan
        Administration administration = new Administration(user);

        // Voeg nieuwe gebruikers toe
        administration.addUser("Fysiotherapeut Anne Louise");
        administration.addUser("Huisarts Bob Metselaar");
        administration.addUser("Tandarts Jacoba Helena");
        administration.addUser("Apotheker Dirk Kat");

        administration.menu();
    }
}
