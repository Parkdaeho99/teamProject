package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewRecommendService;
import vo.ActionForward;

public class ReviewRecommendAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("리뷰 추천 액션 이동 성공");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String user = (String)session.getAttribute("id");
		System.out.println(board_num);
		ReviewRecommendService rrs = new ReviewRecommendService();
		int count = rrs.reviewRecommend(board_num, user);
		request.setAttribute("recDuplicationCheck", count);
		System.out.println("얘가 0이면 중복이라 추천안한거임>>"+request.getAttribute("recDuplicationCheck"));
		out.println(count);
		
   		return null;
	}

}
