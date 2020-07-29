package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;
public class MemberSearchService {

	public Member memberSearch(String id) {
		// TODO Auto-generated method stub
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con =getConnection();
		memberDAO.setConnection(con);
		Member member = memberDAO.memberSearch(id);
		close(con);
		return member;
	}
	
}








