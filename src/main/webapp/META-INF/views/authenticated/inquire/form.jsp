<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.inquire.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.inquire.form.label.moment" path="moment"/>
	<acme:form-moment code="authenticated.inquire.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.inquire.form.label.description" path="description"/>
	<acme:form-money code="authenticated.inquire.form.label.min" path="min"/>
	<acme:form-money code="authenticated.inquire.form.label.max" path="max"/>
	<acme:form-textarea code="authenticated.inquire.form.label.email" path="email"/>
	
	<acme:form-return code="authenticated.inquire.form.button.return"/>
</acme:form>