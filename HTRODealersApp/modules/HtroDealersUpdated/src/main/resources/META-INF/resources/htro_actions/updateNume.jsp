<%@ include file="../init.jsp"%>

<liferay-ui:success key="detaliirezervareActualizate" message="detaliirezervare-actualizate" />

<liferay-portlet:renderURL var="backURL">
	<portlet:param name="mvcPath" value="/htro_portofoliu/viewPortofoliu.jsp"></portlet:param>
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="UpdateNumeClientAction" var="updateNumeClientActionURL"></liferay-portlet:actionURL>

<%
	User currentUser = themeDisplay.getUser();
	String userEmail = currentUser.getLogin();
	//String numeVanzator = currentUser.getFullName();
	int carNo = ParamUtil.getInteger(request, "carNo");
	System.out.println("updateNume - carNo =>>>" + carNo);
	System.out.println("updateNume - userEmail =>>>" + userEmail);
	//System.out.println("updateNume - numeVanzator =>>>" + numeVanzator);
%>

<aui:form action="<%=updateNumeClientActionURL%>" name="<portlet:namespace />fm">
	<aui:fieldset-group markupView="lexicon">
		<h3>Rezervare Noua - Introduceti detaliile:</h3>
		<aui:fieldset>
			<liferay-ui:message
				key="Introduceti doar datele pe care doriti sa le modificati! (Campurile nemodificate vor ramane cu valorile originale.)" />
			<aui:input name="numeClient" label="Nume Client" title="Nume Client" />
			<aui:input name="numeVanzator" label="Nume Vanzator" title="Nume Vanzator" />
			<aui:input name="carNo" title="ID Automobil" value="<%=carNo%>" type="hidden" />
			<aui:input name="userEmail" title="userEmail" type="hidden" value="<%=userEmail%>" />
		</aui:fieldset>
	</aui:fieldset-group>
	<aui:button-row>
		<aui:button type="submit" value="Actualizati Numele dorite" />
		<aui:button onClick="<%=backURL.toString()%>" type="cancel" value="Cancel" />
	</aui:button-row>
</aui:form>
