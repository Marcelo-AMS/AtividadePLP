package Entidades;

import java.util.ArrayList;

public abstract class Service<T> {
    protected void cadastro(ArrayList<T> lista){}
    protected void excluir(ArrayList<T> lista){}
    protected abstract void visualizar(ArrayList<T> lista);
}
