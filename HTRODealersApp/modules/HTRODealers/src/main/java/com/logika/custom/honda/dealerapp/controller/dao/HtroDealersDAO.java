
package com.logika.custom.honda.dealerapp.controller.dao;

import com.logika.custom.honda.dealerapp.model.HtroDealers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
public class HtroDealersDAO {

	public HtroDealersDAO(Connection jdbcConnection) {
		this.jdbcConnection = jdbcConnection;
	}

	public String GetDealerIDs(String dealerName) throws SQLException {
		String sql = "select distinct customer_id from hnd_dealers_v d ,HND_DEALER_PORTOFOLIO_V p where instr(d.customer_id, to_char(p.RES_DEALER_ID),1,1)<>0 and d.customer_name= '"
			+ dealerName + "'";

		String dealerIDs = "";

		Statement statement = jdbcConnection.createStatement();

		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String dealerIDValue = resultSet.getString("CUSTOMER_ID");
			dealerIDs = dealerIDValue == null ? "" : dealerIDValue;
		}

		resultSet.close();
		statement.close();

		return dealerIDs;
	}

	public List<HtroDealers> ListAllDealers() throws SQLException {
		List<HtroDealers> listHtroDealers = new ArrayList<>();
		String sql =
			"select * from hnd_dealers_v where customer_id in (select distinct RES_DEALER_ID from HND_DEALER_PORTOFOLIO_V)";

		Statement statement = jdbcConnection.createStatement();

		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String dealerID = resultSet.getString("CUSTOMER_ID");
			String dealerName = resultSet.getString("CUSTOMER_NAME");

			dealerID = dealerID == null ? "" : dealerID;
			dealerName = dealerName == null ? "" : dealerName;

			HtroDealers htroDealer = new HtroDealers(dealerID, dealerName);
			listHtroDealers.add(htroDealer);
		}

		resultSet.close();
		statement.close();

		return listHtroDealers;
	}

	private Connection jdbcConnection;

}