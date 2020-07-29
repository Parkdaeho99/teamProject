package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ReplyDeleteService;
import vo.ActionForward;

public class ReplyDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("댓글삭제액션 입장");
		int num = Integer.parseInt(request.getParameter("reply_num"));
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		ReplyDeleteService rds = new ReplyDeleteService();
		rds.replyDelete(num);
		ActionForward forward = new ActionForward();
		forward.setPath("./reviewReadForm.do?board_num="+board_num);
		forward.setRedirect(true);
		System.out.println("설정된 패스 > "+forward.getPath());
		return forward;
	}

}
