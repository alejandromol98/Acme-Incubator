<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.workProgramme.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.workProgramme.form.label.moment" path="moment"/>
	<acme:form-moment code="authenticated.workProgramme.form.label.deadline" path="deadline"/>
	<acme:form-money code="authenticated.workProgramme.form.label.budget" path="budget"/>
	
	<acme:form-return code="authenticated.workProgramme.form.button.return"/>
</acme:form>