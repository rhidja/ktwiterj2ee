$(document).ready(function($) {
    $(".btn-home").click(function(e) {
        window.location = '/';
    });
    $(".btn-admin").click(function(e) {
        $('article').load('admin.html');
        $.ajax({
            type: 'GET',
            url: 'rest/users',
            contentType: "application/json; charset=UTF-8",
            success: function(data) {
                alert("OK");
//                var json = JSON.parse(data);
//                $.each(json, function(index, value) {
//                    $(".list-group").append(
//                        '<li class="list-group-item">'+
//                            '<div class="checkbox">'+
//                                '<input type="checkbox" id="checkbox" />'+
//                                '<label for="checkbox">'+
//                                    value.login+
//                                '</label>'+
//                            '</div>'+
//                            '<div class="pull-right action-buttons">'+
//                                '<a href="#"><span class="glyphicon glyphicon-pencil"></span></a>'+
//                                '<a href="#" class="trash"><span class="glyphicon glyphicon-trash"></span></a>'+
//                                '<a href="#" class="flag"><span class="glyphicon glyphicon-flag"></span></a>'+
//                            '</div>'+
//                        '</li>'
//                    );
//                });
            }
        });
    });
    $(".btn-about").click(function(e) {
        $('article').load('about.html');
    });
    $(".btn-contact").click(function(e) {
        $('article').load('/contact');
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
            url: 'rest/signin',
            contentType: "application/json; charset=UTF-8",
            data: "login="+$login+"&password="+$password,
            success: function(data) {
                alert(data);
                //window.location = '/';
            }
        });
        return false;
    });

    // Se deconnecter.
//    $(".btn-logout").on("click", function(e) {
//        $.ajax({
//            url: '/logout',
//            success: function(data) {
//                window.location = '/';
//            }
//        });
//    });

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
            url: 'rest/signup',
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
        alert("OK");
        $login = (".btn-delete-member").parents("li").find("mbr-login").text();
        alert("login");
//        $login = $("#ipt-Login").val();
//        $email = $("#ipt-Email").val();
//        $password = $("#ipt-Password").val();
//        $.ajax({
//            type: 'POST',
//            url: 'rest/signup',
//            contentType: "application/json; charset=UTF-8",
//            data: "login="+$login+"&email="+$email+"&password="+$password,
//            success: function(data) {
//                alert(data);
//                //$('article').load('article.scala.html');
//            }
//        });
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
            url: 'rest/submitpost',
            contentType: "application/json; charset=UTF-8",
            data: "post="+$post,
            success: function(data) {
                alert("OK");
//                var json = data.toJSON();
//                $.each(json, function(index, value) {
//                      alert(value);
//                    $("article").append(   
//                        '<link rel="stylesheet" media="screen" href="css/post.css">'+
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
//                                                '<b>'+value.getAutor+'</b>'+
//                                            '</a>'+
//                                            '<p>'+value.getContent+'</p>'+
//                                        '</div>'+
//                                    '</div>'+
//                                    '<div class="row action">'+
//                                        '<button type="button" class="btn btn-primary btn-xs col-md-offset-1 btn-comment" title="Comment">'+
//                                            '<span class="glyphicon glyphicon-comment"></span>'+
//                                        '</button>'+
//                                        '<button type="button" class="btn btn-primary btn-xs">'+
//                                            '<span class="glyphicon">@post.getComments.size</span>'+
//                                        '</button>'+						
//                                        '|'+ 
//                                        '<button type="button" class="btn btn-primary btn-xs pst-like" title="Like">'+
//                                            '<span class="glyphicon glyphicon-thumbs-up"></span>'+
//                                        '</button>'+
//                                        '|'+
//                                        '<button type="button" class="btn btn-primary btn-xs">'+
//                                            '<span class="glyphicon">'+value.getLikes.size+' Likes</span>'+
//                                        '</button>'+						
//                                        '<button type="button" class="btn btn-danger btn-xs pst-delete" title="Delete">'+
//                                            '<span class="glyphicon glyphicon-trash"></span>'+
//                                        '</button>'+
//                                        '<span class="mic-info pull-right btn-xs">'+
//                                            'In: <a href="#">Montpellier</a> on '+value.getPostDate+
//                                        '</span>'+
//                                    '</div>'+
//                                    '<div class="row hidden">'+
//                                        '<form class="frm-comment">'+
//                                            '<input type="hidden" id="post-id" value="@post.getId">'+
//                                            '<input type="text" id="comment-cnt" class="col-md-offset-1 col-md-11" placeholder="Write a comment...">'+
//                                            '<input type="submit" class="hidden"/>'+
//                                        '</form>'+
//                                    '</div>'+
//                                '</div>'+
//                                '<div class="panel-body">'+
//                                    '<ul class="list-group row comments"></ul>'+
//                                '</div>'+
//                            '</div>'+
//                        '</div>'
//                    );    
//                });
               
                $("#ipt-post").val('');
                //$("article").html(data);

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

    // Faire un like pour un post.
//    $('section').on("click", ".pst-like", function(e) {
//        $postid = $(this).parents(".post").find("#post-id").val();
//        $.ajax({
//            type: 'POST',
//            url: '/likePost',
//            contentType: "application/json; charset=UTF-8",
//            data: JSON.stringify({"post-id": $postid}),
//            success: function(data) {
//                $("article").html(data);
//            }
//        });
//        return false;
//    });

    // Supprimer un post.
//    $('section').on("click", ".pst-delete", function(e) {
//        $postid = $(this).parents(".post").find("#post-id").val();
//        //*
//        $.ajax({
//            type: 'POST',
//            url: '/deletePost',
//            contentType: "application/json; charset=UTF-8",
//            data: JSON.stringify({"post-id": $postid}),
//            success: function(data) {
//                $("#article").html(data);
//            }
//        });
//        //*/
//        return false;
//    });


//=================================================================================================================
//==========================================   Comments   =========================================================
//=================================================================================================================	

    // Affichier le champ commentaire

//    $('article').on("click", ".btn-comment", function(e) {
//        $(this).parents('.action').next().removeClass("hidden");
//    });
//
//    // Commenter un post. 
//    $('article').on("submit", ".frm-comment", function(e) {
//        $postid = $(this).children("#post-id").val();
//        $comment = $(this).children("#comment-cnt").val();
//        $(this).children("#comment-cnt").val('');
//        $.ajax({
//            type: 'POST',
//            url: '/comment',
//            contentType: "application/json; charset=UTF-8",
//            data: JSON.stringify({"post-id": $postid, "comment": $comment}),
//            success: function(data) {
//                $("article").html(data);
//            }
//        });
//        return false;
//    });

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
});