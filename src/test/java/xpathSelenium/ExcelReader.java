package xpathSelenium;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    public static void main(String[] args) throws Exception {
    	System.out.println(System.getProperty("user.dir"+""));
        FileInputStream fis = new FileInputStream("src\\test\\resources\\testdata\\testdata.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            for (Cell cell : row) {
                System.out.print(cell + " | ");
            }
            System.out.println();
        }

        workbook.close();
    }
}
