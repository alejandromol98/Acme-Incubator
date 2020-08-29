<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="entrepreneur.workProgramme.form.label.title" path="title"/>
	<jstl:if test="${ command != 'create' }">
		<acme:form-moment code="entrepreneur.workProgramme.form.label.moment" path="moment"/>
	</jstl:if>

	<acme:form-moment code="entrepreneur.workProgramme.form.label.deadline" path="deadline"/>
	<acme:form-money code="entrepreneur.workProgramme.form.label.budget" path="budget"/>
	
	<acme:form-submit test="${command == 'create'}" code="entrepreneur.workProgramme.form.button.create"
			action="/entrepreneur/work-programme/create?invId=${invId}" />
	<acme:form-return code="entrepreneur.workProgramme.form.button.return"/>
</acme:form>