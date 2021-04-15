package Classes;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.CardLayout;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.HashMap;

public class Manager {

    private String username;
    private String password;
    public Manager(){
        return;
    }

    
    public String getUserName(){return username;}
    public String getPassword(){return password;}
    public void listCustomers(){return;}

    //Constructor
    public Manager(String userid) {
        userid = getUserName();
    }

}
