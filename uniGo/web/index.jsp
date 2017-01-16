<%@ page import="gr.inf.unigo.Student" %>
<%@ page import="java.io.File" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="apple-touch-icon" href="images/ios_logo.png"/>
    <link rel="apple-touch-startup-image" media="(device-width: 320px) and (device-height: 568px) and (-webkit-device-pixel-ratio: 2)"
          href="apple-touch-startup-image-640x1096.png">
    <title>uniGo</title>
    <link rel="stylesheet" href="css/framework7.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="css/colors/magenta.css">
    <link type="text/css" rel="stylesheet" href="css/swipebox.css"/>
    <link type="text/css" rel="stylesheet" href="css/animations.css"/>
    <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,300,700,900' rel='stylesheet' type='text/css'>
</head>
<body id="mobile_wrap">
<% Student student = ( Student ) session.getAttribute("user");
    File f = new File("/usr/share/tomcat/webapps/uniGo_war/images/profiles/" + student.getUserName() + ".jpg");
    String image;

    if ( !f.exists() )
         image = "images/profiles/empty-profile.png";
    else
        image = "images/profiles/" + student.getUserName() + ".jpg";
%>
<div class="statusbar-overlay"></div>

<div class="panel-overlay"></div>

<div class="panel panel-left panel-cover">
    <div class="user_login_info">
        <div class="user_thumb">
            <img id="prof" src="<%= image %>" alt="" title=""/>

            <div class="user_details">
                <p>Hi, <span id="dok"><%= student.getUserName() %></span></p>
            </div>
            <div id="result"></div>
            <div class="user_social">
                <form id="form-profile" method="post" name="form-profile">
                    <!--action="features.html"-->
                    <label for="file-input">
                        <img src="images/icons/white/upload-white.png"/>
                    </label>

                    <input id="file-input" type="file" name="image" accept="image/*"/>
                    <!--<input type="submit" name="submit" value="Submit" />-->
                </form>

            </div>
            <!--<div class="user_social">-->
            <!--<a href="#" data-popup=".popup-social" class="open-popup"><img src="images/icons/white/twitter.png" alt="" title="" /></a>              -->
            <!--</div>       -->
        </div>

        <nav class="user-nav">
            <ul>
                <li><a href="features.html" class="close-panel"><img src="images/icons/white/settings.png" alt="" title=""/><span>Settings</span></a></li>
                <li><a href="features.html" class="close-panel"><img src="images/icons/white/briefcase.png" alt="" title=""/><span>Account</span></a></li>
                <li><a href="features.html" class="close-panel"><img src="images/icons/white/message.png" alt="" title=""/><span>Messages</span><strong class="green">12</strong></a></li>
                <li><a href="features.html" class="close-panel"><img src="images/icons/white/download.png" alt="" title=""/><span>Downloads</span><strong class="blue">5</strong></a></li>
                <li><a href="index.html" class="external"><img src="images/icons/white/lock.png" alt="" title=""/><span>Logout</span></a></li>
            </ul>
        </nav>
    </div>
</div>

<div class="panel panel-right panel-cover">

    <h2>Search</h2>

    <div class="search_form">
        <form id="SearchForm" method="post">
            <input type="text" name="keyword" value="" class="search_input" placeholder="keyword"/>
            <input type="image" name="submit" class="search_submit" id="submit" src="images/icons/white/search.png"/>
        </form>
    </div>
    <div class="clear"></div>
    <h3>POPULAR POSTS</h3>
    <ul class="popular_posts">
        <li>
            <a href="blog-single.html" class="close-panel"><img src="images/photos/photo1.jpg" alt="" title=""/></a>
            <span><a href="blog-single.html" class="close-panel">Design is not just what it looks like and feels
                like.</a></span></li>
        <li>
            <a href="blog-single.html" class="close-panel"><img src="images/photos/photo2.jpg" alt="" title=""/></a>
            <span><a href="blog-single.html" class="close-panel">Fashion fades, only style remains the same.</a></span>
        </li>
        <li>
            <a href="blog-single.html" class="close-panel"><img src="images/photos/photo3.jpg" alt="" title=""/></a>
            <span><a href="blog-single.html" class="close-panel">Sed ut perspiciatis unde omnis iste
                accusantium.</a></span></li>
        <li>
            <a href="blog-single.html" class="close-panel"><img src="images/photos/photo4.jpg" alt="" title=""/></a>
            <span><a href="blog-single.html" class="close-panel">Nemo enim ipsam voluptatem quia voluptas.</a></span>
        </li>
        <li>
            <a href="blog-single.html" class="close-panel"><img src="images/photos/photo5.jpg" alt="" title=""/></a>
            <span><a href="blog-single.html" class="close-panel">Totam rem aperiam, eaque ipsa quae ab illo inventore
                veritatis.</a></span></li>
    </ul>
