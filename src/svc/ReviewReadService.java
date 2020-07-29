package svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ReviewsDAO;
import vo.Reviews;

public class ReviewReadService {
	public Reviews readBoard(int board_num) {
		ReviewsDAO reviewsDAO = ReviewsDAO.getInstance();
		Connection con = getConnection();
		reviewsDAO.setConnection(con);
		Reviews reviews = reviewsDAO.selectReview(board_num);
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reviews;
	}
	
}
