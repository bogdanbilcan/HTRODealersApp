package com.logika.custom.honda.dealerapp.controller.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PropsUtil;

import com.logika.custom.honda.dealerapp.controller.dao.HtroDealersDAO;
import com.logika.custom.honda.dealerapp.controller.dao.PortofoliuItemDAO;
import com.logika.custom.honda.dealerapp.controller.dao.StocItemDAO;
import com.logika.custom.honda.dealerapp.model.PortofoliuItem;
import com.logika.custom.honda.dealerapp.model.StocItem;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;
public class DBConnection {

	public static DBConnection getInstance() {
		return SingletonHolder.SINGLETON;
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
		final String procedureCall =
			"{call apps.hnd_dealer_app_pkg.cancel_reservation(?,?,?)}";

		try {
			statement = jdbcConnection.prepareCall(procedureCall);
			statement.setInt(1, carNo);
			statement.setString(2, userEmail);
			statement.registerOutParameter(3, Types.VARCHAR);
			statement.execute();
			p_msgOut = statement.getString(3);

			return p_msgOut;
		} catch (SQLException sqle) {

			// System.out.println("Error calling PLSQL procedure " +
			// sqle.getMessage());

			hndApp_log.error(
				"CancelRezervation - Error calling PLSQL procedure", sqle);
			throw new Exception(sqle);
		} finally {
			try {
				if (!statement.isClosed())statement.close();
			} catch (SQLException sqle) {
				hndApp_log.error(
					"CancelRezervation - Error closing statement", sqle);
				sqle.printStackTrace();
			}
		}
	}

	public List<StocItem> GetAllStocItems() {
		StocItemDAO stocItemDAO = new StocItemDAO(jdbcConnection);
		try {
			return stocItemDAO.ListAllStocItems();
		} catch (SQLException sqle) {
			hndApp_log.error("GetAllStocItems - SQLException", sqle);
			sqle.printStackTrace();
		}

		return new ArrayList<>();
	}

	public String GetDealerIDs(String dealerName) {
		HtroDealersDAO htroDealersDAO = new HtroDealersDAO(jdbcConnection);
		try {
			return htroDealersDAO.GetDealerIDs(dealerName);
		} catch (SQLException sqle) {
			hndApp_log.error("GetDealerIDs - SQLException", sqle);
			sqle.printStackTrace();
		}

		return "";
	}

	public List<PortofoliuItem> GetFilteredPortofoliuItems(
		String tipAuto, String statusRezervare, String dealerID) {

		PortofoliuItemDAO portofoliuItemDAO = new PortofoliuItemDAO(
			jdbcConnection);
		try {
			return portofoliuItemDAO.ListFilteredPortofoliuItems(
				tipAuto, statusRezervare, dealerID);
		} catch (SQLException sqle) {
			hndApp_log.error("GetFilteredPortofoliuItems - SQLException", sqle);
			sqle.printStackTrace();
		}

		return new ArrayList<>();
	}

	public List<StocItem> GetFilteredStocItems(String filter1, String filter2) {
		StocItemDAO stocItemDAO = new StocItemDAO(jdbcConnection);
		try {
			return stocItemDAO.ListFilteredStocItems(filter1, filter2);
		} catch (SQLException sqle) {
			hndApp_log.error("GetFilteredStocItems - SQLException", sqle);
			sqle.printStackTrace();
		}

		return new ArrayList<>();
	}

	public List<String> GetPortofoliuStatus(String dealerID) {
		PortofoliuItemDAO portofoliuItemDAO = new PortofoliuItemDAO(
			jdbcConnection);
		try {
			return portofoliuItemDAO.List_Portf_Status(dealerID);
		} catch (SQLException sqle) {
			hndApp_log.error("GetPortofoliuStatus - SQLException", sqle);
			sqle.printStackTrace();
		}

		return new ArrayList<>();
	}

	public List<String> GetPortofoliuTipAuto(String dealerID) {
		PortofoliuItemDAO portofoliuItemDAO = new PortofoliuItemDAO(
			jdbcConnection);
		try {
			return portofoliuItemDAO.List_Port_Tip_Auto(dealerID);
		} catch (SQLException sqle) {
			hndApp_log.error("GetPortofoliuTipAuto - SQLException", sqle);
			sqle.printStackTrace();
		}

		return new ArrayList<>();
	}

