
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div>
	<h1>Grille de compétences</h1>

	<form action="Controls" method="post">
		<div class="form-group">
			Recherche par groupe d'APP :
			<input type="hidden" value="3" name="int">
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
	
	<div id="group"></div>
	
	<ul class="nav nav-tabs" role="tablist" id="tabs">
	  <li role="presentation"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
	</ul>

	<div class="tab-content">
	  <div role="tabpanel" class="tab-pane" id="home">...</div>
	</div>


	
</div>