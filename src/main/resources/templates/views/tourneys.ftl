<#include "../elements/match-modal.ftl">

<#assign categories_list = ["Mlodzik", "Mlodziczka", "Kadetka"] />

<div class="panel">
    <ul class="nav nav-pills" role = "tablist">
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
        <table class="table table-bordered">
            <tbody>
            <tr>
                <th></th>
                <th>Zespół 1</th>
                <th>Zespół 2</th>
                <th>Zespół 3</th>
            </tr>
            <tr>
                <th>Zespół 1</th>
                <td class="danger"></td>
                <td style="cursor: pointer" data-toggle="modal" data-target="#matchModal" data-id="ctrl.matchId">wynik 1
                    : 2
                </td>
                <td>wynik 1 : 3</td>
            </tr>
            <tr>
                <th>Zespół 2</th>

                <td>wynik 2 : 1</td>
                <td class="danger"></td>
                <td>wynik 2 : 3</td>
            </tr>
            <tr>
                <th>Zespół 3</th>
                <td>wynik 3 : 1</td>
                <td>wynik 3 : 2</td>
                <td class="danger"></td>
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
                <th>Zespół</th>
                <th>Punkty</th>
                <th>Sety</th>
                <th>Małe punkty</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>









