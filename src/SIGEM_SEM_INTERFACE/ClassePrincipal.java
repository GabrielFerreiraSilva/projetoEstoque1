package SIGEM_SEM_INTERFACE;
public class ClassePrincipal {
    public static void main(String[] args) {
        Servicos svc = new Servicos();
        System.out.println("=================================");
        System.out.println("Olá Colaborador!");
        svc.realizarLogin();
        if(svc.getCargo().equals("gerente")){
            Gerente g1 = new Gerente();
        }else if(svc.getCargo().equals("repositor")){
            Repositor r1 = new Repositor();
        }else if(svc.getCargo().equals("estoquista")){
            Estoquista e1 = new Estoquista();
        }
    }
}