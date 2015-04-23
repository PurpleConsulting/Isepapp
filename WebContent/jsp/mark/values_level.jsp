<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">


<div>
	<h2>Les niveaux</h2>

	<form method="post" action="ValuesController">
	<input type="hidden" value="1" name="modify"/>
		<table class="table table-striped" id="marktab">
			<tr>
				<th>Niveau:</th>
				<th>Note:</th>

			</tr>
<input type="hidden" name="int" value='${i}' />
			<c:forEach var="i" begin="0" end='${i}'>
				<tr>
					<td><input type="text" name="title${i}"
						value="<c:out value="${valeur[i].getTitle()}"></c:out>" /></td>
					<td><input type="text" name="points${i}"
						value="<c:out value="${valeur[i].getPoints()}"></c:out>" /></td>
				</tr>
				<input type="hidden" name="id${i}"
						value="<c:out value="${valeur[i].getId()}"></c:out>" />
			</c:forEach>


		</table>

		<div id="valider">
			<button type="submit" class="btn btn-default" id="ModifierNot">Valider</button>
		</div>

	</form>
	<div id="modifier">
		<button type="submit" class="btn btn-default" id="ModifierNotM">Modifier</button>
		
	</div>
	<button type="submit" class="btn btn-default"id="AjouterB" >Ajouter</button>
</div>


<div id="ajouter">
	<form method="post" action="ValuesController">
		
	  <div class="form-group">
	    <label >Title</label>
	    <input type="text" class="form-control" name="newtitle" id="newtitle" >
	  </div>
	  <input type="hidden" value="2" name="add" id="add"/>
	  <div class="form-group">
	    <label for="points">Points</label>
	    <input type="text" class="form-control" name="newpoints" id="newpoints" >
	  </div>
	  
	  <button type="submit" class="btn btn-default">Submit</button>
	</form>

</div>

