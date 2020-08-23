<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.discussionForum.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.discussionForum.form.label.moment" path="moment"/>
	<acme:form-textarea code="authenticated.discussionForum.form.label.users" path="users"/>
	
	<acme:form-textbox code="authenticated.discussionForum.form.label.invRoundTicker" path="invRoundTicker"/>
	
	<acme:form-hidden path="id"/> 
	<acme:form-submit code="authenticated.discussionForum.form.label.messages" method="get" action="/authenticated/message/list?id=${id}" />
	
	<acme:form-return code="authenticated.discussionForum.form.button.return"/>
</acme:form>