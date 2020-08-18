<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.inquire.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.inquire.form.label.moment" path="moment"/>
	<acme:form-moment code="authenticated.inquire.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.inquire.form.label.description" path="description"/>
	<acme:form-money code="authenticated.inquire.form.label.min" path="min"/>
	<acme:form-money code="authenticated.inquire.form.label.max" path="max"/>
	<acme:form-textarea code="authenticated.inquire.form.label.email" path="email"/>
	
	<security:authorize access="hasRole('Administrator')">
		<acme:form-submit test="${command == 'show'}" code="administrator.inquiry.form.button.update"
			action="/administrator/inquiry/update/" />
		<acme:form-submit test="${command == 'show'}" code="administrator.inquiry.form.button.delete"
			action="/administrator/inquiry/delete/" />
		<acme:form-submit test="${command == 'update'}" code="administrator.inquiry.form.button.update"
			action="/administrator/inquiry/update/" />
		<acme:form-submit test="${command == 'delete'}" code="administrator.inquiry.form.button.delete"
			action="/administrator/inquiry/delete/" />
	</security:authorize>
	
	<acme:form-return code="authenticated.inquire.form.button.return"/>
</acme:form>