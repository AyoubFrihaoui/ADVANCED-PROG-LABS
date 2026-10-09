package exo1.q1.v1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;

public class FileLogger {
    private Writer writer;

    public FileLogger(File f) throws IOException {
        writer = new FileWriter(f);
    }

    public void log(String message) throws IOException {
        writer.write(LocalDateTime.now().toString());
        writer.write(" ");
        writer.write(message);
        writer.write('\n');
    }

    public void end() throws IOException {
            writer.close();    
    }
}
