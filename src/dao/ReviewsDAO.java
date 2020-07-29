package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.Reviews;

public class ReviewsDAO {
	public static ReviewsDAO instance;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	private ReviewsDAO() {
		
	}
	public static ReviewsDAO getInstance(){
		if(instance == null){
			instance = new ReviewsDAO();
		}
		return instance;
	}
	public void setConnection(Connection con){
		this.con = con;
	}
	
	public Reviews selectReviews(){
		String sql="SELECT * FROM reviews order by num desc;";
		Reviews reviews = new Reviews();
		ArrayList<String> num = new ArrayList<String>();
		ArrayList<String> category = new ArrayList<String>();
		ArrayList<String> title = new ArrayList<String>();
		ArrayList<String> imgPath = new ArrayList<String>();
		ArrayList<String> user = new ArrayList<String>();
		ArrayList<String> content = new ArrayList<String>();
		ArrayList<String> pName = new ArrayList<String>();
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				num.add(rs.getString("num"));
				category.add(rs.getString("category"));
				title.add(rs.getString("title"));
				imgPath.add(rs.getString("imgPath"));
				user.add(rs.getString("user"));
				content.add(rs.getString("content"));
				pName.add(rs.getString("pName"));
			}
			reviews.setNum(num);
			reviews.setCategory(category);
			reviews.setTitle(title);
			reviews.setImgPath(imgPath);
			reviews.setUser(user);
			reviews.setContent(content);
			reviews.setpName(pName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return reviews;
	}
	
