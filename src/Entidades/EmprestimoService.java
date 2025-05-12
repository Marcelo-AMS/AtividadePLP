package Entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class EmprestimoService extends Service<Emprestimo>{
    private Scanner entrada = new Scanner(System.in);

    public boolean verificacao(ArrayList<User> listaUser, String isbn, int id) {
        for (User user : listaUser) {
            if (user.getId() == id) {
                for (int i = 0; i < user.getHistoricoEmprestimo().length; i++) {
                    Emprestimo emprestimo = user.getHistoricoEmprestimo()[i];
                    if (emprestimo != null && emprestimo.getLivro().getIsbn().equalsIgnoreCase(isbn)) {
                        System.out.println("O empréstimo não pode ser concluído, pois o usuário já possui um livro com este ISBN!");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void registroEmprestimo(ArrayList<User> listaUser, ArrayList<Livro> biblioteca, ArrayList<Emprestimo> emprestimos) {
        System.out.println("Informe o ISBN do livro para registrar o empréstimo: ");
        String isbnaux = entrada.nextLine();

        System.out.println("Informe o ID do usuário para registrar o empréstimo: ");
        int idaux = entrada.nextInt();
        entrada.nextLine();

        Livro livro = null;
        User usuario = null;

        for (Livro livro1 : biblioteca) {
            if (livro1.getIsbn().equals(isbnaux)) {
                livro = livro1;
                break;
            }
        }

        for (User user : listaUser) {
            if (user.getId() == idaux) {
                usuario = user;
                break;
            }
        }

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        if (livro.getQuantidadeDisponivel() <= 0) {
            System.out.println("Nenhum exemplar disponível.");
            return;
        }

        if (!verificacao(listaUser, isbnaux, idaux)) {
            return;
        }

        int cont = 0;
        for (Emprestimo emprestimo : usuario.getHistoricoEmprestimo()) {
            if (emprestimo != null) {
                cont++;
            }
        }

        if (cont >= 3) {
            System.out.println("O usuário atingiu o limite de empréstimos!");
            return;
        }

        Emprestimo novoEmprestimo = new Emprestimo();
        novoEmprestimo.setUsuario(usuario);
        novoEmprestimo.setLivro(livro);
        novoEmprestimo.setDataEmprestimo(LocalDate.now());
        novoEmprestimo.setDataDevolucao(LocalDate.now().plusMonths(1));
        novoEmprestimo.setDevolvido(false);

        emprestimos.add(novoEmprestimo);
        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);

        for (int i = 0; i < usuario.getHistoricoEmprestimo().length; i++) {
            if (usuario.getHistoricoEmprestimo()[i] == null) {
                usuario.getHistoricoEmprestimo()[i] = novoEmprestimo;
                break;
            }
        }

        System.out.println("Empréstimo registrado com sucesso!");
    }

    @Override
    public void visualizar(ArrayList<Emprestimo> emprestimos) {
        System.out.println("Informe o ID do usuário para verificar o status de empréstimo: ");
        int auxID = entrada.nextInt();
        entrada.nextLine();

        System.out.println("Informe o ISBN do livro para verificar o status de empréstimo: ");
        String auxISBN = entrada.nextLine();

        for (Emprestimo auxEmprestimo : emprestimos) {
            if (auxEmprestimo.getUsuario().getId() == auxID &&
                    auxEmprestimo.getLivro().getIsbn().equalsIgnoreCase(auxISBN)) {

                String status = auxEmprestimo.isDevolvido() ? "Devolvido" : "Encaminhado";

                System.out.println("_______________________________________________");
                System.out.printf("Nome: %s\nLivro: %s\nData de empréstimo: %s\nData de devolução: %s\nStatus: %s\n",
                        auxEmprestimo.getUsuario().getNome(), auxEmprestimo.getLivro().getTitulo(),
                        auxEmprestimo.getDataEmprestimo(), auxEmprestimo.getDataDevolucao(), status);
                System.out.println("_______________________________________________");
                return;
            }
        }
        System.out.println("Empréstimo não encontrado.");
    }

    public void registroDevolucao(ArrayList<Emprestimo> emprestimos) {
        System.out.println("Informe o ID do usuário para registrar a devolução: ");
        int auxID = entrada.nextInt();
        entrada.nextLine();

        System.out.println("Informe o ISBN do livro para registrar a devolução: ");
        String auxISBN = entrada.nextLine();

        boolean encontrado = false;
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().getId() == auxID &&
                    emprestimo.getLivro().getIsbn().equalsIgnoreCase(auxISBN)) {

                emprestimo.setDevolvido(true);
                emprestimo.getLivro().setQuantidadeDisponivel(emprestimo.getLivro().getQuantidadeDisponivel() + 1);
                System.out.println("Devolução registrada com sucesso!");
                encontrado = true;

                for (int i = 0; i < emprestimo.getUsuario().getHistoricoEmprestimo().length; i++) {
                    if (emprestimo.getUsuario().getHistoricoEmprestimo()[i] != null &&
                            emprestimo.getUsuario().getHistoricoEmprestimo()[i].getLivro().getIsbn().equalsIgnoreCase(auxISBN)) {

                        emprestimo.getUsuario().getHistoricoEmprestimo()[i] = null;
                        break;
                    }
                }
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Empréstimo não encontrado.");
        }
    }
}
