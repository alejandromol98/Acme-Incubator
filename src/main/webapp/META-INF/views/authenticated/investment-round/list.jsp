<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="authenticated.investmentRound.list.label.ticker" path="ticker" width="20%"/>
	<acme:list-column code="authenticated.investmentRound.list.label.title" path="title" width="20%" />
	<acme:list-column code="authenticated.investmentRound.list.label.kind" path="kind" width="20%" />
</acme:list>

