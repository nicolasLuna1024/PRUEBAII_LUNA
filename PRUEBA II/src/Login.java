import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    private JTextField textUser;
    private JPasswordField textPass;
    private JButton LOGINButton;
    private JPanel panel1;

    public Login(){
        super("Login");
        setSize(400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel1);



        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String user = textUser.getText();
                String passwordStr = new String(textPass.getPassword());
                try {
                    if (VerificarCredenciales(user,passwordStr)){

                        Menu menu = new Menu(); // LLAMAR A LA VETANA MENU
                        menu.iniciar();
                        dispose();
                        //JOptionPane.showMessageDialog(null,"LOGIN EXITOSO");

                    }else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

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
    // ProbarConexion
    public void probarConexion() {
        try (Connection conn = conexionBase()) {
            if (conn != null) {
                JOptionPane.showMessageDialog(null, "Conexión a la base de datos exitosa");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + e.getMessage());
        }
    }
    // Metodo verificar
    public boolean VerificarCredenciales(String username, String password) throws SQLException {

        Connection conecta = conexionBase();
        String query = "SELECT * FROM USUARIO WHERE username = ? AND password = ?";
        PreparedStatement pstmt= conecta.prepareStatement(query);
        pstmt.setString(1,username);
        pstmt.setString(2,password);

        ResultSet rs = pstmt.executeQuery();
        boolean resultado = ((ResultSet) rs).next(); // true si hay un resultado

        rs.close();
        pstmt.close();
        conecta.close();

        return resultado;
    }

    //Meetodo para llamar a la ventana
    public void iniciarLogin(){
        setVisible(true);
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
