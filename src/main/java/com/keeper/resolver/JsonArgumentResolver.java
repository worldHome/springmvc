package com.keeper.resolver;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class JsonArgumentResolver implements HandlerMethodArgumentResolver {
	private static final String REQUEST_BODY_ATTR = "REQUEST_BODY_ATTR";
	private static final String ENCODING = "UTF-8";

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mvcContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest
				.getNativeRequest(HttpServletRequest.class);
		if (!request.getContentType().contains("json")) {
			return null;
		}
		Object json = this.getRequestBody(request);
		
		return null;
	}

	public Object getRequestBody(HttpServletRequest request) {
		try {
			Object json = request.getAttribute(REQUEST_BODY_ATTR);
			if (json == null) {
				json = IOUtils.toString(request.getInputStream(), ENCODING);
				request.setAttribute(REQUEST_BODY_ATTR, json);
				return json;
			}
			return json;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(JsonArg.class) != null;
	}

}
