<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<h1 class="col-xs-offset-1 col-xs-10">
	Valeur des notes.
</h1>
<div class="row">
	<div class="alert alert-info col-xs-offset-1 col-xs-10">
		<strong>Aide</strong>: Les notes présente ici sont à la disposition
		des tutors lorsqu'il notent les étudiants. Toute modification des
		valeurs associées au notes auront un effet sur les moyennes.
	</div>
	<div class="alert alert-warning cascade-warning col-xs-offset-1 col-xs-10 suppression">
		<strong>Attention</strong>: la suppression d'une valeur possible
		supprimera également les notes correspondantes affectés aux élèves.
	</div>
	<c:if test="${pages.getError()}">
		<div class="alert alert-danger col-md-offset-1 col-md-10">
			<strong>Attention</strong>,
			<c:out value="${pages.getErrorMessage()}"></c:out>
		</div>
	</c:if>
</div>

<!------------------------------- grille evaluation  ------------------------------------------------->
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 values">
	<em style="color:#337AB7">Les values de la grille d'évaluation</em>
		<hr />
		<form class="form-inline grille off" method="post" action="Values">
			<div class="line">
				<a class="btn btn-default modify" href="#" role="button"><span
					class="fa fa-pencil"></span> <span>Modifier</span></a>
				<a data-toggle="modal" data-target="#modaladd"
					class="btn btn-default add" href="#" role="button"><span
					class="fa fa-plus"></span> Ajouter</a> 
			</div>
			<input type="hidden" value="1" name="modify" /> 
			<input type="hidden" value='${fn:length(valeur)-1}' name="int">
			<c:forEach var="valeur" items="${valeur}" varStatus="status">
				<div class="line">
					<div class="form-group">
						<input type="text" class="form-control title" name="title${status.index}"
							value="<c:out value="${valeur.getTitle()}"></c:out>">
					</div>
					<div class="form-group">
						<input type="text" class="form-control value" name="points${status.index}"
							value="<c:out value="${valeur.getPoints()}"></c:out>">
					</div>
					<div class="form-group">
						<a class="remove" id="del${status.index}" href="#"><span
							class="fa fa-times danger col-xs-offset-6"></span></a> <input
							type="hidden" name="id${status.index}" class="display" id="id${status.index}"
							value="<c:out value="${valeur.getId()}"></c:out>" />
					</div>
					<div class="form-group">
						<div style="display: none"
							class="alert warning alert-warning cascade-warning warning_modify"></div>
					</div>
					<div class="form-group">
						<input type="checkbox" name="delete"
							value="<c:out value="${valeur.getId()}"></c:out>"
							style="visibility: hidden">
					</div>
				</div>
			</c:forEach>
			<button type="submit" class="btn btn-default" id="modifyValue">
				<span class="fa fa-check"> </span> Valider
			</button>
		</form>
		<hr />
	</div>
</div>
<!------------------------------- evaluation croissé ------------------------------------------------->
<div class="row">
	<div class="col-xs-offset-1 col-xs-10 values">
	<em style="color:#337AB7">Les values d'évaluations croissés</em>
		<hr />
		<form class="form-inline off" method="post" action="Values">
			<div class="line">
				<a class="btn btn-default modify" href="#" role="button">
				<span class="fa fa-pencil"></span> <span>Modifier</span></a>
			</div>
			<input type="hidden" value="3" name="modify" /> <input type="hidden"
				value='${fn:length(valeurCross)-1}' name="intCross">
			<c:forEach var="valeurCross" items="${valeurCross}" varStatus="status">
				<div class="line">
					<div class="form-group">
						<input type="text" class="form-control title" name="titleCross${status.index}"
							value="<c:out value="${valeurCross.getTitle()}"></c:out>">
					</div>
					<div class="form-group">
						<input type="text" class="form-control value" name="pointsCross${status.index}"
							value="<c:out value="${valeurCross.getPoints()}"></c:out>">
					</div>
					<div class="form-group">
						<div style="display: none"
							class="alert warning alert-warning cascade-warning warning_modify"></div>
						<input type="hidden" name="idCross${status.index}" class="display" 
							value="<c:out value="${valeurCross.getId()}"></c:out>" />
					</div>
				</div>
			</c:forEach>
			<button type="submit" class="btn btn-default" id="modifyValueCross">
				<span class="fa fa-check"> </span> Valider
			</button>
		</form>
		<hr />
	</div>
</div>

<!------------------------------- ajouter value ------------------------------------------------->
<div id="modaladd" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Ajouter une valeur.</h4>
			</div>
			<div class="modal-body">
				<form>
					<div class="form-group">
						<input class="form-control" placeholder="titre, exemple acquis"
							id="newtitle" maxlength="30" />
					</div>
					<div class="form-group">
						<input class="form-control" placeholder="rentrer un nombre"
							id="newpoints" /> 
					</div>
					<div style="display: none"
						class="warningA alert-warning cascade-warning warningadd"></div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
				<button type="submit" class="btn btn-primary" id="add">Ajouter</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>