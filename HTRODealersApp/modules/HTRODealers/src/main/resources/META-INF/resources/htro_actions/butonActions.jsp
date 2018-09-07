<%@ include file="../init.jsp" %>

<%
	String mvcPath = ParamUtil.getString(request, "mvcPath");
	ResultRow row = (ResultRow)request.getAttribute("SEARCH_CONTAINER_RESULT_ROW");

	PortofoliuItem portofoliuItem = (PortofoliuItem)row.getObject();

	String tipRezervare = portofoliuItem.getTIP_LINIE();

	boolean validateAction = false;
	boolean validateAction2 = false;

	if (tipRezervare == null || tipRezervare.isEmpty()) {
		tipRezervare = "null";
	}

	if (tipRezervare.equalsIgnoreCase("Temporara")) {
		validateAction = true;

		// 		System.out.println("tipRezervare =>>>" + tipRezervare);

	}

if (tipRezervare.equalsIgnoreCase("Ferma") || tipRezervare.equalsIgnoreCase("Stoc")) {
		validateAction2 = true;

		// 		System.out.println("tipRezervare2 =>>>" + tipRezervare);

	}

/* if (tipRezervare.equalsIgnoreCase("Ferma")) {
		validateAction2 = true;

		// 		System.out.println("tipRezervare2 =>>>" + tipRezervare);

	} */
%>

<%-- markupView="lexicon" icon="<%= StringPool.BLANK %>" --%>
<liferay-ui:icon-menu direction="down-side" message='<%= "Actiuni" %>'>
	<c:if test="<%= validateAction %>">
		<liferay-portlet:renderURL var="anulareRezervaURL">
			<portlet:param name="carNo" value="<%= String.valueOf(portofoliuItem.getHTRO_CAR_NO()) %>" />
			<portlet:param name="tipRezervare" value="<%= String.valueOf(portofoliuItem.getTIP_LINIE()) %>" />
			<portlet:param name="mvcPath" value="/htro_actions/anulareRezervare.jsp"></portlet:param>
		</liferay-portlet:renderURL>

		<span style="white-space: nowrap"> <liferay-ui:icon message="<%= "Anulare Rezervare" %>"
				url="<%= anulareRezervaURL.toString() %>"
			/>
		</span>
	</c:if>

	<c:if test="<%= validateAction %>">
		<liferay-portlet:renderURL var="setRezervareFerma">
			<portlet:param name="carNo" value="<%= String.valueOf(portofoliuItem.getHTRO_CAR_NO()) %>" />
			<portlet:param name="tipRezervare" value="<%= String.valueOf(portofoliuItem.getTIP_LINIE()) %>" />
			<portlet:param name="mvcPath" value="/htro_actions/setRezervareFerma.jsp"></portlet:param>
		</liferay-portlet:renderURL>

		<span style="white-space: nowrap"> <liferay-ui:icon message="<%= "Setare Rezervare Ferma" %>"
				url="<%= setRezervareFerma.toString() %>">
			</liferay-ui:icon>
		</span>
	</c:if>

	<c:if test="<%= validateAction2 %>">
		<liferay-portlet:renderURL var="updateClientNameURL">
			<portlet:param name="carNo" value="<%= String.valueOf(portofoliuItem.getHTRO_CAR_NO()) %>" />
			<portlet:param name="numeClient" value="<%= String.valueOf(portofoliuItem.getNUME_CLIENT()) %>" />
			<portlet:param name="numeVanzator" value="<%= String.valueOf(portofoliuItem.getNUME_VANZATOR()) %>" />
			<portlet:param name="tipRezervare" value="<%= String.valueOf(portofoliuItem.getTIP_LINIE()) %>" />
			<portlet:param name="mvcPath" value="/htro_actions/updateNume.jsp"></portlet:param>
		</liferay-portlet:renderURL>

		<span style="white-space: nowrap"> <liferay-ui:icon message="<%= "Actualizare" %>"
				url="<%= updateClientNameURL.toString() %>"
			/>
		</span>
	</c:if>
</liferay-ui:icon-menu>