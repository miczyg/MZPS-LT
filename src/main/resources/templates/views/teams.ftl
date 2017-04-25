
<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">List of Teams </span></div>
    <div class="panel-body">
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>CATEGORY</th>
                    <th>SEX</th>
                    <th width="100"></th>
                    <th width="100"></th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="u in ctrl.getAllTeams()">
                    <td>{{u.id}}</td>
                    <td>{{u.name}}</td>
                    <td>{{u.category}}</td>
                    <td>{{u.sex}}</td>
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
<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">Team </span></div>
    <div class="panel-body">
        <div class="formcontainer">
            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                <input type="hidden" ng-model="ctrl.team.id"/>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="uname">Name</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.team.name" id="uname"
                                   class="username form-control input-sm" placeholder="Enter team name" required
                                   ng-minlength="3"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="category">Category</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.team.category" id="category" class="form-control input-sm"
                                   placeholder="Enter category, ex. U16" required ng-minlength="3"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="sex">Sex</label>
                        <div class="col-md-7">
                        <#--dropdown here-->
                            <select ng-model="ctrl.team.sex" id="sex" class="form-control input-sm"
                                    placeholder="MALE/FEMALE">
                                <option>MALE</option>
                                <option>FEMALE</option>
                            </select>
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
