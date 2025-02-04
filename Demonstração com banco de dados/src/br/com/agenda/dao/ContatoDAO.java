package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;


public class ContatoDAO {

	/*
	 * CRUD
	 * C: CREATE
	 * R: READ
	 * U: UPDATE
	 * D: DELETE
	 */
	
	public void save(Contato contato) {
		
		String sql = "INSERT INTO contatos(nome, fone, email) VALUES (?, ?, ?)";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			// Criar uma conex�o com o banco de dados
			conn = ConnectionFactory.createConnetionToMySQL();
			
			// Criamos uma prepared statement para execurar uma Query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			//Adicionar os valores que s�o esperados pela Query
			pstm.setString(1, contato.getNome());
			pstm.setString(2, contato.getFone());
			pstm.setString(3, contato.getEmail());
			
			// Executar a query
			pstm.execute();
			
			System.out.println("Contato salvo com sucesso! aluno: Gabriel Pinheiro Brants Gon�alves/SP3013456");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			// Fechar as conex�es
			try {
				
				if(pstm!=null)
					pstm.close();
				
				if(conn!=null)
					conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public List<Contato> getContatos(){
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		// Classe que vai recuperar os dados do banco ***SELECT***
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnetionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Contato contato = new Contato();
				
				// Recuperar o id
				contato.setId(rset.getInt("id"));
				
				// Recuperar o nome
				contato.setNome(rset.getString("nome"));
				
				// Recuperar a idade
				contato.setFone(rset.getString("fone"));
				
				// Recuperar a data
				contato.setEmail(rset.getString("email"));
			
				contatos.add(contato);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if(rset!=null)
					rset.close();
				if(pstm!=null)
					pstm.close();
				if(conn!=null)
					conn.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return contatos;
	}

}
