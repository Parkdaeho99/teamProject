package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
public class MemberPermitService {

	public boolean memberPermit(String id) {
		// TODO Auto-generated method stub
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con =getConnection();
		memberDAO.setConnection(con);
		boolean result = memberDAO.memberPermit(id);
		close(con);
		return result;
	}
	
}








