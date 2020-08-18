<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.toolRecord.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.toolRecord.form.label.activitySector" path="activitySector"/>
	<acme:form-textbox code="authenticated.toolRecord.form.label.investor" path="investor"/>
	<acme:form-textarea code="authenticated.toolRecord.form.label.description" path="description"/>
	<acme:form-url code="authenticated.toolRecord.form.label.website" path="website"/>
	<acme:form-textbox code="authenticated.toolRecord.form.label.email" path="email"/>
	<acme:form-select code="authenticated.toolRecord.form.label.source" path="source" >
		<acme:form-option code="authenticated.toolRecord.form.label.status.openSource" value="Open_Source" selected="${source == 'Open_Source'}"/>
        <acme:form-option code="authenticated.toolRecord.form.label.status.closedSource" value="Closed_Source" selected="${source == 'Closed_Source'}"/>
    </acme:form-select>
	<acme:form-integer code="authenticated.toolRecord.form.label.rate" path="rate"/>
	
	<security:authorize access="hasRole('Administrator')">
		<acme:form-submit test="${command == 'show'}" code="administrator.toolRecord.form.button.update"
			action="/administrator/tool-record/update/" />
		<acme:form-submit test="${command == 'show'}" code="administrator.toolRecord.form.button.delete"
			action="/administrator/tool-record/delete/" />
		<acme:form-submit test="${command == 'update'}" code="administrator.toolRecord.form.button.update"
			action="/administrator/tool-record/update/" />
		<acme:form-submit test="${command == 'delete'}" code="administrator.toolRecord.form.button.delete"
			action="/administrator/tool-record/delete/" />
	</security:authorize>
	
	<acme:form-return code="authenticated.toolRecord.form.button.return"/>
</acme:form>