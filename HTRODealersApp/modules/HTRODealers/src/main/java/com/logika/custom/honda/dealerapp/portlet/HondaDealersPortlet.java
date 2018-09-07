
package com.logika.custom.honda.dealerapp.portlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.logika.custom.honda.dealerapp.constants.HondaDealersPortletKeys;
import com.logika.custom.honda.dealerapp.controller.service.DBConnection;
import com.logika.custom.honda.dealerapp.controller.service.ExportDataUtil;
import com.logika.custom.honda.dealerapp.model.PortofoliuItem;

/**
 * @author admin
 */
@Component(immediate = true, property = {
	"com.liferay.portlet.display-category=category.sample", "com.liferay.portlet.instanceable=false",
	// "javax.portlet.display-name=",
	"com.liferay.portlet.scopeable=true", "javax.portlet.expiration-cache=0", "javax.portlet.init-param.template-path=/",
	"javax.portlet.init-param.view-template=/htro_stoc/viewStoc.jsp", "javax.portlet.name=" + HondaDealersPortletKeys.HondaDealers,
	"javax.portlet.resource-bundle=content.Language", "javax.portlet.security-role-ref=power-user,user",
	"javax.portlet.supports.mime-type=text/html"
}, service = Portlet.class)
public class HondaDealersPortlet extends MVCPortlet {

	final String OPERATION_ONE = "getTipAuto";
	final String OPERATION_TWO = "exportAction";

	// final String OPERATION_THREE = "openPopup";

	private static final Log hndApp_log = LogFactoryUtil.getLog(HondaDealersPortlet.class);

	public void RezervaAction(ActionRequest request, ActionResponse response)
		throws IOException, PortalException, PortletException {
		/*
		 * <aui:input name="Tip Rezervare" /> <aui:input name="Nume Client" />
		 * <aui:input name="Nume Vanzator" /> <aui:input name="HTRO_CAR_NO"
		 * type="hidden" /> <aui:input name="ExtraParam" type="hidden" />
		 */

		int dealerID = Integer.valueOf(ParamUtil.getString(request, "dealerID"));
		String userEmail = ParamUtil.getString(request, "userEmail");
		String tipRezervare = ParamUtil.getString(request, "tipRezervare");
		String numeClient = ParamUtil.getString(request, "numeClient");
		String numeVanzator = ParamUtil.getString(request, "numeVanzator");
		int carNo = Integer.valueOf(ParamUtil.getString(request, "carNo"));

		// System.out.println(ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME)
		// + " SYSOUT - RezervaAction - carNO ===>" + carNo + " - tipRezervare
		// ===>" + tipRezervare + " - dealerID ===>"
		// + dealerID + " - userEmail ===>" + userEmail + " - numeClient ===>" +
		// numeClient);

		if (tipRezervare.equalsIgnoreCase("Ferma")) {
			tipRezervare = "FIRM";
		}
		else if (tipRezervare.equalsIgnoreCase("Temporara")) {
			tipRezervare = "NOT FIRM";
		}

		// System.out.println(ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME)
		// + " - RezervaAction - Calling DB PKG --call
		// hnd_dealer_app_pkg.create_reservation()-- with the parameters.");

		hndApp_log.info(
			"RezervaAction - dealerID ===>" + dealerID + " - userEmail ===>" + userEmail + "- tipRezervare ===>" + tipRezervare +
				"- numeClient ===>" + numeClient + "- numeVanzator ===>" + numeVanzator + "- carNO ===>" + carNo);

		if ((carNo > 0) && (dealerID > 0)) {
			try {
				String messageOut =
					DBConnection.getInstance().MakeRezervation(carNo, dealerID, tipRezervare, numeClient, numeVanzator, userEmail);

				hndApp_log.info("RezervaAction - messageOut = " + messageOut);

				if (messageOut != null) {
					if (messageOut.contains("ORA-20001:")) {
						hndApp_log.error("Rezervare FAILED - ORA-20001");

						// SessionMessages.add(request, "rezervareNeadaugata");

						SessionErrors.add(request, "rezervareNeadaugata");
					}
					else {

						// System.out.println("Mesaj de eroare fara codul
						// rezervat - Rezervare OK");

						hndApp_log.info("Mesaj de eroare diferit- Rezervare ???");
						SessionMessages.add(request, "rezervareAdaugata");
					}
				}
				else {

					// System.out.println("Mesaj de eroare gol - Rezervare OK");

					hndApp_log.info("Mesaj de eroare gol - Rezervare OK");
					SessionMessages.add(request, "rezervareAdaugata");
				}

			}
			catch (Exception e) {
				hndApp_log.error("Exceptie generata la adaugarea unei noi rezervari - ", e);
				e.printStackTrace();
				SessionErrors.add(request, e.getClass().getName());
				PortalUtil.copyRequestParameters(request, response);
				response.setRenderParameter("mvcPath", "/htro_actions/adaugaRezervare.jsp");
			}
		}

		// PortletPreferences prefs = request.getPreferences();
		// prefs.setValues("guestbook-entries", array);

		//

		// try {
		// prefs.store();
		// }
		// catch (IOException ex) {
		// Logger.getLogger(HondaDealersPortlet.class.getName()).log(
		// Level.SEVERE, null, ex);
		// }
		// catch (ValidatorException ex) {
		// Logger.getLogger(HondaDealersPortlet.class.getName()).log(
		// Level.SEVERE, null, ex);
		// }

	}

