
package com.example.login.portlet;

import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
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
		"mvc.command.name=/login/login"
	}, service = MVCActionCommand.class)
public class MyLoginMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(actionRequest));

		System.out.println("doProcessAction - request = " + request.toString());

		HttpServletResponse response = PortalUtil.getHttpServletResponse(actionResponse);

		System.out.println("doProcessAction - response = " + response.toString());

		String login = ParamUtil.getString(actionRequest, "login");
		String password = actionRequest.getParameter("password");
		boolean rememberMe = ParamUtil.getBoolean(actionRequest, "rememberMe");
		String loginCode = ParamUtil.getString(actionRequest, "LoginCode");
		String authType = CompanyConstants.AUTH_TYPE_EA;

		System.out.println("doProcessAction - login = " + login);
		System.out.println("doProcessAction - password = " + password);
		System.out.println("doProcessAction - rememberMe = " + rememberMe);
		System.out.println("doProcessAction - loginCode = " + loginCode);

		InternetAddress[] to = new InternetAddress[1];

		to[0] = new InternetAddress("bogdan.bilcan@logika.ro", "bogdan.bilcan@logika.ro");

		InternetAddress from = new InternetAddress("bogdan.bilcan@outlook.com", "bogdan.bilcan@outlook.com");

		String body = "MAIL FROM LOGIN" + loginCode;
		String subject = "MAIL FROM LOGIN";

		MailMessage mailMessage = new MailMessage();
		mailMessage.setHTMLFormat(true);
		mailMessage.setTo(to);
		mailMessage.setFrom(from);
		mailMessage.setSubject(subject);
		mailMessage.setBody(body);

		MailServiceUtil.sendEmail(mailMessage);

		System.out.println("Mail trimis");

		AuthenticatedSessionManagerUtil.login(request, response, login, password, rememberMe, authType);

		System.out.println("redirected - themeDisplay.getPathMain()= " + themeDisplay.getPathMain());

		actionResponse.sendRedirect(themeDisplay.getPathMain());
	}

}