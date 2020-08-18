<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.toolRecord.form.label.title" path="title"/>
	<acme:form-textbox code="administrator.toolRecord.form.label.activitySector" path="activitySector"/>
	<acme:form-textbox code="administrator.toolRecord.form.label.investor" path="investor"/>
	<acme:form-textarea code="administrator.toolRecord.form.label.description" path="description"/>
	<acme:form-url code="administrator.toolRecord.form.label.website" path="website"/>
	<acme:form-textbox code="administrator.toolRecord.form.label.email" path="email"/>
	<acme:form-select code="administrator.toolRecord.form.label.source" path="source" >
		<acme:form-option code="administrator.toolRecord.form.label.status.openSource" value="Open_Source" selected="${source == 'Open_Source'}"/>
        <acme:form-option code="administrator.toolRecord.form.label.status.closedSource" value="Closed_Source" selected="${source == 'Closed_Source'}"/>
    </acme:form-select>
	<acme:form-integer code="administrator.toolRecord.form.label.rate" path="rate"/>
	
	<acme:form-submit test="${command == 'create'}" 
		code="administrator.toolRecord.form.button.create" 
		action="/administrator/tool-record/create"/>	
	
	<acme:form-return code="administrator.toolRecord.form.button.return"/>
</acme:form>