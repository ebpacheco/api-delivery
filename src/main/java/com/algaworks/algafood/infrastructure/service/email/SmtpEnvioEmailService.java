package com.algaworks.algafood.infrastructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService {

	@Autowired
	private EmailProperties emailProperties;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Configuration freemakerConfig;

	@Override
	public void enviar(Mensagem mensagem) {
		try {
			String corpo = processarTemplater(mensagem);

			MimeMessage mimeMessage = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
			helper.setSubject(mensagem.getAssunto());
			helper.setText(corpo, true);

			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Nao foi possivel enviar e-mail", e);
		}

	}

	private String processarTemplater(Mensagem mensagem) {
		try {
			Template template = freemakerConfig.getTemplate(mensagem.getCorpo());
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
		} catch (Exception e) {
			throw new EmailException("Nao foi possivel enviar montar template do e-mail", e);
		}

	}

}
