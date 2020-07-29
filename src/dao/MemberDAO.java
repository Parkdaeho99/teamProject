package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import vo.Member;

public class MemberDAO {
	public static MemberDAO instance;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	private MemberDAO() {
		
	}
	public static MemberDAO getInstance(){
		if(instance == null){
			instance = new MemberDAO();
		}
		return instance;
	}
	public void setConnection(Connection con){
		this.con = con;
	}
	
	public String selectLoginId(Member member){
		String loginId = null;
		String sql="SELECT id FROM users WHERE id=? AND pass=HEX(AES_ENCRYPT(?,'egg'))";
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				loginId = rs.getString("id");
			}
		}catch(Exception ex){
			System.out.println(" 에러 " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}
		
		return loginId;
	}
	
	public int insertMember(Member member){
		String sql="INSERT INTO users VALUES (?,?,HEX(AES_ENCRYPT(?,'egg')),HEX(AES_ENCRYPT(?,'egg')),?,?,?,?,?,?,0)";
		int insertCount=0;
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getId());
			pstmt.setString(3, member.getPass());
			pstmt.setString(4, member.getPassT());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getpCall());
			pstmt.setString(7, member.getGender());
			pstmt.setString(8, member.getBirth());
			pstmt.setString(9, member.geteAgree());
			pstmt.setString(10, member.getsAgree());
			insertCount=pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("joinMember 에러: " + ex);			
		}finally{
			close(pstmt);
		}
		
		return insertCount;
	}
	
	public int loginResult(String id, String pass) {
		String pass_sql="";
		String sql = "SELECT id,CAST(AES_DECRYPT(UNHEX(pass),'egg') AS CHAR(10000)) pass,ban FROM users WHERE id=?";
		boolean ban = false;

		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					id = rs.getString("id");
					pass_sql = rs.getString("pass");
					ban =  rs.getBoolean("ban");
				} while(rs.next());
			} else {
				return 0; //없는 계정
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		if(pass_sql.equals(pass)) {
			if(ban == true) {
				return 3; //밴 상태
			}
			return 1; //성공
		} else
			return 2; //비번 틀림
	}
	
	public Member selectMember(String id) {
		String sql="SELECT * FROM users WHERE id=?";
		Member selectMember = new Member();
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs =pstmt.executeQuery();
			rs.next();
			selectMember.setName(rs.getString("name"));
			selectMember.setId(rs.getString("id"));
			selectMember.setPass(rs.getString("pass"));
			selectMember.setPassT(rs.getString("passT"));
			selectMember.setPhone(rs.getString("phone"));
			selectMember.setpCall(rs.getString("pcall"));
			selectMember.setGender(rs.getString("gender"));
			selectMember.setBirth(rs.getString("birth"));
			selectMember.seteAgree(rs.getString("eAgree"));
			selectMember.setsAgree(rs.getString("sAgree"));
		}catch(Exception ex){
			System.out.println("joinMember 에러: " + ex);			
		}finally{
			close(pstmt);
		}
		
		return selectMember;
	}
	public int updateUser(Member member) {
		System.out.println("유저정보수정 DAO 입장");
		String sql="UPDATE users SET name=?, pass=HEX(AES_ENCRYPT(?,'egg')), passT=HEX(AES_ENCRYPT(?,'egg')), phone=?, pcall=?, gender=?, birth=?, eAgree=?, sAgree=? WHERE id=?";
		int result=0;
		
		try{
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getPassT());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getpCall());
			pstmt.setString(6, member.getGender());
			pstmt.setString(7, member.getBirth());
			pstmt.setString(8, member.geteAgree());
			pstmt.setString(9, member.getsAgree());
			pstmt.setString(10, member.getId());
			result =pstmt.executeUpdate();
			return result;
		}catch(Exception ex){
			System.out.println("joinMember 에러: " + ex);			
		}finally{
			close(pstmt);
		}
		
		return result;
	}
	public int idCheck(String id) {
		String sql = "select count(id) idcnt from users where id = '" + id + "'";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("idcnt");
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rs);
			close(pstmt);
		}
		return -1;
	}
	public Member memberSearch(String id) {
		// TODO Auto-generated method stub
		Member member = new Member();
		String sql = "SELECT name,id,gender,birth,ban FROM users WHERE id = '" + id + "'";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member.setName(rs.getString("name"));
				member.setId(rs.getString("id"));
				member.setGender(rs.getString("gender"));
				member.setBirth(rs.getString("birth"));
				member.setBan(rs.getBoolean("ban"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rs);
			close(pstmt);
		}
		return member;
	}
	public boolean deleteMember(String id) {
		System.out.println("삭제 컨트롤" + id);
		boolean result = false;
		int cnt = 0;
		String sql = "delete from users where id = '"+ id + "'";
			try {
				pstmt = con.prepareStatement(sql);
				cnt = pstmt.executeUpdate();
				if(cnt == 1) {
					result = true;
				}
				con.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return result;
	}
	
	public boolean memberBan(String id) {
		String sql = "UPDATE users SET ban=1 WHERE id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			int result = pstmt.executeUpdate();
			if(result > 0) {
				con.commit();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return false;
	}
	
	public boolean memberPermit(String id) {
		String sql = "UPDATE users SET ban=0 WHERE id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			int result = pstmt.executeUpdate();
			if(result > 0) {
				con.commit();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return false;
	}
}