package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReviewReadService;
import vo.ActionForward;
import vo.Reviews;

public class ReviewReadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("리뷰보기 액션 이동 성공");
		HttpSession session = request.getSession();
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		System.out.println(board_num);
		ReviewReadService rrs = new ReviewReadService();
		Reviews review = rrs.readBoard(board_num);
		System.out.println(review.getContent().get(0));
		session.setAttribute("review",review);
   	    ActionForward forward = new ActionForward();
   		forward.setPath("reviewReadForm.jsp?board_num"+board_num);
   		return forward;
	}

}
