package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class Configurer implements WebSocketConfigurer {

	
	@Autowired
    Handler handler;
    
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "ws").setAllowedOrigins("*");//.withSockJS();
        registry.addHandler(handler, "sock").setAllowedOrigins("*").withSockJS();
    }
}