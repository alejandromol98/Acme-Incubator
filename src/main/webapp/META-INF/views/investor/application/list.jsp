<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:list>
	<acme:list-column code="investor.application.list.label.ticker" path="ticker" width="20%"/>
	<acme:list-column code="investor.application.list.label.invRoundTicker" path="investmentRoundTicker" width="20%"/>
	<acme:list-column code="investor.application.list.label.statement" path="statement" width="40%" />
	<acme:list-column code="investor.application.list.label.offer" path="offer" width="20%" />
</acme:list>

