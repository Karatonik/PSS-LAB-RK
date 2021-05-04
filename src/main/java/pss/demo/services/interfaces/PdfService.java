package pss.demo.services.interfaces;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface PdfService {

   void saveDelegationToPdf(int id) throws FileNotFoundException;
    void printToPdf(int id , HttpServletResponse response) throws IOException;
}
