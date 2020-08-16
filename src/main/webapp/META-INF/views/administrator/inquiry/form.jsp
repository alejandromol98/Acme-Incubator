<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.inquiry.form.label.title" path="title"/>	
	<acme:form-moment code="administrator.inquiry.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.inquiry.form.label.description" path="description"/>
	<acme:form-money code="administrator.inquiry.form.label.min" path="min"/>
	<acme:form-money code="administrator.inquiry.form.label.max" path="max"/>
	<acme:form-url code="administrator.inquiry.form.label.email" path="email" />
		
	<acme:form-submit test="${command == 'create'}" 
		code="administrator.inquiry.form.button.create" 
		action="/administrator/inquiry/create"/>		
	
	<acme:form-return code="administrator.inquiry.form.button.return"/>
</acme:form>