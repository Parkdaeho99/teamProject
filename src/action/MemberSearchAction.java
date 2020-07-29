package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberSearchService;
import vo.ActionForward;
import vo.Member;

public class MemberSearchAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
		 	response.setContentType("text/html;charset=UTF-8");
		 	PrintWriter out = response.getWriter();
		 	String id = request.getParameter("id");
		 	MemberSearchService mss = new MemberSearchService();
		 	Member member = mss.memberSearch(id);
		 	if(member.getId() == null) {
		 		out.println("<script>");
		 		out.println("alert('없는 회원입니다. 다시 검색하세요.');");
		 		out.println("history.back();");
		 		out.println("</script>");
		 		return null;				
		 	} else {
		 		request.setAttribute("member", member);
		 	}
		 	ActionForward forward = new ActionForward();
		 	forward.setPath("/memberManagerForm.jsp");
		 	return forward;
	}
}