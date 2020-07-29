package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.BoardListAction;
import action.BoardWriteProAction;
import action.CateListAction;
import action.DeleteMemberAction;
import action.DeleteReviewAction;
import action.IdCheckAction;
import action.JoinAction;
import action.LoginAction;
import action.MemberBanAction;
import action.MemberPermitAction;
import action.MemberSearchAction;
import action.RecCountAction;
import action.ReplyAction;
import action.ReplyDeleteAction;
import action.ReplyWriteAction;
import action.ReviewReadAction;
import action.ReviewRecommendAction;
import action.ReviewsAction;
import action.SelectMemberAction;
import action.TotalSearchAction;
import action.UpdateMemberAction;
import action.UpdateReviewAction;
import action.UpdateUserAction;
import vo.ActionForward;

@WebServlet("*.do")
public class Controller extends javax.servlet.http.HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;
		Action action1 = null;

		if(command.equals("/loginForm.do")){
			forward=new ActionForward();
			forward.setPath("/loginForm.jsp");
		} else if(command.equals("/login.do")) {
			action = new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/logout.do")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("./index.do");
		} else if(command.equals("/joinForm.do")) {
			forward=new ActionForward();
			forward.setPath("/joinForm.jsp");
		} else if(command.equals("/joinAction.do")) {
			action = new JoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/index.do")) {
			action = new ReviewsAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/reviewWriteForm.do")) {
			forward=new ActionForward();
			forward.setPath("/reviewWriteForm.jsp");
			forward.setRedirect(false);
		} else if(command.equals("/reviewReadForm.do")) {
			action = new ReviewReadAction();
			action1 = new ReplyAction();
			try {
				forward = action.execute(request, response);
				forward = action1.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/replyWrite.do")) {
			action = new ReplyWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/boardWritePro.do")) {
			action = new BoardWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/boardList.do")) {
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/deleteReview.do")) {
			action = new DeleteReviewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/updateReview.do")) {
			action = new UpdateReviewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/replyDelete.do")) {
			action = new ReplyDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/selectMember.do")) {
			action = new SelectMemberAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/userUpdate.do")) {
			action = new UpdateUserAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/totalSearch.do")) {
			action = new TotalSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if(command.equals("/idCheck.do")) {
			action = new IdCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/cateList.do")) {
			action = new CateListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/recommendReview.do")) {
			action = new ReviewRecommendAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/recCount.do")) {
			action = new RecCountAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/memberSearchForm.do")) {
			forward=new ActionForward();
			forward.setPath("/memberSearchForm.jsp");
		} else if(command.equals("/memberSearch.do")) {
			action = new MemberSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/updateMember.do")) {
			action = new UpdateMemberAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/deleteMember.do")) {
			System.out.println("삭제 컨트롤");
			action = new DeleteMemberAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/memberBan.do")) {
			action = new MemberBanAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/memberPermit.do")) {
			action = new MemberPermitAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(forward != null){
			
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher=
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
			
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}   
	
}