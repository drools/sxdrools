package main.data;

public class RuleLogger {

	// メンバ変数の定義
	// メッセージ
	private String message;
	// ruleID
	private String ruleID;

	// コンストラクタの設定
	// 引数なし
	public RuleLogger() {
	}

	// コンストラクタの設定
	// 引数あり
	public RuleLogger(String ruleID, String message) {
		this.setRuleID(ruleID);
		this.message = message;
	}

	// ゲッター・セッター
	// メッセージ
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// ルールID
	public String getRuleID() {
		return ruleID;
	}

	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}

	// ルールログの表示
	public String toString() {
		return message;
	}

}
