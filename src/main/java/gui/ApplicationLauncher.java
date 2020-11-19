package gui;

import java.util.Locale;

import javax.swing.UIManager;

import configuration.ConfigXML;
import factory.BusinessLogicFactory;
import businessLogic.BLFacade;

public class ApplicationLauncher {
	
	public static void main(String[] args) {

		ConfigXML c=ConfigXML.getInstance();
	
		System.out.println(c.getLocale());
		
		Locale.setDefault(new Locale(c.getLocale()));
		
		System.out.println("Locale: "+Locale.getDefault());
		
		Login a=new Login();
		a.setVisible(true);



		try {
			
			BLFacade appFacadeInterface;
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			boolean isLocal = c.isBusinessLogicLocal();
			boolean initializeMode = c.getDataBaseOpenMode().equals("initialize");
			appFacadeInterface = BusinessLogicFactory.createBusinessLogic(isLocal, initializeMode);
			MainGUI.setBussinessLogic(appFacadeInterface);

			
		}catch (Exception e) {
			//a.jLabelSelectOption.setText("Error: "+e.toString());
			//a.jLabelSelectOption.setForeground(Color.RED);		
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}

	}

}
