package model;

public class Usuario {
    private int id;
    private String nome;
    private String login;
    private String senha;
    private String email;


    public Usuario(){
    }
    //Construtor com parâmetros para usuários novos
    public Usuario(int id, String nome, String login, String senha, String email){
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
    }
    //Construtor com parâmetros para usuários já cadastrados
    public Usuario( String nome, String login, String senha,  String email){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
    }

    //GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s  | Login: %s | Email: %s%n",
                id, nome, login, email);
    }
}
