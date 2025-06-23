package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

    public static String status = "Não conectou...";

    public ConexaoMySQL(){
    }

    public static Connection getInstance() {
        Connection connection = null;
        try {
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);

            String serverName = "localhost";
            String mydatabase = "academiaBSF";
            String url = "jdbc:mysql://" + serverName + ":3306/" + mydatabase + "?useSSL=false&serverTimezone=UTC";
            //String url = "jdbc:mysql://" + serverName + ":3306/" + mydatabase + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
            String username = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                status = "STATUS--->Conectado com sucesso!";
            } else {
                status = "STATUS--->Não foi possível realizar conexão";
            }
            return connection;
        } catch (ClassNotFoundException ex) {
            System.out.println("O driver especificado não foi encontrado.");
            return null;
        } catch (SQLException ex2) {
            System.out.println("Erro ao tentar conectar ao banco de dados: " + ex2.getMessage());
            return null;
        }
    }
    public static String statusConection() {
        return status;
    }

    public static boolean FecharConexao() {
        try {
            ConexaoMySQL.getInstance().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return ConexaoMySQL.getInstance();
    }
}