	public void AnulareRezervareAction(ActionRequest request, ActionResponse response)
		throws IOException, PortalException, PortletException {

		try {
			int carNo = ParamUtil.getInteger(request, "carNo");
			String userEmail = ParamUtil.getString(request, "userEmail");

			// System.out.println("AnulareRezervareAction - userEmail ===>" +
			// userEmail);
			// System.out.println("AnulareRezervareAction - carNO ===>" +
			// carNo);
			// System.out.println("AnulareRezervareAction - Calling DB PKG
			// --call hnd_dealer_app_pkg.CancelRezervation()-- with the above
			// parameters.");

			hndApp_log.info("AnulareRezervareAction - userEmail ===>" + userEmail + "- carNO ===>" + carNo);

			if (carNo > 0) {
				String messageOut = DBConnection.getInstance().CancelRezervation(carNo, userEmail);

				hndApp_log.info("AnulareRezervareAction - messageOut = " + messageOut);

				if (messageOut == null) {
					SessionMessages.add(request, "rezervareAnulata");
					response.setRenderParameter("mvcPath", "/htro_portofoliu/viewPortofoliu.jsp");
				}
				else {

					// System.out.println("Mesaj de eroare gol - Rezervare OK");

					hndApp_log.error("Mesaj de eroare existent - " + messageOut);
					SessionErrors.add(request, "rezervareNeadaugata");
					PortalUtil.copyRequestParameters(request, response);
					response.setRenderParameter("mvcPath", "/htro_actions/anulareRezervare.jsp");
					throw new Exception(messageOut);
				}

			}
			else {
				hndApp_log.error("ID-ul Automobilului nu a fost citit corect!");
				throw new Exception("ID-ul Automobilului nu a fost citit corect!");
			}
		}
		catch (Exception e) {
			hndApp_log.error("Exceptie generata la anularea unei rezervari - ", e);
			e.printStackTrace();
			SessionErrors.add(request, e.getClass().getName());
			PortalUtil.copyRequestParameters(request, response);
			response.setRenderParameter("mvcPath", "/htro_actions/anulareRezervare.jsp");
		}
	}

	public void SetareRezervareFermaAction(ActionRequest request, ActionResponse response)
		throws IOException, PortalException, PortletException {

		int carNo = ParamUtil.getInteger(request, "carNo");
		String userEmail = ParamUtil.getString(request, "userEmail");

		// System.out.println("SetareRezervareFermaAction - userEmail ===>" +
		// userEmail);
		// System.out.println("SetareRezervareFermaAction - carNO ===>" +
		// carNo);
		// System.out.println("SetareRezervareFermaAction - Calling DB PKG
		// --call hnd_dealer_app_pkg.setFirmRezervation()-- with the above
		// parameters.");

		hndApp_log.info("SetareRezervareFermaAction - userEmail ===>" + userEmail + "- carNO ===>" + carNo);

		try {
			if (carNo > 0) {
				String messageOut = DBConnection.getInstance().SetFirmRezervation(carNo, userEmail);

				hndApp_log.info("SetareRezervareFermaAction - messageOut = " + messageOut);

				if (messageOut == null) {
					SessionMessages.add(request, "rezervareActualizata");
					response.setRenderParameter("mvcPath", "/htro_portofoliu/viewPortofoliu.jsp");
				}
				else {

					// System.out.println("Mesaj de eroare gol - Rezervare OK");

					hndApp_log.error("Mesaj de eroare existent - " + messageOut);
					SessionErrors.add(request, "rezervareNeadaugata");
					PortalUtil.copyRequestParameters(request, response);
					response.setRenderParameter("mvcPath", "/htro_actions/setRezervareFerma.jsp");
					throw new Exception(messageOut);
				}

			}
			else {
				hndApp_log.error("ID-ul Automobilului nu a fost citit corect!");
				throw new Exception("ID-ul Automobilului nu a fost citit corect!");
			}
		}
		catch (Exception e) {
			hndApp_log.error("Exceptie generata la Setarea Ferma a unei rezervari - ", e);
			e.printStackTrace();
			SessionErrors.add(request, e.getClass().getName());
			PortalUtil.copyRequestParameters(request, response);
			response.setRenderParameter("mvcPath", "/htro_actions/setRezervareFerma.jsp");
		}
	}

