import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Buscar extends JFrame {
    private JTextField textBuscarCedula;
    private JButton botonLogin;
    private JButton botonBuscar;
    private JButton botonIngresar;
    private JPanel panelBuscar;
    private JTextArea buscarArea;

    public Buscar(){
        super("BUSCAR PACIENTE");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelBuscar);


        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerRegistro();

            }
        });


        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ingreso ingreso2 = new Ingreso();
                ingreso2.iniciar2();
                dispose();
            }
        });


        botonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login2 = new Login();
                login2.iniciarLogin();
                dispose();
            }
        });
    }


    // Metodo Conexion base
    public Connection conexionBase() throws SQLException {
        String url ="jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user = "root";
        String password = "12345";
        return DriverManager.getConnection(url,user,password);
    }
    // Metodo para Buscar paciente
    public void VerRegistro(){
        try{
            Connection conn = conexionBase();
            String query = "select * from;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            buscarArea.setText(" ");
            while (rs.next()){
                String Cedula = rs.getString("cedula");
                Integer HistorialClinico = rs.getInt("n_historial_clinico");
                String Nombre = rs.getString("nombre");
                String Apellido = rs.getString("apellido");
                String Telefono = rs.getString("telefono");
                Integer Edad = rs.getInt("edad");
                String Descripcion = rs.getString("descripcion_enfermedad");


                buscarArea.append("Cedula: " + Cedula + "\n");
                buscarArea.append("Numero Historial: " + HistorialClinico + "\n");
                buscarArea.append("Nombre: " + Nombre + "\n");
                buscarArea.append("Apellido: " + Apellido + "\n");
                buscarArea.append("Telefono: " + Telefono + "\n");
                buscarArea.append("Edad: " + Edad + "\n");
                buscarArea.append("Descripcion Enfermedad: " +Descripcion + "\n");
                buscarArea.append("\n");
            }
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    //Meetodo para llamar a la ventana
    public void iniciar3(){
        setVisible(true);
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
