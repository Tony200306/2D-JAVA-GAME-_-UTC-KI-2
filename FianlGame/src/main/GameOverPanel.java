package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverPanel implements ActionListener {
     static  JFrame frame = new JFrame();
    public GameOverPanel( String x) {


     JPanel panel = new JPanel();

        frame.setSize(200,200);
        frame.setLocationRelativeTo(null);
        frame.setTitle(x);


        JLabel label = new JLabel(x);
        frame.add(panel);
        panel.setLayout(null);
        label.setBounds(50,10,100,50);
        panel.add(label);

        JButton button = new JButton("EXIT");
        button.setBounds(55,80,80,25);
        button.addActionListener(this);
        panel.add(button);


        frame.setVisible(true);


    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        System.out.println("hi");
        frame.dispose();
        
        System.exit(0);
	}

//    public void setGameOver(boolean gameFinished) {
//        label.setVisible(gameFinished);
//    }
}