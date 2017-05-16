<#macro categories_nav categories_list>
<div class="panel">
    <ul class="nav nav-pills" role="tablist">
        <#list categories_list as category>
            <li role="presentation" ng-class="{active:ctrl.isSelected('${category}')}">
                <a role="tab" ng-click="ctrl.selectCategory('${category}')">${category}</a></li>
        </#list>
    </ul>
</div>
</#macro>