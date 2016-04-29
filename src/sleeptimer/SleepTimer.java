
package sleeptimer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SleepTimer {

    private static int time;
    
    public static void main(String[] args) {
        
        // Creates all the components
        JFrame frame = new JFrame("When do you want to go to sleep?");
        JPanel panel = new JPanel();
        JButton button = new JButton("Sleep");
        JTextField field = new JTextField(10);
        JRadioButton seconds = new JRadioButton("Seconds");
        JRadioButton minutes = new JRadioButton("Minutes");
        JRadioButton hours = new JRadioButton("Hours");
        ButtonGroup timeType = new ButtonGroup();
        
        // adds properties for the frame
        frame.setSize(400, 150);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // JPanel stuff
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(null);
        
        // Field Stuff
        field.setBounds(30, 25, 150, 20);
        
        // Button Group and JRadioButton adding
        seconds.setBounds(30, 60, 75, 20);
        seconds.setBackground(Color.DARK_GRAY);
        seconds.setForeground(Color.WHITE);
        
        minutes.setBounds(120, 60, 70, 20);
        minutes.setBackground(Color.DARK_GRAY);
        minutes.setForeground(Color.WHITE);
        
        hours.setBounds(210, 60, 60, 20);
        hours.setBackground(Color.DARK_GRAY);
        hours.setForeground(Color.WHITE);
        
        timeType.add(seconds);
        timeType.add(minutes);
        timeType.add(hours);
        
        // Button stuff
        button.setBounds(225, 25, 90, 30);
        button.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Determines the time you need to sleep for
                if (seconds.isSelected()){
                    time = Integer.parseInt(field.getText()) * 1000;
                    sleep();
                } else if (minutes.isSelected()){
                    time = Integer.parseInt(field.getText()) * 1000 * 60;
                    sleep();
                } else if (hours.isSelected()){
                    time = Integer.parseInt(field.getText()) * 1000 * 60 * 60;
                    sleep();
                } else {
                    JOptionPane.showMessageDialog(null, "You Haven't Selected a Proper Time", "Common Man", time);
                }
            }
        });
        
        // Adding evertyhing together
        panel.add(field);
        panel.add(seconds);
        panel.add(minutes);
        panel.add(hours);
        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);
    }
    
    public static void sleep(){
        // Sleeps the thread then sleeps the computer
        try {
            Thread.sleep(time);
            Runtime rt = Runtime.getRuntime();
            rt.exec("cmd.exe /c start rundll32.exe powrprof.dll,SetSuspendState 0,1,0");
        } catch (IOException ex) {
            Logger.getLogger(SleepTimer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SleepTimer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
