package controllers;

import data.Conectar;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AdminController {
    
    public boolean Login(int cpf,String senha){
        Connection con = Conectar.getConectar();
        
        String sql = "select * from admin where cpf = '"+cpf+"' and senha='"+senha+"'";
        
        try(PreparedStatement smt = con.prepareStatement(sql)){
            
            ResultSet resultado = smt.executeQuery();
            if(sql != null)
                return true;
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Atualizado com sucesso!");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Erro ao alterar paciente");
        }
        
        return false;
    }
}
