package org.pocMockServer;

import static org.mockserver.integration.ClientAndProxy.startClientAndProxy;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

import org.mockserver.integration.ClientAndProxy;
import org.mockserver.integration.ClientAndServer;

public class MockHelper {
	
	private ClientAndProxy proxy;
	private ClientAndServer mockServer;
	
	public void startProxy() {
	    mockServer = startClientAndServer(1080);
	    proxy = startClientAndProxy(1090);
	}
	
	public void stopProxy() {
	    proxy.stop();
	    mockServer.stop();
	}
	

}