	public void UpdateNumeClientAction(ActionRequest request, ActionResponse response)
		throws IOException, PortalException, PortletException {

		String numeClient = ParamUtil.getString(request, "numeClient");
		String numeVanzator = ParamUtil.getString(request, "numeVanzator");
		int carNo = ParamUtil.getInteger(request, "carNo");
		String userEmail = ParamUtil.getString(request, "userEmail");

		// System.out.println("UpdateNumeClientAction - userEmail ===>" +
		// userEmail);
		// System.out.println("UpdateNumeClientAction - carNO ===>" + carNo);
		// System.out.println("UpdateNumeClientAction - numeClient ===>" +
		// numeClient);
		// System.out.println("UpdateNumeClientAction - numeVanzator ===>" +
		// numeVanzator);
		// System.out.println("UpdateNumeClientAction - Calling DB PKG --call
		// hnd_dealer_app_pkg.UpdateCustomerName()-- with the above
		// parameters.");

		hndApp_log.info(
			"UpdateNumeClientAction - userEmail ===>" + userEmail + " - carNO ===>" + carNo + " numeClient ===>" + numeClient +
				" numeVanzator ===> " + numeVanzator);

		try {
			if (carNo > 0) {
				String messageOut = DBConnection.getInstance().UpdateCustomerName(carNo, numeClient, numeVanzator, userEmail);

				hndApp_log.info("UpdateNumeClientAction - messageOut = " + messageOut);

				if (messageOut == null) {
					SessionMessages.add(request, "detaliirezervareActualizate");
					response.setRenderParameter("mvcPath", "/htro_portofoliu/viewPortofoliu.jsp");
				}
				else {

					// System.out.println("Mesaj de eroare gol - Rezervare OK");

					hndApp_log.error("UpdateNumeClientAction - Mesaj de eroare existent - " + messageOut);
					SessionErrors.add(request, "rezervareNeadaugata");
					PortalUtil.copyRequestParameters(request, response);
					response.setRenderParameter("mvcPath", "/htro_actions/updateNume.jsp");
					throw new Exception(messageOut);
				}

			}
			else {
				hndApp_log.error("ID-ul Automobilului nu a fost citit corect!");
				throw new Exception("ID-ul Automobilului nu a fost citit corect!");
			}

		}
		catch (Exception e) {
			hndApp_log.error("Exceptie generata la modificarea datelor unei rezervari - ", e);
			e.printStackTrace();
			SessionErrors.add(request, e.getClass().getName());
			PortalUtil.copyRequestParameters(request, response);
			response.setRenderParameter("mvcPath", "/htro_actions/updateNume.jsp");
		}
	}

	public void _executeTwo(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		String dealerIdValue = "";

		try {

			// String exportFileName = ParamUtil.getString(request,
			// "exportFileName");
			// System.out.println("The exportFileName is-------->" +
			// exportFileName);

			String tipAuto = ParamUtil.getString(resourceRequest, "tipAuto");
			String statusRez = ParamUtil.getString(resourceRequest, "statusRez");
			dealerIdValue = ParamUtil.getString(resourceRequest, "dealerIdValue");

			hndApp_log.info(
				"ExportToExcel - tipAuto ===>" + tipAuto + " statusRez ===>" + statusRez + " dealerIdValue ===> " + dealerIdValue);

			// if (Validator.isNull(exportFileName)) {
			// exportFileName = "PortofoliuItems.xls";
			// }

			List<PortofoliuItem> portofoliuItemsList = new ArrayList<>();
			/*
			 * if (tipAuto.equalsIgnoreCase("SelectAll")) { portofoliuItemsList
			 * = DBConnection.getInstance().GetFilteredPortofoliuItems("",
			 * dealerIdValue); } else { portofoliuItemsList =
			 * DBConnection.getInstance().GetFilteredPortofoliuItems(tipAuto,
			 * dealerIdValue); }
			 */
			portofoliuItemsList = DBConnection.getInstance().GetFilteredPortofoliuItems(tipAuto, statusRez, dealerIdValue);

			HSSFWorkbook workbook = ExportDataUtil.exportToExcelFile(portofoliuItemsList);

			resourceResponse.setContentType("application/x-excel");
			resourceResponse.setProperty("Content-Disposition", "attachment; filename=PortofoliuItems.xls");
			OutputStream os = resourceResponse.getPortletOutputStream();
			workbook.write(os);
			os.close();

			// System.out.println("Excel demo file written successfully...");

			hndApp_log.info("ExportToExcel -Excel demo file written successfully...");

		}
		catch (Exception e) {

			// _log.error(e, e);

			hndApp_log.error("Exceptie generata la functia de export a datelor din portofoliu - dealer id = " + dealerIdValue, e);
		}
	}

