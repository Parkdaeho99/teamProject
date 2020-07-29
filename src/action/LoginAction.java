package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LoginService;
import vo.ActionForward;

public class LoginAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
		 	response.setCharacterEncoding("UTF-8");
		 	response.setContentType("text/html; charset=UTF-8");
		 	PrintWriter out = response.getWriter();
		 	HttpSession session = request.getSession();
		 	String id = request.getParameter("id");
		 	String pass = request.getParameter("passwd");
		 	LoginService loginService = new LoginService();
		 	int result = loginService.getLoginResult(id, pass);
		 
		 	ActionForward forward = new ActionForward();
		 	if(result == 1) {
		 		System.out.println("로그인 성공");
		 		session.setAttribute("id",id);
		 		forward.setPath("./index.do");
		 		forward.setRedirect(true);
		 	} else if(result == 0){
		 		out.print("<script>alert('존재하지 않는 계정입니다.');");
		 		out.print("history.back()");
		 		out.print("</script>");
		 		forward = null;
		 	} else if(result == 2) {
		 		out.print("<script>alert('비밀번호가 일치하지 않습니다.');");
		 		out.print("history.back()");
		 		out.print("</script>");
		 		forward = null;
		 	} else if(result == 3) {
				out.print("<script>alert('해당 계정은 밴 처리되었습니다. 관리자에게 문의해주시길 바랍니다.');");
				out.print("history.back()");
				out.print("</script>");
				forward = null;
			}
		 	
	   		return forward;
	}
}