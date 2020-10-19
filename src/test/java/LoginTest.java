

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;

public class LoginTest {

	static DataAccess sut=new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));;
	static BLFacadeImplementation testBL=new BLFacadeImplementation();

	private int nan = 12345678;
	private Integer nan2;

	@Test
	public void test1() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String pasahitza = "kaixo";
		
		try {
			Date jaioD = sdf.parse("21/07/1999");
			sut.pertsonaErregistratu("iz", "ab1", "ab2", "email", 10, jaioD, nan, "A", "kaixo", "kaixo");
			boolean login = sut.login(nan, pasahitza);
			assertTrue(login);
		}	
		catch(Exception e) {
			fail();
		}
		finally {
			//metodo hau sortu dut dataAccessen, testaren ondoren db-a hasierako egoeran
			//bezala uzteko
			sut.removePertsona(nan);
		}
	}
	
	@Test
	public void test2() {
		int nan2 = 1234567;
		String pasahitza = "kaixo";

		try {
			boolean login = sut.login(nan2, pasahitza);
			assertTrue(!login);
		}	
		catch(Exception e) {
			fail();
		}

	}
	
	@Test
	public void test3() {
		String pasahitza = "kaixo";
		int nan2 = 11111111;
		
		try {
			boolean login = sut.login(nan2, pasahitza);
			assertTrue(!login);
		}	
		catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void test4() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String pasahitza = "a";
		
		try {
			Date jaioD = sdf.parse("21/07/1999");
			sut.pertsonaErregistratu("iz", "ab1", "ab2", "email", 10, jaioD, nan, "A", "kaixo", "kaixo");
			boolean login = sut.login(nan, pasahitza);
			assertTrue(!login);
		}	
		catch(Exception e) {
			fail();
		}
		finally {
			sut.removePertsona(nan);
		}
	}
	
	@Test
	public void test5() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String pasahitza = null;
		
		try {
			Date jaioD = sdf.parse("21/07/1999");
			sut.pertsonaErregistratu("iz", "ab1", "ab2", "email", 10, jaioD, nan, "A", "kaixo", "kaixo");
			boolean login = sut.login(nan, pasahitza);
			assertTrue(!login);
		}	
		catch(Exception e) {
			fail();
		}
		finally {
			sut.removePertsona(nan);
		}
	}
}
