package view;

import model.Usuario;

public class TesteUsuario {
    public static void main(String[] args) {
        System.out.println("Localizando usuários em sistema...");

        Usuario[] listaUsuarios = new Usuario[2];

        listaUsuarios[0] = new Usuario();
        listaUsuarios[0].setId(5);
        listaUsuarios[0].setNome("Adriano Mocassim");
        listaUsuarios[0].setLogin("AdrianoMocassim");
        listaUsuarios[0].setEmail("adrimocassim@seuemail.com");

        System.out.println("\nNome:\t" + listaUsuarios[0].getNome());

        listaUsuarios[1] = new Usuario("Lilian Rodrigues Cunha","lilianRodrigues" , "0123456","lilian.rodri@seuemail.com");
        System.out.println("\nNome:\t" + listaUsuarios[1].getNome());

        System.out.println("\n=== RELATÓRIO DE USUARIOS ===");
        for (int i = 0; i < listaUsuarios.length; i++) {
            System.out.println("Usuario " + (i + 1) + ":");
            System.out.println(listaUsuarios[i].toString());
        }

        // Busca sequencial por e-mail
        String emailBuscado = "adrimocassim@seuemail.com";
        System.out.println("\n=== BUSCA POR EMAIL: " + emailBuscado + " ===");
        boolean encontrado = false;
        for (Usuario emailUsuario : listaUsuarios) {
            if (emailUsuario.getEmail().equals(emailBuscado)) {
                System.out.println("Usuario encontrado:");
                System.out.println(listaUsuarios.toString());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Usuario não encontrado.");
        }
    }
}
