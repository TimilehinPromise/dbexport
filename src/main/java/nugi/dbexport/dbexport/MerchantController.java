package nugi.dbexport.dbexport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/export/excel")
public class MerchantController {

    @Autowired
    private Mservice mservice;

    @GetMapping("/{merchantUUID}")
    public void exportToExcelById(HttpServletResponse response, @PathVariable("merchantUUID") String merchantUUID) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=merchants_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<MerchantCustomer> merchantCustomerList = mservice.findById(merchantUUID);

        ExcelExporter excelExporter = new ExcelExporter(merchantCustomerList);

        excelExporter.export(response);

    }

    @GetMapping()
    public void exportToExcelAll(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=merchants_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<MerchantCustomer> merchantCustomerList = mservice.findAll();

        ExcelExporter excelExporter = new ExcelExporter(merchantCustomerList);

        excelExporter.export(response);

    }
}