import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{

    private JPanel panelMenu;
    private JButton botonRegistrar;
    private JButton botonBuscar;

    public Menu(){
        super("Menu Acceso");
        setContentPane(panelMenu);
        setSize(300,300);


        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ingreso ingreso = new Ingreso();
                ingreso.iniciar2();
                dispose();
            }
        });
        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Buscar buscar = new Buscar();
                buscar.iniciar3();
                dispose();
            }
        });
    }





    // Metodo para Iniciar la ventana desde otra venta
    public void iniciar(){
        setVisible(true);
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
