package com.skilldistillery.morebetterapp.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.morebetterapp.data.ArticleDAO;
import com.skilldistillery.morebetterapp.data.CategoryDAO;
import com.skilldistillery.morebetterapp.data.EventDAO;
import com.skilldistillery.morebetterapp.data.UserDAO;
import com.skilldistillery.morebetterapp.entities.Event;
import com.skilldistillery.morebetterapp.entities.User;

@Controller
public class HomeController {

	@Autowired
	private ArticleDAO articleDao;
	@Autowired
	private CategoryDAO categoryDao;
	@Autowired
	private EventDAO eventDao;
	@Autowired
	private UserDAO userDao;

	@RequestMapping(path = { "/", "home.do" })
	public String home() {
		return "index";
	}

	@RequestMapping(path = "addUserToEvent.do", params = { "uId", "eId" }, method = RequestMethod.POST)
	public ModelAndView addUserToEvent(@RequestParam Integer uId, Integer eId, HttpSession session)
			throws SQLException {
		ModelAndView mv = new ModelAndView();
		List<Event> events = userDao.addUserToEvent(uId, eId);
		mv.addObject("eventsToAttend", events);

		mv.setViewName("userEventsList"); // JSP for adding user

		return mv;

	}

	@RequestMapping(path = "userEvents.do", method = RequestMethod.GET)
	public ModelAndView userEvent(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User updatedUser = (User) (session.getAttribute("loggedInUser"));
		User refreshedUser = userDao.findUserById(updatedUser.getId());

		session.setAttribute("loggedInUser", refreshedUser);
		mv.addObject("eventsToAttend", refreshedUser.getEventsAttended());

		mv.setViewName("userEventsList");
		return mv;
	}

	@RequestMapping(path = "removeFromEvent.do", params = { "uId", "eId" }, method = RequestMethod.POST) // cannot
																											// remove																							// id=1
	public ModelAndView removeUserFromEvent(@RequestParam Integer uId, @RequestParam Integer eId, HttpSession session)
			throws SQLException {
		ModelAndView mv = new ModelAndView();
		List<Event> events = userDao.deleteUserFromEvent(uId, eId);

		mv.setViewName("redirect:userEvents.do"); // JSP for adding user

		return mv;

	}

}
