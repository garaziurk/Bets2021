

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;

@RunWith(MockitoJUnitRunner.class)
public class LoginMockFacade {
	DataAccess dataAccess=Mockito.mock(DataAccess.class);
	
	@InjectMocks
	 BLFacade sut=new BLFacadeImplementation(dataAccess);

	private Integer nan;
	
	@Test
	public void test1() {
		int nan = 12345678;
		String pasahitza = "kaixo";
		
		try {
			Mockito.doReturn(true).when(dataAccess).login(Mockito.anyInt(), Mockito.anyString());
			
			boolean login = sut.login(nan, pasahitza);
			assertTrue(login);
		}
		catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void test2() {
		int nan = 1234567;
		String pasahitza = "kaixo";
		
		try {
			Mockito.doReturn(false).when(dataAccess).login(Mockito.anyInt(), Mockito.anyString());
			
			boolean login = sut.login(nan, pasahitza);
			assertTrue(!login);
		}
		catch (Exception e) {
			fail();
		}
	}

	@Test
	public void test3() {
		nan = 11111111;
		String pasahitza = "kaixo";
		
		try {
			Mockito.doReturn(false).when(dataAccess).login(Mockito.anyInt(), Mockito.anyString());
			
			boolean login = sut.login(nan, pasahitza);
			assertTrue(!login);
		}
		catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void test4() {
		nan = 12345678;
		String pasahitza = "a";
		
		try {
			Mockito.doReturn(false).when(dataAccess).login(Mockito.anyInt(), Mockito.anyString());
			
			boolean login = sut.login(nan, pasahitza);
			assertTrue(!login);
		}
		catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void test5() {
		nan = 12345678;
		String pasahitza = null;
		
		try {
			Mockito.doReturn(false).when(dataAccess).login(Mockito.anyInt(), Mockito.anyString());
			
			boolean login = sut.login(nan, pasahitza);
			assertTrue(!login);
		}
		catch (Exception e) {
			fail();
		}
	}
}
