<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        
        <link rel="stylesheet" href="styles/4b6ffa21.main.css">
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
                <h3>Repo <c:out value="${repo.id}" /></h3>
                <dl class="dl-horizontal">
                  <dt>name</dt>
                  <dd><c:out value="${repo.name}" /></dd>
                  <dt>ownerId</dt>
                  <dd><c:out value="${repo.ownerId}" /></dd>
                  <dt>id</dt>
                  <dd><c:out value="${repo.id}" /></dd>
                  <dt>repoId</dt>
                  <dd><c:out value="${repo.repoId}" /></dd>
                  <dt>parent</dt>
                  <dd><c:out value="${repo.parentId}" /></dd>
                  <dt>firstScanedAt</dt>
                  <dd><c:out value="${repo.firstScanedAt}" /></dd>
                  <dt>lastScanedAt</dt>
                  <dd><c:out value="${repo.lastScanedAt}" /></dd>
                  <dt>pushedAt</dt>
                  <dd><c:out value="${repo.pushedAt}" /></dd>
                  <dt>updatedAt</dt>
                  <dd><c:out value="${repo.updatedAt}" /></dd>
                  <dt>createdAt</dt>
                  <dd><c:out value="${repo.createdAt}" /></dd>
                  <dt>size</dt>
                  <dd><c:out value="${repo.size}" /></dd>
                  <dt>language</dt>
                  <dd><c:out value="${repo.language}" /></dd>
                  <dt>forks</dt>
                  <dd><c:out value="${repo.forks}" /></dd>
                </dl>
            </div>

            <div class="span4">
                <h4>Suggestions</h4>
                <p>Get recomendations based on your favourite projects, technologies and languages.</p>
                <a id="surprise" href="/" class="btn btn-success disabled">Surprise me!</a>
                <h4>Search</h4>
                <p>Search repo by name, owner, relevance, language...</p>
                <form id="search" action="index" method="get">
                    <fieldset>
                        <input type="text" placeholder="owner/name" name="id" class="span12" >
                        <input type="text" placeholder="Owner" name="owner" class="span12" >
                        <input type="text" placeholder="Name" name="repoName" class="span12" >
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
    
    <script src="scripts/8ab52a5b.plugins.js"></script>
</body>
</html>
