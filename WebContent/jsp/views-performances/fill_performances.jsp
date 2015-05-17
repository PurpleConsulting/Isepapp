
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
			<li role="presentation" class="active">
				<a href='#tab1' aria-controls="#tab1" role="tab" data-toggle="tab">
					Mettre la première compétence ici ...
				</a>
			</li>
			<c:forEach var="skill" items="${skills}" varStatus="status">
				<li role="presentation"><a href='#tab${status.count + 1}' aria-controls="#tab1" role="tab" data-toggle="tab">
				  	<c:out value="${skill.getTitle()}"></c:out>
				</a></li>
			</c:forEach>
		</ul>
	
		 <!-- Tab panes -->
		  <div class="tab-content">
		    <div role="tabpanel" class="tab-pane active" id="${skill}">
		    Travail en groupe
		    </div>
		    <div role="tabpanel" class="tab-pane" id="Communication">Communication</div>
		    <div role="tabpanel" class="tab-pane" id="messages">...</div>
		    <div role="tabpanel" class="tab-pane" id="settings">...</div>
		  </div>
	</div>
</div>

	