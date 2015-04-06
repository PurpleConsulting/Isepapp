package org.purple.model;

import java.util.ArrayList;
import java.util.List;

public class Page {
	
	public Page(){
		
		
	}
	
	private boolean error;
	private String errorMessage;
	private boolean warning;
	private String warningMessage;
	private String infoMessage;
	
	
	private ArrayList<String> css;
	private ArrayList<String> js;
	
	
	
	public ArrayList<String> getCss() {
		return css;
	}
	public void setCss(ArrayList<String> css) {
		this.css = css;
	}
	public ArrayList<String> getJs() {
		return js;
	}
	public void setJs(ArrayList<String> js) {
		this.js = js;
	}
	public boolean getError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public boolean getWarning() {
		return warning;
	}
	public void setWarning(boolean warning) {
		this.warning = warning;
	}
	public String getWarningMessage() {
		return warningMessage;
	}
	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}
	public String getInfoMessage() {
		return infoMessage;
	}
	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}
	
	
	
}
