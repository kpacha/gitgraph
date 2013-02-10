<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>GitHub Graph - Search Results</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
        
        <link rel="stylesheet" href="styles/60bb6b2d.main.css">
        <script src="scripts/vendor/cf69c6f2.modernizr.min.js"></script>
    </head>
    <body>

    <div class="navbar navbar-inverse">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="/">GitHub Graph</a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              Logged in as <a href="#" class="navbar-link">Username</a>
            </p>
            <ul class="nav">
              <li><a href="/">Home</a></li>
              <li><a href="#search">Search</a></li>
              <li><a href="#surprise">Surprise me!</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid" >

        <div class="row-fluid">

            <div class="span8">
                <h3>Repositories</h3>
                <ul class="unstyled">
					<c:forEach var="repo" items="${repos}">
                    <li>
						<c:set var="pushedAt" value="${repo.pushedAt}"/>
						<c:set var="createdAt" value="${repo.createdAt}"/>
						<ul class="nav nav-tabs">
						  <li>
						  	<h4><a href="index?ownerId=<c:out value="${repo.ownerId}"/>"><c:out value="${repo.ownerId}"/></a> / <a href="index?id=<c:out value="${repo.id}"/>"><c:out value="${repo.name}"/></a></h4>
						  </li>
						  <li class="dropdown pull-right">
						    <a class="dropdown-toggle" data-toggle="dropdown" href="#">more <b class="caret"></b></a>
						    <ul class="dropdown-menu">
						    	<li><a href="<c:out value="${repo.svnUrl}"/>" target="_blank"><i class="icon-home"></i> see on GitHub</a></li>
						    	<li><a href="http://resume.github.com/?<c:out value="${repo.ownerId}"/>" target="_blank"><i class="icon-home"></i> see owner's résumé</a></li>
						  	</ul>
						  </li>
						</ul>
						<p>
        					<small><c:out value="${repo.description}"/></small>
        				</p>
        				<div class="row-fluid">
            				<div class="span4">
	                    		<small class="muted">created <abbr class="timeago" title="<fmt:formatDate pattern="yyyy-MM-dd" value="${createdAt}" />"><fmt:formatDate pattern="yyyy-MM-dd" value="${createdAt}" /></abbr></small>
	                    	</div>
            				<div class="span4">
	                    		<small class="muted">pushed <abbr class="timeago" title="<fmt:formatDate pattern="yyyy-MM-dd" value="${pushedAt}" />"><fmt:formatDate pattern="yyyy-MM-dd" value="${pushedAt}" /></abbr></small>
	                    	</div>
            				<div class="span4">
	                    		<small class="muted text-right pull-right"><span class="badge badge-inverse"><c:out value="${repo.language}"/></span> <span class="badge badge-success"><c:out value="${repo.watchers}"/></span> <span class="badge"><c:out value="${repo.size}"/></span> <span class="badge badge-info"><c:out value="${repo.forks}"/></span></small>
            				</div>
                    	</div>
                   	</li>
					</c:forEach>
                </ul>
            </div>

            <div class="span4">
                <h4>Suggestions</h4>
                <p>Get recomendations based on your favourite projects, technologies and languages.</p>
                <a id="surprise" href="/" class="btn btn-success disabled">Surprise me!</a>
                <h4>Inspect</h4>
                <form id="search" action="/" method="get">
                    <fieldset class="input-append span12">
                        <input type="text" placeholder="owner/name" name="id" class="span9" >
                        <button type="submit" class="btn btn-success">Search!</button>
                    </fieldset>
                </form>
                <h4>Search</h4>
                <p>Search repo by name, owner, relevance, language...</p>
                <form id="search" action="/" method="post">
                    <fieldset>
                        <input type="text" placeholder="Owner" name="ownerId" class="span12" >
                        <input type="text" placeholder="Name" name="name" class="span12" >
                        <input type="text" placeholder="Programing language" name="language" class="span12" >
                        <button type="submit" class="btn btn-success">Search!</button>
                    </fieldset>
                </form>
            </div>

        </div>
        
        <hr>
		<footer>
			<p>Quota: <c:out value="${quota}" /></p>
		</footer>
	
    </div>

        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an outdated browser. <a href="http://browsehappy.com/">Upgrade your browser today</a> or <a href="http://www.google.com/chromeframe/?redirect=true">install Google Chrome Frame</a> to better experience this site.</p>
        <![endif]-->

        <!-- Add your site or application content here -->

        <!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script> -->
        <script src="scripts/vendor/8bc61845.jquery.min.js"><\/script>
        <script>window.jQuery || document.write('<script src="scripts/vendor/8bc61845.jquery.min.js"><\/script>')</script>
        
        

        <!-- Google Analytics: change UA-XXXXX-X to be your site's ID. -->
        <!-- 
        <script>
            var _gaq=[['_setAccount','UA-XXXXX-X'],['_trackPageview']];
            (function(d,t){var g=d.createElement(t),s=d.getElementsByTagName(t)[0];
            g.src=('https:'==location.protocol?'//ssl':'//www')+'.google-analytics.com/ga.js';
            s.parentNode.insertBefore(g,s)}(document,'script'));
        </script>
         -->
    
    <script src="scripts/ff0639f4.plugins.js"></script>
</body>
</html>
