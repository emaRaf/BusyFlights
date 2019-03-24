package crazyair;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("endpoints")
@Component
public class AppProperties {

    private String crazyAirUrl;
    private String thoughJetUrl;

    public String getCrazyAirUrl() {
	return crazyAirUrl;
    }

    public void setCrazyAirUrl(String crazyAirUrl) {
	this.crazyAirUrl = crazyAirUrl;
    }

    public String getThoughJetUrl() {
	return thoughJetUrl;
    }

    public void setThoughJetUrl(String thoughJetUrl) {
	this.thoughJetUrl = thoughJetUrl;
    }

    public AppProperties() {

    }

}
