<#import "parts/common.ftlh" as c>
<#import "parts/pager.ftlh" as p>
<@c.page>
<div>
    <form method="post" enctype="multipart/form-data">
        <input type="text" name="header" placeholder="Enter title">
        <input type="text" name="text" placeholder="Enter your message">
        <input type="file" name="file">
        <button type="submit">Add</button>
    </form>
</div>
<div> News List</div>
<#list page.content as news>
<div>
    <i>${news.header}</i>
    <span>${news.text}</span>
    <div>
        <#if news.filename??>
            <img src="/img/${news.filename}">
        </#if>
    </div>
    <i>${news.createDateTime}</i>
</div>
<#else>
No news
</#list>
<@p.pager url page />
</@c.page>
