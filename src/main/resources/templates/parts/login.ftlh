<#macro login path isRegisterForm>
<div class="mt-2">
    <form action="${path}" method="post">
        <div class="form-group row">
            <label  class="col-sm-4 col-form-label"> User Name : </label>
            <div class="col-sm-8">
                <input type="text" name="username" class="form-control" placeholder="User name"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-4 col-form-label"> Password:</label>
            <div class="col-sm-8">
                <input type="password" name="password"  class="form-control" placeholder="Password"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary"><#if isRegisterForm>Create<#else>Sign In</#if></button>
        <#if !isRegisterForm> <div class="mt-2"><a href="/registration">Add new user</a></div></#if>
    </form>
</div>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <button type="submit" class="btn btn-primary">Sign Out</button>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>
</#macro>

