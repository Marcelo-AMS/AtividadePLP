package Entidades;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService extends Service<User>{
    private Scanner entradaInt = new Scanner(System.in);
    private Scanner entradaSt = new Scanner(System.in);

    @Override
    public void cadastro(ArrayList<User> listaUser) {
        System.out.println("Informe o nome do Usuário a ser cadastrado: ");
        String nomeaux = entradaSt.nextLine();

        System.out.println("Informe a idade do Usuário a ser cadastrado: ");
        int idadeaux = entradaInt.nextInt();
        entradaSt.nextLine();

        if (idadeaux >= 15) {
            System.out.println("Informe o CPF do Usuário a ser cadastrado: ex: xxx.xxx.xxx-xx");
            String cpf = entradaSt.nextLine();

            System.out.println("Informe o ID do Usuário a ser cadastrado: ");
            int ID = entradaInt.nextInt();
            entradaSt.nextLine();

            System.out.println("Informe o Email do Usuário a ser cadastrado: ");
            String email = entradaSt.nextLine();

            System.out.println("Informe o número telefônico do Usuário para contato: ");
            String telefone = entradaSt.nextLine();

            System.out.println("Informe o Endereço residencial do Usuário: ");
            String endereco = entradaSt.nextLine();

            User novoUser = new User(nomeaux, cpf, idadeaux, endereco, email, telefone, ID);
            listaUser.add(novoUser);
        } else {
            System.out.println("O Usuário não pode ser cadastrado pois só é permitido o cadastro de pessoas com idade acima de 14 anos!");
        }
    }

    @Override
    public void excluir(ArrayList<User> listaUser) {
        System.out.println("Informe o ID do usuário que deseja excluir: ");
        int idTemp = entradaInt.nextInt();
        entradaSt.nextLine();
        for (User user : listaUser) {
            if (user.getId() == idTemp) {
                listaUser.remove(user);
                System.out.println("Usuário desvinculado!");
                return;
            }
        }
        System.out.println("Usuário não encontrado!");
    }

    @Override
    public void visualizar(ArrayList<User> listaUser) {
        System.out.println("Informe o ID do usuário que deseja visualizar: ");
        int idTemp = entradaInt.nextInt();
        entradaSt.nextLine();
        for (User user : listaUser) {
            if (user.getId() == idTemp) {
                System.out.println("_______________________________________________");
                System.out.printf("Nome: %s\nIdade: %d\nCPF: %s\nID: %d\nTelefone: %s\nEmail: %s\nEndereço: %s",
                        user.getNome(), user.getIdade(), user.getCpf(), user.getId(), user.getTelefone(), user.getEmail(), user.getEndereco());

                String status;
                System.out.println("\n\nEmpréstimos Registrados.");
                for (Emprestimo emprestimo : user.getHistoricoEmprestimo()) {
                    if (emprestimo != null) {
                        status = emprestimo.isDevolvido() ? "Devolvido" : "Encaminhado";
                        System.out.println("\nNome: " + emprestimo.getUsuario().getNome() +
                                "\nLivro: " + emprestimo.getLivro().getTitulo() +
                                "\nData de Empréstimo: " + emprestimo.getDataEmprestimo() +
                                "\nData de Devolução: " + emprestimo.getDataDevolucao() +
                                "\nStatus: " + status);
                        System.out.println("_______________________________________________");
                    }
                }
                if (user.getHistoricoEmprestimo()[0] == null) {
                    System.out.println("Usuário não possui empréstimos realizados!");
                    System.out.println("_______________________________________________");
                }
                return;
            }
        }
        System.out.println("Usuário não encontrado!");
    }
}
