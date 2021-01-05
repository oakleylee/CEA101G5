package com.filter;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.MemVO;

public class StoreLoginFilter implements Filter {

	ServletContext context = null;

	public void init (FilterConfig config) {
		context = config.getServletContext();
	}
	
	public void destory() {
		context = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		MemVO memVO = (MemVO)session.getAttribute("memLogin");
		if(memVO.getMemCondition() != 4) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/front-store-end/restaurant/storeNoAuth.jsp");
			return;
		
		}else {
			chain.doFilter(request, response);
		}	
	}
}