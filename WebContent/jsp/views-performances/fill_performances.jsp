
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div>
	<h1>Grille de compétences</h1>

	<form>
		<div class="form-group">
			Recherche par groupe d'APP :
			<input type="hidden" value="3" name="int">
			<select class="form-control">
				<option>Sélectionnez un groupe</option>
				<c:forEach var="group" items="${group_names}" varStatus="status">
					<option value="${group}"><c:out value="${group}"></c:out></option>
					
				</c:forEach>			
			</select>
			<button type="submit" class="btn btn-default">OK</button>
		</div>
	</form>


</div>