<%@ include file="../init.jsp" %>
<!-- <div class="container-fluid"> -->
	<liferay-ui:success key="rezervareAdaugata" message="rezervare-adaugata" />
	<liferay-ui:error key="rezervareNeadaugata" message="rezervare-neadaugata" />

	<%
	/*
		User currentUser = themeDisplay.getUser();

		String userEmail = currentUser.getLogin();

		_log.info("View Stoc - userEmail ==> ", userEmail);
	*/
	String noResults = "Nu s-au gasit automobile in baza de date conform filtrelor aplicate.";

	String tipAuto = (String)ParamUtil.getString(request, "tipAuto");
	String culoareExterior = (String)ParamUtil.getString(request, "culoareExterior");
	%>

	<liferay-portlet:renderURL varImpl="iteratorURL">
		<portlet:param name="tipAuto" value="<%= tipAuto %>" />
		<portlet:param name="culoareExterior" value="<%= culoareExterior %>" />
		<portlet:param name="mvcPath" value="/htro_stoc/viewStoc.jsp" />
	</liferay-portlet:renderURL>

	<liferay-portlet:resourceURL id="getTipAuto" var="getTipAutoURL" />

	<liferay-portlet:renderURL varImpl="cautaURL">
		<portlet:param name="mvcPath" value="/htro_stoc/viewStoc.jsp" />
	</liferay-portlet:renderURL>

	<liferay-portlet:renderURL var="portofoliuURL">
		<portlet:param name="mvcPath" value="/htro_portofoliu/viewPortofoliu.jsp" />
	</liferay-portlet:renderURL>
	<!-- <form class="form-inline"> -->
		<!-- cssClass="form-inline" -->
		<aui:form action="<%= cautaURL %>" cssClass="form-inline" method="get" name="searchForm">
			<%-- <liferay-ui:header title="Filtrare Lista Automobile" /> --%>
			<liferay-portlet:renderURLParams varImpl="cautaURL" />
			<!-- <div style="display: inline;"> -->
				<div style="float: left;">
					<aui:select id="tipAuto" inlineField="true" label="Tip Autovehicul" name="tipAuto" showEmptyOption="false"
						onChange="<%= renderResponse.getNamespace() + "getTipAuto()" %>" value="InputSomething">
						<aui:option label="Afiseaza Toate Tipurile" selected="false" value="SelectAll" />

						<%
						List<String> tipAutoList = DBConnection.getInstance().GetStocTipAuto();

								for (String tipa : tipAutoList) {
						%>

							<aui:option label="<%= tipa %>" value="<%= tipa %>" />

						<%
						}
						%>

					</aui:select>

					<aui:select id="culoareExterior" inlineField="true" label="Culoare Exterior" name="culoareExterior"
						showEmptyOption="false">
						<aui:option label="Afiseaza Toate Culorile" selected="false" value="SelectAll" />

						<%
						List<String> culoareExteriorList = DBConnection.getInstance().GetStocCuloareExt(tipAuto);

								for (String culoareExt : culoareExteriorList) {
						%>

							<aui:option label="<%= culoareExt %>" value="<%= culoareExt %>" />

						<%
						}
						%>

					</aui:select>
				</div>

				<div style="float: right;">
					<aui:button cssClass="btn btn-large btn-primary" type="submit" value="Filtreaza" />
					<aui:button cssClass="btn btn-info btn-large" onClick="<%= portofoliuURL.toString() %>" value="Deschide Portofoliu" />
				</div>
			<!-- </div> -->
		</aui:form>

		<aui:script>
		Liferay.provide(
				window,
				'<portlet:namespace />getTipAuto',
				function() {

					var A = AUI();
					var fetchTipAutoURL = '<%= getTipAutoURL.toString() %>';
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

	<!-- </form> -->

	<%
	List<StocItem> stocItems1 = new ArrayList();

	// 			System.out.println("TimeInfo before select = " + LocalTime.now(ZoneId.of("Europe/Bucharest")));

	if (tipAuto.equalsIgnoreCase("SelectAll")) {
		if (culoareExterior.equalsIgnoreCase("SelectAll")) {
			stocItems1 = DBConnection.getInstance().GetFilteredStocItems("", "");
		} else {
			stocItems1 = DBConnection.getInstance().GetFilteredStocItems("", culoareExterior);
		}
	} else {
		if (culoareExterior.equalsIgnoreCase("SelectAll")) {
			stocItems1 = DBConnection.getInstance().GetFilteredStocItems(tipAuto, "");
		} else {
			stocItems1 = DBConnection.getInstance().GetFilteredStocItems(tipAuto, culoareExterior);
		}
	}

	// 			System.out.println("TimeInfo after select = " + LocalTime.now(ZoneId.of("Europe/Bucharest")));

	int listSize = stocItems1.size();

	boolean validateStocView = false;

	if (!("".equalsIgnoreCase(tipAuto))) {
		validateStocView = true;
	}
	%>

	<c:if test="<%= validateStocView %>">
		<div style="clear: both;">
			<div class="container-fluid">
				<liferay-ui:search-container delta="20" emptyResultsMessage="<%= noResults %>" iteratorURL="<%= iteratorURL %>"
					total="<%= listSize %>" deltaConfigurable="true">
					<liferay-ui:search-container-results>

						<%
						results = ListUtil.subList(stocItems1, searchContainer.getStart(), searchContainer.getEnd());
									total = listSize;
									searchContainer.setTotal(total);
									searchContainer.setResults(results);
									iteratorURL.setParameter("cur", searchContainer.getCurParam());
						%>

					</liferay-ui:search-container-results>

					<liferay-ui:search-container-row className="StocItem" keyProperty="HTRO_CAR_NO" modelVar="stocItem"
						escapedModel="true">
						<liferay-ui:search-container-column-jsp align="left" name="Actiuni" path="/htro_actions/butonRezerva.jsp" />
						<%-- <liferay-ui:search-container-column-text property="HTRO_CAR_NO" /> --%>
						<%-- <liferay-ui:search-container-column-text property="RES_DEALER_ID" /> --%>
						<%-- <liferay-ui:search-container-column-text name="An Fabricatie CIV" property="AN_FABRICATIE_CIV" truncate="true" /> --%>
						<liferay-ui:search-container-column-text name="An Fabricatie CIV">
							<span style="white-space: nowrap"><%= stocItem.getAN_FABRICATIE_CIV() %></span>
						</liferay-ui:search-container-column-text>
						<%-- <liferay-ui:search-container-column-text name="Tip Autovehicul" property="TIP_AUTOVEHICUL" truncate="true" /> --%>
						<liferay-ui:search-container-column-text align="center" name="Tip Autovehicul" title="Tip Autovehicul">
							<span style="white-space: nowrap"><%= stocItem.getTIP_AUTOVEHICUL() %></span>
						</liferay-ui:search-container-column-text>
						<%-- <liferay-ui:search-container-column-text name="Cod Culoare Exterior" property="COD_CULOARE_EXTERIO" truncate="true" /> --%>
						<liferay-ui:search-container-column-text name="Cod Culoare Exterior">
							<span style="white-space: nowrap"><%= stocItem.getCOD_CULOARE_EXTERIO() %></span>
						</liferay-ui:search-container-column-text>
						<%-- <liferay-ui:search-container-column-text name="Culoare Exterior" property="DESC_CULOARE_EXTERIOR" truncate="true" /> --%>
						<liferay-ui:search-container-column-text name="Culoare Exterior">
							<span style="white-space: nowrap"><%= stocItem.getDESC_CULOARE_EXTERIOR() %></span>
						</liferay-ui:search-container-column-text>
						<%-- <liferay-ui:search-container-column-text name="Vopsea Metalizata" property="VOPSEA_METALIZATA" truncate="true" /> --%>
						<liferay-ui:search-container-column-text name="Vopsea Metalizata" truncate="true">
							<span style="white-space: nowrap"><%= stocItem.getVOPSEA_METALIZATA() %></span>
						</liferay-ui:search-container-column-text>
						<%-- <liferay-ui:search-container-column-text name="Culoare Interior" property="CULOARE_INTERIOR" truncate="true" /> --%>
						<liferay-ui:search-container-column-text name="Culoare Interior">
							<span style="white-space: nowrap"><%= stocItem.getCULOARE_INTERIOR() %></span>
						</liferay-ui:search-container-column-text>
						<%-- <liferay-ui:search-container-column-text name="Observatii" property="OBSERVATII" truncate="true" /> --%>
						<liferay-ui:search-container-column-text name="Observatii">
							<span style="white-space: nowrap"><%= stocItem.getOBSERVATII() %></span>
						</liferay-ui:search-container-column-text>
						<%-- <liferay-ui:search-container-column-text name="Locatie" property="LOCATIE" truncate="true" /> --%>
						<liferay-ui:search-container-column-text name="(*) Locatie">
							<span style="white-space: nowrap"><%= stocItem.getLOCATIE() %></span>
						</liferay-ui:search-container-column-text>
						<%-- <liferay-ui:search-container-column-text name="Omologare Individuala" property="OMOLOGARE_IND" truncate="true" /> --%>
						<liferay-ui:search-container-column-text name="Omologare">
							<span style="white-space: nowrap"><%= stocItem.getOMOLOGARE_IND() %></span>
						</liferay-ui:search-container-column-text>
						<%-- <liferay-ui:search-container-column-text name="Luna sosire in tara" property="LUNA_SOSIRE_IN_TARA" truncate="true" /> --%>
						<liferay-ui:search-container-column-text name="Sosire in tara">
							<span style="white-space: nowrap"><%= stocItem.getLUNA_SOSIRE_IN_TARA() %></span>
						</liferay-ui:search-container-column-text>
						<%-- <liferay-ui:search-container-column-text name="Rezervata" property="REZERVATA" truncate="true" /> --%>
						<%-- <liferay-ui:search-container-column-text name="Data expirare rezervare" property="DATA_EXPIRARE_REZ" truncate="true" /> --%>

						<%--
						<liferay-ui:search-container-column-text name="Expirare rezervare">
							<span style="white-space: nowrap"><%= stocItem.getDATA_EXPIRARE_REZ() %></span>
						</liferay-ui:search-container-column-text>
						--%>

						<%

						// System.out.println("TimeInfo before = " + LocalTime.now(ZoneId.of("Europe/Bucharest")));

									String htroCarNo = String.valueOf(stocItem.getHTRO_CAR_NO());
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
						%>

						<liferay-ui:search-container-column-text name="Expirare rezervare vehicul alternativ">
							<span style="white-space: nowrap"><%= reservation_end_date %></span>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text name="(*) Locatie vehicul alternativ">
							<span style="white-space: nowrap"><%= location %></span>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text title="Sosire in tara veh. alternativ"
							name="Sosire in tara veh. alternativ">
							<span style="white-space: nowrap"><%= arrival_date %></span>
						</liferay-ui:search-container-column-text>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator />
					<%-- <liferay-ui:search-iterator displayStyle='<%= "list" %>' markupView="lexicon" /> --%>
				</liferay-ui:search-container>

				<p>(*) Pentru locatie Gent/Ontruck termen de sosire in Pitesti - 30 de zile de la data plasarii comenzii ferme!</p>
			</div>
		</div>
	</c:if>
<!-- </div> -->