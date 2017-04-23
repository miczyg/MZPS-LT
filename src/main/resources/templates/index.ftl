<!DOCTYPE html>

<html lang="en" ng-app="mzpsApp">
    <head>
        <title>${title}</title>
        <link href="/webjars/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
    </head>
    <body>

        <div ui-view></div>
        <script src="js/lib/angular.min.js" ></script>
        <script src="js/lib/angular-ui-router.min.js" ></script>
        <script src="js/lib/localforage.min.js" ></script>
        <script src="js/lib/ngStorage.min.js"></script>
        <script src="js/app/app.js"></script>
        <script src="js/app/TeamService.js"></script>
        <script src="js/app/TeamController.js"></script>
    </body>
</html>