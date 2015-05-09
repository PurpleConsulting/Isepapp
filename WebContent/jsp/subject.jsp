<!--  BODY PART SUBJECT PAGE MODULE  -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row">
	 <a href="assets/CURRENTAPPSUBJECT.pdf" download> <span class="fa fa-download fa-5x"></span></a>
</div>
<div class="row">
	<div class="col-xs-offset-1 col-xs-10">
		<nav>
		  <ul class="pager">
		    <li class="previous"><a id="prev" href="#"><span aria-hidden="true">&larr;</span> Précédent </a></li>
		    <li class="next"><a id="next" href="#">Suivant <span aria-hidden="true">&rarr;</span></a></li>
		  </ul>
		</nav>
		 <span id="pagination">Page: <span id="page_num"></span> / <span id="page_count"></span></span>
		<div>
  			<canvas id="the-canvas" style="border:1px solid #BDBDBD"></canvas>
		</div>
	</div>
</div>