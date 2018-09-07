package com.logika.custom.honda.dealerapp.controller.dao;

import com.logika.custom.honda.dealerapp.model.PortofoliuItem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class PortofoliuItemDAO {

	public PortofoliuItemDAO(Connection jdbcConnection) {
		this.jdbcConnection = jdbcConnection;
	}

	public List<String> List_Port_Tip_Auto(String dealerID)
		throws SQLException {

		List<String> ListPortTipAuto = new ArrayList<>();

		// String sql = "SELECT DISTINCT TIP_AUTOVEHICUL FROM
		// HND_DEALER_PORTOFOLIO_V WHERE RES_DEALER_ID=" + dealerID;

		String sql = "SELECT DISTINCT TIP_AUTOVEHICUL FROM HND_DEALER_PORTOFOLIO_V WHERE instr('" + dealerID +
			"', to_char(RES_DEALER_ID),1,1)<>0";
		Statement statement = jdbcConnection.createStatement();

		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String TIP_AUTOVEHICUL = resultSet.getString("TIP_AUTOVEHICUL");
			ListPortTipAuto.add(TIP_AUTOVEHICUL);
		}

		resultSet.close();
		statement.close();

		ListPortTipAuto.sort(Comparator.naturalOrder());

		return ListPortTipAuto;
	}

	public List<String> List_Portf_Status(String dealerID) throws SQLException {
		List<String> ListPortfStatus = new ArrayList<>();

		// String sql = "SELECT DISTINCT TIP_AUTOVEHICUL FROM
		// HND_DEALER_PORTOFOLIO_V WHERE RES_DEALER_ID=" + dealerID;

		String sql = "SELECT DISTINCT TIP_LINIE FROM HND_DEALER_PORTOFOLIO_V WHERE INSTR('" + dealerID +
			"', TO_CHAR(RES_DEALER_ID),1,1)<>0 ";

		Statement statement = jdbcConnection.createStatement();

		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String status = resultSet.getString("TIP_LINIE");
			ListPortfStatus.add(status);
		}

		resultSet.close();
		statement.close();

		ListPortfStatus.sort(Comparator.naturalOrder());

		return ListPortfStatus;
	}

	public List<PortofoliuItem> ListAllPortofoliuItems(String dealerID)
		throws SQLException {

		List<PortofoliuItem> listPortofoliuItems = new ArrayList<>();

		// String sql = "SELECT * FROM HND_DEALER_PORTOFOLIO_V WHERE
		// RES_DEALER_ID=" + dealerID;

		String sql = "SELECT * FROM HND_DEALER_PORTOFOLIO_V WHERE instr('" + dealerID +
			"', to_char(RES_DEALER_ID),1,1)<>0";
		Statement statement = jdbcConnection.createStatement();

		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int HTRO_CAR_NO = resultSet.getInt("HTRO_CAR_NO");
			String DATA_REZ_SAU_FACTURA = resultSet.getString(
				"DATA_REZ_SAU_FACTURA");
			String DATA_EXPIRARE = resultSet.getString("DATA_EXPIRARE");
			int RES_DEALER_ID = resultSet.getInt("RES_DEALER_ID");
			String TIP_LINIE = resultSet.getString("TIP_LINIE");
			String LOCATIE = resultSet.getString("LOCATIE");
			String LUNA_PRODUCTIE = resultSet.getString("LUNA_PRODUCTIE");
			String LUNA_SOSIRE_IN_TARA = resultSet.getString(
				"LUNA_SOSIRE_IN_TARA");
			String COD_MODEL = resultSet.getString("COD_MODEL");
			String TIP_AUTOVEHICUL = resultSet.getString("TIP_AUTOVEHICUL");
			String COD_CULOARE_EXT = resultSet.getString("COD_CULOARE_EXT");
			String CULOARE_EXTERIOR = resultSet.getString("CULOARE_EXTERIOR");
			String CULOARE_INTERIOR = resultSet.getString("CULOARE_INTERIOR");
			String OBSERVATII = resultSet.getString("OBSERVATII");
			String NUME_CLIENT = resultSet.getString("NUME_CLIENT");
			String NUME_VANZATOR = resultSet.getString("NUME_VANZATOR");
			String VIN = resultSet.getString("VIN");
			String ENGINE_NO = resultSet.getString("ENGINE_NO");
			int AN_FABRICATIE_CF_CIV = resultSet.getInt("AN_FABRICATIE_CF_CIV");
			String OMOLOGARE_INDIVIDUALA = resultSet.getString(
				"OMOLOGARE_INDIVIDUALA");
			int PRET_LISTA = resultSet.getInt("PRET_LISTA");
			int DISCOUNT_STANDARD = resultSet.getInt("DISCOUNT_STANDARD");
			int DISCOUNT_SUPLIMENTAR = resultSet.getInt("DISCOUNT_SUPLIMENTAR");
			int TRUSA_LEGISLATIVA = resultSet.getInt("TRUSA_LEGISLATIVA");
			int PRET_FINAL = resultSet.getInt("PRET_FINAL");
			int AVANS_PLATIT = resultSet.getInt("AVANS_PLATIT");
			int REST_DE_PLATA = resultSet.getInt("REST_DE_PLATA");
			int CUSTOMER_TRX_ID = resultSet.getInt("CUSTOMER_TRX_ID");
			String REZ_CUST_ID = resultSet.getString("REZ_CUST_ID");
			int SOLD_CUST_ID = resultSet.getInt("SOLD_CUST_ID");

			DATA_REZ_SAU_FACTURA =
				DATA_REZ_SAU_FACTURA == null ? "" : DATA_REZ_SAU_FACTURA;
			DATA_EXPIRARE = DATA_EXPIRARE == null ? "" : DATA_EXPIRARE;
			TIP_LINIE = TIP_LINIE == null ? "" : TIP_LINIE;
			LOCATIE = LOCATIE == null ? "" : LOCATIE;
			LUNA_PRODUCTIE = LUNA_PRODUCTIE == null ? "" : LUNA_PRODUCTIE;
			LUNA_SOSIRE_IN_TARA =
				LUNA_SOSIRE_IN_TARA == null ? "" : LUNA_SOSIRE_IN_TARA;
			COD_MODEL = COD_MODEL == null ? "" : COD_MODEL;
			TIP_AUTOVEHICUL = TIP_AUTOVEHICUL == null ? "" : TIP_AUTOVEHICUL;
			COD_CULOARE_EXT = COD_CULOARE_EXT == null ? "" : COD_CULOARE_EXT;
			CULOARE_EXTERIOR = CULOARE_EXTERIOR == null ? "" : CULOARE_EXTERIOR;
			CULOARE_INTERIOR = CULOARE_INTERIOR == null ? "" : CULOARE_INTERIOR;
			OBSERVATII = OBSERVATII == null ? "" : OBSERVATII;
			NUME_CLIENT = NUME_CLIENT == null ? "" : NUME_CLIENT;
			NUME_VANZATOR = NUME_VANZATOR == null ? "" : NUME_VANZATOR;
			VIN = VIN == null ? "" : VIN;
			ENGINE_NO = ENGINE_NO == null ? "" : ENGINE_NO;
			OMOLOGARE_INDIVIDUALA =
				OMOLOGARE_INDIVIDUALA == null ? "" : OMOLOGARE_INDIVIDUALA;
			REZ_CUST_ID = REZ_CUST_ID == null ? "" : REZ_CUST_ID;

			PortofoliuItem portofoliuItem = new PortofoliuItem(HTRO_CAR_NO, DATA_REZ_SAU_FACTURA, DATA_EXPIRARE,
					RES_DEALER_ID, TIP_LINIE, LOCATIE, LUNA_PRODUCTIE,
					LUNA_SOSIRE_IN_TARA, COD_MODEL, TIP_AUTOVEHICUL,
					COD_CULOARE_EXT, CULOARE_EXTERIOR, CULOARE_INTERIOR,
					OBSERVATII, NUME_CLIENT, NUME_VANZATOR, VIN, ENGINE_NO,
					AN_FABRICATIE_CF_CIV, OMOLOGARE_INDIVIDUALA, PRET_LISTA,
					DISCOUNT_STANDARD, DISCOUNT_SUPLIMENTAR, TRUSA_LEGISLATIVA,
					PRET_FINAL, AVANS_PLATIT, REST_DE_PLATA, CUSTOMER_TRX_ID,
					REZ_CUST_ID, SOLD_CUST_ID);

			listPortofoliuItems.add(portofoliuItem);
		}

		resultSet.close();
		statement.close();

		return listPortofoliuItems;
	}

	public List<PortofoliuItem> ListFilteredPortofoliuItems(
			String tipAuto, String statusRezervare, String dealerID)
		throws SQLException {

		List<PortofoliuItem> listPortofoliuItems = new ArrayList<>();

		// String sql = "SELECT * FROM HND_DEALER_PORTOFOLIO_V WHERE
		// LOWER(TIP_AUTOVEHICUL) LIKE LOWER('%" + filtru+ "%') AND
		// RES_DEALER_ID=" + dealerID;

		// String sql = "SELECT * FROM HND_DEALER_PORTOFOLIO_V WHERE
		// TIP_AUTOVEHICUL=NVL('" + filtru+ "',TIP_AUTOVEHICUL) AND
		// RES_DEALER_ID=" + dealerID;

		/*
		 * String sql =
		 * "SELECT * FROM HND_DEALER_PORTOFOLIO_V WHERE TIP_LINIE=NVL('" +
		 * statusRezervare + "',TIP_LINIE) AND TIP_AUTOVEHICUL=NVL('" + tipAuto
		 * + "',TIP_AUTOVEHICUL) AND instr('" + dealerID +
		 * "', to_char(RES_DEALER_ID),1,1)<>0";
		 */

		String sql1 = "SELECT * FROM HND_DEALER_PORTOFOLIO_V WHERE TIP_AUTOVEHICUL=NVL('" + tipAuto
			+ "',TIP_AUTOVEHICUL) AND instr('" + dealerID + "', to_char(RES_DEALER_ID),1,1)<>0";

		String sql2 = "SELECT * FROM HND_DEALER_PORTOFOLIO_V WHERE instr('" + dealerID +
			"',to_char(RES_DEALER_ID),1,1)<>0";

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = null;

		if (tipAuto == null || tipAuto.equalsIgnoreCase("")) {
			resultSet = statement.executeQuery(sql2);
		} else {
			resultSet = statement.executeQuery(sql1);
		}

		while (resultSet.next()) {
			int HTRO_CAR_NO = resultSet.getInt("HTRO_CAR_NO");
			String DATA_REZ_SAU_FACTURA = resultSet.getString(
				"DATA_REZ_SAU_FACTURA");
			String DATA_EXPIRARE = resultSet.getString("DATA_EXPIRARE");
			int RES_DEALER_ID = resultSet.getInt("RES_DEALER_ID");
			String TIP_LINIE = resultSet.getString("TIP_LINIE");
			String LOCATIE = resultSet.getString("LOCATIE");
			String LUNA_PRODUCTIE = resultSet.getString("LUNA_PRODUCTIE");
			String LUNA_SOSIRE_IN_TARA = resultSet.getString(
				"LUNA_SOSIRE_IN_TARA");
			String COD_MODEL = resultSet.getString("COD_MODEL");
			String TIP_AUTOVEHICUL = resultSet.getString("TIP_AUTOVEHICUL");
			String COD_CULOARE_EXT = resultSet.getString("COD_CULOARE_EXT");
			String CULOARE_EXTERIOR = resultSet.getString("CULOARE_EXTERIOR");
			String CULOARE_INTERIOR = resultSet.getString("CULOARE_INTERIOR");
			String OBSERVATII = resultSet.getString("OBSERVATII");
			String NUME_CLIENT = resultSet.getString("NUME_CLIENT");
			String NUME_VANZATOR = resultSet.getString("NUME_VANZATOR");
			String VIN = resultSet.getString("VIN");
			String ENGINE_NO = resultSet.getString("ENGINE_NO");
			int AN_FABRICATIE_CF_CIV = resultSet.getInt("AN_FABRICATIE_CF_CIV");
			String OMOLOGARE_INDIVIDUALA = resultSet.getString(
				"OMOLOGARE_INDIVIDUALA");
			int PRET_LISTA = resultSet.getInt("PRET_LISTA");
			int DISCOUNT_STANDARD = resultSet.getInt("DISCOUNT_STANDARD");
			int DISCOUNT_SUPLIMENTAR = resultSet.getInt("DISCOUNT_SUPLIMENTAR");
			int TRUSA_LEGISLATIVA = resultSet.getInt("TRUSA_LEGISLATIVA");
			int PRET_FINAL = resultSet.getInt("PRET_FINAL");
			int AVANS_PLATIT = resultSet.getInt("AVANS_PLATIT");
			int REST_DE_PLATA = resultSet.getInt("REST_DE_PLATA");
			int CUSTOMER_TRX_ID = resultSet.getInt("CUSTOMER_TRX_ID");
			String REZ_CUST_ID = resultSet.getString("REZ_CUST_ID");
			int SOLD_CUST_ID = resultSet.getInt("SOLD_CUST_ID");

			DATA_REZ_SAU_FACTURA =
				DATA_REZ_SAU_FACTURA == null ? "" : DATA_REZ_SAU_FACTURA;
			DATA_EXPIRARE = DATA_EXPIRARE == null ? "" : DATA_EXPIRARE;
			TIP_LINIE = TIP_LINIE == null ? "" : TIP_LINIE;
			LOCATIE = LOCATIE == null ? "" : LOCATIE;
			LUNA_PRODUCTIE = LUNA_PRODUCTIE == null ? "" : LUNA_PRODUCTIE;
			LUNA_SOSIRE_IN_TARA =
				LUNA_SOSIRE_IN_TARA == null ? "" : LUNA_SOSIRE_IN_TARA;
			COD_MODEL = COD_MODEL == null ? "" : COD_MODEL;
			TIP_AUTOVEHICUL = TIP_AUTOVEHICUL == null ? "" : TIP_AUTOVEHICUL;
			COD_CULOARE_EXT = COD_CULOARE_EXT == null ? "" : COD_CULOARE_EXT;
			CULOARE_EXTERIOR = CULOARE_EXTERIOR == null ? "" : CULOARE_EXTERIOR;
			CULOARE_INTERIOR = CULOARE_INTERIOR == null ? "" : CULOARE_INTERIOR;
			OBSERVATII = OBSERVATII == null ? "" : OBSERVATII;
			NUME_CLIENT = NUME_CLIENT == null ? "" : NUME_CLIENT;
			NUME_VANZATOR = NUME_VANZATOR == null ? "" : NUME_VANZATOR;
			VIN = VIN == null ? "" : VIN;
			ENGINE_NO = ENGINE_NO == null ? "" : ENGINE_NO;
			OMOLOGARE_INDIVIDUALA =
				OMOLOGARE_INDIVIDUALA == null ? "" : OMOLOGARE_INDIVIDUALA;
			REZ_CUST_ID = REZ_CUST_ID == null ? "" : REZ_CUST_ID;

			PortofoliuItem portofoliuItem = new PortofoliuItem(HTRO_CAR_NO, DATA_REZ_SAU_FACTURA, DATA_EXPIRARE,
					RES_DEALER_ID, TIP_LINIE, LOCATIE, LUNA_PRODUCTIE,
					LUNA_SOSIRE_IN_TARA, COD_MODEL, TIP_AUTOVEHICUL,
					COD_CULOARE_EXT, CULOARE_EXTERIOR, CULOARE_INTERIOR,
					OBSERVATII, NUME_CLIENT, NUME_VANZATOR, VIN, ENGINE_NO,
					AN_FABRICATIE_CF_CIV, OMOLOGARE_INDIVIDUALA, PRET_LISTA,
					DISCOUNT_STANDARD, DISCOUNT_SUPLIMENTAR, TRUSA_LEGISLATIVA,
					PRET_FINAL, AVANS_PLATIT, REST_DE_PLATA, CUSTOMER_TRX_ID,
					REZ_CUST_ID, SOLD_CUST_ID);

			listPortofoliuItems.add(portofoliuItem);
		}

		resultSet.close();
		statement.close();

		return listPortofoliuItems;
	}

	private Connection jdbcConnection;

}