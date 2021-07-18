package nugi.dbexport.dbexport;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;



public class ExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<MerchantCustomer> merchantCustomerList;

    public ExcelExporter(List<MerchantCustomer> merchantCustomerList) {
        this.merchantCustomerList = merchantCustomerList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Merchants Transcation Info");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, " ID", style);
        createCell(row, 1, "merchant_ims_id", style);
        createCell(row, 2, "created_at", style);
        createCell(row, 3, "updated_at", style);
        createCell(row, 4, "trans_amount", style);
        createCell(row, 5, "order_amount", style);
        createCell(row, 6, "total_trans", style);
        createCell(row, 7, "total_order", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof LocalDateTime) {
            cell.setCellValue(String.valueOf((LocalDateTime) value));
        }
        else if (value instanceof BigDecimal) {
            cell.setCellValue(String.valueOf((BigDecimal) value));}

        else if (value instanceof Long) {
            cell.setCellValue((Long) value);}
        else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (MerchantCustomer merchantCustomer : merchantCustomerList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, merchantCustomer.getId(), style);
            createCell(row, columnCount++, merchantCustomer.getMerchantUuid(), style);
            createCell(row, columnCount++, merchantCustomer.getCreatedAt(), style);
            createCell(row, columnCount++, merchantCustomer.getUpdatedAt(), style);
            createCell(row, columnCount++, merchantCustomer.getTransAmount(), style);
            createCell(row, columnCount++, merchantCustomer.getOrderAmount(), style);
            createCell(row, columnCount++, merchantCustomer.getTotalTrans(), style);
            createCell(row, columnCount++, merchantCustomer.getTotalOrder(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}