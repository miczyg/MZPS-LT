<#--Specify allowed categories-->
<#--TODO: categories refactor to separate file-->
<#assign categories_list = ["Mlodzik", "Mlodziczka", "Kadetka"] />

<#--TODO: split for diffrent files?-->
<#--TOURNEY TABLE-->
<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">Lista turniejów</span></div>
    <div class="panel-body">
    	<div class="table-responsive">
        	<table class="table table-hover">
            	<thead>
            	<tr>
                	<th></th>
                	<th>Nazwa turnieju</th>
                    <th>Kategoria</th>
                	<th>Data</th>
                    <th>Adres</th>
                	<th width="100"></th>
                	<th width="100"></th>
				</tr>
				</thead>
                <tbody>
                <tr ng-repeat="u in ctrl.getAllTourneys()">
                    <td>{{ $index + 1 }}</td>
                    <td>{{u.name}}</td>
                    <td>{{u.categoryName}}</td>
                    <td>{{u.date}}</td>
                    <td>{{u.address.cityName}}
                        <br>
                        {{u.address.streetName}}
                        <br>
                        {{u.address.hallName}}
                    </td>
                    <td>
                        <button type="button" ng-click="ctrl.editTourney(u.id)" class="btn btn-success custom-width">Edit
                        </button>
                    </td>
                    <td>
                        <button type="button" ng-click="ctrl.removeTourney(u.id)" class="btn btn-danger custom-width">
                            Remove
                        </button>
                    </td>
                </tr>
                </tbody>
			</table>
		</div>
	</div>
</div>

<#--TOURNEY ADD FORM-->
<div class="panel panel-default">
    <div class="panel-heading"><span class="lead">Nowy turniej</span></div>
    <div class="panel-body">
        <div class="formcontainer">
            <div class="alert alert-success" role="alert" ng-if="ctrl.tourneySuccessMessage">{{ctrl.tourneySuccessMessage}}</div>
            <div class="alert alert-danger" role="alert" ng-if="ctrl.tourneyErrorMessage">{{ctrl.tourneyErrorMessage}}</div>
            <form ng-submit="ctrl.submitTourney()" name="tourneyForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl.tourney.id"/>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label" for="tourneyName">Nazwa turnieju</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.tourney.name" id="tourneyName"
                                   class="username form-control input-sm" placeholder="Enter tourney name" required
                                   ng-minlength="3"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label" for="category">Kategoria</label>
                        <div class="col-md-7">
                            <select class="form-control" ng-model="ctrl.tourney.category" id="category" required">
                            <option disabled selected value style="display: none">Wybierz kategorię </option>
                        <#list categories_list as category>
                            <option>${category}</option>
                        </#list>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label" for="tourneyDate">Data</label>
                        <div class="col-md-7">
                            <input class="form-control input-sm" name="tourneyDate" placeholder="DD/MM/YYY"
                                   type="text" ng-model="ctrl.tourney.date" id="tourneyDate" required />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label" for="cityName">Nazwa miasta</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.tourney.address.cityName" id="cityName"
                                   class="username form-control input-sm" placeholder="Enter city name" required />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label" for="streetName">Nazwa ulicy</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.tourney.address.streetName" id="streetName"
                                   class="username form-control input-sm" placeholder="Enter street name" required />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label" for="hallName">Nazwa hali</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.tourney.address.hallName" id="hallName"
                                   class="username form-control input-sm" placeholder="Enter hall name" required />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit" value="{{!ctrl.tourney.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm"
                               ng-disabled="tourneyForm.$invalid || tourneyForm.$pristine">
                        <button type="button" ng-click="ctrl.resetTourneyForm()" class="btn btn-warning btn-sm"
                                ng-disabled="tourneyForm.$pristine">Reset Form
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<#--LEAGUE LIST-->
<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">Lista lig</span></div>
    <div class="panel-body">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th></th>
                    <th>Nazwa ligi</th>
                    <th>Punkty ligowe</th>
                    <th width="100"></th>
                    <th width="100"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="u in ctrl.getAllLeagues()">
                    <td>{{ $index + 1 }}</td>
                    <td>{{u.name}}</td>
                    <td>
                        <table border="1">
                            <tr>
                                <th class="league-points-table">miejsce</th>
                                <th class="league-points-table">punkty</th>
                            </tr>
                            <tr ng-repeat="l in u.leaguePoints">
                                <td class="league-points-table">{{l.place}}</td>
                                <td class="league-points-table">{{l.points}}</td>
                            </tr>
                        </table>
                    <td>
                        <button type="button" ng-click="ctrl.editLeague(u.id)" class="btn btn-success custom-width">Edit
                        </button>
                    </td>
                    <td>
                        <button type="button" ng-click="ctrl.removeLeague(u.id)" class="btn btn-danger custom-width">
                            Remove
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<#---->
<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">Nowa liga</span></div>
    <div class="panel-body">
        <div class="formcontainer">
            <div class="alert alert-success" role="alert" ng-if="ctrl.leagueSuccessMessage">{{ctrl.leagueSuccessMessage}}</div>
            <div class="alert alert-danger" role="alert" ng-if="ctrl.leagueErrorMessage">{{ctrl.leagueErrorMessage}}</div>
            <form ng-submit="ctrl.submitLeague()" name="leagueForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl.league.id"/>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label" for="leagueName">Nazwa ligi</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.league.name" id="leagueName"
                                   class="username form-control input-sm" placeholder="Enter league name" required
                                   ng-minlength="3"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label" for="leaguePoints">Punkty ligowe</label>
                        <br>
                        <div class="col-md-7">
                            <fieldset data-ng-repeat="leaguePoint in ctrl.league.leaguePoints">
                                <label class="col-md-2 control-label" for="leaguePoint">{{leaguePoint.place + " miejsce"}}</label>
                                <input type="number" ng-model="leaguePoint.points"
                                       id="leaguePoint" placeholder="Enter league points"
                                       class="form-control input-sm" style="width: 35%; display: inline;" required>
                                <button class="btn btn-danger btn-sm" ng-show="$last" ng-click="ctrl.removeLeaguePointsChoice()">-</button>
                            </fieldset>
                            <button class="btn btn-success" ng-click="ctrl.addNewLeaguePointsChoice()">+</button>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit" value="{{!ctrl.league.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm"
                               ng-disabled="leagueForm.$invalid || leagueForm.$pristine">
                        <button type="button" ng-click="ctrl.resetLeagueForm()" class="btn btn-warning btn-sm"
                                ng-disabled="leagueForm.$pristine">Reset Form
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>