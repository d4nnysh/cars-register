import javax.swing.*;
import java.awt.event.*;
import java.awt.GridLayout;
/**
 * Okno sluzace do alertowania uzytkownika
 */
public class Alert_window extends JFrame implements ActionListener{
    private JLabel informacja = new JLabel(); 
    private JButton ok= new JButton("OK");
    private JPanel panel= new JPanel();
    private int kod;
    public Alert_window(int kod){
        this.kod=kod;
        setBounds(100, 20, 300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Uwaga!");
        switch (kod) {
            case 0:
            informacja.setText("Nie znaleziono takiego kierowcy");    
            break;
            case 1:
            informacja.setText("Nieprawidlowy PESEL"); 
            break;
            case 2:
            informacja.setText("Nie znaleziono takiego samochodu");
            break;
            case 3:
            informacja.setText("Nieprawidlowa rejestracja");    
            break;
            case 4:
            informacja.setText("Wpisz liczby w polach rok oraz przebieg");    
            break;
        }
        informacja.setFont(informacja.getFont().deriveFont(15f));
        ok.setFont(informacja.getFont().deriveFont(15f));
        ok.addActionListener(this);
        panel.setLayout(new GridLayout(0,1));
        panel.add(informacja);
        panel.add(ok);
        getContentPane().add(panel); 
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==ok){
            dispose();
        }
    }
}