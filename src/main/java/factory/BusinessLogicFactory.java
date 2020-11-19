package factory;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;

public class BusinessLogicFactory {
	public static BLFacade createBusinessLogic() {
		try {
			ConfigXML c = ConfigXML.getInstance();
			if(c.isBusinessLogicLocal()) {		
				DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
				return new BLFacadeImplementation(da);
			}
			else { //If remote
				//URL url = new URL(serviceName);
				URL url = new URL("http://localhost:9999/ws?wsdl");

				//1st argument refers to wsdl document above
				//2nd argument is service name, refer to wsdl document above
				//	        QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
				QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");

				Service service = Service.create(url, qname);

				return service.getPort(BLFacade.class);
			}
		}
		catch (Exception e) {
			//a.jLabelSelectOption.setText("Error: "+e.toString());
			//a.jLabelSelectOption.setForeground(Color.RED);		
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}
		return null;
	}
}