	public Reviews selectReview(int board_num) {
		String sql_increaseViews = "UPDATE reviews SET views=views+1 WHERE num = ?";
		String sql = "SELECT * FROM reviews WHERE num = ?";
		try {
			pstmt = con.prepareStatement(sql_increaseViews);
			pstmt.setInt(1, board_num);
			pstmt.executeUpdate();
			close(pstmt);
			con.commit();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Reviews reviews = new Reviews();
		ArrayList<String> num = new ArrayList<String>();
		ArrayList<String> category = new ArrayList<String>();
		ArrayList<String> title = new ArrayList<String>();
		ArrayList<String> imgPath = new ArrayList<String>();
		ArrayList<String> user = new ArrayList<String>();
		ArrayList<String> content = new ArrayList<String>();
		ArrayList<String> pName = new ArrayList<String>();
		ArrayList<String> views = new ArrayList<String>();
		ArrayList<String> recommend = new ArrayList<String>();
		try {
			while(rs.next()) {
				num.add(rs.getString("num"));
				category.add(rs.getString("category"));
				title.add(rs.getString("title"));
				imgPath.add(rs.getString("imgPath"));
				user.add(rs.getString("user"));
				content.add(rs.getString("content"));
				pName.add(rs.getString("pName"));
				imgPath.add(rs.getString("imgPath2"));
				imgPath.add(rs.getString("imgPath3"));
				views.add(rs.getString("views"));
				recommend.add(rs.getString("recommend"));
			}
			reviews.setNum(num);
			reviews.setCategory(category);
			reviews.setTitle(title);
			reviews.setImgPath(imgPath);
			reviews.setUser(user);
			reviews.setContent(content);
			reviews.setpName(pName);
			reviews.setViews(views);
			reviews.setRecommend(recommend);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return reviews;
	}
	
	public int insertArticle(Reviews reviews) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		int insertCount=0;

		try{
			pstmt=con.prepareStatement("select max(num) from reviews");
			rs = pstmt.executeQuery();

			while(rs.next())
				num =rs.getInt(1);
			System.out.println("num >>"+num);
			sql="insert into reviews values(0,?,?,?,?,?,'제품명 미구현',?,?,0,0)";
			pstmt.close();
			String title = encodeContent(reviews.getTitle().get(0));
			String content = encodeContent(reviews.getContent().get(0));
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reviews.getCategory().get(0));
			pstmt.setString(2, title);
			pstmt.setString(3, reviews.getImage().get(0));
			pstmt.setString(4, reviews.getUser().get(0));
			pstmt.setString(5, content);
			System.out.println(reviews.getImage().size());
			if(!reviews.getImage().get(1).equals("이미지없음")) {
				pstmt.setString(6, reviews.getImage().get(1));
			} else pstmt.setString(6, null);
			if(!reviews.getImage().get(2).equals("이미지없음")) {
				pstmt.setString(7, reviews.getImage().get(2));
			} else pstmt.setString(7, null);
				
			insertCount=pstmt.executeUpdate();
			
		}catch(Exception ex){
			System.out.println("boardInsert 에러 : "+ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}
	
	public int selectListCount() {
		int listCount= 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			System.out.println("getConnection");
			pstmt=con.prepareStatement("select count(*) from reviews");
			rs = pstmt.executeQuery();

			if(rs.next()){
				listCount=rs.getInt(1);
			}
		}catch(Exception ex){
			System.out.println("getListCount 에러: " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}

		return listCount;
	}

	public int selectListCount(String keyword) {
		System.out.println("selectListCount keyword 진입");
		int listCount= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			System.out.println("getConnection");
			pstmt=con.prepareStatement("select count(*) from reviews WHERE title like ? || user like ? || content like ?");
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setString(3, "%"+keyword+"%");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				listCount=rs.getInt(1);
			}
		}catch(Exception ex){
			System.out.println("getListCount 에러: " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}
		System.out.println("DAO에서의 listcount >>"+listCount);
		return listCount;
	}

	public Reviews selectArticleList(int page, int limit) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="select * from reviews order by num desc limit ?,8";
		Reviews articleList = null;
		Reviews board = null;
		int startrow=(page-1)*8; 
		System.out.println(startrow);
		ArrayList<String> num = new ArrayList<String>();
		ArrayList<String> category = new ArrayList<String>();
		ArrayList<String> title = new ArrayList<String>();
		ArrayList<String> content = new ArrayList<String>();
		ArrayList<String> image = new ArrayList<String>();
		ArrayList<String> user = new ArrayList<String>();
		try{
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();
			while(rs.next()){
				category.add(rs.getString("category"));
				title.add(rs.getString("title"));
				content.add(rs.getString("content"));
				image.add(rs.getString("imgPath"));
				num.add(rs.getString("num"));
				user.add(rs.getString("user"));
			}
			board = new Reviews();
			board.setCategory(category);
			board.setTitle(title);
			board.setContent(content);
			board.setImage(image);
			board.setNum(num);
			board.setUser(user);
			articleList= board;
		}catch(Exception ex){
			System.out.println("getBoardList 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return articleList;
	}
	
	public Reviews selectArticleList(int page, int limit, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="select * from reviews WHERE title like ? || user like ? || content like ? order by num desc limit ?,? ";
		Reviews articleList = null;
		Reviews board = null;
		int startrow=(page-1)*8; 
		System.out.println("DAO에서의현재페이지"+page);
		System.out.println("DAO에서의리미트"+limit);
		ArrayList<String> num = new ArrayList<String>();
		ArrayList<String> category = new ArrayList<String>();
		ArrayList<String> title = new ArrayList<String>();
		ArrayList<String> content = new ArrayList<String>();
		ArrayList<String> image = new ArrayList<String>();
		ArrayList<String> user = new ArrayList<String>();
		try{
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setString(3, "%"+keyword+"%");
			pstmt.setInt(4, startrow);
			pstmt.setInt(5, limit);
			rs = pstmt.executeQuery();
			while(rs.next()){
				category.add(rs.getString("category"));
				title.add(rs.getString("title"));
				content.add(rs.getString("content"));
				image.add(rs.getString("imgPath"));
				num.add(rs.getString("num"));
				user.add(rs.getString("user"));
			}
			board = new Reviews();
			board.setCategory(category);
			board.setTitle(title);
			board.setContent(content);
			board.setImage(image);
			board.setNum(num);
			board.setUser(user);
			articleList= board;
			System.out.println("_____"+articleList.getContent().size());
		}catch(Exception ex){
			System.out.println("getBoardList 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}
	
	public boolean deleteReview(int num) {
		System.out.println("num값>"+num);
		String sql = "delete from reviews where num=?";
		int count = 0;
		boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			count = pstmt.executeUpdate();
			if( count >= 1 ) {
				System.out.println("삭제 성공"+"삭제된 행 개수 >"+count);
				con.commit();
				result = true;
				return result;
			} else {
				System.out.println("삭제 실패");
				result = false;
				return result;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			close(rs);
			close(pstmt);
		}
		System.out.println("삭제 실패");
		return false;
	}
	
	public boolean updateReview(Reviews review) {
		System.out.println("updateReviewDAO 입장");
		System.out.println("review.getNum().get(0)>>"+review.getNum().get(0));
		String sql = "update reviews set category=?, title=?, imgPath=?, content=?, imgPath2=?, imgPath3=? where num=?";
		int count = 0;
		boolean result = false;
		try {
			String title = encodeContent(review.getTitle().get(0));
			String content = encodeContent(review.getContent().get(0));
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, review.getCategory().get(0));
			pstmt.setString(2, title);
			pstmt.setString(3, review.getImage().get(0));
			pstmt.setString(4, content);
			if(!review.getImage().get(1).equals("이미지없음")) {
				pstmt.setString(5, review.getImage().get(1));
			} else pstmt.setString(5, null);
			if(!review.getImage().get(2).equals("이미지없음")) {
				pstmt.setString(6, review.getImage().get(2));
			} else pstmt.setString(6, null);
			pstmt.setInt(7, Integer.parseInt(review.getNum().get(0)));
			
			count = pstmt.executeUpdate();
			if( count >= 1 ) {
				System.out.println("수정 성공"+"수정된 행 개수 >"+count);
				con.commit();
				result = true;
				return result;
			} else {
				System.out.println("수정 실패");
				result = false;
				return result;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			close(rs);
			close(pstmt);
		}
		System.out.println("수정 실패");
		return false;
	}
	public int cateListCount(String category) {
		System.out.println("selectListCount keyword 진입");
		int listCount= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			System.out.println("getConnection");
			pstmt=con.prepareStatement("select count(*) from reviews WHERE category like ?");
			pstmt.setString(1, "%"+category+"%");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				listCount=rs.getInt(1);
			}
		}catch(Exception ex){
			System.out.println("getListCount 에러: " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}
		System.out.println("DAO에서의 listcount >>"+listCount);
		return listCount;
	}
	public Reviews cateArticleList(int page, int limit, String category) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="select * from reviews WHERE category like ? order by num desc limit ?,? ";
		Reviews articleList = null;
		Reviews board = null;
		int startrow=(page-1)*8; 
		System.out.println("DAO에서의현재페이지"+page);
		System.out.println("DAO에서의리미트"+limit);
		ArrayList<String> num = new ArrayList<String>();
		ArrayList<String> cate = new ArrayList<String>();
		ArrayList<String> title = new ArrayList<String>();
		ArrayList<String> content = new ArrayList<String>();
		ArrayList<String> image = new ArrayList<String>();
		ArrayList<String> user = new ArrayList<String>();
		try{
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setString(1, "%"+ category +"%");
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
			while(rs.next()){
				cate.add(rs.getString("category"));
				title.add(rs.getString("title"));
				content.add(rs.getString("content"));
				image.add(rs.getString("imgPath"));
				num.add(rs.getString("num"));
				user.add(rs.getString("user"));
			}
			board = new Reviews();
			board.setCategory(cate);
			board.setTitle(title);
			board.setContent(content);
			board.setImage(image);
			board.setNum(num);
			board.setUser(user);
			articleList= board;
			System.out.println("_____"+articleList.getContent().size());
		}catch(Exception ex){
			System.out.println("getBoardList 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}
	
	public int recommendReview(int board_num, String user) {
		String sql = "UPDATE reviews SET recommend=recommend+1 WHERE num = ?";
		String sql1 = "INSERT INTO recduplicateCheck VALUES(?,?)";
		String sql2 = "SELECT user FROM recduplicateCheck WHERE num = ?";
		try {
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getString("user").equals(user)) {
					close(rs);
					close(pstmt);
					return 0;
				}
			}
			
			close(rs);
			close(pstmt);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			int count = pstmt.executeUpdate();
			if(count>0) {
				con.commit();
			}
			close(pstmt);
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, board_num);
			pstmt.setString(2, user);
			pstmt.executeUpdate();
			con.commit();
			
			return count;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		close(rs);
		close(pstmt);
		
		return 0;
	}
	
	public int recCount(int board_num) {
		String sql = "SELECT recommend FROM reviews WHERE num = ?";
		int count = -1;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt("recommend");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);			
		}
		return count;
	}
	
	
//	script,html태그 방지 메소드 시작
	public static String replace(String str, String pattern, String replace) {
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();

		while ((e = str.indexOf(pattern, s)) >= 0) {
		result.append(str.substring(s, e));
		result.append(replace);
		s = e+pattern.length();
		}
		result.append(str.substring(s));
		return result.toString();
		}
	
	public static String encodeContent(String content) {
		String ret = content;
		try {
		ret = replace(ret, "&", "&amp;");
		ret = replace(ret, "<", "&lt;");
		ret = replace(ret, ">", "&gt;");
		} catch (NullPointerException e) {
		e.printStackTrace();
		}
		return ret;
		}

		public static String decodeContent(String content) {
		String ret = content;
		try {
		ret = replace(ret, "&amp;", "&");
		ret = replace(ret, "&lt;", "<");
		ret = replace(ret, "&gt;", ">");
		}
		catch (NullPointerException e){
		e.printStackTrace();
		}
		return ret;
		}
//		script, html태그 방지 메소드끝
}