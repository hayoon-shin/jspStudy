<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="main.css">
    <link rel="stylesheet" href="headerNav.css">
    <script src="main.js"></script>
    <script src="https://kit.fontawesome.com/70bcde4f7a.js" crossorigin="anonymous"></script>
</head>
<body onload="carousel();" >
<%@ include file="/join/headerNav.jsp"%>
     <div class="slideshow">
        <div class="slideshow_slides">
            <a href="#"><img src="slide-1.jpg" alt=""></a>
            <a href="#"><img src="slide-2.jpg" alt=""></a>
            <a href="#"><img src="slide-3.jpg" alt=""></a>
            <a href="#"><img src="slide-4.jpg" alt=""></a>
        </div>
        <div class="slideshow_nav">
            <a href="#" class="prev"><i class="fa-solid fa-circle-chevron-left"></i></a>
            <a href="#" class="next"><i class="fa-solid fa-circle-chevron-right"></i></a>
        </div>
        <div class="slideshow_indicator">
            <a href="#" class="active"><i class="fa-solid fa-circle-dot"></i></a>
            <a href="#"><i class="fa-solid fa-circle-dot"></i></a>
            <a href="#"><i class="fa-solid fa-circle-dot"></i></a>
            <a href="#"><i class="fa-solid fa-circle-dot"></i></a>
        </div>
    </div>