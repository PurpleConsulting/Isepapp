 <form class="form-horizontal" id="delete-std" method="post">
 	<div style="margin-left:10%; width:80%">
 		<p>
 			Voulez vous <u>vraiment</u> supprimer cet �tudiant?  <span class="fa fa-exclamation-triangle"></span><br/>
 			La suppr�ssion entrainera automatiquement la suppr�ssion en cascade<br/>
 			<ul>
	 			<li>des <strong>notes</strong></li>
	 			<li>des �valuations <strong>crois�es</strong></li>
	 			<li>et des autres informations li�es � l�tudiant</li>
 			</ul>
 			Pour coufirmer la supp�ssion copier le pseudo ISEP: <strong><em class="pop">ldivad</em></strong>.
 		</p>
 	</div>
 	<div class="form-group has-error" style="margin-left:10%; width:80%">
 		<input name="suggestion-std" class="col-xs-10 form-control delete-std" type="text"/>
 	</div>
 </form>