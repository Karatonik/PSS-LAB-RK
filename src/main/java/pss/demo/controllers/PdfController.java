package pss.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pss.demo.services.PdfServiceImp;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/pdf")
public class PdfController {

    PdfServiceImp pdfServiceImp;


    public PdfController(PdfServiceImp pdfServiceImp) {
        this.pdfServiceImp = pdfServiceImp;
    }

    @GetMapping("/local/{delegationId}")
    public ResponseEntity<Void> saveDelegationToPdf(@PathVariable int delegationId) throws FileNotFoundException {
        return  pdfServiceImp.saveDelegationToPdf(delegationId);
    }

    @GetMapping("/{delegationId}")
    public ResponseEntity<Void> printToPdf(@PathVariable int delegationId , HttpServletResponse response) throws IOException {
     return  pdfServiceImp.printToPdf(delegationId,response);
    }

}
