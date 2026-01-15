
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Adm
 */
public class conectaDAO {

    private Connection conn;

    private final String URL = "jdbc:mysql://localhost:3306/leiloes?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private final String USER = "root";
    private final String PASSWORD = "JFK2020/pmw";

    /**
     * Estabelece uma conexão com a base de dados MySQL. O método carrega o
     * driver JDBC, tenta estabelecer a ligação utilizando as credenciais
     * configuradas e inicializa o objeto Statement.
     *
     * @return Connection O objeto de conexão estabelecida, ou o estado atualda
     * variável 'conn' em caso de erro.
     */
    public Connection connectDB() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver MySQL JDBC carregado com sucesso!");

            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            return conn;

        } catch (ClassNotFoundException ex) {
            System.err.println("ERRO: O Driver não está disponível na biblioteca (Classpath).");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + ex.getMessage());
            System.err.println("ERRO de Banco de Dados (SQL):");
            System.err.println("Código: " + ex.getSQLState());
            System.err.println("Mensagem: " + ex.getMessage());
        }
            return conn;
    }

    /**
     * Fecha a conexão recebida como parâmetro.
     *
     * @param conn A conexão a ser fechada.
     */
    public void disconnectDB(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexão encerrada com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }

}
