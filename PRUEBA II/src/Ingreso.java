import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ingreso extends JFrame {
    private JTextField textCedula;
    private JTextField textNclinico;
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textTelefono;
    private JTextField textEdad;
    private JTextField textEnfermedad;
    private JPanel panelIngreso;
    private JButton botonIngresar;
    private JButton botonBuscar;

    public Ingreso(){
        super("Ingreso Pacientes");
        setContentPane(panelIngreso);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);

        botonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InsertarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Buscar buscar2 = new Buscar();
                buscar2.iniciar3();
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

    // Metodo Ingresar
    public void InsertarDatos() throws SQLException {
        String cedula = textCedula.getText();
        String numeroHistorial = textNclinico.getText();
        String nombre = textNombre.getText();
        String apellido = textApellido.getText();
        String telefono = textTelefono.getText();
        String edad = textEdad.getText();
        String descripcion = textEnfermedad.getText();

        Connection conecta =conexionBase();

        String query ="INSERT INTO PACIENTE(cedula,n_historial_clinico,nombre,apellido,telefono,edad,descripcion_enfermedad)values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conecta.prepareStatement(query);
        pstmt.setString(1,cedula);
        pstmt.setInt(2,Integer.parseInt(numeroHistorial));
        pstmt.setString(3,nombre);
        pstmt.setString(4,apellido);
        pstmt.setString(5,telefono);
        pstmt.setInt(6,Integer.parseInt(edad));
        pstmt.setString(7,descripcion);


        // ACTUALIZAR

        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0){
            JOptionPane.showMessageDialog(null,"REGISTRO INSERTADO CORRECTAMENTE");
        }
        pstmt.close();
        conecta.close();

    }



    //Meetodo para llamar a la ventana
    public void iniciar2(){
        setVisible(true);
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
