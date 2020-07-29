package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.ReviewsDAO;
import vo.Reviews;

public class CateListService {
	public int getListCount(String category) {
		int listCount = 0;
		Connection con = getConnection();
		ReviewsDAO boardDAO = ReviewsDAO.getInstance();
		boardDAO.setConnection(con);
		listCount = boardDAO.cateListCount(category);
		close(con);
		System.out.println("서비스에서의 listCount>"+listCount);
		return listCount;
	}

	public Reviews getArticleList(int page, int limit, String category) throws Exception{
		Reviews articleList = null;
		Connection con = getConnection();
		ReviewsDAO boardDAO = ReviewsDAO.getInstance();
		boardDAO.setConnection(con);
		articleList = boardDAO.cateArticleList(page,limit, category);
		close(con);
		return articleList;
	}
}
