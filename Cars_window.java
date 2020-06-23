import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Cars_window extends JFrame implements ActionListener{
    private Db_connect d;
    private ArrayList<Car> samochody;

    public Cars_window(Db_connect d){
        this.d=d;
        try {
            samochody=d.getCarsList();
            samochody.forEach(x -> System.out.println(x.toString()));
        } catch (Exception e) {
            //TODO: handle exception
        }
        


    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    class Car_cart{
        JButton edit= new JButton("Edytuj");

    }
}