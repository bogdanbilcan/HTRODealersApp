<%@ include file="../init.jsp" %>
<!-- <div class="container-fluid"> -->
<liferay-ui:success key="detaliirezervareActualizate" message="detaliirezervare-actualizate" />
<liferay-ui:success key="rezervareAnulata" message="rezervare-anulata" />
<liferay-ui:success key="rezervareActualizata" message="rezervare-actualizata" />

<%
	User currentUser = themeDisplay.getUser();

	String userEmail = currentUser.getLogin();
	/*

	// code to be uncommented when getting Dealer Name from Liferay and DealerID from DB based on Name

	String[] dealerNameArray = (String[])currentUser.getExpandoBridge().getAttribute("DealerName");
	String dealerName = String.join("", dealerNameArray);
	String dealerIDsfromDB = "";
	System.out.println("dealerName = " + dealerName);

	if (!dealerName.isEmpty()) {
		dealerIDsfromDB = DBConnection.getInstance().GetDealerIDs(dealerName);
		currentUser.getExpandoBridge().setAttribute("DealerId", dealerIDsfromDB);
	}

	*/
	String dealerIdValue = (String)currentUser.getExpandoBridge().getAttribute("DealerId");
	//System.out.println("dealerIdValue read = " + dealerIdValue);

	//_log.info("View Portofoliu - UserLogin = "+userEmail+" and dealerIdValue => "+ dealerIdValue);

	/*

	// Code part for reading different types of custom fields from user

	String name = "DealerName";
	int type = currentUser.getExpandoBridge().getAttributeType(name);
	System.out.println("type read = " + type);
	Serializable value = currentUser.getExpandoBridge().getAttribute(name);

	if (type == ExpandoColumnConstants.STRING_ARRAY) {
		String[] curValue = (String[])value;
		System.out.println("curValue read = " + String.join("", curValue));
	}

	*/

	String tipAuto = (String)ParamUtil.getString(request, "tipAuto");
	//String statusRez = (String) ParamUtil.getString(request, "statusRez");
	String statusRez = "";

	//System.out.println("tipAuto read = " + tipAuto);
	//System.out.println("statusRez read = " + statusRez);

	List<String> tipAutoList = DBConnection.getInstance().GetPortofoliuTipAuto(dealerIdValue);
	//List<String> statusList = DBConnection.getInstance().GetPortofoliuStatus(dealerIdValue);

	// 		System.out.println("tipAutoSelectValue portofoliu page==> " + tipAuto);
	// 		System.out.println("dealerID portofoliu page==> " + dealerIdValue);

	String noResults = "Nu s-au gasit automobile in baza de date conform filtrelor aplicate.";
	List<PortofoliuItem> portofoliuItems1 = DBConnection.getInstance().GetFilteredPortofoliuItems(tipAuto,
			statusRez, dealerIdValue);

	int listSize = portofoliuItems1.size();

	// 		System.out.println("listSize portofoliuItems1 page==> " + listSize);
%>

<%!private static Log _log = LogFactoryUtil.getLog("htro_portofoliu.viewPortofoliu_jsp"); %>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="tipAuto" value="<%= tipAuto %>" />
	<portlet:param name="mvcPath" value="/htro_portofoliu/viewPortofoliu.jsp" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL varImpl="cautaPortfURL">
	<%-- <portlet:param name="tipAuto" value="<%= tipAuto %>" /> --%>
	<portlet:param name="mvcPath" value="/htro_portofoliu/viewPortofoliu.jsp"></portlet:param>
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="stocURL">
	<portlet:param name="mvcPath" value="/htro_stoc/viewStoc.jsp"></portlet:param>
</liferay-portlet:renderURL>

<liferay-portlet:resourceURL id="exportAction" var="exportActionURL">
	<portlet:param name="tipAuto" value="<%= tipAuto %>" />
	<portlet:param name="statusRez" value="<%= statusRez %>" />
	<portlet:param name="dealerIdValue" value="<%= dealerIdValue %>" />
</liferay-portlet:resourceURL>

<!-- <form class="form-inline"> -->
<aui:form action="<%= cautaPortfURL %>" cssClass="form-inline" method="get" name="PortfSearch">
	<%-- <liferay-ui:header title='<%= "Filtrare Lista Automobile" %>' /> --%>
	<liferay-portlet:renderURLParams varImpl="cautaPortfURL" />

	<div style="display: inline;">
		<div style="float: left;">
			<aui:select label="Tip Autovehicul" name="tipAuto" showEmptyOption="false">
				<aui:option label="Afiseaza toate autovehiculele" selected="true" value="" />

				<%
					for (String tipa : tipAutoList) {
				%>

				<aui:option label="<%= tipa %>" value="<%= tipa %>" />

				<%
					}
				%>

			</aui:select>

			<%--
					<aui:select label="Status Rezervare" name="statusRez" showEmptyOption="false">
						<aui:option label="Afiseaza toate Status-urile" selected="true" value="" />

						<%
							for (String status : statusList) {
						%>

						<aui:option label="<%= status %>" value="<%= status %>" />

						<%
							}
						%>

					</aui:select>
			--%>

		</div>

		<div style="float: right;">
			<aui:button cssClass="btn btn-primary" type="submit" value="Filtreaza" />
			<aui:button cssClass="btn btn-info" onClick="<%= stocURL.toString() %>" value='<%= "Inapoi la Stoc" %>' />
			<aui:button cssClass="btn btn-info" onClick="<%= exportActionURL.toString() %>" value="Export to Excel" />
		</div>
	</div>
