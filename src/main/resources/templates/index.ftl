<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/app.css"/>
        <link rel="stylesheet" type="text/css" href="css/navbar.css">

        <script src="js/lib/angular.min.js" ></script>
        <script src="js/lib/angular-ui-router.min.js" ></script>
        <script src="js/lib/localforage.min.js" ></script>
        <script src="js/lib/ngStorage.min.js"></script>
        <script src="js/app/app.js"></script>
        <script src="js/app/TeamService.js"></script>
        <script src="js/app/TeamController.js"></script>
    </head>
    <body  ng-app="mzpsApp">
        <div role="navigation" class="navbar navbar-inverse">
            <div class="navbar-header">
                <a class="navbar-brand" ui-sref="home">Turnieje Ligowe MZPS</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a ui-sref="classification">Tabela</a></li>
                <li><a ui-sref="tournaments">Turnieje</a></li>
                <li><a ui-sref="teams">Zespoły</a></li>
            </ul>
        </div>
        <div class="container-fluid">
            <div ui-view></div>
        </div>

    </body>
</html>