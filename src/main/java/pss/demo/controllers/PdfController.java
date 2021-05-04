package pss.demo.controllers;

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
    public void saveDelegationToPdf(@PathVariable int delegationId) throws FileNotFoundException {
        pdfServiceImp.saveDelegationToPdf(delegationId);
    }

    @GetMapping("/{delegationId}")
    public void printToPdf(@PathVariable int delegationId , HttpServletResponse response) throws IOException {
        pdfServiceImp.printToPdf(delegationId,response);
    }

}
