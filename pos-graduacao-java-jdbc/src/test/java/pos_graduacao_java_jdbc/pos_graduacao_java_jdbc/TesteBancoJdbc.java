package pos_graduacao_java_jdbc.pos_graduacao_java_jdbc;

import java.util.List;

import org.junit.Test;

import Dao.UserPosDAO;
import model.BeanUserFone;
import model.Telefone;
import model.Userposgraduacao;

public class TesteBancoJdbc {
	@Test
	public void initBanco() { // metodo de insert
		
		UserPosDAO userPosDAO = new UserPosDAO();
		Userposgraduacao userposgraduacao = new Userposgraduacao();
		
		//userposgraduacao.setId(4L);
		userposgraduacao.setNome("dracula");
		userposgraduacao.setEmail("dracula@totalcomunicacao.com.br");
				
		
		
		userPosDAO.salvar(userposgraduacao);
	}
	@Test
	public void initListar( ) throws Exception {
		UserPosDAO dao = new UserPosDAO();
		List<Userposgraduacao>list = dao.listar();
		try {
			List<Userposgraduacao> list1 = dao.listar();
			
			for (Userposgraduacao userposgraduacao : list1) {
				System.out.println(userposgraduacao);
				System.out.println("-----------------------");
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void initbuscar() {
		UserPosDAO dao = new UserPosDAO();
		
		try {
			Userposgraduacao userposgraduacao = dao.buscar(5L);
			
			System.out.println(userposgraduacao);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void initAtualizar() {
		try {
			
		
		UserPosDAO dao = new UserPosDAO();
		Userposgraduacao objetoBanco = dao.buscar(5L);
		objetoBanco.setNome("Nome mudado com metodo atualizar");
		
		dao.atualizar(objetoBanco);
		//UserPosDAO objetoBanco = dao.buscar(5L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	@Test
	public void initDeletar() {
		try {
			UserPosDAO dao = new UserPosDAO();
			dao.deletar(9L);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testeInsertTelefone() {
		
		Telefone telefone = new Telefone();
		telefone.setNumero("(61) 5589-3484");
		telefone.setTipo("Casa");
		telefone.setUsuario(4L);
		
		UserPosDAO dao = new UserPosDAO();
		dao.salvarTelefone(telefone);
		
		
	}
	@Test
	public void testeCarregaFonesUser () {
		UserPosDAO dao = new UserPosDAO();
		
		List<BeanUserFone> beanUserFones = dao.listaUserFone(4L);
		
		for (BeanUserFone beanUserFone : beanUserFones) {
			System.out.println(beanUserFone);
			System.out.println("--------------------------------------------------------------");
			
		}
	}
	
	@Test
	public void testeDeleteUserFone() {
		UserPosDAO dao = new UserPosDAO();
		dao.deleteFonesPorUser(17L);
		
	}

}
