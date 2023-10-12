class User {

    int userID;
    String userName;

    public User(int id, String name) {
        this.userID = id;
        this.userName = name;
    }

    String getUserName() {
        return userName;
    }

    int getUserID() {
        return userID;
    }
}
