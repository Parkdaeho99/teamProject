package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.RecCountService;
import vo.ActionForward;

public class RecCountAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("리뷰 추천수 조회 액션 이동 성공");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		System.out.println(board_num);
		RecCountService rrs = new RecCountService();
		int recCount = rrs.recCount(board_num);
		System.out.println("해당 리뷰의 추천수는 >>"+recCount);
		request.setAttribute("recCount", recCount);
		ActionForward forward = new ActionForward();
		forward.setPath("reviewReadForm.jsp?board_num"+board_num);
   		return forward;
	}

}
