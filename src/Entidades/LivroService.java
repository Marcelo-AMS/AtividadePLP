package Entidades;

import java.util.ArrayList;
import java.util.Scanner;

public class LivroService extends Service<Livro> {
    private Scanner entradaSt = new Scanner(System.in);
    private Scanner entradaInt = new Scanner(System.in);

    @Override
    public void cadastro(ArrayList<Livro> biblioteca) {
        System.out.println("Informe o Título do livro a ser registrado: ");
        String titulo = entradaSt.nextLine();

        System.out.println("Informe o gênero do livro: ");
        String genero = entradaSt.nextLine();

        System.out.println("Informe o nome do Autor: ");
        String autor = entradaSt.nextLine();

        System.out.println("Informe a Editora: ");
        String editora = entradaSt.nextLine();

        System.out.println("Informe o ISBN: ");
        String isbn = entradaSt.nextLine();

        System.out.println("Informe o Ano de publicação: ");
        int anoPublicacao = entradaInt.nextInt();
        entradaInt.nextLine();

        System.out.println("Informe a quantidade de Exemplares disponíveis: ");
        int quantidadeDisponivel = entradaInt.nextInt();
        entradaInt.nextLine();

        Livro livro = new Livro(titulo, autor, editora, isbn, genero, anoPublicacao, quantidadeDisponivel);
        biblioteca.add(livro);

        System.out.println("Livro cadastrado com sucesso!");
    }

    @Override
    public void excluir(ArrayList<Livro> biblioteca) {
        System.out.println("Informe o ISBN do livro que deseja excluir: ");
        String idTemp = entradaSt.nextLine();

        for (Livro book : biblioteca) {
            if (book.getIsbn().equals(idTemp)) {
                biblioteca.remove(book);
                System.out.println("Livro removido com sucesso!");
                return;
            }
        }

        System.out.println("Livro não encontrado!");
    }

    @Override
    public void visualizar(ArrayList<Livro> biblioteca) {
        System.out.println("Informe o ISBN do livro que deseja visualizar: ");
        String idTemp = entradaSt.nextLine();

        for (Livro book : biblioteca) {
            if (book.getIsbn().equals(idTemp)) {
                System.out.println("_______________________________________________");
                System.out.printf(
                        "Título: %s\nAutor: %s\nISBN: %s\nGênero: %s\nEditora: %s\nAno: %d\nEstoque: %d\n",
                        book.getTitulo(), book.getAutor(), book.getIsbn(),
                        book.getGenero(), book.getEditora(),
                        book.getAnoPublicacao(), book.getQuantidadeDisponivel());
                System.out.println("_______________________________________________");
                return;
            }
        }
        System.out.println("Livro não encontrado!");
    }
}
