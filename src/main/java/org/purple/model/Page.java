package org.purple.model;

public class Page {
	
	private String error;
	private String errorMessage;
	private String warning;
	private String warningMessage;
	private String infoMessage;
	
	private String[] css;
	private String[] js;
	
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
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
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
