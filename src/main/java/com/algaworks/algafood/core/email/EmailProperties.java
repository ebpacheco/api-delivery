package com.algaworks.algafood.core.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Validated
@Setter
@Getter
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {

	@NotNull
	private String remetente;

	private Implementacao impl = Implementacao.FAKE;

	private Sandbox sandbox = new Sandbox();

	public enum Implementacao {
		SMTP, FAKE, SANDBOX
	}

	@Getter
	@Setter
	public class Sandbox {

		private String destinatario;

	}

}