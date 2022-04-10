<#import "parts/common.ftlh" as c>
<#import "parts/pager.ftlh" as p>
<@c.page>
<div>
    <a class="btn btn-primary mt-2" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add news
    </a>
    <div class="collapse mt-2" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data" >
                <div class="form-group">
                    <input type="text" class="form-control" name="header" placeholder="Enter title">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="text" placeholder="Enter your message">
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile" >
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary" type="submit">Add</button>
                </div>
            </form>
    </div>
</div>
<div>
    <#if error??>
    <span>${error}</span>
</#if>
</div>
<#list page.content as news>
<div class="card text-center mt-2" style="width: 30rem;">
        <div class="card-header">
            <h2>${news.header}</h2>
        </div>
        <div class="m-2">
            <p class="card-text">${news.text}</p>
        </div>
        <div>
            <#if news.image??>
            <img src="/img/news/${news.id}" class="card-img-top" alt="Card image cap" style="width:100%">
        </#if>
    </div>
    <div class="card-footer">
    <i>${news.createDateTime}</i>
    </div>
</div>
<#else>
No news
</#list>
<@p.pager url page />
</@c.page>
