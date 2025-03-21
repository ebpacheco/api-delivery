package com.algaworks.algafood.core.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.FakeEnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.SandBoxEnvioEmailService;
import com.algaworks.algafood.infrastructure.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {

	@Autowired
	private EmailProperties emailProperties;

	@Bean
	EnvioEmailService envioEmailService() {
		// Acho melhor usar switch aqui do que if/else if
		switch (emailProperties.getImpl()) {
		case FAKE:
			return new FakeEnvioEmailService();
		case SMTP:
			return new SmtpEnvioEmailService();
		case SANDBOX:
			return new SandBoxEnvioEmailService();
		default:
			return null;
		}
	}
}
