<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<div>

		<h1>Evaluation croisée : Groupe (nom du groupe)</h1>

		<div class="deadline">Deadline à (heure de depot)</div>

		<div class="nom">
			<button type="button" class="btn btn-default"><</button>
			Léa
			<button type="button" class="btn btn-default">></button>
		</div>

	</div>

<div id="formulaire">

	<form method="post" action="traitement.php">

		<div class="couleur"></div>
		<div class="table-responsive">
			<table class="table table-bordered">
				<tr>compétence n°1
				</tr>

				<tr>
					<th class="sscomp1">Sous-compétence</th>
					<th class="mark">Loin d'être acquis</th>
					<th class="mark">En cours d'acquisition</th>
					<th class="mark">Acquis</th>
					<th class="mark">Au-delà</th>
				</tr>

				<tr>
					<td>Sous-compétence n°1</td>
					<td class="sscomp1"><label> <input type="radio"
							name="sscomp1" id="optionsRadios2" value="Loin d'être acquis">
					</label></td>

					<td class="sscomp1"><label> <input type="radio"
							name="sscomp1" id="optionsRadios2" value="En cours d'acquisition">
					</label></td>

					<td class="sscomp1"><label> <input type="radio"
							name="sscomp1" id="optionsRadios2" value="Acquis">
					</label></td>

					<td class="sscomp1"><label> <input type="radio"
							name="sscomp1" id="optionsRadios2" value="Au-delà">
					</label></td>
				</tr>
			</table>
		</div>
		<button type="submit" class="btn btn-default">envoyer</button>
	</form>
</div>