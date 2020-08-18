<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.technologyRecord.form.label.title" path="title"/>
	<acme:form-textbox code="administrator.technologyRecord.form.label.activitySector" path="activitySector"/>
	<acme:form-textbox code="administrator.technologyRecord.form.label.investor" path="investor"/>
	<acme:form-textarea code="administrator.technologyRecord.form.label.description" path="description"/>
	<acme:form-url code="administrator.technologyRecord.form.label.website" path="website"/>
	<acme:form-textbox code="administrator.technologyRecord.form.label.email" path="email"/>
	<acme:form-select code="administrator.technologyRecord.form.label.source" path="source" >
		<acme:form-option code="administrator.technologyRecord.form.label.status.openSource" value="Open_Source" selected="${source == 'Open_Source'}"/>
        <acme:form-option code="administrator.technologyRecord.form.label.status.closedSource" value="Closed_Source" selected="${source == 'Closed_Source'}"/>
    </acme:form-select>
	<acme:form-integer code="administrator.technologyRecord.form.label.rate" path="rate"/>
	
	<acme:form-submit test="${command == 'create'}" 
		code="administrator.technologyRecord.form.button.create" 
		action="/administrator/technology-record/create"/>	
	
	<acme:form-return code="administrator.technologyRecord.form.button.return"/>
</acme:form>