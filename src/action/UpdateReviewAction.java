package action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.UpdateReviewService;
import vo.ActionForward;
import vo.Reviews;

public class UpdateReviewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		System.out.println("UpdateReviewAction 입장");
		Date cal = Calendar.getInstance().getTime();
   		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
   		String datestr = sdf.format(cal.getTime());
   		
		HttpSession session = request.getSession();
		Reviews review = null;
		String saveFolder="c:/boardUpload";
		int fileSize=5*1024*1024;
		MultipartRequest multi=new MultipartRequest(request,
				saveFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		review = new Reviews();
		ArrayList<String> num = new ArrayList<String>();
		num.add(request.getParameter("board_num"));
		ArrayList<String> category = new ArrayList<String>();
		category.add(multi.getParameter("category"));
		ArrayList<String> title = new ArrayList<String>();
		title.add(multi.getParameter("title"));
		ArrayList<String> image = new ArrayList<String>();
		image.clear();
		if(multi.getFilesystemName("image") != null) {
			File oldFile = new File(saveFolder+"/"+multi.getFilesystemName("image"));
			File newFile = new File(saveFolder+"/"+category.get(0)+"_"+datestr+"_"+multi.getFilesystemName("image"));
			oldFile.renameTo(newFile);
			image.add(category.get(0)+"_"+datestr+"_"+multi.getFilesystemName("image"));
			System.out.println(image.get(0));
		}
		if(multi.getFilesystemName("image2") != null) {
			File oldFile = new File(saveFolder+"/"+multi.getFilesystemName("image2"));
			File newFile = new File(saveFolder+"/"+category.get(0)+"_"+datestr+"_"+multi.getFilesystemName("image2"));
			oldFile.renameTo(newFile);
			image.add(category.get(0)+"_"+datestr+"_"+multi.getFilesystemName("image2"));
		} else image.add("이미지없음");
		if(multi.getFilesystemName("image3") != null) {
			File oldFile = new File(saveFolder+"/"+multi.getFilesystemName("image3"));
			File newFile = new File(saveFolder+"/"+category.get(0)+"_"+datestr+"_"+multi.getFilesystemName("image3"));
			oldFile.renameTo(newFile);
			image.add(category.get(0)+"_"+datestr+"_"+multi.getFilesystemName("image3"));		
		} else image.add("이미지없음");
		
		ArrayList<String> user = new ArrayList<String>();
		user.add((String)session.getAttribute("id"));
		ArrayList<String> content = new ArrayList<String>();
		content.add(multi.getParameter("content"));
		review.setCategory(category);
		review.setTitle(title);
		review.setImage(image);
		review.setUser(user);
		review.setContent(content);
		review.setNum(num);
		UpdateReviewService updateReviewService = new UpdateReviewService();
		boolean isWriteSuccess = updateReviewService.updateReview(review);

		if(!isWriteSuccess){
			response.setContentType("text/html;charset=UTF-8");
			out.println("<script>");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		else{
			out.println("<script>");
			out.println("history.go(-3);");
			out.println("</script>");
			out.close();
		}

		return null;
	}

}
