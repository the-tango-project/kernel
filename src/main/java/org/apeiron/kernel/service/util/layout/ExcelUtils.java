package org.apeiron.kernel.service.util.layout;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelUtils {

    private static final int MAX_ROW_GENERATED = 100;

    private ExcelUtils() {}

    public static File getFileName(String nombre) {
        try {
            return File.createTempFile(nombre + LocalDateTime.now().toLocalDate(), ".xlsx");
        } catch (IOException e) {
            throw new UnsupportedOperationException("Crear Archivo temporal, permisos insuficientes", e);
        }
    }

    public static void configHeader(Workbook wb, XSSFSheet sheet, Column[] columns) {
        CellStyle style = defaultCellStyle(wb);
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i].getName());
            cell.setCellStyle(style);
            cell.setCellComment(createComment(wb, sheet, columns[i].getDesc()));
            sheet.autoSizeColumn(i);
        }
    }

    public static void addValidationInColumn(XSSFSheet sheet, String catalogName, int colIndex) {
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createFormulaListConstraint(catalogName);
        CellRangeAddressList addressList = new CellRangeAddressList(1, MAX_ROW_GENERATED, colIndex, colIndex);
        XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
        validation.setSuppressDropDownArrow(true);
        validation.setShowErrorBox(false);
        sheet.addValidationData(validation);
    }

    public static void createCatalogoRow(Workbook wb, XSSFSheet sheet, String[] row, int index, String catalogName) {
        int rowNumber = (index + 1);
        int rowLength = row.length;

        Row headerRow = sheet.createRow(index);
        for (int i = 0; i < rowLength; i++) {
            headerRow.createCell(i).setCellValue(row[i]);
        }
        if (rowLength > 0) {
            Name namedCel = wb.createName();
            namedCel.setNameName(catalogName);
            String reference = sheet.getSheetName() + "!$A$" + rowNumber + ":$" + columnNames(rowLength) + "$" + rowNumber;
            namedCel.setRefersToFormula(reference);
        }
    }

    public static CellStyle defaultCellStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.DARK_GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(defaultFontColor(wb));
        return style;
    }

    public static String columnNames(int rowLength) {
        StringBuilder sb = new StringBuilder();
        while (rowLength > 0) {
            int num = (rowLength - 1) % 26;
            char letter = (char) (num + 65);
            sb.append(letter);
            rowLength = (rowLength - 1) / 26;
        }
        return sb.toString();
    }

    public static Font defaultFontColor(Workbook wb) {
        Font font = wb.createFont();
        font.setBold(true);
        font.setColor(HSSFColorPredefined.WHITE.getIndex());
        return font;
    }

    public static Comment createComment(Workbook wb, XSSFSheet sheet, String text) {
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        CreationHelper factory = wb.getCreationHelper();
        ClientAnchor anchor = factory.createClientAnchor();
        anchor.setCol1(0);
        anchor.setCol2(1);
        anchor.setRow1(2);
        anchor.setRow2(3);
        Comment comment = drawing.createCellComment(anchor);
        RichTextString str = factory.createRichTextString(text);
        comment.setString(str);
        comment.setAuthor("Conahcyt");
        return comment;
    }
}
