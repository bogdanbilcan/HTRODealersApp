
package com.custom.logika.htro.dealersapp.controller.service;

import java.util.List;

import javax.mail.internet.InternetAddress;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class SendMailUtil {

	private static final Log hndApp_log = LogFactoryUtil.getLog(SendMailUtil.class);

	public static boolean sendMailProforma(String body, List<String> listaAdrese)
		throws Exception {

		InternetAddress fromAddress = null;
		InternetAddress[] toAdresess = new InternetAddress[listaAdrese.size()];
		hndApp_log.info("sendMailProforma() - entry");

		if (listaAdrese.isEmpty()) {
			hndApp_log.error("sendMailProforma() - Adresele de email catre care se trimite cerere Proforma nu exista!!");
			throw new Exception("Adresele de email catre care se trimite cerere Proforma nu exista!!!");

		}

		try {
			fromAddress = new InternetAddress("office@hondatrading.ro");

			for (int i = 0; i < listaAdrese.size(); i++) {
				toAdresess[i] = new InternetAddress(listaAdrese.get(i));
			}

			MailMessage mailMessage = new MailMessage();
			mailMessage.setHTMLFormat(true);
			mailMessage.setTo(toAdresess);
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject("HTRO Dealers App - Cerere Proforma");
			mailMessage.setBody(body);

			MailServiceUtil.sendEmail(mailMessage);
			hndApp_log.info("sendMailProforma() - Cerere Proforma transmisa OK");
			return true;
			// System.out.println("Send mail with HTML Text - success");

		}
		catch (Exception e) {
			e.printStackTrace();
			hndApp_log.error("sendMailProforma() - eroare la transmitere Cerere Proforma");
			return false;
			// System.out.println("Send mail with HTML Text - failed");
		}

	}

	public static boolean sendMailTransport(String body, List<String> listaAdrese)
		throws Exception {

		InternetAddress fromAddress = null;
		InternetAddress[] toAdresess = new InternetAddress[listaAdrese.size()];
		hndApp_log.info("sendMailTransport() - entry");

		if (listaAdrese.isEmpty()) {
			hndApp_log.error("sendMailTransport() - Adresele de email catre care se trimite cerere Transport nu exista!!");
			throw new Exception("Adresele de email catre care se trimite cerere Transport nu exista!!!");
		}

		try {
			fromAddress = new InternetAddress("office@hondatrading.ro");
			for (int i = 0; i < listaAdrese.size(); i++) {
				toAdresess[i] = new InternetAddress(listaAdrese.get(i));
			}

			MailMessage mailMessage = new MailMessage();
			mailMessage.setHTMLFormat(true);
			mailMessage.setTo(toAdresess);
			mailMessage.setFrom(fromAddress);
			mailMessage.setSubject("HTRO Dealers App - Cerere Transport");
			mailMessage.setBody(body);

			MailServiceUtil.sendEmail(mailMessage);
			hndApp_log.info("sendMailTransport() - Cerere Transport transmisa OK");
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			hndApp_log.error("sendMailTransport() - eroare la transmitere Cerere Transport");
			return false;
		}

	}

}
