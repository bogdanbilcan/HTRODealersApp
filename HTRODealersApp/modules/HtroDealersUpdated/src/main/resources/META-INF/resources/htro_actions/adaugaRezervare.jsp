<%@ include file="../init.jsp"%>

<liferay-ui:success key="rezervareAdaugata" message="rezervare-adaugata" />

<liferay-portlet:renderURL var="backURL">
	<portlet:param name="mvcPath" value="/htro_stoc/viewStoc.jsp"></portlet:param>
</liferay-portlet:renderURL>

<%
	User currentUser = themeDisplay.getUser();
	String dealerIdValue = (String) currentUser.getExpandoBridge().getAttribute("DealerId");
	System.out.println("dealerIdValue read = " + dealerIdValue);

	/*
	String[] dealerNameArray = (String[]) currentUser.getExpandoBridge().getAttribute("DealerName");
	String dealerName = String.join("", dealerNameArray);
	System.out.println("dealerName = " + dealerName);
	String dealerIDsfromDB = "";
	
	if (!dealerName.isEmpty()) {
		dealerIDsfromDB = DBConnection.getInstance().GetDealerIDs(dealerName);
	}
	System.out.println("dealerIDsfromDB-rezerva =>>> " + dealerIDsfromDB);
	*/

	String carNo = ParamUtil.getString(request, "carNO");
	String rezTipAuto = ParamUtil.getString(request, "rezTipAuto");
	String rezCuloareExt = ParamUtil.getString(request, "rezCuloareExt");
	String numeVanzator = currentUser.getFullName();
	String userEmail = currentUser.getLogin();
	String rezCuloareInt = ParamUtil.getString(request, "rezCuloareInt");

	System.out.println("userEmail-rezerva ==> " + userEmail);
	System.out.println("carNo-rezerva =>>> " + carNo);
	System.out.println("numeVanzator-rezerva =>>> " + numeVanzator);
%>

<liferay-portlet:actionURL name="RezervaAction" var="rezervaActionURL">
	<portlet:param name="carNo" value="<%=carNo%>"></portlet:param>
	<portlet:param name="dealerID" value="<%=dealerIdValue%>"></portlet:param>
	<portlet:param name="userEmail" value="<%=userEmail%>"></portlet:param>
</liferay-portlet:actionURL>

<aui:form action="<%=rezervaActionURL%>" method="POST" name="<portlet:namespace />fm">
	<aui:fieldset-group markupView="lexicon">
		<h3>Rezervare Noua - Introduceti detaliile:</h3>
		<aui:fieldset>
			<aui:select label="Tip Rezervare" name="tipRezervare" showEmptyOption="true" required="true" showRequiredLabel="true">
				<aui:option label="Ferma" value="Ferma" />
				<aui:option label="Temporara" value="Temporara" />
			</aui:select>
			<aui:input name="numeClient" label="Nume Client" title="Nume Client">
				<aui:validator name="required" errorMessage="Nume client invalid. Introduceti numele clientului." />
			</aui:input>
			<aui:input name="numeVanzator" label="Nume Vanzator" title="Nume Vanzator">
				<aui:validator name="required" errorMessage="Nume Vanzator invalid. Introduceti numele Vanzatorului." />
			</aui:input>
			<!-- 			<form class="form-inline"> -->
			<div style="display: inline;">
				<%-- 					<aui:input inlineField="true" name="carNo" label="ID Automobil" type="text" disabled="true" value="<%=carNo%>" /> --%>
				<%-- 					<aui:input inlineField="true" name="dealerID" label="Dealer ID" type="text" disabled="true" value="<%=dealerID%>" /> --%>
				<%-- 					<aui:input inlineField="true" name="userEmail" label="User Email" type="text" disabled="true" value="<%=userEmail%>" /> --%>
				<p>
					---
					<%=rezTipAuto%>
					---
					<%=rezCuloareExt%>
					---
					<%=rezCuloareInt%>
					---
				</p>
			</div>
			<!-- 			</form> -->
		</aui:fieldset>
	</aui:fieldset-group>
	<aui:button-row>
		<aui:button type="submit" value="Rezerva" />
		<aui:button onClick="<%=backURL.toString()%>" type="cancel" value="Cancel" />
	</aui:button-row>
</aui:form>
