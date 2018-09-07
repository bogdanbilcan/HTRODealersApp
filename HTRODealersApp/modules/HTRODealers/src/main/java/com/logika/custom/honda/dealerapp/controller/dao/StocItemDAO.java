
package com.logika.custom.honda.dealerapp.controller.dao;

import com.logika.custom.honda.dealerapp.model.StocItem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class StocItemDAO {

	public StocItemDAO(Connection jdbcConnection) {
		this.jdbcConnection = jdbcConnection;
	}

	public List<String> List_Culoare_Ext(String model_auto)
		throws SQLException {

		List<String> ListCuloareExt = new ArrayList<>();

		// String sql="SELECT DISTINCT DESC_CULOARE_EXTERIOR FROM
		// HND_STOC_HTRO_V WHERE TIP_AUTOVEHICUL LIKE (NVL ('" + model_auto +"',
		// '%')) AND DESC_CULOARE_EXTERIOR IN (SELECT COLOR_DESCRIPTION FROM
		// HND_MODEL_COLOR_MAPPING_V WHERE MODEL_DESCRIPTION LIKE (NVL ('" +
		// model_auto +"', '%')))";

		// String sql = "SELECT DISTINCT DESC_CULOARE_EXTERIOR FROM
		// HND_STOC_HTRO_V WHERE TIP_AUTOVEHICUL LIKE (NVL ('"+ model_auto + "',
		// '%'))";
		// String sql = "SELECT DISTINCT DESC_CULOARE_EXTERIOR FROM
		// HND_STOC_HTRO_V WHERE TIP_AUTOVEHICUL=NVL('"+ model_auto +
		// "',TIP_AUTOVEHICUL)";

		String sql = "SELECT DISTINCT DESC_CULOARE_EXTERIOR FROM HND_STOC_HTRO_V WHERE TIP_AUTOVEHICUL='" + model_auto
			+ "'";
		Statement statement = jdbcConnection.createStatement();

		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String COLOR_DESCRIPTION =
				resultSet.getString("DESC_CULOARE_EXTERIOR");
			ListCuloareExt.add(COLOR_DESCRIPTION);
		}

		ListCuloareExt.sort(Comparator.naturalOrder());
		resultSet.close();
		statement.close();

		return ListCuloareExt;
	}

	public List<String> List_Tip_Auto() throws SQLException {
		List<String> ListTipAuto = new ArrayList<>();

		// String sql = "SELECT DISTINCT MODEL_DESCRIPTION FROM
		// HND_MODEL_COLOR_MAPPING_V WHERE COLOR_DESCRIPTION LIKE
		// ('%"+ culoare + "%')";

		// String sql = "SELECT DISTINCT TIP_AUTOVEHICUL FROM HND_STOC_HTRO_V
		// WHERE TIP_AUTOVEHICUL IN " +
		// "(SELECT MODEL_DESCRIPTION FROM HND_MODEL_COLOR_MAPPING_V WHERE
		// COLOR_DESCRIPTION LIKE ('%" + culoare +
		// "%'))";

		// String sql = "SELECT DISTINCT TIP_AUTOVEHICUL FROM HND_STOC_HTRO_V
		// WHERE TIP_AUTOVEHICUL IN (SELECT MODEL_DESCRIPTION FROM
		// HND_MODEL_COLOR_MAPPING_V)";

		String sql = "SELECT DISTINCT TIP_AUTOVEHICUL FROM HND_STOC_HTRO_V";

		Statement statement = jdbcConnection.createStatement();

		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String MODEL_DESCRIPTION = resultSet.getString("TIP_AUTOVEHICUL");
			ListTipAuto.add(MODEL_DESCRIPTION);
		}

		ListTipAuto.sort(Comparator.naturalOrder());
		resultSet.close();
		statement.close();

		return ListTipAuto;
	}

	public List<StocItem> ListAllStocItems() throws SQLException {
		List<StocItem> listStocItems = new ArrayList<>();
		String sql = "SELECT * FROM HND_STOC_HTRO_V";
		Statement statement = jdbcConnection.createStatement();

		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int HTRO_CAR_NO = resultSet.getInt("HTRO_CAR_NO");
			int RES_DEALER_ID = resultSet.getInt("RES_DEALER_ID");
			int AN_FABRICATIE_CIV = resultSet.getInt("AN_FABRICATIE_CIV");
			String TIP_AUTOVEHICUL = resultSet.getString("TIP_AUTOVEHICUL");
			String COD_CULOARE_EXTERIOR =
				resultSet.getString("COD_CULOARE_EXTERIOR");
			String DESC_CULOARE_EXTERIOR =
				resultSet.getString("DESC_CULOARE_EXTERIOR");
			String VOPSEA_METALIZATA = resultSet.getString("VOPSEA_METALIZATA");
			String CULOARE_INTERIOR = resultSet.getString("CULOARE_INTERIOR");
			String OBSERVATII = resultSet.getString("OBSERVATII");
			String LOCATIE = resultSet.getString("LOCATIE");
			String OMOLOGARE_IND = resultSet.getString("OMOLOGARE_IND");
			String LUNA_SOSIRE_IN_TARA =
				resultSet.getString("LUNA_SOSIRE_IN_TARA");
			String REZERVATA = resultSet.getString("REZERVATA");
			String DATA_EXPIRARE_REZ = resultSet.getString("DATA_EXPIRARE_REZ");

			TIP_AUTOVEHICUL = TIP_AUTOVEHICUL == null ? "" : TIP_AUTOVEHICUL;
			COD_CULOARE_EXTERIOR =
				COD_CULOARE_EXTERIOR == null ? "" : COD_CULOARE_EXTERIOR;
			DESC_CULOARE_EXTERIOR =
				DESC_CULOARE_EXTERIOR == null ? "" : DESC_CULOARE_EXTERIOR;
			VOPSEA_METALIZATA =
				VOPSEA_METALIZATA == null ? "" : VOPSEA_METALIZATA;
			CULOARE_INTERIOR = CULOARE_INTERIOR == null ? "" : CULOARE_INTERIOR;
			OBSERVATII = OBSERVATII == null ? "" : OBSERVATII;
			LOCATIE = LOCATIE == null ? "" : LOCATIE;
			OMOLOGARE_IND = OMOLOGARE_IND == null ? "" : OMOLOGARE_IND;
			LUNA_SOSIRE_IN_TARA =
				LUNA_SOSIRE_IN_TARA == null ? "" : LUNA_SOSIRE_IN_TARA;
			REZERVATA = REZERVATA == null ? "" : REZERVATA;
			DATA_EXPIRARE_REZ =
				DATA_EXPIRARE_REZ == null ? "" : DATA_EXPIRARE_REZ;

			StocItem stocItem = new StocItem(HTRO_CAR_NO, RES_DEALER_ID, AN_FABRICATIE_CIV, TIP_AUTOVEHICUL,
					COD_CULOARE_EXTERIOR,
					DESC_CULOARE_EXTERIOR,
					VOPSEA_METALIZATA, CULOARE_INTERIOR, OBSERVATII,
					LOCATIE,
					OMOLOGARE_IND,
					LUNA_SOSIRE_IN_TARA, REZERVATA, DATA_EXPIRARE_REZ);

			listStocItems.add(stocItem);
		}

		resultSet.close();
		statement.close();

		return listStocItems;
	}

	public List<StocItem> ListFilteredStocItems(
			String tipAuto, String culoareExt)
		throws SQLException {

		List<StocItem> listStocItems = new ArrayList<>();

		// String sql = "SELECT * FROM HND_STOC_HTRO_V WHERE
		// LOWER(TIP_AUTOVEHICUL) LIKE LOWER('%" + tipAuto+ "%') AND
		// lower(DESC_CULOARE_EXTERIOR) LIKE lower('%" + culoareExt + "%')";

		String sql = "SELECT * FROM HND_STOC_HTRO_V WHERE TIP_AUTOVEHICUL=NVL('" + tipAuto
			+ "',TIP_AUTOVEHICUL) AND DESC_CULOARE_EXTERIOR=NVL('" + culoareExt + "',DESC_CULOARE_EXTERIOR)";
		String sql1 = "SELECT * FROM HND_STOC_HTRO_V";

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = null;

		if ((tipAuto == null) || (tipAuto == "null")) {
			tipAuto = "";
		}

		if ((culoareExt == null) || (culoareExt == "null")) {
			culoareExt = "";
		}

		if (tipAuto == "" && culoareExt == "") {
			resultSet = statement.executeQuery(sql1);
		} else {
			resultSet = statement.executeQuery(sql);
		}

		while (resultSet.next()) {
			int HTRO_CAR_NO = resultSet.getInt("HTRO_CAR_NO");
			int RES_DEALER_ID = resultSet.getInt("RES_DEALER_ID");
			int AN_FABRICATIE_CIV = resultSet.getInt("AN_FABRICATIE_CIV");
			String TIP_AUTOVEHICUL = resultSet.getString("TIP_AUTOVEHICUL");
			String COD_CULOARE_EXTERIOR = resultSet.getString(
				"COD_CULOARE_EXTERIOR");
			String DESC_CULOARE_EXTERIOR = resultSet.getString(
				"DESC_CULOARE_EXTERIOR");
			String VOPSEA_METALIZATA = resultSet.getString("VOPSEA_METALIZATA");
			String CULOARE_INTERIOR = resultSet.getString("CULOARE_INTERIOR");
			String OBSERVATII = resultSet.getString("OBSERVATII");
			String LOCATIE = resultSet.getString("LOCATIE");
			String OMOLOGARE_IND = resultSet.getString("OMOLOGARE_IND");
			String LUNA_SOSIRE_IN_TARA = resultSet.getString(
				"LUNA_SOSIRE_IN_TARA");
			String REZERVATA = resultSet.getString("REZERVATA");
			String DATA_EXPIRARE_REZ = resultSet.getString("DATA_EXPIRARE_REZ");

			TIP_AUTOVEHICUL = TIP_AUTOVEHICUL == null ? "" : TIP_AUTOVEHICUL;
			COD_CULOARE_EXTERIOR =
				COD_CULOARE_EXTERIOR == null ? "" : COD_CULOARE_EXTERIOR;
			DESC_CULOARE_EXTERIOR =
				DESC_CULOARE_EXTERIOR == null ? "" : DESC_CULOARE_EXTERIOR;
			VOPSEA_METALIZATA =
				VOPSEA_METALIZATA == null ? "" : VOPSEA_METALIZATA;
			CULOARE_INTERIOR = CULOARE_INTERIOR == null ? "" : CULOARE_INTERIOR;
			OBSERVATII = OBSERVATII == null ? "" : OBSERVATII;
			LOCATIE = LOCATIE == null ? "" : LOCATIE;
			OMOLOGARE_IND = OMOLOGARE_IND == null ? "" : OMOLOGARE_IND;
			LUNA_SOSIRE_IN_TARA =
				LUNA_SOSIRE_IN_TARA == null ? "" : LUNA_SOSIRE_IN_TARA;
			REZERVATA = REZERVATA == null ? "" : REZERVATA;
			DATA_EXPIRARE_REZ =
				DATA_EXPIRARE_REZ == null ? "" : DATA_EXPIRARE_REZ;

			StocItem stocItem = new StocItem(HTRO_CAR_NO, RES_DEALER_ID, AN_FABRICATIE_CIV, TIP_AUTOVEHICUL,
					COD_CULOARE_EXTERIOR, DESC_CULOARE_EXTERIOR,
					VOPSEA_METALIZATA, CULOARE_INTERIOR, OBSERVATII, LOCATIE,
					OMOLOGARE_IND, LUNA_SOSIRE_IN_TARA,
					REZERVATA, DATA_EXPIRARE_REZ);
			listStocItems.add(stocItem);
		}

		resultSet.close();
		statement.close();

		return listStocItems;
	}

	private Connection jdbcConnection;

}