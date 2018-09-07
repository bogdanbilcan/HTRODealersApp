
package com.custom.logika.htro.dealersapp.portlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;

import com.custom.logika.htro.dealersapp.constants.HtroDealersUpdatedPortletKeys;
import com.custom.logika.htro.dealersapp.controller.service.DBConnection;
import com.custom.logika.htro.dealersapp.controller.service.ExportDataUtil;
import com.custom.logika.htro.dealersapp.controller.service.SendMailUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserGroupServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

/**
 * @author Bogdan Bilcan
 */
@Component(immediate = true, property = {
	"com.liferay.portlet.display-category=HTROApps", "javax.portlet.display-name=HTRO Dealers App Updated",
	"javax.portlet.init-param.template-path=/", "javax.portlet.name=" + HtroDealersUpdatedPortletKeys.HtroDealersUpdated,
	"javax.portlet.resource-bundle=content.Language", "javax.portlet.security-role-ref=power-user,user",
	"com.liferay.portlet.display-category=category.HTRODealersApps", "com.liferay.portlet.instanceable=false",
	"com.liferay.portlet.scopeable=true", "javax.portlet.expiration-cache=0",
	"javax.portlet.init-param.view-template=/htro_stoc/viewStoc.jsp", "javax.portlet.supports.mime-type=text/html"
}, service = Portlet.class)

public class HtroDealersUpdatedPortlet extends MVCPortlet {

	private static final Log hndApp_log = LogFactoryUtil.getLog(HtroDealersUpdatedPortlet.class);
	private final String getTipAutoOPERATION = "getTipAuto";
	private final String exportRaport1OPERATION = "exportRaport1";
	private final String exportRaport2OPERATION = "exportRaport2";

	public void updateUserGroups(ActionRequest request, ActionResponse response) {

		hndApp_log.info("updateUserGroups - Start updating custom fields");
		String tipAutovehicule = "TipAutovehicule";

		String automobile = "Automobile";
		String motociclete = "Motociclete";
		String toate = "Toate";

		String[] tipAuto = request.getParameterValues("tipAutoveh");
		String[] tipMoto = request.getParameterValues("tipMoto");
		String[] tipToate = request.getParameterValues("tipToate");

		// hndApp_log.info("updateUserGroups - tipAuto[] = " + tipAuto);
		// hndApp_log.info("updateUserGroups - tipMoto[] = " + tipMoto);
		// hndApp_log.info("updateUserGroups - tipToate[] = " + tipToate);

		boolean statusAuto = tipAuto != null && !tipAuto[0].equalsIgnoreCase("false");
		boolean statusMoto = tipMoto != null && !tipMoto[0].equalsIgnoreCase("false");
		boolean statusToate = tipToate != null && !tipToate[0].equalsIgnoreCase("false");

		hndApp_log.info("updateUserGroups - statusAuto = " + statusAuto);
		hndApp_log.info("updateUserGroups - statusMoto = " + statusMoto);
		hndApp_log.info("updateUserGroups - statusToate = " + statusToate);

		if (statusAuto) {

			for (int i = 0; i < tipAuto.length; i++) {
				String name = tipAuto[i];
				try {
					UserGroup usrGr = UserGroupServiceUtil.getUserGroup(name);
					usrGr.getExpandoBridge().setAttribute(tipAutovehicule, new String[] {
						automobile
					});

					hndApp_log.info("updateUserGroups - setAttribute automobile to " + name);
				}
				catch (PortalException e1) {
					hndApp_log.error("createUserGroups - Erroare la citiree UserGroups custom fields", e1);
					e1.printStackTrace();
					SessionErrors.add(request, e1.getClass().getName());
					PortalUtil.copyRequestParameters(request, response);
					response.setRenderParameter("jspPage", "/htro_stoc/viewStoc.jsp");
				}
			}
		}

		if (statusMoto) {
			for (int i = 0; i < tipMoto.length; i++) {
				String name = tipMoto[i];
				try {
					UserGroup usrGr = UserGroupServiceUtil.getUserGroup(name);
					usrGr.getExpandoBridge().setAttribute(tipAutovehicule, new String[] {
						motociclete
					});
					hndApp_log.info("updateUserGroups - setAttribute motociclete to " + name);
				}
				catch (PortalException e1) {
					hndApp_log.error("createUserGroups - Erroare la citiree UserGroups custom fields", e1);
					e1.printStackTrace();
					SessionErrors.add(request, e1.getClass().getName());
					PortalUtil.copyRequestParameters(request, response);
					response.setRenderParameter("jspPage", "/htro_stoc/viewStoc.jsp");
				}
			}
		}

		if (statusToate) {
			for (int i = 0; i < tipToate.length; i++) {
				String name = tipToate[i];
				try {
					UserGroup usrGr = UserGroupServiceUtil.getUserGroup(name);
					usrGr.getExpandoBridge().setAttribute(tipAutovehicule, new String[] {
						toate
					});
					hndApp_log.info("updateUserGroups - setAttribute toate to " + name);
				}
				catch (PortalException e1) {
					hndApp_log.error("createUserGroups - Erroare la citiree UserGroups custom fields", e1);
					e1.printStackTrace();
					SessionErrors.add(request, e1.getClass().getName());
					PortalUtil.copyRequestParameters(request, response);
					response.setRenderParameter("jspPage", "/htro_stoc/viewStoc.jsp");
				}
			}
		}

		hndApp_log.info("updateUserGroups - Finish updating custom field on grups");
	}

