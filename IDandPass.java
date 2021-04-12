import java.util.HashMap;

public class IDandPass {
    HashMap<String,String> loginInfo = new HashMap<>();

    IDandPass(){
        loginInfo.put("person1", "password");
        loginInfo.put("person2", "password2");
        loginInfo.put("person3", "password3");
        loginInfo.put("a", "a");
    }

    protected HashMap getLoginInfo() {
        return loginInfo;
    }
}
