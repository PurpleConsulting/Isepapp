package org.purple.bean;

import java.util.ArrayList;

public class Page {

	public Page() {
		this.css = new ArrayList<String>();
		this.js = new ArrayList<String>();
		this.content = "";
		this.title = "ISEP - APP";
	}

	private String title;
	private boolean error;
	private boolean warning;
	private boolean success;
	private boolean info;
	private String errorMessage;
	private String warningMessage;
	private String successMessage;
	private String infoMessage;

	private ArrayList<String> css;
	private ArrayList<String> js;
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<String> getCss() {
		return this.css;
	}

	public void setCss(String... css) {
		for (String c : css) {
			this.css.add(c);
		}
	}

	public ArrayList<String> getJs() {
		return this.js;
	}

	public void setJs(String... js) {
		for (String j : js) {
			this.js.add(j);
		}
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

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	
	public boolean getInfo() {
		return info;
	}

	public void setInfo(boolean info) {
		this.info = info;
	}
	
	

}

