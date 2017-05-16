

<#--Specify allowed categories-->
<#assign categories_list = ["Mlodzik", "Mlodziczka", "Kadetka"] />

<div class="panel">
    <ul class="nav nav-pills" role = "tablist">
        <li role="presentation" ng-class="{active:ctrl.isSelected('')}">
            <a role="tab" ng-click="ctrl.selectCategory('')">Wszystkie</a></li>
    <#list categories_list as category>
        <li role="presentation" ng-class="{active:ctrl.isSelected('${category}')}">
            <a role="tab" ng-click="ctrl.selectCategory('${category}')">${category}</a></li>
    </#list>
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
                    <td>{{u.category.categoryName}}</td>
                    <td>{{u.coach}}</td>
                    <td>{{u.phone}}</td>
                <#--SHOW ONLY IF ADMIN-->
                    <td>
                        <button type="button" ng-click="ctrl.editTeam(u.id)" class="btn btn-success custom-width">Edytuj
                        </button>
                    </td>
                    <td>
                        <button type="button" ng-click="ctrl.removeTeam(u.id)" class="btn btn-danger custom-width">
                            Usuń
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
                        <label class="col-md-2 control-label" for="teamName">Nazwa zespołu</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.team.name" id="teamName"
                                   class="username form-control input-sm" placeholder="Enter team name" required
                                   ng-minlength="3"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label" for="category">Kategoria</label>
                        <div class="col-md-7">
                            <select class="form-control" ng-model="ctrl.team.category" id="category" required">
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
                        <label class="col-md-2 control-label" for="coach">Trener</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.team.coach" id="coach"
                                   class="form-control input-sm" placeholder="Enter coach name" required
                                   ng-minlength="5"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label" for="phone">Telefon kontaktowy</label>
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
