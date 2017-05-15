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

<div class="panel panel-default">
    <div class="panel-heading"><span class="lead">Nowy turniej</span></div>
    <div class="panel-body">
        <div class="formcontainer">
            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
            <form ng-submit="ctrl.submit()" name="tourneyForm" class="form-horizontal">
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
                            <select ng-model="ctrl.tourney.category" id="category" required">
                            <option>Mlodzik</option>
                            <option>Mlodziczka</option>
                            <option>Kadet</option>
                            <option>Kadetka</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label" for="tourneyDate">Data</label>
                        <div class="col-md-7">
                            <input type="date" ng-model="ctrl.tourney.date" id="tourneyDate" required />
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
                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm"
                                ng-disabled="tourneyForm.$pristine">Reset Form
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">Lista lig</span></div>
    <div class="panel-body">TEST</div>
</div>

<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">Nowa liga</span></div>
    <div class="panel-body">TESTl</div>
</div>