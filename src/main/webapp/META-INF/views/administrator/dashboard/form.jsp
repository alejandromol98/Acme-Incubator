<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textarea code="administrator.dashboard.form.label.ratioInvRoundWithMonema" path="ratioInvRoundWithMonema"/>
	<acme:form-double code="administrator.dashboard.form.label.ratioApplicationsWithLink" path="ratioApplicationsWithLink" />
	<acme:form-textarea code="administrator.dashboard.form.label.ratioApplicationsWithPassword" path="ratioApplicationsWithPassword"/>
	
	<acme:form-return code="administrator.dashboard.form.button.return"/>
</acme:form>