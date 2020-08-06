<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>

	<acme:list-column code="anonymous.toolRecord.list.label.title" path="title" width="20%"/>
	<acme:list-column code="anonymous.toolRecord.list.label.activitySector" path="activitySector" width="20%"/>
	<acme:list-column code="anonymous.toolRecord.list.label.rate" path="rate" width="20%" />
	
</acme:list>

