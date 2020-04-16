package model.dao;

import com.mysql.jdbc.PreparedStatement;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Servico;

/**
 *
 * @author Felipe Derkian de Sousa Freitas
 */


public class ServicosDAO {
    
    public void create(Servico s){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {            
                stmt = (PreparedStatement) con.prepareStatement("INSERT INTO Servico ( horario , calendario , preco , id_cliente , id_func) VALUES (?,?,?,?,?)");
            
            
            stmt.setInt(    1, s.getHorario());
            stmt.setString( 2,s.getCalendario());
            stmt.setDouble( 3, s.getPreco());
            stmt.setInt(    4, s.getId_cliente());
            stmt.setInt(    5, s.getId_func());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao SALVAR: "+ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }   
    
    
    
}
