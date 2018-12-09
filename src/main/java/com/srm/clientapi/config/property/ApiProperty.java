package com.srm.clientapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("clientapi")
public class ApiProperty {

	private String originPermitida;

	private final Security security = new Security();

	public Security gettSecurity() {
		return this.security;
	}

	public static class Security {

		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}

	}

	public String getOriginPermitida() {
		return originPermitida;
	}

	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}

	public Security getSecurity() {
		return security;
	}

}
