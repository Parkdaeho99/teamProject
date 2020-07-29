package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ReviewsService;
import vo.ActionForward;
import vo.Reviews;

public class ReviewsAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
	   		System.out.println("액션진입");
	   		ReviewsService reviewsService = new ReviewsService();
	   		Reviews reviews = reviewsService.selectTitle();
	   		request.setAttribute("reviews",reviews);
	   	    ActionForward forward = new ActionForward();
//	   		forward.setRedirect(true);
	   		forward.setPath("index.jsp");
	   		return forward;
	}
}