package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ReviewsDAO;

public class DeleteReviewService {
	public boolean deleteReview(int num) {
		System.out.println("DeleteReviewService 입장");
		boolean deleteResult = false;
		Connection con = getConnection();
		ReviewsDAO boardDAO = ReviewsDAO.getInstance();
		boardDAO.setConnection(con);
		deleteResult = boardDAO.deleteReview(num);
		close(con);
		return deleteResult;
	}
}
