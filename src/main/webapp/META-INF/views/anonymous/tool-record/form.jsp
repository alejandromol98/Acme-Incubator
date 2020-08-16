<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.toolRecord.form.label.title" path="title"/>
	<acme:form-textbox code="anonymous.toolRecord.form.label.activitySector" path="activitySector"/>
	<acme:form-textbox code="anonymous.toolRecord.form.label.investor" path="investor"/>
	<acme:form-textarea code="anonymous.toolRecord.form.label.description" path="description"/>
	<acme:form-url code="anonymous.toolRecord.form.label.website" path="website"/>
	<acme:form-textbox code="anonymous.toolRecord.form.label.email" path="email"/>
	<acme:form-select code="anonymous.toolRecord.form.label.source" path="source" >
		<acme:form-option code="anonymous.toolRecord.form.label.status.openSource" value="Open_Source" selected="${source == 'Open_Source'}"/>
        <acme:form-option code="anonymous.toolRecord.form.label.status.closedSource" value="Closed_Source" selected="${source == 'Closed_Source'}"/>
    </acme:form-select>
	<acme:form-integer code="anonymous.toolRecord.form.label.rate" path="rate"/>
	
	<acme:form-return code="anonymous.toolRecord.form.button.return"/>
</acme:form>