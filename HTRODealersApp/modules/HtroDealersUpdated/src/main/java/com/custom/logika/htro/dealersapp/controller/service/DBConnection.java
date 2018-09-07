
package com.custom.logika.htro.dealersapp.controller.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.custom.logika.htro.dealersapp.controller.dao.PortofoliuItemDAO;
import com.custom.logika.htro.dealersapp.controller.dao.StocItemDAO;
import com.custom.logika.htro.dealersapp.model.PortofoliuItem;
import com.custom.logika.htro.dealersapp.model.StocItem;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PropsUtil;

public class DBConnection {

	public static DBConnection getInstance() {

		return SingletonHolder.SINGLETON;
	}

	public List<List<String>> getSearchedAndRes()
		throws Exception {

		List<List<String>> tableData = new ArrayList<>();

		String sql1 = "select * from hnd_search_and_res_v";

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = jdbcConnection.prepareStatement(sql1);
			resultSet = statement.executeQuery();

			if (resultSet != null) {
				int size = resultSet.getMetaData().getColumnCount();

				// System.out.println("ListTableItems size = " + size);

				while (resultSet.next()) {
					List<String> listTableItems = new ArrayList<>();
					for (int i = 1; i <= size; i++) {
						if (resultSet.getMetaData().getColumnType(i) == 2) {
							String temp = resultSet.getInt(i) + "";
							temp = temp.equalsIgnoreCase("null") ? "" : temp;
							listTableItems.add(temp);
						}
						if (resultSet.getMetaData().getColumnType(i) == 12) {
							String temp = resultSet.getString(i) + "";
							temp = temp.equalsIgnoreCase("null") ? "" : temp;
							listTableItems.add(temp);
						}
					}
					tableData.add(listTableItems);
				}
			}

			resultSet.close();
			statement.close();

			return tableData;
		}
		catch (SQLException sqle) {
			hndApp_log.error("getSearchedAndRes - Error calling PLSQL procedure", sqle);
			throw new Exception(sqle);
		}
		finally {
			try {
				if (!statement.isClosed())
					statement.close();
			}
			catch (SQLException sqle) {
				hndApp_log.error("getSearchedAndRes - Error closing statement", sqle);
				sqle.printStackTrace();
			}
		}
	}

	public Map<String, String> getDealers() {

		String sql = "SELECT DISTINCT DEALERS.CUSTOMER_NAME as DEALER, customer_id AS DEALER_ID FROM HND_DEALERS_V DEALERS ORDER BY 1 ASC";

		Map<String, String> dealers = new HashMap<String, String>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = jdbcConnection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String DEALER = resultSet.getString("DEALER");
				String DEALER_ID = resultSet.getString("DEALER_ID");
				dealers.put(DEALER, DEALER_ID);
			}
			resultSet.close();
			statement.close();
		}
		catch (SQLException sqle) {
			hndApp_log.error("GetPortfDealers - Error executing query statement", sqle);
		}
		finally {
			try {
				if (!resultSet.isClosed())
					resultSet.close();
				if (!statement.isClosed())
					statement.close();
			}
			catch (SQLException sqle) {
				hndApp_log.error("GetPortfDealers - Error closing statement", sqle);
				sqle.printStackTrace();
			}
		}
		return dealers;
	}

	public List<String> GetPortfDealers() {

		String sql =
			"SELECT DISTINCT DEALERS.CUSTOMER_NAME AS DEALER FROM HND_DEALER_PORTOFOLIO_V PORTOF, HND_DEALERS_V DEALERS WHERE PORTOF.RES_DEALER_ID LIKE DEALERS.CUSTOMER_ID ORDER BY 1 ASC";

		List<String> portfDealers = new ArrayList<String>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = jdbcConnection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String DEALER = resultSet.getString("DEALER");
				portfDealers.add(DEALER);
			}
			resultSet.close();
			statement.close();
		}
		catch (SQLException sqle) {
			hndApp_log.error("GetPortfDealers - Error executing query statement", sqle);
		}
		finally {
			try {
				if (!resultSet.isClosed())
					resultSet.close();
				if (!statement.isClosed())
					statement.close();
			}
			catch (SQLException sqle) {
				hndApp_log.error("GetPortfDealers - Error closing statement", sqle);
				sqle.printStackTrace();
			}
		}
		return portfDealers;
	}

	public String SetStatusCereri(int carNo, boolean proforma, boolean transport)
		throws Exception {

		/*
		 * procedure create_reservation(p_htro_car_no number, p_dealer_id
		 * number, p_reservation_type varchar2, p_customer_name varchar2,
		 * p_salesmen_name varchar2, p_user_name varchar2, p_msg out varchar2);
		 */

		CallableStatement statement = null;
		String p_msgOut = "";

		final String procedureCall = "{call apps.hnd_dealer_app_pkg.set_status_cereri(?, ?, ?, ?)}";
		try {
			statement = jdbcConnection.prepareCall(procedureCall);

			statement.setInt(1, carNo);
			statement.setBoolean(2, proforma);
			statement.setBoolean(3, transport);
			statement.registerOutParameter(4, Types.VARCHAR);

			statement.execute();

			p_msgOut = statement.getString(7);

			return p_msgOut;

		}
		catch (SQLException sqle) {

			// System.out.println("MakeRezervation - Error calling PLSQL
			// procedure " + sqle.getMessage());

			hndApp_log.error("SetStatusCereri - Error calling PLSQL procedure", sqle);
			throw new Exception(sqle);
		}
		finally {
			try {
				if (!statement.isClosed())
					statement.close();
			}
			catch (SQLException sqle) {
				hndApp_log.error("SetStatusCereri - Error closing statement", sqle);
				sqle.printStackTrace();
			}
		}
	}

	public String CancelRezervation(int carNo, String userEmail)
		throws Exception {

		/*
		 * create or replace package hnd_dealer_app_pkg as procedure
		 * create_reservation(p_htro_car_no number, p_dealer_id number,
		 * p_reservation_type varchar2, p_customer_name varchar2,
		 * p_salesmen_name varchar2); procedure cancel_reservation(p_htro_car_no
		 * number); procedure update_reservation(p_htro_car_no number);
		 * procedure update_customer_name(p_htro_car_no number, p_customer_name
		 * varchar2); end;
		 */

		CallableStatement statement = null;
		String p_msgOut = "";
		final String procedureCall = "{call apps.hnd_dealer_app_pkg.cancel_reservation(?,?,?)}";

		try {
			statement = jdbcConnection.prepareCall(procedureCall);
			statement.setInt(1, carNo);
			statement.setString(2, userEmail);
			statement.registerOutParameter(3, Types.VARCHAR);
			statement.execute();
			p_msgOut = statement.getString(3);

			return p_msgOut;
		}
		catch (SQLException sqle) {

			// System.out.println("Error calling PLSQL procedure " +
			// sqle.getMessage());

			hndApp_log.error("CancelRezervation - Error calling PLSQL procedure", sqle);
			throw new Exception(sqle);
		}
		finally {
			try {
				if (!statement.isClosed())
					statement.close();
			}
			catch (SQLException sqle) {
				hndApp_log.error("CancelRezervation - Error closing statement", sqle);
				sqle.printStackTrace();
			}
		}
	}

	public List<String> GetColumnHeaders(String tableName)
		throws Exception {

		String sql = "select COLUMN_NAME from ALL_TAB_COLUMNS where TABLE_NAME=upper(?)";

		List<String> columnHeaders = new ArrayList<String>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, tableName);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String COLUMN_NAME = resultSet.getString("COLUMN_NAME");
				columnHeaders.add(COLUMN_NAME);
			}

			resultSet.close();
			statement.close();

			return columnHeaders;

		}
		catch (SQLException sqle) {

			// System.out.println("MakeRezervation - Error calling PLSQL
			// procedure " + sqle.getMessage());

			hndApp_log.error("MakeRezervation - Error calling PLSQL procedure", sqle);
			throw new Exception(sqle);
		}
		finally {
			try {
				if (!statement.isClosed())
					statement.close();
			}
			catch (SQLException sqle) {
				hndApp_log.error("MakeRezervation - Error closing statement", sqle);
				sqle.printStackTrace();
			}
		}
	}

	public List<List<String>> ListTableItems(String tableName)
		throws Exception {

		List<List<String>> tableData = new ArrayList<>();

		String sql1 = "select * from " + tableName;

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = jdbcConnection.prepareStatement(sql1);
			resultSet = statement.executeQuery();

			if (resultSet != null) {
				int size = resultSet.getMetaData().getColumnCount();
				System.out.println("ListTableItems size = " + size);

				while (resultSet.next()) {
					List<String> listTableItems = new ArrayList<>();
					for (int i = 1; i <= size; i++) {
						if (resultSet.getMetaData().getColumnType(i) == 2) {
							String temp = resultSet.getInt(i) + "";
							temp = temp.equalsIgnoreCase("null") ? "" : temp;
							listTableItems.add(temp);
						}
						if (resultSet.getMetaData().getColumnType(i) == 93) {
							String temp = resultSet.getDate(i) + "";
							temp = temp.equalsIgnoreCase("null") ? "" : temp;
							listTableItems.add(temp);
						}
						if (resultSet.getMetaData().getColumnType(i) == 12) {
							String temp = resultSet.getString(i) + "";
							temp = temp.equalsIgnoreCase("null") ? "" : temp;
							listTableItems.add(temp);
						}
					}
					tableData.add(listTableItems);
				}
				// for (int i = 1; i <= size; i++) {
				// System.out.println(
				// "ListTableItems getColumnType[" + i + "]= " +
				// resultSet.getMetaData().getColumnType(i) + " - " +
				// resultSet.getMetaData().getColumnTypeName(i));
				//
				// }
			}

			resultSet.close();
			statement.close();

			return tableData;
		}
		catch (SQLException sqle) {
			hndApp_log.error("ListTableItems - Error calling PLSQL procedure", sqle);
			throw new Exception(sqle);
		}
		finally {
			try {
				if (!statement.isClosed())
					statement.close();
			}
			catch (SQLException sqle) {
				hndApp_log.error("ListTableItems - Error closing statement", sqle);
				sqle.printStackTrace();
			}
		}
	}

	public List<PortofoliuItem> GetAllPortofoliuItems(String dealerID, boolean isAdmin, String tipAutoveh) {

		PortofoliuItemDAO portofoliuItemDAO = new PortofoliuItemDAO(jdbcConnection);
		try {
			return portofoliuItemDAO.ListAllPortofoliuItems(dealerID, isAdmin, tipAutoveh);
		}
		catch (SQLException sqle) {
			hndApp_log.error("GetAllPortofoliuItems - SQLException", sqle);
			sqle.printStackTrace();
		}

		return new ArrayList<>();
	}

	public List<StocItem> GetFilteredStocItems(String tipAuto, String culoareExterior, String tipAutoveh) {

		StocItemDAO stocItemDAO = new StocItemDAO(jdbcConnection);
		try {
			return stocItemDAO.ListFilteredStocItems(tipAuto, culoareExterior, tipAutoveh);
		}
		catch (SQLException sqle) {
			hndApp_log.error("GetFilteredStocItems - SQLException", sqle);
			sqle.printStackTrace();
		}

		return new ArrayList<>();
	}

	public List<String> GetSecondRezDetails(String htroCarNo)
		throws Exception {

		List<String> secondRezDetails = new ArrayList<>();

		final String procedureCall = "{ call apps.hnd_dealer_app_pkg.next_available_car_info(?, ?, ?, ?) }";
		Date p_reservation_end_date = null;
		String p_location = "";
		String p_arrival_date = "";
		int carNo = Integer.parseInt(htroCarNo);
		CallableStatement statement = null;
		try {
			statement = jdbcConnection.prepareCall(procedureCall);

			statement.setInt(1, carNo);
			statement.registerOutParameter(2, Types.DATE);
			statement.registerOutParameter(3, Types.VARCHAR);
			statement.registerOutParameter(4, Types.VARCHAR);

			statement.execute();

			p_reservation_end_date = statement.getDate(2);
			p_location = statement.getString(3);
			p_arrival_date = statement.getString(4);

			p_arrival_date = p_arrival_date == null ? "" : p_arrival_date;

			if (p_reservation_end_date != null) {
				DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

				String reservation_end_date = df.format(p_reservation_end_date);

				/*
				 * String arrival_date = ""; if (p_arrival_date != null) {
				 * arrival_date = df.format(p_arrival_date); }
				 */

				secondRezDetails.add(reservation_end_date);
				secondRezDetails.add(p_location);
				secondRezDetails.add(p_arrival_date);

				return secondRezDetails;
			}
			else {
				return secondRezDetails;
			}

		}
		catch (SQLException sqle) {

			// System.out.println("Error calling PLSQL procedure " +
			// sqle.getMessage());

			hndApp_log.error("GetSecondRezDetails - Error calling PLSQL procedure ", sqle);
			throw new Exception(sqle);
		}
		finally {
			try {
				if (!statement.isClosed())
					statement.close();
			}
			catch (SQLException sqle) {
				hndApp_log.error("GetSecondRezDetails - Error closing sql statement ", sqle);
				sqle.printStackTrace();
			}
		}
	}

	public List<String> GetStocCuloareExt(String tipAuto, String tipAutoveh) {

		StocItemDAO stocItemDAO = new StocItemDAO(jdbcConnection);
		try {
			return stocItemDAO.List_Culoare_Ext(tipAuto, tipAutoveh);
		}
		catch (SQLException sqle) {
			hndApp_log.error("GetStocCuloareExt - SQLException", sqle);
			sqle.printStackTrace();
		}
		return new ArrayList<>();
	}

	public List<String> GetStocTipAuto(String tipAutoveh) {

		StocItemDAO stocItemDAO = new StocItemDAO(jdbcConnection);
		try {
			return stocItemDAO.List_Tip_Auto(tipAutoveh);
		}
		catch (SQLException sqle) {
			hndApp_log.error("GetStocTipAuto - SQLException", sqle);
			sqle.printStackTrace();
		}

		return new ArrayList<>();
	}

	public String MakeRezervation(
		int carNo, int dealerID, String rezervationType, String customerName, String salesmenName, String userEmail)
		throws Exception {

		/*
		 * procedure create_reservation(p_htro_car_no number, p_dealer_id
		 * number, p_reservation_type varchar2, p_customer_name varchar2,
		 * p_salesmen_name varchar2, p_user_name varchar2, p_msg out varchar2);
		 */

		CallableStatement statement = null;
		String p_msgOut = "";

		final String procedureCall = "{call apps.hnd_dealer_app_pkg.create_reservation(?, ?, ?, ?, ?, ?, ?)}";
		try {
			statement = jdbcConnection.prepareCall(procedureCall);

			statement.setInt(1, carNo);
			statement.setInt(2, dealerID);
			statement.setString(3, rezervationType);
			statement.setString(4, customerName);
			statement.setString(5, salesmenName);
			statement.setString(6, userEmail);
			statement.registerOutParameter(7, Types.VARCHAR);

			statement.execute();

			p_msgOut = statement.getString(7);

			return p_msgOut;

		}
		catch (SQLException sqle) {

			// System.out.println("MakeRezervation - Error calling PLSQL
			// procedure " + sqle.getMessage());

			hndApp_log.error("MakeRezervation - Error calling PLSQL procedure", sqle);
			throw new Exception(sqle);
		}
		finally {
			try {
				if (!statement.isClosed())
					statement.close();
			}
			catch (SQLException sqle) {
				hndApp_log.error("MakeRezervation - Error closing statement", sqle);
				sqle.printStackTrace();
			}
		}
	}

	public void RegisterCarSearch(int dealerId, String tipAuto, String culoareExterior)
		throws Exception {

		CallableStatement statement = null;

		final String procedureCall = "{call apps.hnd_dealer_app_pkg.record_car_search(?,?,?)}";
		try {
			statement = jdbcConnection.prepareCall(procedureCall);
			statement.setInt(1, dealerId);
			statement.setString(2, tipAuto);
			statement.setString(3, culoareExterior);
			statement.execute();
		}
		catch (SQLException sqle) {
			hndApp_log.error("RegisterCarSearch - Error calling PLSQL procedure", sqle);
			throw new Exception(sqle);
		}
		finally {
			try {
				if (!statement.isClosed())
					statement.close();
			}
			catch (SQLException sqle) {
				hndApp_log.error("RegisterCarSearch - Error closing statement", sqle);
				sqle.printStackTrace();
			}
		}

	}

	public void RegisterCerereTransport(int carNo)
		throws Exception {

		CallableStatement statement = null;

		final String procedureCall = "{call apps.hnd_dealer_app_pkg.set_int_requested_date(?)}";
		try {
			statement = jdbcConnection.prepareCall(procedureCall);
			statement.setInt(1, carNo);
			statement.execute();
		}
		catch (SQLException sqle) {
			hndApp_log.error("RegisterCerereTransport - Error calling PLSQL procedure", sqle);
			throw new Exception(sqle);
		}
		finally {
			try {
				if (!statement.isClosed())
					statement.close();
			}
			catch (SQLException sqle) {
				hndApp_log.error("RegisterCerereTransport - Error closing statement", sqle);
				sqle.printStackTrace();
			}
		}
	}

	public void RegisterCerereProforma(int carNo)
		throws Exception {

		CallableStatement statement = null;

		final String procedureCall = "{call apps.hnd_dealer_app_pkg.set_prof_requested_date(?)}";
		try {
			statement = jdbcConnection.prepareCall(procedureCall);
			statement.setInt(1, carNo);
			statement.execute();
		}
		catch (SQLException sqle) {
			hndApp_log.error("RegisterCerereProforma - Error calling PLSQL procedure", sqle);
			throw new Exception(sqle);
		}
		finally {
			try {
				if (!statement.isClosed())
					statement.close();
			}
			catch (SQLException sqle) {
				hndApp_log.error("RegisterCerereProforma - Error closing statement", sqle);
				sqle.printStackTrace();
			}
		}
	}

	public String SetFirmRezervation(int carNo, String userName)
		throws Exception {

		/*
		 * create or replace package hnd_dealer_app_pkg as procedure
		 * create_reservation(p_htro_car_no number, p_dealer_id number,
		 * p_reservation_type varchar2, p_customer_name varchar2,
		 * p_salesmen_name varchar2); procedure cancel_reservation(p_htro_car_no
		 * number); procedure update_reservation(p_htro_car_no number);
		 * procedure update_customer_name(p_htro_car_no number, p_customer_name
		 * varchar2); end;
		 */
		String p_msgOut = "";
		CallableStatement statement = null;
		final String procedureCall = "{call apps.hnd_dealer_app_pkg.update_reservation(?,?,?)}";
		try {
			statement = jdbcConnection.prepareCall(procedureCall);
			statement.setInt(1, carNo);
			statement.setString(2, userName);
			statement.registerOutParameter(3, Types.VARCHAR);
			statement.execute();
			p_msgOut = statement.getString(3);
			p_msgOut = statement.getString(3);

			return p_msgOut;
		}
		catch (SQLException sqle) {

			// System.out.println("Error calling PLSQL procedure " +
			// sqle.getMessage());

			hndApp_log.error("SetFirmRezervation - Error calling PLSQL procedure ", sqle);
			throw new Exception(sqle);
		}
		finally {
			try {
				if (!statement.isClosed())
					statement.close();
			}
			catch (SQLException sqle) {
				hndApp_log.error("SetFirmRezervation - Error closing SQL statement ", sqle);
				sqle.printStackTrace();
			}
		}
	}

	public String UpdateCustomerName(int carNo, String customerName, String salesmenName, String userEmail)
		throws Exception {

		/*
		 * create or replace package hnd_dealer_app_pkg as procedure
		 * create_reservation(p_htro_car_no number, p_dealer_id number,
		 * p_reservation_type varchar2, p_customer_name varchar2,
		 * p_salesmen_name varchar2); procedure cancel_reservation(p_htro_car_no
		 * number); procedure update_reservation(p_htro_car_no number);
		 * procedure update_customer_name(p_htro_car_no number, p_customer_name
		 * varchar2); end;
		 */
		String p_msgOut = "";
		CallableStatement statement = null;
		final String procedureCall = "{call apps.hnd_dealer_app_pkg.update_reservation_details(?,?,?,?,?)}";
		try {
			statement = jdbcConnection.prepareCall(procedureCall);
			statement.setInt(1, carNo);
			statement.setString(2, customerName);
			statement.setString(3, salesmenName);
			statement.setString(4, userEmail);
			statement.registerOutParameter(5, Types.VARCHAR);
			statement.execute();
			p_msgOut = statement.getString(5);

			return p_msgOut;
		}
		catch (SQLException sqle) {

			// System.out.println("Error calling PLSQL procedure " +
			// sqle.getMessage());

			hndApp_log.error("UpdateCustomerName - Error calling PLSQL procedure ", sqle);
			throw new Exception(sqle);
		}
		finally {
			try {
				if (!statement.isClosed())
					statement.close();
			}
			catch (SQLException sqle) {
				hndApp_log.error("UpdateCustomerName - Error closing SQL statement ", sqle);
				sqle.printStackTrace();
			}
		}
	}

	private DBConnection() {
		Thread thread = Thread.currentThread();

		// Get the thread's class loader. You'll reinstate it after using
		// the data source you look up using JNDI

		ClassLoader origLoader = thread.getContextClassLoader();

		// Set Liferay's class loader on the thread

		thread.setContextClassLoader(PortalClassLoaderUtil.getClassLoader());
		try {

			// Retrieve the name of the JNDI Datasource from
			// portal-ext.properties

			dsname = PropsUtil.get("jdbc.external.jndi.name");

			// Look up the data source and connect to it

			InitialContext iContext = new InitialContext();

			// dataSource = (DataSource)
			// iContext.lookup("java:comp/env/jdbc/CustomDBPoolShared");

			dataSource = (DataSource) iContext.lookup("java:comp/env/" + dsname);
			jdbcConnection = dataSource.getConnection();

		}
		catch (NamingException ne) {
			ne.printStackTrace();
		}
		catch (SQLException sqle) {
			hndApp_log.error("DBConnection - SQLException", sqle);
			sqle.printStackTrace();

		}
		finally {

			// Switch back to the original context class loader

			thread.setContextClassLoader(origLoader);
		}
	}

	private static final Log hndApp_log = LogFactoryUtil.getLog(DBConnection.class);

	private DataSource dataSource;
	private String dsname = null;
	private Connection jdbcConnection;

	private static final class SingletonHolder {

		private static final DBConnection SINGLETON = new DBConnection();

	}

}
