<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.discussionForum.form.label.title" path="title"/>
	
	<jstl:if test="${command != 'create' }">
			<acme:form-moment code="authenticated.discussionForum.form.label.moment" path="moment" readonly="true"/>
	</jstl:if>
	
	<acme:form-textarea code="authenticated.discussionForum.form.label.users" path="users"/>
	
	<jstl:if test="${command != 'create' }">
		<acme:form-textbox code="authenticated.discussionForum.form.label.invRoundTicker" path="invRoundTicker" readonly="true"/>
	</jstl:if>
	
	<acme:form-textbox code="authenticated.discussionForum.form.label.author" path="authorUsername" readonly="true" />
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.discussionForum.form.button.create" 
		action="/authenticated/discussion-forum/create?invId=${invId}"/>
	
	<jstl:if test="${command != 'create' }">
		<acme:form-hidden path="id"/> 
		<acme:form-submit code="authenticated.discussionForum.form.label.messages" method="get" action="/authenticated/message/list?id=${id}" />
	</jstl:if>
	
	<jstl:if test="${command == 'show' && isAuthor == true}">
		<acme:form-submit code="authenticated.discussionForum.form.button.delete" action="/authenticated/discussion-forum/delete"/>
	</jstl:if>
	<jstl:if test="${command == 'delete'}">
		<acme:form-submit code="authenticated.discussionForum.form.button.delete" action="/authenticated/discussion-forum/delete"/>
	</jstl:if>
	<jstl:if test="${command == 'show' && isAuthor == true}">
		<acme:form-submit code="authenticated.discussionForum.form.button.update" action="/authenticated/discussion-forum/update"/>
	</jstl:if>
	<jstl:if test="${command == 'update' && isAuthor == true}">
		<acme:form-submit code="authenticated.discussionForum.form.button.update" action="/authenticated/discussion-forum/update"/>
	</jstl:if>
	
	<acme:form-return code="authenticated.discussionForum.form.button.return"/>
</acme:form>