package model.dao;

import com.mysql.jdbc.PreparedStatement;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cliente;
import model.bean.Login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC Ryzen
 */
public class LoginDAO {
    
    public void create(Login l){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
            
            try {          
                
                stmt = (PreparedStatement) con.prepareStatement("INSERT INTO login (login , senha) VALUES (?,?)");
                
                stmt.setString(1, l.getUsuario());            
                stmt.setString(2,l.getSenha());
                                             
                stmt.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Salvo com sucesso");
                
            } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "Erro ao cadastrar: "+ex);
            } finally{
                 ConnectionFactory.closeConnection(con, stmt);
            }
           
            
    } 
    
    
    public boolean checkLogin(String login , String senha ){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean check = false;
        
       
        try {
            
            stmt = (PreparedStatement) con.prepareStatement("SELECT * FROM login where login=? and senha=?;");
            stmt.setString(1, login);
            stmt.setString(2, senha);            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                check = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
            
       
        
        
        return check;
    }
    
    
}
