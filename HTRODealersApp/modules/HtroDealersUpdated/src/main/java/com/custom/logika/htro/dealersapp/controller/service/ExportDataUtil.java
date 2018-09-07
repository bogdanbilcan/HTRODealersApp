
package com.custom.logika.htro.dealersapp.controller.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTItems;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STAxis;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STItemType;

import com.custom.logika.htro.dealersapp.model.PortofoliuItem;

public class ExportDataUtil {

	// create workbook cu raport 22 coloane
	public static XSSFWorkbook exportToExcel1(List<List<String>> ItemsList, List<String> tableHeaders) {

		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("HTRO Custom Report1");

		// Create a Font for styling header cells
		XSSFFont headerFont = workbook.createFont();
		headerFont.setFontName("Calibri");
		headerFont.setFontHeightInPoints((short) 10);
		headerFont.setColor(IndexedColors.BLACK.index);

		// Cell Style for formatting Date
		// CellStyle dateCellStyle = workbook.createCellStyle();
		// dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

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
			List<String> temp = ItemsList.get(index);
			// System.out.println("ExportRaport1 - temp.size() = " +
			// temp.size());
			for (int colIdx = 0; colIdx < temp.size(); colIdx++) {
				XSSFCell cell = row.createCell(colIdx);
				cell.setCellValue(temp.get(colIdx));
			}
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < tableHeaders.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		// Freze pane (start with column 4)
		sheet.createFreezePane(5, 0);

		// Filter columns
		for (int i = sheet.getRow(0).getFirstCellNum(), end = sheet.getRow(0).getLastCellNum(); i < end; i++) {
			CellRangeAddress cellRangeAddr =
				new CellRangeAddress(0, tableHeaders.size(), sheet.getRow(0).getFirstCellNum(), sheet.getRow(0).getLastCellNum());
			sheet.setAutoFilter(cellRangeAddr);
		}

		return workbook;
	}

	// create workbook cu raport searched + rezervari total
	public static XSSFWorkbook exportToExcel2(List<List<String>> ItemsList) {

		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("HTRO Custom Report1");

		// Create a Font for styling header cells
		XSSFFont headerFont = workbook.createFont();
		headerFont.setFontName("Calibri");
		headerFont.setFontHeightInPoints((short) 10);
		headerFont.setColor(IndexedColors.BLACK.index);

		// Cell Style for formatting Date
		// CellStyle dateCellStyle = workbook.createCellStyle();
		// dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		// Create a CellStyle with the font
		XSSFCellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// create first row with table headers
		int rownum = 0;
		XSSFRow row = sheet.createRow(rownum);
		List<String> tableHeaders = new ArrayList<String>();

		for (int x = 0; x < tableHeaders.size(); x++) {
			XSSFCell cell = row.createCell(x);
			cell.setCellValue(tableHeaders.get(x));
			cell.setCellStyle(headerCellStyle);
		}

		// Adding the rest of the data.
		System.out.println("ExportRaport1 - ItemsList.size() = " + ItemsList.size());
		for (int index = 0; index < ItemsList.size(); index++) {
			row = sheet.createRow(index + 1);
			List<String> temp = ItemsList.get(index);
			// System.out.println("ExportRaport1 - temp.size() = " +
			// temp.size());
			for (int colIdx = 0; colIdx < temp.size(); colIdx++) {
				XSSFCell cell = row.createCell(colIdx);
				cell.setCellValue(temp.get(colIdx));
			}
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < tableHeaders.size(); i++) {
			sheet.autoSizeColumn(i);
		}

		// Freze pane (start with column 4)
		sheet.createFreezePane(5, 0);

		// Filter columns
		for (int i = sheet.getRow(0).getFirstCellNum(), end = sheet.getRow(0).getLastCellNum(); i < end; i++) {
			CellRangeAddress cellRangeAddr =
				new CellRangeAddress(0, 22, sheet.getRow(0).getFirstCellNum(), sheet.getRow(0).getLastCellNum());
			sheet.setAutoFilter(cellRangeAddr);
		}

		return workbook;
	}

	// export raport searched + total rezervari / tip / dealer cu pivot
	public static XSSFWorkbook createPivotSearchedAndReserved(List<List<String>> ItemsList) {

		// Blank workbook
		XSSFWorkbook my_xlsx_workbook = exportToExcel2(ItemsList);

		/* Read Data to be Pivoted - we have only one worksheet */
		XSSFSheet sheet = my_xlsx_workbook.getSheetAt(0);

		/* Get the reference for Pivot Data */
		CellReference a1 = new CellReference("A1");
		CellReference a2 = new CellReference("V734");

		AreaReference a = new AreaReference(a1, a2, SpreadsheetVersion.EXCEL2007);

		/* Find out where the Pivot Table needs to be placed */
		CellReference b = new CellReference("B2");

		/* Add a new sheet to create Pivot Table */
		XSSFSheet pivot_sheet;
		if (my_xlsx_workbook.getSheet("pivot") != null) {
			my_xlsx_workbook.removeSheetAt(my_xlsx_workbook.getSheetIndex("Raport-Interogari+Rezervari"));
			pivot_sheet = my_xlsx_workbook.createSheet("Raport-Interogari+Rezervari");
		}
		else {
			pivot_sheet = my_xlsx_workbook.createSheet("Raport-Interogari+Rezervari");
		}

		/* Create Pivot Table on a separate worksheet */
		XSSFPivotTable pivotTable = pivot_sheet.createPivotTable(a, b, sheet);

		/* customizeaza pivotul */
		pivotTable.addRowLabel(4);
		pivotTable.addRowLabel(2);

		pivotTable.addColumnLabel(DataConsolidateFunction.COUNT, 0, "Numar rezervari");
		pivotTable.addColumnLabel(DataConsolidateFunction.COUNT, 0, "Numar interogari");

		int colDescr = 11;
		addColLabel(pivotTable, colDescr, my_xlsx_workbook);

		// adauga filtru dupa dealer
		pivotTable.addReportFilter(1);

		return my_xlsx_workbook;
	}

