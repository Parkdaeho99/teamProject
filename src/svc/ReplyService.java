package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ReplyDAO;
import vo.Reply;

public class ReplyService {

	public Reply selectReply(int board_num) {
		System.out.println("reply서비스 진입");
		// TODO Auto-generated method stub
		ReplyDAO replyDAO = ReplyDAO.getInstance();
		Connection con = getConnection();
		replyDAO.setConnection(con);
		Reply reply = replyDAO.selectReply(board_num);
		close(con);
		return reply;
	}

}