	public void createUserGroups(ActionRequest request, ActionResponse response) {

		hndApp_log.info("createUserGroups - StartCreating");
		String description = "";
		String tipAutovehicule = "TipAutovehicule";
		Map<String, String> dealers = DBConnection.getInstance().getDealers();

		for (String name : dealers.keySet()) {
			// dealer name is key
			// dealer id is value - also is description for group
			description = dealers.get(name);
			hndApp_log.info("createUserGroups - name = " + name);
			try {
				@SuppressWarnings("deprecation")
				UserGroup newGr = UserGroupServiceUtil.addUserGroup(name, description);
				newGr.getExpandoBridge().setAttribute(tipAutovehicule, "Toate");
			}
			catch (PortalException e1) {
				hndApp_log.error("createUserGroups - Erroare la creare de UserGroups", e1);
				e1.printStackTrace();
				SessionErrors.add(request, e1.getClass().getName());
				PortalUtil.copyRequestParameters(request, response);
				response.setRenderParameter("jspPage", "/htro_stoc/viewStoc.jsp");
			}
		}

		for (String name : dealers.keySet()) {
			// dealer name is key
			// dealer id is value - also is description for group
			try {
				UserGroup usrGr = UserGroupServiceUtil.getUserGroup(name);
				String[] TipAutovehicule = (String[]) usrGr.getExpandoBridge().getAttribute(tipAutovehicule);
				String tipAutoveh = String.join("", TipAutovehicule);
				description = usrGr.getDescription();
				hndApp_log.info(
					"createUserGroups - read usergroup = " + name + " - tipAutoveh = " + tipAutoveh + " - descr = " + description);
			}
			catch (PortalException e1) {
				hndApp_log.error("createUserGroups - Erroare la citiree UserGroups custom fields", e1);
				e1.printStackTrace();
				SessionErrors.add(request, e1.getClass().getName());
				PortalUtil.copyRequestParameters(request, response);
				response.setRenderParameter("jspPage", "/htro_stoc/viewStoc.jsp");
			}
		}

		hndApp_log.info("createUserGroups - Finish Creating Roles");
	}

	public void setEmails(ActionRequest request, ActionResponse response) {

		hndApp_log.info("setEmails - Start Updating the emails");
		String emailAddresses = ParamUtil.getString(request, "emailAddresses");
		String EmailAddresses = "EmailAddresses";
		Group test = null;
		hndApp_log.info("setEmails - emailAddresses = " + emailAddresses);

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		try {
			test = themeDisplay.getLayout().getGroup();
			test.getExpandoBridge().setAttribute(EmailAddresses, emailAddresses);
		}
		catch (PortalException e1) {
			hndApp_log.error("setEmails - Exceptie generata la scrierea adreselor de email catre care sa se trimita cererile!! - ", e1);
			SessionErrors.add(request, e1.getClass().getName());
			PortalUtil.copyRequestParameters(request, response);
			response.setRenderParameter("mvcPath", "/htro_admin/adminActions.jsp");
			e1.printStackTrace();
		}
		hndApp_log.info("setEmails - Finished updating the emails");
	}

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			String tipAuto = ParamUtil.getString(renderRequest, "tipAuto");
			String culoareExterior = ParamUtil.getString(renderRequest, "culoareExterior");
			String dealerIdValue = ParamUtil.getString(renderRequest, "dealerIdValue");

