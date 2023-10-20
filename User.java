class User {

    int userID;
    String userName;
    String userRole;

    public User(int userID, String userRole, String name) {
        this.userID = userID;
        this.userName = name;
        this.userRole = userRole;
    }

    String getUserName() {
        return userName;
    }

    int getUserID() {
        return userID;
    }

    String getUserRole() {
        return userRole;
    }

}
