public class User {
    private final String username;

    public User(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username must not be blank");
        }
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

