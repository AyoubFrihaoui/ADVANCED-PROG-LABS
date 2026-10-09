package exo1.q1.v2;

/**
 * SOLUTION — the computation program, decoupled from any concrete logger.
 *
 * <p>Compare with {@code exo1.q1.v1.LogicielDeCalcul}: the logger is no
 * longer created inside the constructor (hard-coded {@code monlog.txt}),
 * it is INJECTED by the caller. The caller — not the business code —
 * decides which logger to use. No {@code throws IOException} anywhere:
 * logging can no longer break or pollute the computation API.
 */
public class LogicielDeCalcul {

    private final Logger logger;

    /** The caller provides the logger (dependency injection). */
    public LogicielDeCalcul(Logger logger) {
        this.logger = logger;
    }

    public double calculSuperComplexe(int n) {
        logger.log("start of computation");
        double resultat = 0.0;
        for (int i = 0; i < n; i++) {
            logger.log("Iteration " + i);
            resultat += i;
        }
        logger.log("end of computation");
        return resultat;
    }

    public void terminer() {
        logger.end();
    }
}
