package com.test.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator;

@Component("viewNameTranslator")
public final class MediaTypeRequestToViewTranslator implements RequestToViewNameTranslator {
	
	@Autowired
	private ContentNegotiationManager contentNegotiationManager;
	
	@SuppressWarnings("UnusedDeclaration")
	private DefaultRequestToViewNameTranslator defaultTranslator = new DefaultRequestToViewNameTranslator();
	private List<String> ignoredTypes = Arrays.asList("text/html");
	
	@Override
	public String getViewName(HttpServletRequest request){
		// TODO Auto-generated method stub
		
		String mediaType = resolveMediaType(request);
		String viewName = defaultTranslator.getViewName(request);
		
		return viewName + mediaType;
	}

	private String resolveMediaType(HttpServletRequest request) {
		// TODO Auto-generated method stub
		try {
			List<MediaType> types = contentNegotiationManager.resolveMediaTypes(new ServletWebRequest(request));
			String type = types == null || types.size()==0 ? "" : types.get(0).toString();
			return ignoredTypes.contains(type) ? "" : ";" + type;
		} catch (HttpMediaTypeNotAcceptableException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	
}
