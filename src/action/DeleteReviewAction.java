package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DeleteReviewService;
import vo.ActionForward;

public class DeleteReviewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DeleteReviewAction 입장");
		PrintWriter out = response.getWriter();
		int num = Integer.parseInt(request.getParameter("board_num"));
		boolean deleteResult = false;
		DeleteReviewService drs = new DeleteReviewService();
		deleteResult = drs.deleteReview(num);
		if(!deleteResult){
			out.println("<script>");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		else{
			out.println("<script>");
			out.println("history.go(-2);");
			out.println("</script>");
			out.close();
		}
		ActionForward forward = new ActionForward();
		
		
		return forward;
	}

}
