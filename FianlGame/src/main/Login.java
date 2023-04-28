package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Login extends JPanel implements ActionListener {

    static String  user ;
    static String pass ;
    static JTextField userText = new JTextField();
    static JPasswordField passwordText = new JPasswordField();

    static JLabel JLabellabelFAIL = new JLabel("Ban nhap sai mat khau hoac username");


    static String StoredUsername ;
    static String StoredPassword ;
    static JFrame frame = new JFrame();
     static JFrame window = new JFrame();
    JPanel panel = new JPanel();



    public void Handle_Data(){

//      panel.setResizable(false);
        frame.setSize(400,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);

        panel.setLayout(null);



        JLabel userlabel = new JLabel("User");
        userlabel.setBounds(10,20,80,25);
        panel.add(userlabel);



        userText.setBounds(100,20,165,25);
        panel.add(userText);


        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,60,80,25);
        panel.add(passwordLabel);

        passwordText.setBounds(100,60 ,165,25);
        panel.add(passwordText);



        JButton button = new JButton("Login");
        button.setBounds(10,80,80,25);
        button.addActionListener(this);
        panel.add(button);

        JLabellabelFAIL.setBounds(100,20,165,25);
        panel.add(JLabellabelFAIL);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        user = userText.getText();
        pass = passwordText.getText();
        try {
            ResultSet resultSet = null ;
            ResultSet resultSet1 = null ;
            //Tạo kết nối
            Connection connection = DataBase.getConnection();
            // Statement
            Statement statement = connection.createStatement();

            String sql1 = " SELECT * FROM `highscore`";

            resultSet1 = statement.executeQuery(sql1);

            String[] StoredUsername1 = new String[10];


            //validate
            int i =0 ;
            int count = 0 ;
            while (resultSet1.next()){
                StoredUsername1[i]= resultSet1.getString("Player");
                System.out.println(StoredUsername1[i]);
                i++ ;
                count++ ;
            }
            System.out.println(i);
            int flag = 0 ;
            int x= 0 ;
            while (x<i){
                if(StoredUsername1[x].equals(userText.getText())){
                    user=StoredUsername1[x];
                    flag ++;
                    break;
                }
                x++;
            }
            if(flag== 0){
JFrame frame1 = new JFrame();

                JLabellabelFAIL.setBounds(20,120 ,300,25);
                panel.add(JLabellabelFAIL);
            }
            else {
                JLabellabelFAIL.setBounds(100,20,165,25);
                panel.add(JLabellabelFAIL);
            }


                String sql = " SELECT * FROM `highscore` where Player= '" + user + "'; ";
                resultSet = statement.executeQuery(sql);
                resultSet.next() ;// rs.next() : Chuyen con tro den hang tiep theo cua cot truy van
                StoredUsername = resultSet.getString("Player");
                StoredPassword=resultSet.getString("Password");


            if(pass.equals(StoredPassword)){

                System.out.println(StoredUsername);
                System.out.println(StoredPassword);
                System.out.println("true");
                Login.frame.dispose();


                    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    window.setResizable(false);
                    window.setTitle("Game");


                    GamePanel gamePanel = new GamePanel();
                    window.add(gamePanel);

                    window.pack();
                    window.setLocationRelativeTo(null);
                    window.setVisible(true);
                    gamePanel.startGameThread();
            }
            else {
                JLabellabelFAIL.setBounds(20,120 ,300,25);
                panel.add(JLabellabelFAIL);
            }
            //CLOSE
            DataBase.closeConnection(connection);
        } catch (SQLException er) {
            er.printStackTrace();
        } 
    }
}