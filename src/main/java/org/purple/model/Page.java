package org.purple.model;

import java.util.List;

public class Page {
	
	private boolean error;
	private String errorMessage;
	private boolean warning;
	private String warningMessage;
	private String infoMessage;
	
	
	private String[] css;
	private String[] js;
	
	public Page(String[] css, String[] js){
		
		this.css=css;
		this.js=js;
	}
	
	public String[] getCss() {
		return css;
	}
	public void setCss(String[] css) {
		this.css = css;
	}
	public String[] getJs() {
		return js;
	}
	public void setJs(String[] js) {
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
