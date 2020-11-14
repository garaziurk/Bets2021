

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;
import test.businessLogic.TestFacadeImplementation;

public class LoginTest {

	public static DataAccess sut=new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));;
	public static TestFacadeImplementation testBL=new TestFacadeImplementation();
	
	private Integer nan2;

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
}
