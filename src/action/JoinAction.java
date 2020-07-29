package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.JoinService;
import vo.ActionForward;
import vo.Member;

public class JoinAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
		 	
		 	Member member=new Member();
	   		boolean joinResult=false;
	   		
	   		member.setName(request.getParameter("name"));
	   		member.setId(request.getParameter("id"));
	   		member.setPass(request.getParameter("pass"));
	   		member.setPassT(request.getParameter("passT"));
	   		member.setPhone(request.getParameter("phone"));
	   		String pcall = request.getParameter("pcall1")+"-"+request.getParameter("pcall2")+"-"+request.getParameter("pcall3");
	   		member.setpCall(pcall);
	   		member.setGender(request.getParameter("gender"));
	   		member.setBirth(request.getParameter("birth"));
	   		member.seteAgree(request.getParameter("eAgree"));
	   		member.setsAgree(request.getParameter("sAgree"));
	   		
	   		JoinService memberJoinService = new JoinService();
	   		joinResult=memberJoinService.joinMember(member);
	   		
	   		ActionForward forward = null;
	   		if(joinResult==false){
	   			response.setContentType("text/html;charset=UTF-8");
	   			PrintWriter out = response.getWriter();
	   			out.println("<script>");
	   			out.println("alert('에러')");
	   			out.println("history.back()");
	   			out.println("</script>");
		   	}
	   		else{
	   	    forward = new ActionForward();
	   		forward.setRedirect(true);
	   		forward.setPath("./index.do");
	   		}
	   		return forward;
	}
}