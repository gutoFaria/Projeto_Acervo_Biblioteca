/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import data.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Livro;

/**
 *
 * @author guto
 */
public class LivroController {
    public void CadastrarLivro(Livro l){
        Connection con  = Conectar.getConectar();
        
        //comando sql
        String sql = "insert into livro(titulo,cidade,assunto,ano,cdu,emprestado,numPaginas,editora,autor,numEdicao) values(?,?,?,?,?,?,?,?,?,?)";
        
        //preparedStatement para setar os valores
        
        try(PreparedStatement smt = con.prepareCall(sql)) {
            //para cada ? associa um valor iniciando em 1
            
            smt.setString(1,l.getTitulo());
            smt.setString(2,l.getCidade());
            smt.setString(3,l.getAssunto());
            smt.setString(4,l.getAno());
            smt.setString(5,l.getCdu());
            smt.setBoolean(6,l.isEmprestado());
            smt.setInt(7,l.getNumPaginas());
            smt.setString(8,l.getEditora());
            smt.setString(9,l.getAutores());
            smt.setInt(10,l.getNumEdicao());
            
            smt.executeUpdate();
            smt.close();
            con.close();
            
            JOptionPane.showMessageDialog(null, "Livro cadastrado!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar livro!");
        }
    }
    
    //retornar uma lista de aluno
    public List<Livro> ListarLivros(){
        Connection con = Conectar.getConectar();
        List<Livro> lista = new ArrayList();
        
        String sql = "select * from livro order by titulo";
        
        try(PreparedStatement smt = con.prepareStatement(sql)){
            ResultSet res = smt.executeQuery();
            
            //percorrer o res 
            //colocar os atributos entre parentes assim como eles estão no banco de dados
            while(res.next()){
                Livro l = new Livro();
                l.setIdRegistro(res.getInt("idRegistro"));
                l.setTitulo(res.getString("titulo"));
                l.setCidade(res.getString("cidade"));
                l.setAssunto(res.getString("assunto"));
                l.setAno(res.getString("ano"));
                l.setNumPaginas(res.getInt("numPaginas"));
                l.setEditora(res.getString("editora"));
                l.setAutores(res.getString("autor"));
                l.setNumEdicao(res.getInt("numEdicao"));
                l.setCdu(res.getString("cdu"));
                
                
                lista.add(l);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Erro " + ex.getMessage() );
        }
        
        return lista;
    }
    
    
    //atualizar
    public void AtualizarLivro(Livro l){
        Connection con = Conectar.getConectar();
        
        String sql = "update livro set titulo = ?, cidade = ?, assunto = ?, ano = ?,cdu = ?,"
                + "numPaginas = ?,editora =?,autor = ?,numEdicao = ? where idRegistro = ?";
        
        try(PreparedStatement smt = con.prepareCall(sql)) {
            //para cada ? associa um valor iniciando em 1
            
            smt.setString(1,l.getTitulo());
            smt.setString(2,l.getCidade());
            smt.setString(3,l.getAssunto());
            smt.setString(4,l.getAno());
            smt.setString(5,l.getCdu());
            smt.setInt(6,l.getNumPaginas());
            smt.setString(7,l.getEditora());
            smt.setString(8,l.getAutores());
            smt.setInt(9,l.getNumEdicao());
            
            smt.setInt(10,l.getIdRegistro());
            
            smt.executeUpdate();
            smt.close();
            con.close();
            
            JOptionPane.showMessageDialog(null, "Livro atualizado!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar livro!");
        }
        
    }
    
    
    public void ExcluirLivro(Livro l){
        Connection con = Conectar.getConectar();
        String sql = "delete from livro where idRegistro = ?";
        
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir o livro ?",
                " Excluir",JOptionPane.YES_NO_OPTION);
        
        if(opcao == JOptionPane.YES_OPTION){
            try(PreparedStatement smt = con.prepareStatement(sql)){
                smt.setInt(1,l.getIdRegistro());
                smt.executeUpdate();
                smt.close();
                con.close();
                
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir aluno!");
            }
        }
    }
}
