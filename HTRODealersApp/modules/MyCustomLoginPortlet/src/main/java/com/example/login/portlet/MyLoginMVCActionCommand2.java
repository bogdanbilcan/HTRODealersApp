
package com.example.login.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

@Component(property =
	{
		"javax.portlet.name=MyLoginPortlet",
		"mvc.command.name=logCheck"
	}, service = MVCActionCommand.class)
public class MyLoginMVCActionCommand2 extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		HttpServletRequest request =
			PortalUtil.getOriginalServletRequest(
				PortalUtil.getHttpServletRequest(actionRequest));

		HttpServletResponse response =
			PortalUtil.getHttpServletResponse(actionResponse);

		System.out.println("logCheck - doProcessAction - response = " + response.toString());

		String login = ParamUtil.getString(actionRequest, "login");
		String password = actionRequest.getParameter("password");
		boolean rememberMe = ParamUtil.getBoolean(actionRequest, "rememberMe");
		String loginCode = ParamUtil.getString(actionRequest, "LoginCode");
		String authType = CompanyConstants.AUTH_TYPE_EA;

		request.setAttribute("login", login);
		request.setAttribute("password", password);
		request.setAttribute("rememberMe", rememberMe);
		request.setAttribute("authType", authType);

		System.out.println("logCheck - doProcessAction - login = " + login);
		System.out.println("logCheck - doProcessAction - password = " + password);
		System.out.println("logCheck - doProcessAction - rememberMe = " + rememberMe);
		System.out.println("logCheck - doProcessAction - loginCode = " + loginCode);

		String generatedCode = "customblabla";

		if (loginCode.equalsIgnoreCase(generatedCode)) {
			AuthenticatedSessionManagerUtil.login(request, response, login, password, rememberMe, authType);
			System.out.println("logCheck - redirected - themeDisplay.getPathMain()= " + themeDisplay.getPathMain());
			actionResponse.sendRedirect(themeDisplay.getPathMain());
		}
		else {
			actionResponse.sendRedirect(themeDisplay.getPathMain());
		}

	}

}
