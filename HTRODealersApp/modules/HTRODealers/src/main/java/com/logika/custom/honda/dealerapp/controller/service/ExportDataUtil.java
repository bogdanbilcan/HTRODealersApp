package com.logika.custom.honda.dealerapp.controller.service;

import com.logika.custom.honda.dealerapp.model.PortofoliuItem;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class ExportDataUtil {

	public static HSSFWorkbook exportToExcelFile(
		List<PortofoliuItem> portofoliuItemsList) {

		// Blank workbook

		HSSFWorkbook workbook = new HSSFWorkbook();

		// Create a blank sheet

		HSSFSheet sheet = workbook.createSheet("Sample HTRO Portofoliu Data");

		String[] tableHeaders = {"Car No.", "Status", "Data rezervare/factura", "Data expirare", "Locatie",
				"Luna sosire in tara", "Cod model", "Tip Autovehicul",
				"Cod Culoare", "Culoare Exterior", "Culoare interior",
				"Nume client", "Nume vanzator", "VIN No.", "Engine No.",
				"An Fabricatie CIV", "Observatii", "Omologare individuala",
				"Pret lista", "Discount standard", "Discount suplimentar",
				"Valoare Trusa Legislativa", "Pret final", "Avans platit",
				"Rest de plata"
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

			for (int cellnum =
			 0; cellnum < portofoliuDataList.size(); cellnum++) {

				HSSFCell cell = row.createCell(cellnum);

				cell.setCellValue(portofoliuDataList.get(cellnum));
			}
		}

		return workbook;
	}

}