package services;

import javax.servlet.http.Part;
import java.util.Optional;

public interface FileSaver {
        Optional<String> saveFile(Part p, String directoryName);
}
