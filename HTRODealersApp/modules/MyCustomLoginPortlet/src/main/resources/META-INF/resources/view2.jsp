<%@ include file="/init.jsp"%>

<p>
	<b><liferay-ui:message key="myloginportlet_MyLogin.caption" /></b>
</p>

<%
	boolean codeVerified = false;
	String password = actionRequest.getParameter("password");
%>

<c:choose>
	<c:when test="<%=themeDisplay.isSignedIn() && codeVerified%>">

		<%
			String signedInAs = HtmlUtil.escape(user.getFullName());

					if (themeDisplay.isShowMyAccountIcon() && (themeDisplay.getURLMyAccount() != null)) {
						String myAccountURL = String.valueOf(themeDisplay.getURLMyAccount());

						signedInAs = "<a class=\"signed-in\" href=\"" + HtmlUtil.escape(myAccountURL) + "\">" + signedInAs + "</a>";
					}
		%>

		<liferay-ui:message arguments="<%=signedInAs%>" key="you-are-signed-in-as-x" translateArguments="<%=false%>" />
	</c:when>
	<c:otherwise>

		<%
			String formName = "loginForm";

					if (windowState.equals(LiferayWindowState.EXCLUSIVE)) {
						formName += "Modal";
					}

					String redirect = ParamUtil.getString(request, "redirect");
					System.out.println("view2 - loginForm - redirect = " + redirect);
					String login = (String) SessionErrors.get(renderRequest, "login");
					System.out.println("view2 - loginForm - login = " + login);
					boolean rememberMe = ParamUtil.getBoolean(request, "rememberMe");
		%>

		<div class="login-container">

			<portlet:actionURL name="logCheck" secure="<%=PropsValues.COMPANY_SECURITY_AUTH_REQUIRES_HTTPS || request.isSecure()%>"
				var="logCheckURL">
				<portlet:param name="mvcRenderCommandName" value="logCheck" />
			</portlet:actionURL>

			<aui:form action="<%=logCheckURL%>" autocomplete='<%=PropsValues.COMPANY_SECURITY_LOGIN_FORM_AUTOCOMPLETE ? "on" : "off"%>'
				cssClass="sign-in-form" method="post" name="<%=formName%>" onSubmit="event.preventDefault();">

				<aui:input name="login" type="hidden" value="<%=login%>" />
				<aui:input name="password" type="hidden" value="<%=password%>" />
				<aui:input name="rememberMe" type="hidden" value="<%=rememberMe%>" />
				<aui:input name="authType" type="hidden" value="<%=authType%>" />

				<aui:fieldset>

					<aui:input name="LoginCode" showRequiredLabel="<%=false%>" type="text" value="" />

				</aui:fieldset>

				<aui:button-row>
					<aui:button cssClass="btn-lg" type="submit" value="sign-in" />
				</aui:button-row>
			</aui:form>

		</div>

	</c:otherwise>
</c:choose>