</aui:form>
<!-- </form> -->
<div style="clear: both;">
	<div class="container-fluid">
		<liferay-ui:search-container delta="25" emptyResultsMessage="<%= noResults %>" iteratorURL="<%= iteratorURL %>"
			total="<%= listSize %>" deltaConfigurable="true">
			<liferay-ui:search-container-results>

				<%
					results = ListUtil.subList(portofoliuItems1, searchContainer.getStart(), searchContainer.getEnd());
							total = listSize;
							searchContainer.setTotal(total);
							searchContainer.setResults(results);
							iteratorURL.setParameter("cur", searchContainer.getCurParam());
							//System.out.println("filteredStoc Cur PRINT:" + searchContainer.getCur());

				%>

			</liferay-ui:search-container-results>

			<liferay-ui:search-container-row className="PortofoliuItem" keyProperty="HTRO_CAR_NO" modelVar="portofoliuItem">
				<liferay-ui:search-container-column-jsp align="left" name="Actiuni" path="/htro_actions/butonActions.jsp" />
				<%-- <liferay-ui:search-container-column-text name="HTRO Number" property="HTRO_CAR_NO" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Car No.">
					<span style="white-space: nowrap"><%= portofoliuItem.getHTRO_CAR_NO() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Tip rezervare" property="TIP_LINIE" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Status">
					<span style="white-space: nowrap"><%= portofoliuItem.getTIP_LINIE() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Data rezervare/factura" property="DATA_REZ_SAU_FACTURA" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Data rezervare/factura">
					<span style="white-space: nowrap"><%= portofoliuItem.getDATA_REZ_SAU_FACTURA() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Data expirare" property="DATA_EXPIRARE" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Data expirare">
					<span style="white-space: nowrap"><%= portofoliuItem.getDATA_EXPIRARE() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Locatie" property="LOCATIE" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Locatie">
					<span style="white-space: nowrap"><%= portofoliuItem.getLOCATIE() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Luna sosire in tara" property="LUNA_SOSIRE_IN_TARA" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Luna sosire in tara">
					<span style="white-space: nowrap"><%= portofoliuItem.getLUNA_SOSIRE_IN_TARA() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Cod model" property="COD_MODEL" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Cod model">
					<span style="white-space: nowrap"><%= portofoliuItem.getCOD_MODEL() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Tip Autovehicul" property="TIP_AUTOVEHICUL" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Tip Autovehicul">
					<span style="white-space: nowrap"><%= portofoliuItem.getTIP_AUTOVEHICUL() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Cod Culoare" property="COD_CULOARE_EXT" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Cod Culoare">
					<span style="white-space: nowrap"><%= portofoliuItem.getCOD_CULOARE_EXT() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Culoare exterior" property="CULOARE_EXTERIOR" truncate="false" /> --%>
				<liferay-ui:search-container-column-text name="Culoare Exterior">
					<span style="white-space: nowrap"><%= portofoliuItem.getCULOARE_EXTERIOR() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Culoare interior" property="CULOARE_INTERIOR" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Culoare interior">
					<span style="white-space: nowrap"><%= portofoliuItem.getCULOARE_INTERIOR() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Nume client" property="NUME_CLIENT" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Nume client">
					<span style="white-space: nowrap"><%= portofoliuItem.getNUME_CLIENT() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Nume vanzator" property="NUME_VANZATOR" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Nume vanzator">
					<span style="white-space: nowrap"><%= portofoliuItem.getNUME_VANZATOR() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="VIN No." property="VIN" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="VIN No.">
					<span style="white-space: nowrap"><%= portofoliuItem.getVIN() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Engine No." property="ENGINE_NO" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Engine No.">
					<span style="white-space: nowrap"><%= portofoliuItem.getENGINE_NO() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="An de Fabricatie CIV" property="AN_FABRICATIE_CF_CIV" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="An de Fabricatie CIV">
					<span style="white-space: nowrap"><%= portofoliuItem.getAN_FABRICATIE_CF_CIV() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Observatii" property="OBSERVATII" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Observatii">
					<span style="white-space: nowrap"><%= portofoliuItem.getOBSERVATII() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Omologare individuala" property="OMOLOGARE_INDIVIDUALA" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Omolog.">
					<span style="white-space: nowrap"><%= portofoliuItem.getOMOLOGARE_INDIVIDUALA() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Pret lista" property="PRET_LISTA" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Pret lista">
					<span style="white-space: nowrap"><%= portofoliuItem.getPRET_LISTA() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Discount standard" property="DISCOUNT_STANDARD" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Discount standard">
					<span style="white-space: nowrap"><%= portofoliuItem.getDISCOUNT_STANDARD() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Discount suplimentar" property="DISCOUNT_SUPLIMENTAR" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Discount suplimentar">
					<span style="white-space: nowrap"><%= portofoliuItem.getDISCOUNT_SUPLIMENTAR() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Valoare Trusa Legislativa" property="TRUSA_LEGISLATIVA" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Valoare Trusa Legislativa">
					<span style="white-space: nowrap"><%= portofoliuItem.getTRUSA_LEGISLATIVA() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Pret final" property="PRET_FINAL" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Pret final">
					<span style="white-space: nowrap"><%= portofoliuItem.getPRET_FINAL() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Avans platit" property="AVANS_PLATIT" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Avans platit">
					<span style="white-space: nowrap"><%= portofoliuItem.getAVANS_PLATIT() %></span>
				</liferay-ui:search-container-column-text>
				<%-- <liferay-ui:search-container-column-text name="Rest de plata" property="REST_DE_PLATA" truncate="true" /> --%>
				<liferay-ui:search-container-column-text name="Rest de plata">
					<span style="white-space: nowrap"><%= portofoliuItem.getREST_DE_PLATA() %></span>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
			<%-- <liferay-ui:search-iterator displayStyle="list" markupView="lexicon" /> --%>
		</liferay-ui:search-container>
	</div>
</div>