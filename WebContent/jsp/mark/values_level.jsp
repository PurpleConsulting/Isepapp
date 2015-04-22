<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
 	<h2>Les niveaux</h2>
 	
		<table class="table table-striped" id="marktab">
			<tr>
				<th>Niveau:</th>
				<th>Note:</th>
				
			</tr>
							
			<c:forEach var="i" begin="0" end='${i}'>
				<tr>
					<td><c:out value="${valeur[i].getTitle()}"></c:out></td>
					<td><c:out value="${valeur[i].getPoints()}"></c:out></td>
				</tr>
			</c:forEach>
			
			
		</table>
		
	 	<button type="submit" class="btn btn-default" id="ModifierNot">Modifier</button>
</div>