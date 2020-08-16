<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.technologyRecord.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.technologyRecord.form.label.activitySector" path="activitySector"/>
	<acme:form-textbox code="authenticated.technologyRecord.form.label.investor" path="investor"/>
	<acme:form-textarea code="authenticated.technologyRecord.form.label.description" path="description"/>
	<acme:form-url code="authenticated.technologyRecord.form.label.website" path="website"/>
	<acme:form-textbox code="authenticated.technologyRecord.form.label.email" path="email"/>
	<acme:form-select code="authenticated.technologyRecord.form.label.source" path="source" >
		<acme:form-option code="authenticated.technologyRecord.form.label.status.openSource" value="Open_Source" selected="${source == 'Open_Source'}"/>
        <acme:form-option code="authenticated.technologyRecord.form.label.status.closedSource" value="Closed_Source" selected="${source == 'Closed_Source'}"/>
    </acme:form-select>
	<acme:form-integer code="authenticated.technologyRecord.form.label.rate" path="rate"/>
	
	<security:authorize access="hasRole('Administrator')">
		<acme:form-submit test="${command == 'show'}" code="administrator.technologyRecord.form.button.update"
			action="/administrator/technology-record/update/" />
		<acme:form-submit test="${command == 'show'}" code="administrator.technologyRecord.form.button.delete"
			action="/administrator/technology-record/delete/" />
		<acme:form-submit test="${command == 'update'}" code="administrator.technologyRecord.form.button.update"
			action="/administrator/technology-record/update/" />
		<acme:form-submit test="${command == 'delete'}" code="administrator.technologyRecord.form.button.delete"
			action="/administrator/technology-record/delete/" />
	</security:authorize>
	
	<acme:form-return code="authenticated.technologyRecord.form.button.return"/>
</acme:form>