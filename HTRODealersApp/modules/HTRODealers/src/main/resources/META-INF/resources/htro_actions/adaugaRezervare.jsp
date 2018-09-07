<%@ include file="../init.jsp" %>
<liferay-ui:success key="rezervareAdaugata" message="rezervare-adaugata" />

<liferay-portlet:renderURL var="backURL">
	<portlet:param name="mvcPath" value="/htro_stoc/viewStoc.jsp"></portlet:param>
</liferay-portlet:renderURL>

<%
User currentUser = themeDisplay.getUser();

String dealerIdValue = (String)currentUser.getExpandoBridge().getAttribute("DealerId");

//System.out.println("dealerIdValue read = " + dealerIdValue);

/*
String[] dealerNameArray = (String[])currentUser.getExpandoBridge().getAttribute("DealerName");
String dealerName = String.join("", dealerNameArray);
System.out.println("dealerName = " + dealerName);
String dealerIDsfromDB = "";

if (!dealerName.isEmpty()) {
	dealerIDsfromDB = DBConnection.getInstance().GetDealerIDs(dealerName);
}

System.out.println("dealerIDsfromDB-rezerva =>>> " + dealerIDsfromDB);
*/

String carNo = ParamUtil.getString(request, "carNo");
String rezTipAuto = ParamUtil.getString(request, "rezTipAuto");
String rezCuloareExt = ParamUtil.getString(request, "rezCuloareExt");
String numeVanzator = currentUser.getFullName();
String userEmail = currentUser.getLogin();
String rezCuloareInt = ParamUtil.getString(request, "rezCuloareInt");

_log.info("Adauga Rezervare JSP - userEmail ==> " + userEmail + " carNo ==> " + carNo + " numeVanzator ==> "
		+ numeVanzator + " dealerIdValue ==> " + dealerIdValue);
%>

<%!private static Log _log = LogFactoryUtil.getLog("htro_actions.adaugaRezervare_jsp"); %>
<liferay-portlet:actionURL name="RezervaAction" var="rezervaActionURL">
	<portlet:param name="carNo" value="<%= carNo %>"></portlet:param>
	<portlet:param name="dealerID" value="<%= dealerIdValue %>"></portlet:param>
	<portlet:param name="userEmail" value="<%= userEmail %>"></portlet:param>
</liferay-portlet:actionURL>

<aui:form action="<%= rezervaActionURL %>" method="POST" name="<portlet:namespace />fm">
	<aui:fieldset-group markupView="lexicon">
		<h3>Rezervare Noua - Introduceti detaliile:</h3>
		<aui:fieldset>
			<aui:select label="Tip Rezervare" name="tipRezervare" required="true" showEmptyOption="true" showRequiredLabel="true">
				<aui:option label="Ferma" value="Ferma" />
				<aui:option label="Temporara" value="Temporara" />
			</aui:select>

			<aui:input label="Nume Client" name="numeClient" title="Nume Client">
				<aui:validator errorMessage="Nume client invalid. Introduceti numele clientului." name="required" />
			</aui:input>

			<aui:input label="Nume Vanzator" name="numeVanzator" title="Nume Vanzator">
				<aui:validator errorMessage="Nume Vanzator invalid. Introduceti numele Vanzatorului." name="required" />
			</aui:input>
			<!-- <form class="form-inline"> -->
				<div style="display: inline;">
					<%-- <aui:input disabled="true" inlineField="true" label="ID Automobil" name="carNo" type="text" value="<%= carNo %>" /> --%>
					<%-- <aui:input disabled="true" inlineField="true" label="Dealer ID" name="dealerID" type="text" value="<%= dealerID %>" /> --%>
					<%-- <aui:input disabled="true" inlineField="true" label="User Email" name="userEmail" type="text" value="<%= userEmail %>" /> --%>
					<p>
						---
						<%= rezTipAuto %>
						---
						<%= rezCuloareExt %>
						---
						<%= rezCuloareInt %>
						---
					</p>
				</div>
			<!-- </form> -->
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" value="Rezerva" />
		<aui:button onClick="<%= backURL.toString() %>" type="cancel" value="Cancel" />
	</aui:button-row>
</aui:form>