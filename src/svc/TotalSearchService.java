package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ReviewsDAO;
import vo.Reviews;

public class TotalSearchService {
	public int getListCount(String keyword) {
		int listCount = 0;
		Connection con = getConnection();
		ReviewsDAO boardDAO = ReviewsDAO.getInstance();
		boardDAO.setConnection(con);
		listCount = boardDAO.selectListCount(keyword);
		close(con);
		System.out.println("서비스에서의 listCount>"+listCount);
		return listCount;
	}

	public Reviews getArticleList(int page, int limit, String keyword) throws Exception{
		Reviews articleList = null;
		Connection con = getConnection();
		ReviewsDAO boardDAO = ReviewsDAO.getInstance();
		boardDAO.setConnection(con);
		articleList = boardDAO.selectArticleList(page,limit, keyword);
		close(con);
		return articleList;
	}
}
