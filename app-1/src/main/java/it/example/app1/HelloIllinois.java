package it.example.app1;

import org.springframework.cloud.context.config.annotation.RefreshScope;

//@Component
@RefreshScope
//@ConditionalOnExpression(value = " '${config.dynamic.context.state}' == 'illinois'")
public class HelloIllinois implements HelloService {


	@Override
	public String sayHello() {
		return "Hi from Illinois";
	}

}
