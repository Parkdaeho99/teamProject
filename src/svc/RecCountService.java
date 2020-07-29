package svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ReviewsDAO;

public class RecCountService {
	public int recCount(int board_num) {
		System.out.println("리뷰 추천수 조회 서비스 이동 성공");
		ReviewsDAO reviewsDAO = ReviewsDAO.getInstance();
		Connection con = getConnection();
		reviewsDAO.setConnection(con);
		int count = reviewsDAO.recCount(board_num);
		if(count < 0) {
			System.out.println("조회 실패");
		} else if(count >= 0) {
			System.out.println("조회 성공");
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
}
