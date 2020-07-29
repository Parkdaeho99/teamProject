package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.IdCheckService;
import vo.ActionForward;

public class IdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
   		boolean joinResult=false;
   		String id = request.getParameter("id");
   		
   		IdCheckService idSVC = new IdCheckService();
   		joinResult = idSVC.idChecksvc(id);
   		
   		ActionForward forward = null;
   		if(joinResult == true){
   			response.setContentType("text/html;charset=UTF-8");
   			PrintWriter out = response.getWriter();
   			out.println("<script>");
   			
   			out.println("alert('사용할 수 없는 아이디입니다.')");
   			out.println("history.back()");
//   			out.println("self.close()");
   			out.println("</script>");
	   	}
   		else{
   			response.setContentType("text/html;charset=UTF-8");
   			PrintWriter out = response.getWriter();
   			out.println("<script>");
   			out.println("alert('사용 가능한 아이디입니다.')");
   			out.println("history.back()");
//   			out.println("self.close()");
   			out.println("</script>");
   			
   		}
   		return forward;
	}

}
