<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Page Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css"
          href="static/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="static/css/main.css" />
    <link rel="stylesheet" type="text/css" media="screen"
          href="static/css/homepage.css" />
    <script type="text/javascript" src="static/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="static/js/bootstrap.bundle.min.js"></script>
</head>

<body>
<c:set var="isadmin" value='<%=session.getAttribute("isadmin")%>'></c:set>
<div class="header">
    <img src="static/img/Hcmute.svg.png" class="logo" /> <span
        class="headerTitle">
			<h1>ICSSE 2017</h1>
			<h2>
				IEEE INTERNATIONAL CONFERENCE <br />ON SYSTEM SCIENCE AND
				ENGINEERING
			</h2>
		</span>
    <div class="navBar">
			<span class="row navContainer"> <span class="col-md-1.5 nav">
					<img src="static/img/svg/homeIcon.svg" />
					<button class="navBtn rightBorder"><a href="/" style="color: inherit">HOME</a></button>
			</span> <span class="col-md-1.5 nav">
					<button class="navBtn rightBorder">ABOUT</button>
			</span> <span class="col-md-1.5 nav">
					<button class="navBtn rightBorder">FOR AUTHOR</button>
			</span> <span class="col-md-1.5 nav">
					<button class="navBtn rightBorder">KEYNOTE SPEAKERS</button>
			</span> <span class="col-md-1.5 nav">
					<button class="navBtn rightBorder">PROGRAM</button>
			</span> <span class="col-md-1.5 nav">
					<button class="navBtn">PUBLICATION</button>
			</span>
			</span> <span class="latestNews">
				<div class="labelContainer">
					<label><a href="/tinmoicapnhat">NEWS</a></label>
				</div>
			</span>
    </div>
</div>
<div class="main">
    <div class="content">
        <div class="titleWrapper">
            <div class="title">
                <c:choose>
                    <c:when test="${isadmin == 'true' && isNews == false}">
                        <h1>
                                ${titlehp} <a href="/edithomepage">(Edit)</a>
                        </h1>
                    </c:when>
                    <c:when test="${isadmin != 'true' || isNews == true}">
                        <h1>${titlehp}</h1>
                    </c:when>
                </c:choose>

            </div>
        </div>
        <div class="body">${bodyhp}</div>
    </div>
    <div class="newsContainer">
        <c:forEach var="news" items="${listnews}">
            <div class="newsThread">
                <a href="/newsdetails?id=${news.id}">${news.date_created}</a>
                <p>${news.title}</p>
            </div>
        </c:forEach>
    </div>
</div>
</body>

</html>