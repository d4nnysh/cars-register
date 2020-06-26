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
            kierowcy.sort(Comparator.comparing(Driver::getNazwisko));
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
                if(d.findDriver(Long.parseLong(pesel.getText()))!=null){
                    Edit_driver ec= new Edit_driver(d, d.findDriver(Long.parseLong(pesel.getText())));
                }
            }
            else if(e.getSource()==dodaj){
                Edit_driver ed= new Edit_driver(d);
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
        private JLabel pesel, imie, nazwisko, l_samochodow; 
        private JButton edytuj= new JButton("Edytuj");
        private Driver k;

        
        public Driver_cart(Driver k){
            this.k=k;
            pesel=new JLabel("PESEL: "+k.getPesel());
            imie=new JLabel("Imie: "+k.getImie());
            nazwisko=new JLabel("Nazwisko: "+k.getNazwisko());
            try {
                l_samochodow=new JLabel("Liczba pojazdow: "+d.getCarsOfDriver(k.getPesel()).size());
            } catch (Exception e) {
                l_samochodow=new JLabel("Liczba pojazdow: 0");
            }
            this.setLayout(new GridLayout(1,0));
            this.add(pesel);
            this.add(imie);
            this.add(nazwisko);
            this.add(l_samochodow);
            this.add(edytuj);
            edytuj.addActionListener(this);
        }
        @Override
        public void actionPerformed(ActionEvent e) {            
            try {
                Edit_driver ed= new Edit_driver(d, d.findDriver(k.getPesel()));
            } catch (Exception x) {
                x.printStackTrace();
            }
        }

    }
}