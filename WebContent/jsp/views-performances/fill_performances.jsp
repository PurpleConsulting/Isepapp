
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
				<c:forEach var="i" begin="0" end="3">
					<option value="<c:out value="${name[i].getName()}"></c:out>"><c:out value="${name[i].getName()}"></c:out></option>
				
				</c:forEach>
				

			</select>
			<button type="submit" class="btn btn-default">OK</button>
		</div>
	</form>
	<c:out value="${group_names[0]}"></c:out>
</div>