<%@ include file="../init.jsp" %>

<%
	User currentUser = themeDisplay.getUser();

	String userFullName = currentUser.getFullName();

	String mvcPath = ParamUtil.getString(request, "mvcPath");
	ResultRow row = (ResultRow)request.getAttribute("SEARCH_CONTAINER_RESULT_ROW");

	StocItem stocItem = (StocItem)row.getObject();

	String rezervata = stocItem.getREZERVATA();
	String rezTipAuto = stocItem.getTIP_AUTOVEHICUL();
	String rezCuloareExt = stocItem.getDESC_CULOARE_EXTERIOR();
	String rezCuloareInt = stocItem.getCULOARE_INTERIOR();

	// current time in UTC+2 time zone

	LocalTime nowInUtc = LocalTime.now(ZoneId.of("Europe/Bucharest"));

	LocalTime endTime = LocalTime.of(20, 00);
	LocalTime startTime = LocalTime.of(9, 0);

	boolean validateAction = false;
	boolean showTime = false;

	if (rezervata.equalsIgnoreCase("N")) {
		if ((nowInUtc.isAfter(endTime)) || (nowInUtc.isBefore(startTime))) {
			validateAction = false;
			showTime = true;
		} else {
			validateAction = true;
			showTime = false;
		}
	}

	//_log.info("ButonRezerva JSP - nowInUtc ==> " + nowInUtc);

%>

<%!private static Log _log = LogFactoryUtil.getLog("htro_actions.updateNume_jsp"); %>
<liferay-ui:icon-menu direction="left-side" message='<%= "Actiuni" %>'>
	<c:if test="<%= showTime %>">
		<span style="white-space: nowrap"> Rezervare dezactivata!! </span>
	</c:if>

	<c:if test="<%= validateAction %>">
		<liferay-portlet:renderURL var="rezervaURL">
			<liferay-portlet:param name="carNo" value="<%= String.valueOf(stocItem.getHTRO_CAR_NO()) %>" />
			<liferay-portlet:param name="numeVanzator" value="<%= userFullName %>" />
			<liferay-portlet:param name="rezTipAuto" value="<%= rezTipAuto %>" />
			<liferay-portlet:param name="rezCuloareExt" value="<%= rezCuloareExt %>" />
			<liferay-portlet:param name="rezCuloareInt" value="<%= rezCuloareInt %>" />
			<liferay-portlet:param name="mvcPath" value="/htro_actions/adaugaRezervare.jsp" />
		</liferay-portlet:renderURL>
		<%-- <aui:button cssClass="btn btn-xs" onClick="<%= rezervaURL.toString() %>" value="Rezerva"></aui:button> --%>
		<span style="white-space: nowrap"> <liferay-ui:icon iconCssClass="icon-plus-sign" message="Rezerva"
				url="<%= rezervaURL.toString() %>"
			/>
		</span>
	</c:if>
</liferay-ui:icon-menu>