package exo1.without_builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.HashMap;

import org.junit.Test;

/**
 * SOLUTION tests (Q2) — prove the Main1 helper enforces the same rules
 * the builder will later centralize.
 */
public class Main1Test {

	@Test
	public void testValidRequestPasses() {
		HttpRequest req = new HttpRequest("POST", "/login", "h",
				new HashMap<>(), Collections.emptyMap(), "{}", true, false, 1000);
		Main1.valider(req); // must not throw
		assertEquals("h", req.getHost());
	}

	@Test
	public void testMissingHost() {
		HttpRequest req = new HttpRequest("GET", "/u", null,
				Collections.emptyMap(), Collections.emptyMap(), null, true, false, 1000);
		try {
			Main1.valider(req);
			fail("expected IllegalArgumentException for missing host");
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

	@Test
	public void testBodyForbiddenOnGet() {
		HttpRequest req = new HttpRequest("GET", "/u", "h",
				Collections.emptyMap(), Collections.emptyMap(), "body", true, false, 1000);
		try {
			Main1.valider(req);
			fail("expected IllegalArgumentException for body on GET");
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

	@Test
	public void testIncompatibleFlags() {
		HttpRequest req = new HttpRequest("POST", "/u", "h",
				Collections.emptyMap(), Collections.emptyMap(), null, true, true, 1000);
		try {
			Main1.valider(req);
			fail("expected IllegalArgumentException for keepAlive + followRedirects");
		} catch (IllegalArgumentException e) {
			// expected
		}
	}
}
