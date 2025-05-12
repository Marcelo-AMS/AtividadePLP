package Entidades;

public class User extends Pessoa{
    private String endereco;
    private String email;
    private String telefone;
    private int id;
    private Emprestimo[] historicoEmprestimo;

    public User(String nome, String cpf, int idade, String endereco, String email, String telefone, int id) {
        super(nome, cpf, idade);
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.id = id;
        this.historicoEmprestimo = new Emprestimo[3];
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Emprestimo[] getHistoricoEmprestimo() {
        return historicoEmprestimo;
    }

    public void setHistorico(Emprestimo[] historico) {
        this.historicoEmprestimo = historico;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
