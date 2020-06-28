import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.awt.BorderLayout;
import java.awt.GridLayout;



import javax.swing.*;

public class Cars_window extends JFrame implements ActionListener{
    private Db_connect d;
    private ArrayList<Car> samochody;
    private JPanel panel, top_panel, center_panel;
    private JScrollPane scroll;
    private JButton wroc, szukaj, dodaj, odswiez;
    private JLabel wpisz= new JLabel("Wpisz rejestracje");
    private JTextField rejestracja= new JTextField();
    private Main_window mw;

    public Cars_window(Db_connect d, Main_window mw){
        this.d=d;
        this.mw=mw;
        try {
            setBounds(100, 20, 1000, 666);
            setTitle("Menu samochodow");        
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            samochody=d.getCarsList();
            samochody.sort(Comparator.comparing(Car::getRejestracja));
            panel = new JPanel();
            top_panel = new JPanel();
            center_panel= new JPanel();

            top_panel.setLayout(new GridLayout(1,0));
            center_panel.setLayout(new BoxLayout(center_panel, 1));

            panel.setLayout(new BorderLayout());

            wroc= new JButton("Wroc");
            szukaj= new JButton("Szukaj");
            dodaj= new JButton("Dodaj samochod");
            odswiez= new JButton("Odswiez");
            wroc.addActionListener(this);
            szukaj.addActionListener(this);
            dodaj.addActionListener(this);
            odswiez.addActionListener(this);

            for (Car car : samochody) {
                center_panel.add(new Car_cart(car, this));
                center_panel.add(new JSeparator());
            }
            scroll= new JScrollPane(center_panel);
            
            top_panel.add(wpisz);
            top_panel.add(rejestracja);
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
                dispose();
                mw.odswiez();
            }
            else if(e.getSource()==szukaj){
                if(d.findCar(rejestracja.getText())!=null){
                    Edit_car ec= new Edit_car(d, d.findCar(rejestracja.getText()), this);
                }
                else{
                    Alert_window aw= new Alert_window(3);
                }
            }
            else if(e.getSource()==dodaj){
                Edit_car ec= new Edit_car(d, this);
            }
            else if(e.getSource()==odswiez){
               odswiez();
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
        
    }
    public void odswiez(){
        Cars_window cw= new Cars_window(d, mw);
        dispose();
    }

    class Car_cart extends JPanel implements ActionListener{
        private JLabel rejestracja, marka, model, rok, przebieg; 
        private JButton edytuj= new JButton("Edytuj");
        private Car s;
        private Cars_window cw;
        
        public Car_cart(Car s, Cars_window cw){
            this.s=s;
            this.cw=cw;
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
            try {
                Edit_car ec= new Edit_car(d, d.findCar(s.getRejestracja()), cw);
            } catch (Exception x) {
                x.printStackTrace();
            }
        }

    }
}