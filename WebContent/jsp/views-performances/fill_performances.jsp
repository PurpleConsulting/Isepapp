
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div>
	<h1>Grille de compétences</h1>

	<form action="Controls" method="post">
		<div class="form-group">
			Recherche par groupe d'APP :
			<select class="form-control" id="group">
				<option>Sélectionnez un groupe</option>
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
		  	<form action="Controls" method="post">
		  		<c:forEach var="skill_sub_title" items="${skills}" varStatus="status">
			    	<div role="tabpanel" class="tab-pane" id="tab${status.count}">
			    		<h3>
			    			<c:out value='${status.count} - ${skill_sub_title.getSub_title()}'></c:out>
			    		</h3>
				  		<fieldset>
				  			<legend>
				  				
				  				Test
				  			</legend>
				  		</fieldset>
				  	</div>
				</c:forEach>
		  	</form>
			    <div role="tabpanel" class="tab-pane active" id="${skill}">
			    </div>
		  </div>
	</div>
</div>

	