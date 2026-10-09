package exo1.q1.v2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * SOLUTION tests — prove the decoupled logger design works and is testable.
 */
public class TestLogger {

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    @Test
    public void computationReturnsSumWithMemoryLogger() {
        MemoryLogger logger = new MemoryLogger();
        LogicielDeCalcul logiciel = new LogicielDeCalcul(logger);
        // 0 + 1 + 2 + 3 + 4 + 5 = 15
        assertEquals(15.0, logiciel.calculSuperComplexe(6), 0.0);
        logiciel.terminer();
    }

    @Test
    public void memoryLoggerCapturesEveryStep() {
        MemoryLogger logger = new MemoryLogger();
        new LogicielDeCalcul(logger).calculSuperComplexe(2);
        List<String> messages = logger.getMessages();
        assertEquals(4, messages.size()); // start + 2 iterations + end
        assertEquals("start of computation", messages.get(0));
        assertEquals("Iteration 0", messages.get(1));
        assertEquals("Iteration 1", messages.get(2));
        assertEquals("end of computation", messages.get(3));
    }

    @Test
    public void nullLoggerDisablesLoggingWithoutChangingResults() {
        LogicielDeCalcul logiciel = new LogicielDeCalcul(new NullLogger());
        assertEquals(15.0, logiciel.calculSuperComplexe(6), 0.0);
        logiciel.terminer(); // must not throw
    }

    @Test
    public void fileLoggerWritesToFile() throws Exception {
        File f = tmp.newFile("test-log.txt");
        LogicielDeCalcul logiciel = new LogicielDeCalcul(new FileLogger(f));
        assertEquals(3.0, logiciel.calculSuperComplexe(3), 0.0); // 0 + 1 + 2
        logiciel.terminer();
        String content = new String(Files.readAllBytes(f.toPath()), StandardCharsets.UTF_8);
        assertTrue(content.contains("Iteration 0"));
        assertTrue(content.contains("Iteration 2"));
    }
}
