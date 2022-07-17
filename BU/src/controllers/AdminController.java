package controllers;

import data.Conectar;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.Admin;


public class AdminController {
    
    public boolean Login(String cpf,String senha){
        Connection con = Conectar.getConectar();
        
        
        String sql = "select * from admin where cpf ='"+cpf+"' and senha='"+senha+"' ";
        //String sql = "select * from admin where cpf=" ";
        ResultSet res = null;
        
        try(PreparedStatement smt = con.prepareStatement(sql)){
            res= smt.executeQuery(sql);
            System.out.println(res);
            if(res != null){
                return true;
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Erro " + ex.getMessage() );
        }
        
        return false;
    }
}
