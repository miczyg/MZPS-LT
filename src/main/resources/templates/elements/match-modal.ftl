<!-- Modal -->
<div class="modal fade" id="matchModal" role="dialog" aria-labelledby="matchModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form ng-submit="ctrl.submitMatchResult()" name="matchForm">
                <div class="modal-header">
                    <h5 class="modal-title" id="matchModalLabel">Wynik meczu</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="table-responsive">
                        <table class="table table-hover table-condensed">
                            <thead>
                            <tr>
                                <th>SET</th>
                                <th>{{ctrl.activeMatch.matchTeams[0].name}}</th>
                                <th>{{ctrl.activeMatch.matchTeams[1].name}}</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td scope="row">1</td>
                                <td>
                                    <input class="form-control" type="number " min="0"
                                           ng-model="ctrl.activeMatch.teamResults[0].set1Points"/>
                                </td>
                                <td><input class="form-control" type="number" min="0"
                                           ng-model="ctrl.activeMatch.teamResults[1].set1Points"/>
                                </td>
                            </tr>
                            <tr>
                                <td scope="row">1</td>
                                <td>
                                    <input class="form-control" type="number" min="0"
                                           ng-model="ctrl.activeMatch.teamResults[0].set2Points"/>
                                </td>
                                <td><input class="form-control" type="number" min="0"
                                           ng-model="ctrl.activeMatch.teamResults[1].set2Points"/>
                                </td>
                            </tr>
                            <tr>
                                <td scope="row">1</td>
                                <td>
                                    <input class="form-control" type="number" min="0"
                                           ng-model="ctrl.activeMatch.teamResults[0].set3Points"/>
                                </td>
                                <td><input class="form-control"type="number" min="0"
                                           ng-model="ctrl.activeMatch.teamResults[1].set3Points"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Zamknij</button>
                    <button type="submit" class="btn btn-primary" data-dismiss="modal"
                            ng-disabled="matchForm.$invalid || matchForm.$pristine"
                            ng-click="ctrl.submitMatchResult()">Zapisz wynik</button>
                </div>
            </form>
        </div>
    </div>
</div>

