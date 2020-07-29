package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.SelectMemberService;
import vo.ActionForward;
import vo.Member;

public class SelectMemberAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member member = new Member();
		String id = request.getParameter("id");
		SelectMemberService ums = new SelectMemberService();
		member = ums.selectMember(id);
		request.setAttribute("member", member);
		ActionForward forward = new ActionForward();
		forward.setPath("/myPage.jsp");
		return forward;
	}
}
