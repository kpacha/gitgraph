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
        <title>GitHub Graph</title>
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
              <li class="active"><a href="/">Home</a></li>
              <li><a href="#search">Search</a></li>
              <li><a href="#surprise">Surprise me!</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid" >

        <div class="hero-unit">
            <h1>GitHub Graph</h1>
            <p>Explore GitHub better and faster!</p>
            <ul>
                <li>Search repo by name, owner, relevance, language...</li>
                <li>Recomendations by profile</li>
            </ul>
            <h3>Get inspired and stay updated.</h3>
        </div>

        <div class="row-fluid">
            <div class="span4">
                <h3>Repositories</h3>
                <h4>Most popular</h4>
                <ul class="unstyled">
					<c:forEach var="repo" items="${reposByWatchers}">
						<li><a href="index?id=<c:out value="${repo.id}"/>"><c:out value="${repo.name}" /></a><small class="muted text-right pull-right"><span class="badge badge-success"><c:out value="${repo.watchers}"/></span> <span class="badge badge-info"><c:out value="${repo.forks}"/></span></small></li>
					</c:forEach>
                </ul>
                <h4>Most actives</h4>
                <ul class="unstyled">
					<c:forEach var="repo" items="${reposByLastPushedAt}">
						<c:set var="date" value="${repo.pushedAt}"/>
						<li>
							<a href="index?id=<c:out value="${repo.id}"/>"><c:out value="${repo.name}" /></a>
							<div class="pull-right">
								<abbr class="timeago" title="<fmt:formatDate pattern="yyyy-MM-dd" value="${date}" />"><fmt:formatDate pattern="yyyy-MM-dd" value="${date}" /></abbr>
            				</div>
						</li>
					</c:forEach>
                </ul>
                <h4>Most forked</h4>
                <ul class="unstyled">
					<c:forEach var="repo" items="${reposByForks}">
						<li><a href="index?id=<c:out value="${repo.id}"/>"><c:out value="${repo.name}" /></a><small class="muted text-right pull-right"><span class="badge badge-info"><c:out value="${repo.forks}"/></span></small></li>
					</c:forEach>
                </ul>
            </div>
            <div class="span4">
                <h3>Users</h3>
                <h4>Most popular</h4>
                <ul class="unstyled">
					<c:forEach var="repo" items="${reposByWatchers}">
						<li><c:out value="${repo.name}" /> | <a href="index?id=<c:out value="${repo.id}"/>">show</a></li>
					</c:forEach>
                </ul>
                <h4>Most actives</h4>
                <ul class="unstyled">
					<c:forEach var="repo" items="${reposByLastPushedAt}">
						<li><c:out value="${repo.name}" /> | <a href="index?id=<c:out value="${repo.id}"/>">show</a></li>
					</c:forEach>
                </ul>
                <h4>Most forked</h4>
                <ul class="unstyled">
					<c:forEach var="repo" items="${reposByForks}">
						<li><c:out value="${repo.name}" /> | <a href="index?id=<c:out value="${repo.id}"/>">show</a></li>
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

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
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
