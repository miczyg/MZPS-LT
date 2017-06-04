<#include "../elements/match-modal.ftl">

<#assign categories_list = ["Mlodzik", "Mlodziczka", "Kadetka"] />

<div class="panel">
    <ul class="nav nav-pills" role="tablist">
    <#list categories_list as category>
        <li role="presentation" ng-class="{active:ctrl.isSelected('${category}')}">
            <a role="tab" ng-click="ctrl.selectCategory('${category}')">${category}</a></li>
    </#list>
    </ul>
</div>

<#--TOURNEY DROPDOWNS-->
<div class="panel">
    <ul class="nav nav-pills">
        <li class="dropdown" ng-repeat="t in ctrl.tourneys">
            <a class="dropdown-toggle" data-toggle="dropdown">{{t.name}}<span
                    class="caret"></span></a>
            <ul class="dropdown-menu">
                <li ng-repeat="l in ctrl.leagues | filterForTourney:t.id"><a class="active" ng-click="ctrl.getMatches(l.id)">{{l.name}}</a></li>
            </ul>
        </li>
    </ul>
</div>

<#--BERGER TABLE-->
<div class="panel panel-default">
    <div class="panel-heading">
        <span class="lead">Tabela Bergera</span>
    </div>
<#--TODO: logic for auto generate-->
    <div class="panel-body">
        <table class="table table-bordered col-md-4">
            <thead>
            <tr>
                <th></th>
                <th ng-repeat="team in ctrl.mock_league.teams">{{team.name}}</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="team_row in ctrl.mock_league.teams">
                <th>{{team_row.name}}</th>
                <td ng-repeat="team_col in ctrl.mock_league.teams"
                    data-toggle="{{team_row === team_col ? '' : 'modal'}}"
                    data-target="#matchModal"
                    data-team1="{{team_row.name}}"
                    data-team2="{{team_col.name}}"
                    ng-class="team_row === team_col ? 'danger' : 'cell_active'">
                    {{ctrl.getMatchResult(team_row, team_col)}}
                </td>
            <#--TODO: change above-->
            </tr>
            </tbody>
        </table>
    </div>
</div>

<#--TOURNEY CLASSIFICATION-->
<div class="panel panel-default">
    <div class="panel-heading">
        <span class="lead">Klasyfikacja turnieju </span>
    </div>

<#--TODO: logic for auto generate-->
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Miejsce</th>
                <th>Zespół</th>
                <th>Punkty</th>
                <th>Sety</th>
                <th>Małe punkty</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="team in ctrl.mock_league.teams">
                <td>{{ $index + 1 }}</td>
                <td>{{team.name}}</td>
            </tr>
            <td>{{team.tpoint}}</td>
            <td>{{team.sets}}</td>
            <td>{{team.small_points}}</td>
            </tbody>
        </table>
    </div>
</div>









