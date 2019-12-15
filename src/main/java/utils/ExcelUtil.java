package utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelUtil {
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private String excelPath;

    public boolean setExcel(String path) {
        if (workbook != null) {
            try {
                workbook.close();
            } catch (Exception e) {
                return false;
            }
        }

        File file = new File(path);

        try {
            FileInputStream stream = new FileInputStream(file);
            workbook = new XSSFWorkbook(stream);
            this.excelPath = path;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean setWorkSheet(int index) {
        try {
            this.sheet = workbook.getSheetAt(index);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getData(int row, int column) {
        try {
            XSSFRow xssfRow = this.sheet.getRow(row);
            XSSFCell xssfCell = xssfRow.getCell(column);
            return xssfCell.toString();
        } catch (NullPointerException e) {
            System.out.println("Neko polje je null");
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public boolean setData(int row, int column, String data) {
        try {
            XSSFRow xssfRow = this.sheet.getRow(row);

            if (xssfRow == null) {
                xssfRow = this.sheet.createRow(row);
            }
            XSSFCell xssfCell = xssfRow.getCell(column);
            if (xssfCell == null) {
                xssfCell = xssfRow.createCell(column);
            }

            xssfCell.setCellValue(data);

            FileOutputStream stream = new FileOutputStream(this.excelPath);
            workbook.write(stream);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getRowNumber() {
        try {
            return this.sheet.getLastRowNum() + 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public boolean closeExcel(){
        if (workbook != null){
            try {
                workbook.close();
                workbook = null;
                return true;
            }catch (Exception e){
                workbook = null;
                return false;
            }
        }
        return true;
    }
}