	// export raport 22 coloane + sheet pivot
	public static XSSFWorkbook exportExcel1AndPivot(List<List<String>> ItemsList, List<String> tableHeaders) {

		// Blank workbook
		XSSFWorkbook my_xlsx_workbook = exportToExcel1(ItemsList, tableHeaders);

		/* Read Data to be Pivoted - we have only one worksheet */
		XSSFSheet sheet = my_xlsx_workbook.getSheetAt(0);

		/* Get the reference for Pivot Data */
		CellReference a1 = new CellReference("A1");
		CellReference a2 = new CellReference("Y" + ItemsList.size());

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
		int colDescr = 12; // model car short descr
		addColLabel(pivotTable, colDescr, my_xlsx_workbook);

		pivotTable.addReportFilter(16); // filter dupa vin
		pivotTable.addReportFilter(7); // filter dupa location

		return my_xlsx_workbook;
	}

	private static void addColLabel(XSSFPivotTable pivotTable, int columnIndex, XSSFWorkbook my_xlsx_workbook) {

		AreaReference pivotArea = pivotTable.getPivotCacheDefinition().getPivotArea(my_xlsx_workbook);

		int lastRowIndex = pivotArea.getLastCell().getRow() - pivotArea.getFirstCell().getRow();
		int lastColIndex = pivotArea.getLastCell().getCol() - pivotArea.getFirstCell().getCol();

		if (columnIndex > lastColIndex) {
			throw new IndexOutOfBoundsException();
		}
		CTPivotFields pivotFields = pivotTable.getCTPivotTableDefinition().getPivotFields();

		CTPivotField pivotField = CTPivotField.Factory.newInstance();
		CTItems items = pivotField.addNewItems();

		pivotField.setAxis(STAxis.AXIS_COL);
		pivotField.setShowAll(false);
		for (int i = 0; i <= lastRowIndex; i++) {
			items.addNewItem().setT(STItemType.DEFAULT);
		}
		items.setCount(items.sizeOfItemArray());
		pivotFields.setPivotFieldArray(columnIndex, pivotField);

		CTColFields rowFields;
		if (pivotTable.getCTPivotTableDefinition().getColFields() != null) {
			rowFields = pivotTable.getCTPivotTableDefinition().getColFields();
		}
		else {
			rowFields = pivotTable.getCTPivotTableDefinition().addNewColFields();
		}

		rowFields.addNewField().setX(columnIndex);
		rowFields.setCount(rowFields.sizeOfFieldArray());
	}

	// metoda veche de export portofoliu.
	public static HSSFWorkbook exportToExcelFile(List<PortofoliuItem> portofoliuItemsList) {

		// Blank workbook
		HSSFWorkbook workbook = new HSSFWorkbook();
		// Create a blank sheet
		HSSFSheet sheet = workbook.createSheet("Sample HTRO Portofoliu Data");

		String[] tableHeaders = {
			"Car No.", "Status", "Data rezervare/factura", "Data expirare", "Locatie", "Luna sosire in tara", "Cod model",
			"Tip Autovehicul", "Cod Culoare", "Culoare Exterior", "Culoare interior", "Nume client", "Nume vanzator", "VIN No.",
			"Engine No.", "An Fabricatie CIV", "Observatii", "Omologare individuala", "Pret lista", "Discount standard",
			"Discount suplimentar", "Valoare Trusa Legislativa", "Pret final", "Avans platit", "Rest de plata"
		};

		// create first row with table headers
		int rownum = 0;
		HSSFRow row = sheet.createRow(rownum);
		for (int x = 0; x < tableHeaders.length; x++) {
			HSSFCell cell = row.createCell(x);
			cell.setCellValue(tableHeaders[x]);
		}

		for (int index = 0; index < portofoliuItemsList.size(); index++) {
			row = sheet.createRow(index + 1);
			PortofoliuItem testPortofoliu = portofoliuItemsList.get(index);
			List<String> portofoliuDataList = testPortofoliu.toList();
			for (int cellnum = 0; cellnum < portofoliuDataList.size(); cellnum++) {
				HSSFCell cell = row.createCell(cellnum);
				cell.setCellValue(portofoliuDataList.get(cellnum));
			}
		}

		return workbook;
	}

}
