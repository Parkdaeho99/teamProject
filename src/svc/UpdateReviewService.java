package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ReviewsDAO;
import vo.Reviews;

public class UpdateReviewService {

	public boolean updateReview(Reviews review) {
		System.out.println("UpdateReviewService 입장");
		boolean updateResult = false;
		Connection con = getConnection();
		ReviewsDAO boardDAO = ReviewsDAO.getInstance();
		boardDAO.setConnection(con);
		updateResult = boardDAO.updateReview(review);
		close(con);
		return updateResult;
	}

}
