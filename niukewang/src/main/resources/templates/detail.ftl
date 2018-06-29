<#include "header.ftl">
<link rel="stylesheet" href="../styles/detail.css">
<div class="zg-wrap zu-main clearfix with-indention-votebar" itemscope="" itemtype="http://schema.org/Question"
     id="zh-single-question-page" data-urltoken="36301524" role="main">
    <div class="zu-main-content">
        <div class="zu-main-content-inner">
            <meta itemprop="isTopQuestion" content="false">
            <meta itemprop="visitsCount" content="402">
            <!--
            <div class="zm-tag-editor zg-section">
                <div class="zm-tag-editor-labels zg-clear">
                    <a data-tip="t$b$19550730" class="zm-item-tag" href="">新浪微博</a>
                    <a data-tip="t$b$19554412" class="zm-item-tag" href="">网络营销</a>
                    <a data-tip="t$b$19559739" class="zm-item-tag" href="">微博粉丝</a>
                    <a data-tip="t$b$19560290" class="zm-item-tag" href="">僵尸粉</a>
                    <a data-tip="t$b$19565757" class="zm-item-tag" href="">网络水军</a>
                    <a href="javascript:;" class="zu-edit-button" name="edit">
                        <i class="zu-edit-button-icon"></i>修改</a>
                </div>
            </div>
            -->
            <div id="zh-question-title" data-editable="true" class="zm-editable-status-normal">
                <h2 class="zm-item-title">

                    <span class="zm-editable-content">${question.title}</span>

                </h2>
            </div>
            <div id="zh-question-detail" class="zm-item-rich-text zm-editable-status-normal">
                <div class="zm-editable-content">${question.content}</div>
            </div>
            <div class="zm-side-section">
                <div class="zm-side-section-inner" id="zh-question-side-header-wrap">
                    <button data-follow="q:m:button" class="follow-button zg-follow zg-btn-green" data-id="6727688">
                        关注问题
                    </button>
                    <div class="zh-question-followers-sidebar">
                        <div class="zg-gray-normal">
                            <a href="">
                                <strong>9</strong></a>人关注该问题
                        </div>
                        <div class="list zu-small-avatar-list zg-clear">
                                <span class="zm-item-link-avatar">
                                    <img title="匿名用户" class="zm-item-img-avatar"
                                         src="../images/res/aadd7b895_s.jpg"></span>
                            <a data-tip="p$b$yi-yi-98-91-99" class="zm-item-link-avatar" href=""
                               data-original_title="奕奕">
                                <img src="../images/res/6ceea810748d179f57cac0baa5cf9592_s.jpg"
                                     class="zm-item-img-avatar"></a>
                            <a data-tip="p$b$wang-wu-29-54" class="zm-item-link-avatar" href=""
                               data-original_title="王五">
                                <img src="../images/res/da8e974dc_s.jpg" class="zm-item-img-avatar"></a>
                            <a data-tip="p$b$huang-xiang-90-99" class="zm-item-link-avatar" href=""
                               data-original_title="案小声">
                                <img src="../images/res/19456ebfe8b207320735f282769ac635_s.jpg"
                                     class="zm-item-img-avatar"></a>
                            <a data-tip="p$b$yingxiaodao" class="zm-item-link-avatar" href="" data-original_title="营销岛">
                                <img src="../images/res/11be4a90ed938abfbab4899df56ee754_s.png"
                                     class="zm-item-img-avatar"></a>
                            <a data-tip="p$b$hero-hou-23" class="zm-item-link-avatar" href=""
                               data-original_title="Hero Hou">
                                <img src="../images/res/da8e974dc_s.jpg" class="zm-item-img-avatar"></a>
                            <a data-tip="p$b$jin-hua-zhang-10" class="zm-item-link-avatar" href=""
                               data-original_title="荷尔蒙毒品">
                                <img src="../images/res/d207854fffc9e0289fbd6bbbb3986988_s.jpg"
                                     class="zm-item-img-avatar"></a>
                            <a title="宇轩" data-tip="p$t$yu-xuan-91-11" class="zm-item-link-avatar" href="">
                                <img src="../images/res/1ac7840eeb19ada0bbf85f51702d5784_s.jpg"
                                     class="zm-item-img-avatar"></a>
                            <a title="尹锋" data-tip="p$t$yin-feng" class="zm-item-link-avatar" href="">
                                <img src="../images/res/da8e974dc_s.jpg" class="zm-item-img-avatar"></a>
                        </div>
                    </div>
                </div>
            </div>
            <div id="zh-question-answer-wrap" data-pagesize="10" class="zh-question-answer-wrapper navigable"
                 data-widget="navigable" data-navigable-options="{&quot;items&quot;: &quot;&gt;.zm-item-answer&quot;}"
                 data-init="{&quot;params&quot;: {&quot;url_token&quot;: 36301524, &quot;pagesize&quot;: 10, &quot;offset&quot;: 0}, &quot;nodename&quot;: &quot;QuestionAnswerListV2&quot;}">

                <#list comments as comment>
                    <div class="zm-item-answer  zm-item-expanded js-comment">
                        <link itemprop="url" href="">
                        <meta itemprop="answer-id" content="22162611">
                        <meta itemprop="answer-url-token" content="66862039">
                        <a class="zg-anchor-hidden" name="answer-22162611"></a>
                    <div class="zm-votebar goog-scrollfloater js-vote" data-id="${comment.comment.id}">
                        <#if (comment.liked > 0)>
                        <button class="up js-like pressed" title="赞同">
                        <#else>
                        <button class="up js-like" title="赞同">
                        </#if>
                                <i class="icon vote-arrow"></i>
                                <span class="count js-voteCount">${comment.likeCount}</span>
                                <span class="label sr-only">赞同</span>
                            </button>

                        <#if (comment.liked < 0)>
                        <button class="down js-dislike pressed" title="反对，不会显示你的姓名">
                        <#else>
                        <button class="down js-dislike" title="反对，不会显示你的姓名">
                        </#if>
                        <i class="icon vote-arrow"></i>
                        <span class="label sr-only">反对，不会显示你的姓名</span>
                    </button>
                    </div>
                    <div class="answer-head">
                        <div class="zm-item-answer-author-info">
                            <a class="zm-item-link-avatar avatar-link" href="" target="_blank"
                               data-tip="p$t$yingxiaodao">
                                <img src="${comment.user.headUrl}"
                                     class="zm-list-avatar avatar"></a>
                            <a class="author-link" target="_blank" href="/user/${comment.user.id}">${comment.user.name}</a>
                            </div>
                        <div class="zm-item-vote-info" data-votecount="28" data-za-module="VoteInfo">
                                <span class="voters text">
                                    <a href="" class="more text">
                                        <span class="js-voteCount">${comment.likeCount}</span>&nbsp;人赞同</a></span>
                        </div>
                    </div>
                    <div class="zm-item-rich-text expandable js-collapse-body" data-resourceid="6727688"
                         data-action="/answer/content" data-author-name="营销岛"
                         data-entry-url="/question/36301524/answer/66862039">
                        <div class="zm-editable-content clearfix">
                            ${comment.comment.content}
                        </div>
                    </div>
                    <a class="zg-anchor-hidden ac" name="22162611-comment"></a>
                    <div class="zm-item-meta answer-actions clearfix js-contentActions">
                        <div class="zm-meta-panel">
                            <a itemprop="url" class="answer-date-link meta-item" target="_blank" href="">发布于
                                 ${comment.comment.createdDate?string('yyyy-MM-dd HH:mm:ss')}</a>

                            <#--<!---->
                            <a href="" name="addcomment" class="meta-item toggle-comment js-toggleCommentBox">
                                <i class="z-icon-comment"></i>4 条评论</a>
                            <a href="" class="meta-item zu-autohide js-thank" data-thanked="false">
                                <i class="z-icon-thank"></i>感谢</a>

                            <button class="item-collapse js-collapse" style="transition: none;">
                                <i class="z-icon-fold"></i>收起
                            </button>
                            <#--&ndash;&gt;-->
                        </div>
                    </div>
                </div>
                </#list>
            </div>
            <a name="draft"></a>
            <form action="/addComment" method="post" id="commentform">
                <input type="hidden" name="questionId" value="${question.id}"/>
            <div id="zh-question-answer-form-wrap" class="zh-question-answer-form-wrap">
                <div class="zm-editable-editor-wrap" style="">
                    <div class="zm-editable-editor-outer">
                        <div class="zm-editable-editor-field-wrap">
                            <textarea name="content" id="content" class="zm-editable-editor-field-element editable" style="font-style: italic;width:100%;"></textarea>
                        </div>
                    </div>

                    <div class="zm-command clearfix">
                            <span class=" zg-right">
                                <button type="submit" class="submit-button zg-btn-blue">发布回答</button></span>
                    </div>
                </div>
            </div>
            </form>

        </div>
    </div>
</div>
<#include "footer.ftl">