<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textarea code="administrator.customisation.form.label.spamwords" path="spamwords"/>
	<acme:form-double code="administrator.customisation.form.label.threshold" path="threshold"/>
	<acme:form-textarea code="administrator.customisation.form.label.activitySectors" path="activitySectors"/>
	
	<acme:form-return code="administrator.customisation.form.button.return"/>
</acme:form>