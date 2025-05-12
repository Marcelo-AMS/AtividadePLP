package Entidades;

import java.time.LocalDate;

public class Emprestimo {
    private Livro livro;
    private User usuario;
    private LocalDate dataEmprestimo;
    private LocalDate  dataDevolucao;
    private boolean devolvido;

    public Emprestimo() {}

    public Emprestimo(Livro livro, User usuario, LocalDate  dataEmprestimo, LocalDate  dataDevolucao) {
        this.livro = livro;
        this.usuario = usuario;
        this.setDataEmprestimo(dataEmprestimo);
        this.setDataDevolucao(dataDevolucao);
        this.devolvido = false;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    
}
