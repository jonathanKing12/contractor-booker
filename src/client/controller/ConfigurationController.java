package client.controller;

import java.io.IOException;

import transport.contractor.ContractorException;
import client.model.ConfigurableModel;
import client.model.ConfigurationModel;

public class ConfigurationController implements ConfigurableController {

	private ConfigurableModel model;

	public ConfigurationController() {
		try {
			model = new ConfigurationModel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setFileName(String fileName) {
		try {
			model.setLocation(fileName);
		} catch (ContractorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getFileName() {
		String location = null;
		try {
			location = model.getLocation();
			System.out.println("getting file name in configuration controller " + location);
		} catch (ContractorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return location;
	}
}
