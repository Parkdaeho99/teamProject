package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.ReplyDAO;

public class ReplyDeleteService {

	public boolean replyDelete(int num) {
		System.out.println("댓글 삭제 서비스 입장");
		boolean reply_delete_result = false;
		Connection con = getConnection();
		ReplyDAO replyDAO = ReplyDAO.getInstance();
		replyDAO.setConnection(con);
		reply_delete_result = replyDAO.deleteReply(num);
		close(con);
		return reply_delete_result;
	}
}
