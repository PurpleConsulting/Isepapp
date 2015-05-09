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
      	<li><a class="navbar-brand icon_isep" href="#"><img  src="img/icon_isep.png" alt="logo isep"></a></li>
      	<li><a class="link1nav" href="Home">Home</a></li>
  		<li><a class="link1nav" href="Subject">Le sujet</a></li>
  		<li class="dropdown">
  			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">La gestion <span class="caret"></span></a>
  			<ul class="dropdown-menu" role="menu">
	            <li><a href="#">Les compétences</a></li>
	            <li><a href="#">Notes et<br/>coefficients </a></li>
          	</ul>
  		</li>
  		<li class="dropdown">
  			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Les groupes <span class="caret"></span></a>
  			<ul class="dropdown-menu" role="menu">
	            <li><a href="Promo">Les classes</a></li>
	            <li><a href="#">Groupes d'APP</a></li>
	            <li class="divider"></li>
	            <li><a href="Students?pseudo=ldivad">Recherche d'étudiant</a></li>
	            <li><a href="#">Recherche d'un tuteur</a></li>
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
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Fourtout Modules<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Module1</a></li>
            <li><a href="#">Module2</a></li>
            <li><a href="#">Module3</a></li>
            <li class="divider"></li>
            <li><a href="#">Module Ok 1</a></li>
            <li><a href="#">Module ok 2</a></li>
            <li class="divider"></li>
            <li><a href="#">Remplacez les modules.</a></li>
          </ul>
        </li>
      </ul>
      <!--<form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>-->
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>