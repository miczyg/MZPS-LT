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
<div class="panel" ng-if="ctrl.getTourneys().length">
    <ul class="nav nav-pills">
        <li class="dropdown" ng-repeat="t in ctrl.getTourneys()">
            <a class="dropdown-toggle" data-toggle="dropdown">{{t.name}}<span
                    class="caret"></span></a>
            <ul class="dropdown-menu">
                <li ng-repeat="l in ctrl.getLeagues() | filterForTourney:t.id">
                    <a class="active cell_active"
                       ng-click="ctrl.selectLeague(l)">{{l.name}}</a>
                </li>
            </ul>
        </li>
    </ul>
</div>

<#--MATCH TABLE-->
<div class="panel panel-default " ng-if="ctrl.getMatches().length">
    <div class="panel-heading">
        <span class="lead">Mecze</span>
    </div>
<#--TODO: logic for auto generate-->
    <div class="panel-body">
        <table class="table table-striped table-nonfluid col-md-3">
            <tbody>
            <tr ng-repeat="match in ctrl.getMatches()">
                <th>{{ $index + 1 }}</th>
                <td>{{match.matchTeams[0].name}} : {{match.matchTeams[1].name}}</td>
                <td data-toggle="modal"
                    data-target="#matchModal"
                    class="cell_active"
                    ng-click="ctrl.setActiveMatch(match)"
                >
                    {{ctrl.displayResult(match)}}
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<#--TOURNEY CLASSIFICATION-->
<div class="panel panel-default " ng-if="ctrl.getMatches().length">
    <div class="panel-heading">
        <span class="lead">Klasyfikacja turnieju </span>
    </div>

<#--TODO: logic for auto generate-->
    <div class="panel-body">
        <table class="table table-striped table-nonfluid">
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
            <#--add popupus with detailed-->
            <tr ng-repeat="result in ctrl.getStandings() | orderBy:['tPoints', 'setsWon - pointsLost', 'pointsWon - setsLost']">
                <td>{{ $index + 1 }}</td>
                <td>{{result.name}}</td>
                <td>{{result.tPoints}}</td>
                <td>{{result.pointsWon - result.pointsLost}}</td>
                <td>{{result.setsWon - result.setsLost}}</td>
            </tr>
            </tbody>
        </table>
        <button type="button" ng-click="ctrl.confirmTourneyResults()" class="btn btn-warning btn-sm">
            Zatwierdź wyniki turnieju
        </button>
    </div>
</div>