	public List<String> GetSecondRezDetails(String htroCarNo) throws Exception {
		List<String> secondRezDetails = new ArrayList<>();

		final String procedureCall =
			"{ call apps.hnd_dealer_app_pkg.next_available_car_info(?, ?, ?, ?) }";
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
				 * String arrival_date = "";
				 *
				 * if (p_arrival_date != null) { arrival_date =
				 * df.format(p_arrival_date); }
				 */

				secondRezDetails.add(reservation_end_date);
				secondRezDetails.add(p_location);
				secondRezDetails.add(p_arrival_date);

				return secondRezDetails;
			} else {
				return secondRezDetails;
			}

		} catch (SQLException sqle) {

			// System.out.println("Error calling PLSQL procedure " +
			// sqle.getMessage());

			hndApp_log.error(
				"GetSecondRezDetails - Error calling PLSQL procedure ", sqle);
			throw new Exception(sqle);
		} finally {
			try {
				if (!statement.isClosed())statement.close();
			} catch (SQLException sqle) {
				hndApp_log.error(
					"GetSecondRezDetails - Error closing sql statement ", sqle);
				sqle.printStackTrace();
			}
		}
	}

	public List<String> GetStocCuloareExt(String tipAuto) {
		StocItemDAO stocItemDAO = new StocItemDAO(jdbcConnection);
		try {
			return stocItemDAO.List_Culoare_Ext(tipAuto);
		} catch (SQLException sqle) {
			hndApp_log.error("GetStocCuloareExt - SQLException", sqle);
			sqle.printStackTrace();
		}

		return new ArrayList<>();
	}

	public List<String> GetStocTipAuto() {
		StocItemDAO stocItemDAO = new StocItemDAO(jdbcConnection);
		try {
			return stocItemDAO.List_Tip_Auto();
		} catch (SQLException sqle) {
			hndApp_log.error("GetStocTipAuto - SQLException", sqle);
			sqle.printStackTrace();
		}

		return new ArrayList<>();
	}

	public String MakeRezervation(int carNo, int dealerID, String rezervationType, String customerName,
			String salesmenName, String userEmail) throws Exception {

		/*
		 * procedure create_reservation(p_htro_car_no number, p_dealer_id
		 * number, p_reservation_type varchar2, p_customer_name varchar2,
		 * p_salesmen_name varchar2, p_user_name varchar2, p_msg out varchar2);
		 */

		CallableStatement statement = null;
		String p_msgOut = "";

		final String procedureCall =
			"{call apps.hnd_dealer_app_pkg.create_reservation(?, ?, ?, ?, ?, ?, ?)}";
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

		} catch (SQLException sqle) {

			// System.out.println("MakeRezervation - Error calling PLSQL
			// procedure " + sqle.getMessage());

			hndApp_log.error(
				"MakeRezervation - Error calling PLSQL procedure", sqle);
			throw new Exception(sqle);
		} finally {
			try {
				if (!statement.isClosed())statement.close();
			} catch (SQLException sqle) {
				hndApp_log.error(
					"MakeRezervation - Error closing statement", sqle);
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
		final String procedureCall =
			"{call apps.hnd_dealer_app_pkg.update_reservation(?,?,?)}";
		try {
			statement = jdbcConnection.prepareCall(procedureCall);
			statement.setInt(1, carNo);
			statement.setString(2, userName);
			statement.registerOutParameter(3, Types.VARCHAR);
			statement.execute();
			p_msgOut = statement.getString(3);
			p_msgOut = statement.getString(3);

			return p_msgOut;
		} catch (SQLException sqle) {

			// System.out.println("Error calling PLSQL procedure " +
			// sqle.getMessage());

			hndApp_log.error(
				"SetFirmRezervation - Error calling PLSQL procedure ", sqle);
			throw new Exception(sqle);
		} finally {
			try {
				if (!statement.isClosed())statement.close();
			} catch (SQLException sqle) {
				hndApp_log.error(
					"SetFirmRezervation - Error closing SQL statement ", sqle);
				sqle.printStackTrace();
			}
		}
	}

	public String UpdateCustomerName(
			int carNo, String customerName, String salesmenName,
			String userEmail)
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
		final String procedureCall =
			"{call apps.hnd_dealer_app_pkg.update_reservation_details(?,?,?,?,?)}";
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
		} catch (SQLException sqle) {

			// System.out.println("Error calling PLSQL procedure " +
			// sqle.getMessage());

			hndApp_log.error(
				"UpdateCustomerName - Error calling PLSQL procedure ", sqle);
			throw new Exception(sqle);
		} finally {
			try {
				if (!statement.isClosed())statement.close();
			} catch (SQLException sqle) {
				hndApp_log.error(
					"UpdateCustomerName - Error closing SQL statement ", sqle);
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

			dataSource = (DataSource)iContext.lookup("java:comp/env/" + dsname);
			jdbcConnection = dataSource.getConnection();

		} catch (NamingException ne) {
			ne.printStackTrace();
		} catch (SQLException sqle) {
			hndApp_log.error("DBConnection - SQLException", sqle);
			sqle.printStackTrace();

		} finally {

			// Switch back to the original context class loader

			thread.setContextClassLoader(origLoader);
		}
	}

	private static final Log hndApp_log = LogFactoryUtil.getLog(
		DBConnection.class);

	private DataSource dataSource;
	private String dsname = null;
	private Connection jdbcConnection;

	private static final class SingletonHolder {
		private static final DBConnection SINGLETON = new DBConnection();

	}

}