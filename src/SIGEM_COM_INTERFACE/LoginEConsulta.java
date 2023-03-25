package SIGEM_COM_INTERFACE;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class LoginEConsulta {
    private String matriculaRecebida;
    private String senhaRecebida;
    private boolean flag = false;
    private boolean acabou;
    private String cargo;
    File arquivo = new File("C:/Users/Computador/IdeaProjects/projetoEstoque1/src/SIGEM_COM_INTERFACE/funcionarios.txt");
    File arquivo2 = new File("C:/Users/Computador/IdeaProjects/projetoEstoque1/src/SIGEM_COM_INTERFACE/produtos.txt");
    public LoginEConsulta(String mr, String sr){
        this.matriculaRecebida = mr;
        this.senhaRecebida = sr;
    }
    public void realizarLogin(){
        try {
            Scanner leitura = new Scanner(arquivo);
            while (leitura.hasNextLine()) {
                if(leitura.nextLine().equals(this.matriculaRecebida)){
                    if(leitura.nextLine().equals(this.senhaRecebida)){
                        this.flag = true;
                        this.cargo = leitura.nextLine();
                    }
                }
            }
            this.acabou = true;
        }
        catch(FileNotFoundException e){
            System.out.println("Ocorreu um erro!");
        }
    }
    public void consultarProduto(){
        boolean flag = false;
        String cb = JOptionPane.showInputDialog(null, "Digite o Código de Barras do Produto", "Consultar Produto", JOptionPane.PLAIN_MESSAGE);
        try{
            Scanner leitura = new Scanner(arquivo2);
            while(leitura.hasNextLine()){
                if(leitura.nextLine().equals(cb)){
                    JOptionPane.showMessageDialog(null, "Código de Barras: " + cb + "\nMarca: "
                            + leitura.nextLine() + "\nModelo: " + leitura.nextLine() + "\nPreço: " + leitura.nextLine()
                            + "\nQuantidade no Estoque: " + leitura.nextLine(), "Consultar Produto", JOptionPane.PLAIN_MESSAGE);
                    flag = true;
                    break;
                }
            }
            if(!flag){
                JOptionPane.showMessageDialog(null, "Produto Não Encontrado", "Consultar Produto", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Ocorreu um erro!");
        }
    }
    public boolean getAcabou(){
        return this.acabou;
    }
    public boolean getFlag(){
        return this.flag;
    }
    public String getCargo(){
        return this.cargo;
    }
}