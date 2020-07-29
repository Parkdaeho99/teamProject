package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;

public class IdCheckService {

	public boolean idChecksvc(String id) {
		boolean idCheck = false;
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		int check = memberDAO.idCheck(id);
		if(check > 0){
			idCheck = true;
			commit(con);
		}
		else{
			rollback(con);
		}
		close(con);
		return idCheck;
	}
}
