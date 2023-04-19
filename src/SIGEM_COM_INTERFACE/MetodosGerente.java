package SIGEM_COM_INTERFACE;

import javax.swing.*;
import java.io.File;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class MetodosGerente {
    File arquivo = new File("C:/Users/Computador/IdeaProjects/projetoEstoque1/src/SIGEM_COM_INTERFACE/funcionarios.txt");
    File arquivo2 = new File("C:/Users/Computador/IdeaProjects/projetoEstoque1/src/SIGEM_COM_INTERFACE/produtos.txt");
    File auxiliar = new File("C:/Users/Computador/IdeaProjects/projetoEstoque1/src/SIGEM_COM_INTERFACE/auxiliar.txt");
    public void adicionarProduto(){
        String cb = JOptionPane.showInputDialog(null, "Digite o Código de Barras do Novo Produto", "Adicionar Produto Ao Estoque", JOptionPane.PLAIN_MESSAGE);
        String marca = JOptionPane.showInputDialog(null, "Digite a Marca do Novo Produto", "Adicionar Produto Ao Estoque", JOptionPane.PLAIN_MESSAGE);
        String modelo = JOptionPane.showInputDialog(null, "Digite o Modelo do Novo Produto", "Adicionar Produto Ao Estoque", JOptionPane.PLAIN_MESSAGE);
        String preco = JOptionPane.showInputDialog(null, "Digite o Preço do Novo Produto", "Adicionar Produto Ao Estoque", JOptionPane.PLAIN_MESSAGE);
        try{
            FileWriter escrita = new FileWriter(arquivo2, true);
            escrita.write(cb + "\n");
            escrita.write(marca + "\n");
            escrita.write(modelo + "\n");
            escrita.write(preco + "\n");
            escrita.write("0\n");
            escrita.close();
            JOptionPane.showMessageDialog(null, "Produto Adicionado Com Sucesso", "Adicionar Produto Ao Estoque", JOptionPane.PLAIN_MESSAGE);
        }
        catch(Exception exception){
            System.out.println("Erro ao cadastrar o novo produto");
        }
    }
    public void retirarProduto(){
        try{
            String cb = JOptionPane.showInputDialog(null, "Digite o Código de Barras do Produto", "Retirar Produto do Cadastro", JOptionPane.PLAIN_MESSAGE);
            Scanner leitura = new Scanner(arquivo2);
            FileWriter escrita = new FileWriter(auxiliar, true);
            int contador = 0;
            while(leitura.hasNextLine()){
                String linhaLida = leitura.nextLine();
                if(!(linhaLida.equals(cb))){
                    escrita.write(linhaLida + "\n");
                    contador++;
                }
                else{
                    break;
                }
            }
            contador += 5;
            Scanner leitura2 = new Scanner(arquivo2);
            while(leitura2.hasNextLine()){
                String linhaLida = leitura2.nextLine();
                if(contador > 0){
                    contador--;
                    continue;
                }
                escrita.write(linhaLida + "\n");
            }
            escrita.close();
            FileWriter escrita2 = new FileWriter(arquivo2, false);
            escrita2.write("");
            escrita2.close();
            Scanner leitura3 = new Scanner(auxiliar);
            FileWriter escrita3 = new FileWriter(arquivo2, true);
            while(leitura3.hasNextLine()){
                String linhaLida = leitura3.nextLine();
                escrita3.write(linhaLida + "\n");
            }
            escrita3.close();
            FileWriter escrita4 = new FileWriter(auxiliar, false);
            escrita4.write("");
            escrita4.close();
            JOptionPane.showMessageDialog(null, "Produto Removido do Cadastro Com Sucesso", "Retirar Produto do Cadastro", JOptionPane.PLAIN_MESSAGE);
        }
        catch(Exception exception){
            System.out.println("Ocorreu um erro!");
        }
    }
    public void adicionarFuncionario(){
        String matricula = JOptionPane.showInputDialog(null, "Digite a Matrícula do Novo Funcionário", "Adicionar Funcionário Ao Cadastro", JOptionPane.PLAIN_MESSAGE);
        String senha = JOptionPane.showInputDialog(null, "Digite a Senha do Novo Funcionário", "Adicionar Funcionário Ao Cadastro", JOptionPane.PLAIN_MESSAGE);
        String cargo = JOptionPane.showInputDialog(null, "Digite o Cargo do Novo Funcionário", "Adicionar Funcionário Ao Cadastro", JOptionPane.PLAIN_MESSAGE);
        try{
            FileWriter escrita = new FileWriter(arquivo, true);
            escrita.write(matricula + "\n");
            escrita.write(senha + "\n");
            escrita.write(cargo + "\n");
            escrita.close();
            JOptionPane.showMessageDialog(null, "Funcionário Adicionado Ao Cadastro Com Sucesso", "Adicionar Funcionário Ao Cadastro", JOptionPane.PLAIN_MESSAGE);
        }
        catch(Exception exception){
            System.out.println("Erro ao cadastrar o novo funcionário");
        }
    }
    public void removerFuncionario(){
        try{
            String matricula = JOptionPane.showInputDialog(null, "Digite a Matrícula do Funcionário", "Retirar Funcionário do Cadastro", JOptionPane.PLAIN_MESSAGE);
            Scanner leitura = new Scanner(arquivo);
            FileWriter escrita = new FileWriter(auxiliar, true);
            int contador = 0;
            while(leitura.hasNextLine()){
                String linhaLida = leitura.nextLine();
                if(!(linhaLida.equals(matricula))){
                    escrita.write(linhaLida + "\n");
                    contador++;
                }
                else{
                    break;
                }
            }
            contador += 5;
            Scanner leitura2 = new Scanner(arquivo);
            while(leitura2.hasNextLine()){
                String linhaLida = leitura2.nextLine();
                if(contador > 0){
                    contador--;
                    continue;
                }
                escrita.write(linhaLida + "\n");
            }
            escrita.close();
            FileWriter escrita2 = new FileWriter(arquivo, false);
            escrita2.write("");
            escrita2.close();
            Scanner leitura3 = new Scanner(auxiliar);
            FileWriter escrita3 = new FileWriter(arquivo, true);
            while(leitura3.hasNextLine()){
                String linhaLida = leitura3.nextLine();
                escrita3.write(linhaLida + "\n");
            }
            escrita3.close();
            FileWriter escrita4 = new FileWriter(auxiliar, false);
            escrita4.write("");
            escrita4.close();
            JOptionPane.showMessageDialog(null, "Funcionário Removido Do Cadastro Com Sucesso", "Retirar Funcionário do Cadastro", JOptionPane.PLAIN_MESSAGE);
        }
        catch(Exception exception){
            System.out.println("Ocorreu um erro!");
        }
    }

    public void alterarSenha(){
        String matricula = JOptionPane.showInputDialog(null, "Digite sua matrícula", "Alterar Senha", JOptionPane.PLAIN_MESSAGE);
        String novaSenha = JOptionPane.showInputDialog(null, "Digite a Nova Senha", "Alterar Senha", JOptionPane.PLAIN_MESSAGE);
        try{
            Scanner leitura = new Scanner(arquivo);
            while(leitura.hasNextLine()){
                String matriculaLida = leitura.nextLine();
                String senhaLida = leitura.nextLine();
                String cargoLido = leitura.nextLine();
                if(matriculaLida.equals(matricula)){
                    FileWriter escritor2 = new FileWriter(auxiliar, true);
                    escritor2.write(matriculaLida + "\n");
                    escritor2.write(novaSenha + "\n");
                    escritor2.write(cargoLido + "\n");
                    escritor2.close();
                }
                else{
                    FileWriter escritor1 = new FileWriter(auxiliar, true);
                    escritor1.write(matriculaLida + "\n");
                    escritor1.write(senhaLida + "\n");
                    escritor1.write(cargoLido + "\n");
                    escritor1.close();
                }
            }
            PrintWriter limpador = new PrintWriter(arquivo);
            limpador.flush();
            limpador.close();
            Scanner leitura2 = new Scanner(auxiliar);
            while(leitura2.hasNextLine()){
                String matriculaLida = leitura2.nextLine();
                String senhaLida = leitura2.nextLine();
                String cargoLido = leitura2.nextLine();
                FileWriter escritor3 = new FileWriter(arquivo, true);
                escritor3.write(matriculaLida + "\n");
                escritor3.write(senhaLida + "\n");
                escritor3.write(cargoLido + "\n");
                escritor3.close();
            }
            PrintWriter limpador2 = new PrintWriter(auxiliar);
            limpador2.flush();
            limpador2.close();
            JOptionPane.showMessageDialog(null, "Senha Alterada Com Sucesso", "Alterar Senha", JOptionPane.PLAIN_MESSAGE);
        }
        catch(Exception e){
            System.out.println("Ocorreu um erro");
        }
    }

}