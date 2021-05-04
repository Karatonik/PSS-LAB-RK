package pss.demo.services;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pss.demo.models.Delegation;
import pss.demo.repositorys.DelegationRepository;
import pss.demo.services.interfaces.PdfService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Optional;

@Service
public class PdfServiceImp implements PdfService {

    DelegationRepository delegationRepository;
    @Autowired
    public PdfServiceImp(DelegationRepository delegationRepository) {
        this.delegationRepository = delegationRepository;
    }


    @Override
    public void saveDelegationToPdf(int id) throws FileNotFoundException {
        Optional<Delegation> delegationOptional = delegationRepository.findById(id);
        if(delegationOptional.isPresent()){
            Delegation delegation = delegationOptional.get();

            String headerValue =delegation.getDelegationId()+"Delegation.pdf";
            Document document = new Document(PageSize.A4);
            File file = new File("./PDF/"+headerValue);
            OutputStream outputStream = new FileOutputStream(file);

            PdfWriter.getInstance(document,outputStream);

            document.open();

            document.add(new Paragraph("Delegacja nr."+delegation.getDelegationId()));

            document.add(new Paragraph("User"));
            document.add(new Paragraph("Name :"+delegation.getUser().getName()));
            document.add(new Paragraph("Last name :"+delegation.getUser().getLastName()));
            document.add(new Paragraph("Email :"+delegation.getUser().getEmail()));
            document.add(new Paragraph("Company address :"+delegation.getUser().getCompanyAddress()));
            document.add(new Paragraph("Company Nip :"+delegation.getUser().getCompanyNip()));

            document.add(new Paragraph("---------------------------------------------------------------------"));

            document.add(new Paragraph("Description :"+delegation.getDescription()));
            document.add(new Paragraph("Data time start :"+delegation.getDateTimeStart()));
            document.add(new Paragraph("Data time stop :"+delegation.getDateTimeStop()));
            document.add(new Paragraph("Travel diet amount :"+delegation.getTravelDietAmount()));
            document.add(new Paragraph("break fast number :"+delegation.getBreakfastNumber()));
            document.add(new Paragraph("dinner number :"+delegation.getDinnerNumber()));
            document.add(new Paragraph("supper number :"+delegation.getSupperNumber()));
            document.add(new Paragraph("Transport type :"+delegation.getTransportType()));
            document.add(new Paragraph("Ticket price :"+delegation.getTicketPrice()+"zł"));
            document.add(new Paragraph("Auto capacity:"+delegation.getAutoCapacity()));
            document.add(new Paragraph("km: "+delegation.getKm()+"km"));
            document.add(new Paragraph("Accomodation price:"+delegation.getAccomodationPrice()+"zł"));
            document.add(new Paragraph("Other tickets price: "+delegation.getOtherTicketsPrice()+"zł"));
            document.add(new Paragraph("Other out lay Desc: "+delegation.getOtherOutlayDesc()));
            document.add(new Paragraph("Other out lay price: "+delegation.getOtherOutlayPrice()+"zł"));
            document.close();
        }


    }

    @Override
    public void printToPdf(int id, HttpServletResponse response) throws IOException {
       Optional<Delegation> delegationOptional = delegationRepository.findById(id);
        if(delegationOptional.isPresent()){
            Delegation delegation = delegationOptional.get();



            response.setContentType("application/pdf");
            String headerKey = "Content-Disposition";
            String headerValue ="attachment; filename="+delegation.getDelegationId()+"Delegation.pdf";
            response.setHeader(headerKey,headerValue);

            Document document = new Document(PageSize.A4);

            PdfWriter.getInstance(document,response.getOutputStream());

            document.open();

            document.add(new Paragraph("Delegacja nr."+delegation.getDelegationId()));


            document.add(new Paragraph("User"));
            document.add(new Paragraph("Name :"+delegation.getUser().getName()));
            document.add(new Paragraph("Last name :"+delegation.getUser().getLastName()));
            document.add(new Paragraph("Email :"+delegation.getUser().getEmail()));
            document.add(new Paragraph("Company address :"+delegation.getUser().getCompanyAddress()));
            document.add(new Paragraph("Company Nip :"+delegation.getUser().getCompanyNip()));

            document.add(new Paragraph("---------------------------------------------------------------------"));

            document.add(new Paragraph("Description :"+delegation.getDescription()));
            document.add(new Paragraph("Data time start :"+delegation.getDateTimeStart()));
            document.add(new Paragraph("Data time stop :"+delegation.getDateTimeStop()));
            document.add(new Paragraph("Travel diet amount :"+delegation.getTravelDietAmount()));
            document.add(new Paragraph("break fast number :"+delegation.getBreakfastNumber()));
            document.add(new Paragraph("dinner number :"+delegation.getDinnerNumber()));
            document.add(new Paragraph("supper number :"+delegation.getSupperNumber()));
            document.add(new Paragraph("Transport type :"+delegation.getTransportType()));
            document.add(new Paragraph("Ticket price :"+delegation.getTicketPrice()+"zł"));
            document.add(new Paragraph("Auto capacity:"+delegation.getAutoCapacity()));
            document.add(new Paragraph("km: "+delegation.getKm()+"km"));
            document.add(new Paragraph("Accomodation price:"+delegation.getAccomodationPrice()+"zł"));
            document.add(new Paragraph("Other tickets price: "+delegation.getOtherTicketsPrice()+"zł"));
            document.add(new Paragraph("Other out lay Desc: "+delegation.getOtherOutlayDesc()));
            document.add(new Paragraph("Other out lay price: "+delegation.getOtherOutlayPrice()+"zł"));
            document.close();

        }

    }
}
