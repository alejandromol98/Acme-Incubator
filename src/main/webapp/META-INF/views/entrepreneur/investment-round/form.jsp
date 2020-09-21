<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${command != 'create' && isFinalMode == true}">
		<acme:form-textbox code="entrepreneur.investmentRound.form.label.ticker" path="ticker" readonly="true" />
		<acme:form-checkbox code="entrepreneur.investmentRound.form.label.isFinalMode" path="isFinalMode" readonly="true" />
		<acme:form-textbox code="entrepreneur.investmentRound.form.label.title" path="title" readonly="true" />
		<acme:form-moment code="entrepreneur.investmentRound.form.label.moment" path="moment" readonly="true" />
		<acme:form-textbox code="entrepreneur.investmentRound.form.label.kind" path="kind" readonly="true" />	
		<acme:form-textarea code="entrepreneur.investmentRound.form.label.description" path="description" readonly="true" />
		<acme:form-money code="entrepreneur.investmentRound.form.label.amount" path="amount" readonly="true" />
		<acme:form-url code="entrepreneur.investmentRound.form.label.moreInfo" path="moreInfo" readonly="true" />
	</jstl:if>
	
	<jstl:if test="${command == 'create' || isFinalMode == false}">
		<acme:form-textbox code="entrepreneur.investmentRound.form.label.ticker" path="ticker"/>
		<jstl:if test="${command != 'create'}">
			<acme:form-checkbox code="entrepreneur.investmentRound.form.label.isFinalMode" path="isFinalMode"/>
		</jstl:if>
		<acme:form-textbox code="entrepreneur.investmentRound.form.label.title" path="title"/>
		<jstl:if test="${command != 'create'}">
			<acme:form-moment code="entrepreneur.investmentRound.form.label.moment" path="moment" readonly="true" />
		</jstl:if>
	
		<acme:form-select code="entrepreneur.investmentRound.form.label.kind" path="kind">
			<acme:form-option code="entrepreneur.investmentRound.form.label.kind.seed" value="SEED" selected="${kind == 'SEED'}"/>
			<acme:form-option code="entrepreneur.investmentRound.form.label.kind.angel" value="ANGEL" selected="${kind == 'ANGEL'}"/>
			<acme:form-option code="entrepreneur.investmentRound.form.label.kind.series_a" value="SERIES_A" selected="${kind == 'SERIES_A'}"/>
			<acme:form-option code="entrepreneur.investmentRound.form.label.kind.series_b" value="SERIES_B" selected="${kind == 'SERIES_B'}"/>
			<acme:form-option code="entrepreneur.investmentRound.form.label.kind.series_c" value="SERIES_C" selected="${kind == 'SERIES_C'}"/>
			<acme:form-option code="entrepreneur.investmentRound.form.label.kind.bridge" value="BRIDGE" selected="${kind == 'BRIDGE'}"/>
		</acme:form-select>
	
		<acme:form-textarea code="entrepreneur.investmentRound.form.label.description" path="description"/>
		<acme:form-money code="entrepreneur.investmentRound.form.label.amount" path="amount"/>
		<acme:form-url code="entrepreneur.investmentRound.form.label.moreInfo" path="moreInfo"/>
	</jstl:if>
	
	
	<jstl:if test="${command == 'show' && isFinalMode == false}">
		<acme:form-submit code="entrepreneur.investmentRound.form.button.update" action="/entrepreneur/investment-round/update"/>
	</jstl:if>
	<jstl:if test="${command == 'show'}">
		<acme:form-submit code="entrepreneur.investmentRound.form.button.delete" action="/entrepreneur/investment-round/delete"/>
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:form-submit code="entrepreneur.investmentRound.form.button.create" action="/entrepreneur/investment-round/create"/>
	</jstl:if>
	<jstl:if test="${command == 'update' }">
		<acme:form-submit code="entrepreneur.investmentRound.form.button.update" action="/entrepreneur/investment-round/update"/>
	</jstl:if>
	<jstl:if test="${command == 'delete'}">
		<acme:form-submit code="entrepreneur.investmentRound.form.button.delete" action="/entrepreneur/investment-round/delete"/>
	</jstl:if>
		
	<jstl:if test="${command != 'create' }">
		
		<jstl:if test="${ isFinalMode == false && workProgrammesAmount == true}">
			<acme:form-hidden path="invId" />
			<acme:form-submit method="get" code="entrepreneur.workProgramme.form.button.create"
			action="/entrepreneur/work-programme/create?invId=${id}" />
		</jstl:if>
		<acme:form-submit code="entrepreneur.investmentRound.form.label.workProgrammes" method="get" action="/entrepreneur/work-programme/list?id=${id}" />
		<acme:form-submit code="entrepreneur.investmentRound.form.label.accountingRecord" method="get" action="/authenticated/accounting-record/list?id=${id}" />
	</jstl:if>
	
	<jstl:if test="${command != 'create' }"> 
		<acme:form-hidden path="invId" />
			<acme:form-submit method="get" code="entrepreneur.monema.form.button.create"
			action="/entrepreneur/monema/create?invId=${id}" />
	</jstl:if>
	<acme:form-submit code="entrepreneur.investmentRound.form.label.monema" method="get" action="/entrepreneur/monema/list?id=${id}" />
	
	<acme:form-return code="entrepreneur.investmentRound.form.button.return"/>
</acme:form>