			dealerIdValue = dealerIdValue == null ? "0" : dealerIdValue;
			if (tipAuto != null && tipAuto.length() > 0) {
				int dealerId = Integer.parseInt(dealerIdValue);
				tipAuto = tipAuto.equalsIgnoreCase("SelectAll") ? "" : tipAuto;
				culoareExterior = culoareExterior.equalsIgnoreCase("SelectAll") ? "" : culoareExterior;
				try {
					DBConnection.getInstance().RegisterCarSearch(dealerId, tipAuto, culoareExterior);
					hndApp_log.info("render - S-a cautat tipAuto = " + tipAuto + " si culoareExterior = " + culoareExterior);
				}
				catch (Exception e) {
					hndApp_log.error("Exceptie generata la inregistrarea tipului cautat in render (stoc)- ", e);
					e.printStackTrace();
					throw new PortletException(e);
				}
			}
		}
		catch (Exception e) {
			hndApp_log.error("Exceptie generata la randare - ", e);
			e.printStackTrace();
			throw new PortletException(e);
		}

		super.render(renderRequest, renderResponse);
	}

	public void _executeExportRaport1(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		try {

			List<List<String>> reportTableData = new ArrayList<>();
			List<String> reportHeaders = new ArrayList<>();

			reportTableData = DBConnection.getInstance().ListTableItems("hnd_car_stock_info_rep1_v");
			reportHeaders = DBConnection.getInstance().GetColumnHeaders("hnd_car_stock_info_rep1_v");

			OutputStream os;
			XSSFWorkbook workbook = ExportDataUtil.exportExcel1AndPivot(reportTableData, reportHeaders);
			resourceResponse.setContentType("application/x-excel");
			resourceResponse.setProperty("Content-Disposition", "attachment; filename=HTRO_Custom_Report.xlsx");
			os = resourceResponse.getPortletOutputStream();
			workbook.write(os);

			os.close();

			hndApp_log.info("ExportRaport1 - Excel file written successfully...");
		}
		catch (Exception e) {
			hndApp_log.error("ExportRaport1 - Exceptie generata de constructia raportului HTRO Custom Report1", e);
			e.printStackTrace();
			SessionErrors.add(resourceRequest, e.getClass().getName());
		}

	}

	public void _executeExportRaport2(ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		try {

			List<List<String>> reportTableData = new ArrayList<>();

			reportTableData = DBConnection.getInstance().ListTableItems("hnd_car_stock_info_rep1_v");
			XSSFWorkbook workbook = ExportDataUtil.createPivotSearchedAndReserved(reportTableData);

			resourceResponse.setContentType("application/x-excel");
			resourceResponse.setProperty("Content-Disposition", "attachment; filename=RaportRezervariSiInterogari.xlsx");
			OutputStream os = resourceResponse.getPortletOutputStream();
			workbook.write(os);
			os.close();

			hndApp_log.info("RaportRezervariSiInterogari -Excel demo file written successfully...");
		}
		catch (Exception e) {
			hndApp_log.error("RaportRezervariSiInterogari - Exceptie generata de constructia raportului!!", e);
			e.printStackTrace();
			SessionErrors.add(resourceRequest, e.getClass().getName());
		}
	}

	public void VerificaCheckboxes(ActionRequest request, ActionResponse response) {

		String[] cerereTransport = request.getParameterValues("transport");
		String[] cerereProforma = request.getParameterValues("proforma");
		List<String> toAddresses = new ArrayList<String>();
		String adrese = "";
		String EmailAddresses = "EmailAddresses";
		Group test = null;

		boolean testProforma = false;
		boolean testTransport = false;

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		try {
			test = themeDisplay.getLayout().getGroup();
			adrese = (String) test.getExpandoBridge().getAttribute(EmailAddresses);
			System.out.println("adrese read = " + adrese);
			if (adrese != null && adrese.length() > 0) {
				if (adrese.contains(",")) {
					String[] temp = adrese.split(",");
					for (int i = 0; i < temp.length; i++) {
						temp[i] = temp[i].trim();
					}
					toAddresses = Arrays.asList(temp);
				}
				else {
					toAddresses.add(adrese.trim());
				}
			}
		}
		catch (PortalException e1) {
			hndApp_log.error("Exceptie generata la citirea adreselor de email catre care sa se trimita cererile!! - ", e1);
			SessionErrors.add(request, "cerereNetransmisa");
			SessionErrors.add(request, e1.getClass().getName());
			PortalUtil.copyRequestParameters(request, response);
			response.setRenderParameter("mvcPath", "/htro_portofoliu/viewPortofoliu.jsp");
			e1.printStackTrace();
		}

		if ((cerereTransport == null || cerereTransport[0].equalsIgnoreCase("false")) &&
			(cerereProforma == null || cerereProforma[0].equalsIgnoreCase("false"))) {
			SessionErrors.clear(request);
			SessionErrors.add(request, "cerereNetransmisa2");
			PortalUtil.copyRequestParameters(request, response);
			response.setRenderParameter("mvcPath", "/htro_portofoliu/viewPortofoliu.jsp");
		}

		if (cerereTransport != null && !cerereTransport[0].equalsIgnoreCase("false")) {
			System.out.println("VerificaCheckboxes - cerereTransport ===>" + cerereTransport.length);
			List<Integer> carNo = new ArrayList<Integer>();
			String firstPart =
				"<style>.mystyle{border: 1px solid black;border-collapse: collapse;}</style><body style=\"margin: 1;padding: 1;\"><table><tbody><tr><td><div><p>Buna ziua,</p>S-a generat cererea de transport pentru urmatoarele automobile:</div><br><div class=\"tg-wrap\"><table class=\"table table-striped table-bordered nowrap mystyle\" ><thead><tr><th class=\"mystyle\">Dealer</th><th class=\"mystyle\">Locatie</th><th class=\"mystyle\">Cod Model</th><th class=\"mystyle\">Tip Auto</th><th class=\"mystyle\">Cod Culoare Ext.</th><th class=\"mystyle\">VIN No.</th><th class=\"mystyle\">Engine No.</th><th class=\"mystyle\">Nume Client</th><th class=\"mystyle\">Nume Vanzator</th></tr></thead><tbody>";
			String dataStart = "<tr>";
			String dataStyleStart = "<td class=\"mystyle\"> ";
			String dataStyleEnd = " </td>";
			String dataEnd = " </td></tr>";
			String endPart =
				"</tbody></table></div></td></tr><tr><td><div><br><p>Multumesc,</p>HTRO Dealers Application</div></td></tr></tbody></table></body></html>";

			String emailBody1 = firstPart;

			for (int i = 0; i < cerereTransport.length; i++) {
				int carNoTemp = Integer.parseInt(cerereTransport[i].split(",")[0].split("=")[1]);
				carNo.add(carNoTemp);
				String dealer = cerereTransport[i].split("DEALER=")[1].split(",")[0];
				String locatie = cerereTransport[i].split("LOCATIE=")[1].split(",")[0];
				String cod_model = cerereTransport[i].split("COD_MODEL=")[1].split(",")[0];
				String tip_auto = cerereTransport[i].split("TIP_AUTOVEHICUL=")[1].split(",")[0];
				String cod_culoare = cerereTransport[i].split("COD_CULOARE_EXT=")[1].split(",")[0];
				String vin = cerereTransport[i].split("VIN=")[1].split(",")[0];
				String engine_no = cerereTransport[i].split("ENGINE_NO=")[1].split(",")[0];
				String nume_client = cerereTransport[i].split("NUME_CLIENT=")[1].split(",")[0];
				String nume_vanzator = cerereTransport[i].split("NUME_VANZATOR=")[1].split(",")[0];

				String data = dataStart + dataStyleStart + dealer + dataStyleEnd + dataStyleStart + locatie + dataStyleEnd +
					dataStyleStart + cod_model + dataStyleEnd + dataStyleStart + tip_auto + dataStyleEnd + dataStyleStart + cod_culoare +
					dataStyleEnd + dataStyleStart + vin + dataStyleEnd + dataStyleStart + engine_no + dataStyleEnd + dataStyleStart +
					nume_client + dataStyleEnd + dataStyleStart + nume_vanzator + dataEnd;

				emailBody1 += data;

				System.out.println(
					"VerificaCheckboxes - cerereTransport - carNo = " + carNo + " dealer = " + dealer + " locatie = " + locatie +
						" cod_model = " + cod_model + " tip_auto = " + tip_auto + " cod_culoare = " + cod_culoare + " vin = " + vin +
						" engine_no = " + engine_no + " nume_client = " + nume_client + " nume_vanzator = " + nume_vanzator);
			}

			emailBody1 += endPart;

			try {
				testTransport = SendMailUtil.sendMailTransport(emailBody1, toAddresses);
				if (testTransport) {
					hndApp_log.info("Mail - Cerere Transport transmisa OK");
					for (int nr : carNo) {
						DBConnection.getInstance().RegisterCerereTransport(nr);
						hndApp_log.info("Cerere Transport transmisa si inregistrata in DB OK");
					}

					SessionMessages.add(request, "cereretransportTransmisa");
					response.sendRedirect("/htro_portofoliu/viewPortofoliu.jsp");
				}
				else {
					hndApp_log.error("Exceptie generata la trimiterea mail-ului cu cererea de transport");
				}
			}
			catch (Exception e) {
				hndApp_log.error("Exceptie generata la trimiterea unei cereri de transport - ", e);
				e.printStackTrace();
				SessionErrors.add(request, "cereretransportNetransmisa");
				SessionErrors.add(request, e.getClass().getName());
				PortalUtil.copyRequestParameters(request, response);
				response.setRenderParameter("jspPage", "/htro_portofoliu/viewPortofoliu.jsp");
			}
		}

		if (cerereProforma != null && !(cerereProforma[0].equalsIgnoreCase("false"))) {

			System.out.println("VerificaCheckboxes - cerereProforma ===>" + cerereProforma.length);
			List<Integer> carNo = new ArrayList<Integer>();
			String firstPart =
				"<style>.mystyle{border: 1px solid black;border-collapse: collapse;}</style><body style=\"margin: 1;padding: 1;\"><table><tbody><tr><td><div><p>Buna ziua,</p>S-a generat cererea pentru eliberare Proforma aferenta urmatoarelor automobile:</div><br><div class=\"tg-wrap\"><table class=\"table table-striped table-bordered nowrap mystyle\" ><thead><tr><th class=\"mystyle\">Dealer</th><th class=\"mystyle\">Locatie</th><th class=\"mystyle\">Cod Model</th><th class=\"mystyle\">Tip Auto</th><th class=\"mystyle\">Cod Culoare Ext.</th><th class=\"mystyle\">VIN No.</th><th class=\"mystyle\">Engine No.</th><th class=\"mystyle\">Nume Client</th><th class=\"mystyle\">Nume Vanzator</th></tr></thead><tbody>";
			String dataStart = "<tr>";
			String dataStyleStart = "<td class=\"mystyle\"> ";
			String dataStyleEnd = " </td>";
			String dataEnd = " </td></tr>";
			String endPart =
				"</tbody></table></div></td></tr><tr><td><div><br><p>Multumesc,</p>HTRO Dealers Application</div></td></tr></tbody></table></body></html>";

			String emailBody = firstPart;
			for (int i = 0; i < cerereProforma.length; i++) {
				int carNoTemp = Integer.parseInt(cerereProforma[i].split(",")[0].split("=")[1]);
				carNo.add(carNoTemp);
				String dealer = cerereProforma[i].split("DEALER=")[1].split(",")[0];
				String locatie = cerereProforma[i].split("LOCATIE=")[1].split(",")[0];
				String cod_model = cerereProforma[i].split("COD_MODEL=")[1].split(",")[0];
				String tip_auto = cerereProforma[i].split("TIP_AUTOVEHICUL=")[1].split(",")[0];
				String cod_culoare = cerereProforma[i].split("COD_CULOARE_EXT=")[1].split(",")[0];
				String vin = cerereProforma[i].split("VIN=")[1].split(",")[0];
				String engine_no = cerereProforma[i].split("ENGINE_NO=")[1].split(",")[0];
				String nume_client = cerereProforma[i].split("NUME_CLIENT=")[1].split(",")[0];
				String nume_vanzator = cerereProforma[i].split("NUME_VANZATOR=")[1].split(",")[0];

				String data = dataStart + dataStyleStart + dealer + dataStyleEnd + dataStyleStart + locatie + dataStyleEnd +
					dataStyleStart + cod_model + dataStyleEnd + dataStyleStart + tip_auto + dataStyleEnd + dataStyleStart + cod_culoare +
					dataStyleEnd + dataStyleStart + vin + dataStyleEnd + dataStyleStart + engine_no + dataStyleEnd + dataStyleStart +
					nume_client + dataStyleEnd + dataStyleStart + nume_vanzator + dataEnd;

				emailBody += data;

				System.out.println(
					"VerificaCheckboxes - cerereProforma - carNo = " + carNo + " dealer = " + dealer + " locatie = " + locatie +
						" cod_model = " + cod_model + " tip_auto = " + tip_auto + " cod_culoare = " + cod_culoare + " vin = " + vin +
						" engine_no = " + engine_no + " nume_client = " + nume_client + " nume_vanzator = " + nume_vanzator);
			}

			emailBody += endPart;

			try {
				testProforma = SendMailUtil.sendMailProforma(emailBody, toAddresses);
				if (testProforma) {
					hndApp_log.info("Mail - Cerere Proforma transmisa OK");
					for (int nr : carNo) {
						DBConnection.getInstance().RegisterCerereProforma(nr);
						hndApp_log.info("Cerere Proforma transmisa si inregistrata in DB OK");
					}
					SessionMessages.add(request, "cerereproformaTransmisa");
					response.sendRedirect("/htro_portofoliu/viewPortofoliu.jsp");
				}
				else {
					hndApp_log.error("Exceptie generata la trimiterea mail-ului cu Cerere Proforma");
				}
			}
			catch (Exception e) {
				hndApp_log.error("Exceptie generata la trimiterea unei cereri de Proforma ", e);
				e.printStackTrace();
				SessionErrors.add(request, "cerereproformaNetransmisa");
				SessionErrors.add(request, e.getClass().getName());
				PortalUtil.copyRequestParameters(request, response);
				response.setRenderParameter("jspPage", "/htro_portofoliu/viewPortofoliu.jsp");
			}
		}
	}

	public void RezervaAction(ActionRequest request, ActionResponse response) {

		int dealerID = Integer.valueOf(ParamUtil.getString(request, "dealerID"));
		String userEmail = ParamUtil.getString(request, "userEmail");
		String tipRezervare = ParamUtil.getString(request, "tipRezervare");
		String numeClient = ParamUtil.getString(request, "numeClient");
		String numeVanzator = ParamUtil.getString(request, "numeVanzator");
		int carNo = Integer.valueOf(ParamUtil.getString(request, "carNo"));

		if (tipRezervare.equalsIgnoreCase("Ferma")) {
			tipRezervare = "FIRM";
		}
		else if (tipRezervare.equalsIgnoreCase("Temporara")) {
			tipRezervare = "NOT FIRM";
		}

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
						SessionErrors.add(request, "rezervareNeadaugata");
					}
					else {
						hndApp_log.info("Mesaj de eroare diferit- Rezervare ???");
						SessionMessages.add(request, "rezervareAdaugata");
					}
				}
				else {
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
	}

	public void AnulareRezervareAction(ActionRequest request, ActionResponse response) {

		try {
			int carNo = ParamUtil.getInteger(request, "carNo");
			String userEmail = ParamUtil.getString(request, "userEmail");

			hndApp_log.info("AnulareRezervareAction - userEmail ===>" + userEmail + "- carNO ===>" + carNo);

			if (carNo > 0) {
				String messageOut = DBConnection.getInstance().CancelRezervation(carNo, userEmail);

				hndApp_log.info("AnulareRezervareAction - messageOut = " + messageOut);

				if (messageOut == null) {
					SessionMessages.add(request, "rezervareAnulata");
					response.setRenderParameter("mvcPath", "/htro_portofoliu/viewPortofoliu.jsp");
				}
				else {
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

	public void SetareRezervareFermaAction(ActionRequest request, ActionResponse response) {

		int carNo = ParamUtil.getInteger(request, "carNo");
		String userEmail = ParamUtil.getString(request, "userEmail");

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

	public void UpdateNumeClientAction(ActionRequest request, ActionResponse response) {

		String numeClient = ParamUtil.getString(request, "numeClient");
		String numeVanzator = ParamUtil.getString(request, "numeVanzator");
		int carNo = ParamUtil.getInteger(request, "carNo");
		String userEmail = ParamUtil.getString(request, "userEmail");

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

	public void _executeGetTipAuto(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		String tipAuto = ParamUtil.getString(resourceRequest, "tipAuto1");

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		User currentUser = themeDisplay.getUser();

		String tipAutoveh = "";
		String tipFinal = "";
		String customField = "TipAutovehicule";
		List<UserGroup> usrGrps = currentUser.getUserGroups();

		if (!usrGrps.isEmpty()) {
			for (UserGroup usrGrp : usrGrps) {

				String[] TipAutovehicule = (String[]) usrGrp.getExpandoBridge().getAttribute(customField);
				String test = String.join("", TipAutovehicule);
				tipAutoveh += test + ",";
			}

			tipAutoveh = tipAutoveh.substring(0, tipAutoveh.length() - 1);

			if (tipAutoveh.contains("Motociclete")) {
				tipFinal = "Moto";
				if (tipAutoveh.contains("Automobile")) {
					tipFinal = ""; // gol == toate
				}
			}
			else if (tipAutoveh.contains("Automobile")) {
				tipFinal = "Auto";
			}

			System.out.println("_executeGetTipAuto - usrGrps custom field = " + tipAutoveh);
			System.out.println("_executeGetTipAuto - usrGrps custom field Final = " + tipFinal);
		}

		// build the JsonArray to be sent back
		JSONArray jsonCuloareExtArray = JSONFactoryUtil.createJSONArray();
		List<String> culoareExteriorList = new ArrayList<>();

		String noCuloare = "";

		if (tipAuto.equalsIgnoreCase("SelectAll")) {
			noCuloare = "SelectAll";
			jsonCuloareExtArray.put(noCuloare);
		}
		else {
			noCuloare = "SelectAll";
			jsonCuloareExtArray.put(noCuloare);
			culoareExteriorList = DBConnection.getInstance().GetStocCuloareExt(tipAuto, tipFinal);
		}

		culoareExteriorList.forEach(e -> jsonCuloareExtArray.put(e));

		// set the content Type
		resourceResponse.setContentType("text/javascript");

		// using printWrite to write to the response
		PrintWriter writer = resourceResponse.getWriter();
		writer.write(jsonCuloareExtArray.toString());
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		String resourceId = resourceRequest.getResourceID();

		if (Validator.isNotNull(resourceId)) {
			System.out.println("resource id found: " + resourceId);
			switch (resourceId) {
			case getTipAutoOPERATION:
				_executeGetTipAuto(resourceRequest, resourceResponse);
				break;
			case exportRaport1OPERATION:
				_executeExportRaport1(resourceRequest, resourceResponse);
				break;
			case exportRaport2OPERATION:
				_executeExportRaport2(resourceRequest, resourceResponse);
				break;
			default:
				System.out.println("Unexpected resource id found: " + resourceId);
			}
		}
		else {

			// report back error that resource id was missing
			hndApp_log.error("Exceptie generata in serveResource - Resource id not found!!");
			SessionErrors.add(resourceRequest, this.getClass().getName());
		}
	}

}
