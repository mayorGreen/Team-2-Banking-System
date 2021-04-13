package Classes;
import java.util.HashMap;

public class IDandPass {
    HashMap<String,String> loginInfo = new HashMap<>();
    public int password;
    public String username;

    public IDandPass(){
        loginInfo.put("person1", "password");
        loginInfo.put("person2", "password2");
        loginInfo.put("person3", "password3");
        loginInfo.put("a", "a");
        loginInfo.put(username, String.valueOf(password));
    }

    public IDandPass(String username, int password)
    {
        this.username = username;
        this.password = password;
        loginInfo.put(username, String.valueOf(password));
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashMap getLoginInfo() {
        return loginInfo;
    }
}
