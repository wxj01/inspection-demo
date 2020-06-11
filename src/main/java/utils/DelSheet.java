package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class DelSheet {
    public DelSheet() {
    }

    public static void deleteSheet(String targetFile, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(targetFile);
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            fileWrite(targetFile, wb);
            wb.removeSheetAt(wb.getSheetIndex(sheetName));
            fileWrite(targetFile, wb);
            fis.close();
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public static void fileWrite(String targetFile, HSSFWorkbook wb) throws Exception {
        FileOutputStream fileOut = new FileOutputStream(targetFile);
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }
}
