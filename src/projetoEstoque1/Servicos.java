package projetoEstoque1;
import java.util.Scanner;
import java.io.File;
public class Servicos {
    private String senhaEsperada;
    private int contadorAuxiliar;
    private String cargo;
    File arquivo = new File("C:/Users/Computador/IdeaProjects/projetoEstoque1/src/projetoEstoque1/funcionarios.txt");
    File arquivo2 = new File("C:/Users/Computador/IdeaProjects/projetoEstoque1/src/projetoEstoque1/produtos.txt");
    public void realizarLogin(){
        while(true){
            int matriculaRecebida = receberMatricula();
            boolean status = verificarMatricula(matriculaRecebida);
            if(status){
                break;
            }
            else{
                System.out.println("A matrícula informada não está cadastrada");
            }
        }
        while(true){
            String senhaRecebida = receberSenha();
            if(senhaRecebida.equals(senhaEsperada)){
                System.out.println("Acesso concedido");
                cargo = identificarCargo();
                break;
            }
            else{
                System.out.println("Senha Incorreta");
            }
        }
        //System.out.println(cargo);
    }
    public void consultarProduto(){
        String marca = null;
        String modelo = null;
        float preco = 0;
        boolean flag = false;
        while(true){
            Scanner teclado = new Scanner(System.in);
            System.out.print("Por favor, digite o código de barras do produto: ");
            long cb = teclado.nextLong();
            try{
                Scanner leitura = new Scanner(arquivo2);
                while(leitura.hasNextLine()){
                    try{
                        Long cbLido = Long.parseLong(leitura.nextLine());
                        marca = leitura.nextLine();
                        modelo = leitura.nextLine();
                        preco = Float.parseFloat(leitura.nextLine());
                        if(cbLido == cb){
                            System.out.println("Código de barras: " + cb);
                            System.out.println("Marca: " + marca);
                            System.out.println("Modelo: " + modelo);
                            System.out.println("Preço: R$ " + preco);
                            flag = true;
                            break;
                        }
                    }
                    catch(Exception exception){
                        continue;
                    }
                }
            }
            catch(Exception exception){
                System.out.println("Erro ao acessar a base de dados");
            }
            if(!flag){
                System.out.println("O produto não está cadastrado");
            }
            else{
                break;
            }
        }
    }
    private int receberMatricula(){
        System.out.print("Por favor, digite sua matrícula: ");
        Scanner teclado = new Scanner(System.in);
        return teclado.nextInt();
    }
    private boolean verificarMatricula(int mat){
        boolean flag = false;
        try
        {
            Scanner leitura = new Scanner(arquivo);
            while(leitura.hasNextLine()){
                contadorAuxiliar++;
                if(flag){
                    senhaEsperada = leitura.nextLine();
                    break;
                }
                try
                {
                    int matriculaLida = Integer.parseInt(leitura.nextLine());
                    if(matriculaLida == mat){
                        flag = true;
                        continue;
                    }
                }
                catch(Exception exception)
                {
                    continue;
                }
            }
        }
        catch(Exception exception)
        {
            System.out.println("Ocorreu um erro ao acessar a base de dados");
        }
        return flag;
    }
    private String receberSenha(){
        System.out.print("Por favor, digite sua senha: ");
        Scanner teclado = new Scanner(System.in);
        return teclado.nextLine();
    }
    private String identificarCargo(){
        int contadorInterno = contadorAuxiliar;
        String cargo = null;
        try
        {
            Scanner leitura = new Scanner(arquivo);
            while(contadorInterno >= 0){
                cargo = leitura.nextLine();
                contadorInterno--;
            }
        }
        catch(Exception exception)
        {
            System.out.println("Erro");
        }
        return cargo;
    }
}