import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.awt.BorderLayout;
import java.awt.GridLayout;



import javax.swing.*;

public class Cars_window extends JFrame implements ActionListener{
    private Db_connect d;
    private ArrayList<Car> samochody;
    private JPanel panel, top_panel;
    private JButton wroc;

    public Cars_window(Db_connect d){
        this.d=d;
        try {
            setBounds(100, 20, 1000, 666);
            setTitle("Menu samochodow");        
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            samochody=d.getCarsList();
            samochody.sort(Comparator.comparing(Car::getRejestracja));
            panel = new JPanel();
            top_panel = new JPanel();
            JPanel center_panel= new JPanel();
            center_panel.setLayout(new GridLayout(0,1));
            panel.setLayout(new BorderLayout());

            wroc= new JButton("Wroc");
            wroc.addActionListener(this);

            panel.add(wroc, BorderLayout.SOUTH);
            samochody.forEach(x -> center_panel.add(new Car_cart(x)));
            JScrollPane scroll= new JScrollPane(center_panel);

            panel.add(scroll, BorderLayout.CENTER);

            getContentPane().add(panel);            
            setVisible(true);
            


        } catch (Exception e) {
            //TODO: handle exception
        }
        


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==wroc){
            setVisible(false);
        }
       
    }
    class Car_cart extends JPanel implements ActionListener{
        private JLabel rejestracja, marka, model, rok, przebieg; 
        private JButton edytuj= new JButton("Edytuj");

        
        public Car_cart(Car s){
            rejestracja=new JLabel("Rejestracja: "+s.getRejestracja());
            marka=new JLabel("Marka: "+s.getMarka());
            model=new JLabel("Model: "+s.getModel());
            rok=new JLabel("Rok: "+s.getRok());
            przebieg=new JLabel("Przebieg: "+s.getPrzebieg());
            this.setLayout(new GridLayout(1,0));
            this.add(rejestracja);
            this.add(marka);
            this.add(model);
            this.add(rok);
            this.add(przebieg);
            this.add(edytuj);
            edytuj.addActionListener(this);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==edytuj){
                System.out.println("XD");
            }
        
        }

    }
}