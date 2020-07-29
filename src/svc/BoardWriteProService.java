package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReviewsDAO;
import vo.Reviews;

public class BoardWriteProService {

	public boolean registArticle(Reviews reviews) {
		boolean isWriteSuccess = false;
		Connection con = getConnection();
		ReviewsDAO reviewsDAO = ReviewsDAO.getInstance();
		reviewsDAO.setConnection(con);
		int insertCount = reviewsDAO.insertArticle(reviews);
		
		if(insertCount > 0){
			commit(con);
			isWriteSuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isWriteSuccess;
	}

}
