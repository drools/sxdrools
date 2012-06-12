//パッケージの作成
package main.domain;
//パッケージのインポート
import java.io.Serializable;

public class BlockedClassroom implements Serializable{
	//メンバ変数の定義
	private Classroom classroom;
	private Day day;
	private int length;
	
	//コンストラクタの設定
	//引数なし
	public BlockedClassroom() {	}
	//引数あり
	public BlockedClassroom(Classroom classroom, Day day, String length) {
		this.classroom = classroom;
		this.day = day;
		this.length = Integer.parseInt(length);
	}
	
	//ゲッター・セッター
	//教室
	public Classroom getClassroom() {
		return classroom;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	//営業日
	public Day getDay() {
		return day;
	}
	public void setDay(Day day) {
		this.day = day;
	}
	//コース期間
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}

}
