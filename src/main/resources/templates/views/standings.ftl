<div class="panel">
    <ul class="nav nav-pills">
    <#--TODO: controllers here as response to reload table-->
        <li role="presentation" class="active"><a ui-sref="male16">Młodzicy</a></li>
        <li role="presentation"><a ui-sref="female18">Młodziczki</a></li>
        <li role="presentation"><a ui-sref="male16">Kadeci</a></li>
        <li role="presentation"><a ui-sref="female18">Kadetki</a></li>
    </ul>
</div>

<div class="panel panel-default">
    <div class="panel-heading"><span class="lead">Ranking ligowy </span></div>
    <div class="panel-body">
        <div class="table-responsive">
            <table class="table table-condensed">
                <thead>
                <tr>
                    <th>ZESPÓŁ</th>
                    <th>PUNKTY RANGINGOWE</th>
                    <th>LIGA TURNIEJOWA</th>
                </tr>
                </thead>
                <tbody>
                <#--TODO: reurn controlled by selected tab-->
                <tr ng-repeat="u in ctrl.getStandings(category, sex) | orderBy : 'points'">
                    <td>{{ $index + 1 }}</td>
                    <td>{{u.name}}</td>
                    <td>{{u.points}}</td>
                    <td>{{u.current_league}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>