package pss.demo.services.interfaces;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface PdfService {

    ResponseEntity<Void> saveDelegationToPdf(int id) throws FileNotFoundException;
    ResponseEntity<Void> printToPdf(int id , HttpServletResponse response) throws IOException;
}
