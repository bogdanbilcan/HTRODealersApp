package com.example.login.portlet;

import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.petra.mail.MailEngine;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

@Component(property = { "javax.portlet.name=MyLoginPortlet",
		"mvc.command.name=/login/login" }, service = MVCActionCommand.class)
public class MyLoginMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		HttpServletRequest request = PortalUtil
				.getOriginalServletRequest(PortalUtil.getHttpServletRequest(actionRequest));

		HttpServletResponse response = PortalUtil.getHttpServletResponse(actionResponse);

		String login = ParamUtil.getString(actionRequest, "login");
		String password = actionRequest.getParameter("password");
		boolean rememberMe = ParamUtil.getBoolean(actionRequest, "rememberMe");
		String authType = CompanyConstants.AUTH_TYPE_EA;

		InternetAddress[] to = new InternetAddress[1];
		
		to[0] = new InternetAddress("sorin.miroiu@logika.ro", "sorin.miroiu@logika.ro");

		InternetAddress from = new InternetAddress("sorin.miroiu@gmail.com", "sorin.miroiu@gmail.com");

		String body = "MAIL FROM LOGIN";
		String subject = "MAIL FROM LOGIN";

		MailEngine.send(from, to, null, null, subject, body);
		
		System.out.println("trimis");

		AuthenticatedSessionManagerUtil.login(request, response, login, password, rememberMe, authType);

		actionResponse.sendRedirect(themeDisplay.getPathMain());
	}

}
