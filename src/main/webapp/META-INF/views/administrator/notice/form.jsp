<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-url code="administrator.notice.form.label.headerPicture" path="headerPicture"/>
	<acme:form-textbox code="administrator.notice.form.label.title" path="title"/>	
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="administrator.notice.form.label.moment" path="moment" readonly="true"/>
	</jstl:if>
	<acme:form-moment code="administrator.notice.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.notice.form.label.body" path="body"/>
	<acme:form-url code="administrator.notice.form.label.link" path="link1"/>
	<acme:form-url code="administrator.notice.form.label.link" path="link2"/>
	
	<jstl:if test="${command == 'create'}">
		<acme:form-checkbox code="anonymous.user-account.label.accept" path="accept" />
	</jstl:if>
		
	<acme:form-submit test="${command == 'create'}" 
		code="administrator.notice.form.button.create" 
		action="/administrator/notice/create"/>		
	
	<acme:form-return code="administrator.notice.form.button.return"/>
</acme:form>