
package com.custom.logika.htro.dealersapp.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.custom.logika.htro.dealersapp.model.StocItem;

public class StocItemDAO {

	public StocItemDAO(Connection jdbcConnection) {
		this.jdbcConnection = jdbcConnection;
	}

	public List<String> List_Culoare_Ext(String model_auto, String tipAutoveh)
		throws SQLException {

		List<String> ListCuloareExt = new ArrayList<>();
		String sql1 = "SELECT DISTINCT DESC_CULOARE_EXTERIOR FROM HND_STOC_HTRO_V WHERE TIP_AUTOVEHICUL=?";
		String sql2 = "SELECT DISTINCT DESC_CULOARE_EXTERIOR FROM HND_STOC_HTRO_V WHERE TIP_AUTOVEHICUL=? AND PRODUCT_TYPE = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (tipAutoveh.isEmpty()) {
			statement = jdbcConnection.prepareStatement(sql1);
			statement.setString(1, model_auto);
		}
		else {
			statement = jdbcConnection.prepareStatement(sql2);
			statement.setString(1, model_auto);
			statement.setString(2, tipAutoveh);
		}

		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			String COLOR_DESCRIPTION = resultSet.getString("DESC_CULOARE_EXTERIOR");
			ListCuloareExt.add(COLOR_DESCRIPTION);
		}
		ListCuloareExt.sort(Comparator.naturalOrder());
		resultSet.close();
		statement.close();
		return ListCuloareExt;
	}

	public List<String> List_Tip_Auto(String tipAutoveh)
		throws SQLException {

		List<String> ListTipAuto = new ArrayList<>();

		String sql1 = "SELECT DISTINCT TIP_AUTOVEHICUL FROM HND_STOC_HTRO_V";
		String sql2 = "SELECT DISTINCT TIP_AUTOVEHICUL FROM HND_STOC_HTRO_V WHERE PRODUCT_TYPE = ?";

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if (tipAutoveh.isEmpty()) {
			statement = jdbcConnection.prepareStatement(sql1);
		}
		else {
			statement = jdbcConnection.prepareStatement(sql2);
			statement.setString(1, tipAutoveh);
		}

		resultSet = statement.executeQuery();

		while (resultSet.next()) {
			String MODEL_DESCRIPTION = resultSet.getString("TIP_AUTOVEHICUL");
			ListTipAuto.add(MODEL_DESCRIPTION);
		}

		ListTipAuto.sort(Comparator.naturalOrder());
		resultSet.close();
		statement.close();

		return ListTipAuto;
	}

	public List<StocItem> ListFilteredStocItems(String tipAuto, String culoareExt, String tipAutoveh)
		throws SQLException {

		List<StocItem> listStocItems = new ArrayList<>();

		String sql1 = "SELECT * FROM HND_STOC_HTRO_V WHERE TIP_AUTOVEHICUL= ? AND DESC_CULOARE_EXTERIOR= ? ";
		String sql2 = "SELECT * FROM HND_STOC_HTRO_V WHERE TIP_AUTOVEHICUL= ? ";
		String sql3 = "SELECT * FROM HND_STOC_HTRO_V WHERE DESC_CULOARE_EXTERIOR= ? ";
		String sql4 = "SELECT * FROM HND_STOC_HTRO_V";

		String sql5 = "SELECT * FROM HND_STOC_HTRO_V WHERE PRODUCT_TYPE=? AND TIP_AUTOVEHICUL= ? AND DESC_CULOARE_EXTERIOR= ? ";
		String sql6 = "SELECT * FROM HND_STOC_HTRO_V WHERE PRODUCT_TYPE=? AND TIP_AUTOVEHICUL= ? ";
		String sql7 = "SELECT * FROM HND_STOC_HTRO_V WHERE PRODUCT_TYPE=? AND DESC_CULOARE_EXTERIOR= ? ";
		String sql8 = "SELECT * FROM HND_STOC_HTRO_V WHERE PRODUCT_TYPE=? ";

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		if ((tipAuto == null) || (tipAuto.equalsIgnoreCase("null"))) {
			tipAuto = "";
		}
		if ((culoareExt == null) || (culoareExt.equalsIgnoreCase("null"))) {
			culoareExt = "";
		}

		if (tipAutoveh.isEmpty()) {
			if (tipAuto.equalsIgnoreCase("") && culoareExt.equalsIgnoreCase("")) {
				statement = jdbcConnection.prepareStatement(sql4);
			}
			else if (tipAuto.equalsIgnoreCase("") && !culoareExt.equalsIgnoreCase("")) {
				statement = jdbcConnection.prepareStatement(sql3);
				statement.setString(1, culoareExt);
			}
			else if (!tipAuto.equalsIgnoreCase("") && culoareExt.equalsIgnoreCase("")) {
				statement = jdbcConnection.prepareStatement(sql2);
				statement.setString(1, tipAuto);
			}
			else {// parametrii completati
				statement = jdbcConnection.prepareStatement(sql1);
				statement.setString(1, tipAuto);
				statement.setString(2, culoareExt);
			}
		}
		else {
			if (tipAuto.equalsIgnoreCase("") && culoareExt.equalsIgnoreCase("")) {
				statement = jdbcConnection.prepareStatement(sql8);
				statement.setString(1, tipAutoveh);
			}
			else if (tipAuto.equalsIgnoreCase("") && !culoareExt.equalsIgnoreCase("")) {
				statement = jdbcConnection.prepareStatement(sql7);
				statement.setString(1, tipAutoveh);
				statement.setString(2, culoareExt);
			}
			else if (!tipAuto.equalsIgnoreCase("") && culoareExt.equalsIgnoreCase("")) {
				statement = jdbcConnection.prepareStatement(sql6);
				statement.setString(1, tipAutoveh);
				statement.setString(2, tipAuto);
			}
			else {// parametrii completati
				statement = jdbcConnection.prepareStatement(sql5);
				statement.setString(1, tipAutoveh);
				statement.setString(2, tipAuto);
				statement.setString(3, culoareExt);
			}
		}

		resultSet = statement.executeQuery();

		while (resultSet.next()) {
			int HTRO_CAR_NO = resultSet.getInt("HTRO_CAR_NO");
			int RES_DEALER_ID = resultSet.getInt("RES_DEALER_ID");
			String DEALER_NAME = resultSet.getString("DEALER_NAME");
			int AN_FABRICATIE_CIV = resultSet.getInt("AN_FABRICATIE_CIV");
			String TIP_AUTOVEHICUL = resultSet.getString("TIP_AUTOVEHICUL");
			String COD_CULOARE_EXTERIOR = resultSet.getString("COD_CULOARE_EXTERIOR");
			String DESC_CULOARE_EXTERIOR = resultSet.getString("DESC_CULOARE_EXTERIOR");
			String VOPSEA_METALIZATA = resultSet.getString("VOPSEA_METALIZATA");
			String CULOARE_INTERIOR = resultSet.getString("CULOARE_INTERIOR");
			String OBSERVATII = resultSet.getString("OBSERVATII");
			String LOCATIE = resultSet.getString("LOCATIE");
			String OMOLOGARE_IND = resultSet.getString("OMOLOGARE_IND");
			String LUNA_SOSIRE_IN_TARA = resultSet.getString("LUNA_SOSIRE_IN_TARA");
			String REZERVATA = resultSet.getString("REZERVATA");
			String DATA_EXPIRARE_REZ = resultSet.getString("DATA_EXPIRARE_REZ");
			String PRODUCT_TYPE = resultSet.getString("PRODUCT_TYPE");
			String INVOICED = resultSet.getString("INVOICED");

			if ("Moto".equalsIgnoreCase(PRODUCT_TYPE)) {
				CULOARE_INTERIOR = "";
			}

			DEALER_NAME = DEALER_NAME == null ? "" : DEALER_NAME;
			TIP_AUTOVEHICUL = TIP_AUTOVEHICUL == null ? "" : TIP_AUTOVEHICUL;
			COD_CULOARE_EXTERIOR = COD_CULOARE_EXTERIOR == null ? "" : COD_CULOARE_EXTERIOR;
			DESC_CULOARE_EXTERIOR = DESC_CULOARE_EXTERIOR == null ? "" : DESC_CULOARE_EXTERIOR;
			VOPSEA_METALIZATA = VOPSEA_METALIZATA == null ? "" : VOPSEA_METALIZATA;
			CULOARE_INTERIOR = CULOARE_INTERIOR == null ? "" : CULOARE_INTERIOR;
			OBSERVATII = OBSERVATII == null ? "" : OBSERVATII;
			LOCATIE = LOCATIE == null ? "" : LOCATIE;
			OMOLOGARE_IND = OMOLOGARE_IND == null ? "" : OMOLOGARE_IND;
			LUNA_SOSIRE_IN_TARA = LUNA_SOSIRE_IN_TARA == null ? "" : LUNA_SOSIRE_IN_TARA;
			REZERVATA = REZERVATA == null ? "" : REZERVATA;
			DATA_EXPIRARE_REZ = DATA_EXPIRARE_REZ == null ? "" : DATA_EXPIRARE_REZ;
			INVOICED = INVOICED == null ? "" : INVOICED;

			StocItem stocItem = new StocItem(
				HTRO_CAR_NO, RES_DEALER_ID, DEALER_NAME, AN_FABRICATIE_CIV, TIP_AUTOVEHICUL, COD_CULOARE_EXTERIOR, DESC_CULOARE_EXTERIOR,
				VOPSEA_METALIZATA, CULOARE_INTERIOR, OBSERVATII, LOCATIE, OMOLOGARE_IND, LUNA_SOSIRE_IN_TARA, REZERVATA, DATA_EXPIRARE_REZ,
				INVOICED, PRODUCT_TYPE);
			listStocItems.add(stocItem);
		}

		resultSet.close();
		statement.close();

		return listStocItems;
	}

	private Connection jdbcConnection;

}
