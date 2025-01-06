package controllers;

public class UserSession {
    private static UserSession instance;
    private String email;
    private String userId;  // Make sure this is a String, not int

    // Private constructor to prevent instantiation
    private UserSession() {}

    // Get the single instance of UserSession
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    // Set and get methods for email and userId
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUserId(String userId) {  // Ensure userId is a String
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
