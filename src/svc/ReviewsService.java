package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReviewsDAO;
import vo.Reviews;

public class ReviewsService {

	public Reviews selectTitle() {
		// TODO Auto-generated method stub
		System.out.println("서비스진입");
		ReviewsDAO reviewsDAO = ReviewsDAO.getInstance();
		Connection con = getConnection();
		reviewsDAO.setConnection(con);
		Reviews reviews = reviewsDAO.selectReviews();
		if(!reviews.getTitle().isEmpty()){
			commit(con);
		}
		else{
			rollback(con);
		}
		close(con);
		return reviews;
	}

}
