package controller;

import connection.ConexaoMySQL;
import model.Treino;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ControlaTreino {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // TODO: Método para inserir um treino no banco de dados
    public static void adicionarTreino(Treino treino) {
        Connection conn = ConexaoMySQL.getInstance();

        try {
            String sql = "INSERT INTO treino_bsf (nome, data_execucao, usuario_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, treino.getNome());
            stmt.setDate(2, Date.valueOf(treino.getDataExecucao()));
            stmt.setInt(3, treino.getUsuarioId());
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n✅ Treino adicionado com sucesso!");
    }
    //TODO: Atualiza treino no banco
    public void atualizarTreino(Treino treino) {
        Connection conn = ConexaoMySQL.getInstance();
        try{
            String sql = "UPDATE treino_bsf SET nome = ?, data_execucao = ?, usuario_id = ? WHERE id = ?";
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString(1, treino.getNome());
            smt.setDate(2,Date.valueOf(treino.getDataExecucao()));
            smt.setInt(3, treino.getUsuarioId());
            smt.setInt(4, treino.getId());
            smt.execute();
            smt.close();
            conn.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("\n✅ Treino atualizado com sucesso!");
    }

    //TODO: Remove treino no banco
    public void removerTreino (Treino treino){
        Connection conn = ConexaoMySQL.getInstance();
        try {
            String sql = "DELETE FROM treino_bsf WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, treino.getId());
            stmt.execute();
            stmt.close();
            conn.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n✅ Treino removido com sucesso!");
    }
    //TODO: Busca e altera data do treino
    public void alterarDataTreino(Treino treino){
        Connection conn = ConexaoMySQL.getInstance();
        try {
            String sql = """
                        UPDATE treino_bsf
                        SET data_execucao = ?
                        WHERE id = ?
                        """;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(treino.getDataExecucao()));
            stmt.setInt(2, treino.getId());
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (DateTimeParseException e) {
            System.out.println("❌ Formato de data inválido! Use DD/MM/AAAA");
        } catch (Exception e) {
            System.out.println("❌ Erro ao adicionar treino: " + e.getMessage());
        }
        System.out.println("✅ Data do treino alterado com sucesso!");
    }
    //TODO: Lista todos os treinos ordenados por data
    public List<Treino> listarTodos(){
        Connection conn = ConexaoMySQL.getInstance();
        List<Treino> lista = new ArrayList<>();
        try {
            String sql = """
                        SELECT treino.*, usuarios.nome as usuario_nome
                        FROM treino_bsf treino
                        INNER JOIN usuarios_bsf usuarios ON treino.usuario_id = usuarios.id
                        ORDER BY treino.data_execucao DESC
                        """;
            Statement stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);
            while(resultados.next()){
                int id = resultados.getInt("id");
                String nome = resultados.getString("nome");
                LocalDate dataExecucao = resultados.getDate("data_execucao").toLocalDate();
                int usuarioId = resultados.getInt("usuario_id");
                String usuarioNome = resultados.getString("usuario_nome");
                lista.add(new Treino(id, nome, dataExecucao, usuarioId, usuarioNome));
            }
            stmt.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return lista;
    }
    //TODO: Busca treinos por usuário
    public List<Treino> listarPorUsuario(int usuarioId){
        Connection conn = ConexaoMySQL.getInstance();
        List<Treino> lista = new ArrayList<>();
        try {
            String sql = """
                    SELECT treino.*, usuario.nome as usuario_nome
                    FROM treino_bsf treino
                    INNER JOIN usuarios_bsf usuario ON treino.usuario_id = usuario.id
                    WHERE treino.usuario_id = ?
                    ORDER BY treino.data_execucao DESC
                    """;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usuarioId);
            ResultSet resultados = stmt.executeQuery();
            while(resultados.next()){
                int id = resultados.getInt("id");
                String nome = resultados.getString("nome");
                LocalDate dataExecucao = resultados.getDate("data_execucao").toLocalDate();
                String usuarioNome = resultados.getString("usuario_nome");
                lista.add(new Treino(id, nome, dataExecucao, usuarioId, usuarioNome));
            }
            stmt.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return lista;
    }

    //TODO: Busca treinos por parte do nome
    public List<Treino> listarPorParteDoNome(String pnome){
        Connection conn = ConexaoMySQL.getInstance();
        List<Treino> lista = new ArrayList<>();
        try{
            String sql = """
                    SELECT treino.*, usuario.nome as usuario_nome 
                    FROM treino_bsf treino
                    INNER JOIN usuarios_bsf usuario ON treino.usuario_id = usuario.id
                    WHERE treino.nome LIKE ?
                    ORDER BY treino.data_execucao DESC
                    """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + pnome + "%");
            ResultSet resultados = stmt.executeQuery();
            while(resultados.next()){
                int id = resultados.getInt("id");
                String nome = resultados.getString("nome");
                LocalDate dataExecucao = resultados.getDate("data_execucao").toLocalDate();
                int usuarioId = resultados.getInt("usuario_id");
                String usuarioNome = resultados.getString("usuario_nome");
                lista.add(new Treino(id, nome, dataExecucao, usuarioId, usuarioNome));
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    //TODO: Relatório geral de treino
    public List<Treino> relatorio(){
        Connection conn = ConexaoMySQL.getInstance();
        List<Treino> listaTreino =  new ArrayList<>();

        try{
            String sql = """
                    SELECT treino.*, usuario.nome as usuario_nome 
                    FROM treino_bsf treino
                    INNER JOIN usuarios_bsf usuario ON treino.usuario_id = usuario.id
                    """;
            Statement stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);
            while(resultados.next()){
                int id = resultados.getInt("id");
                String nome = resultados.getString("nome");
                LocalDate dataExecucao = resultados.getDate("data_execucao").toLocalDate();
                int usuarioId = resultados.getInt("usuario_id");
                String usuarioNome = resultados.getString("usuario_nome");
                listaTreino.add(new Treino(id, nome, dataExecucao, usuarioId, usuarioNome));
            }
            stmt.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaTreino;
    }

    //TODO: Busca de treino por data
    public static int buscaSequencial(List<Treino> listaTreino, int idBuscado, java.util.Scanner ler){
        int pos = -1;
        for (int i = 0; i < listaTreino.size(); i++) {
            if(idBuscado == listaTreino.get(i).getId()){
                try {
                    System.out.println("Entre com a nova data de execução (DD/MM/AAAA): ");
                    String dataExercicio = ler.nextLine();

                    listaTreino.get(i).setDataExecucao(LocalDate.parse(dataExercicio, formatter));
                    System.out.printf("%s", listaTreino.get(i).toString());
                    pos = i;
                    break;
                } catch (DateTimeParseException e){
                    System.out.println("❌ Erro: Formato de data inválido. Use DD/MM/AAAA");
                    return -1;
                }
            }
        }
        return pos;
    }
}
