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
        <title>GitHub Graph - Repo <c:out value="${repo.id}" /></title>
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
			  	<h3>
			  		<a href="index?ownerId=<c:out value="${repo.ownerId}"/>"><c:out value="${repo.ownerId}"/></a> / <a href="index?id=<c:out value="${repo.id}"/>"><c:out value="${repo.name}"/></a>
			  		<ul class="nav nav-pills pull-right">
					  <li class="dropdown pull-right">
					    <a class="dropdown-toggle" data-toggle="dropdown" href="#"><b class="caret"></b></a>
					    <ul class="dropdown-menu">
					    	<li><small><a href="<c:out value="${repo.svnUrl}"/>" target="_blank"><i class="icon-home"></i> see on GitHub</a></small></li>
					    	<li><small><a href="http://resume.github.com/?<c:out value="${repo.ownerId}"/>" target="_blank"><i class="icon-home"></i> see owner's résumé</a></small></li>
					  	</ul>
					  </li>
					</ul>
				</h3>
				<c:if test="${not empty repo.parentId}">
					<small class="muted">forked from <a href="/?id=<c:out value="${repo.parentId}" />"><c:out value="${repo.parentId}" /></a></small>
				</c:if>
                <dl class="dl-horizontal">
                  <dt>name</dt>
                  <dd><c:out value="${repo.name}" /></dd>
                  <dt>description</dt>
                  <dd><c:out value="${repo.description}" /></dd>
                  <dt>is fork</dt>
                  <dd><c:out value="${repo.fork}" /></dd>
                  <dt>owner</dt>
                  <dd><c:out value="${repo.ownerId}" /> <small class="muted"><a href="/?ownerId=<c:out value="${repo.ownerId}" />">see more <c:out value="${repo.ownerId}" />'s repos</a></small></dd>
                  <dt>id</dt>
                  <dd><c:out value="${repo.id}" /></dd>
                  <dt>key</dt>
                  <dd><c:out value="${repo.repoId}" /></dd>
                  <dt>parent</dt>
                  <dd><c:choose><c:when test="${empty repo.parentId}">-</c:when><c:otherwise><a href="/?id=<c:out value="${repo.parentId}" />"><c:out value="${repo.parentId}" /></a></c:otherwise></c:choose></dd>
                  <dt>homepage</dt>
                  <dd><c:choose><c:when test="${empty repo.homepage}">-</c:when><c:otherwise><a href="<c:out value="${repo.homepage}" />" target="_blank"><c:out value="${repo.homepage}" /></a></c:otherwise></c:choose></dd>
                  <dt>pushedAt</dt>
				  <c:set var="pushedAt" value="${repo.pushedAt}"/>
                  <dd><abbr class="timeago" title="<fmt:formatDate pattern="yyyy-MM-dd" value="${pushedAt}" />"><fmt:formatDate pattern="yyyy-MM-dd" value="${pushedAt}" /></abbr></dd>
                  <dt>updatedAt</dt>
				  <c:set var="updatedAt" value="${repo.updatedAt}"/>
                  <dd><abbr class="timeago" title="<fmt:formatDate pattern="yyyy-MM-dd" value="${updatedAt}" />"><fmt:formatDate pattern="yyyy-MM-dd" value="${updatedAt}" /></abbr></dd>
                  <dt>createdAt</dt>
				  <c:set var="createdAt" value="${repo.createdAt}"/>
                  <dd><abbr class="timeago" title="<fmt:formatDate pattern="yyyy-MM-dd" value="${createdAt}" />"><fmt:formatDate pattern="yyyy-MM-dd" value="${createdAt}" /></abbr></dd>
                  <dt>language</dt>
                  <dd><span class="badge badge-inverse"><c:out value="${repo.language}"/></span></dd>
                  <dt>watchers</dt>
                  <dd><span class="badge badge-success"><c:out value="${repo.watchers}"/></span></dd>
                  <dt>size</dt>
                  <dd><span class="badge"><c:out value="${repo.size}"/></span></dd>
                  <dt>forks</dt>
                  <dd><span class="badge badge-info"><c:out value="${repo.forks}"/></span></dd>
                  <dt>open issues</dt>
                  <dd><span class="badge badge-important"><c:out value="${repo.openIssues}"/></span></dd>
                </dl>
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
			<small class="muted"><strong>First scan:</strong> <c:out value="${repo.firstScanedAt}" />. <strong>Last scan:</strong> <c:out value="${repo.lastScanedAt}" /></small>
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
