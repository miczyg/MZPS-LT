<#--Specify allowed categories-->
<#assign categories_list = ["Mlodzik", "Mlodziczka", "Kadetka"] />

<div class="panel">
    <ul class="nav nav-pills" role = "tablist">
    <#list categories_list as category>
        <li role="presentation" ng-class="{active:ctrl.isSelected('${category}')}">
            <a role="tab" ng-click="ctrl.selectCategory('${category}')">${category}</a></li>
    </#list>
    </ul>
</div>

<div class="panel panel-default">
    <div class="panel-heading"><span class="lead">Ranking ligowy </span></div>
    <div class="panel-body">
        <div class="table-responsive">
            <table class="table table-condensed">
                <thead>
                <tr>
                    <th>MIEJSCE</th>
                    <th>ZESPÓŁ</th>
                    <th>PUNKTY RANKINGOWE</th>
                    <th>LIGA TURNIEJOWA</th>
                </tr>
                </thead>
                <tbody>
                <#--TODO: reurn controlled by selected tab-->
                <tr ng-repeat="u in ctrl.getStandings() | orderBy : 'u.totalSeasonPoints'">
                    <td>{{ $index + 1 }}</td>
                    <td>{{u.name}}</td>
                    <td>{{u.totalSeasonPoints}}</td>
                    <td>{{u.league.name}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>