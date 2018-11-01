
package com.custom.logika.htro.dealersapp.controller.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataFields;

public class ExportDataUtil {

	// create workbook cu raport 22 coloane
	public static XSSFWorkbook exportToExcel1(List<List<Object>> ItemsList, List<String> tableHeaders) {

		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("HTRO Custom Report1");

		// Create a Font for styling header cells

		XSSFFont headerFont = workbook.createFont();
		headerFont.setFontName("Calibri");
		headerFont.setFontHeightInPoints((short) 10);
		headerFont.setColor(IndexedColors.BLACK.index);

		DataFormat format = workbook.createDataFormat();
		XSSFCellStyle nrStyle = workbook.createCellStyle();
		nrStyle.setDataFormat(format.getFormat("0"));

		// Cell Style for formatting Date
		XSSFCellStyle dateCellStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

		// Create a CellStyle with the font
		XSSFCellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// create first row with table headers
		int rownum = 0;
		XSSFRow row = sheet.createRow(rownum);

		for (int x = 0; x < tableHeaders.size(); x++) {
			XSSFCell cell = row.createCell(x);
			cell.setCellValue(tableHeaders.get(x));
			cell.setCellStyle(headerCellStyle);
		}

		// Adding the rest of the data.
		System.out.println("ExportRaport1 - ItemsList.size() = " + ItemsList.size());
		for (int index = 0; index < ItemsList.size(); index++) {
			row = sheet.createRow(index + 1);
			List<Object> temp = ItemsList.get(index);
			for (int colIdx = 0; colIdx < temp.size(); colIdx++) {
				XSSFCell cell = row.createCell(colIdx);
				if (temp.get(colIdx) instanceof Date) {
					cell.setCellValue((Date) temp.get(colIdx));
					cell.setCellStyle(dateCellStyle);
				}
				else if (temp.get(colIdx) instanceof String) {
					cell.setCellValue(temp.get(colIdx).toString());
				}
				else {
					cell.setCellValue(temp.get(colIdx) == null ? "" : temp.get(colIdx).toString());
					cell.setCellStyle(nrStyle);
				}
			}
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < tableHeaders.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		// Freze pane (columns 1 - 11 + row 1 - frezed)
		sheet.createFreezePane(11, 1);

		// Filter columns
		CellRangeAddress cellRangeAddr = new CellRangeAddress(0, ItemsList.size(), 0, tableHeaders.size());
		sheet.setAutoFilter(cellRangeAddr);

		return workbook;
	}

	// create workbook cu raport searched + rezervari total
	public static XSSFWorkbook exportToExcel2(List<List<Object>> ItemsList, List<String> tableHeaders) {

		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("HTRO Report Data");

		// Create a Font for styling header cells
		XSSFFont headerFont = workbook.createFont();
		headerFont.setFontName("Calibri");
		headerFont.setFontHeightInPoints((short) 10);
		// headerFont.setColor(IndexedColors.BLACK.index);

		DataFormat format = workbook.createDataFormat();
		XSSFCellStyle nrStyle = workbook.createCellStyle();
		nrStyle.setDataFormat(format.getFormat("0"));

		// Cell Style for formatting Date
		XSSFCellStyle dateCellStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

		// Create a CellStyle with the font
		XSSFCellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// create first row with table headers
		int rownum = 0;
		XSSFRow row = sheet.createRow(rownum);

		for (int x = 0; x < tableHeaders.size(); x++) {
			XSSFCell cell = row.createCell(x);
			cell.setCellValue(tableHeaders.get(x));
			cell.setCellStyle(headerCellStyle);
		}

		// Adding the rest of the data.
		System.out.println("exportToExcel2 - ItemsList.size() = " + ItemsList.size());
		for (int index = 0; index < ItemsList.size(); index++) {
			row = sheet.createRow(index + 1);
			List<Object> temp = ItemsList.get(index);
			// System.out.println("ExportRaport1 - temp.size() = "+temp.size());
			for (int colIdx = 0; colIdx < temp.size(); colIdx++) {
				XSSFCell cell = row.createCell(colIdx);
				if (temp.get(colIdx) instanceof Date) {
					cell.setCellValue((Date) temp.get(colIdx));
					cell.setCellStyle(dateCellStyle);
				}
				else if (temp.get(colIdx) instanceof String) {
					cell.setCellValue(temp.get(colIdx).toString());
				}
				else {
					cell.setCellValue(temp.get(colIdx) == null ? "" : temp.get(colIdx).toString());
					cell.setCellStyle(nrStyle);
				}
			}
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < tableHeaders.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		return workbook;
	}

	// export raport searched + total rezervari / tip / dealer cu pivot
	public static XSSFWorkbook createPivotSearchedAndReserved(List<List<Object>> ItemsList, List<String> tableHeaders) {

		// Blank workbook
		XSSFWorkbook my_xlsx_workbook = exportToExcel2(ItemsList, tableHeaders);

		/* Read Data to be Pivoted - we have only one worksheet */
		XSSFSheet sheet = my_xlsx_workbook.getSheetAt(0);

		int firstrow = sheet.getFirstRowNum();
		int firstcol = sheet.getRow(firstrow).getFirstCellNum();
		int lastrow = sheet.getLastRowNum() - 1;
		int lastcol = sheet.getRow(lastrow).getLastCellNum() - 1;

		// System.out.println("createPivotSearchedAndReserved - firstrow = " +
		// firstrow + " firstcol = " + firstcol + " lastrow = " + lastrow +"
		// lastcol = " + lastcol);

		/* Get the reference for Pivot Data */

		CellReference firstColCell = new CellReference(firstrow, firstcol);
		CellReference lastColCell = new CellReference(lastrow, lastcol);

		System.out.println("createPivotSearchedAndReserved - firstColCell = " + firstColCell.formatAsString() +
			" lastColCell = " + lastColCell.formatAsString());

		AreaReference a = new AreaReference(firstColCell, lastColCell, SpreadsheetVersion.EXCEL2007);

		System.out.println("createPivotSearchedAndReserved - AreaReference = " + a.formatAsString());

		/* Find out where the Pivot Table needs to be placed */
		CellReference b = new CellReference("B2");

		// System.out.println("createPivotSearchedAndReserved - CellReference =
		// " + b.formatAsString());

		/* Add a new sheet to create Pivot Table */
		XSSFSheet pivot_sheet = null;

		if (my_xlsx_workbook.getSheet("Raport_Interogari_Rezervari") != null) {
			my_xlsx_workbook.removeSheetAt(my_xlsx_workbook.getSheetIndex("Raport_Interogari_Rezervari"));
		}

		pivot_sheet = my_xlsx_workbook.createSheet("Raport_Interogari_Rezervari");

		/* Create Pivot Table on a separate worksheet */
		XSSFPivotTable pivotTable = pivot_sheet.createPivotTable(a, b, sheet);

		/* customizeaza pivotul */
		pivotTable.addRowLabel(2);// model short desc
		pivotTable.addRowLabel(1);// model desc

		int colDescr = 0; // perioada
		int colDescr2 = 3; // tip

		pivotTable.addColLabel(colDescr);
		pivotTable.addColLabel(colDescr2);

		// adauga filtru dupa dealer
		pivotTable.addReportFilter(4); // dealer

		pivotTable.addColumnLabel(DataConsolidateFunction.COUNT, 5, "Suma_Automobile");

		return my_xlsx_workbook;
	}

	// create workbook cu raport free stoc cars + last production date.
	public static XSSFWorkbook exportToExcel3(XSSFWorkbook workbook, List<List<Object>> ItemsList, List<String> tableHeaders) {

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("HTRO Report Data2");

		// Create a Font for styling header cells
		XSSFFont headerFont = workbook.createFont();
		headerFont.setFontName("Calibri");
		headerFont.setFontHeightInPoints((short) 10);
		// headerFont.setColor(IndexedColors.BLACK.index);

		// Create a CellStyle with the font
		XSSFCellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Cell Style for formatting Date
		XSSFCellStyle dateCellStyle = workbook.createCellStyle();
		XSSFCreationHelper createHelper = workbook.getCreationHelper();
		short dateFormat = createHelper.createDataFormat().getFormat("mmm-yy");
		dateCellStyle.setDataFormat(dateFormat);

		DataFormat format = workbook.createDataFormat();
		XSSFCellStyle nrStyle = workbook.createCellStyle();
		nrStyle.setDataFormat(format.getFormat("0"));

		// create first row with table headers
		int rownum = 0;
		XSSFRow row = sheet.createRow(rownum);

		for (int x = 0; x < tableHeaders.size(); x++) {
			XSSFCell cell = row.createCell(x);
			cell.setCellValue(tableHeaders.get(x));
			cell.setCellStyle(headerCellStyle);
		}

		// Adding the rest of the data.
		System.out.println("exportToExcel3 - ItemsList.size() = " + ItemsList.size());

		for (int index = 0; index < ItemsList.size(); index++) {
			row = sheet.createRow(index + 1);
			List<Object> temp = ItemsList.get(index);
			for (int colIdx = 0; colIdx < temp.size(); colIdx++) {
				XSSFCell cell = row.createCell(colIdx);
				if (temp.get(colIdx) instanceof Date) {
					cell.setCellValue((Date) temp.get(colIdx));
					cell.setCellStyle(dateCellStyle);
				}
				else if (temp.get(colIdx) instanceof String) {
					cell.setCellValue(temp.get(colIdx).toString());
				}
				else {
					cell.setCellValue(temp.get(colIdx) == null ? "" : temp.get(colIdx).toString());
					cell.setCellStyle(nrStyle);
				}
			}
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < tableHeaders.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		return workbook;
	}

	// export raport cu al 2-lea pivot cu masini free si last production month
	public static XSSFWorkbook createSecondPivot(XSSFWorkbook my_xlsx_workbook, List<List<Object>> ItemsList, List<String> tableHeaders) {

		// Blank workbook
		my_xlsx_workbook = exportToExcel3(my_xlsx_workbook, ItemsList, tableHeaders);

		/* Read Data to be Pivoted - we have only one worksheet */
		XSSFSheet sheet = my_xlsx_workbook.getSheet("HTRO Report Data2");

		int firstrow = sheet.getFirstRowNum();
		int firstcol = sheet.getRow(firstrow).getFirstCellNum();
		int lastrow = sheet.getLastRowNum() - 1;
		int lastcol = sheet.getRow(lastrow).getLastCellNum() - 1;

		/* Get the reference for Pivot Data */

		CellReference firstColCell = new CellReference(firstrow, firstcol);
		CellReference lastColCell = new CellReference(lastrow, lastcol);

		AreaReference a = new AreaReference(firstColCell, lastColCell, SpreadsheetVersion.EXCEL2007);

		/* Find out where the Pivot Table needs to be placed */
		CellReference b = new CellReference("B2");

		/* Add a new sheet to create Pivot Table */
		XSSFSheet pivot_sheet;
		if (my_xlsx_workbook.getSheet("RaportFreeStoc") != null) {
			my_xlsx_workbook.removeSheetAt(my_xlsx_workbook.getSheetIndex("RaportFreeStoc"));
			pivot_sheet = my_xlsx_workbook.createSheet("RaportFreeStoc");
		}
		else {
			pivot_sheet = my_xlsx_workbook.createSheet("RaportFreeStoc");
		}

		/* Create Pivot Table on a separate worksheet */
		XSSFPivotTable pivotTable = pivot_sheet.createPivotTable(a, b, sheet);

		/* customizeaza pivotul */
		pivotTable.addRowLabel(2);// model short desc
		pivotTable.addRowLabel(1);// model desc

		pivotTable.addColumnLabel(DataConsolidateFunction.SUM, 0, "CONFIRMED_STOCK");
		pivotTable.addColumnLabel(DataConsolidateFunction.SUM, 3, "LAST_PRODUCTION_MONTH");

		// https://stackoverflow.com/questions/40511928/how-to-set-pivottable-field-number-format-cell-with-apache-poi

		long fieldIndex = 3;
		long numFmtId = 17; // 17 mmm-yy
		Optional.ofNullable(pivotTable.getCTPivotTableDefinition().getDataFields()).map(CTDataFields::getDataFieldList).map(
			List::stream).ifPresent(
				stream -> stream.filter(dataField -> dataField.getFld() == fieldIndex).findFirst().ifPresent(
					dataField -> dataField.setNumFmtId(numFmtId)));

		return my_xlsx_workbook;
	}

	// export raport 22 coloane + sheet pivot
	public static XSSFWorkbook exportExcel1AndPivot(List<List<Object>> ItemsList, List<String> tableHeaders) {

		// Blank workbook
		XSSFWorkbook my_xlsx_workbook = exportToExcel1(ItemsList, tableHeaders);

		/* Read Data to be Pivoted - we have only one worksheet */
		XSSFSheet sheet = my_xlsx_workbook.getSheetAt(0);

		/* Get the reference for Pivot Data */
		CellReference a1 = new CellReference("A1");
		CellReference a2 = new CellReference("Z" + ItemsList.size());

		AreaReference a = new AreaReference(a1, a2, SpreadsheetVersion.EXCEL2007);

		/* Find out where the Pivot Table needs to be placed */
		CellReference b = new CellReference("C2");

		/* Add a new sheet to create Pivot Table */
		XSSFSheet pivot_sheet;
		if (my_xlsx_workbook.getSheet("pivot") != null) {
			my_xlsx_workbook.removeSheetAt(my_xlsx_workbook.getSheetIndex("pivot"));
			pivot_sheet = my_xlsx_workbook.createSheet("pivot");
		}
		else {
			pivot_sheet = my_xlsx_workbook.createSheet("pivot");
		}

		/* Create Pivot Table on a separate worksheet */
		XSSFPivotTable pivotTable = pivot_sheet.createPivotTable(a, b, sheet);

		/* Add filters */
		pivotTable.addRowLabel(5);// res type
		pivotTable.addRowLabel(3);// res dealer == res by dealer

		pivotTable.addColumnLabel(DataConsolidateFunction.COUNT, 0, "Count of HND Car No");

		int colDescr = 13; // model car short descr
		pivotTable.addColLabel(colDescr);

		pivotTable.addReportFilter(17); // filter dupa vin
		pivotTable.addReportFilter(8); // filter dupa location

		return my_xlsx_workbook;
	}

}
