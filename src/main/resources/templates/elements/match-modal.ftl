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
                    <div class="table-responsive">
                        <table class="table table-hover table-condensed">
                            <thead>
                            <tr>
                                <th>SET</th>
                                <th class="team1">...</th>
                                <th class="team2">...</th>
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

