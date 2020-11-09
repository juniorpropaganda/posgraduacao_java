package pos_java_spring_data;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pos_java_spring_data.dao.InterfaceSringDataUser;
import pos_java_spring_data.dao.InterfaceTelefone;
import pos_java_spring_data.model.Telefone;
import pos_java_spring_data.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTest {
	@Autowired
	private InterfaceSringDataUser interfaceSringDataUser;
	
	@Autowired
	private InterfaceTelefone InterfaceTelefone;
	
	@Test
	public void testeInsert() {
		System.out.println("iniciou spring com sucesso");
		
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setEmail("junior@gmail.com");
		usuarioSpringData.setIdade(39);
		usuarioSpringData.setLogin(" teste 123");
		usuarioSpringData.setNome("fodão da programação");
		usuarioSpringData.setSenha("123");
		
		interfaceSringDataUser.save(usuarioSpringData);
		
		System.out.println("Usuarios cadastrados -->" + interfaceSringDataUser.count());
	}
	
	@Test
	public void testeConsulta() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSringDataUser.findById(1L);
		
		System.out.println("Idade --> " + usuarioSpringData.get().getIdade());
		System.out.println("email--> " + usuarioSpringData.get().getEmail());
		System.out.println("Login --> " + usuarioSpringData.get().getLogin());
		System.out.println("Senha --> " + usuarioSpringData.get().getSenha());
		System.out.println("Nome --> " + usuarioSpringData.get().getNome());
		System.out.println("ID --> " + usuarioSpringData.get().getId());
		System.out.println("iniciou spring com sucesso");
		
		for (Telefone telefone : usuarioSpringData.get().getTelefones()) {
			System.out.println(telefone.getNumero());
			System.out.println(telefone.getTipo());
			System.out.println(telefone.getId());
			System.out.println(telefone.getUsuarioSpringData().getNome());
			System.out.println("--------------------------------------------------------------------");
		}
	}
	
	@Test
	public void testeConsultaTodos() {
		Iterable<UsuarioSpringData> lista = interfaceSringDataUser.findAll();
		
		for (UsuarioSpringData usuarioSpringData : lista) {
			
			System.out.println("Idade --> " + usuarioSpringData.getIdade());
			System.out.println("email--> " + usuarioSpringData.getEmail());
			System.out.println("Login --> " + usuarioSpringData.getLogin());
			System.out.println("Senha --> " + usuarioSpringData.getSenha());
			System.out.println("Nome --> " + usuarioSpringData.getNome());
			System.out.println("ID --> " + usuarioSpringData.getId());
			System.out.println("---------------------------------------------------------------");
			
		}
	}
	
	@Test
	public void testeUpdate() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSringDataUser.findById(3L);
			
		UsuarioSpringData data = usuarioSpringData.get();
		
		data.setIdade(23);
		data.setEmail("programador@gmail.com");
		data.setLogin("software");
		data.setSenha("kkk");
		interfaceSringDataUser.save(data);	
	}
	
	@Test
	public void testeDelete() { // essa é deletar por ID
		interfaceSringDataUser.deleteById(5L);
	}
	
	@Test
	public void testeDelete2() { // uma outra forma de deletar, essa é deletar por consulta
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSringDataUser.findById(4L);
		
		interfaceSringDataUser.delete(usuarioSpringData.get());
	}
	
	@Test
	public void testeConsultaNome() {
		List<UsuarioSpringData> list = interfaceSringDataUser.buscaPorNome("junior");
		
		for (UsuarioSpringData usuarioSpringData : list) {
			
			System.out.println("Idade --> " + usuarioSpringData.getIdade());
			System.out.println("email--> " + usuarioSpringData.getEmail());
			System.out.println("Login --> " + usuarioSpringData.getLogin());
			System.out.println("Senha --> " + usuarioSpringData.getSenha());
			System.out.println("Nome --> " + usuarioSpringData.getNome());
			System.out.println("ID --> " + usuarioSpringData.getId());
			System.out.println("---------------------------------------------------------------");
		}
	}
	
	@Test
	public void testeConsultaNomeParam() {
		UsuarioSpringData usuarioSpringData = interfaceSringDataUser.buscaPorNomeParam("junior pro");
		
			
			System.out.println("Idade --> " + usuarioSpringData.getIdade());
			System.out.println("email--> " + usuarioSpringData.getEmail());
			System.out.println("Login --> " + usuarioSpringData.getLogin());
			System.out.println("Senha --> " + usuarioSpringData.getSenha());
			System.out.println("Nome --> " + usuarioSpringData.getNome());
			System.out.println("ID --> " + usuarioSpringData.getId());
			System.out.println("---------------------------------------------------------------");

	}
	@Test
	public void testeDeletePornome() {
		interfaceSringDataUser.deletePorNome("fodão da programação");
	}
	
	@Test
	public void testeUpdateEmailPorNome () {
		interfaceSringDataUser.updateEmailPorNome("emailspringdata@gmail.com", "junior pro");
	}
	
	@Test
	public void testeInsertTelefone() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSringDataUser.findById(2L);
		
		Telefone telefone = new Telefone();
		telefone.setTipo("casa");
		telefone.setNumero("7894646465");
		telefone.setUsuarioSpringData(usuarioSpringData.get());
		
		InterfaceTelefone.save(telefone);
	}
}
