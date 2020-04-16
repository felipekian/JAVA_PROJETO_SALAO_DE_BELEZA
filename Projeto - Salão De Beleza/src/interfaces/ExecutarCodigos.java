/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author FelipeDerkian
 */
public interface ExecutarCodigos {
    
    public void Excluir(int CPF);
    public void Cadastrar(int CPF , String nome , String telefone);
    public void Alterar(int CPF , String nome , String telefone);
    public boolean pesquisar(int CPF);
    
}
