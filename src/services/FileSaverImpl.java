package services;

import repositories.StudentsDaoImpl;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class FileSaverImpl implements FileSaver {
    private String basicPath;

    public FileSaverImpl(String basicPath) {
        this.basicPath = basicPath;
    }

    @Override
    public Optional<String> saveFile(Part p, String directoryName) {
        if(p.getSize() != 0) {
            String resultFileAddress = basicPath + File.separator + directoryName;
            File file = new File(resultFileAddress);
            if (!file.exists()) file.mkdir();
            String[] splittedFileName = p.getSubmittedFileName().split("\\.");
            String fileName = Math.random() + "." + splittedFileName[splittedFileName.length - 1];
            String fullFileName = resultFileAddress + File.separator + fileName;
            try {
                p.write(fullFileName);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            return Optional.of(File.separator + directoryName + File.separator + fileName);
        }
        return Optional.empty();
    }

}
