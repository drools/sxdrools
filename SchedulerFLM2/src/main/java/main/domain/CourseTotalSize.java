package main.domain;

import java.io.Serializable;

public class CourseTotalSize implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2769799412402018285L;

	// メンバ変数の定義
	private Course course;
	private int totalSize;
	private int countSize;

	// コンストラクタの設定
	// 引数なし
	public CourseTotalSize() {
	}

	// 引数あり
	public CourseTotalSize(Course course, String totalSize) {
		this.course = course;
		this.totalSize = Integer.parseInt(totalSize);
	}

	// ゲッター・セッター
	// コース
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	// コース総定員
	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	// 日程計画後の総定員
	public int getCountSize() {
		return countSize;
	}

	public void setCountSize(int countSize) {
		this.countSize = countSize;
	}

}
