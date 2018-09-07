<%@page import="com.liferay.portal.kernel.model.User"%>
<%@page import="com.liferay.portal.kernel.service.UserLocalServiceUtil"%>

<%@include file="../init.jsp"%>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

<style>
.message-container {
	padding: 10px;
	margin: 2px;
	display: none;
	background: rgba(128, 128, 128, 0.33);
	border: 1px solid #0A0A0C;
}
</style>
<h2>Simple Jquery Data Table with Zero Configuration</h2>

<liferay-portlet:renderURL var="portofoliuURL">
	<portlet:param name="mvcPath" value="/htro_portofoliu/viewPortofoliu.jsp" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="stocURL">
	<portlet:param name="mvcPath" value="/htro_stoc/viewStoc.jsp" />
</liferay-portlet:renderURL>

<div style="float: right;">
	<aui:button cssClass="btn btn-info btn-large" onClick="<%=portofoliuURL.toString()%>" value="Deschide Portofoliu" />
	<aui:button cssClass="btn btn-info btn-large" onClick="<%=stocURL.toString()%>" value="Deschide Stoc" />
</div>

<%
List<User> userList=UserLocalServiceUtil.getUsers(-1,-1);
%>
<c:if test="<%=userList!=null&&!userList.isEmpty() %>">
	<table id="userTable" class="display nowrap" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>User Id</th>
				<th>Screen Name</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email Address</th>
				<th>Job Title</th>
				<th>User Id</th>
				<th>Screen Name</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email Address</th>
				<th>Job Title</th>
				<th>User Id</th>
				<th>Screen Name</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email Address</th>
				<th>Job Title</th>
				<th>User Id</th>
				<th>Screen Name</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email Address</th>
				<th>Job Title</th>
			</tr>
		</thead>

		<tbody>
			<%for(User curUser:userList){ %>
			<tr>
				<td><%=curUser.getUserId()%></td>
				<td><%=curUser.getScreenName()%></td>
				<td><%=curUser.getFirstName()%></td>
				<td><%=curUser.getLastName()%></td>
				<td><%=curUser.getEmailAddress()%></td>
				<td><%=curUser.getJobTitle()%></td>
				<td><%=curUser.getUserId()%></td>
				<td><%=curUser.getScreenName()%></td>
				<td><%=curUser.getFirstName()%></td>
				<td><%=curUser.getLastName()%></td>
				<td><%=curUser.getEmailAddress()%></td>
				<td><%=curUser.getJobTitle()%></td>
				<td><%=curUser.getUserId()%></td>
				<td><%=curUser.getScreenName()%></td>
				<td><%=curUser.getFirstName()%></td>
				<td><%=curUser.getLastName()%></td>
				<td><%=curUser.getEmailAddress()%></td>
				<td><%=curUser.getJobTitle()%></td>
				<td><%=curUser.getUserId()%></td>
				<td><%=curUser.getScreenName()%></td>
				<td><%=curUser.getFirstName()%></td>
				<td><%=curUser.getLastName()%></td>
				<td><%=curUser.getEmailAddress()%></td>
				<td><%=curUser.getJobTitle()%></td>
			</tr>
			<%} %>
		</tbody>
		<tfoot>
			<tr>
				<th>User Id</th>
				<th>Screen Name</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email Address</th>
				<th>Job Title</th>
				<th>User Id</th>
				<th>Screen Name</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email Address</th>
				<th>Job Title</th>
				<th>User Id</th>
				<th>Screen Name</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email Address</th>
				<th>Job Title</th>
				<th>User Id</th>
				<th>Screen Name</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email Address</th>
				<th>Job Title</th>
			</tr>
		</tfoot>
	</table>
	<script type="text/javascript">
    $(document).ready(function() {
        $('#userTable').DataTable(
        		{
        			scrollY: 300,
        		    paging: true,
        		    scrollX: true
        		});
    } );
    </script>
</c:if>
<c:if test="<%=Validator.isNull(userList)||userList.isEmpty()%>">
	<div class="message-container">No data to Display</div>
</c:if>