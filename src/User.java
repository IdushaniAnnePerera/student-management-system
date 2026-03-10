import java.util.ArrayList;
import java.util.Arrays;

public abstract class User {
    private String email;
    private String password;

    public User(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract String[] getUserRoles();

    public boolean hasUserRole(String role) {
        for (String userRole : getUserRoles()) {
            if(userRole.equals(role)) return true;
        }

        return false;
    }
}

