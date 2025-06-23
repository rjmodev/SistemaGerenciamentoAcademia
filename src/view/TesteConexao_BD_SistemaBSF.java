package view;

import connection.ConexaoMySQL;

public class TesteConexao_BD_SistemaBSF {
    public static void main(String[] args) {
        System.out.println("\n🔗 Testando conexão com banco de dados...");

        ConexaoMySQL.getInstance();
        System.out.println(ConexaoMySQL.statusConection());

        if (ConexaoMySQL.FecharConexao()) {
            System.out.println("Conexão fechada com sucesso!");
        } else {
            System.out.println("Erro ao fechar conexão.");
        }
    }
}
