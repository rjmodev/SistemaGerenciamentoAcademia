
package view;

import controller.ControlaUsuario;
import controller.ControlaTreino;
import model.Usuario;
import model.Treino;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuCompletoPrincipal {
    private static Scanner sc = new Scanner(System.in);
    private static ControlaUsuario controlUsuario = new ControlaUsuario();
    private static ControlaTreino controlTreino = new ControlaTreino();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        System.out.println("\n"+"#".repeat(80));
        System.out.println("-".repeat(25)+" SISTEMA DE GERENCIAMENTO BSF "+"-".repeat(25));
        System.out.println("#".repeat(80));

        // Metodo para iniciar o sistema
        iniciarSistema();
    }

    private static void iniciarSistema() {
        int opcao;

        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();

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
                    menuTestes();
                    break;
                case 5:
                    menuEstatisticas();
                    break;
                case 6:
                    System.out.println("\n🏁 Encerrando o sistema...");
                    System.out.println("Obrigado por usar o Sistema BSF !");
                    break;
                default:
                    System.out.println("❌ Opção inválida! Digite um número de 1 a 6.");
            }
        } while (opcao != 6);

        sc.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("""
                            \n══════════════════════════════════════════════════
                                  🏋️  MENU PRINCIPAL DO SISTEMA BSF  🏋️               
                            ══════════════════════════════════════════════════
                              1. 👥 Gerenciador de Usuários                   
                              2. 💪 Gerenciador de Treinos                    
                              3. 📊 Relatórios Gerais                          
                              4. 🧪 Executar Testes                           
                              5. 📈 Estatísticas Avançadas                     
                              6. 🚪 Sair do Sistema                           
                            ══════════════════════════════════════════════════
                            ➤ Digite uma opção do menu: 
                            """);
    }

    private static int lerOpcao() {
        try {
            int opcao = sc.nextInt();
            sc.nextLine();
            return opcao;
        } catch (Exception e) {
            sc.nextLine();
            return -1;
        }
    }

    // MENU DE USUARIOS
    private static void menuUsuarios() {
        int opcao;

        do {
            System.out.println("""
                                \n══════════════════════════════════════════════════
                                            👥 GERENCIADOR DE USUÁRIOS            
                                ══════════════════════════════════════════════════
                                  1. ➕ Adicionar novo usuário                    
                                  2. ✏️  Atualizar usuário existente              
                                  3. 🗑️  Remover usuário                          
                                  4. 🔐 Alterar senha                             
                                  5. 📋 Listar todos os usuários                  
                                  6. 🔍 Buscar usuário por nome                   
                                  7. 📊 Relatório detalhado de usuários           
                                  8 . ⬅️  Voltar ao menu principal                  
                               ══════════════════════════════════════════════════
                               ➤ Digite uma opção do menu: 
                                """);

            opcao = lerOpcao();

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
                    relatorioDetalhadoUsuarios();
                    break;
                case 8:
                    System.out.println("⬅️ Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("❌ Opção inválida! Digite um número de 1 a 8.");
            }
        } while (opcao != 8);
    }

    private static void adicionarUsuario() {
        System.out.println("\n→ 1. ➕ ADICIONAR NOVO USUÁRIO");

        try {
            System.out.print("\n📝 Nome completo: ");
            String nome = sc.nextLine().trim();

            System.out.print("\n👤 Login de usuário: ");
            String login = sc.nextLine().trim();

            System.out.print("\n🔒 Senha: ");
            String senha = sc.nextLine().trim();

            System.out.print("\n📧 Email: ");
            String email = sc.nextLine().trim();

            // Validações para preenchimento obrigatório dos campos
            if (nome.isEmpty() || login.isEmpty() || senha.isEmpty() || email.isEmpty()) {
                System.out.println("❌ O preenchimento de todos os campos são obrigatórios!");
                return;
            }

            if (!email.contains("@")) {
                System.out.println("❌ Email inválido!");
                return;
            }

            Usuario addUsuario = new Usuario(nome, login, senha, email);
            controlUsuario.adicionarUsuario(addUsuario);

        } catch (Exception e) {
            System.out.println("❌ Erro ao adicionar usuário: " + e.getMessage());
        }
    }

    private static void atualizarUsuario() {
        System.out.println("\n→ 2. ✏️ ATUALIZAR USUÁRIO");

        listarUsuarios();

        try {
            System.out.print("\n🆔 Digite o ID do usuário a ser atualizado: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("\n📝 Novo nome: ");
            String nome = sc.nextLine().trim();

            System.out.print("\n👤 Novo login: ");
            String login = sc.nextLine().trim();

            System.out.print("\n🔒 Nova senha: ");
            String senha = sc.nextLine().trim();

            System.out.print("\n📧 Novo email: ");
            String email = sc.nextLine().trim();

            System.out.println("_".repeat(80));
            Usuario atualizaUsuario = new Usuario(id, nome, login, senha, email);
            controlUsuario.atualizarUsuario(atualizaUsuario);

        } catch (Exception e) {
            System.out.println("❌ Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    private static void removerUsuario() {
        System.out.println("\n→ 3. 🗑️ REMOVER USUÁRIO");

        listarUsuarios();

        try {
            System.out.print("\n🆔 Digite o ID do usuário a ser removido: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("\n⚠️ Tem certeza que deseja remover este usuário? (s/n): ");
            String confirmacao = sc.nextLine().trim();

            if (confirmacao.equalsIgnoreCase("s") || confirmacao.equalsIgnoreCase("sim")) {
                Usuario deletaUsuario = new Usuario();
                deletaUsuario.setId(id);
                controlUsuario.removerUsuario(deletaUsuario);
            } else {
                System.out.println("❌ Remoção cancelada.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao remover usuário: " + e.getMessage());
        }
    }

    private static void alterarSenha() {
        System.out.println("\n→ 4. 🔐 ALTERAR SENHA");

        try {
            List<Usuario> listaUsuario = controlUsuario.listarTodos();

            if (listaUsuario.isEmpty()) {
                System.out.println("❌ Nenhum usuário cadastrado.");
                return;
            }

            // Ordena a lista de usuários por ID em ordem crescente
            listaUsuario.sort(Comparator.comparing(Usuario::getId));

            System.out.println("\n👥 Usuários em sistema:");
            System.out.println("_".repeat(70));

            for (Usuario usuario : listaUsuario) {
                System.out.printf("🆔 %d - %s (%s)%n", usuario.getId(), usuario.getNome(), usuario.getLogin());
            }
            System.out.println("_".repeat(70));
            System.out.print("🆔 Digite o ID do usuário para alterar a senha: ");
            int idBuscado = sc.nextInt();
            sc.nextLine();

            int pos = ControlaUsuario.buscaSequencial(listaUsuario, idBuscado, sc);
            if (pos != -1) {
                controlUsuario.alterarSenha(listaUsuario.get(pos));
            } else {
                System.out.println("❌ ID não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao alterar senha: " + e.getMessage());
        }
    }

    private static void listarUsuarios() {
        System.out.println("\n→ 📋 LISTA DE USUÁRIOS ←");

        try {
            List<Usuario> listaUsuario = controlUsuario.listarTodos();

            // Ordena a lista de usuários por ID em ordem crescente
            listaUsuario.sort(Comparator.comparing(Usuario::getId));

            if (listaUsuario.isEmpty()) {
                System.out.println("❌ Nenhum usuário encontrado.");
            } else {
                System.out.println("_".repeat(80));
                System.out.printf("%-5s %-20s %-15s %-25s%n", "ID", "Nome", "Login", "Email");
                System.out.println("_".repeat(80));

                for (Usuario usuario : listaUsuario) {
                    System.out.printf("%-5d %-20s %-15s %-25s%n",
                            usuario.getId(),
                            usuario.getNome().length() > 18 ? usuario.getNome().substring(0, 15) + "..." : usuario.getNome(),
                            usuario.getLogin().length() > 13 ? usuario.getLogin().substring(0, 10) + "..." : usuario.getLogin(),
                            usuario.getEmail().length() > 23 ? usuario.getEmail().substring(0, 20) + "..." : usuario.getEmail()
                    );
                }
                System.out.println("_".repeat(80));
                System.out.println("📊 Total: " + listaUsuario.size() + " usuário(s)");
                System.out.println("_".repeat(80));
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao listar usuários: " + e.getMessage());
        }
    }

    private static void buscarUsuarioPorNome() {
        System.out.println("\n→ 6. 🔍 BUSCAR USUÁRIO POR NOME");

        try {
            System.out.print("\n📝 Digite qualquer parte do nome: ");
            String nome = sc.nextLine().trim();

            if (nome.isEmpty()) {
                System.out.println("\n❌ Nome não pode estar vazio!");
                return;
            }

            List<Usuario> listaUsuario = controlUsuario.listarPorParteDoNome(nome);
            if (listaUsuario.isEmpty()) {
                System.out.println("\n❌ Nenhum usuário cadastrado com esse nome.");
            } else {
                System.out.println("\n✅ Usuários encontrados:");
                System.out.println("_".repeat(70));
                System.out.printf("%-5s %-20s %-15s %-25s%n", "ID", "Nome", "Login", "Email");
                System.out.println("_".repeat(70));
                for (Usuario usuario : listaUsuario) {
                    System.out.printf("%-5d %-20s %-15s %-25s%n",
                            usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getEmail());
                }
                System.out.println("_".repeat(70));
                System.out.println("📊 Total encontrado: " + listaUsuario.size() + " usuário(s)");
                System.out.println("_".repeat(70));
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar usuário: " + e.getMessage());
        }
    }

    private static void relatorioDetalhadoUsuarios() {
        System.out.println("\n→ 7. 📊 RELATÓRIO DETALHADO DE USUÁRIOS");

        try {
            List<Usuario> listaUsuarios = controlUsuario.relatorio();
            if (listaUsuarios.isEmpty()) {
                System.out.println("\n❌ Nenhum usuário cadastrado.");
                return;
            }

            for (Usuario usuario : listaUsuarios) {
                System.out.println("┌" + "─".repeat(50) + "┐");
                System.out.printf("│ 🆔 ID: %-42d │%n", usuario.getId());
                System.out.printf("│ 👤 Nome: %-39s │%n", usuario.getNome());
                System.out.printf("│ 🔑 Login: %-38s │%n", usuario.getLogin());
                System.out.printf("│ 📧 Email: %-38s │%n", usuario.getEmail());

                // Buscar treinos do usuário
                List<Treino> treinosUsuario = controlTreino.listarPorUsuario(usuario.getId());
                System.out.printf("│ 💪 Treinos: %-36d │%n", treinosUsuario.size());

                if (!treinosUsuario.isEmpty()) {
                    System.out.println("│ 📋 Últimos treinos:                              │");
                    int count = 0;
                    for (Treino treino : treinosUsuario) {
                        // Mostrar apenas os 3 últimos treinos
                        if (count >= 3) break;
                        String treinoInfo = String.format("   • %s (%s)",
                                treino.getNome().length() > 20 ? treino.getNome().substring(0, 17) + "..." : treino.getNome(),
                                treino.getDataExecucao().format(formatter));
                        System.out.printf("│ %-48s │%n", treinoInfo);
                        count++;
                    }
                }
                System.out.println("└" + "─".repeat(50) + "┘");
                System.out.println();
            }

            System.out.println("📊 RESUMO: " + listaUsuarios.size() + " usuário(s) cadastrado(s)");

        } catch (Exception e) {
            System.out.println("❌ Erro ao gerar relatório: " + e.getMessage());
        }
    }

    //MENU DE TREINOS
    private static void menuTreinos() {
        int opcao;

        do {
            System.out.println("\n══════════════════════════════════════════════════");
            System.out.println("          💪 GERENCIADOR DE TREINOS               ");
            System.out.println("══════════════════════════════════════════════════");
            System.out.println("  1. ➕ Adicionar novo treino                     ");
            System.out.println("  2. ✏️  Atualizar treino existente                ");
            System.out.println("  3. 🗑️  Remover treino                            ");
            System.out.println("  4. 📅 Alterar data do treino                    ");
            System.out.println("  5. 📋 Listar todos os treinos realizados                  ");
            System.out.println("  6. 👤 Buscar treinos por usuário                ");
            System.out.println("  7. 🔍 Buscar treino por nome                    ");
            System.out.println("  8. 📊 Relatório detalhado de treinos            ");
            System.out.println("  9. ⬅️  Voltar ao menu principal                  ");
            System.out.println("══════════════════════════════════════════════════");
            System.out.print("➤ Escolha uma opção: ");

            opcao = lerOpcao();

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
                    relatorioDetalhadoTreinos();
                    break;
                case 9:
                    System.out.println("⬅️ Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("❌ Opção inválida! Digite um número de 1 a 9.");
            }
        } while (opcao != 9);
    }

    private static void adicionarTreino() {
        System.out.println("\n-> 1. ➕ ADICIONAR NOVO TREINO");

        try {

            List<Usuario> listaUsuarios = controlUsuario.listarTodos();
            if (listaUsuarios.isEmpty()) {
                System.out.println("\n❌ Nenhum usuário cadastrado. Cadastre um usuário primeiro.");
                return;
            }
            // Ordena a lista de usuários por ID em ordem crescente
            listaUsuarios.sort(Comparator.comparing(Usuario::getId));

            System.out.println("\n👥 Usuários disponíveis:");

            for (Usuario usuario : listaUsuarios) {
                System.out.printf("🆔 %d - %s%n", usuario.getId(), usuario.getNome());
            }

            System.out.print("\n🆔 Digite o ID do usuário: ");
            int usuarioId = sc.nextInt();
            sc.nextLine();

            System.out.print("\n💪 Digite o nome do treino: ");
            String nomeTreino = sc.nextLine().trim();

            System.out.print("📅 Data de execução (DD/MM/AAAA): ");
            String dataExercicio = sc.nextLine().trim();

            if (nomeTreino.isEmpty() || dataExercicio.isEmpty() || dataExercicio.isEmpty()) {
                System.out.println("❌ Todos os campos devem ser preenchidos obrigatoriamente!");
                return;
            }

            LocalDate dataExecucao = LocalDate.parse(dataExercicio, formatter);
            Treino addTreino = new Treino(nomeTreino, dataExecucao, usuarioId);
            controlTreino.adicionarTreino(addTreino);

        } catch (DateTimeParseException e) {
            System.out.println("❌ Formato de data inválido! Use DD/MM/AAAA");
        } catch (Exception e) {
            System.out.println("❌ Erro ao adicionar treino: " + e.getMessage());
        }
    }

    private static void atualizarTreino() {

        System.out.println("\n→ 2. ✏️ ATUALIZAR TREINO");

        try {
            listarTreinos();

            System.out.print("\n🆔 Digite o ID do treino a ser atualizado: ");
            int id = sc.nextInt();
            sc.nextLine();

            List<Usuario> listaUsuarios = controlUsuario.listarTodos();

            // Ordena a lista de usuários por ID em ordem crescente
            listaUsuarios.sort(Comparator.comparing(Usuario::getId));

            System.out.println("\n👥 Usuários disponíveis:");
            for (Usuario usuario : listaUsuarios) {
                System.out.printf("🆔 %d - %s%n%n", usuario.getId(), usuario.getNome());
            }

            System.out.print("\n🆔 Digite o ID do usuário: ");
            int usuarioId = sc.nextInt();
            sc.nextLine();

            System.out.print("\n Digite o novo nome do treino: ");
            String nome = sc.nextLine().trim();

            System.out.print("\n📅 Digite a nova data de execução (DD/MM/AAAA): ");
            String novaData = sc.nextLine().trim();

            LocalDate dataExecucao = LocalDate.parse(novaData, formatter);
            Treino attTreino = new Treino(id, nome, dataExecucao, usuarioId);
            controlTreino.atualizarTreino(attTreino);

        } catch (DateTimeParseException e) {
            System.out.println("❌ Formato de data inválido! Use dd/MM/yyyy");
        } catch (Exception e) {
            System.out.println("❌ Erro ao atualizar treino: " + e.getMessage());
        }
    }

    private static void removerTreino() {
        System.out.println("\n→ 3. 🗑️ REMOVER TREINO");

        try {
            listarTreinos();

            System.out.print("\n🆔 Digite o ID do treino a ser removido: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("\n⚠️ Tem certeza que deseja remover este treino? (s/n): ");
            String confirmacao = sc.nextLine().trim();
            sc.nextLine();

            if (confirmacao.equalsIgnoreCase("s") || confirmacao.equalsIgnoreCase("sim")) {
                Treino deletaTreino = new Treino();
                deletaTreino.setId(id);
                controlTreino.removerTreino(deletaTreino);
            } else {
                System.out.println("❌ Remoção cancelada.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao remover treino: " + e.getMessage());
        }
    }

    private static void alterarDataTreino() {
        System.out.println("\n→ 4. 📅 ALTERAR DATA DO TREINO");

        try {
            List<Treino> listaTreino = controlTreino.listarTodos();

            if (listaTreino.isEmpty()) {
                System.out.println("❌ Nenhum treino cadastrado.");
                return;
            }

            System.out.println("💪 Treinos disponíveis:");
            for (Treino treino : listaTreino) {
                System.out.printf("🆔 %d - %s (%s) - %s%n",
                        treino.getId(),
                        treino.getNome(),
                        treino.getDataExecucao().format(formatter),
                        treino.getUsuarioNome());
            }

            System.out.print("🆔 Digite o ID do treino para alterar a data: ");
            int idBuscado = sc.nextInt();
            sc.nextLine();

            int pos = ControlaTreino.buscaSequencial(listaTreino, idBuscado, sc);
            if (pos != -1) {
                controlTreino.alterarDataTreino(listaTreino.get(pos));
            } else {
                System.out.println("❌ ID não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao alterar data: " + e.getMessage());
        }
    }

    private static void listarTreinos() {
        System.out.println("\n→ 💪 LISTA DE TREINOS REALIZADOS ←");

        try {
            List<Treino> listaTreino = controlTreino.listarTodos();
            if (listaTreino.isEmpty()) {
                System.out.println("❌ Nenhum treino encontrado.");
            } else {
                // Ordena a lista por ID em ordem crescente
                listaTreino.sort(Comparator.comparing(Treino::getId));

                System.out.println("_".repeat(70));
                System.out.printf("%-5s %-25s %-12s %-20s%n", "ID", "Nome do Treino", "Data", "Usuário");
                System.out.println("_".repeat(70));
                for (Treino treino : listaTreino) {
                    System.out.printf("%-5d %-25s %-12s %-20s%n",
                            treino.getId(),
                            treino.getNome().length() > 23 ? treino.getNome().substring(0, 20) + "..." : treino.getNome(),
                            treino.getDataExecucao().format(formatter),
                            treino.getUsuarioNome() != null ?
                                    (treino.getUsuarioNome().length() > 18 ? treino.getUsuarioNome().substring(0, 15) + "..." : treino.getUsuarioNome()) :
                                    "Usuário " + treino.getUsuarioId()
                    );
                }
                System.out.println("_".repeat(70));
                System.out.println("📊 Total: " + listaTreino.size() + " treino(s)\n");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao listar treinos: " + e.getMessage());
        }
    }

    private static void buscarTreinosPorUsuario() {
        System.out.println("\n→ 6. 👤 BUSCAR TREINOS POR USUÁRIO");

        try {
            List<Usuario> listaUsuarios = controlUsuario.listarTodos();
            if (listaUsuarios.isEmpty()) {
                System.out.println("❌ Nenhum usuário cadastrado.");
                return;
            }

            // Ordena a lista de usuários por ID em ordem crescente
            listaUsuarios.sort(Comparator.comparing(Usuario::getId));

            System.out.println("\n👥 Usuários disponíveis:");
            for (Usuario usuario : listaUsuarios) {
                List<Treino> treinosDoUsuario = controlTreino.listarPorUsuario(usuario.getId());
                System.out.printf("🆔 %d - %s (%d treinos)%n",
                        usuario.getId(), usuario.getNome(), treinosDoUsuario.size());
            }

            System.out.println("_".repeat(60));
            System.out.print("🆔 Digite o ID do usuário: ");
            int usuarioId = sc.nextInt();
            sc.nextLine();

            List<Treino> listaTreino = controlTreino.listarPorUsuario(usuarioId);
            if (listaTreino.isEmpty()) {
                System.out.println("❌ Nenhum treino encontrado para este usuário.");
            } else {
                // Ordena a lista de treinos por ID em ordem crescente
                listaTreino.sort(Comparator.comparing(Treino::getId));

                System.out.println("\n✅ Treinos do usuário:");
                System.out.printf("%-5s %-25s %-12s%n", "ID", "Nome do Treino", "Data");
                System.out.println("-".repeat(45));
                for (Treino treino : listaTreino) {
                    System.out.printf("%-5d %-25s %-12s%n",
                            treino.getId(),
                            treino.getNome().length() > 23 ? treino.getNome().substring(0, 20) + "..." : treino.getNome(),
                            treino.getDataExecucao().format(formatter)
                    );
                }
                System.out.println("-".repeat(80));
                System.out.println("📊 Total encontrado: " + listaTreino.size() + " treino(s)");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar treinos: " + e.getMessage());
        }
    }

    private static void buscarTreinoPorNome() {
        System.out.println("\n→ 7. 🔍 BUSCAR TREINO POR NOME");

        try {
            System.out.print("\n💪 Digite parte do nome do treino: ");
            String parteNome = sc.nextLine().trim();

            if (parteNome.isEmpty()) {
                System.out.println("❌ Nome não pode estar vazio!");
                return;
            }

            List<Treino> listaTreino = controlTreino.listarPorParteDoNome(parteNome);
            if (listaTreino.isEmpty()) {
                System.out.println("❌ Nenhum treino encontrado com esse nome.");
            } else {
                // Ordena a lista de treinos por ID em ordem crescente
                listaTreino.sort(Comparator.comparing(Treino::getId));

                System.out.println("✅ Treinos encontrados:");
                System.out.printf("%-5s %-25s %-12s %-20s%n", "ID", "Nome do Treino", "Data", "Usuário");
                System.out.println("-".repeat(80));
                for (Treino treino : listaTreino) {
                    System.out.printf("%-5d %-25s %-12s %-20s%n",
                            treino.getId(),
                            treino.getNome().length() > 23 ? treino.getNome().substring(0, 20) + "..." : treino.getNome(),
                            treino.getDataExecucao().format(formatter),
                            treino.getUsuarioNome() != null ?
                                    (treino.getUsuarioNome().length() > 18 ? treino.getUsuarioNome().substring(0, 15) + "..." : treino.getUsuarioNome()) :
                                    "Usuário " + treino.getUsuarioId()
                    );
                }
                System.out.println("📊 Total encontrado: " + listaTreino.size() + " treino(s)");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar treino: " + e.getMessage());
        }
    }

    private static void relatorioDetalhadoTreinos() {
        System.out.println("\n→ 8. 💪 RELATÓRIO DETALHADO DE TREINOS");

        try {
            List<Treino> listaTreino = controlTreino.relatorio();
            if (listaTreino.isEmpty()) {
                System.out.println("❌ Nenhum treino cadastrado.");
                return;
            }
            // Ordena a lista de treinos por ID em ordem crescente
            listaTreino.sort(Comparator.comparing(Treino::getId));

            for (Treino treino : listaTreino) {
                System.out.println("┌" + "─".repeat(50) + "┐");
                System.out.printf("│ 🆔 ID: %-42d │%n", treino.getId());
                System.out.printf("│ 💪 Nome: %-39s │%n", treino.getNome());
                System.out.printf("│ 📅 Data: %-39s │%n", treino.getDataExecucao().format(formatter));
                System.out.printf("│ 👤 Usuário: %-36s │%n", treino.getUsuarioNome() != null ? treino.getUsuarioNome() : "Usuário " + treino.getUsuarioId());
                System.out.println("└" + "─".repeat(50) + "┘");
                System.out.println();
            }

            System.out.println("📊 RESUMO: " + listaTreino.size() + " treino(s) cadastrado(s)");

        } catch (Exception e) {
            System.out.println("❌ Erro ao gerar relatório: " + e.getMessage());
        }
    }

    // MENU DE RELATORIOS
    private static void menuRelatorios() {
        int opcao;

        do {
            System.out.println("\n\n══════════════════════════════════════════════════");
            System.out.println("              📊 RELATÓRIOS GERAIS BSF            ");
            System.out.println("══════════════════════════════════════════════════");
            System.out.println("  1. 👥 Relatório de Usuários                    ");
            System.out.println("  2. 💪 Relatório de Treinos                     ");
            System.out.println("  3. 📋 Relatório Completo (Usuários + Treinos)  ");
            System.out.println("  4. 📈 Estatísticas do Sistema                  ");
            System.out.println("  5. ⬅️  Voltar ao menu principal                ");
            System.out.println("══════════════════════════════════════════════════");
            System.out.print("➤ Escolha uma opção: ");

            opcao = lerOpcao();

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
                    System.out.println("⬅️ Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("❌ Opção inválida! Digite um número de 1 a 5.");
            }
        } while (opcao != 5);
    }

    private static void relatorioUsuarios() {
        System.out.println("\n→ 1. 👥 RELATÓRIO DE USUÁRIOS");

        try {
            List<Usuario> listaUsuarios = controlUsuario.relatorio();
            if (listaUsuarios.isEmpty()) {
                System.out.println("❌ Nenhum usuário cadastrado.");
            } else {
                System.out.printf("%-5s %-20s %-15s %-25s%n", "ID", "Nome", "Login", "Email");
                System.out.println("-".repeat(60));
                for (Usuario usuario : listaUsuarios) {
                    System.out.printf("%-5d %-20s %-15s %-25s%n",
                            usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getEmail());
                }
                System.out.println("-".repeat(60));
                System.out.println("📊 Total: " + listaUsuarios.size() + " usuário(s)");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao gerar relatório de usuários: " + e.getMessage());
        }
    }

    private static void relatorioTreinos() {
        System.out.println("\n→ 2. 💪 RELATÓRIO DE TREINOS");

        try {
            List<Treino> listaTreino = controlTreino.relatorio();
            if (listaTreino.isEmpty()) {
                System.out.println("❌ Nenhum treino cadastrado.");
            } else {
                System.out.printf("%-5s %-25s %-12s %-20s%n", "ID", "Nome do Treino", "Data", "Usuário");
                System.out.println("-".repeat(80));
                for (Treino treino : listaTreino) {
                    System.out.printf("%-5d %-25s %-12s %-20s%n",
                            treino.getId(),
                            treino.getNome(),
                            treino.getDataExecucao().format(formatter),
                            treino.getUsuarioNome() != null ? treino.getUsuarioNome() : "Usuário " + treino.getUsuarioId()
                    );
                }
                System.out.println("-".repeat(80));
                System.out.println("📊 Total: " + listaTreino.size() + " treino(s)");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao gerar relatório de treinos: " + e.getMessage());
        }
    }

    private static void relatorioCompleto() {
        System.out.println("\n→ 3. 📋 RELATÓRIO COMPLETO (USUÁRIOS + TREINOS)");

        try {
            List<Usuario> listaUsuarios = controlUsuario.relatorio();
            if (listaUsuarios.isEmpty()) {
                System.out.println("❌ Nenhum usuário cadastrado.");
                return;
            }

            System.out.println("👥 USUÁRIOS:");
            System.out.printf("%-5s %-20s %-15s %-25s%n", "ID", "Nome", "Login", "Email");
            System.out.println("-".repeat(80));
            for (Usuario usuario : listaUsuarios) {
                System.out.printf("%-5d %-20s %-15s %-25s%n",
                        usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getEmail());
            }

            List<Treino> listaTreino = controlTreino.relatorio();
            System.out.println("\n💪 TREINOS:");
            if (listaTreino.isEmpty()) {
                System.out.println("❌ Nenhum treino cadastrado.");
            } else {
                System.out.printf("%-5s %-25s %-12s %-20s%n", "ID", "Nome do Treino", "Data", "Usuário");
                System.out.println("-".repeat(80));
                for (Treino treino : listaTreino) {
                    System.out.printf("%-5d %-25s %-12s %-20s%n",
                            treino.getId(),
                            treino.getNome(),
                            treino.getDataExecucao().format(formatter),
                            treino.getUsuarioNome() != null ? treino.getUsuarioNome() : "Usuário " + treino.getUsuarioId()
                    );
                }
            }

            System.out.println("\n📊 RESUMO GERAL:");
            System.out.println("Total de usuários: " + listaUsuarios.size());
            System.out.println("Total de treinos: " + listaTreino.size());
            if (!listaUsuarios.isEmpty()) {
                System.out.printf("Média de treinos por usuário: %.2f%n",
                        (double)listaTreino.size() / listaUsuarios.size());
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao gerar relatório completo: " + e.getMessage());
        }
    }

    private static void estatisticasSistema() {
        System.out.println("\n→ 4. 📈 ESTATÍSTICAS DO SISTEMA");

        try {
            List<Usuario> listaUsuarios = controlUsuario.relatorio();
            List<Treino> listaTreinos = controlTreino.relatorio();

            System.out.println("📊 DADOS GERAIS:");
            System.out.println("Total de usuários cadastrados: " + listaUsuarios.size());
            System.out.println("Total de treinos cadastrados: " + listaTreinos.size());

            if (!listaUsuarios.isEmpty()) {
                System.out.printf("Média de treinos por usuário: %.2f%n",
                        (double)listaTreinos.size() / listaUsuarios.size());
            }

            // Listar estatísticas por usuário
            if (!listaUsuarios.isEmpty()) {
                System.out.println("\n👥 TREINOS POR USUÁRIO:");
                for (Usuario usuario : listaUsuarios) {
                    List<Treino> treinosUsuario = controlTreino.listarPorUsuario(usuario.getId());
                    System.out.printf("- %s: %d treino(s)%n", usuario.getNome(), treinosUsuario.size());
                }
            }

            // Usuário mais ativo
            if (!listaUsuarios.isEmpty() && !listaTreinos.isEmpty()) {
                String usuarioMaisAtivo = "";
                int maxTreinos = 0;

                for (Usuario usuario : listaUsuarios) {
                    List<Treino> treinosUsuario = controlTreino.listarPorUsuario(usuario.getId());
                    if (treinosUsuario.size() > maxTreinos) {
                        maxTreinos = treinosUsuario.size();
                        usuarioMaisAtivo = usuario.getNome();
                    }
                }

                System.out.println("\n🏆 USUÁRIO MAIS ATIVO:");
                System.out.printf("- %s com %d treino(s)%n", usuarioMaisAtivo, maxTreinos);
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao gerar estatísticas: " + e.getMessage());
        }
    }

    // MENU DE TESTES
    private static void menuTestes() {
        int opcao;

        do {
            System.out.println("""
                                \n══════════════════════════════════════════════════
                                              🧪 EXECUTAR TESTES BSF 🧪          
                                ══════════════════════════════════════════════════
                                  1. 🧪 Testar CRUD de Usuários                 
                                  2. 🧪 Testar CRUD de Treinos                  
                                  3. 🧪 Testar Relatórios                       
                                  4. ⬅️  Voltar ao menu principal                
                                ══════════════════════════════════════════════════
                                ➤ Escolha uma opção: 
                                """);
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    testarCRUDUsuarios();
                    break;
                case 2:
                    testarCRUDTreinos();
                    break;
                case 3:
                    testarRelatorios();
                    break;
                case 4:
                    System.out.println("⬅️ Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("❌ Opção inválida! Digite um número de 1 a 4.");
            }
        } while (opcao != 4);
    }

    private static void testarCRUDUsuarios() {

        System.out.println("\n→ 1. 🧪 TESTE CRUD DE USUÁRIOS");


        // Teste com 3 usuários
        System.out.println("➤ Adicionando usuários de teste...");
        controlUsuario.adicionarUsuario(new Usuario("Ana Maria Santos", "ana.mary", "ana5124!", "ana.maria.santos@empresa.com.br"));
        controlUsuario.adicionarUsuario(new Usuario("João Augusto", "joao.augusto", "joao521", "joao.augusto@estudante.edu.br"));
        controlUsuario.adicionarUsuario(new Usuario("Kauan Lion", "kauanzin", "kaua21543", "kaua.free@freelancer.com"));        System.out.println("\n✅ 3 usuários adicionados.");


        System.out.println("\n➤ Listando usuários após adição:");
        listarUsuarios();

        // Atualizando usuario
        List<Usuario> listaUsuario = controlUsuario.listarTodos();
        if (listaUsuario.size() >= 2) {
            Usuario usuario2 = listaUsuario.get(1);
            usuario2.setNome("Carlos Jose");
            usuario2.setLogin("carlos.se");
            usuario2.setEmail("carlos.jose@seuemail.com");
            controlUsuario.atualizarUsuario(usuario2);
            System.out.println("\n➤ Atualizado segundo usuário.");
        }

          System.out.println("\n➤ Listando após atualização:");
        listarUsuarios();

        if (listaUsuario.size() >= 3) {
            Usuario usuario3 = listaUsuario.get(2);
            controlUsuario.removerUsuario(usuario3);
            System.out.println("\n➤ Removido terceiro usuário.");
        }

        System.out.println("\n➤ Lista final de usuários:");
        listarUsuarios();
    }

    private static void testarCRUDTreinos() {
        System.out.println("\n→ 2. 🧪 TESTE CRUD DE TREINOS");

        List<Usuario> listaUsuarios = controlUsuario.listarTodos();
        if (listaUsuarios.isEmpty()) {
            System.out.println("❌ Não há usuários para associar treinos. Adicione usuários primeiro.");
            return;
        }

        //Necessário selecionar o usuario
        int usuarioId = listaUsuarios.get(0).getId();

        // Nesse teste é adicionado 3 tipos de treinos
        System.out.println("➤ Adicionando treinos de teste...");
        controlTreino.adicionarTreino(new Treino("Musculação: Fullbody", LocalDate.now(), usuarioId));
        controlTreino.adicionarTreino(new Treino("Cardio: Esteira", LocalDate.now().plusDays(1), usuarioId));
        controlTreino.adicionarTreino(new Treino("Alongamento", LocalDate.now().plusDays(2), usuarioId));
        System.out.println("✅ 3 treinos adicionados.");

        // Listar todos os treinos após adição
        System.out.println("\n➤ Listando treinos após adição:");
        listarTreinos();

        // Atualizar o segundo treino
        List<Treino> listaTreino = controlTreino.listarTodos();
        if (listaTreino.size() >= 2) {
            //Instaciando e selecionando o treino na lista
            Treino treino2 = listaTreino.get(1);
            treino2.setNome("Cardio: Escada");
            treino2.setDataExecucao(LocalDate.now().plusDays(3));
            controlTreino.atualizarTreino(treino2);
            System.out.println("\n➤ Atualizado segundo treino.");
        }

        // Listando novamente após a atualização do treino
        System.out.println("\n➤ Listando após atualização:");
        listarTreinos();

        // Remover o terceiro treino
        if (listaTreino.size() >= 3) {
            Treino treino3 = listaTreino.get(2);
            controlTreino.removerTreino(treino3);
            System.out.println("\n➤ Removido terceiro treino.");
        }

        // Lista final de todos os treinos
        System.out.println("\n➤ Lista final de treinos:");
        listarTreinos();
    }

    private static void testarRelatorios() {
        System.out.println("\n→ 3. 🧪 TESTE DE RELATÓRIOS");

        System.out.println("\n➤ Relatório de Usuários:");
        relatorioUsuarios();

        System.out.println("\n➤ Relatório de Treinos:");
        relatorioTreinos();

        System.out.println("\n➤ Relatório Completo:");
        relatorioCompleto();

        System.out.println("\n➤ Estatísticas do Sistema:");
        estatisticasSistema();
    }

    //MENU ESTATÍSTICAS AVANÇADAS
    private static void menuEstatisticas() {
        int opcao;

        do {
            System.out.println("\n══════════════════════════════════════════════════");
            System.out.println("            📈 ESTATÍSTICAS AVANÇADAS             ");
            System.out.println("══════════════════════════════════════════════════");
            System.out.println("  1. 📅 Treinos por período (Mensal)             ");
            System.out.println("  2. 👤 Treinos por usuário específico           ");
            System.out.println("  3. ⬅️  Voltar ao menu principal                ");
            System.out.println("══════════════════════════════════════════════════");
            System.out.print("➤ Escolha uma opção: ");

            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    treinosPorPeriodo();
                    break;
                case 2:
                    treinosPorUsuarioEspecifico();
                    break;
                case 3:
                    System.out.println("⬅️ Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("❌ Opção inválida! Digite 1, 2 ou 3.");
            }
        } while (opcao != 3);
    }

    private static void treinosPorPeriodo() {
        System.out.println("\n→ 1. 📅 TREINOS POR PERÍODO (MENSAL)");

        try {
            System.out.print("Informe o mês (1-12): ");
            int mes = sc.nextInt();

            if (mes < 1 || mes > 12) {
                System.out.println("❌ Mês inválido! Digite um número entre 1 e 12.");
                sc.nextLine();
                return;
            }

            System.out.print("Informe o ano: ");
            int ano = sc.nextInt();
            sc.nextLine();

            if (ano < 1900 || ano > 2100) {
                System.out.println("❌ Ano inválido! Digite um ano entre 1900 e 2100.");
                return;
            }

            // Calculo da  data de início e fim
            LocalDate dataInicio = LocalDate.of(ano, mes, 1);
            LocalDate dataFim = dataInicio.plusMonths(1).minusDays(1);

            System.out.println("\n➤ Período: " + dataInicio.format(formatter) + " a " + dataFim.format(formatter));

            // Buscar todos os treinos nesse período
            List<Treino> todosTreinos = controlTreino.listarTodos();
            List<Treino> treinosPeriodo = new ArrayList<>();
            for (Treino treino : todosTreinos) {
                if (!treino.getDataExecucao().isBefore(dataInicio) && !treino.getDataExecucao().isAfter(dataFim)) {
                    treinosPeriodo.add(treino);
                }
            }

            if (treinosPeriodo.isEmpty()) {
                System.out.println("❌ Nenhum treino encontrado neste período.");
            } else {
                System.out.println("✅ Treinos encontrados:");
                System.out.printf("%-5s %-25s %-12s %-20s%n", "ID", "Nome do Treino", "Data", "Usuário");
                System.out.println("-".repeat(80));
                for (Treino treino : treinosPeriodo) {
                    System.out.printf("%-5d %-25s %-12s %-20s%n",
                            treino.getId(),
                            treino.getNome(),
                            treino.getDataExecucao().format(formatter),
                            treino.getUsuarioNome() != null ? (treino.getUsuarioNome().length() > 18 ? treino.getUsuarioNome().substring(0, 15) + "..." : treino.getUsuarioNome()) :
                                    "Usuário " + treino.getUsuarioId()
                    );
                }
                System.out.println("-".repeat(80));
                System.out.println("📊 Total: " + treinosPeriodo.size() + " treino(s)");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
            sc.nextLine();
        }
    }

    private static void treinosPorUsuarioEspecifico() {
        System.out.println("\n→ 2. 👤 TREINOS POR USUÁRIO ESPECÍFICO");

        try {
            List<Usuario> listaUsuario = controlUsuario.listarTodos();
            if (listaUsuario.isEmpty()) {
                System.out.println("❌ Nenhum usuário cadastrado.");
                return;
            }

            listarUsuarios();
            System.out.print("🆔 Digite o ID do usuário: ");
            int usuarioId = sc.nextInt();
            sc.nextLine();

            // Valida a existencia do ID informado com o ID obtida na lista
            boolean usuarioExiste = false;
            for (Usuario usuario : listaUsuario) {
                if (usuario.getId() == usuarioId) {
                    usuarioExiste = true;
                    break;
                }
            }

            if (!usuarioExiste) {
                System.out.println("❌ Usuário com ID " + usuarioId + " não encontrado.");
                return;
            }

            List<Treino> listaTreinos = controlTreino.listarPorUsuario(usuarioId);
            if (listaTreinos.isEmpty()) {
                System.out.println("❌ Nenhum treino encontrado para este usuário.");
            } else {
                System.out.println("-".repeat(60));
                System.out.println("✅ Treinos do usuário:");
                System.out.printf("%-5s %-25s %-12s%n", "ID", "Nome do Treino", "Data");
                System.out.println("-".repeat(60));
                for (Treino treino : listaTreinos) {
                    System.out.printf("%-5d %-25s %-12s%n",
                            treino.getId(),
                            treino.getNome().length() > 23 ? treino.getNome().substring(0, 20) + "..." : treino.getNome(),
                            treino.getDataExecucao().format(formatter)
                    );
                }
                System.out.println("-".repeat(60));
                System.out.println("📊 Total: " + listaTreinos.size() + " treino(s)");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
            sc.nextLine(); 
        }
    }
}
