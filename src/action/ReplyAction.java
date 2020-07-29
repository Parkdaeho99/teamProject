package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReplyService;
import vo.ActionForward;
import vo.Reply;

public class ReplyAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
		 	System.out.println("reply액션 진입");
		 	String str = "";
		 	ArrayList<String> temp = new ArrayList<String>();
	   		HttpSession session = request.getSession();
		 	ReplyService replyService = new ReplyService();
	   		Reply reply = new Reply();
	   		reply = replyService.selectReply(Integer.parseInt(request.getParameter("board_num")));
	   		if(reply != null) {
		   		for(int i=0; i<reply.getReply_content().size(); i++) {
		   			str = reply.getReply_content().get(i);
		   			temp.add(str.replace("\r\n","<br>"));
		   		}	   		
		   		reply.setReply_content(temp);
	   		}
	   		session.setAttribute("reply", reply);
	   	    ActionForward forward = new ActionForward();
	   		forward.setPath("reviewReadForm.jsp?board_num"+Integer.parseInt(request.getParameter("board_num")));
	   		return forward;
	}
}