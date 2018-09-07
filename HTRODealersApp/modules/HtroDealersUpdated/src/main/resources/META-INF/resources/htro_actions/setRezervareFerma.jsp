<%@ include file="../init.jsp"%>

<liferay-ui:success key="rezervareActualizata" message="rezervare-actualizata" />

<liferay-portlet:renderURL var="backURL">
	<portlet:param name="mvcPath" value="/htro_portofoliu/viewPortofoliu.jsp"></portlet:param>
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="SetareRezervareFermaAction" var="SetareRezervareFermaActionURL"></liferay-portlet:actionURL>

<%
	User currentUser = themeDisplay.getUser();
	String userEmail = currentUser.getLogin();
	int carNo = ParamUtil.getInteger(request, "carNo");
	System.out.println("setareRezervare - carNo =>>>" + carNo);
	System.out.println("setareRezervare - userEmail =>>>" + userEmail);
%>

<aui:form action="<%=SetareRezervareFermaActionURL%>" name="<portlet:namespace />fm">
	<aui:fieldset-group markupView="lexicon">
		<h3>Setare Tip Rezervare: Definitiva</h3>
		<aui:fieldset>

			<div class="alert alert-danger">
				<strong class="lead">ATENTIE: Actiune ireversibila !!</strong>
				<p>
					Apasati <strong>Setati Rezervarea</strong> pentru a continua sau <strong> Cancel </strong> pentru a renunta!
				</p>
			</div>

			<aui:input name="carNo" title="ID Automobil" value="<%=carNo%>" type="hidden" />
			<aui:input name="userEmail" title="userEmail" type="hidden" value="<%=userEmail%>" />

		</aui:fieldset>
	</aui:fieldset-group>
	<aui:button-row>
		<aui:button type="submit" value="Setati Rezervare Ferma" />
		<aui:button onClick="<%=backURL.toString()%>" type="cancel" value="Cancel" />
	</aui:button-row>
</aui:form>
