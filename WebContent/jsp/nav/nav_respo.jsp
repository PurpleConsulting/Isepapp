<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default" id="navmain">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <!-- <a class="navbar-brand" href="Home"></a> -->
    </div>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      	<li><a class="icon_isep" href="#"><img  src="img/icon_isep.png" alt="logo isep"></a></li>
      	<li><a class="link1nav" href="Home">Home</a></li>
  		<li><a href="Subject">Le sujet</a></li>
  		<li class="dropdown">
  			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">La gestion <span class="caret"></span></a>
  			<ul class="dropdown-menu" role="menu">
	            <li><a href="#">Les compétences</a></li>
	            <li><a href="Values">Valeurs des notes </a></li>
	            <li><a href="#">Notes et<br/>coefficients </a></li>
          	</ul>
  		</li>
  		<li class="dropdown">
  			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Les groupes <span class="caret"></span></a>
  			<ul class="dropdown-menu" role="menu">
	            <li><a href="Promo">Les classes</a></li>
	            <li><a href="#">Les Tuteurs</a></li>
	            <li class="divider"></li>
	            <li><a href="#">L'appel</a></li>
	            <li><a href="Controls">Evaluation des Groupes</a></li>
	            <li class="divider"></li>
	            <li><a href="Students?pseudo=ldivad">Recherche d'étudiant</a></li>
          	</ul>
  		</li>
  		<li class="dropdown">
  			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Les livrables<span class="caret"></span></a>
  			<ul class="dropdown-menu" role="menu">
	            <li><a href="#">Deadlines</a></li>
	            <li><a href="#">Dépots</a></li>
          </ul>
  		</li>
  		<li><a href="Signout">Déconnexion</a></li>
      </ul>
      <form class="navbar-form navbar-left" role="search" action="Search" method="GET">
        <div class="form-group">
          <input name="keyword" type="text" class="form-control typeahead search-virgin" placeholder="Search">
          <button style="Display:none;" type="submit" class="btn btn-default">Submit</button>
        </div>
      </form>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>