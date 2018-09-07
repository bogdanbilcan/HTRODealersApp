<%@ include file="../init.jsp" %>
<liferay-ui:success key="rezervareAnulata" message="rezervare-anulata" />

<liferay-portlet:renderURL var="backURL">
	<portlet:param name="mvcPath" value="/htro_portofoliu/viewPortofoliu.jsp"></portlet:param>
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="AnulareRezervareAction" var="AnulareRezervareActionURL"></liferay-portlet:actionURL>

<%
User currentUser = themeDisplay.getUser();

String userEmail = currentUser.getLogin();

int carNo = ParamUtil.getInteger(request, "carNo");
String tipRezervare = ParamUtil.getString(request, "tipRezervare");
boolean validateAction = false;

if (tipRezervare.equalsIgnoreCase("Temporara")) {
	validateAction = true;
	//System.out.println("anulareRezervare - tipRezervare =>>>" + tipRezervare);
}
//System.out.println("anulareRezervare - carNo =>>>" + carNo);
//System.out.println("anulareRezervare - userEmail =>>>" + userEmail);

_log.info("AnulareRezervare JSP - userEmail ==> " + userEmail + " carNo ==> " + carNo);
%>

<%!private static Log _log = LogFactoryUtil.getLog("htro_actions.anulareRezervare_jsp"); %>
<aui:form action="<%= AnulareRezervareActionURL %>" name="<portlet:namespace />fm">
	<aui:fieldset-group markupView="lexicon">
		<h3>Anulare Rezervare:</h3>
		<aui:fieldset>
			<div class="alert alert-danger">
				<strong class="lead">ATENTIE: Actiune ireversibila !! </strong>
				<p>
					Apasati <strong> Anulati Rezervarea </strong> pentru a continua sau <strong> Cancel </strong> pentru a renunta!
				</p>
			</div>

			<aui:input name="userEmail" title="userEmail" type="hidden" value="<%= userEmail %>" />
			<aui:input name="carNo" title="ID Automobil" type="hidden" value="<%= carNo %>" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button type="submit" value="Anulati Rezervarea" />
		<aui:button onClick="<%= backURL.toString() %>" type="cancel" value="Cancel" />
	</aui:button-row>
</aui:form>