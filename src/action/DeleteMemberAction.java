package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.DeleteMemberService;
import vo.ActionForward;

public class DeleteMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		System.out.println("삭제 액션"+id);
		boolean deleteResult = false;
		DeleteMemberService drs = new DeleteMemberService();
		HttpSession session = request.getSession();
		deleteResult = drs.deleteMember(id);
		ActionForward forward = new ActionForward();
		if(deleteResult) {
			System.out.println("삭제 성공");
			session.invalidate();
			forward.setPath("/index.do");
		} else {
			System.out.println("삭제 실패");
			forward.setPath("/index.do?id="+id);
		}
		
		return forward;
	}

}
