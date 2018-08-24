package it.example.app1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
public class App1Application {
	
	private static String CLOUD_PROFILE = "spring.cloud.config.profile";
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private HelloService helloService;
	
	@Autowired
	private ContextHolder ctx;
	
	
	@Autowired
	private ContextRefresher ctxRefresher;
	
	@RequestMapping("/")
	public String query(@RequestParam("q") String q) {
		return environment.getProperty(q);
	}

	@RequestMapping("/hello")
	public String hello() {
		return helloService.sayHello();
	}
	
	@RequestMapping("/context")
	public String readContext() {
		//on demand context refresh
		
		ctxRefresher.refresh();
		return ctx.toString();
		
	}
	@RequestMapping("/profile")
	public String changeProfile(@RequestParam("p") String p) {
		ConfigurableEnvironment cfgenvironment = (ConfigurableEnvironment)environment;
		
		cfgenvironment.setActiveProfiles(p);
		
		//Need to override initial profile which spring cloud config client loads at start up.
		MutablePropertySources propertySources = cfgenvironment.getPropertySources();
		propertySources.remove(CLOUD_PROFILE);
		Map dynamicProfile = new HashMap();
		dynamicProfile.put(CLOUD_PROFILE, p);
		propertySources.addFirst(new MapPropertySource("DynamicContext", dynamicProfile));
		 
		 return "Profile changed to "+p;
	}
	
	 
	
	public static void main(String[] args) {
		SpringApplication.run(App1Application.class, args);
		
		
	}
	

	
	
}
