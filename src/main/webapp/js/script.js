$(document).ready(function($) {
 
    
    $(".btn-home").click(function(e) {
        window.location = 'http://localhost:9000/ktwiter/rest/about';
    });
    
    $(".btn-test").click(function(e) {
        $.ajax({
            type: 'GET',
            url: 'rest/test/link',
            contentType: "text/html; charset=UTF-8",
            success: function(data) {
                if(data=="fail"){
                    $('article').load('signin.html');
                }else{
                    setCookie("login",data, 1);
                }
            }
        });
    });
    
    $(".btn-admin").click(function(e) {
        $('article').load('admin.html');
        $.ajax({
            type: 'GET',
            url: 'rest/admin',
            contentType: "application/json; charset=UTF-8",
            success: function(json) {
                    $.each(json, function(index, value) {
                        $(".list-group").append(
                            '<li class="list-group-item">'+
                                '<div class="checkbox">'+
                                    '<input type="checkbox" id="checkbox"/>'+
                                    '<label for="checkbox">'+
                                        '<a href="#" class="mbr-login"><b>'+value.login+'</b></a>'+
                                    '</label>'+
                                '</div>'+
                                '<div class="pull-right action-buttons">'+
                                    '<a href="#"><span class="glyphicon glyphicon-pencil"></span></a>'+
                                    '<a href="#" class="trash btn-delete-member"><span class="glyphicon glyphicon-trash"></span></a>'+
                                    '<a href="#" class="flag"><span class="glyphicon glyphicon-flag"></span></a>'+
                                '</div>'+
                            '</li>'
                        );
                    });
               
            }
        });
    });
    $(".btn-about").click(function(e) {
        $('article').load('rest/about');
    });
    $(".btn-contact").click(function(e) {
        $('article').load('rest/contact');
    });

//=================================================================================================================
//==========================================   Connexion   ========================================================
//=================================================================================================================	

    // Afficher le formulaire de connexion.
 
    $(".signin_frm").click(function(e) {
        $('article').load('signin.html');
    });

    // Se connecter.
    $('article').on("click", ".signin_frm", function(e) {
        $login = $("#ipt_login").val();
        $password = $("#ipt_password").val();
        $.ajax({
            type: 'POST',
            url: 'rest/ctrlsessions/signin',
            contentType: "text/html; charset=UTF-8",
            data: "login="+$login+"&password="+$password,
            success: function(data) {
                setCookie("login", data, 1);
                if(data!="fail"){
                    window.location = '/ktwiter';
                }else{
                    $('article').load('signin.html');
                }
            }
        });
        return false;
    });

    // Se deconnecter.
    $("header").on("click",".btn-logout",function(e) {
        $.ajax({
            url: 'rest/ctrlsessions/signout',
            success: function(login) {
                setCookie("login", login, -1);
                window.location = '/ktwiter';
            }
        });
    });

//=================================================================================================================
//==========================================     Member    ========================================================
//=================================================================================================================	

    // Afficher le formulaire d'inscription.
    $(".signup_frm").on("click", function(e) {
        $('article').load('signup.html');
    });

    // Ajouter un nouveau membre.
    $('article').on("click", ".btn-submit-member", function(e) {
        $login = $("#ipt-Login").val();
        $email = $("#ipt-Email").val();
        $password = $("#ipt-Password").val();
        $.ajax({
            type: 'POST',
            url: 'rest/ctrlmembers/addmember',
            contentType: "application/json; charset=UTF-8",
            data: "login="+$login+"&email="+$email+"&password="+$password,
            success: function(data) {
                alert(data);
                //$('article').load('article.scala.html');
            }
        });
    });
    
    // Supprimer un membre existant.
    $('article').on("click", ".btn-delete-member", function(e) {
        $login = $(this).parents("li").find(".mbr-login").text();
        $.ajax({
            type: 'POST',
            url: 'rest/ctrlmembers/delmember',
            contentType: "application/json; charset=UTF-8",
            data: "login="+$login,
            success: function(data) {
                alert(data);
                //$('article').load('article.scala.html');
            }
        });
    });   

    // Mettre a jours le profile d'un membre.
//    $('article').on("click", ".btn-update", function(e) {
//        $nom = $("#ipt_nom").val();
//        $prenom = $("#ipt_prenom").val();
//        $email = $("#ipt_email").val();
//        $day = $("#ipt_day").val();
//        $month = $("#ipt_month").val();
//        $year = $("#ipt_year").val();
//        $password = $("#ipt_password").val();
//        if ($("input:checked").val() != null) {
//            $sex = $("input:checked").val();
//        }
//        else {
//            $sex = "";
//        }
//        $.ajax({
//            type: 'POST',
//            url: '/updateprofile',
//            contentType: "application/json; charset=UTF-8",
//            data: JSON.stringify({"nom": $nom, "prenom": $prenom, "email": $email, "password": $password, "day": $day, "month": $month, "year": $year, "sex": $sex}),
//            success: function(data) {
////                //$('article').load('article.scala.html');
//            }
//        });
//        return false;
//    });

//    $('article').on("click", ".mbr-delete", function(e) {
//        alert("OK");
//        //$('article').load('/signup');
//    });

//    $('.btn-search').on("click", function(e) {
//        $search = $("#ipt-search").val();
//        alert($search);
//        $.ajax({
//            type: 'POST',
//            url: '/search',
//            contentType: "application/json; charset=UTF-8",
//            data: JSON.stringify({"search": $search}),
//            success: function(data) {
//                $('article').html(data);
//            }
//        });
//        return false;
//    });

//==================================================================================================================
//================================================   Posts   =======================================================
//==================================================================================================================

    // Faire un post. 	

    $(".btn-post-submit").on("click", function(e) {
        $post = $("#ipt-post").val();
        $.ajax({
            type: 'POST',
            url: 'rest/ctrlposts/submitpost',
            contentType: "application/json; charset=UTF-8",
            data: "post="+$post,
            success: function(json) {
                $('article').html("");
                $('article').append('<link rel="stylesheet" media="screen" href="css/post.css">')
                affichage(json);
//                
//                $.each(json, function(index, post) {
//                    $('article').append(
//                        '<div class="row post">'+
//                            '<div class="panel panel-default">'+
//                                '<div class="panel-heading">'+
//                                    '<div class="row">'+
//                                        '<div class="col-md-1">'+
//                                            '<img src="img/avatar.png" class="img-circle img-responsive" alt=""/>'+
//                                        '</div>'+
//                                        '<div>'+
//                                            'By :'+
//                                            '<a href="#" class="autor-login">'+
//                                                '<b>'+post.autor.login+'</b>'+
//                                            '</a>'+
//                                            '<p>'+post.content+'</p>'+
//                                        '</div>'+
//                                    '</div>'+
//                                    '<div class="row action">'+
//                                        '<button type="button" class="btn btn-primary btn-xs col-md-offset-1 btn-comment" title="Comment">'+
//                                            '<span class="glyphicon glyphicon-comment"></span>'+
//                                        '</button>'+
//                                        '<button type="button" class="btn btn-primary btn-xs">'+
//                                            '<span class="glyphicon">'+post.comments.length+'</span>'+
//                                        '</button>'+						
//                                        '|'+ 
//                                        '<button type="button" class="btn btn-primary btn-xs pst-like" title="Like">'+
//                                            '<span class="glyphicon glyphicon-thumbs-up"></span>'+
//                                        '</button>'+
//                                        '|'+
//                                        '<button type="button" class="btn btn-primary btn-xs">'+
//                                            '<span class="glyphicon">'+post.postLikes+' Likes</span>'+
//                                        '</button>'+						
//                                        '<button type="button" class="btn btn-danger btn-xs pst-delete" title="Delete">'+
//                                            '<span class="glyphicon glyphicon-trash"></span>'+
//                                        '</button>'+
//                                        '<span class="mic-info pull-right btn-xs">'+
//                                            'In: <a href="#">Montpellier</a> on '+post.getPostDate+
//                                        '</span>'+
//                                    '</div>'+
//                                    '<div class="row hidden">'+
//                                        '<form class="frm-comment">'+
//                                            '<input type="hidden" id="post-id" value="'+post.id+'">'+
//                                            '<input type="text" id="comment-cnt" class="col-md-offset-1 col-md-11" placeholder="Write a comment...">'+
//                                            '<input type="submit" class="hidden"/>'+
//                                        '</form>'+
//                                    '</div>'+
//                                '</div>'+
//                                '<div class="panel-body">'+
//                                    '<ul class="list-group row comments1">'+
//                                    
//                                    '</ul>'+
//                                '</div>'+
//                            '</div>'+
//                        '</div>'
//                    );
//                    $.each(post.comments, function(indx, cmnt) {                     
//                        $('.comments1').append(
//                             '<li class="list-group-item col-md-offset-1">'+
//                                 '<div class="comment-row">'+
//                                     '<input type="hidden" id="comment-id" value="'+cmnt.id+'"/>'+
//                                     '<div class="col-md-1">'+
//                                         '<img src="img/avatar.png" class="img-circle img-responsive" alt="" />'+
//                                     '</div>'+
//                                     '<div>'+
//                                         '<div>'+
//                                             'By : '+
//                                             '<a href="#" class="autor-login">'+
//                                                 '<b>'+cmnt.autor.login+'</b>'+
//                                             '</a>'+
//                                         '</div>'+
//                                         '<div class="comment-text">'+
//                                             cmnt.content+
//                                         '</div>'+
//                                         '<div class="action col-md-offset-1">'+
////                                             '@if(Likes.isLikedComment(com, Member.getMember(session().get("Connected")))){'+
////                                                 '<button type="button" class="btn btn-primary btn-xs cmt-like" title="Like">'+
////                                                     '<span class="glyphicon glyphicon-thumbs-down"></span>'+
////                                                 '</button>'+
////                                             '}else{'+
//                                                 '<button type="button" class="btn btn-primary btn-xs cmt-like" title="Like">'+
//                                                     '<span class="glyphicon glyphicon-thumbs-up"></span>'+
//                                                 '</button>'+
////
////                                             '}'+
//                                             '<button type="button" class="btn btn-primary btn-xs">'+
//                                                 '<span class="glyphicon">'+cmnt.commentLikes+'</span>'+
//                                             '</button>'+
////                                                 '@if(com.getAutor.getLogin==member){'+
////                                                     '|'+ 
//                                                     '<button type="button" class="btn btn-danger btn-xs cmt-delete" title="Delete">'+
//                                                         '<span class="glyphicon glyphicon-trash"></span>'+
//                                                     '</button>'+
////                                                 '}'+
//                                                 '<span class="mic-info pull-right btn-xs">'+
//                                                     'In: <a href="#">Montpellier</a> on :'+cmnt.commentDate+
//                                                 '</span>'+
//                                         '</div>'+
//                                     '</div>'+
//                                 '</div>'+
//                             '</li>'
//                         );
//                    });    
//                });
               
                $("#ipt-post").val('');
                //$("article").html(data);
            }
        });
        return false;
    });
    
    
    // Supprimer un post.
    $('section').on("click", ".pst-delete", function(e) {
        $postid = $(this).parents(".post").find("#post-id").val();
        $.ajax({
            type: 'POST',
            url: 'rest/ctrlposts/deletepost',
            contentType: "application/json; charset=UTF-8",
            data: "post-id="+$postid,
            success: function(data) {
                $("article").html(data);
            }
        });
        return false;
    });
    
    
     // Faire un like pour un post.
    $('section').on("click", ".pst-like", function(e) {
        $postid = $(this).parents(".post").find("#post-id").val();
        alert($postid);
        $.ajax({
            type: 'POST',
            url: 'rest/ctrllikes/likepost',
            contentType: "application/json; charset=UTF-8",
            data: "post-id="+$postid,
            success: function(data) {
                $("article").html(data);
            }
        });
        return false;
    });   

    //Afficher tous les posts

//    $(".btn-all").click(function(e) {
//        $.ajax({
//            type: 'GET',
//            url: '/posts',
//            contentType: "application/json; charset=UTF-8",
//            success: function(data) {
//                $('article').html(data);
//            }
//        });
//    });

    //Afficher les posts du membre connecte.

//    $(".btn-wall").click(function(e) {
//        $.ajax({
//            type: 'GET',
//            url: '/wall',
//            contentType: "application/json; charset=UTF-8",
//            success: function(data) {
//                $('article').html(data);
//            }
//        });
//    });






//=================================================================================================================
//==========================================   Comments   =========================================================
//=================================================================================================================	

    // Affichier le champ commentaire

    $('article').on("click", ".btn-comment", function(e) {
        $(this).parents('.action').next().removeClass("hidden");
    });

    // Commenter un post. 
    $('article').on("submit", ".frm-comment", function(e) {
        $postid = $(this).children("#post-id").val();
        $comment = $(this).children("#comment-cnt").val();
        $(this).children("#comment-cnt").val('');
        $.ajax({
            type: 'POST',
            url: 'rest/ctrlcomments/submitcomment',
            contentType: "application/json; charset=UTF-8",
            data: "post-id="+$postid+"&comment="+$comment,
            success: function(data) {
//                $('article').append('<link rel="stylesheet" media="screen" href="css/post.css">')
//                $('article').append('<ul class="list-group row comments"></ul>');                
//                $.each(json, function(index, value) {
//                $('.comments').append(
//                    '<li class="list-group-item col-md-offset-1">'+
//                        '<div class="comment-row">'+
//                            '<input type="hidden" id="comment-id" value="'+value.id+'"/>'+
//                            '<div class="col-md-1">'+
//				'<img src="img/avatar.png" class="img-circle img-responsive" alt="" />'+
//                            '</div>'+
//                            '<div>'+
//				'<div>'+
//                                    'By : '+
//                                    '<a href="#" class="autor-login">'+
//                                        '<b>'+value.login+'</b>'+
//                                    '</a>'+
//				'</div>'+
//				'<div class="comment-text">'+
//                                    value.content+
//                                '</div>'+
//				'<div class="action col-md-offset-1">'+
//                                    '@if(Likes.isLikedComment(com, Member.getMember(session().get("Connected")))){'+
//                                        '<button type="button" class="btn btn-primary btn-xs cmt-like" title="Like">'+
//                                            '<span class="glyphicon glyphicon-thumbs-down"></span>'+
//                                        '</button>'+
//                                    '}else{'+
//                                        '<button type="button" class="btn btn-primary btn-xs cmt-like" title="Like">'+
//                                            '<span class="glyphicon glyphicon-thumbs-up"></span>'+
//                                        '</button>'+
//
//                                    '}'+
//                                    '<button type="button" class="btn btn-primary btn-xs">'+
//                                        '<span class="glyphicon">@com.getLikeComment</span>'+
//                                    '</button>'+
//					'@if(com.getAutor.getLogin==member){'+
//                                            '|'+ 
//                                            '<button type="button" class="btn btn-danger btn-xs cmt-delete" title="Delete">'+
//                                                '<span class="glyphicon glyphicon-trash"></span>'+
//                                            '</button>'+
//                                        '}'+
//					'<span class="mic-info pull-right btn-xs">'+
//                                            'In: <a href="#">Montpellier</a> on : @com.getCommentDate.format("MMMM dd") at : @com.getCommentDate.format("hh:mm")'+
//					'</span>'+
//				'</div>'+
//                            '</div>'+
//                        '</div>'+
//                    '</li>'
//                );
//                });
//                alert(data);
//                //$("article").html(data);
            }
        });
        return false;
    });

    // Faire un like pour un commentaire.
//    $('article').on("click", ".cmt-like", function(e) {
//        $commentid = $(this).parents(".comment-row").find("#comment-id").val();
//        $.ajax({
//            type: 'POST',
//            url: '/likeComment',
//            contentType: "application/json; charset=UTF-8",
//            data: JSON.stringify({"comment-id": $commentid}),
//            success: function(data) {
//                $("article").html(data);
//            }
//        });
//        return false;
//    });

    // Supprimer un commentaire.

//    $('article').on("click", ".cmt-delete", function(e) {
//        $commentid = $(this).parents(".comment-row").find("#comment-id").val();
//        $.ajax({
//            type: 'POST',
//            url: '/deleteComment',
//            contentType: "application/json; charset=UTF-8",
//            data: JSON.stringify({"comment-id": $commentid}),
//            success: function(data) {
//                $("article").html(data);
//            }
//        });
//        return false;
//    });

//=================================================================================================================
//==========================================    Profile   =========================================================
//=================================================================================================================	

    // Afficher un profile.
//    $('article').on("click", ".autor-login", function(e) {
//        $login = $(this).find("b").text();
//        $.ajax({
//            type: 'POST',
//            url: '/profile',
//            contentType: "application/json; charset=UTF-8",
//            data: JSON.stringify({"login": $login}),
//            success: function(data) {
//                $('article').html(data);
//            }
//        });
//    });

    //Afficher le profile du membere connecte.
//    $(".view-profile").on("click", function(e) {
//        $.ajax({
//            type: 'GET',
//            url: '/viewprofile',
//            contentType: "application/json; charset=UTF-8",
//            success: function(data) {
//                $('article').html(data);
//            }
//        });
//    });

    //Afficher le formulaire pour la modification du profile du membre connecte.
//    $("article").on("click", ".btn-edit-profile", function(e) {
//        $.ajax({
//            type: 'GET',
//            url: '/editprofile',
//            contentType: "application/json; charset=UTF-8",
//            success: function(data) {
//                $('article').html(data);
//            }
//        });
//    });

    function affichage(json){
                $.each(json, function(index, post) {
                    $('article').append(
                        '<div class="row post">'+
                            '<div class="panel panel-default">'+
                                '<div class="panel-heading">'+
                                    '<div class="row">'+
                                        '<div class="col-md-1">'+
                                            '<img src="img/avatar.png" class="img-circle img-responsive" alt=""/>'+
                                        '</div>'+
                                        '<div>'+
                                            'By :'+
                                            '<a href="#" class="autor-login">'+
                                                '<b>'+post.autor.login+'</b>'+
                                            '</a>'+
                                            '<p>'+post.content+'</p>'+
                                        '</div>'+
                                    '</div>'+
                                    '<div class="row action">'+
                                        '<button type="button" class="btn btn-primary btn-xs col-md-offset-1 btn-comment" title="Comment">'+
                                            '<span class="glyphicon glyphicon-comment"></span>'+
                                        '</button>'+
                                        '<button type="button" class="btn btn-primary btn-xs">'+
                                            '<span class="glyphicon">'+post.comments.length+'</span>'+
                                        '</button>'+						
                                        '|'+ 
                                        '<button type="button" class="btn btn-primary btn-xs pst-like" title="Like">'+
                                            '<span class="glyphicon glyphicon-thumbs-up"></span>'+
                                        '</button>'+
                                        '|'+
                                        '<button type="button" class="btn btn-primary btn-xs">'+
                                            '<span class="glyphicon">'+post.postLikes+' Likes</span>'+
                                        '</button>'+						
                                        '<button type="button" class="btn btn-danger btn-xs pst-delete" title="Delete">'+
                                            '<span class="glyphicon glyphicon-trash"></span>'+
                                        '</button>'+
                                        '<span class="mic-info pull-right btn-xs">'+
                                            'In: <a href="#">Montpellier</a> on '+post.getPostDate+
                                        '</span>'+
                                    '</div>'+
                                    '<div class="row hidden">'+
                                        '<form class="frm-comment">'+
                                            '<input type="hidden" id="post-id" value="'+post.id+'">'+
                                            '<input type="text" id="comment-cnt" class="col-md-offset-1 col-md-11" placeholder="Write a comment...">'+
                                            '<input type="submit" class="hidden"/>'+
                                        '</form>'+
                                    '</div>'+
                                '</div>'+
                                '<div class="panel-body">'+
                                    '<ul class="list-group row comments1">'+
                                    
                                    '</ul>'+
                                '</div>'+
                            '</div>'+
                        '</div>'
                    );
                    $.each(post.comments, function(indx, cmnt) {                     
                        $('.comments1').append(
                             '<li class="list-group-item col-md-offset-1">'+
                                 '<div class="comment-row">'+
                                     '<input type="hidden" id="comment-id" value="'+cmnt.id+'"/>'+
                                     '<div class="col-md-1">'+
                                         '<img src="img/avatar.png" class="img-circle img-responsive" alt="" />'+
                                     '</div>'+
                                     '<div>'+
                                         '<div>'+
                                             'By : '+
                                             '<a href="#" class="autor-login">'+
                                                 '<b>'+cmnt.autor.login+'</b>'+
                                             '</a>'+
                                         '</div>'+
                                         '<div class="comment-text">'+
                                             cmnt.content+
                                         '</div>'+
                                         '<div class="action col-md-offset-1">'+
//                                             '@if(Likes.isLikedComment(com, Member.getMember(session().get("Connected")))){'+
//                                                 '<button type="button" class="btn btn-primary btn-xs cmt-like" title="Like">'+
//                                                     '<span class="glyphicon glyphicon-thumbs-down"></span>'+
//                                                 '</button>'+
//                                             '}else{'+
                                                 '<button type="button" class="btn btn-primary btn-xs cmt-like" title="Like">'+
                                                     '<span class="glyphicon glyphicon-thumbs-up"></span>'+
                                                 '</button>'+
//
//                                             '}'+
                                             '<button type="button" class="btn btn-primary btn-xs">'+
                                                 '<span class="glyphicon">'+cmnt.commentLikes+'</span>'+
                                             '</button>'+
//                                                 '@if(com.getAutor.getLogin==member){'+
//                                                     '|'+ 
                                                     '<button type="button" class="btn btn-danger btn-xs cmt-delete" title="Delete">'+
                                                         '<span class="glyphicon glyphicon-trash"></span>'+
                                                     '</button>'+
//                                                 '}'+
                                                 '<span class="mic-info pull-right btn-xs">'+
                                                     'In: <a href="#">Montpellier</a> on :'+cmnt.commentDate+
                                                 '</span>'+
                                         '</div>'+
                                     '</div>'+
                                 '</div>'+
                             '</li>'
                         );
                    });    
                });

    }
});