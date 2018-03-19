package webhoinghi.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import webhoinghi.dao.AdminDAO;
import webhoinghi.model.Admin;
import webhoinghi.model.News;
import webhoinghi.service.NewsService;
import webhoinghi.utils.PathUtils;
import webhoinghi.service.AdminService;;

@Controller
public class HomeController {
	
	@Autowired 
	private NewsService newsservice;
	
	@Autowired 
	private AdminService adminservice;
	
	@GetMapping("/")
	public String home(HttpSession session,HttpServletRequest request, ModelMap mm) 
	{
		session = request.getSession();
		List<News> lstnews = newsservice.findAll();
		mm.addAttribute("listnews", lstnews);
		return "homepage";
	}
	
	@GetMapping("/tinmoicapnhat")
	public String newslist(HttpSession session,HttpServletRequest request, ModelMap mm) 
	{
		session = request.getSession();
		List<News> lstnews = newsservice.findAll();
		mm.addAttribute("listnews", lstnews);
		return "newslist";
	}
	
	@PostMapping("/addNews")
	public String addNews(@ModelAttribute News news,HttpSession session,HttpServletRequest request, ModelMap mm)
	{
		mm.addAttribute("id", news.getId());
		return "editnews";
	}
	
	@PostMapping("/editNews")
	public String editnews(@ModelAttribute News news,HttpSession session,HttpServletRequest request, ModelMap mm) 
	{
		mm.addAttribute("id",news.getId());
		return "editnews";
	}
	
	@GetMapping("/login")
	public String login() 
	{
		return "login";
	}
	
	@PostMapping("/loginverify")
	public String login(@ModelAttribute Admin admin,HttpSession session,HttpServletRequest request, ModelMap mm) 
	{
		session= request.getSession();
		boolean f = adminservice.validatelogin(admin.getUsername(), admin.getPassword());
		if (f==true) session.setAttribute("isadmin", "true");
		else session.setAttribute("isadmin", "false");
		return "redirect:/";
	}
	
	@PostMapping("/deleteNews")
	public String deleteNews(@ModelAttribute News news)
	{
		newsservice.delete(news.getId());
		return "redirect:/tinmoicapnhat";
	}
	
	@PostMapping("/saveNews")
	public String saveNews(@ModelAttribute News news, @RequestParam(name="image") MultipartFile file,HttpServletRequest request) throws Exception
	{
		if (file.getSize() != 0) {
            File dir = new File(request.getServletContext().getRealPath("/uploads/idImg/"));

            //Create folders if not exist
            if (!dir.exists()) dir.mkdirs();

            String fileExtension = PathUtils.GetFileExtension(file.getOriginalFilename());
            File newFile = new File(dir.getAbsolutePath() + "/" + file.getOriginalFilename());
            file.transferTo(newFile);
            
        }
		news.setImgurl(newsservice.uploadimg(file));
		news.setDate_created(new Date());
		newsservice.save(news);
		return "redirect:/tinmoicapnhat";
	}
		
	
}
