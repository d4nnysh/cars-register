import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.awt.BorderLayout;
import java.awt.GridLayout;



import javax.swing.*;

public class Drivers_window extends JFrame implements ActionListener{
    private Db_connect d;
    private ArrayList<Driver> kierowcy;
    private JPanel panel, top_panel, center_panel;
    private JScrollPane scroll;
    private JButton wroc, szukaj, dodaj, odswiez;
    private JLabel wpisz= new JLabel("Wpisz pesel");
    private JTextField pesel= new JTextField("xxxxxx");

    public Drivers_window(Db_connect d){
        this.d=d;
        try {
            setBounds(100, 20, 1000, 666);
            setTitle("Menu kierowcow");        
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            kierowcy=d.getDriversList();
            kierowcy.sort(Comparator.comparing(Driver::getPesel));
            panel = new JPanel();
            top_panel = new JPanel();
            center_panel= new JPanel();

            
            center_panel.setLayout(new BoxLayout(center_panel, 1));

            panel.setLayout(new BorderLayout());

            wroc= new JButton("Wroc");
            szukaj= new JButton("Szukaj");
            dodaj= new JButton("Dodaj kierowce");
            odswiez= new JButton("Odswiez");
            wroc.addActionListener(this);
            szukaj.addActionListener(this);
            dodaj.addActionListener(this);
            odswiez.addActionListener(this);


            kierowcy.forEach(x -> center_panel.add(new Driver_cart(x)));
            for (Driver driver : kierowcy) {
                center_panel.add(new Driver_cart(driver));
                center_panel.add(new JSeparator());
            }
            scroll= new JScrollPane(center_panel);

            
            top_panel.add(wpisz);
            top_panel.add(pesel);
            top_panel.add(szukaj);
            top_panel.add(dodaj);
            top_panel.add(odswiez);
            
            panel.add(wroc, BorderLayout.SOUTH);
            panel.add(scroll, BorderLayout.CENTER);
            panel.add(top_panel, BorderLayout.NORTH);

            getContentPane().add(panel);            
            setVisible(true);
            


        } catch (Exception e) {
            e.printStackTrace();
        }
        


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource()==wroc){
                setVisible(false);
            }
            else if(e.getSource()==szukaj){
                
            }
            else if(e.getSource()==dodaj){
                
            }
            else if(e.getSource()==odswiez){
                Drivers_window dw= new Drivers_window(d);
                this.dispose();
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
        
    }
    class Driver_cart extends JPanel implements ActionListener{
        private JLabel rejestracja, marka, model, rok, przebieg; 
        private JButton edytuj= new JButton("Edytuj");
        private Driver d;

        
        public Driver_cart(Driver d){
            this.d=d;
            // rejestracja=new JLabel("Rejestracja: "+s.getRejestracja());
            // marka=new JLabel("Marka: "+s.getMarka());
            // model=new JLabel("Model: "+s.getModel());
            // rok=new JLabel("Rok: "+s.getRok());
            // przebieg=new JLabel("Przebieg: "+s.getPrzebieg());
            this.setLayout(new GridLayout(1,0));
            // this.add(rejestracja);
            // this.add(marka);
            // this.add(model);
            // this.add(rok);
            // this.add(przebieg);
            this.add(edytuj);
            edytuj.addActionListener(this);
        }
        @Override
        public void actionPerformed(ActionEvent e) {            
            try {
                // Edit_car ec= new Edit_car(d, d.findCar(s.getRejestracja()));
            } catch (Exception x) {
                x.printStackTrace();
            }
        }

    }
}