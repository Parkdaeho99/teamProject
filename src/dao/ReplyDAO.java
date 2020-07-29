package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.Reply;

public class ReplyDAO {
	public static ReplyDAO instance;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

	private ReplyDAO() {

	}

	public static ReplyDAO getInstance() {
		if (instance == null) {
			instance = new ReplyDAO();
		}
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public Reply selectReply(int board_num) {
		System.out.println("리플라이 dao 진입");
		String sql = "SELECT * FROM reply WHERE ref_num=?";
		Reply reply = new Reply();
		ArrayList<String> reply_user = new ArrayList<String>();
		ArrayList<String> reply_content = new ArrayList<String>();
		ArrayList<String> reply_date = new ArrayList<String>();
		ArrayList<Integer> reply_num = new ArrayList<Integer>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			System.out.println("abcd");
			if (rs.next()) {
				do {
					reply_num.add(rs.getInt("num"));
					reply_user.add(rs.getString("user"));
					reply_content.add(rs.getString("content"));
					reply_date.add(rs.getString("date"));
				} while (rs.next());
			} else {
				System.out.println("디비에 정보가 하나도 없습니다.");
				return null;
			}
			reply.setBoard_num(reply_num);
			reply.setReply_user(reply_user);
			reply.setReply_content(reply_content);
			reply.setReply_date(reply_date);
		} catch (Exception ex) {
			System.out.println(" 에러 " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}

		return reply;
	}

	public boolean writeReply(Reply reply) {
		System.out.println("댓글쓰기DAO");
		String sql = "INSERT INTO reply VALUES(?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, reply.getBoard_num().get(0));
			pstmt.setString(3, reply.getReply_user().get(0));
			pstmt.setString(4, encodeContent(reply.getReply_content().get(0)));
			pstmt.setString(5, reply.getReply_date().get(0));

			int count = pstmt.executeUpdate();
			if (count > 0) {
				System.out.println("등록성공!");
				return true;
			} else
				return false;
		} catch (Exception ex) {
			System.out.println(" 에러 " + ex);
		} finally {
			close(rs);
			close(pstmt);
		}
		return false;
	}

	public boolean deleteReply(int num) {
		System.out.println("댓글삭제DAO 입장");
		System.out.println("현재 댓글 번호>" + num);
		String sql = "DELETE FROM reply WHERE num=?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			if (result >= 1) {
				con.commit();
				System.out.println("댓글삭제 성공");
				return true;
			} else {
				System.out.println("댓글삭제 실패");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println("댓글삭제 실패");
		return false;
	}

//	script,html태그 방지 메소드 시작
	public static String replace(String str, String pattern, String replace) {
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();

		while ((e = str.indexOf(pattern, s)) >= 0) {
			result.append(str.substring(s, e));
			result.append(replace);
			s = e + pattern.length();
		}
		result.append(str.substring(s));
		return result.toString();
	}

	public static String encodeContent(String content) {
		String ret = content;
		try {
			ret = replace(ret, "&", "&amp;");
			ret = replace(ret, "<", "&lt;");
			ret = replace(ret, ">", "&gt;");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return ret;
	}
//		script, html태그 방지 메소드끝
}