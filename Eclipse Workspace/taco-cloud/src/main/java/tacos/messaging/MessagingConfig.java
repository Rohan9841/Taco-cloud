package tacos.messaging;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import tacos.Order;

public class MessagingConfig {
	
	@Bean
	/*
	 * MappingJackson2MessageConverter uses the Jackson 2 JSON library to convert
	 * messages to and from JSON. This bean declaration will enable
	 * MappingJackson2MessageConverter to be used instead of SimpleMessageConverter.
	 */
	public MappingJackson2MessageConverter messageConverter() {
		MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
		
		/*
		 * setTypeIdPropertyName enables receiver to know what type to convert an incoming message to. By
		 * default _typeId will contain full classname of the type being converted i.e Order.class. However, 
		 * that's inflexible as the receiver also needs the same full classname. 
		 */
		messageConverter.setTypeIdPropertyName("_typeId");
		
		
		Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
		
		/*
		 * The value 'order' will be sent to the messages's "_typeId" property instead
		 * of full class name*
		 */		
		typeIdMappings.put("order", Order.class);
		
		/* maps synthetic type name to the actual type by calling setTypeIdMappings() on the message converter*/
		messageConverter.setTypeIdMappings(typeIdMappings);
		return messageConverter;
	}
}
