package busyflight;

public class FlightProviderClientRequest {

    private String origin;
    private String destination;

    public FlightProviderClientRequest(String origin, String destination) {
	this.origin = origin;
	this.destination = destination;
    }

    public String getOrigin() {
	return origin;
    }

    public void setOrigin(String origin) {
	this.origin = origin;
    }

    public String getDestination() {
	return destination;
    }

    public void setDestination(String destination) {
	this.destination = destination;
    }

}
