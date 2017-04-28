<div class="container-fluid">
    <ul class="nav nav-tabs">
    <#--TODO: here - control of rest of the page -->
        <li role="presentation" class="active"><a ui-sref="male16">Młodzicy</a></li>
        <li role="presentation"><a ui-sref="female18">Młodziczki</a></li>
        <li role="presentation"><a ui-sref="male16">Kadeci</a></li>
        <li role="presentation"><a ui-sref="female18">Kadetki</a></li>
    </ul>
</div class="container-fluid">
<#--TODO: probably bad odea with sidenav on desktop?-->
<#--BELOW BAD EXAMPLE-->
<div>
    <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; Turnieje</span>

    <div id="mySidenav" class="sidenav">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <#--TODO: sort in a way-->
        <a>
            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="true">
                Turniej 1
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                <li><a href="#">I Liga</a></li>
                <li><a href="#">II Liga</a></li>
                <li><a href="#">III Liga</a></li>
            </ul>
        </a>
        <a href="#">Turniej 2</a>
        <a href="#">Turniej 3</a>
        <a href="#">Turniej 4</a>
    </div>
</div>

