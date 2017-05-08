<div class="panel">
    <ul class="nav nav-pills">
        <li role="presentation" class="active"><a ui-sref="male16">Młodzicy</a></li>
        <li role="presentation"><a ui-sref="female18">Młodziczki</a></li>
        <li role="presentation"><a ui-sref="male16">Kadeci</a></li>
        <li role="presentation"><a ui-sref="female18">Kadetki</a></li>
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

<!-- Modal -->
<div class="modal fade" id="matchModal" tabindex="-1" role="dialog" aria-labelledby="matchModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h5 class="modal-title" id="matchModalLabel">Wynik meczu</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <table class="table table-hover table-condensed">
                            <thead>
                            <tr>
                                <th>SET</th>
                                <th>Zespól 1</th>
                                <th>Zespoł 2</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td scope="row">1</td>
                                <td><input type="number" min="0" ng-model="ctrl.matchResult.team1Result.set1Points"/>
                                </td>
                                <td><input type="number" min="0" ng-model="ctrl.matchResult.team2Result.set1Points"/>
                                </td>
                            </tr>
                            <tr>
                                <td scope="row">2</td>
                                <td><input type="number" min="0" ng-model="ctrl.matchResult.team1Result.set2Points"/>
                                </td>
                                <td><input type="number" min="0" ng-model="ctrl.matchResult.team2Result.set2Points"/>
                                </td>
                            </tr>
                            <tr>
                                <td scope="row">3</td>
                                <td><input type="number" min="0" ng-model="ctrl.matchResult.team1Result.set3Points"/>
                                </td>
                                <td><input type="number" min="0" ng-model="ctrl.matchResult.team2Result.set3Points"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Zamknij</button>
                    <button type="submit" class="btn btn-primary" formaction="ctrl.submitMatchResult()">Zapisz wynik
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>







