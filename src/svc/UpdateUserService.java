package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;

public class UpdateUserService {

	public boolean updateUser(Member member) {
		// TODO Auto-generated method stub
		boolean updateSuccess = false;
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		int updateCount = memberDAO.updateUser(member);
		if(updateCount > 0){
			updateSuccess = true;
			commit(con);
		}
		else{
			rollback(con);
		}
		close(con);
		return updateSuccess;
	}
}
