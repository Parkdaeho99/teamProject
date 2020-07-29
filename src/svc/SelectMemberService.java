package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;

public class SelectMemberService {

	public Member selectMember(String id) {
		System.out.println("유저정보수정 서비스 진입");
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		Member member = memberDAO.selectMember(id);
		close(con);
		return member;
	}
	
}