</div>

<div class="views">

    <div class="view view-main">

        <div class="pages  toolbar-through">

            <div data-page="index" class="page homepage">
                <div class="page-content">
                    <div class="logo"><img src="images/logo.png" alt="" title=""/></div>
                    <!-- Slider -->
                    <div class="swiper-container swiper-init" data-effect="slide" data-direction="vertical" data-pagination=".swiper-pagination">
                        <div class="swiper-wrapper">

                            <div class="swiper-slide">
                                <div class="slider-box">
                                    <%  String gender = student.getGender();
                                        if ( gender.equalsIgnoreCase( "Male" ) )
                                        {
                                            out.println( "<img id=\"avatar\" src=\"images/avatar_white.png\"/>");
                                        }
                                        else
                                        {
                                            out.println( "<img id=\"avatar\" src=\"images/avatar_female.png\"/>");

                                        }
                                    %>
                                    <div id="slider-text">Hi <%= student.getUserName() %></div>
                                </div>
                                <%--<span>MOBIX <br />IS <br /> AWESOME</span>--%>
                            </div>
                            <div class="swiper-slide">
                                <span>MOBIX <br />IS <br /> CREATIVE</span>
                                <a href="features.html" class="swiper_read_more">see all features</a>                     </div>
                            <div class="swiper-slide">
                                <span>MOBIX <br />IS <br /> ADAPTABLE</span>
                                <a href="#" class="swiper_read_more open-popup" data-popup=".popup-menu">start navigating</a>                      </div>
                        </div>
                        <div class="swiper-pagination"></div>
                    </div>
                </div>
            </div>


        </div>

        <!-- Bottom Toolbar-->
        <div class="toolbar">
            <div class="toolbar-inner">
                <ul class="toolbar_icons">
                    <li><a href="#" data-panel="left" class="open-panel"><img src="images/icons/white/user.png" alt="" title=""/></a></li>
                    <li><a href="photos.html"><img src="images/icons/white/tables.png" alt="" title=""/></a></li>
                    <li class="menuicon"><a href="checkin.html"><img src="images/map-white.png" alt="" title=""/></a></li>
                    <li><a href="announcements"><img src="images/icons/white/blog.png" alt="" title=""/></a></li>
                    <li><a href="rankings.html"><img src="images/icons/white/slider.png" alt="" title=""/></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/framework7.js"></script>
<script type="text/javascript" src="js/my-app.js"></script>
<script type="text/javascript" src="js/jquery.swipebox.js"></script>
<script type="text/javascript" src="js/email.js"></script>

<%--<!-- Upload photo when selecting photo -->--%>
<script>
    var formData = new FormData();
    $(document).ready(function(){

        $('#file-input').change(function(){

            var imageFile = this.files[0];

            formData.append('image', imageFile);

            var reader = new FileReader();

            // Closure to capture the file information.
            reader.onload = (function(theFile) {
                return function(e) {
                    document.getElementById('prof').setAttribute('src', e.target.result );
                };
            })(imageFile);

            // Read in the image file as a data URL.
            reader.readAsDataURL(imageFile);

            $.ajax({
                url : '/uniGo_war/imgUpload',
                type : 'POST',
                data : imageFile,
                processData: false,
                contentType: false,
//                dataType: 'json',
                success : function(data) {
                    $('#result').innerText = data;
                }
            });

            this.value = '';
        })
    });
</script>

<script type="text/javascript">
    function ldap_login( name, pass )
    {
        var msg = '(*) Sign-in failed! Wrong username or password! Try again in 1 minute';

        var error = '<p style="color:firebrick;font-size: larger" class="error_login">' + msg + '</p>';

        $.ajax({
            type: 'post',
            url: '/uniGo_war/login',
            data: {
                user_name:name,
                user_pass:pass
            },
            success: function (response) {

                if ( response == 'OK' )
                {
                    $( '.signup_bottom' ).html( response );

                    localStorage.userID = name;

                    document.getElementById( "cl-pop" ).click();
                }
                else
                {
                    error = '<p style="color:firebrick;font-size: larger" class="error_login">' + response + '</p>';
                    $( '.signup_bottom' ).html( error );
                }
            }
        });
    }
</script>
<script>
    var map;

    function initMap() {
        $("iframe").replaceWith("<div id=\"google_map\" style=\"width:100%;height:300px\" onclick=\"initMap()\"></div>");
        map = new google.maps.Map(document.getElementById('google_map'), {
            zoom: 12,
            center: new google.maps.LatLng(39.3622, 22.9422)
        });
    }
</script>
<script
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCDbFAAXlBKqQajVIxCbLBF-lz2ZIHp2Jg">
</script>
</body>
</html>