<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="authenticated.workProgramme.list.label.title" path="title" width="60%" />
	<acme:list-column code="authenticated.workProgramme.list.label.budget" path="budget" width="20%" />
	<acme:list-column code="authenticated.workProgramme.list.label.deadline" path="deadline" width="20%" />
</acme:list>

