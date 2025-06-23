package view;

import controller.ControlaUsuario;
import controller.ControlaTreino;
import model.Usuario;
import model.Treino;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipalBSF_V1 {
    private static Scanner sc = new Scanner(System.in);
    private static ControlaUsuario controlUsuario = new ControlaUsuario();
    private static ControlaTreino controlTreino = new ControlaTreino();

    public static void main(String[] args) {
        exibirMenuPrincipal();
    }

    public static void exibirMenuPrincipal() {
        int opcao;

        do {
            System.out.println("\n=============== BEM VINDO AO SISTEMA BSF ===============");
            System.out.println("######## Menu Principal BSF ########");
            System.out.println("1. Gerenciar Usuários");
            System.out.println("2. Gerenciar Treinos");
            System.out.println("3. Relatórios");
            System.out.println("4. Sair do sistema");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    menuUsuarios();
                    break;
                case 2:
                    menuTreinos();
                    break;
                case 3:
                    menuRelatorios();
                    break;
                case 4:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 4);

        sc.close();
    }

    // MENU DE USUARIOS
    private static void menuUsuarios() {
        int opcao;

        do {
            System.out.println("\n=============== GERENCIADOR DE USUÁRIOS BSF===============");
            System.out.println("1. Adicionar usuário");
            System.out.println("2. Atualizar usuário");
            System.out.println("3. Remover usuário");
            System.out.println("4. Alterar senha de usuário");
            System.out.println("5. Listar todos os usuários");
            System.out.println("6. Buscar usuário por nome");
            System.out.println("7. Voltar ao menu principal");
            System.out.print("Escolha uma opção do menu: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    adicionarUsuario();
                    break;
                case 2:
                    atualizarUsuario();
                    break;
                case 3:
                    removerUsuario();
                    break;
                case 4:
                    alterarSenha();
                    break;
                case 5:
                    listarUsuarios();
                    break;
                case 6:
                    buscarUsuarioPorNome();
                    break;
                case 7:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 7);
    }

    private static void adicionarUsuario() {
        System.out.println("\n--- ADICIONAR USUÁRIO ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Login: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        Usuario novoUsuario = new Usuario(nome, login, senha, email);
        controlUsuario.adicionarUsuario(novoUsuario);
    }

    private static void atualizarUsuario() {
        System.out.println("\n--- ATUALIZAR USUÁRIO ---");
        listarUsuarios();
        System.out.print("Digite o ID do usuário a ser atualizado: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Novo Nome: ");
        String nome = sc.nextLine();
        System.out.print("Novo Login: ");
        String login = sc.nextLine();
        System.out.print("Nova Senha: ");
        String senha = sc.nextLine();
        System.out.print("Novo Email: ");
        String email = sc.nextLine();

        Usuario atualizaUsuario = new Usuario(id, nome, login, senha, email);
        controlUsuario.atualizarUsuario(atualizaUsuario);
    }

    private static void removerUsuario() {
        System.out.println("\n--- REMOVER USUÁRIO ---");
        listarUsuarios();
        System.out.print("Digite o ID do usuário a ser removido: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Tem certeza que deseja remover este usuário? (s/n): ");
        String confirmacao = sc.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            Usuario apagaUsuario = new Usuario();
            apagaUsuario.setId(id);
            controlUsuario.removerUsuario(apagaUsuario);
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    private static void alterarSenha() {
        System.out.println("\n--- ALTERAR SENHA ---");
        List<Usuario> listaUsuario = controlUsuario.listarTodos();

        for(Usuario usuario : listaUsuario) {
            System.out.printf("%s", usuario.toString());
        }

        System.out.print("Digite o ID do usuário correspondente para alterar a senha: ");
        int idBuscado = sc.nextInt();
        sc.nextLine();

        int pos = controlUsuario.buscaSequencial(listaUsuario, idBuscado, sc);
        if (pos != -1) {
            controlUsuario.alterarSenha(listaUsuario.get(pos));
        } else {
            System.out.println("ID não encontrado.");
        }
    }

    private static void listarUsuarios() {
        System.out.println("\n--- LISTA DE USUÁRIOS ---");
        List<Usuario> listaUsuario = controlUsuario.listarTodos();
        if (listaUsuario.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            for (Usuario usuario : listaUsuario) {
                System.out.printf("%s", listaUsuario.toString());
            }
        }
    }

    private static void buscarUsuarioPorNome() {
        System.out.println("\n--- BUSCAR USUÁRIO POR NOME ---");
        System.out.print("Digite uma parte do nome: ");
        String nome = sc.nextLine();

        List<Usuario> lista = controlUsuario.listarPorParteDoNome(nome);
        if (lista.isEmpty()) {
            System.out.println("Nenhum usuário encontrado com esse nome.");
        } else {
            for (Usuario listaUsuario : lista) {
                System.out.printf("%s", listaUsuario.toString());
            }
        }
    }

    // MENU DE TREINOS
    private static void menuTreinos() {
        int opcao;

        do {
            System.out.println("\n=============== GERENCIADOR DE TREINOS - SISTEMA BSF ===============");
            System.out.println("1. Adicionar treino");
            System.out.println("2. Atualizar treino");
            System.out.println("3. Remover treino");
            System.out.println("4. Alterar data do treino");
            System.out.println("5. Listar todos os treinos");
            System.out.println("6. Buscar treinos por usuário");
            System.out.println("7. Buscar treino por nome");
            System.out.println("8. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    adicionarTreino();
                    break;
                case 2:
                    atualizarTreino();
                    break;
                case 3:
                    removerTreino();
                    break;
                case 4:
                    alterarDataTreino();
                    break;
                case 5:
                    listarTreinos();
                    break;
                case 6:
                    buscarTreinosPorUsuario();
                    break;
                case 7:
                    buscarTreinoPorNome();
                    break;
                case 8:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 8);
    }

    private static void adicionarTreino() {
        System.out.println("\n--- ADICIONAR TREINO ---");

        // Usuarios Disponiveis
        System.out.println("Usuários disponíveis:");
        List<Usuario> listaUsuarios = controlUsuario.listarTodos();
        if (listaUsuarios.isEmpty()) {
            System.out.println("Não possui usuário cadastrado. Cadastre um usuário primeiro.");
            return;
        }

        for (Usuario usuario : listaUsuarios) {
            System.out.printf("%s", usuario.toString());
        }

        System.out.print("ID do usuário: ");
        int usuarioId = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome do treino: ");
        String nome = sc.nextLine();

        System.out.print("Data de execução (AAAA-MM-DD): ");
        String lerDataExecucao = sc.nextLine();

        try {
            LocalDate dataExecucao = LocalDate.parse(lerDataExecucao);
            Treino novoTreino = new Treino(nome, dataExecucao, usuarioId);
            controlTreino.adicionarTreino(novoTreino);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido! Use AAAA-MM-DD");
        }
    }

    private static void atualizarTreino() {
        System.out.println("\n--- ATUALIZAR TREINO ---");
        listarTreinos();
        System.out.print("Digite o ID do treino a ser atualizado: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Usuários disponíveis:");
        List<Usuario> listaUsuarios = controlUsuario.listarTodos();
        for (Usuario usuario : listaUsuarios) {
            System.out.printf("%s", usuario.toString());
        }

        System.out.print("ID do usuário: ");
        int usuarioId = sc.nextInt();
        sc.nextLine();

        System.out.print("Novo nome do treino: ");
        String nome = sc.nextLine();

        System.out.print("Nova data de execução (AAAA-MM-DD): ");
        String novaDataExecucao = sc.nextLine();

        try {
            LocalDate dataExecucao = LocalDate.parse(novaDataExecucao);
            Treino alteraTreino = new Treino(id, nome, dataExecucao, usuarioId);
            controlTreino.atualizarTreino(alteraTreino);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido! Use AAAA-MM-DD");
        }
    }

    private static void removerTreino() {
        System.out.println("\n--- REMOVER TREINO ---");
        listarTreinos();
        System.out.print("Digite o ID do treino a ser removido: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Tem certeza que deseja remover este treino? (s/n): ");
        String confirmacao = sc.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            Treino deletaTreino = new Treino();
            deletaTreino.setId(id);
            controlTreino.removerTreino(deletaTreino);
        } else {
            System.out.println("Remoção cancelada.");
        }
    }

    private static void alterarDataTreino() {
        System.out.println("\n--- ALTERAR DATA DO TREINO ---");
        List<Treino> listaTreino = controlTreino.listarTodos();

        for(Treino treino : listaTreino) {
            System.out.printf("%s", treino.toString());
        }

        System.out.print("Digite o ID do treino para alterar a data: ");
        int idBuscado = sc.nextInt();
        sc.nextLine();

        int pos = ControlaTreino.buscaSequencial(listaTreino, idBuscado, sc);
        if (pos != -1) {
            controlTreino.alterarDataTreino(listaTreino.get(pos));
        } else {
            System.out.println("ID não encontrado.");
        }
    }

    private static void listarTreinos() {
        System.out.println("\n--- LISTA DE TREINOS ---");
        List<Treino> listaTreino = controlTreino.listarTodos();
        if (listaTreino.isEmpty()) {
            System.out.println("Nenhum treino encontrado.");
        } else {
            for (Treino treino : listaTreino) {
                System.out.printf("%s", treino.toString());
            }
        }
    }

    private static void buscarTreinosPorUsuario() {
        System.out.println("\n--- BUSCAR TREINOS POR USUÁRIO ---");

        System.out.println("Usuários disponíveis:");
        List<Usuario> listaTreinoUsuario = controlUsuario.listarTodos();
        if (listaTreinoUsuario.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (Usuario usuario : listaTreinoUsuario) {
            System.out.printf("%s", usuario.toString());
        }

        System.out.print("Digite o ID do usuário: ");
        int usuarioId = sc.nextInt();
        sc.nextLine();

        List<Treino> listaUsuarioId = controlTreino.listarPorUsuario(usuarioId);
        if (listaUsuarioId.isEmpty()) {
            System.out.println("Nenhum treino encontrado para este usuário.");
        } else {
            System.out.println("Treinos do usuário:");
            for (Treino treino : listaUsuarioId) {
                System.out.printf("%s", treino.toString());
            }
        }
    }

    private static void buscarTreinoPorNome() {
        System.out.println("\n--- BUSCAR TREINO POR NOME ---");
        System.out.print("Digite parte do nome do treino: ");
        String nome = sc.nextLine();

        List<Treino> listaTreinoNome = controlTreino.listarPorParteDoNome(nome);
        if (listaTreinoNome.isEmpty()) {
            System.out.println("Nenhum treino encontrado com esse nome.");
        } else {
            for (Treino treino : listaTreinoNome) {
                System.out.printf("%s", treino.toString());
            }
        }
    }

    // MENU DE RELATORIOS
    private static void menuRelatorios() {
        int opcao;

        do {
            System.out.println("\n=============== MENU DE RELATÓRIOS BSF ===============");
            System.out.println("1. Relatório de Usuários");
            System.out.println("2. Relatório de Treinos");
            System.out.println("3. Relatório Completo (Usuários + Treinos)");
            System.out.println("4. Estatísticas do Sistema");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    relatorioUsuarios();
                    break;
                case 2:
                    relatorioTreinos();
                    break;
                case 3:
                    relatorioCompleto();
                    break;
                case 4:
                    estatisticasSistema();
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 5);
    }

    private static void relatorioUsuarios() {
        System.out.println("\n=============== RELATÓRIO DE USUÁRIOS ===============");
        List<Usuario> lista = controlUsuario.relatorio();
        if (lista.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            System.out.println("Total de usuários: " + lista.size());
            System.out.println("----------------------------------------");
            for (Usuario usuario : lista) {
                System.out.printf("%s", usuario.toString());
            }
        }
    }

    private static void relatorioTreinos() {
        System.out.println("\n=============== RELATÓRIO DE TREINOS ===============");
        List<Treino> lista = controlTreino.relatorio();
        if (lista.isEmpty()) {
            System.out.println("Nenhum treino cadastrado.");
        } else {
            System.out.println("Total de treinos: " + lista.size());
            System.out.println("----------------------------------------");
            for (Treino treino : lista) {
                System.out.printf("%s", treino.toString());
            }
        }
    }

    private static void relatorioCompleto() {
        System.out.println("\n=============== RELATÓRIO COMPLETO ===============");

        // Relatório de Usuários
        List<Usuario> usuarios = controlUsuario.relatorio();
        System.out.println("USUÁRIOS CADASTRADOS: " + usuarios.size());
        System.out.println("----------------------------------------");
        for (Usuario usuario : usuarios) {
            System.out.printf("%s", usuario.toString());

            // Buscar por treinos do usuário
            List<Treino> treinosUsuario = controlTreino.listarPorUsuario(usuario.getId());
            if (!treinosUsuario.isEmpty()) {
                System.out.println("  Treinos deste usuário:");
                for (Treino t : treinosUsuario) {
                    System.out.printf("    - %s (Data: %s)%n",
                            t.getNome(), t.getDataExecucao());
                }
            } else {
                System.out.println("  Nenhum treino cadastrado para este usuário.");
            }
            System.out.println();
        }

        // Resumo geral
        List<Treino> treinos = controlTreino.relatorio();
        System.out.println("========================================");
        System.out.println("RESUMO GERAL:");
        System.out.println("Total de usuários: " + usuarios.size());
        System.out.println("Total de treinos: " + treinos.size());
        if (!usuarios.isEmpty()) {
            System.out.printf("Média de treinos por usuário: %.2f%n",
                    (double)treinos.size() / usuarios.size());
        }
    }

    private static void estatisticasSistema() {
        System.out.println("\n=============== ESTATÍSTICAS DO SISTEMA ===============");

        List<Usuario> usuarios = controlUsuario.relatorio();
        List<Treino> treinos = controlTreino.relatorio();

        System.out.println("📊 DADOS GERAIS:");
        System.out.println("Total de usuários cadastrados: " + usuarios.size());
        System.out.println("Total de treinos cadastrados: " + treinos.size());

        if (!usuarios.isEmpty()) {
            System.out.printf("Média de treinos por usuário: %.2f%n",
                    (double)treinos.size() / usuarios.size());
        }

        // Estatísticas por usuário
        if (!usuarios.isEmpty()) {
            System.out.println("\n👥 TREINOS POR USUÁRIO:");
            for (Usuario usuario : usuarios) {
                List<Treino> treinosUsuario = controlTreino.listarPorUsuario(usuario.getId());
                System.out.printf("- %s: %d treino(s)%n", usuario.getNome(), treinosUsuario.size());
            }
        }

        // Usuário mais ativo
        if (!usuarios.isEmpty() && !treinos.isEmpty()) {
            String usuarioMaisAtivo = "";
            int maxTreinos = 0;

            for (Usuario usuario : usuarios) {
                List<Treino> treinosUsuario = controlTreino.listarPorUsuario(usuario.getId());
                if (treinosUsuario.size() > maxTreinos) {
                    maxTreinos = treinosUsuario.size();
                    usuarioMaisAtivo = usuario.getNome();
                }
            }

            if (maxTreinos > 0) {
                System.out.println("\n🏆 USUÁRIO MAIS ATIVO:");
                System.out.printf("- %s com %d treino(s)%n", usuarioMaisAtivo, maxTreinos);
            }
        }
    }
}


