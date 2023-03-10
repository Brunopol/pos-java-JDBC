package pos_java_jdbc.pos_java_jdbc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dao.UserPosDAO;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class AppTest {

	@Test
	public void testInsert() {

		UserPosDAO userPosDAO = new UserPosDAO();
		Userposjava userposjava = new Userposjava();
		userposjava.setNome("Elaine");
		userposjava.setEmail("elaine@gmail.com");
		userPosDAO.salvar(userposjava);

	}

	@SuppressWarnings("unused")
	@Test

	public void testPegarTodosNoBanco() {

		UserPosDAO userPosDAO = new UserPosDAO();

		List<Userposjava> list = new ArrayList<Userposjava>();
		list = userPosDAO.consultarRetornaLista();

		for (Userposjava userposjava : list) {

			System.out.println(userposjava);

		}

	}

	@Test

	public void testBuscarNoBanco() {

		UserPosDAO userPosDAO = new UserPosDAO();

		List<Userposjava> list = new ArrayList<Userposjava>();
		list = userPosDAO.buscar("sdghasdg");

		for (Userposjava userposjava : list) {

			System.out.println(userposjava);

		}

	}

	@Test

	public void testAtualizar() {

		UserPosDAO userPosDAO = new UserPosDAO();
		List<Userposjava> list = new ArrayList<Userposjava>();

		list = userPosDAO.buscar(1);

		list.get(0).setNome("Bruno");

		userPosDAO.atualizar(list.get(0));

	}

	@Test

	public void testDeletar() {

		UserPosDAO userPosDAO = new UserPosDAO();

		userPosDAO.deletar(6);

	}

	@Test

	public void testAddTelefone() {

		UserPosDAO userPosDAO = new UserPosDAO();
		
		Telefone telefone = new Telefone();
		
		telefone.setNumero("123513252456test");
		telefone.setTipo("casa");
		telefone.setUsuariopessoa(3L);
		
		userPosDAO.salvarTelefone(telefone);
		
		
	}
	
	@Test
	
	public void testRetornaUserFone() {
		
		UserPosDAO userPosDAO = new UserPosDAO();
		
		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();
		
		beanUserFones = userPosDAO.retornaUserTelefone(1L);
		
		for (BeanUserFone beanUserFone : beanUserFones) {
			
			System.out.println(beanUserFone);
			
		}
		
		
		
		
	}
	
	@Test
	public void testDeletarUserComTelefone() {
		
		UserPosDAO userPosDAO = new UserPosDAO();
		
		userPosDAO.deletarUserComTelefone(5L);
		
		
		
	}
	
	
	

}
