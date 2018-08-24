package it.example.app1;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConditionalOnExpression(value = "'${config.dynamic.context.country}' == 'us'")
public class HelloUS implements HelloService {


	@Override
	public String sayHello() {
		return "Hy from US";
	}

}
