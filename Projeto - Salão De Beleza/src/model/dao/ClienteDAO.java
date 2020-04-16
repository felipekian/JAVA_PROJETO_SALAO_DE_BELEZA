/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.mysql.jdbc.PreparedStatement;
import connection.ConnectionFactory;
import interfaces.ExecutarCodigos;
import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Cliente;
import view.TelaPesquisarCliente;

/**
 *
 * @author Felipe Derkian de Sousa Freitas
 */
public class ClienteDAO implements ExecutarCodigos {
    
     public void create(Cliente s){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
            
            try {
                                
                
                stmt = (PreparedStatement) con.prepareStatement("INSERT INTO cliente (cpf , nome , telefone) VALUES (?,?,?)");
                stmt.setInt(1, s.getCpf());            
                stmt.setString(2,s.getNome());
                stmt.setString(3,s.getTelefone());               
                
                stmt.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Salvo com sucesso");
                
            } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "Erro ao SALVAR: "+ex);
            } finally{
                 ConnectionFactory.closeConnection(con, stmt);
            }
           
            
    }
     
    
    public ArrayList<Cliente> read(){
        
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null; 
        
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        
        
         try {
                          
             stmt = (PreparedStatement) con.prepareStatement("SELECT * FROM cliente");             
             
             rs = stmt.executeQuery();
             
             while(rs.next()){
                          
                 Cliente c = new Cliente();
                 
                 c.setCpf(rs.getInt("cpf"));
                 c.setNome(rs.getString("nome"));
                 c.setTelefone(rs.getString("telefone"));
                 clientes.add(c);
             }
             
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro: "+ex);
         } finally{
             ConnectionFactory.closeConnection(con, stmt, rs);
         }
                
         return clientes;   
    }

    @Override
    public void Excluir(int CPF) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("DELETE FROM cliente WHERE cpf = ?;");
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
   
        Cliente n = new  Cliente();                       //instancia a class cliente
        ClienteDAO t = new ClienteDAO();                   //instancia a class clienteDAO
        
        
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
            
            stmt = (PreparedStatement) con.prepareStatement("UPDATE cliente SET nome = ?, cpf = ? , telefone = ?;");
            stmt.setString(1, nome);
            stmt.setInt(2, CPF);
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
            stmt = (PreparedStatement) con.prepareStatement("SELECT * FROM cliente WHERE cpf=?");            
            stmt.setInt(1,CPF);
                       
            rs = stmt.executeQuery();
            
            
            
            while(rs.next()){                          
                 t = true;
            }
                    
            
        } catch (SQLException ex) {
            Logger.getLogger(TelaPesquisarCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);            
            
        }
        return t;
    
    }

    
}
