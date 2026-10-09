package exo1.q1.v2;

import java.io.File;

/**
 * SOLUTION — demo: same computation, three loggers, zero changes to
 * {@link LogicielDeCalcul}.
 */
public class Main {

    public static void main(String[] args) {
        // 1. In-memory logger: ideal for tests and demos.
        MemoryLogger memory = new MemoryLogger();
        LogicielDeCalcul logiciel = new LogicielDeCalcul(memory);
        System.out.println("result = " + logiciel.calculSuperComplexe(6));
        logiciel.terminer();
        System.out.println("captured messages: " + memory.getMessages());

        // 2. Null logger: logging disabled, computation untouched.
        LogicielDeCalcul silencieux = new LogicielDeCalcul(new NullLogger());
        System.out.println("result (no logs) = " + silencieux.calculSuperComplexe(6));
        silencieux.terminer();

        // 3. File logger: original behavior, chosen by the caller.
        LogicielDeCalcul fichier = new LogicielDeCalcul(new FileLogger(new File("monlog.txt")));
        fichier.calculSuperComplexe(6);
        fichier.terminer();
        System.out.println("wrote monlog.txt");
    }
}
