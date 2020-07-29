package vo;

import java.util.ArrayList;

public class Reply {
	private ArrayList<Integer> board_num = null;
	private ArrayList<String> reply_user = null;
	private ArrayList<String> reply_content = null;
	private ArrayList<String> reply_date = null;
	
	public ArrayList<Integer> getBoard_num() {
		return board_num;
	}
	public void setBoard_num(ArrayList<Integer> board_num) {
		this.board_num = board_num;
	}
	public ArrayList<String> getReply_user() {
		return reply_user;
	}
	public void setReply_user(ArrayList<String> reply_user) {
		this.reply_user = reply_user;
	}
	public ArrayList<String> getReply_content() {
		return reply_content;
	}
	public void setReply_content(ArrayList<String> reply_content) {
		this.reply_content = reply_content;
	}
	public ArrayList<String> getReply_date() {
		return reply_date;
	}
	public void setReply_date(ArrayList<String> reply_date) {
		this.reply_date = reply_date;
	}
	
	
}
