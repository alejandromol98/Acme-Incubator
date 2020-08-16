<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.overture.form.label.title" path="title"/>	
	<acme:form-moment code="administrator.overture.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.overture.form.label.description" path="description"/>
	<acme:form-money code="administrator.overture.form.label.min" path="min"/>
	<acme:form-money code="administrator.overture.form.label.max" path="max"/>
	<acme:form-url code="administrator.overture.form.label.email" path="email" />
		
	<acme:form-submit test="${command == 'create'}" 
		code="administrator.overture.form.button.create" 
		action="/administrator/overture/create"/>		
	
	<acme:form-return code="administrator.overture.form.button.return"/>
</acme:form>