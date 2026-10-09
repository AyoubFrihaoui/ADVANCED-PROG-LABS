package exo1.q1.v1;

import java.io.File;
import java.io.IOException;

public class LogicielDeCalcul {
    
    private FileLogger fileLogger;

    public LogicielDeCalcul() throws IOException {
        fileLogger = new FileLogger(new File("monlog.txt"));
    }

    public double calculSuperComplexe(int n) throws IOException {
        fileLogger.log("début du calcul");
        double resultat = 0.0;
        for (int i = 0; i < n; i++) {
            fileLogger.log("Itération "+i);  
            resultat += i;
        }
        fileLogger.log("fin du calcul");
        return resultat;
    }

    public void terminer() throws IOException {
        fileLogger.end();
    }
}
