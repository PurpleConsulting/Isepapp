
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div>
	<h1>Grille de comp�tences</h1>

	<form action="Controls" method="post">
		<div class="form-group">
			Recherche par groupe d'APP :
			<select class="form-control" id="group">
				<option>S�lectionnez un groupe</option>
				<c:forEach var="group" items="${group_names}" varStatus="status">
					<option value="${group}">
						<c:out value="${group}"></c:out>
					</option>
				</c:forEach>			
			</select>
		</div>
	</form>
	
	<div id="name_group">
	</div>
	
	<!-- Nav tabs -->
	<div role="tabpanel">
		<ul class="nav nav-tabs" role="tablist" id="tabs">
			<c:forEach var="skill" items="${skills}" varStatus="status">
				<li role="presentation" class='${status.count==1 ? "active":""}'>
					<a href="#tab${status.count}" role="tab" data-toggle="tab">
				  		<c:out value="${status.count}. ${skill.getTitle()}"></c:out>
					</a>
				</li>
			</c:forEach>
		</ul>
	
		 <!-- Tab panes -->
		  <div class="tab-content">
		  		<c:forEach var="skill" items="${skills}" varStatus="status">
		  			<form action= "Controls" method="post" id="form${skill.getId()}">
				    	<div role="tabpanel" class='tab-pane ${status.count==1 ? "active":""}' id="tab${status.count}">
				    		<h3>
				    			<c:out value='${status.count} - ${skill.getSubtitle()}'></c:out>
				    		</h3>
				    		<c:forEach var="sub_skill" items="${skill.getSub_skills()}" varStatus="status">
						  		<hr /><c:out value='${sub_skill.getTitle()} : '></c:out>
							  	<div class="radio" class="line">
							  		<c:forEach var="value" items="${values}" varStatus="status">
									  <label>
									    <input type="radio" name='${sub_skill.getId()}' id="value${status.count}" value='${value.getId()}'  ${status.count==1 ? "checked":""}>
									    <c:out value='${value.getTitle()}'></c:out>
									  </label>
									  <input type="hidden" value='${sub_skill.getId()}' class="val">
									  
									</c:forEach>
									<c:set var="val"  value="${skill.getId()}"/>
								</div>
						  	</c:forEach>
					  	</div>
					  	<button class="btn btn-default btn-test" value="${val}">Bouton</button>
					</form>
				</c:forEach>
		  </div>
	</div>
</div>

	