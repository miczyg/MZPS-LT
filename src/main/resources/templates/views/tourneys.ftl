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

<div class="panel">
    <ul class="nav nav-pills">
    <#--TODO: auto generate pill with dropdowns -->
        <li class="active dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" ui-sref="#">I Turniej Ligowy<span
                    class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a class="active" href="#">I Liga</a></li>
                <li><a href="#">II Liga</a></li>
                <li><a href="#">III Liga</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" ui-sref="#">II Turniej Ligowy<span
                    class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">I Liga</a></li>
                <li><a href="#">II Liga</a></li>
                <li><a href="#">III Liga</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" ui-sref="#">III Turniej Ligowy<span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">I Liga</a></li>
                <li><a href="#">II Liga</a></li>
                <li><a href="#">III Liga</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" ui-sref="#">IV Turniej Ligowy<span
                    class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">I Liga</a></li>
                <li><a href="#">II Liga</a></li>
                <li><a href="#">III Liga</a></li>
            </ul>
        </li>
    </ul>
</div>


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









