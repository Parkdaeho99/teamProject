package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
public class LoginService {

	public int getLoginResult(String id, String passwd) {
		// TODO Auto-generated method stub
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con =getConnection();
		memberDAO.setConnection(con);
		int result = memberDAO.loginResult(id,passwd);
		close(con);
		return result;
	}
	
}








