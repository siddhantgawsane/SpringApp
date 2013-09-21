package com.springmvcproject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.GenericServlet;//.getServletContext
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

@Controller
public class UserController {
	
	List<Employee> results = null;
	//response.getWriter().println(uname+pass);
	Query q = null;
	PersistenceManager pm = null;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getSignInPage(ModelMap model) {
 
		return "signin";
 	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String getSignUpPage(ModelMap model) {
 
		return "signup";
 	}
	
	@RequestMapping(value = "/serve", method = RequestMethod.GET)
	public void serveBlob(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
        try {
			blobstoreService.serve(blobKey, res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
	
	
	@RequestMapping(value = "/viewimages", method = RequestMethod.GET)
	public String getImagesPage(ModelMap model) {
 
		return "viewimages";
 	}
	
	@RequestMapping(value = "/viewvideos", method = RequestMethod.GET)
	public String getVideosPage(ModelMap model) {
 
		return "viewvideos";
 	}

		@RequestMapping(value = "/recommend", method = RequestMethod.GET)
	public String getEmailSendingPage(ModelMap model) {
 
		return "recommend";
 	}
	
	@RequestMapping(value = "/viewusers", method = RequestMethod.GET)
	public void getUserDetails(ModelMap model, HttpServletRequest req, HttpServletResponse res) {
		
		List<Employee> results = null;
		Query q = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			q = pm.newQuery(Employee.class); //,"username == '*'"
			results = (List<Employee>) q.execute();
			req.setAttribute("UsersList", results); 
		}catch(Exception e){
			e.printStackTrace(); 
		}finally {
			pm.close();
		}
		if(req.getAttribute("UsersList")!=null)
		{
			RequestDispatcher rd = req.getRequestDispatcher("/users");
			try {
				rd.forward(req, res);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
 	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String getUserDetailsPage(ModelMap model, HttpServletRequest req, HttpServletResponse res) {
 
		//System.out.println(req.getAttribute("UsersList"));
		return "viewusers";
 	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String getUpdatePage(ModelMap model) {
 
		return "update";
 	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView UpdateUserDetails( HttpServletRequest request, ModelMap model) {
		
		HttpSession session = request.getSession(true);
		String uname = request.getParameter("uname");
	    String pass = request.getParameter("password");
	    String fname = request.getParameter("fname");
	    String lname = request.getParameter("lname");
	    String email = request.getParameter("email"); 
	    //zYpHSk3LfbXwlI5Y23k_Vw
	    String bks = "AMIfv96lIbCuj448uM2WmWgAWGvX7dfPG38HYBlzXdxTrTElNNNLqO2Ki7-JkdXusFLmm5PA4tk8iEp46vTdPFd0XylgKujJhFWXoMCVSbrXGHfpG0v6Kz71mXDC9RJEkjg_k6qhDczBeon5W0NNMtrLHJSjbQhuMTn2_DLl-enafHwgHY_VNsQ";
	    
	    Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(request);
        BlobKey blobKey = blobs.get("mypic");
        PersistenceManager pm = PMF.get().getPersistenceManager();
	    
	    try {
	    	Query q =pm.newQuery(Employee.class,"username == '"+uname+"'");
	    	List<Employee> results = (List<Employee>) q.execute();
	        Employee u = pm.getObjectById(Employee.class,results.get(0).getId()); 
	        u.setPassword(pass);
	        u.setFirstName(fname);
	        u.setLastName(lname);
	        u.setEmailId(email);
	        if (blobs!=null) 
	        {
	        	bks = blobKey.getKeyString();
	        	u.setBlobKey(blobKey.getKeyString());
	        }
//	        response.getWriter().println("Updated successfully");
//			response.sendRedirect("/HomePage.jsp");
	        session.setAttribute("fname", u.getFirstName());
			session.setAttribute("lname", u.getLastName());
			session.setAttribute("emailid", u.getEmailId());
			session.setAttribute("logins", u.getLogins());
			session.setAttribute("username",uname);
			session.setAttribute("blob-key", u.getBlobKeyString());
	    } 
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally {
	        pm.close();
	    }   

	    return new ModelAndView("redirect:home/"+uname);
 	}
	
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String getSignOutPage(ModelMap model) {
 
		return "signout";
 	}
	@RequestMapping(value = "/home/{uname}", method = RequestMethod.GET)
	public String getHomePage(@PathVariable String uname, HttpServletRequest request, ModelMap model) {
		
		pm = PMF.get().getPersistenceManager();
		
		System.out.println(uname);
		try {
			Query q =pm.newQuery(Employee.class,"username == '"+uname+"'");
			results = (List<Employee>) q.execute();
/*			if(!results.isEmpty())
			{
				model.addAttribute("userobj", results.get(0));
			}
*/		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			pm.close();
		}   
		return "home";
 	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signUp(HttpServletRequest request, ModelMap model) {
 
		String username = request.getParameter("uname");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String password= request.getParameter("password");
//		String blobkey= request.getParameter("blobkey");
		String email= request.getParameter("email"); //			zYpHSk3LfbXwlI5Y23k_Vw
		String bks = "AMIfv96lIbCuj448uM2WmWgAWGvX7dfPG38HYBlzXdxTrTElNNNLqO2Ki7-JkdXusFLmm5PA4tk8iEp46vTdPFd0XylgKujJhFWXoMCVSbrXGHfpG0v6Kz71mXDC9RJEkjg_k6qhDczBeon5W0NNMtrLHJSjbQhuMTn2_DLl-enafHwgHY_VNsQ";
		
		Employee u = new Employee();
		
		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(request);
		if(blobs!=null)
		{
			BlobKey blobKey = blobs.get("mypic");
			bks = blobKey.getKeyString();
		}
		
		u.setBlobKey(bks);
		u.setUsername(username);
		u.setFirstName(fname);
		u.setLastName(lname);
		u.setPassword(password);
		
		u.setEmailId(email);
		u.setLoginDate(new Date());
 
		pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(u);
		} finally {
			pm.close();
		}
 
		return new ModelAndView("redirect:/");

	}
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ModelAndView signin(HttpServletRequest request, ModelMap model) {
		String uname = request.getParameter("uname");
		String pass = request.getParameter("password");
		
		pm = PMF.get().getPersistenceManager();
		HttpSession session = request.getSession(true);
		System.out.println(uname+pass);
		try {
			q = pm.newQuery(Employee.class,"username == '"+uname+"' & password == '" +pass+"'");
			results = (List<Employee>) q.execute();

			if (!results.isEmpty()) {
				System.out.println("Logged in");
				results.get(0).incrementLogins();
				Employee u = pm.getObjectById(Employee.class,results.get(0).getId());
				u.setLoginDate(new Date());
				session.setAttribute("fname", u.getFirstName());
				session.setAttribute("lname", u.getLastName());
				session.setAttribute("emailid", u.getEmailId());
				session.setAttribute("logins", u.getLogins());
				session.setAttribute("username",uname);
				session.setAttribute("blob-key", u.getBlobKeyString());
				return new ModelAndView("redirect:home/"+uname);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		finally {
			pm.close();
		}
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/reminderEmail", method = RequestMethod.GET)
	public void sendReminderEmail(ModelMap model) {
 
		Date currentDate=new Date();
		pm = PMF.get().getPersistenceManager();
		Queue queue = QueueFactory.getQueue("optimize-queue");
		try {
			currentDate = new SimpleDateFormat( "yyyyMMdd" ).parse( "20130905" );
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			q = pm.newQuery(Employee.class,"loginDate < '"+currentDate+"'");
			results = (List<Employee>) q.execute();

			if (!results.isEmpty()) {
//				resp.getWriter().println("Found");
				for(Employee u : results)
				{	//+"&loginDate="+u.getLoginDate().toString()
					TaskOptions taskOptions = TaskOptions.Builder.withUrl("/sendEmail?emailId="+u.getEmailId()).method(Method.GET);
					queue.add(taskOptions);
					//sendEmail(u.getEmailId());
					System.out.println("\nSuccessfully created a Task in the Queue");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		finally {

			//q.closeAll();
			pm.close();
		}
		//return "signout";
 	}

	@RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
	private void sendEmail(HttpServletRequest req) {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		Session session1 = Session.getDefaultInstance(props, null);
		String emailId = req.getParameter("emailId");
		String msgBody = "Dear User, You have not logged in to springapplication1501.appspot.com since 7 Days!! Please login and enjoy a variety of services";
		try {
			Message msg = new MimeMessage(session1);
			msg.setFrom(new InternetAddress("siddhant.g@a-cti.com","springapplication1501.appspot.com Admin"));
			msg.addRecipient(Message.RecipientType.CC,new InternetAddress(emailId,"Mr. User"));
			msg.setSubject("Check out this cool new web-app!");
			msg.setText(msgBody);
			Transport.send(msg);
			
			//e.getWriter().println("Email sent successfully");

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
