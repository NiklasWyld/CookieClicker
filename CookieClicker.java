/**
 * Cookie Clicker
 * 
 * @author Niklas
 * @version 1.0
 */

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.*;

public class CookieClicker
{
    static int cookie_count = 0;
    static int cookie_add = 1;
    
    public static void loadProgram(JLabel cookie_display) {
        User user = loadUser();
        
        cookie_count = user.cookies;
        cookie_add = user.CPC;
        cookie_display.setText("Current Cookies: " + cookie_count + " | CPC: " + cookie_add);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        
        JButton cookie = new JButton("Cookie");
        cookie.setBounds(10, 10, 100, 100);
        
        JButton buy = new JButton("Buy more CPC (1) | Cost: 50 Cookies");
        buy.setBounds(10, 200, 300, 40);
        
        JButton buy1 = new JButton("Buy more CPC (2) | Cost: 100 Cookies");
        buy1.setBounds(10, 250, 300, 40);
        
        JButton buy2 = new JButton("Buy more CPC (5) | Cost: 250 Cookies");
        buy2.setBounds(10, 300, 300, 40);
        
        JButton buyX2 = new JButton("Duplicate CPC | Cost: 100.000 Cookies");
        buyX2.setBounds(10, 350, 300, 40);
        
        JButton save = new JButton("Save");
        save.setBounds(350, 10, 100, 40);
        
        JLabel cookie_display = new JLabel("Current Cookies: " + cookie_count + " | CPC: " + cookie_add);
        cookie_display.setBounds(10, 120, 300, 20);
        
        // Load Program
        loadProgram(cookie_display);
        
        cookie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cookie_count = cookie_count + cookie_add;
                cookie_display.setText("Current Cookies: " + cookie_count + " | CPC: " + cookie_add);
            }
        });
        
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cookie_count - 50 > 0) {
                    cookie_count = cookie_count - 50;
                    cookie_add++;
                    cookie_display.setText("Current Cookies: " + cookie_count + " | CPC: " + cookie_add);
                } else {
                    cookie_display.setText("Not enough cookies!");
                }
            }
        });
        
        buy1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cookie_count - 100 > 0) {
                    cookie_count = cookie_count - 100;
                    cookie_add = cookie_add + 2;
                    cookie_display.setText("Current Cookies: " + cookie_count + " | CPC: " + cookie_add);
                } else {
                    cookie_display.setText("Not enough cookies!");
                }
            }
        });
        
        buy2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cookie_count - 250 > 0) {
                    cookie_count = cookie_count - 250;
                    cookie_add = cookie_add + 5;
                    cookie_display.setText("Current Cookies: " + cookie_count + " | CPC: " + cookie_add);
                } else {
                    cookie_display.setText("Not enough cookies!");
                }
            }
        });
        
        buyX2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cookie_count - 100000 > 0) {
                    cookie_count = cookie_count - 100000;
                    cookie_add = cookie_add * 2;
                    cookie_display.setText("Current Cookies: " + cookie_count + " | CPC: " + cookie_add);
                } else {
                    cookie_display.setText("Not enough cookies!");
                }
            }
        });
        
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User(cookie_count, cookie_add);
                writeUser(user);
            }
        });
        
        frame.add(cookie_display);
        frame.add(cookie);
        frame.add(buy);
        frame.add(buy1);
        frame.add(buy2);
        frame.add(buyX2);
        frame.add(save);
        
        frame.setSize(500, 500);
        frame.setTitle("Cookie Clicker");
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public static void writeUser(User user) {
        try (FileOutputStream fos = new FileOutputStream("cookie_clicker_user.ccu");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(user);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static User loadUser() {
        try (FileInputStream fis = new FileInputStream("cookie_clicker_user.ccu");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            User user = (User) ois.readObject();

            return user;
        } catch (IOException | ClassNotFoundException ex) {
            return new User(0, 1);
        }
    }
}


class User implements Serializable {
    public int cookies;
    public int CPC = 1;
    
    public User(int cookies, int CPC) {
        this.cookies = cookies;
        this.CPC = CPC;
    }
}
