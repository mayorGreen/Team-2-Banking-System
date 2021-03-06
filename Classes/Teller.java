package Classes;

// Teller class
public class Teller {
    
    private String username;
    private String password;

    public Teller(String username, String password){
        this.username = username;
        this.password = password;
    }

    // getters
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    
    // setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

} // end Teller