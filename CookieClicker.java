/**
 * Cookie Clicker
 * 
 * @author Niklas
 * @version BETA
 */

import javax.swing.*;
import java.awt.event.*;

public class CookieClicker
{
    static int cookie_count = 0;
    static int cookie_add = 1;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        
        JButton cookie = new JButton("Cookie");
        cookie.setBounds(10, 10, 100, 100);
        
        JButton buy = new JButton("Buy more CPC | Cost: 50 Cookies");
        buy.setBounds(10, 300, 250, 40);
        
        JLabel cookie_display = new JLabel("Current Cookies: " + cookie_count + " | CPC: " + cookie_add);
        cookie_display.setBounds(10, 120, 300, 20);
        
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
        
        frame.add(cookie_display);
        frame.add(cookie);
        frame.add(buy);
        
        frame.setSize(500, 500);
        frame.setTitle("Cookie Clicker");
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
