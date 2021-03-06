<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.overture.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.overture.form.label.moment" path="moment"/>
	<acme:form-moment code="authenticated.overture.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.overture.form.label.description" path="description"/>
	<acme:form-money code="authenticated.overture.form.label.min" path="min"/>
	<acme:form-money code="authenticated.overture.form.label.max" path="max"/>
	<acme:form-textarea code="authenticated.overture.form.label.email" path="email"/>
	
	<security:authorize access="hasRole('Administrator')">
		<acme:form-submit test="${command == 'show'}" code="administrator.overture.form.button.update"
			action="/administrator/overture/update/" />
		<acme:form-submit test="${command == 'show'}" code="administrator.overture.form.button.delete"
			action="/administrator/overture/delete/" />
		<acme:form-submit test="${command == 'update'}" code="administrator.overture.form.button.update"
			action="/administrator/overture/update/" />
		<acme:form-submit test="${command == 'delete'}" code="administrator.overture.form.button.delete"
			action="/administrator/overture/delete/" />
	</security:authorize>
	
	<acme:form-return code="authenticated.overture.form.button.return"/>
</acme:form>