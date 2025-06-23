package controller;

import connection.ConexaoMySQL;
import model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ControlaUsuario {
    //TODO: Adicionar usuario no banco
    public void adicionarUsuario(Usuario usuario) {
        Connection conn = ConexaoMySQL.getInstance();
        try {
            String sql = "INSERT INTO usuarios_bsf (nome, login, senha, email) VALUES (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getEmail());
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n✅ ✅ ✅ - USUARIO ADICIONADO COM SUCESSO! - ✅ ✅ ✅");
    }

    //TODO: Atualizar usuario no banco
    public void atualizarUsuario(Usuario usuario) {
        Connection conn = ConexaoMySQL.getInstance();
        try {
            String sql = "UPDATE usuarios_bsf SET nome = ?, login = ?, senha = ?, email = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getEmail());
            stmt.setInt(5, usuario.getId());
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n✅ ✅ ✅ - USUARIO ATUALIZADO COM SUCESSO! - ✅ ✅ ✅");
    }

    //TODO: Remover usuario do banco
    public void removerUsuario(Usuario usuario) {
        Connection conn = ConexaoMySQL.getInstance();
        try {
            String sql = "DELETE FROM usuarios_bsf WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usuario.getId());
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n✅ ✅ ✅ - REMOÇÃO DE USUARIO REALIZADA COM SUCESSO! - ✅ ✅ ✅");
    }

    //TODO: Buscar e alterar senha do usuario
    public void alterarSenha(Usuario usuario) {
        Connection conn = ConexaoMySQL.getInstance();
        try {
            String sql = "UPDATE usuarios_bsf SET senha = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getSenha());
            stmt.setInt(2, usuario.getId());
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n✅ ✅ ✅ - SENHA ALTERADA COM SUCESSO! - ✅ ✅ ✅");
    }

    //TODO: Listar usuarios ordenados por nome
    public List<Usuario> listarTodos() {

        Connection conn = ConexaoMySQL.getInstance();
        List<Usuario> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM usuarios_bsf ORDER BY nome ASC";
            Statement stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);
            while (resultados.next()) {
                int id = resultados.getInt("id");
                String nome = resultados.getString("nome");
                String login = resultados.getString("login");
                String senha = resultados.getString("senha");
                String email = resultados.getString("email");
                lista.add(new Usuario(id, nome, login, senha, email));
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    //TODO: Listar usuarios por parte do nome
    public List<Usuario> listarPorParteDoNome(String pnome) {
        Connection conn = ConexaoMySQL.getInstance();
        List<Usuario> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM usuarios_bsf WHERE nome LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + pnome + "%");
            ResultSet resultados = stmt.executeQuery();
            while(resultados.next()) {
                int id = resultados.getInt("id");
                String nome = resultados.getString("nome");
                String login = resultados.getString("login");
                String senha = resultados.getString("senha");
                String email = resultados.getString("email");
                lista.add(new Usuario(id, nome, login, senha, email));
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    //TODO: Relatório geral de usuarios
    public List<Usuario> relatorio() {
        Connection conn = ConexaoMySQL.getInstance();
        List<Usuario> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM usuarios_bsf";
            Statement stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);
            while(resultados.next()) {
                int id = resultados.getInt("id");
                String nome = resultados.getString("nome");
                String login = resultados.getString("login");
                String senha = resultados.getString("senha");
                String email = resultados.getString("email");
                lista.add(new Usuario(id, nome, login, senha, email));
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public static int buscaSequencial(List<Usuario> listaUsuario, int idBuscado, java.util.Scanner sc) {
        int pos = -1;
        for (int i = 0; i < listaUsuario.size(); i++) {
            if (idBuscado == listaUsuario.get(i).getId()) {
                System.out.printf("%n%s", listaUsuario.get(i).toString());
                System.out.println("\n*** Entre com a nova senha do usuário *** ");
                listaUsuario.get(i).setSenha(sc.nextLine());
                pos = i;
                break;
            }
        }
        return pos;
    }
}
