package com.infosys.demo.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.infosys.demo.entity.Position;
import com.infosys.demo.entity.Transaction;
import com.infosys.demo.service.ExcelService;
import org.springframework.util.ResourceUtils;

@Service("excelService")
public class ExcelServiceImpl implements ExcelService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<Transaction> queryTransactions() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        Workbook wb = null;
        InputStream in = null;
        try {
            File excelFile = ResourceUtils.getFile(ResourceUtils.WAR_URL_PREFIX + "excel/PositionMGNT.xlsx");
            //File excelFile = new File(this.getClass().getResource("/excel/PositionMGNT.xlsx").getFile());
            in = new FileInputStream(excelFile);
            //读取excel模板
            wb = new XSSFWorkbook(in);
            Sheet sheet = wb.getSheetAt(0);

            // 获取第一行数据
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            if (null == firstRow) {
                logger.warn("解析Excel失败，在第一行没有读取到任何数据！");
            }
            // 解析每一行的数据，构造数据对象
            int rowStart = firstRowNum + 1;
            int rowEnd = sheet.getPhysicalNumberOfRows();
            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);

                if (null == row) {
                    continue;
                }

                Transaction resultData = convertRowToTransaction(row);
                if (null == resultData) {
                    logger.warn("第 " + row.getRowNum() + "行数据不合法，已忽略！");
                    continue;
                }
                transactions.add(resultData);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != wb) {
                    wb.close();
                }
                if (null != in) {
                    in.close();
                }
            } catch (Exception e) {
                logger.warn("关闭数据流出错！错误信息：" + e.getMessage());
                return null;
            }
        }
        return transactions;


    }

    @Override
    public List<Position> queryPositions() {
        List<Position> positions = new ArrayList<Position>();
        Workbook wb = null;
        InputStream in = null;
        try {
            File excelFile = ResourceUtils.getFile(ResourceUtils.WAR_URL_PREFIX + "excel/PositionMGNT.xlsx");
            //File excelFile = new File(this.getClass().getResource("/excel/PositionMGNT.xlsx").getFile());
            
            in = new FileInputStream(excelFile);
            //读取excel模板
            wb = new XSSFWorkbook(in);
            Sheet sheet = wb.getSheetAt(1);
            // 获取第一行数据
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            if (null == firstRow) {
                logger.warn("解析Excel失败，在第一行没有读取到任何数据！");
            }
            // 解析每一行的数据，构造数据对象
            int rowStart = firstRowNum + 1;
            int rowEnd = sheet.getPhysicalNumberOfRows();
            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (null == row) {
                    continue;
                }
                Position resultData = convertRowToPosition(row);
                if (null == resultData) {
                    logger.warn("第 " + row.getRowNum() + "行数据不合法，已忽略！");
                    continue;
                }
                positions.add(resultData);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != wb) {
                    wb.close();
                }
                if (null != in) {
                    in.close();
                }
            } catch (Exception e) {
                logger.warn("关闭数据流出错！错误信息：" + e.getMessage());
                return null;
            }
        }


        return positions;
    }

    @Override
    public void updateTransactionSheet(List<Transaction> transactions) {
        Workbook wb = null;
        InputStream in = null;
        OutputStream out = null;
        try {
            //File excelFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "excel/PositionMGNT.xlsx");
            File excelFile = new File(this.getClass().getResource("/excel/PositionMGNT.xlsx").getFile());
            in = new FileInputStream(excelFile);
            wb = new XSSFWorkbook(in);
            Sheet sheet = wb.getSheetAt(0);
            Transaction t = null;
            Cell cell = null;
            Row row=null;
            for (int i = 0; i < transactions.size(); i++) {
                t=transactions.get(i);
                row=sheet.createRow(i+1);

                cell=row.createCell(0);
                cell.setCellValue(t.getTransactionID());

                cell = row.createCell(1);
                cell.setCellValue(t.getTradeID());

                cell = row.createCell(2);
                cell.setCellValue(t.getVersion());

                cell = row.createCell(3);
                cell.setCellValue(t.getSecurityCode());

                cell = row.createCell(4);
                cell.setCellValue(t.getQuantity());

                cell = row.createCell(5);
                cell.setCellValue(t.getInsert_update_cancel());

                cell = row.createCell(6);

                cell.setCellValue(t.getBuy_sell());
            }
            out = new FileOutputStream(excelFile);
            sheet.getWorkbook().write(out);
            //wb.write(out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != wb) {
                    wb.close();
                }
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            } catch (Exception e) {
                logger.warn(e.getMessage());
            }

        }

    }

    @Override
    public void updatePositionSheet(List<Position> positions) {
        Workbook wb = null;
        InputStream in = null;
        OutputStream out = null;
        try {
            //File excelFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "excel/PositionMGNT.xlsx");
            File excelFile = new File(this.getClass().getResource("/excel/PositionMGNT.xlsx").getFile());
            in = new FileInputStream(excelFile);
            wb = new XSSFWorkbook(in);
            Sheet sheet = wb.getSheetAt(1);
            Position p = null;
            Cell cell = null;
            Row row=null;
            for (int i = 0; i < positions.size(); i++) {
                p=positions.get(i);
                row=sheet.createRow(i+1);

                cell = row.createCell(0);
                cell.setCellValue(p.getSecurityCode());

                cell = row.createCell(1);
                cell.setCellValue(p.getQuantity());

            }
            
            out = new FileOutputStream(excelFile);
            sheet.getWorkbook().write(out);
            //wb.write(out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != wb) {
                    wb.close();
                }
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            } catch (Exception e) {
                logger.warn(e.getMessage());
            }
        }
    }


    private Transaction convertRowToTransaction(Row row) {
        Transaction transaction = new Transaction();
        Cell cell = null;
        int cellNum = 0;
        cell = row.getCell(cellNum++);
        String transactionID = getCellFormatValue(cell);
        transaction.setTransactionID(Integer.valueOf(transactionID));

        cell = row.getCell(cellNum++);
        String radeID = getCellFormatValue(cell);
        transaction.setTradeID(Integer.valueOf(radeID));

        cell = row.getCell(cellNum++);
        String version = getCellFormatValue(cell);
        transaction.setVersion(Integer.valueOf(version));

        cell = row.getCell(cellNum++);
        String securityCode = getCellFormatValue(cell);
        transaction.setSecurityCode(securityCode);

        cell = row.getCell(cellNum++);
        String quantity = getCellFormatValue(cell);
        transaction.setQuantity(Integer.valueOf(quantity));


        cell = row.getCell(cellNum++);
        String iuc = getCellFormatValue(cell);
        transaction.setInsert_update_cancel(iuc);

        cell = row.getCell(cellNum++);
        String bs = getCellFormatValue(cell);
        transaction.setBuy_sell(bs);

        return transaction;
    }

    private Position convertRowToPosition(Row row) {
        Position p = new Position();
        Cell cell = null;
        int cellNum = 0;

        cell = row.getCell(cellNum++);
        String securityCode = getCellFormatValue(cell);
        p.setSecurityCode(securityCode);

        cell = row.getCell(cellNum++);
        String quantity = getCellFormatValue(cell);
        p.setQuantity(Integer.valueOf(quantity));

        return p;
    }

    private Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString) || ".XLS".equals(extString)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString) || (".XLSX".equals(extString))) {
                wb = new XSSFWorkbook(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }

    private String getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            // 判断cell类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_BLANK: {
                    cellValue = "";
                    break;
                }
                case Cell.CELL_TYPE_BOOLEAN: {
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                }
                case Cell.CELL_TYPE_ERROR: {
                    cellValue = String.valueOf(cell.getErrorCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA: {
                    try {
                        cellValue = cell.getStringCellValue();
                    } catch (IllegalStateException e) {
                        try {
                            if (DateUtil.isCellDateFormatted(cell)) {
                                cellValue = cell.getDateCellValue();
                            } else {
                                DecimalFormat df = new DecimalFormat("##.####");
                                cellValue = df.format(cell.getNumericCellValue());
                            }
                        } catch (IllegalStateException exception) {
                            cellValue = "FORMULA_ERROR:" + e.getMessage();
                        }
                    }
                    break;
                }
                case Cell.CELL_TYPE_NUMERIC: {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = cell.getDateCellValue();
                    } else {
                        DecimalFormat df = new DecimalFormat("##.####");
                        cellValue = df.format(cell.getNumericCellValue());
                        // cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = cell.getStringCellValue();
            }
        } else {
            cellValue = "Null";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (cellValue instanceof Date) {
            cellValue = dateFormat.format((Date) cellValue);
        }
        return cellValue.toString();
    }

    public static void main(String[] args) throws Exception {
        String filePath = "/Users/dean/Documents/ALDI/bi/git/PositionManagement/src/main/resources/excel/PositionMGNT.xlsx";

        ExcelServiceImpl util = new ExcelServiceImpl();
        Workbook wb = util.readExcel(filePath);


        List<Transaction> transactions = util.queryTransactions();

        for (Transaction t : transactions) {
            System.out.println(t);
        }


    }
}
