package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
public class MemberBanService {

	public boolean memberBan(String id) {
		// TODO Auto-generated method stub
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con =getConnection();
		memberDAO.setConnection(con);
		boolean result = memberDAO.memberBan(id);
		close(con);
		return result;
	}
	
}








