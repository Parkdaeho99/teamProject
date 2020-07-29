package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReplyDAO;
import vo.Reply;

public class ReplyWriteService {
	public void writeReply(Reply reply) {
		// TODO Auto-generated method stub
		System.out.println("댓글쓰기Service");
		ReplyDAO replyDAO = ReplyDAO.getInstance();
		Connection con = getConnection();
		replyDAO.setConnection(con);
		boolean reply_write_result = replyDAO.writeReply(reply);
		if(reply_write_result == true){
			commit(con);
		}
		else{
			rollback(con);
		}
		close(con);
	}
}
