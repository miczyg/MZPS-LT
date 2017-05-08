<div class="panel">
    <ul class="nav nav-pills" role = "tablist">
        <li role="presentation" class="active" ng-class="{active:ctrl.isSelected(1)}">
            <a role="tab" ui-sref="teams" ng-click="ctrl.select(1)">Wszystkie</a></li>
        <li role="presentation" ng-class="{active:ctrl.isSelected(2)}">
            <a role="tab" ng-click="ctrl.select(2)" >Młodzicy</a></li>
        <li role="presentation" ng-class="{active:ctrl.isSelected(3)}">
            <a role="tab" data-toggle="tab" ng-click="ctrl.select(3)"">Młodziczki</a></li>
        <li role="presentation" ng-class="{active:ctrl.isSelected(4)}">
            <a role="tab" ng-click="ctrl.select(4)">Kadeci</a></li>
        <li role="presentation" ng-class="{active:ctrl.isSelected(5)}">
            <a role="tab" ng-click="ctrl.select(5)">Kadetki</a></li>
    </ul>
</div>
<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">Lista Zespołów </span></div>
    <div class="panel-body">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th></th>
                    <th>Nazwa zespołu</th>
                    <th>Kategoria</th>
                    <th>Trener</th>
                    <th>Telefon kontaktowy</th>
                    <th width="100"></th>
                    <th width="100"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="u in ctrl.getAllTeams() | filter:ctrl.categoryFilter:ctrl.strictFilter">
                    <td>{{ $index + 1 }}</td>
                    <td>{{u.name}}</td>
                    <td>{{u.category}}</td>
                    <td>{{u.coach}}</td>
                    <td>{{u.phone}}</td>
                <#--SHOW ONLY IF ADMIN-->
                    <td>
                        <button type="button" ng-click="ctrl.editTeam(u.id)" class="btn btn-success custom-width">Edit
                        </button>
                    </td>
                    <td>
                        <button type="button" ng-click="ctrl.removeTeam(u.id)" class="btn btn-danger custom-width">
                            Remove
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<#--ONLY IF ADMIN-->
<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">Nowy zespół </span></div>
    <div class="panel-body">
        <div class="formcontainer">
            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl.team.id"/>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label" for="uname">Nazwa zespołu</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.team.name" id="uname"
                                   class="username form-control input-sm" placeholder="Enter team name" required
                                   ng-minlength="3"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label" for="category">Kategoria</label>
                        <div class="col-md-7">
                            <select ng-model="ctrl.team.category" id="category" required">
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
                        <label class="col-md-2 control-label" for="sex">Trener</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.team.coach" id="coach"
                                   class="form-control input-sm" placeholder="Enter coach name" required
                                   ng-minlength="5"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label" for="contact">Telefon kontaktowy</label>
                        <div class="col-md-7">
                            <input type="number" ng-model="ctrl.team.phone" id="phone"
                                   class="form-control input-sm" placeholder="Enter contact phone number" required
                                   ng-minlength="9"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit" value="{{!ctrl.team.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm"
                               ng-disabled="myForm.$invalid || myForm.$pristine">
                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm"
                                ng-disabled="myForm.$pristine">Reset Form
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
