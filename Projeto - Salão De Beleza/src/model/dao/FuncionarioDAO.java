/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.mysql.jdbc.PreparedStatement;
import connection.ConnectionFactory;
import interfaces.ExecutarCodigos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Cliente;
import model.bean.Funcionario;

/**
 *
 * @author Felipe Derkian de Sousa Freitas
 */

public class FuncionarioDAO implements ExecutarCodigos{
    
    public void create(Funcionario s){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
                                        
                
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("INSERT INTO funcionario (cpf , nome , telefone , endereco , especialidade) VALUES (?,?,?,?,?)");
            
            stmt.setInt   ( 1,  s.getCpf());            
            stmt.setString( 2,  s.getNome());
            stmt.setString( 3,  s.getTelefone());
            stmt.setString( 4,  s.getEndereco());
            stmt.setString( 5,  s.getEspecialidade());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao SALVAR: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
             
            
    }
    
    public ArrayList<Funcionario> read(){
                
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null; 
        
        ArrayList<Funcionario> func = new ArrayList<Funcionario>();
                
         try {
                         
              
             stmt = (PreparedStatement) con.prepareStatement("SELECT * FROM funcionario");    
             
             rs = stmt.executeQuery();
             
             while(rs.next()){
                          
                 Funcionario f = new Funcionario();
                 
                 f.setCpf(rs.getInt("cpf"));
                 f.setNome(rs.getString("nome"));
                 f.setTelefone(rs.getString("telefone"));
                 f.setEndereco(rs.getString("endereco"));
                 f.setEspecialidade(rs.getString("especialidade"));
                 Funcionario.add(f);
             }
             
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro: "+ex);
         } finally{
             ConnectionFactory.closeConnection(con, stmt, rs);
         }
                
         return func;
        
    }

    @Override
    public void Excluir(int CPF) {
   
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
                        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("DELETE FROM funcionario WHERE cpf = ?;");
            stmt.setInt(1,CPF);
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
            
        }
    
    
    }

    @Override
    public void Cadastrar(int CPF , String nome , String telefone) {
        
        Funcionario n = new  Funcionario();                       //instancia a class cliente
        FuncionarioDAO t = new FuncionarioDAO();                   //instancia a class clienteDAO
        
        
        n.setCpf(CPF);      //pega os valores digitados e salva
        n.setNome(nome);
        n.setTelefone(telefone);
              
        
        t.create(n); 
    
    
    }

    @Override
    public void Alterar(int CPF , String nome , String telefone) {
   
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("UPDATE funcionario SET cpf = ?, nome = ? , telefone = ? , endereco=? , especialidade=?;");
            
            stmt.setInt(1, CPF);
            stmt.setString(2, nome);
            stmt.setString(3, telefone);
            
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    
    
    }

    @Override
    public boolean pesquisar(int CPF) {
    
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean t = false;
        
        try {
            stmt = (PreparedStatement) con.prepareStatement("SELECT * FROM funcionario WHERE cpf=?");
            
            stmt.setInt(1,CPF);
                       
            rs = stmt.executeQuery();
                        
            while(rs.next()){
               t = true;   
            }
                    
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
            
        }
        return t;
    }
    
    
}
