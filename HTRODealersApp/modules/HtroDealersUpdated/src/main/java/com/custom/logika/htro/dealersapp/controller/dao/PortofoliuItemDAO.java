
package com.custom.logika.htro.dealersapp.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.custom.logika.htro.dealersapp.model.PortofoliuItem;

public class PortofoliuItemDAO {

	public PortofoliuItemDAO(Connection jdbcConnection) {
		this.jdbcConnection = jdbcConnection;
	}

	public List<PortofoliuItem> ListAllPortofoliuItems(String dealerID, boolean isAdmin, String tipAutoveh)
		throws SQLException {

		List<PortofoliuItem> listPortofoliuItems = new ArrayList<PortofoliuItem>();

		String sql1 =
			"SELECT dealers.CUSTOMER_NAME DEALER, portof.* FROM HND_DEALER_PORTOFOLIO_V portof, hnd_dealers_v dealers where portof.RES_DEALER_ID like dealers.CUSTOMER_ID ";
		String sql2 =
			"SELECT dealers.CUSTOMER_NAME DEALER, portof.* FROM HND_DEALER_PORTOFOLIO_V portof, hnd_dealers_v dealers where portof.RES_DEALER_ID like dealers.CUSTOMER_ID AND instr(?, to_char(RES_DEALER_ID),1,1)<>0";
		String sql3 =
			"SELECT dealers.CUSTOMER_NAME DEALER, portof.* FROM HND_DEALER_PORTOFOLIO_V portof, hnd_dealers_v dealers where PRODUCT_TYPE=? AND portof.RES_DEALER_ID like dealers.CUSTOMER_ID AND instr(?, to_char(RES_DEALER_ID),1,1)<>0";

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (!isAdmin) {
			if (tipAutoveh.isEmpty()) {
				statement = jdbcConnection.prepareStatement(sql2);
				statement.setString(1, dealerID);
				resultSet = statement.executeQuery();
			}
			else {
				statement = jdbcConnection.prepareStatement(sql3);
				statement.setString(1, tipAutoveh);
				statement.setString(2, dealerID);
				resultSet = statement.executeQuery();
			}
		}
		else {
			statement = jdbcConnection.prepareStatement(sql1);
			resultSet = statement.executeQuery();
		}

		while (resultSet.next()) {
			int HTRO_CAR_NO = resultSet.getInt("HTRO_CAR_NO");
			String DEALER = resultSet.getString("DEALER");
			String DATA_REZ_SAU_FACTURA = resultSet.getString("DATA_REZ_SAU_FACTURA");
			String DATA_EXPIRARE = resultSet.getString("DATA_EXPIRARE");
			int RES_DEALER_ID = resultSet.getInt("RES_DEALER_ID");
			String TIP_LINIE = resultSet.getString("TIP_LINIE");
			String LOCATIE = resultSet.getString("LOCATIE");
			String LUNA_PRODUCTIE = resultSet.getString("LUNA_PRODUCTIE");
			String LUNA_SOSIRE_IN_TARA = resultSet.getString("LUNA_SOSIRE_IN_TARA");
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
			String OMOLOGARE_INDIVIDUALA = resultSet.getString("OMOLOGARE_INDIVIDUALA");
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
			boolean PROFORMA = resultSet.getString("PROFORMA_REQ").equalsIgnoreCase("Y") ? true : false;
			boolean TRANSPORT = resultSet.getString("TRANSPORT_REQ").equalsIgnoreCase("Y") ? true : false;

			DEALER = DEALER == null ? "" : DEALER;
			DATA_REZ_SAU_FACTURA = DATA_REZ_SAU_FACTURA == null ? "" : DATA_REZ_SAU_FACTURA;
			DATA_EXPIRARE = DATA_EXPIRARE == null ? "" : DATA_EXPIRARE;
			TIP_LINIE = TIP_LINIE == null ? "" : TIP_LINIE;
			LOCATIE = LOCATIE == null ? "" : LOCATIE;
			LUNA_PRODUCTIE = LUNA_PRODUCTIE == null ? "" : LUNA_PRODUCTIE;
			LUNA_SOSIRE_IN_TARA = LUNA_SOSIRE_IN_TARA == null ? "" : LUNA_SOSIRE_IN_TARA;
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
			OMOLOGARE_INDIVIDUALA = OMOLOGARE_INDIVIDUALA == null ? "" : OMOLOGARE_INDIVIDUALA;
			REZ_CUST_ID = REZ_CUST_ID == null ? "" : REZ_CUST_ID;

			PortofoliuItem portofoliuItem = new PortofoliuItem(
				HTRO_CAR_NO, DEALER, DATA_REZ_SAU_FACTURA, DATA_EXPIRARE, RES_DEALER_ID, TIP_LINIE, LOCATIE, LUNA_PRODUCTIE,
				LUNA_SOSIRE_IN_TARA, COD_MODEL, TIP_AUTOVEHICUL, COD_CULOARE_EXT, CULOARE_EXTERIOR, CULOARE_INTERIOR, OBSERVATII,
				NUME_CLIENT, NUME_VANZATOR, VIN, ENGINE_NO, AN_FABRICATIE_CF_CIV, OMOLOGARE_INDIVIDUALA, PRET_LISTA, DISCOUNT_STANDARD,
				DISCOUNT_SUPLIMENTAR, TRUSA_LEGISLATIVA, PRET_FINAL, AVANS_PLATIT, REST_DE_PLATA, CUSTOMER_TRX_ID, REZ_CUST_ID,
				SOLD_CUST_ID, PROFORMA, TRANSPORT);

			listPortofoliuItems.add(portofoliuItem);
		}

		resultSet.close();
		statement.close();

		return listPortofoliuItems;
	}

	private Connection jdbcConnection;

}
