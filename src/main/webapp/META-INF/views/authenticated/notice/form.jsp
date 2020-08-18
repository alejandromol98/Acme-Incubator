<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-url code="authenticated.notice.form.label.headerPicture" path="headerPicture"/>
	<acme:form-textbox code="authenticated.notice.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.notice.form.label.moment" path="moment"/>
	<acme:form-moment code="authenticated.notice.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.notice.form.label.body" path="body"/>
	<acme:form-url code="authenticated.notice.form.label.link" path="link1"/>
	<acme:form-url code="authenticated.notice.form.label.link" path="link2"/>
	
	<acme:form-return code="authenticated.notice.form.button.return"/>
</acme:form>