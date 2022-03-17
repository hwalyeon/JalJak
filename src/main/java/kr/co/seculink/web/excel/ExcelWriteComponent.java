package kr.co.seculink.web.excel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriteComponent {

    private Workbook workbook;
    private Map<String, Object> model;
    private HttpServletResponse response;

    public ExcelWriteComponent(Workbook workbook, Map<String, Object> model, HttpServletResponse response) {
        this.workbook = workbook;
        this.model = model;
        this.response = response;
    }

    public void create() {
        setFileName(response, mapToFileName());
        Sheet sheet = workbook.createSheet();
        createHead(sheet, mapToHeadList());

        if ( model.get(ExcelConstant.CELL_TYPE) != null ) {
            createBody(sheet, mapToCellTypeList(), mapToBodyList());
        } else {
            createBody(sheet, null, mapToBodyList());
        }
    }

    private String mapToFileName() {
        return (String) model.get(ExcelConstant.FILE_NAME);
    }

    private List<String> mapToHeadList() {
        return (List<String>) model.get(ExcelConstant.HEAD);
    }

    private List<CellType> mapToCellTypeList() {
        return (List<CellType>) model.get(ExcelConstant.CELL_TYPE);
    }

    private List<List<String>> mapToBodyList() {
        return (List<List<String>>) model.get(ExcelConstant.BODY);
    }

    private void setFileName(HttpServletResponse response, String fileName) {
        response.setHeader("Content-Disposition", "attachment; filename=\"" + getFileExtension(fileName) + "\"");
    }

    private String getFileExtension(String fileName) {
        if (workbook instanceof XSSFWorkbook) {
            fileName += ".xlsx";
        }
        if (workbook instanceof SXSSFWorkbook) {
            fileName += ".xlsx";
        }
        if (workbook instanceof HSSFWorkbook) {
            fileName += ".xls";
        }

        return fileName;
    }

    private void createHead(Sheet sheet, List<String> headList) {
        createRow(sheet, headList, 0);
    }

    private void createBody(Sheet sheet, List<CellType> cellTypeList, List<List<String>> bodyList) {

        int rowSize = bodyList.size();
        for (int i = 0; i < rowSize; i++) {
            createRow(sheet, cellTypeList, bodyList.get(i), i + 1);
        }
    }

    private void mergeRow(Sheet sheet, List<List<String>> bodyList) {

        List<String> ordList = bodyList.stream().map(x -> x.get(0)).collect(Collectors.toList());

        for(int index = 0 ; index<ordList.size(); index++) {
            String ordNo = ordList.get(index);
            int ordLastIndex = ordList.lastIndexOf(ordNo);

            if(index != ordLastIndex) {
                int strIndex = index+2;
                for ( int i = strIndex ; i <= ordLastIndex + 1 ; i++ )
                {
                    Row row = sheet.getRow(i);
                    row.createCell(26, CellType.NUMERIC).setCellValue(0);
                }
                sheet.addMergedRegion(CellRangeAddress.valueOf("AA"+strIndex+":"+"AA"+(ordLastIndex+2)));
                index = ordLastIndex;
            }
        }
    }

    private void createRow(Sheet sheet, List<String> cellList, int rowNum) {
        int size = cellList.size();
        Row row = sheet.createRow(rowNum);
        sheet.autoSizeColumn(rowNum);
        //sheet.setColumnWidth(rowNum, sheet.getColumnWidth(rowNum) + 500);
        for (int i = 0; i < size; i++) {
            row.createCell(i).setCellValue(cellList.get(i));
            if(rowNum == 0) {
                row.getCell(i).setCellStyle(headCellStyle(workbook));
            }/* else {
                if(rowNum % 2 == 0) {
                    row.getCell(i).setCellStyle(cellStyle(workbook));
                }
            }*/
        }

    }

    private void createRow(Sheet sheet, List<CellType> cellTypeList, List<String> cellList, int rowNum) {
        int size = cellList.size();
        Row row = sheet.createRow(rowNum);
        sheet.autoSizeColumn(rowNum);

        CellType cellType = CellType.STRING;

        for (int i = 0; i < size; i++) {
            if ( cellTypeList != null ) {
                cellType = cellTypeList.get(i);
            }

            if ( cellType == CellType.NUMERIC ) {
                if ( cellList.get(i) != null && !"".equals(cellList.get(i)) && !cellList.get(i).isEmpty() ) {
                    try {
                        BigDecimal values = new BigDecimal(cellList.get(i));
                        row.createCell(i, cellType).setCellValue(values.doubleValue());
                    } catch ( NumberFormatException e ) {
                        row.createCell(i, CellType.STRING).setCellValue(cellList.get(i));
                    }
                } else {
                    row.createCell(i, CellType.STRING).setCellValue(cellList.get(i));
                }
            } else {
                row.createCell(i, cellType).setCellValue(cellList.get(i));
            }
            if ( rowNum == 0 ) {
                row.getCell(i).setCellStyle(headCellStyle(workbook));
            }
        }

    }

    /**
     * Header 용 Style
     * @param wb
     * @return
     */
    public CellStyle headCellStyle(Workbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        //cellStyle.setFillForegroundColor((short) 12);
        //cellStyle.setShrinkToFit(true);
        //cellStyle.setWrapText(true);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(234, 160, 156)).getIndex());
        cellStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        // font.setFontHeightInPoints((short) 12);/
        font.setColor(IndexedColors.WHITE.index);
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 컨텐츠 셀 스타일
     * @param wb
     * @return
     */
    public CellStyle cellStyle(Workbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        //cellStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.index);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cellStyle;
    }

    private CellStyle mergeStyle(Workbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }

    private CellStyle createCellStyle() {
        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.index);
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREEN.index);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLUE.index);
        style.setBorderTop(BorderStyle.THIN);
        // style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM_DASHED);
        style.setTopBorderColor(IndexedColors.BLACK.index);
        // style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
        return style;
    }

}
