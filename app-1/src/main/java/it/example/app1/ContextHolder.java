package it.example.app1;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties (prefix = "config.dynamic")
public class ContextHolder {

	private Map<String, String> context;

	/**
	 * @return the context
	 */
	public Map<String, String> getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(Map<String, String> context) {
		this.context = context;
	}
	
	// this method should add a config change event
	public void set(String key, String value) {
		this.context.put(key, value);
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContextHolder [context=");
		builder.append(context);
		builder.append("]");
		return builder.toString();
	}
	

}
