package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;

public class DeleteMemberService {
	public boolean deleteMember(String id) {
		boolean deleteResult = false;
		System.out.println("삭제 서비스" + id);
		Connection con = getConnection();
		MemberDAO boardDAO = MemberDAO.getInstance();
		boardDAO.setConnection(con);
		deleteResult = boardDAO.deleteMember(id);
		close(con);
		return deleteResult;
	}
}
