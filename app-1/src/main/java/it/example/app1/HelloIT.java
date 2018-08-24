package it.example.app1;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConditionalOnExpression(value = "'${config.dynamic.context.country}' == 'it'")
public class HelloIT implements HelloService {


	@Override
	public String sayHello() {
		return "ciao dall'italia";
	}

}
