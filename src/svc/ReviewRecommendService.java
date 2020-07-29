package svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ReviewsDAO;

public class ReviewRecommendService {
	public int reviewRecommend(int board_num, String user) {
		System.out.println("리뷰 추천 서비스 이동 성공");
		ReviewsDAO reviewsDAO = ReviewsDAO.getInstance();
		Connection con = getConnection();
		reviewsDAO.setConnection(con);
		int count = reviewsDAO.recommendReview(board_num, user);
		if(count == 0) {
			System.out.println("추천 실패");
		} else if(count == 1) {
			System.out.println("추천 성공");
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
