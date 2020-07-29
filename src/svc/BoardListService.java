package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ReviewsDAO;
import vo.Reviews;

public class BoardListService {

	public int getListCount() {
		int listCount = 0;
		Connection con = getConnection();
		ReviewsDAO boardDAO = ReviewsDAO.getInstance();
		boardDAO.setConnection(con);
		listCount = boardDAO.selectListCount();
		close(con);
		return listCount;
	}

	public Reviews getArticleList(int page, int limit) throws Exception{
		Reviews articleList = null;
		Connection con = getConnection();
		ReviewsDAO boardDAO = ReviewsDAO.getInstance();
		boardDAO.setConnection(con);
		articleList = boardDAO.selectArticleList(page,limit);
		close(con);
		return articleList;
	}

}