	public void _executeOne(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		String tipAuto = ParamUtil.getString(resourceRequest, "tipAuto1");

		// System.out.println("tipAuto-javaResource =>>> " + tipAuto);

		// build the JsonArray to be sent back

		JSONArray jsonCuloareExtArray = JSONFactoryUtil.createJSONArray();
		List<String> culoareExteriorList = new ArrayList<>();

		String noCuloare = "";

		if (tipAuto.equalsIgnoreCase("SelectAll")) {

			// culoareExteriorList =
			// DBConnection.getInstance().GetStocCuloareExt("");

			noCuloare = "SelectAll";
			jsonCuloareExtArray.put(noCuloare);
		}
		else {
			noCuloare = "SelectAll";
			jsonCuloareExtArray.put(noCuloare);
			culoareExteriorList = DBConnection.getInstance().GetStocCuloareExt(tipAuto);
		}

		// System.out.println("culoareExteriorList-javaResource =>>> " +
		// culoareExteriorList.size());

		// for (String culoare : culoareExteriorList) {
		// jsonCuloareExtArray.put(culoare);
		// }

		culoareExteriorList.forEach(e -> jsonCuloareExtArray.put(e));

		// set the content Type

		resourceResponse.setContentType("text/javascript");

		// using printWrite to write to the response

		PrintWriter writer = resourceResponse.getWriter();

		writer.write(jsonCuloareExtArray.toString());
	}

	/**
	 * public void _executeThree(ResourceRequest resourceRequest,
	 * ResourceResponse resourceResponse) throws IOException, PortletException {
	 * String htroCarNo = ParamUtil.getString(resourceRequest, "htroCarNo1");
	 * System.out.println("htroCarNo - javaResource =>>> " + htroCarNo); String
	 * carNo = ParamUtil.getString(resourceRequest, "carNo");
	 * System.out.println("carNo - javaResource =>>> " + carNo); String
	 * reservation_end_date = ""; String location = ""; String arrival_date =
	 * ""; String retInfo = ""; List<String> detaliiRez = new ArrayList<>(); try
	 * { detaliiRez = DBConnection.getInstance().GetSecondRezDetails(htroCarNo);
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } if (!detaliiRez.isEmpty()) { reservation_end_date
	 * = detaliiRez.get(0); location = detaliiRez.get(1); arrival_date =
	 * detaliiRez.get(2); System.out.println("extra info = " +
	 * reservation_end_date + " - " + location + " - " + arrival_date); retInfo
	 * = "Detalii Rezervare Secundara: \n Data expirare: " +
	 * reservation_end_date + " \n Locatie: " + location + " \n Data sosire in
	 * tara: " + arrival_date; } // set the content Type
	 * resourceResponse.setContentType("text/javascript"); // using printWrite
	 * to write to the response PrintWriter writer =
	 * resourceResponse.getWriter(); writer.write(retInfo); }
	 */

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		String resourceId = resourceRequest.getResourceID();

		if (Validator.isNotNull(resourceId)) {
			switch (resourceId) {
			case OPERATION_ONE:
				_executeOne(resourceRequest, resourceResponse);
				break;
			case OPERATION_TWO:
				_executeTwo(resourceRequest, resourceResponse);
				break;
			default:
				System.out.println("Unexpected resource id found: " + resourceId);
			}
		}
		else {

			// report back error that resource id was missing

			hndApp_log.error("Exceptie generata in  serveResource - Resource id not found!!");
			SessionErrors.add(resourceRequest, this.getClass().getName());
		}
	}

}
