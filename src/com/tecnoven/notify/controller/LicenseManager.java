/**
 * 
 */
package com.tecnoven.notify.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.tecnoven.notify.domain.LicenseEntity;
import com.tecnoven.notify.util.CryptoLibrary;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


/**
 * @author hector
 *
 */
public class LicenseManager {

	/**
	 * 
	 */
	private File licenseFile;
	
	/**
	 * 
	 */
	private static LicenseManager instance;
	
	/**
	 * 
	 */
	private CryptoLibrary cypher;
	
	/**
	 * 
	 */
	private LicenseEntity license;
	
	/**
	 * 
	 * @throws IOException
	 */
	private LicenseManager(){
		String mainPath = System.getProperty("user.dir");
		this.licenseFile = new File(mainPath + File.separator + "license.lic");
		if (!this.licenseFile.exists()){
			throw new IllegalStateException("No se ha encontrado la licencia para utilizar esta aplicacion");
		}
		this.cypher = new CryptoLibrary();
		
		try{
		String fileReaded = FileUtils.readFileToString(this.licenseFile);
		
		String dataDecrypted = cypher.decrypt(fileReaded);
		
		XStream reader = new XStream(new DomDriver());
		this.license = new LicenseEntity();
		reader.processAnnotations(this.license.getClass());
		
		this.license = (LicenseEntity) reader.fromXML(dataDecrypted);
		}catch (IOException e) {
			throw new IllegalStateException("Error intentando abrir el archivo de licenciamiento de esta aplicacion");
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static LicenseManager getInstance(){
		if(instance == null){
			instance = new LicenseManager();
		}
		return instance;
	}

	/**
	 * @return the license
	 */
	public LicenseEntity getLicense() {
		return license;
	}
	
}
