

import org.junit.Test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginTest2 {
	private int nan = 12345678;

	public int getNan() {
		return nan;
	}

	public void setNan(int nan) {
		this.nan = nan;
	}

	@Test
	public void test1() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String pasahitza = "kaixo";
		try {
			Date jaioD = sdf.parse("21/07/1999");
			LoginTest.sut.pertsonaErregistratu("iz", "ab1", "ab2", "email", 10, jaioD, nan, "A", "kaixo", "kaixo");
			boolean login = LoginTest.sut.login(nan, pasahitza);
			assertTrue(login);
		} catch (Exception e) {
			fail();
		} finally {
			LoginTest.testBL.removePertsona(nan);
		}
	}

	@Test
	public void test4() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String pasahitza = "a";
		try {
			Date jaioD = sdf.parse("21/07/1999");
			LoginTest.sut.pertsonaErregistratu("iz", "ab1", "ab2", "email", 10, jaioD, nan, "A", "kaixo", "kaixo");
			boolean login = LoginTest.sut.login(nan, pasahitza);
			assertTrue(!login);
		} catch (Exception e) {
			fail();
		} finally {
			LoginTest.testBL.removePertsona(nan);
		}
	}

	@Test
	public void test5() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String pasahitza = null;
		try {
			Date jaioD = sdf.parse("21/07/1999");
			LoginTest.sut.pertsonaErregistratu("iz", "ab1", "ab2", "email", 10, jaioD, nan, "A", "kaixo", "kaixo");
			boolean login = LoginTest.sut.login(nan, pasahitza);
			assertTrue(!login);
		} catch (Exception e) {
			fail();
		} finally {
			LoginTest.testBL.removePertsona(nan);
		}
	}
}