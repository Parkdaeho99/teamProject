package action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.ReplyWriteService;
import vo.ActionForward;
import vo.Reply;

public class ReplyWriteAction implements Action{
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) 
	 	throws Exception{
		 	System.out.println("댓글쓰기Action");
		 	HttpSession session = request.getSession();
	   		Reply reply = new Reply();
	   		ArrayList<String> id = new ArrayList<String>();
	   		ArrayList<String> content = new ArrayList<String>();
	   		ArrayList<Integer> board_num = new ArrayList<Integer>();
	   		ArrayList<String> date = new ArrayList<String>();
	   		id.add((String)session.getAttribute("id"));
	   		content.add((String)request.getParameter("reply_content"));
	   		board_num.add(Integer.parseInt(request.getParameter("board_num")));
	   		
	   		Date cal = Calendar.getInstance().getTime();
	   		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd / HH:mm:ss");
	   		String datestr = sdf.format(cal.getTime());
	   		date.add(datestr);
	   		
	   		reply.setReply_user(id);
	   		reply.setReply_content(content);
	   		reply.setBoard_num(board_num);
	   		reply.setReply_date(date);
		 	ReplyWriteService replyWriteService = new ReplyWriteService();
	   		replyWriteService.writeReply(reply);
	   		ActionForward forward = new ActionForward();
	   		forward.setPath("./reviewReadForm.do?board_num="+Integer.parseInt(request.getParameter("board_num")));
	   		forward.setRedirect(true);
	   		return forward;
	}
}