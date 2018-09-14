<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@include file="../init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/bs/jszip-2.5.0/dt-1.10.18/b-1.5.2/b-flash-1.5.2/b-html5-1.5.2/fh-3.1.4/datatables.min.css" />

<script type="text/javascript"
	src="https://cdn.datatables.net/v/bs/jszip-2.5.0/dt-1.10.18/b-1.5.2/b-flash-1.5.2/b-html5-1.5.2/fh-3.1.4/datatables.min.js"></script>

<style>
.message-container {
	padding: 10px;
	margin: 2px;
	display: none;
	background: rgba(128, 128, 128, 0.33);
	border: 1px solid #0A0A0C;
}
</style>

<script type="text/javascript">
AUI.$ = jQuery.noConflict();

jQuery(document).ready(function() {
	var table = jQuery('#detaliiDealers').DataTable(
    		{
    			scrollY: 400,
    			lengthMenu: [[50, 75, 100, -1], [50, 75, 100, "All"]],
    		    paging: true,
    		    scrollX: true,
    		    scrollcolapse: true,
    		    pagingType: "full_numbers",
    		    aaSorting: [],
    		    //dom: '<"top"lB>rt<"bottom"ip><"clear">',
    			dom: "<'row'<'col-sm-3'l><'col-sm-6 text-center'><'col-sm-3'>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-5'i><'col-sm-7'p>>",
				fixedHeader: true
			});
});
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>

<body>

	<liferay-portlet:renderURL var="stocURL">
		<portlet:param name="mvcPath" value="/htro_stoc/viewStoc.jsp"></portlet:param>
	</liferay-portlet:renderURL>

	<liferay-portlet:renderURL var="portofoliuURL">
		<portlet:param name="mvcPath" value="/htro_portofoliu/viewPortofoliu.jsp" />
	</liferay-portlet:renderURL>

	<liferay-portlet:actionURL name="createUserGroups" var="createUserGroupsURL" />
	<liferay-portlet:actionURL name="setEmails" var="setEmailsURL" />
	<liferay-portlet:actionURL name="updateUserGroups" var="updateUserGroupsURL" />

	<div style="float: right;">
		<aui:button cssClass="btn btn-info btn-large" onClick="<%=stocURL.toString()%>" value="<%="Inapoi la Stoc"%>" />
		<aui:button cssClass="btn btn-info btn-large" onClick="<%=portofoliuURL.toString()%>" value="<%="Inapoi la Portofoliu"%>" />
	</div>

	<aui:form action="<%=setEmailsURL.toString()%>" method="POST" name="setEmailsAddrs">
		<div class="table-responsive">

			<div style="float: right;">
				<aui:button id="setEmails" type="submit" value="<%="Set Emails"%>" />
			</div>
			<%
				String adrese = "";
				String EmailAddresses = "EmailAddresses";
				Group test = themeDisplay.getLayout().getGroup();
				adrese = (String) test.getExpandoBridge().getAttribute(EmailAddresses);
			%>
			<aui:input id="emailAddresses" type="textarea" name="emailAddresses" autoSize="true"
				label="<%="Adrese Email pentru cererile Proforma si Transport din Portofoliu:"%>" value="<%=adrese%>" />
		</div>
	</aui:form>

	<aui:form action="<%=createUserGroupsURL.toString()%>" method="POST" name="createUserGrps">
		<div style="float: right;">
			<aui:button id="createUserGr" type="submit" value="<%="Creare initiala User Groups"%>" />
		</div>
	</aui:form>

	<aui:form action="<%=updateUserGroupsURL.toString()%>" method="POST" name="updateGroups">
		<div style="float: right;">
			<aui:button id="updateCustomGrFields" type="submit" value="<%="Update Tip Autovehicule"%>" />
		</div>
		<div class="table-responsive-sm">
			<table id="detaliiDealers" class="table table-sm table-bordered nowrap" style="width: 100%">
				<thead>
					<tr>
						<th>Dealer</th>
						<th>Auto</th>
						<th>Moto</th>
						<th>Toate</th>
					</tr>
				</thead>
				<tbody>
					<%
						String tipAutovehicule = "TipAutovehicule";
							Map<String, String> dealers = DBConnection.getInstance().getDealers();
							for (String name : dealers.keySet()) {
								// dealer name is key
								// dealer id is value - also is description for group
								try {
									UserGroup usrGr = UserGroupServiceUtil.getUserGroup(name);
									String[] TipAutovehicule = (String[]) usrGr.getExpandoBridge().getAttribute(tipAutovehicule);
									String tipAutoveh = String.join("", TipAutovehicule);
					%>
					<tr>
						<td><%=name%></td>
						<td style="text-align: center;"><aui:input id="tipAutoveh" type="checkbox"
								checked="<%=tipAutoveh.equalsIgnoreCase("Automobile")%>" name="tipAutoveh" label="<%=""%>" value="<%=name%>" /></td>
						<td style="text-align: center;"><aui:input id="tipMoto" type="checkbox" checked="<%=tipAutoveh.equalsIgnoreCase("Motociclete")%>"
								name="tipMoto" label="<%=""%>" value="<%=name%>" /></td>
						<td style="text-align: center;"><aui:input id="tipToate" type="checkbox" checked="<%=tipAutoveh.equalsIgnoreCase("Toate")%>"
								name="tipToate" label="<%=""%>" value="<%=name%>" /></td>
					</tr>
					<%
						}
								catch (Exception e1) {
									e1.printStackTrace();
								}
							}
					%>
				</tbody>
				<tfoot>
					<tr>
						<th>Dealer</th>
						<th>Auto</th>
						<th>Moto</th>
						<th>Toate</th>
					</tr>
				</tfoot>
			</table>
		</div>
	</aui:form>

</body>
</html>