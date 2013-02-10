# gitgraph

GitHub public repo explorer for [GAE](https://developers.google.com/appengine/) based on the GitHub API [eclipse/egit-github](https://github.com/eclipse/egit-github).

Find interesting projects and get recomendations based on your prefences and/or profile.

Live demo at [http://test.githubgraph.appspot.com/](http://test.githubgraph.appspot.com/)

## Get the code

    git clone https://github.com/kpacha/gitgraph.git

## Configure your project

### GitHub API

Fix the properties file replacing the _XXXXXXXXXXX_ with your GitHub credentials

src/main/resources/com/github/kpacha/gitgraph/service/GitHubAPICredentials.properties

    # GitHub API credentials
    login=XXXXXXXXXXX
    password=XXXXXXXXXXX

### Analytics API (optional)

The jsp files could include the Google Analytics pixel. Just uncomment the next block at the end of both files and change _UA-XXXXX-X_ to be your site's ID

    <script>
        var _gaq=[['_setAccount','UA-XXXXX-X'],['_trackPageview']];
        (function(d,t){var g=d.createElement(t),s=d.getElementsByTagName(t)[0];
        g.src=('https:'==location.protocol?'//ssl':'//www')+'.google-analytics.com/ga.js';
        s.parentNode.insertBefore(g,s)}(document,'script'));
    </script>

## Customize your frontend (optional)

The _yeoman_ folder contains the yeoman project for the webapp. If you are familiar with the yeoman console, you already know what you can do. Visit the [Yeoman Project](http://yeoman.io/) if you do not why it is awesome!

## Run your project

Check out your local deployed version at [http://localhost:8080/](http://localhost:8080/) with a simple

    mvn gae:run

Thanks to the [Maven GAE Plugin](https://github.com/maven-gae-plugin/maven-gae-plugin)

## Deploy your project

### GAE deployment configuration

Create a new server entry in your maven settings with the id _appengine.google.com_

~/.m2/settings.xml

    <servers>
        ...
        <server>
            <id>appengine.google.com</id>
            <username>YOUR_USER_HERE@gmail.com</username>
            <password>YOUR PASSWORD HERE</password>
        </server>
        ...
    </servers>

More over password encryption at the [Maven Project Website](http://maven.apache.org/guides/mini/guide-encryption.html)

Set up your _src/main/webapp/WEB-INF/appengine-web.xml_ with your appspot application id and the version

    <application>githubgraph</application>
    <version>test</version>

You also should check your _pom.xml_ and set up the version to be deployed

    <properties>
        ...
        <gae.application.version>test</gae.application.version>
        ...
    </properties>

### Deploy!

Again, thanks to the [Maven GAE Plugin](https://github.com/maven-gae-plugin/maven-gae-plugin), it is just as simple as

    mvn gae:deploy

## Documentation and reports

There are several basic reports configured. Look for them in the _target_ forlder after executing the _mvn verify_ phase (like _install_ or _deploy_)

    mvn verify

Look at the _pom.xml_ file for the plugin configuration and customize them

    <!-- Generates reports during verify phase -->
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-project-info-reports-plugin</artifactId>
        <version>2.6</version>
        <executions>
            <execution>
            <id>attach-reports</id>
            <phase>verify</phase>
            <goals>
                <goal>index</goal>
                <goal>dependencies</goal>
                <goal>dependency-info</goal>
                <goal>modules</goal>
                <goal>summary</goal>
            </goals>
            </execution>
        </executions>
    </plugin>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-javadoc-plugin</artifactId>
        <version>2.9</version>
        <configuration>
            <aggregate>true</aggregate>
        </configuration>
        <executions>
            <execution>
                <id>attach-javadocs</id>
                <phase>verify</phase>
                <goals>
                    <goal>javadoc</goal>
                </goals>
            </execution>
        </executions>
    </plugin>


## Other dependencies

The [jQery timeago](https://github.com/rmm5t/jquery-timeago) library is used for the time formating
