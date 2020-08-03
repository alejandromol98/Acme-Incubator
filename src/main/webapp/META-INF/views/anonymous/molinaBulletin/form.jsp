<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>

	<acme:form-textbox code="anonymous.molinaBulletin.form.label.title" path="title" />
	<acme:form-textbox code="anonymous.molinaBulletin.form.label.author" path="author" />
	<acme:form-textarea code="anonymous.molinaBulletin.form.label.text" path="text" />
	
	<acme:form-submit code="anonymous.molinaBulletin.form.label.create" action="/anonymous/molina-bulletin/create" />
	<acme:form-return code="anonymous.molinaBulletin.form.label.return" />

</acme:form>