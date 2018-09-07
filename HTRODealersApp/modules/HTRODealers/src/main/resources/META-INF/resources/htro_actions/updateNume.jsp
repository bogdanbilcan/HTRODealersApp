<%@ include file="../init.jsp" %>
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

_log.info("Update Nume Rezervare JSP - userEmail ==> " + userEmail + " carNo ==> " + carNo);
%>

<%!private static Log _log = LogFactoryUtil.getLog("htro_actions.updateNume_jsp"); %>
<aui:form action="<%= updateNumeClientActionURL %>" name="<portlet:namespace />fm">
	<aui:fieldset-group markupView="lexicon">
		<h3>Rezervare Noua - Introduceti detaliile:</h3>
		<aui:fieldset>
			<liferay-ui:message
				key="Introduceti doar datele pe care doriti sa le modificati! (Campurile nemodificate vor ramane cu valorile originale.)"
			/>

			<aui:input label="Nume Client" name="numeClient" title="Nume Client" />
			<aui:input label="Nume Vanzator" name="numeVanzator" title="Nume Vanzator" />
			<aui:input name="carNo" title="ID Automobil" type="hidden" value="<%= carNo %>" />
			<aui:input name="userEmail" title="userEmail" type="hidden" value="<%= userEmail %>" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" value="Actualizati Numele dorite" />
		<aui:button onClick="<%= backURL.toString() %>" type="cancel" value="Cancel" />
	</aui:button-row>
</aui:form>