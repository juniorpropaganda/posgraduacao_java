package pos_java_maven_hibernate;

import java.util.List;
import org.junit.Test;
import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = new UsuarioPessoa();

		pessoa.setIdade(30);
		pessoa.setLogin("jr");
		pessoa.setNome("rabuda");
		pessoa.setSenha("45465");
		pessoa.setSobrenome("pro");
		pessoa.setEmail("junior@gmail.com");

		daoGeneric.salvar(pessoa);
	}

	@Test
	public void testeBuscar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(1L);

		pessoa = daoGeneric.pesquisar(pessoa);

		System.out.println(pessoa);

	}

	// segundo metodo de busca
	@Test
	public void testeBuscar2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);

		System.out.println(pessoa);

	}

	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(1L, UsuarioPessoa.class);

		pessoa.setIdade(55);
		pessoa.setNome("nome atualizado  hibernate");
		pessoa.setLogin("rabuda");

		pessoa = daoGeneric.updateMerge(pessoa);

		System.out.println(pessoa);

	}

	@Test
	public void testeDelete() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);

		daoGeneric.deletarPorId(pessoa);

		System.out.println(pessoa);

	}

	

	
	@Test
	public void testeConsultar() {
	 DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
	 
	 
	 List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);
	 
	 for (UsuarioPessoa usuarioPessoa : list) {
		 
		 System.out.println(usuarioPessoa); //atalho sysout
		 System.out.println("---------------------------------------------");
	}
	 
	}	
	
	

	//Persistência em java
	//unidade 2
	//video PJ-UN02 - 13	
	
	
	@Test
	public void testeQueryList() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createQuery("from UsuarioPesssoa where nome = 'junior'").getResultList();
		
		
		for (UsuarioPessoa usuarioPessoa : list) {
			
			System.out.println(usuarioPessoa);
		}


	}	
	
	
	//Persistência em java
	//unidade 2
	//video PJ-UN02 - 14	

	@Test
	public void testeQueryListMaxResult() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPesssoa order by nome")
				.setMaxResults(3)
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			
			System.out.println(usuarioPessoa);
		}
	}
	
	
	@Test
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa where nome = :nome or sobrenome = :sobrenome")
				.setParameter("nome", "vanessa")
				.setParameter("sobrenome", "junior")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
		
	}
	
	
	@Test
	public void testeQuerySomaIdade() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		Long somaIdade = (Long) daoGeneric.getEntityManager() //esse aqui é para somar
				.createQuery("select sum(u.idade) from UsuarioPessoa u ").getSingleResult();
		
		System.out.println("Soma de todas as idades é --> " + somaIdade);
		System.out.println("----------------------------------------------------");	
		
		
		Double mediaIdade = (Double) daoGeneric.getEntityManager() //esse aqui é para media
				.createQuery("select avg(u.idade) from UsuarioPessoa u ").getSingleResult();
		
		System.out.println("A media de todas as idades é --> " + mediaIdade);
		System.out.println("----------------------------------------------------");	
	}
	
	@Test
	public void testeNameQuery1() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("consultaTodos.todos")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	
	@Test
	public void testeNameQuery2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createNamedQuery("UsuarioPessoa.buscaPorNome")
				.setParameter("nome", "asterio")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeGravaTelefone() {
		DaoGeneric  daoGeneric  = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(3L, UsuarioPessoa.class);
		
		TelefoneUser telefoneUser = new TelefoneUser();
		
		telefoneUser.setTipo("Casa");
		telefoneUser.setNumero("6565-995");
		telefoneUser.setUsuarioPessoa(pessoa);
		
		daoGeneric.salvar(telefoneUser);
	}
	
	
	@Test
	public void testeConsultaTelefones() {
		DaoGeneric  daoGeneric  = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(7L, UsuarioPessoa.class);
		
		for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getUsuarioPessoa().getNome());
			System.out.println("---------------------------------------------------------------------------------------");
			
		}
		

	}
	
}
	 
		 







		 

