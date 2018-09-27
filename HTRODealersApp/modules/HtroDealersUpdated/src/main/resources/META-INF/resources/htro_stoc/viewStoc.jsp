<%@include file="../init.jsp"%>

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
	var table = jQuery('#stoc').DataTable(
    		{
    			scrollY: 600,
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

<liferay-ui:success key="rezervareAdaugata" message="rezervare-adaugata" />
<liferay-ui:error key="rezervareNeadaugata" message="rezervare-neadaugata" />

<%
	User currentUser = themeDisplay.getUser();
	String userFullName = currentUser.getFullName();

	//String dealerIdValue = (String) currentUser.getExpandoBridge().getAttribute("DealerId");
	//System.out.println("ViewStoc - dealerIdValue read = " + dealerIdValue);

	String dealerIdValue = "";
	String tipAutoveh = "";
	String tipFinal = "";
	String customField = "TipAutovehicule";
	List<UserGroup> usrGrps = currentUser.getUserGroups();

	if (!usrGrps.isEmpty()) {
		for (UserGroup usrGrp : usrGrps) {
			dealerIdValue += usrGrp.getDescription() + ",";
			String[] TipAutovehicule = (String[]) usrGrp.getExpandoBridge().getAttribute(customField);
			String test = String.join("", TipAutovehicule);
			tipAutoveh += test + ",";
		}

		dealerIdValue = dealerIdValue.substring(0, dealerIdValue.length() - 1);
		tipAutoveh = tipAutoveh.substring(0, tipAutoveh.length() - 1);

		if (tipAutoveh.contains("Motociclete")) {
			tipFinal = "Moto";
			if (tipAutoveh.contains("Automobile")) {
				tipFinal = ""; // gol  == toate 
			}
		} else if (tipAutoveh.contains("Automobile")) {
			tipFinal = "Auto";
		}

		System.out.println("ViewStoc - usrGrps dealerIdValue = " + dealerIdValue);
		System.out.println("ViewStoc - usrGrps custom field = " + tipAutoveh);
		System.out.println("ViewStoc - usrGrps custom field Final = " + tipFinal);
	}

	List<Role> userRoles = currentUser.getRoles();

	boolean isAdmin = false;

	for (Role role : userRoles) {
		if ("Administrator".equalsIgnoreCase(role.getName())) {
			System.out.println("Logged in user is Admin");
			isAdmin = true;
		}
	}

	String noResults = "Nu s-au gasit automobile in baza de date conform filtrelor aplicate.";
	String tipAuto = (String) ParamUtil.getString(request, "tipAuto");
	String culoareExterior = (String) ParamUtil.getString(request, "culoareExterior");

	List<StocItem> stocItems1 = new ArrayList();

	//System.out.println("TimeInfo before select = " + LocalTime.now(ZoneId.of("Europe/Bucharest")));

	if (tipAuto.equalsIgnoreCase("SelectAll")) {
		if (culoareExterior.equalsIgnoreCase("SelectAll")) {
			stocItems1 = DBConnection.getInstance().GetFilteredStocItems("", "", tipFinal);
		} else {
			stocItems1 = DBConnection.getInstance().GetFilteredStocItems("", culoareExterior, tipFinal);
		}
	} else {
		if (culoareExterior.equalsIgnoreCase("SelectAll")) {
			stocItems1 = DBConnection.getInstance().GetFilteredStocItems(tipAuto, "", tipFinal);
		} else {
			stocItems1 = DBConnection.getInstance().GetFilteredStocItems(tipAuto, culoareExterior, tipFinal);
		}
	}

	//System.out.println("TimeInfo after select = " + LocalTime.now(ZoneId.of("Europe/Bucharest")));

	int listSize = stocItems1.size();

	boolean validateStocView = false;

	if (!tipAuto.equalsIgnoreCase("")) {
		validateStocView = true;
	}

	boolean validDealerId = !dealerIdValue.contains(",");
%>

<liferay-portlet:resourceURL id="getTipAuto" var="getTipAutoURL" />

<liferay-portlet:renderURL varImpl="cautaURL">
	<liferay-portlet:param name="dealerIdValue" value="<%=dealerIdValue%>" />
	<liferay-portlet:param name="mvcPath" value="/htro_stoc/viewStoc.jsp" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="portofoliuURL">
	<portlet:param name="mvcPath" value="/htro_portofoliu/viewPortofoliu.jsp" />
</liferay-portlet:renderURL>

<liferay-portlet:renderURL var="adminURL">
	<portlet:param name="mvcPath" value="/htro_admin/adminActions.jsp"></portlet:param>
</liferay-portlet:renderURL>

<%-- <liferay-portlet:renderURL var="stocV2URL">	<portlet:param name="mvcPath" value="/htro_stoc/viewStocV2.jsp" /></liferay-portlet:renderURL> --%>

<div class="container-fluid">

	<aui:form action="<%=cautaURL%>" method="get" name="searchForm">

		<liferay-portlet:renderURLParams varImpl="cautaURL" />

		<div style="float: left; display: inline-block;">

			<div id="tipAutodiv" style="display: inline-block;">

				<aui:select id="tipAuto" inlineLabel="left" label="Tip Autovehicul" name="tipAuto" showEmptyOption="false"
					onChange="<%=renderResponse.getNamespace() + "getTipAuto()"%>" value="InputSomething">

					<aui:option selected="false" label="Afiseaza Toate Tipurile" value="SelectAll" />
					<%
						List<String> tipAutoList = DBConnection.getInstance().GetStocTipAuto(tipFinal);
								for (String tipa : tipAutoList) {
					%>
					<aui:option label="<%=tipa%>" value="<%=tipa%>" />
					<%
						}
					%>
				</aui:select>

			</div>

			<div id="culoareExteriorDiv" style="display: inline-block;">

				<aui:select id="culoareExterior" inlineLabel="left" label="Culoare Exterior" name="culoareExterior" showEmptyOption="false">

					<aui:option selected="false" label="Afiseaza Toate Culorile" value="SelectAll" />
					<%
						List<String> culoareExteriorList = DBConnection.getInstance().GetStocCuloareExt(tipAuto, tipFinal);
								for (String culoareExt : culoareExteriorList) {
					%>
					<aui:option label="<%=culoareExt%>" value="<%=culoareExt%>" />
					<%
						}
					%>

				</aui:select>

			</div>

		</div>

		<div style="float: right; display: inline-block;">

			<aui:button cssClass="btn btn-primary btn-large" type="submit" value="Filtreaza" />
			<%-- 			<aui:button cssClass="btn btn-info btn-large" onClick="<%=stocV2URL.toString()%>" value="Deschide StocV2" /> --%>
			<aui:button cssClass="btn btn-info btn-large" onClick="<%=portofoliuURL.toString()%>" value="Deschide Portofoliu" />
			<c:if test="<%=isAdmin%>">
				<aui:button cssClass="btn btn-info btn-large" onClick="<%=adminURL.toString()%>" value="Deschide Pagina Administrare" />
			</c:if>
		</div>

	</aui:form>

</div>

<aui:script>

Liferay.provide(
	    window,
	    '<portlet:namespace />getTipAuto',
	    function() {

	        var A = AUI();
	        var fetchTipAutoURL = '<%=getTipAutoURL.toString()%>';
	        
	        //console.log(fetchTipAutoURL);
	        
	        // selecting the sourceSelect drop-down to get the current value
	        var sourceElement = A.one("#<portlet:namespace />tipAuto");
	        
	        // selecting the targetSelect drop-down to populate values
	        var targetElement = A.one("#<portlet:namespace />culoareExterior");
	        
	        //console.log('tipAuto: ' +  sourceElement.val());
	        
	        A.io.request (
	            // the resource URL to fetch words
	           fetchTipAutoURL, {
	            data: {
	                // request parameters to be sent to the Server
	                <portlet:namespace />tipAuto1: sourceElement.val()
	            },
	            dataType: 'json',
	            on: {
	                    failure: function() {
	                        // if there was some error at the server
							// alert("Tipul Auto selectat este gol! Selectati un Tip Auto inainte de filtrare.");
	                        console.log("there was some error at the server...");
	                    },
	                    success: function(event, id, obj) {
	                    	 //clear the content of select
	                    	targetElement.html("");
	                        // JSON Data recieved from Server
	                        var culoareExteriorArray = this.get('responseData');
	                        
	                        //console.log(culoareExteriorArray);
	                        
	                        // crude javascript magic to populate the drop-down
	                        //targetElement.append("<option value='SelectAll'>Afiseaza Toate Culorile</option>");
	                        for (var j=0; j < culoareExteriorArray.length; j++) {
	                        	if ( culoareExteriorArray[j] === ("SelectAll") )
	                        		{
	                        		targetElement.append("<option value='" + culoareExteriorArray[j] + "'> Afiseaza Toate Culorile </option>");
	                        		}
	                        	else
	                        		{
		                            targetElement.append("<option value='" + culoareExteriorArray[j] + "'>" + culoareExteriorArray[j] + "</option>");
	                        		}
	                        }
	                    }
	                }
	            }
	        ); 
	    },
	    ['aui-io']
	);
</aui:script>

<div class="container-fluid">

	<c:if test="<%=validateStocView%>">
		<c:if test="<%=stocItems1 != null && !stocItems1.isEmpty()%>">

			<table id="stoc" class="table table-striped table-bordered nowrap" style="width: 100%">
				<thead>
					<tr>
						<th>Rezerva</th>
						<th>An Fabricatie CIV</th>
						<th>Tip Autovehicul</th>
						<th>Cod Culoare Exterior</th>
						<th>Culoare Exterior</th>
						<th>Vopsea Metalizata</th>
						<th>Culoare Interior</th>
						<th>Observatii</th>
						<th>Locatie</th>
						<th>Sosire in tara</th>
						<th>Expirare rezervare</th>
						<th>Expirare rezervare vehicul alternativ</th>
						<th>(*) Locatie vehicul alternativ</th>
						<th>Sosire in tara veh. alternativ</th>
						<th>Omologare Individuala</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (StocItem stocItem : stocItems1) {

									// System.out.println("TimeInfo before = " + LocalTime.now(ZoneId.of("Europe/Bucharest")));
									String htroCarNo = String.valueOf(stocItem.getHTRO_CAR_NO());
									String rezervata = String.valueOf(stocItem.getREZERVATA());
									String reservation_end_date = "";
									String location = "";
									String arrival_date = "";

									List<String> detaliiRez = DBConnection.getInstance().GetSecondRezDetails(htroCarNo);

									if (!detaliiRez.isEmpty()) {
										reservation_end_date = detaliiRez.get(0);
										location = detaliiRez.get(1);
										arrival_date = detaliiRez.get(2);
									}
									// 	System.out.println("TimeInfo after = " + LocalTime.now(ZoneId.of("Europe/Bucharest")));

									// current time in UTC+2 time zone
									LocalTime nowInUtc = LocalTime.now(ZoneId.of("Europe/Bucharest"));

									LocalTime endTime = LocalTime.of(20, 00);
									LocalTime startTime = LocalTime.of(9, 0);

									boolean validateAction = false;
									boolean showTime = false;
									//validDealerId
									if (rezervata.equalsIgnoreCase("N")) {
										if ((nowInUtc.isAfter(endTime)) || (nowInUtc.isBefore(startTime))) {
											validateAction = false;
											showTime = true;
										}
										else {
											validateAction = true;
											showTime = false;
										}
									}
					%>
					<tr>
						<td><c:if test="<%=showTime || validDealerId%>">
								<span style="white-space: nowrap"> Rezervare dezactivata!! </span>
							</c:if> <c:if test="<%=validateAction && validDealerId%>">

								<liferay-portlet:renderURL var="rezervaURL">
									<portlet:param name="carNO" value="<%=String.valueOf(stocItem.getHTRO_CAR_NO())%>" />
									<portlet:param name="rezTipAuto" value="<%=String.valueOf(stocItem.getTIP_AUTOVEHICUL())%>" />
									<portlet:param name="rezCuloareExt" value="<%=String.valueOf(stocItem.getDESC_CULOARE_EXTERIOR())%>" />
									<portlet:param name="rezCuloareInt" value="<%=String.valueOf(stocItem.getCULOARE_INTERIOR())%>" />
									<portlet:param name="numeVanzator" value="<%=userFullName%>" />
									<liferay-portlet:param name="mvcPath" value="/htro_actions/adaugaRezervare.jsp" />
								</liferay-portlet:renderURL>

								<span style="white-space: nowrap"> <liferay-ui:icon iconCssClass="icon-plus-sign" label="true" message="Rezerva"
										url="<%=rezervaURL.toString()%>" />
								</span>
							</c:if></td>
						<td><%=stocItem.getAN_FABRICATIE_CIV()%></td>
						<td><%=stocItem.getTIP_AUTOVEHICUL()%></td>
						<td><%=stocItem.getCOD_CULOARE_EXTERIO()%></td>
						<td><%=stocItem.getDESC_CULOARE_EXTERIOR()%></td>
						<td><%=stocItem.getVOPSEA_METALIZATA()%></td>
						<td><%=stocItem.getCULOARE_INTERIOR()%></td>
						<td><%=stocItem.getOBSERVATII()%></td>
						<td><%=stocItem.getLOCATIE()%></td>
						<td><%=stocItem.getLUNA_SOSIRE_IN_TARA()%></td>
						<td><%=stocItem.getDATA_EXPIRARE_REZ()%></td>
						<td><%=reservation_end_date%></td>
						<td><%=location%></td>
						<td><%=arrival_date%></td>
						<td><%=stocItem.getOMOLOGARE_IND()%></td>
					</tr>
					<%
						}
					%>
				</tbody>
				<tfoot>
					<tr>
						<th>Rezerva</th>
						<th>An Fabricatie CIV</th>
						<th>Tip Autovehicul</th>
						<th>Cod Culoare Exterior</th>
						<th>Culoare Exterior</th>
						<th>Vopsea Metalizata</th>
						<th>Culoare Interior</th>
						<th>Observatii</th>
						<th>Locatie</th>
						<th>Sosire in tara</th>
						<th>Expirare rezervare</th>
						<th>Expirare rezervare vehicul alternativ</th>
						<th>(*) Locatie vehicul alternativ</th>
						<th>Sosire in tara veh. alternativ</th>
						<th>Omologare Individuala</th>
					</tr>
				</tfoot>
			</table>
			<br>
			<p>(*) Pentru locatie Gent/Ontruck termen de sosire in Pitesti - 30 de zile de la data plasarii comenzii ferme!</p>

		</c:if>
	</c:if>

	<c:if test="<%=Validator.isNull(stocItems1) || stocItems1.isEmpty()%>">
		<div class="message-container"><%=noResults%></div>
	</c:if>

</div>