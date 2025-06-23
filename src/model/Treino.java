package model;

import java.time.LocalDate;

public class Treino {
    private int id;
    private String nome;
    private LocalDate dataExecucao;
    private int usuarioId;
    private String usuarioNome;


    public Treino(){

    }

    //Construtor para novos treinos (Sem ID)
    public Treino(String nome, LocalDate dataExecucao, int usuarioId){
        this.nome = nome;
        this.dataExecucao = dataExecucao;
        this.usuarioId = usuarioId;
    }

    //Construtor para treinos (com ID)
    public Treino(int id, String nome,LocalDate dataExecucao, int usuarioId){
        this.id = id;
        this.nome = nome;
        this.dataExecucao = dataExecucao;
        this.usuarioId = usuarioId;
    }

    //Construtor para relatorios
    public Treino(int id, String nome, LocalDate dataExecucao, int usuarioId, String usuarioNome){
        this.id = id;
        this.nome = nome;
        this.dataExecucao = dataExecucao;
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
    }

    //getters setters
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

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDate getDataExecucao() {
        return dataExecucao;
    }

    public void setDataExecucao(LocalDate dataExecucao) {
        this.dataExecucao = dataExecucao;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }
    @Override
    public String toString(){
        if (usuarioNome != null){
            return String.format("ID: %d | Treino: %s | Data: %s | Usuário: %s%n",
                    id, nome, dataExecucao, usuarioNome);
        } else {
            return String.format("ID: %d | Treino: %s | Data: %s | Usuário: %s%n",
                    id, nome, dataExecucao, usuarioNome);
        }
    }
}
