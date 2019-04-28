package lz.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionComponent {
    public Integer i;

    public SessionComponent() {
	i = 0;
    }

    public Integer getI() {
	return i;
    }

}