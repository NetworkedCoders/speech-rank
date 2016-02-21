import org.junit.Test;

import com.github.peggybrown.speechrank.Importer;

public class ImporterTest {

	@Test
	public void testImport() {
		new Importer().importConfitura2015().forEach(System.out::println);
	}

}
