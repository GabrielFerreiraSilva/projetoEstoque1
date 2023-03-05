package projetoEstoque1;
public class ClassePrincipal {
    public static void main(String[] args) {
        Servicos svc = new Servicos();
        svc.realizarLogin();
        if(svc.getCargo().equals("gerente")){
            Gerente g1 = new Gerente();
        }
    }
}