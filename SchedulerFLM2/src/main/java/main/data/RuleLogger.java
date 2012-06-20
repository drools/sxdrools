package main.data;

public class RuleLogger {
	private String message;
	private String ruleID;

	public RuleLogger() { }

	public RuleLogger(String ruleID, String message) { 
		this.setRuleID(ruleID);
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString() {
		return message;
	}

	public String getRuleID() {
		return ruleID;
	}

	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}
}
